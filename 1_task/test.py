import unittest
from bfs import Graph, get_lines


class TestAlgorithm(unittest.TestCase):
    def test_one_component(self):
        adj_matrix = ['4\n',
                      '0 1 1 0',
                      '1 0 1 0',
                      '1 1 0 1',
                      '0 0 1 0']
        graph = Graph.get_from_adjective_matrix(adj_matrix)
        components = graph.find_component()
        self.assertEqual({1: {0, 1, 2, 3}}, components)

    def test_two_components(self):
        adj_matrix = ['5\n',
                      '0 1 1 0 0',
                      '1 0 1 0 0',
                      '1 1 0 0 0',
                      '0 0 0 0 1',
                      '0 0 0 1 0']
        graph = Graph.get_from_adjective_matrix(adj_matrix)
        components = graph.find_component()
        self.assertEqual({1: {0, 1, 2}, 2: {3, 4}}, components)

    def test_alone_vertex(self):
        adj_matrix = ['4\n',
                      '0 1 1 0',
                      '1 0 1 0',
                      '1 1 0 0',
                      '0 0 0 0']
        graph = Graph.get_from_adjective_matrix(adj_matrix)
        components = graph.find_component()
        self.assertEqual({1: {0, 1, 2}, 2: {3}}, components)

    def test_two_alone_vertexes(self):
        adj_matrix = ['4\n',
                      '0 0 0 0',
                      '0 0 1 0',
                      '0 1 0 0',
                      '0 0 0 0']
        graph = Graph.get_from_adjective_matrix(adj_matrix)
        components = graph.find_component()
        self.assertEqual({1: {0}, 2: {1, 2}, 3: {3}}, components)

    def test_loop(self):
        adj_matrix = ['3\n',
                      '1 0 0',
                      '0 1 0',
                      '0 0 1']
        graph = Graph.get_from_adjective_matrix(adj_matrix)
        components = graph.find_component()
        self.assertEqual({1: {0}, 2: {1}, 3: {2}}, components)


class TestFiles(unittest.TestCase):
    def test_read(self):
        tested_string = '2\n0 1\n1 0'
        with open('test.txt', 'w') as file:
            file.write(tested_string)
        lines = get_lines('test.txt')
        self.assertEqual(['2\n', '0 1\n', '1 0'], lines)


if __name__ == '__main__':
    unittest.main()
