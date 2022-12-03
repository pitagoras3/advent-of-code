def main():
    with open('input/day_03.txt', 'r') as file:
        lines = file.read().strip()
    rucksacks = lines.split("\n")
    day_03_1(rucksacks)
    day_03_2(rucksacks)


def day_03_1(rucksacks):
    two_half_line_rucksacks = [(set(x[:(int(len(x)/2))]), set(x[(int(len(x)/2)):])) for x in rucksacks]
    elems = [list(left_set & right_set)[0] for (left_set, right_set) in two_half_line_rucksacks]
    print(sum([calc_value_of_elem(x) for x in elems]))


def day_03_2(rucksacks):
    three_rucksacks = [[set(x) for x in rucksacks[i:i+3]] for i in range(0, len(rucksacks), 3)]
    print(sum([calc_value_of_elem(list(x & y & z)[0]) for x, y, z in three_rucksacks]))


def calc_value_of_elem(elem):
    if elem.islower():
        return ord(elem) - 97 + 1
    else:
        return ord(elem) - 65 + 27


if __name__ == "__main__":
    main()
