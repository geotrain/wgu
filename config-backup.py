# Greg Westmoreland
# Task 2 - Part B

import os
import paramiko

# ── DNS server connection details (from network-device-list.csv) ─────────────────
DNS_SERVERS = [
    {
        "label": "Server-1",
        "name": "DNS1",
        "host": "127.0.0.1",
        "port": 5013,
        "username": "ubuntu",
        "password": "ubuntu",
        "config_file": "/etc/bind/named.conf",
        "zone_dir": "/etc/bind/",
    },
    {
        "label": "Server-2",
        "name": "DNS2",
        "host": "127.0.0.1",
        "port": 5017,
        "username": "ubuntu",
        "password": "ubuntu",
        "config_file": "/etc/bind/named.conf",
        "zone_dir": "/etc/bind/",
    },
]

BACKUP_ROOT = "DNS-Backup"

def fetch_dns_config(server):
    client = paramiko.SSHClient()
    client.set_missing_host_key_policy(paramiko.AutoAddPolicy())
    client.connect(hostname=server["host"], port=server["port"], username=server["username"], password=server["password"], timeout=15)
    _, stdout, _ = client.exec_command(f"sudo cat {server['config_file']}")
    config = stdout.read().decode()
    _, stdout, _ = client.exec_command(f"ls {server['zone_dir']}")
    zone_listing = stdout.read().decode()
    client.close()
    return config, zone_listing

def create_backup(server, backup_root):
    subdir = os.path.join(backup_root, server["label"])
    os.makedirs(subdir, exist_ok=True)

    print(f"  Fetching config from {server['name']} (port {server['port']}) ...")
    try:
        config, zone_listing = fetch_dns_config(server)
        record_file = os.path.join(subdir, "record-config.txt")
        with open(record_file, "w") as f:
            f.write(f"# DNS Record Configuration Backup\n")
            f.write(f"# Server: {server['name']}\n")
            f.write(f"# Source: {server['config_file']}\n")
            f.write(f"# {'=' * 50}\n\n")
            f.write(config)
            f.write(f"\n\n# Zone file listing ({server['zone_dir']}):\n")
            f.write(zone_listing)
        print(f"[OK] Config saved to {record_file}")
    except Exception as e:
        record_file = os.path.join(subdir, "record-config.txt")
        with open(record_file, "w") as f:
            f.write(f"# DNS Record Configuration Backup\n")
            f.write(f"# Server: {server['name']}\n")
            f.write(f"# ERROR : Could not retrieve live config — {e}\n")
        print(f"[WARNING] SSH failed for {server['name']}: {e}. Placeholder written.")

def main():
    script_dir = os.path.dirname(os.path.abspath(__file__))
    backup_root = os.path.join(script_dir, BACKUP_ROOT)
    print(f"Creating DNS backup directory at: {backup_root}\n")

    for server in DNS_SERVERS:
        create_backup(server, backup_root)

    print(f"\nBackup complete. Directory structure:")
    for root, dirs, files in os.walk(backup_root):
        level = root.replace(backup_root, "").count(os.sep)
        indent = "  " * level
        print(f"{indent}{os.path.basename(root)}/")
        for file in files:
            print(f"{indent}  {file}")

if __name__ == "__main__":
    main()