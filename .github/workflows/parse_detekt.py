import os
import subprocess
import xml.etree.ElementTree as ET

report_path = os.environ.get(
    "DETEKT_REPORT_PATH", "build/reports/detekt/merge.xml"
)
github_url = os.environ.get("GITHUB_URL", "https://github.com")
repository_url = os.environ.get("REPOSITORY_URL")
branch = os.environ.get("BRANCH", "main")


def main():
    errors: list[str] = []
    root = ET.parse(report_path).getroot()
    base_link = f"{github_url}/{repository_url}/tree/{branch}"
    for file in root.findall("file"):
        file_name = file.get('name')
        file_errors = []
        for error in file.findall('error'):
            # column = error.get('column')
            line = error.get('line')
            message = error.get('message')
            # source = error.get('source')
            each_result = f"- [{message}: L{line}]({base_link}/{file_name}#L{line})\n"
            file_errors.append(each_result)
        if file_errors:
            result = f"### [{file_name}]({base_link}/{file_name})\n"
            result += "\n".join(file_errors)
            result += "*** \n"
            errors.append(result)
    if errors:
        result_str = "*** \n" + "\n".join(errors)
        subprocess.run(
            [f"echo '{result_str}' >> $GITHUB_STEP_SUMMARY"], shell=True
        )


if __name__ == "__main__":
    main()
