# Greg Westmoreland
# Task 1 - D4

import csv
import os
import paramiko

CSV_FILE = "network-device-list.csv"

CORRECT_DNS = "10.10.10.10"
FALLBACK_DNS = "10.10.10.20"
SEARCH_DOMAIN = "company.local"

RESOLV_CONTENT = (
    f"# Managed by Network Monitoring System Team\n"
    f"search {SEARCH_DOMAIN}\n"
    f"nameserver {CORRECT_DNS}\n"
    f"nameserver {FALLBACK_DNS}\n"
)

def read_devices(csv_path):
    with open(csv_path, newline="") as f:
        return list(csv.DictReader(f))

def fix_dns_on_device(device):
    host = device["Device Address"]
    username = device["Username"]
    password = device["Password"]
    name = device["Device Name"]

    client = paramiko.SSHClient()
    client.set_missing_host_key_policy(paramiko.AutoAddPolicy())
    client.connect(hostname=host, port=22, username=username, password=password, timeout=15)

    _, stdout, _ = client.exec_command("cat /etc/resolv.conf")
    before = stdout.read().decode().strip()

    escaped = RESOLV_CONTENT.replace("'", "'\\''")
    cmd = f"echo '{escaped}' | sudo tee /etc/resolv.conf > /dev/null"
    client.exec_command(cmd)

    _, stdout, _ = client.exec_command("cat /etc/resolv.conf")
    after = stdout.read().decode().strip()

    client.close()
    return before, after

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

    print(f"Correcting DNS settings on {len(targets)} device(s)...\n")

    for device in targets:
        name = device["Device Name"]
        ip = device["Device Address"]
        print(f"--- {name} ({ip}) ---")
        try:
            before, after = fix_dns_on_device(device)
            print(f"Before:\n {before}")
            print(f"Afterf:\n {after}")
            if CORRECT_DNS in after:
                print(f"[SUCCESS] DNS corrected on {name}.")
            else:
                print(f"[WARNING] DNS may not have been updated correctly on {name}.")
        except Exception as e:
            print(f"[ERROR] Could not connect to {name}: {e}")
        print()

if __name__ == "__main__":
    main()