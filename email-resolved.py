# Greg Westmoreland
# Task 1 - E

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

def read_devices(csv_path):
    with open(csv_path, newline="") as f:
        return list(csv.DictReader(f))

def build_device_list(devices):
    lines = []
    for d in devices:
        if d["Device Address"] not in ("DHCP", "None"):
            lines.append(f"  - {d['Device Name']} ({d['Device Address']})")
    return "\n".join(lines)

def build_resolution_email(device_list_str):
    subject = "RESOLVED: DNS Service Issue and Device Compromise—All Issues Remediated"
    body = f"""Dear Stakeholders,

    This is an automated notification to inform you that the DNS service issue and all related device compromises have been successfully resolved at this time. The following devices were affected and have now been mitigated:

        {device_list_str}

    No further action is need at this time. If you have any comments, questions, or concerns, please direct them to the Incident Response Team.

    Thank you,
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
    device_list_str = build_device_list(devices)
    subject, body = build_resolution_email(device_list_str)
    print("Sending resolution notification email...")
    print(f"\nSubject: {subject}")
    print(f"\nAffected devices included:\n{device_list_str}\n")

    try:
        send_email(subject, body)
        print(f"[SENT] The mitigation email has been successfully sent at {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}")
    except Exception as e:
        print(f"[ERROR] There was an error to send the mitigation email: {e}")

if __name__ == "__main__":
    main()