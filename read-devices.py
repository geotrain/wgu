# Greg Westmoreland
# Task 1 - B1

import csv
import os

CSV_FILE = "network-device-list.csv"

def read_devices(csv_file):
    devices = []
    with open(csv_file, newline="") as f:
        reader = csv.DictReader(f)
        for row in reader:
            devices.append(row)
    return devices

def main():
    script_dir = os.path.dirname(os.path.abspath(__file__))
    csv_path = os.path.join(script_dir, CSV_FILE)
    devices = read_devices(csv_path)
    print("Network Device List")
    print("=" * 40)
    for device in devices:
        print(f"  {device['Device Name']:10} | {device['Device Address']:15} |  {device['OS']}")
    print(f"\nTotal devices: {len(devices)}")

if __name__ == "__main__":
    main()
