import subprocess


def main():
    with open('in.txt', 'r') as in_txt:
        with open('out.txt', 'w') as out_txt:
            subprocess.call(["5_task.exe", in_txt.read()], stdout=out_txt)


if __name__ == '__main__':
    main()
