# Greg Westmoreland
# Task 2 - Part C4

import csv
import os
import smtplib
import requests
import paramiko
from datetime import datetime
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText

CSV_FILE = "network-devices.csv"
EXPECTED_DNS = "10.10.10.10"
FALLBACK_DNS = "10.10.10.20"
SEARCH_DOMAIN = "company.local"
RESOLV_CONTENT = (
    f"#Managed by Network Monitoring System\n"
    f"search {SEARCH_DOMAIN}\n"
    f"nameserver {EXPECTED_DNS}\n"
    f"nameserver {FALLBACK_DNS}\n"
)
SMTP_HOST = "10.10.10.100"
SMTP_PORT = 25
FROM_ADDR = "nms@my-company.local"
TO_ADDR = "admin@my-company.local"
TICKET_URL = "http://10.10.10.200:8080/api/tickets"

def read_devices(csv_path):
    with open(csv_path, newline="") as f:
        return list(csv.DictReader(f))

def get_current_dns(device):
    client = paramiko.SSHClient()
    client.set_missing_host_key_policy(paramiko.AutoAddPolicy())
    client.connect(hostname="127.0.0.1", port=int(device["Access Port"]), username=device["Username"], password=device["Password"], timeout=15)
    _, stdout, _ = client.exec_command("grep '^nameserver' /etc/resolv.conf | head -1 | awk '{print $2}'")
    current_dns = stdout.read().decode().strip()
    client.close()
    return current_dns

def correct_dns(device):
    client = paramiko.SSHClient()
    client.set_missing_host_key_policy(paramiko.AutoAddPolicy())
    client.connect(hostname="127.0.0.1", port=int(device["Access Port"]), username=device["Username"], password=device["Password"], timeout=15)
    escaped = RESOLV_CONTENT.replace("'", "'\\''")
    client.exec_command(f"echo '{escaped}' | sudo tee /etc/resolv.conf > /dev/null")
    client.close()

def send_email(subject, body):
    msg = MIMEMultipart()
    msg["From"] = FROM_ADDR
    msg["To"] = TO_ADDR
    msg["Subject"] = subject
    msg.attach(MIMEText(body, "plain"))
    with smtplib.SMTP(SMTP_HOST, SMTP_PORT, timeout=10) as server:
        server.sendmail(FROM_ADDR, [TO_ADDR], msg.as_string())

def send_altered_email(name, ip, current_dns, timestamp):
    subject = f"DNS Configuration Alert: {name} ({ip})"
    body = f"""Dear Network Administrator,

    This is an automated alert that the DNS configuration for the following device has been altered from the expected settings:

        Device Name: {name}
        IP Address: {ip}
        Detected DNS Setting: {current_dns}
        Expected DNS Setting: {EXPECTED_DNS}
        Time Detected: {timestamp}

    The system will attempt to automatically correct this configuration.

    Best regards,
    Network Monitoring System Team"""
    send_email(subject, body)

def send_corrected_email(name, ip, timestamp):
    subject = f"DNS Configuration Corrected: {name} ({ip})"
    body = f"""Dear Network Administrator,

    The DNS configuration issue previously detected on the following device has been automatically corrected:

        Device Name: {name}
        IP Address: {ip}
        Corrected DNS Setting: {EXPECTED_DNS}
        Time Corrected: {timestamp}

    No further action is required at this time.
    
    Best regards,
    Network Monitoring System Team"""
    send_email(subject, body)

def create_ticket(device, current_dns, timestamp):
    payload = {
        "device_id": device["Device ID"],
        "device_name": device["Device Name"],
        "ip_address": device["Device Address"],
        "issue_type": "DNS Configuration Altered",
        "description": (
            f"DNS setting on '{device['Device Name']}' was altered. "
            f"Detected: {current_dns} | Expected: {EXPECTED_DNS}. "
            f"Detected at {timestamp}."
        ),
        "timestamp": timestamp,
        "status": "Open",
    }
    resp = requests.post(TICKET_URL, json=payload, timeout=10)
    return resp

def update_ticket(ticket_id, timestamp):
    payload = {
        "status": "Resolved",
        "resolved_at": timestamp,
        "resolution": f"DNS setting automatically corrected to {EXPECTED_DNS}.",
    }
    resp = requests.patch(f"{TICKET_URL}/{ticket_id}", json=payload, timeout=10)
    return resp

def main():
    script_dir = os.path.dirname(os.path.abspath(__file__))
    csv_path = os.path.join(script_dir, CSV_FILE)
    devices = read_devices(csv_path)

    targets = [
        d for d in devices
        if d["OS"] == "Ubuntu"
        and d["Device Address"] not in ("DHCP", "None")
        and d["Username"] != "none"
    ]

    print(f"Monitoring DNS settings on {len(targets)} device(s)...\n")

    for device in targets:
        name = device["Device Name"]
        ip = device["Device Address"]
        timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")

        print(f"--- {name} ({ip}) ---")
        try:
            current_dns = get_current_dns(device)
            print(f"  Current DNS : {current_dns}")
            print(f"  Expected DNS: {EXPECTED_DNS}")

            if current_dns != EXPECTED_DNS:
                print(f"  [ALERT] DNS mismatch detected!")
                send_altered_email(name, ip, current_dns, timestamp)
                print(f"  [EMAIL] DNS Altered Notification sent.")

                ticket_id = None
                try:
                    resp = create_ticket(device, current_dns, timestamp)
                    if resp.status_code in (200, 201):
                        ticket_id = resp.json().get("id") or resp.json().get("ticket_id")
                        print(f"[TICKET] Created (ID: {ticket_id})")
                    else:
                        print(f"[TICKET] Creation failed: {resp.status_code}")
                except Exception as e:
                    print(f"[TICKET] Error: {e}")

                correct_dns(device)
                print(f"[FIX] DNS corrected to {EXPECTED_DNS}.")

                corrected_ts = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
                send_corrected_email(name, ip, corrected_ts)
                print(f"[EMAIL] DNS Corrected Notification sent.")

                if ticket_id:
                    try:
                        update_ticket(ticket_id, corrected_ts)
                        print(f"[TICKET] Updated to Resolved.")
                    except Exception as e:
                        print(f"[TICKET] Update error: {e}")
            else:
                print(f"[OK] DNS setting is correct. No action needed.")

        except Exception as e:
            print(f"[ERROR] {e}")
        print()

if __name__ == "__main__":
    main()