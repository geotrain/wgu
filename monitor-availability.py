# Greg Westmoreland
# Task 2 - Part C2

import csv
import os
import subprocess
import smtplib
from datetime import datetime
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText

CSV_FILE = "network-devices.csv"
SMTP_HOST = "10.10.10.100"
SMTP_PORT = 25
FROM_ADDR = "nms@my-company.local"
TO_ADDR = "admin@my-company.local"

def read_devices(csv_path):
    with open(csv_path, newline="") as f:
        return list(csv.DictReader(f))

def ping_device(ip):
    if ip in ("DHCP", "None"):
        return None
    result = subprocess.run(
        ["ping", "-c", "1", "-W", "2", ip],
        stdout=subprocess.DEVNULL,
        stderr=subprocess.DEVNULL,
    )
    return result.returncode == 0

def send_unavailable_email(device_name, ip_address, timestamp):
    subject = f"Network Device Unavailable: {device_name} ({ip_address})"
    body = f"""Dear Network Administrator,

    This is an automated notification that the following network device is currently unavailable:

        Device Name: {device_name}
        IP Address: {ip_address}
        Last Checked: {timestamp}

    Please investigate this issue at your earliest convenience.

    Best regards,
    Network Monitoring System Team"""

    msg = MIMEMultipart()
    msg["From"] = FROM_ADDR
    msg["To"]  = TO_ADDR
    msg["Subject"] = subject
    msg.attach(MIMEText(body, "plain"))

    with smtplib.SMTP(SMTP_HOST, SMTP_PORT, timeout=10) as server:
        server.sendmail(FROM_ADDR, [TO_ADDR], msg.as_string())

def main():
    script_dir = os.path.dirname(os.path.abspath(__file__))
    csv_path = os.path.join(script_dir, CSV_FILE)
    devices = read_devices(csv_path)
    timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")

    print(f"Checking availability of {len(devices)} device(s)...\n")
    print(f"{'Device':<12} {'IP':<18} Status")
    print("  " + "-" * 44)

    for device in devices:
        name = device["Device Name"]
        ip = device["Device Address"]
        up = ping_device(ip)

        if up is None:
            status = "UNKNOWN (DHCP/No IP)"
        elif up:
            status = "UP"
        else:
            status = "DOWN"
            try:
                send_unavailable_email(name, ip, timestamp)
                status += "  → Alert email sent"
            except Exception as e:
                status += f"  → Email ERROR: {e}"

        print(f"{name:<12} {ip:<18} {status}")

if __name__ == "__main__":
    main()