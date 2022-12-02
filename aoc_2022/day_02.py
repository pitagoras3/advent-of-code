def main():
    with open('input/day_02.txt', 'r') as file:
        lines = file.read().strip()
    games = [x.split(" ") for x in lines.split("\n")]
    day_02_1(games)
    day_02_2(games)


def day_02_1(games):
    print(sum([calculate_game_points(x[0], x[1]) for x in games]))


def day_02_2(games):
    print(sum([calculate_game_points_2(x[0], x[1]) for x in games]))


def calculate_game_points(left, right):
    right_points = calculate_item_points(right)
    game_points = calculate_rps_points(left, right)
    return right_points + game_points


def calculate_item_points(right):
    match right:
        case 'A' | 'X':
            return 1
        case 'B' | 'Y':
            return 2
        case 'C' | 'Z':
            return 3
        case _:
            return 0


def calculate_rps_points(left, right):
    match calculate_item_points(left) - calculate_item_points(right):
        case 1 | -2:
            return 0
        case 2 | -1:
            return 6
        case 0 | _:
            return 3


def calculate_game_points_2(left, right):
    abc = ['A', 'B', 'C']
    match (left, right):
        case (_, 'Y'):
            return calculate_item_points(left) + 3
        case (_, 'X'):
            return calculate_item_points(abc[abc.index(left) - 1])
        case (_, 'Z'):
            return calculate_item_points(abc[(abc.index(left) + 1) % 3]) + 6
        case (_, _):
            return 0


if __name__ == "__main__":
    main()
