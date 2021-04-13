class Graph:
    def __init__(self, vertex_count: int, matrix: list[list[int]]):
        self.vertices_count = vertex_count
        self._keys = {x for x in range(self.vertices_count)}
        self._visited: list[bool] = [False for _ in range(vertex_count)]
        self._matrix = matrix
        self._components: dict[int, set[int]] = {}

    @staticmethod
    def get_from_adjective_matrix(lines: list[str]) -> 'Graph':
        vertex_count = int(lines[0])
        matrix = []
        for i in range(1, len(lines)):
            vertices = lines[i].split()
            matrix.append([int(x) for x in vertices])
        return Graph(vertex_count, matrix)

    def find_component(self) -> dict[int, set[int]]:
        component_count = 0
        for vertex in range(len(self._matrix)):
            if not self._visited[vertex]:
                bfs = BFS(self, vertex, self._visited)
                visited_vertices = set(bfs.get_visited_vertices())
                if visited_vertices != self._keys or component_count == 0:
                    component_count += 1
                    self._components[component_count] = visited_vertices
        return self._components

    def __getitem__(self, item: int) -> list[int]:
        return self._matrix[item]


class BFS:
    def __init__(self, graph: Graph, root: int, visited: list[bool]):
        self._graph = graph
        self._visited = visited
        self._vertex_in_graph = self._bfs(root, visited)

    def _bfs(self, start: int, visited: list[bool]) -> list[int]:
        queue = [start]
        visited[start] = True
        vertex_in_graph = [start]
        while queue:
            vertex = queue.pop()
            for i in range(self._graph.vertices_count):
                if self._graph[vertex][i] == 1 and not visited[i]:
                    queue.append(i)
                    vertex_in_graph.append(i)
                    visited[i] = True
        return vertex_in_graph

    def get_visited_vertices(self) -> list[int]:
        return self._vertex_in_graph


class Output:
    def __init__(self, out_txt: str, components: dict[int, set[int]]):
        self._components = components
        with open(out_txt, 'w') as file:
            self._file = file
            self._write()

    def _write(self):
        self._file.write(str(len(self._components.keys())) + '\n')
        for i in self._components:
            vertices = self._components[i]
            for vertex in vertices:
                self._file.write(str(vertex + 1) + ' ')
            self._file.write('0\n')


def get_lines(filename: str) -> list[str]:
    with open(filename, 'r') as in_txt:
        return in_txt.readlines()


def main():
    lines = get_lines('in.txt')
    graph = Graph.get_from_adjective_matrix(lines)
    components = graph.find_component()
    Output('out.txt', components)


if __name__ == '__main__':
    main()
