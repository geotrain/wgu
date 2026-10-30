# Greg Westmoreland
# Task 1 - D1

import paramiko

DNS_HOST = "127.0.0.1"
DNS_PORT = 5013
DNS_USERNAME = "ubuntu"
DNS_PASSWORD = "ubuntu"
DNS_SERVICE = "bind9"

def check_dns_service(host, port, username, password, service):
    client = paramiko.SSHClient()
    client.set_missing_host_key_policy(paramiko.AutoAddPolicy())

    print(f"Attempting connection to DNS server with IP/Port {host}:{port} ...")
    client.connect(hostname=host, port=port, username=username, password=password, timeout=15)
    print("Successfully connected.\n")

    _, stdout, stderr = client.exec_command(
        f"sudo systemctl status {service} --no-pager"
    )
    output = stdout.read().decode()
    error  = stderr.read().decode()
    client.close()

    return output if output else error

def main():
    status_output = check_dns_service(
        DNS_HOST,
        DNS_PORT,
        DNS_USERNAME,
        DNS_PASSWORD,
        DNS_SERVICE
    )

    print(f"DNS Service ({DNS_SERVICE}) Status:")
    print("=" * 50)
    print(status_output)

    if "inactive" in status_output or "failed" in status_output:
        print(f"\n[CONFIRMED] {DNS_SERVICE} is confirmed to be DOWN. Immediate remediation required.")
    elif "active (running)" in status_output:
        print(f"\n[INFO] {DNS_SERVICE} is confirmed to be currently UP.")
    else:
        print(f"\n[UNKNOWN] Could not determine {DNS_SERVICE} status.")

if __name__ == "__main__":
    main()