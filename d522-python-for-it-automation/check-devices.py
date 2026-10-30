# Greg Westmoreland
# Task 1 - B2

import csv
import os
import subprocess
import paramiko

CSV_FILE = "network-device-list.csv"
EXPECTED_DNS = "10.10.10.10"
ROGUE_DNS = "8.8.8.8"

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

def get_dns_via_ssh(ip, port, username, password):
    try:
        client = paramiko.SSHClient()
        client.set_missing_host_key_policy(paramiko.AutoAddPolicy())
        client.connect(hostname=ip, port=22, username=username, password=password, timeout=10)
        _, stdout, _ = client.exec_command("grep '^nameserver' /etc/resolv.conf")
        dns_lines = stdout.read().decode().strip()
        client.close()
        return dns_lines if dns_lines else "No nameserver entry can be found"
    except Exception as e:
        return f"SSH error occurred: {e}"

def main():
    script_dir = os.path.dirname(os.path.abspath(__file__))
    csv_path = os.path.join(script_dir, CSV_FILE)
    devices = read_devices(csv_path)

    print(f"{'Device':<10}  {'IP':<18}  {'Ping':<8}  {'DNS Setting'}")
    print("=" * 70)

    for dev in devices:
        name = dev["Device Name"]
        ip = dev["Device Address"]
        port = dev["Access Port"]
        user = dev["Username"]
        passwd = dev["Password"]
        os_type = dev["OS"]

        up = ping_device(ip)
        if up is None:
            status = "UNKNOWN"
        elif up:
            status = "UP"
        else:
            status = "DOWN"

        if up and os_type == "Ubuntu" and user != "none":
            dns = get_dns_via_ssh(ip, port, user, passwd)
        else:
            dns = "N/A"

        print(f"{name:<10} {ip:<18} {status:<8} {dns}")

if __name__ == "__main__":
    main()