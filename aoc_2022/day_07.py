def main():
    with open('input/day_07.txt', 'r') as file:
        lines = file.read()
    root = Node("/", 0, None, 'dir')
    parsed_input = [[y for y in x.split("\n") if len(
        y) > 0] for x in lines.split("$ ") if len(x) > 0]
    current_node = root
    process_steps(parsed_input, current_node, root)
    day_07_1(root)
    day_07_2(root)


def day_07_1(root):
    print(sum(all_dirs_with_total_size_compared(root, [], lambda x: x <= 100000)))


def day_07_2(root):
    space_needed_to_remove = 30000000 - (70000000 - root.size)
    print(min(all_dirs_with_total_size_compared(root, [], lambda x: x >= space_needed_to_remove)))


def process_steps(steps, current_node, root):
    step = steps.pop(0)
    if (step[0] == "cd /"):
        current_node = root
    elif (step[0] == "cd .."):
        current_node = current_node.parent
    elif (step[0].startswith("cd ")):
        name = step[0][3:]
        current_node = [
            child for child in current_node.children if child.name == name][0]
    elif (step[0] == "ls" and len(current_node.children) == 0):
        for f in step[1:]:
            if (f.startswith("dir ")):
                name = f[4:]
                child_node = Node(name, 0, current_node, 'dir')
                current_node.add_child(child_node)
            else:
                size, name = f.split()
                child_node = Node(name, int(size), current_node, 'file')
                notify_parents_about_size(current_node, int(size))
                current_node.add_child(child_node)

    if (len(steps) > 0):
        process_steps(steps, current_node, root)


def notify_parents_about_size(node, size):
    node.size += size
    if (node.parent != None):
        notify_parents_about_size(node.parent, size)


def all_dirs_with_total_size_compared(node, result, compare):
    for child in node.children:
        all_dirs_with_total_size_compared(child, result, compare)
    if (node.type == 'dir' and compare(node.size)):
        result.append(node.size)
    return result


class Node(object):
    def __init__(self, name, size, parent, type):
        self.name = name
        self.size = size
        self.type = type
        self.parent = parent
        self.children = []

    def add_child(self, obj):
        self.children.append(obj)

    def printNode(self, depth=0):
        print(((" " * depth) + "-"), self.name, f"({self.type}, size={self.size})")
        for child in self.children:
            child.printNode(depth + 1)


if __name__ == "__main__":
    main()
