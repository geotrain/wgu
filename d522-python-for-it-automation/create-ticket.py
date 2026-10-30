# Greg Westmoreland
# Task 1 - C2

import csv
import os
import requests
from datetime import datetime

CSV_FILE = "network-device-list.csv"
TICKET_URL = "http://helpdesk.d522.wgu.internal:5000/api/tickets"
API_TOKEN = "vGkbXkGLqQSo7YLflp9DutuG8st4xdPPF7wnTcwB0FE"
ISSUE_TYPE = "DNS Compromise"
ISSUE_SERVICE = "DNS"

def read_devices(csv_path):
    with open(csv_path, newline="") as f:
        return list(csv.DictReader(f))

def create_ticket(device):
    timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    payload = {
        "title": f"DNS Compromise - {device['Device Name']}",
        "device_id": device["Device ID"],
        "device_name": device["Device Name"],
        "ip_address": device["Device Address"],
        "issue_type": ISSUE_TYPE,
        "service": ISSUE_SERVICE,
        "description": (
            f"Device '{device['Device Name']}' with {device['Device Address']} "
            f"has been identified as being compromised."
            f"Unauthorized DNS configuration has been detected. Immediate remediation required."
            f"Please contact CISO and Incident Response Team."
        ),
        "timestamp": timestamp,
        "status": "open",
    }

    headers = {"Authorization": f"Bearer {API_TOKEN}"}
    response = requests.post(TICKET_URL, json=payload, headers=headers, timeout=10)
    return response

def main():
    script_dir = os.path.dirname(os.path.abspath(__file__))
    csv_path = os.path.join(script_dir, CSV_FILE)
    devices = read_devices(csv_path)

    print(f"Creating tickets for {len(devices)} device(s)...\n")
    print(f"{'Device':<10} {'IP':<18} {'Status':<8} Response")
    print("=" * 70)

    for device in devices:
        name = device["Device Name"]
        ip = device["Device Address"]
        try:
            resp = create_ticket(device)
            if resp.status_code in (200, 201):
                print(f"{name:<10} {ip:<18} {'OK':<8}  {resp.status_code} - {resp.text[:60]}")
            else:
                print(f"{name:<10} {ip:<18} {'FAIL':<8}  {resp.status_code} - {resp.text[:60]}")
        except Exception as e:
            print(f"{name:<10} {ip:<18} {'ERROR':<8} {e}")

if __name__ == "__main__":
    main()