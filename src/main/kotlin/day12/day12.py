map = """abaacccccccccccccaaaaaaaccccccccccccccccccccccccccccccccccaaaaaa
abaaccccccccccccccaaaaaaaaaaccccccccccccccccccccccccccccccccaaaa
abaaaaacccccccccaaaaaaaaaaaaccccccccccccccccccccccccccccccccaaaa
abaaaaaccccccccaaaaaaaaaaaaaacccccccccccccccccdcccccccccccccaaaa
abaaaccccccccccaaaaaaaaccacacccccccccccccccccdddcccccccccccaaaaa
abaaacccccccccaaaaaaaaaaccaaccccccccccccciiiiddddcccccccccccaccc
abcaaaccccccccaaaaaaaaaaaaaaccccccccccciiiiiijddddcccccccccccccc
abccaaccccccccaccaaaaaaaaaaaacccccccccciiiiiijjddddccccaaccccccc
abccccccccccccccaaacaaaaaaaaaaccccccciiiiippijjjddddccaaaccccccc
abccccccccccccccaacccccaaaaaaacccccciiiippppppjjjdddddaaaaaacccc
abccccccccccccccccccccaaaaaaccccccckiiippppppqqjjjdddeeeaaaacccc
abccccccccccccccccccccaaaaaaccccckkkiippppuupqqjjjjdeeeeeaaccccc
abccccccccccccccccccccccccaaccckkkkkkipppuuuuqqqjjjjjeeeeeaccccc
abccccccccccccccccccccccccccckkkkkkoppppuuuuuvqqqjjjjjkeeeeccccc
abcccccccccccccccccccccccccckkkkooooppppuuxuvvqqqqqqjkkkeeeecccc
abccaaccaccccccccccccccccccckkkoooooopuuuuxyvvvqqqqqqkkkkeeecccc
abccaaaaacccccaaccccccccccckkkoooouuuuuuuxxyyvvvvqqqqqkkkkeecccc
abcaaaaacccccaaaacccccccccckkkooouuuuxxxuxxyyvvvvvvvqqqkkkeeeccc
abcaaaaaaaaaaaaacccccccccccjjjooottuxxxxxxxyyyyyvvvvrrrkkkeecccc
abcccaaaacaaaaaaaaacaaccccccjjoootttxxxxxxxyyyyyyvvvrrkkkfffcccc
SbccaacccccaaaaaaaaaaaccccccjjjooottxxxxEzzzyyyyvvvrrrkkkfffcccc
abcccccccccaaaaaaaaaaaccccccjjjooootttxxxyyyyyvvvvrrrkkkfffccccc
abcaacccccaaaaaaaaaaaccccccccjjjooottttxxyyyyywwvrrrrkkkfffccccc
abaaacccccaaaaaaaaaaaaaacccccjjjjonnttxxyyyyyywwwrrlllkfffcccccc
abaaaaaaaaaaacaaaaaaaaaaccccccjjjnnnttxxyywwyyywwrrlllffffcccccc
abaaaaaaaaaaaaaaaaaaaaaaccccccjjjnntttxxwwwwwywwwrrlllfffccccccc
abaaccaaaaaaaaaaaaaaacccccccccjjjnntttxwwwsswwwwwrrlllfffccccccc
abaacccaaaaaaaacccaaacccccccccjjinnttttwwsssswwwsrrlllgffacccccc
abccccaaaaaaccccccaaaccccccccciiinnntttsssssssssssrlllggaacccccc
abccccaaaaaaaccccccccccaaccccciiinnntttsssmmssssssrlllggaacccccc
abccccaacaaaacccccccaacaaaccccciinnnnnnmmmmmmmsssslllgggaaaacccc
abccccccccaaacccccccaaaaacccccciiinnnnnmmmmmmmmmmllllgggaaaacccc
abaaaccccccccccccccccaaaaaacccciiiinnnmmmhhhmmmmmlllgggaaaaccccc
abaaaaacccccccccccaaaaaaaaaccccciiiiiiihhhhhhhhmmlgggggaaacccccc
abaaaaaccccaaccccaaaaaaacaacccccciiiiihhhhhhhhhhggggggcaaacccccc
abaaaaccccaaaccccaaaacaaaaacccccccciiihhaaaaahhhhggggccccccccccc
abaaaaaaacaaacccccaaaaaaaaaccccccccccccccaaaacccccccccccccccccaa
abaacaaaaaaaaaaaccaaaaaaaaccccccccccccccccaaaccccccccccccccccaaa
abcccccaaaaaaaaacccaaaaaaaccccccccccccccccaacccccccccccccccccaaa
abccccccaaaaaaaaaaaaaaaaacccccccccccccccccaaacccccccccccccaaaaaa
abcccccaaaaaaaaaaaaaaaaaaaaaccccccccccccccccccccccccccccccaaaaaa""".split()


class Node:
    def __init__(self, elevation, dist, position, neighbours=None, move=None):
        self.letter = elevation
        self.elevation = ord(elevation)  # to integer
        self.dist = dist
        self.pos = position

    def __eq__(self, other):
        return self.pos == other.pos

    def __hash__(self):
        return hash(self.pos)

    def __str__(self):
        return self.elevation


def getValidMoves(node, map):
    # up, down,left , right
    neighbors = (-1, 0), (1, 0), (0, -1), (0, 1)

    pos = node.pos
    neighbors_list = [False] * len(neighbors)

    for i, neighbor in enumerate(neighbors):
        try:
            if pos[0] + neighbor[0] >= 0 or pos[1] + neighbor[1] >= 0:
                if ord(map[pos[0] + neighbor[0]][pos[1] + neighbor[1]]) - node.elevation < 2:
                    neighbors_list[i] = True
        except IndexError:
            pass
    # up, down, right, left

    valid_moves = {
        "up": neighbors_list[0],
        "down": neighbors_list[1],
        "left": neighbors_list[2],
        "right": neighbors_list[3],

    }
    return valid_moves


def get_neighbors(node, map):
    valid_moves = getValidMoves(node, map)
    neighbours_map = {
        "up": (-1, 0),
        "down": (1, 0),
        "left": (0, -1),
        "right": (0, 1)}

    neighbours = []
    for k, v in valid_moves.items():
        if v:
            rel_pos = neighbours_map[k]
            neighbour_pos = (node.pos[0] + rel_pos[0], node.pos[1] + rel_pos[1])
            if neighbour_pos[0] >= 0 and neighbour_pos[1] >= 0:
                neighbour_node_elevation = map[neighbour_pos[0]][neighbour_pos[1]]
                neighbours.append(
                    Node(
                        neighbour_node_elevation,
                        getDist(end_pos, neighbour_pos),
                        neighbour_pos,
                        move=k
                    )
                )
    return neighbours


start_pos = (0, 0)
end_pos = (0, 0)

for i, x in enumerate(map):
    for j, y in enumerate(x):
        if y == "S":
            start_pos = (i, j)
        if y == "E":
            end_pos = (i, j)


def getDist(startPos, endPos):
    return abs(endPos[0] - startPos[0]) + abs(endPos[1] - startPos[1])


start_node = Node(
    "a",
    getDist(start_pos, end_pos),
    start_pos
)

end_node = Node(
    "z",
    getDist(end_pos, end_pos),
    end_pos
)


def a_star(map, start_node, end_node):
    q = []

    came_from = {}

    g_score = {}
    g_score[start_node] = 0

    f_score = {}
    f_score[start_node] = start_node.dist

    q.append((f_score[start_node], start_node))

    while q:
        current_node = q.pop()[1]
        if current_node.letter == "E":
            return g_score[current_node] + 2  # +1 for start and +1 for end (??????)

        for new in get_neighbors(current_node, map):
            if current_node not in g_score:
                g_score[start_node] = float("inf")
            if new not in g_score:
                g_score[new] = float("inf")

            tentative_score = g_score[current_node] + 1

            if tentative_score < g_score[new]:
                came_from[current_node] = new  # update path
                g_score[new] = tentative_score  # dist so far from start node
                f_score[new] = tentative_score + getDist(new.pos,
                                                         end_node.pos)  # dist so far plus estimate of dist left.
                new_item = (f_score[new], new)
                if new_item not in q:
                    q.append(new_item)
                    q = sorted(q, key=lambda tup: tup[0], reverse=True)  # Sort priority list


print("1: {}".format(a_star(map, start_node, end_node)))

end_pos = (0, 0)

positions = []
for i, x in enumerate(map):
    for j, y in enumerate(x):
        if y == "S":
            positions.append((i, j))
        if y == "E":
            end_pos = (i, j)
        if y == "a":
            positions.append((i, j))

min_steps = float("inf")
for position in positions:

    start_pos = position
    start_node = Node(
        "a",
        getDist(start_pos, end_pos),
        start_pos
    )

    end_node = Node(
        "E",
        getDist(end_pos, end_pos),
        end_pos
    )

    steps = a_star(map, start_node, end_node)
    if steps:
        if steps < min_steps:
            min_steps = steps

print("2: {}".format(min_steps))
