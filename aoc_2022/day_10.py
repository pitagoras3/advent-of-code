def main():
    with open('input/day_10.txt', 'r') as file:
        lines = file.read().split("\n")
    X = 1
    cycle = 1
    sprite_positions = [0, 1, 2]
    during_cycle_to_X = []
    after_cycle_to_X = []
    screen = []
    pixel_drawn = 0
    for line in lines:
        if (line == "noop"):
            print(f'Start cycle\t {cycle}: begin executing noop')
            X = X
            during_cycle_to_X.append((cycle, X))
            sprite_positions = updated_sprite_positions(X)
            print(f'During cycle\t {cycle}: CRT draws pixel in position {pixel_drawn}')
            if(pixel_drawn in sprite_positions):
                screen.append('#')
            else:
                screen.append('.')
            pixel_drawn += 1
            pixel_drawn %= 40
            print(f'Current CRT row: {"".join(screen)}')
            after_cycle_to_X.append((cycle, X))
            print(f'End of cycle\t {cycle}: finish executing noop')
            cycle += 1
        if (line.startswith('addx')):
            print(f'Start cycle\t {cycle}: begin executing {line}')
            command, value_to_add_str = line.split()
            value_to_add = int(value_to_add_str)
            
            X = X
            during_cycle_to_X.append((cycle, X))
            sprite_positions = updated_sprite_positions(X)
            if(pixel_drawn in sprite_positions):
                screen.append('#')
            else:
                screen.append('.')
            pixel_drawn += 1
            pixel_drawn %= 40
            after_cycle_to_X.append((cycle, X))
            cycle += 1

            during_cycle_to_X.append((cycle, X))
            sprite_positions = updated_sprite_positions(X)
            if(pixel_drawn in sprite_positions):
                screen.append('#')
            else:
                screen.append('.')
            pixel_drawn += 1
            pixel_drawn %= 40
            X += value_to_add
            after_cycle_to_X.append((cycle, X))
            print(f'End of cycle\t {cycle}: finish executing {line}')
            cycle += 1
        print()
    print(''.join(screen[0:40]))
    print(''.join(screen[40:80]))
    print(''.join(screen[80:120]))
    print(''.join(screen[120:160]))
    print(''.join(screen[160:200]))
    print(''.join(screen[200:240]))


def updated_sprite_positions(X):
    return [X-1, X, X+1]


def signal_strength(cycle_to_x):
    return cycle_to_x[0] * cycle_to_x[1]


def day_10_1(during_cycle_to_X):
    with open('input/day_10.txt', 'r') as file:
        lines = file.read().split("\n")
    X = 1
    cycle = 0
    during_cycle_to_X = []
    after_cycle_to_X = []
    for line in lines:
        if (line == "noop"):
            cycle += 1
            X = X
            during_cycle_to_X.append((cycle, X))
            after_cycle_to_X.append((cycle, X))
        if (line.startswith('addx')):
            command, value_to_add_str = line.split()
            value_to_add = int(value_to_add_str)
            cycle += 1
            X = X
            during_cycle_to_X.append((cycle, X))
            after_cycle_to_X.append((cycle, X))

            cycle += 1
            during_cycle_to_X.append((cycle, X))
            X += value_to_add
            after_cycle_to_X.append((cycle, X))
    
    print(sum([
        signal_strength(during_cycle_to_X[20 - 1]),
        signal_strength(during_cycle_to_X[60 - 1]),
        signal_strength(during_cycle_to_X[100 - 1]),
        signal_strength(during_cycle_to_X[140 - 1]),
        signal_strength(during_cycle_to_X[180 - 1]),
        signal_strength(during_cycle_to_X[220 - 1])
    ]))


def day_10_2():
    pass


if __name__ == "__main__":
    main()
