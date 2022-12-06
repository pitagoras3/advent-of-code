def main():
    with open('input/day_06.txt', 'r') as file:
        line = file.read()
    print(end_index_of_first_non_repeating_window(line, 4))
    print(end_index_of_first_non_repeating_window(line, 14))


def end_index_of_first_non_repeating_window(line, window_size):
    for i in range(len(line) - window_size + 1):
        different_elems = (i + window_size, len(set(line[i: i + window_size])))
        if (different_elems[1] == window_size):
            return different_elems[0]


if __name__ == "__main__":
    main()
