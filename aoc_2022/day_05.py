import re
import copy


def main():
    with open('input/day_05.txt', 'r') as file:
        lines = file.read()
    raw_crates, instructions = lines.split("\n\n")
    crates_lines = raw_crates.split("\n")
    removed_collums = [[y for index, y in enumerate(x) if index % 2 != 0]
                       for x in crates_lines]
    crates = [x[1:] for x in [list(reversed(
        [row[i] for row in removed_collums if row[i] != ' '])) for i in range(len(removed_collums[-1]))] if len(x) > 0]

    instruction_lines = [[int(y) for y in re.findall(r'\d+', x)]
                         for x in instructions.split("\n")]

    print_result(day_05_1(instruction_lines, copy.deepcopy(crates)))
    print_result(day_05_2(instruction_lines, copy.deepcopy(crates)))


def day_05_1(instruction_lines, crates):
    for how_many, fr, to in instruction_lines:
        for i in range(how_many):
            crates[to-1].append(crates[fr-1].pop())
    return crates


def day_05_2(instruction_lines, crates):
    for how_many, fr, to in instruction_lines:
        last_elems = crates[fr-1][-how_many:]
        del crates[fr-1][-how_many:]
        crates[to-1].extend(last_elems)
    return crates


def print_result(result):
    print(str.join('', [x[-1] for x in result]))


if __name__ == "__main__":
    main()
