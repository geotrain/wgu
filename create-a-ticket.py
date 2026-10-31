# Greg Westmoreland
# Task 2 - Part C3

import csv
import os
import subprocess
import requests
from datetime import datetime

CSV_FILE = "network-devices.csv"
TICKET_URL = "http://10.10.10.200:8080/api/tickets"
ISSUE_TYPE = "Device Unavailable"

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

def create_ticket(device, timestamp):
    payload = {
        "device_id": device["Device ID"],
        "device_name": device["Device Name"],
        "ip_address": device["Device Address"],
        "issue_type": ISSUE_TYPE,
        "description": (
            f"Device '{device['Device Name']}' at {device['Device Address']} "
            f"is not responding to ping. Ping check failed at {timestamp}."
        ),
        "timestamp": timestamp,
        "status": "Open",
    }
    response = requests.post(TICKET_URL, json=payload, timeout=10)
    return response

def main():
    script_dir = os.path.dirname(os.path.abspath(__file__))
    csv_path = os.path.join(script_dir, CSV_FILE)
    devices = read_devices(csv_path)
    timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    print(f"Scanning {len(devices)} device(s) and creating tickets for any unavailable ones...\n")
    print(f"{'Device':<12}  {'IP':<18}  {'Ping':<8}  Ticket Result")
    print("  " + "-" * 60)

    for device in devices:
        name = device["Device Name"]
        ip = device["Device Address"]
        up = ping_device(ip)

        if up is None:
            print(f"{name:<12} {ip:<18} {'UNKNOWN':<8} Skipped (no static IP)")
            continue
        if up:
            print(f"{name:<12} {ip:<18} {'UP':<8} No ticket needed")
            continue

        try:
            resp = create_ticket(device, timestamp)
            if resp.status_code in (200, 201):
                print(f"{name:<12} {ip:<18} {'DOWN':<8} Ticket created: {resp.status_code}")
            else:
                print(f"{name:<12} {ip:<18} {'DOWN':<8} Ticket FAILED: {resp.status_code} {resp.text[:40]}")
        except Exception as e:
            print(f"{name:<12} {ip:<18} {'DOWN':<8} ERROR: {e}")

if __name__ == "__main__":
    main()