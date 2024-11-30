import math


def main():
    with open('input/day_11.txt', 'r') as file:
        lines = file.read()
    monkeys = [monkey_from_string(m) for m in lines.split("\n\n")]

    for i in range(0, 10000):
        print(i)
        for single_monkey in monkeys:
            print(f"Monkey {single_monkey.num}:")
            amount_of_items = len(single_monkey.starting_items)
            for item in single_monkey.starting_items:
                print(f"Monkey inspects an item with a worry level of {item}.")
                result_of_operation = perform_operation(item, single_monkey.operation)
                single_monkey.increase_inspection()
                print(f"Worry level is {single_monkey.operation} to {result_of_operation}.")
                # bored = math.floor(result_of_operation / 3)
                # bored = result_of_operation % 96577
                bored = result_of_operation % 9699690
                # print(f"Monkey gets bored with item. Worry level is divided by 3 to {bored}.")
                checked_bored = check_test(bored, single_monkey.test)
                if(checked_bored):
                    print(f"Current worry level is {single_monkey.test}")
                    to_throw = monkey_to_which_throw(single_monkey.if_true)
                else:
                    print(f"Current worry level is not {single_monkey.test.removeprefix('Test: ')}")
                    to_throw = monkey_to_which_throw(single_monkey.if_false)
                
                print(f"Item with worry level {bored} is thrown to monkey {to_throw}")
                monkeys[to_throw].starting_items.append(bored)
            single_monkey.starting_items = single_monkey.starting_items[amount_of_items:] 
    
    print([x.inspected for x in monkeys])
    res = sorted([x.inspected for x in monkeys], reverse=True)
    print(res[0] * res[1])



def starting_items(line: str):
    return [int(x) for x in line.removeprefix('Starting items: ').split(', ')]


def perform_operation(old: int, operation: str) -> int:
    only_operation = operation.removeprefix("Operation: new = ")
    left, operator, right = [x for x in only_operation.split(" ") if x != ""]
    if (left == "old"):
        left = old
    else:
        left = int(left)
    if (right == "old"):
        right = old
    else:
        right = int(right)

    if (operator == "+"):
        return left + right
    elif (operator == "*"):
        return left * right
    else:
        raise ValueError


def check_test(value: int, test: str):
    divisible_by = int(test.removeprefix('Test: divisible by '))
    return value % divisible_by == 0


def monkey_to_which_throw(if_or_else):
    return int(if_or_else.split()[-1])


def monkey_from_string(monkey_str: str):
    m_lines = [l.strip() for l in monkey_str.split("\n")]
    return Monkey(
        int(m_lines[0].removesuffix(":").split()[-1]),
        starting_items(m_lines[1]),
        m_lines[2],
        m_lines[3],
        m_lines[4],
        m_lines[5]
    )


class Monkey(object):
    def __init__(self, num: int, starting_items, operation, test, if_true, if_false):
        self.num = num
        self.starting_items = starting_items
        self.operation = operation
        self.test = test
        self.if_true = if_true
        self.if_false = if_false
        self.inspected = 0

    def increase_inspection(self):
        self.inspected = self.inspected + 1

    def to_string(self):
        return {
            "num": self.num,
            "starting_items": self.starting_items,
            "operation": self.operation,
            "test": self.test,
            "if_true": self.if_true,
            "if_false": self.if_false,
        }


def day_11_1():
    pass


def day_11_2(root):
    pass


if __name__ == "__main__":
    main()
