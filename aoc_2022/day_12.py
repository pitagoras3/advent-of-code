import sys
import copy

ending_paths = []


def main():
    # sys.setrecursionlimit(100000000)
    with open('input/day_12.txt', 'r') as file:
        matrix = [list(x.strip()) for x in file.read().split("\n")]
    starting_position = (0, 0)
    for i in range(len(matrix)):
        for j in range(len(matrix[0])):
            if (matrix[i][j] == 'S'):
                starting_position = (i, j)

    for x in matrix:
        print(','.join(x))

    paths_rec(starting_position, matrix, [])
    print(min([(len(e)) for e in ending_paths]) - 1)


def paths_rec(current_position, matrix, current_path):
    # print(current_position, matrix[current_position[0]][current_position[1]])
    current_path.append(current_position)
    if (matrix[current_position[0]][current_position[1]] == 'E'):
        ending_paths.append(copy.deepcopy(current_path))
    else:
        around = options_around(current_position, matrix, current_path)
        if (len(around) > 0):
            for e in around:
                # print('len_around', len(around), 'current', current_position,
                #   'for e:', e, 'current_path:', current_path)
                paths_rec(e, matrix, copy.deepcopy(current_path))


def options_around(current_position, matrix, current_path):
    result = []
    y, x = current_position
    left = x - 1
    right = x + 1
    up = y - 1
    down = y + 1
    if (left >= 0):
        result.append((y, left))
    if (right < len(matrix[0])):
        result.append((y, right))
    if (up >= 0):
        result.append((up, x))
    if (down < len(matrix)):
        result.append((down, x))

    if (matrix[current_position[0]][current_position[1]] == 'S'):
        return result
    else:
        temp = [e for e in result if (ord(matrix[e[0]][e[1]]) - ord(matrix[current_position[0]][current_position[1]]) <= 1) and
                e not in current_path]
        # return temp
        return [x for x in temp if matrix[x[0]][x[1]] != 'E' or (
            matrix[x[0]][x[1]] == 'E' and matrix[current_position[0]][current_position[1]] == 'z')]


def day_12_1():
    pass


def day_12_2():
    pass


if __name__ == "__main__":
    main()
