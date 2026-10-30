# Greg Westmoreland
# Task 1 - C1

import csv
import os
import smtplib
from datetime import datetime
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText

CSV_FILE = "network-device-list.csv"

SMTP_HOST = "smtp.d522.wgu.internal"
SMTP_PORT = 1025
FROM_ADDR = "nms@d522.wgu.internal"
TO_ADDR = "stakeholders@d522.wgu.internal"

COMPROMISED_SERVICE = "DNS"

def read_devices(csv_path):
    with open(csv_path, newline="") as f:
        return list(csv.DictReader(f))
def build_alert_email(device_name, ip_address, timestamp):
    subject = "URGENT: Device Compromise Detected—Immediate Attention Required"
    body = f"""Dear Stakeholders,

    This is an automated alert to inform you that the following device(s) have been identified as compromised during the recent network scan:

        Device Name: {device_name}
        IP Address: {ip_address}
        Last Checked: {timestamp}

    An immediate investigation and remediation are recommended to prevent further impact.

    If you have any questions or require additional information, please contact the IT support team.

    Sincerely,
    Network Monitoring Service Team"""

    return subject, body

def send_email(subject, body):
    msg = MIMEMultipart()
    msg["From"] = FROM_ADDR
    msg["To"] = TO_ADDR
    msg["Subject"] = subject
    msg.attach(MIMEText(body, "plain"))

    with smtplib.SMTP(SMTP_HOST, SMTP_PORT, timeout=10) as server:
        server.sendmail(FROM_ADDR, [TO_ADDR], msg.as_string())

def main():
    script_dir = os.path.dirname(os.path.abspath(__file__))
    csv_path = os.path.join(script_dir, CSV_FILE)
    devices = read_devices(csv_path)
    timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    compromised = [d for d in devices if d["Device Address"] not in ("DHCP", "None")]

    for device in compromised:
        name = device["Device Name"]
        ip = device["Device Address"]
        subject, body = build_alert_email(name, ip, timestamp)

        try:
            send_email(subject, body)
            print(f"[SENT] An alert email has been sent for device: {name} ({ip})")
        except Exception as e:
            print(f"[ERROR] Was not able to send an alert for {name}: {e}")

if __name__ == "__main__":
    main()
