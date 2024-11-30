all_coordinates_history = []
all_coordinates = []


def main():
    with open('input/day_09.txt', 'r') as file:
        path = [(x.split(" ")[0], int(x.split(" ")[1]))
                for x in file.read().split("\n")]

    for i in range(0, 10):
        all_coordinates.append((0, 0))
        all_coordinates_history.append([(0, 0)])

    for direction, length in path:
        print(all_coordinates)
        for i in range(length, 0, -1):
            process_step(0, direction)

    print(len(set(all_coordinates_history[-1])))


def process_step(depth, direction):
    previous_head_coordinates = all_coordinates[depth]
    new_head_coordinates = update_coordinate(direction, all_coordinates[depth])
    all_coordinates[depth] = new_head_coordinates
    all_coordinates_history[depth].append(new_head_coordinates)

    tail_coordinates = all_coordinates[depth + 1]
    if (are_close_to_each_other(new_head_coordinates, tail_coordinates)):
        pass
    else:
        follow_step(depth + 1, direction,
                    new_head_coordinates, tail_coordinates)


def follow_step(depth, direction, new_head, old_tail):
    if (are_in_the_same_dimension(new_head, old_tail)):
        new_tail = update_coordinate(direction, old_tail)
    else:
        new_tail = move_tail_diagonally(direction, new_head, old_tail)
    all_coordinates[depth] = new_tail
    all_coordinates_history[depth].append(new_tail)

    if (depth + 1 < len(all_coordinates)):
        if (are_close_to_each_other(new_tail, all_coordinates[depth + 1])):
            pass
        else:
            follow_step(depth + 1, direction, new_tail,
                        all_coordinates[depth + 1])


def are_close_to_each_other(head, tail):
    return abs(head[0] - tail[0]) <= 1 and abs(head[1] - tail[1]) <= 1


def are_in_the_same_dimension(head, tail):
    return head[0] - tail[0] == 0 or head[1] - tail[1] == 0


def update_coordinate(direction, coordinates):
    if direction == "R":
        return (coordinates[0] + 1, coordinates[1])
    elif direction == "L":
        return (coordinates[0] - 1, coordinates[1])
    elif direction == "U":
        return (coordinates[0], coordinates[1] + 1)
    elif direction == "D":
        return (coordinates[0], coordinates[1] - 1)


def move_tail_diagonally(direction, new_head, old_tail):
    if direction == "R":
        x = old_tail[0] + 1
        y = old_tail[1] + determine_increment(new_head[1], old_tail[1])
        return (x, y)
    elif direction == "L":
        x = old_tail[0] - 1
        y = old_tail[1] + determine_increment(new_head[1], old_tail[1])
        return (x, y)
    elif direction == "U":
        x = old_tail[0] + determine_increment(new_head[0], old_tail[0])
        y = old_tail[1] + 1
        return (x, y)
    elif direction == "D":
        x = old_tail[0] + determine_increment(new_head[0], old_tail[0])
        y = old_tail[1] - 1
        return (x, y)

def determine_increment(new_head_z, old_tail_z):
    if(new_head_z > old_tail_z):
        return 1
    else:
        return -1

def part1(path):
    all_head_coordinates = []
    all_tail_coordinates = []
    head_coordinates = (0, 0)
    tail_coordinates = (0, 0)
    all_head_coordinates.append(head_coordinates)
    all_tail_coordinates.append(tail_coordinates)

    for direction, length in path:
        for i in range(length, 0, -1):
            head_coordinates = update_coordinate(direction, head_coordinates)

            if (are_close_to_each_other(head_coordinates, tail_coordinates)):
                pass
            else:
                tail_coordinates = all_head_coordinates[-1]
            all_head_coordinates.append(head_coordinates)
            all_tail_coordinates.append(tail_coordinates)

    print(len(set(all_tail_coordinates)))


if __name__ == "__main__":
    main()
