# Greg Westmoreland
# Task 2 - Part C1

import csv
import os

CSV_FILE = "network-devices.csv"

def read_devices(csv_path):
    with open(csv_path, newline="") as f:
        return list(csv.DictReader(f))

def main():
    script_dir = os.path.dirname(os.path.abspath(__file__))
    csv_path = os.path.join(script_dir, CSV_FILE)

    devices = read_devices(csv_path)

    print("Network Device Inventory")
    print("=" * 60)
    print(f" {'ID':<6} {'Device Name':<12} {'IP Address':<18} {'OS':<14} {'Location'}")
    print("  " + "-" * 56)
    for device in devices:
        print(
            f"{device['Device ID']:<6} "
            f"{device['Device Name']:<12} "
            f"{device['Device Address']:<18} "
            f"{device['OS']:<14} "
            f"{device['Location'].strip()}"
        )
    print(f"\nTotal devices: {len(devices)}")

if __name__ == "__main__":
    main()