"""
Task 2 - Part C5
Monitors DNS service health on all Ubuntu devices and logs an entry
(device name, date, time) when the DNS service is functioning correctly
and the DNS setting has not been altered.
"""

import csv
import os
import paramiko
from datetime import datetime

CSV_FILE  = "network-devices.csv"
LOG_FILE  = "dns_health.log"

EXPECTED_DNS = "10.10.10.10"
DNS_SERVICE  = "bind9"


def read_devices(csv_path):
    with open(csv_path, newline="") as f:
        return list(csv.DictReader(f))


def check_device_dns(device):
    """
    SSH to a device and return (current_dns, dns_service_active).
    dns_service_active is only checked on DNS servers.
    """
    client = paramiko.SSHClient()
    client.set_missing_host_key_policy(paramiko.AutoAddPolicy())
    client.connect(hostname="127.0.0.1", port=int(device["Access Port"]),
                   username=device["Username"], password=device["Password"],
                   timeout=15)

    # Check resolv.conf nameserver
    _, stdout, _ = client.exec_command(
        "grep '^nameserver' /etc/resolv.conf | head -1 | awk '{print $2}'"
    )
    current_dns = stdout.read().decode().strip()

    # Check bind9 service only on DNS servers
    dns_service_ok = None
    if device["Device Name"].startswith("DNS"):
        _, stdout, _ = client.exec_command(f"systemctl is-active {DNS_SERVICE}")
        service_state = stdout.read().decode().strip()
        dns_service_ok = (service_state == "active")

    client.close()
    return current_dns, dns_service_ok


def write_log(log_path, entry):
    with open(log_path, "a") as f:
        f.write(entry + "\n")


def main():
    script_dir = os.path.dirname(os.path.abspath(__file__))
    csv_path   = os.path.join(script_dir, CSV_FILE)
    log_path   = os.path.join(script_dir, LOG_FILE)
    devices    = read_devices(csv_path)

    # Monitor only Ubuntu devices with static IPs
    targets = [
        d for d in devices
        if d["OS"] == "Ubuntu"
        and d["Device Address"] not in ("DHCP", "None")
        and d["Username"] != "none"
    ]

    print(f"Checking DNS health on {len(targets)} device(s)...\n")
    print(f"  {'Device':<12}  {'IP':<18}  {'DNS OK':<8}  {'Svc OK':<8}  Log Entry")
    print("  " + "-" * 70)

    for device in targets:
        name = device["Device Name"]
        ip   = device["Device Address"]
        now  = datetime.now()
        ts   = now.strftime("%Y-%m-%d %H:%M:%S")

        try:
            current_dns, svc_ok = check_device_dns(device)
            dns_ok = (current_dns == EXPECTED_DNS)

            dns_label = "OK" if dns_ok else f"ALTERED({current_dns})"
            svc_label = "OK" if svc_ok else ("DOWN" if svc_ok is False else "N/A")

            if dns_ok and svc_ok is not False:
                log_entry = (
                    f"[{ts}] Device: {name:<12} | IP: {ip:<15} | "
                    f"DNS: {current_dns} (correct) | Service: {svc_label} | Status: HEALTHY"
                )
                write_log(log_path, log_entry)
                print(f"  {name:<12}  {ip:<18}  {dns_label:<8}  {svc_label:<8}  Logged")
            else:
                print(f"  {name:<12}  {ip:<18}  {dns_label:<8}  {svc_label:<8}  NOT logged (issue detected)")

        except Exception as e:
            print(f"  {name:<12}  {ip:<18}  {'ERROR':<8}  {'N/A':<8}  {e}")

    print(f"\nLog file: {log_path}")


if __name__ == "__main__":
    main()
