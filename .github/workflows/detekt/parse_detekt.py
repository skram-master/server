import os

report_path = os.environ.get("DETEKT_REPORT_PATH")


def main():
    print(f"Detekt report path: {report_path}")
