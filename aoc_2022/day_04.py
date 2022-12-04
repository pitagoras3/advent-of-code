def main():
    with open('input/day_04.txt', 'r') as file:
        lines = file.read().strip()
    input = lines.split("\n")
    pairs = [[(y.split('-')[0], y.split('-')[1])
              for y in x.split(',')] for x in input]
    day_04_1(pairs)
    day_04_2(pairs)


def day_04_1(pairs):
    print(sum([1 for x in pairs if (
        (
            (int(x[0][0]) in range(int(x[1][0]), int(x[1][1]) + 1))
            and
            (int(x[0][1]) in range(int(x[1][0]), int(x[1][1]) + 1))
        ) or
        (
            (int(x[1][0]) in range(int(x[0][0]), int(x[0][1]) + 1))
            and
            (int(x[1][1]) in range(int(x[0][0]), int(x[0][1]) + 1))
        )
    )
    ]))


def day_04_2(pairs):
    print(sum([1 for x in pairs if (
        (int(x[0][0]) in range(int(x[1][0]), int(x[1][1]) + 1))
        or
        (int(x[0][1]) in range(int(x[1][0]), int(x[1][1]) + 1))
        or
        (int(x[1][0]) in range(int(x[0][0]), int(x[0][1]) + 1))
        or
        (int(x[1][1]) in range(int(x[0][0]), int(x[0][1]) + 1))
    )
    ]))


if __name__ == "__main__":
    main()
