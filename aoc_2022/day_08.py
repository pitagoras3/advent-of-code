import math

def main():
    with open('input/day_08.txt', 'r') as file:
        lines = file.read()

    # matrix = [[(int(y), 0) for y in [*x]] for x in lines.split("\n")]
    matrix = [[(int(y), []) for y in [*x]] for x in lines.split("\n")]

    for i in range(len(matrix)):
        for j in range(len(matrix[i])):
            value, distances = matrix[i][j]
            middle_to_right_distance = 0
            middle_to_left_distance = 0
            middle_to_bottom_distance = 0
            middle_to_top_distance = 0
            for x in range(j + 1, len(matrix[i])):
                if (matrix[i][x][0] < value):
                    middle_to_right_distance += 1
                else:
                    middle_to_right_distance += 1
                    break
            for x in range(j-1, -1, -1):
                if (matrix[i][x][0] < value):
                    middle_to_left_distance += 1
                else:
                    middle_to_left_distance += 1
                    break
            for y in range(i + 1, len(matrix)):
                if (matrix[y][j][0] < value):
                    middle_to_bottom_distance += 1
                else:
                    middle_to_bottom_distance += 1
                    break
            for y in range(i-1, -1, -1):
                if (matrix[y][j][0] < value):
                    middle_to_top_distance += 1
                else:
                    middle_to_top_distance += 1
                    break
        
            distances.append(middle_to_right_distance)
            distances.append(middle_to_left_distance)
            distances.append(middle_to_bottom_distance)
            distances.append(middle_to_top_distance)
            matrix[i][j] = (value, distances)
    print(matrix)
    max = -1
    for row in [[(y[0], math.prod(y[1])) for y in x] for x in matrix]:
        for v, s in row:
            if(s > max):
                max = s

    print(max)



def calculate_points(matrix, i, j):
    middle_to_left = list(reversed(matrix[i][:j]))
    print(middle_to_left)
    middle_to_right = matrix[i][j + 1:]
    print(middle_to_right)
    print(matrix[:i])
    middle_to_top = list(reversed(matrix[:i]))[j]
    middle_to_bottom = matrix[i:][j]

    print('dupa')


def day_08_1(matrix):
    for i in range(len(matrix)):
        max_height = -1
        for j in range(len(matrix[i])):
            value, visible = matrix[i][j]
            if (value > max_height):
                max_height = value
                visible += 1
            matrix[i][j] = (value, visible)   # type: ignore

    for i in range(len(matrix) - 1, -1, -1):
        max_height = -1
        for j in range(len(matrix[i]) - 1, -1, -1):
            value, visible = matrix[i][j]
            if (value > max_height):
                max_height = value
                visible += 1
            matrix[i][j] = (value, visible)   # type: ignore

    for i in range(len(matrix)):
        max_height = -1
        for j in range(len(matrix[i])):
            value, visible = matrix[j][i]
            if (value > max_height):
                max_height = value
                visible += 1
            matrix[j][i] = (value, visible)   # type: ignore

    for i in range(len(matrix) - 1, -1, -1):
        max_height = -1
        for j in range(len(matrix[i]) - 1, -1, -1):
            value, visible = matrix[j][i]
            if (value > max_height):
                max_height = value
                visible += 1
            matrix[j][i] = (value, visible)   # type: ignore

    print(matrix)

    sum = 0
    for row in matrix:
        for value, visible in row:
            if (visible > 0):
                sum += 1

    print(sum)


def day_08_2():
    pass


if __name__ == "__main__":
    main()
