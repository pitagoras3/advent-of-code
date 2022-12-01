def main():
    with open('input/day_01.txt', 'r') as file:
        lines = file.read().strip()
    day_01_1(lines)
    day_01_2(lines)


def elfs_summed_rations(lines):
    return sorted([sum([int(y) for y in x.split()]) for x in lines.split("\n\n")], reverse=True)


def day_01_1(lines):
    print(elfs_summed_rations(lines)[0])


def day_01_2(lines):
    print(sum(elfs_summed_rations(lines)[:3]))


if __name__ == "__main__":
    main()
