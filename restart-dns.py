# Greg Westmoreland
# Task 1 - D2

import paramiko
import time


DNS_HOST = "127.0.0.1"
DNS_PORT = 5013
DNS_USERNAME = "ubuntu"
DNS_PASSWORD = "ubuntu"
DNS_SERVICE = "bind9"

def run_ssh_command(client, command):
    _, stdout, stderr = client.exec_command(command)
    out = stdout.read().decode()
    err = stderr.read().decode()
    return out if out else err

def restart_dns_service(host, port, username, password, service):
    client = paramiko.SSHClient()
    client.set_missing_host_key_policy(paramiko.AutoAddPolicy())

    print(f"Attempting connection to DNS server with IP/Port {host}:{port} ...")
    client.connect(hostname=host, port=port, username=username, password=password, timeout=15)
    print("Successfully connected.\n")

    print(f"Step [1 of 3] Checking current status of {service} ...")
    pre_status = run_ssh_command(client, f"sudo systemctl status {service} --no-pager")
    print(pre_status)


    print(f"Step [2 of 3] Restarting {service} ...")
    run_ssh_command(client, f"sudo systemctl restart {service}")
    time.sleep(3)

    print(f"Step [3 of 3] Verifying {service} status after restart ...")
    post_status = run_ssh_command(client, f"sudo systemctl status {service} --no-pager")
    print(post_status)

    client.close()
    return post_status

def main():
    post_status = restart_dns_service(
        DNS_HOST,
        DNS_PORT,
        DNS_USERNAME,
        DNS_PASSWORD,
        DNS_SERVICE
    )

    print("=" * 50)
    if "active (running)" in post_status:
        print(f"[SUCCESS] {DNS_SERVICE} is confirmed to be UP and running.")
    else:
        print(f"[FAILURE] {DNS_SERVICE} did not start correctly. Manual intervention is needed.")

if __name__ == "__main__":
    main()