import java.io.IOException;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        var fileHandler = new FileHandler();
        var stringTaskInformation = fileHandler.ReadFile("in.txt");
        var task = getTaskFromString(stringTaskInformation);
        var fordBellman = new FordBellman();
        var answer = fordBellman.algorithm(task.source(), task.destination(), task.adjacencyMatrix());
        fileHandler.WriteFile("out.txt", answer);
    }

    private static Task getTaskFromString(String adj) {
        adj = adj.replace("\r", "");
        var lines = adj.split("\n");
        var size = Integer.parseInt(lines[0]);
        var matrix = getDefaultMatrix(size);
        for (var i = 0; i < size; i++) {
            var line = lines[i + 1];
            if (line.equals("0")) continue;
            var lineSplit = line.split(" ");
            for (var j = 0; j < line.length(); j += 2) {
                var vertex = Integer.parseInt(lineSplit[j]);
                if (vertex == 0) break;
                var weight = Integer.parseInt(lineSplit[j + 1]);
                matrix[i][vertex - 1] = weight;
            }
        }

        var source = Integer.parseInt(lines[lines.length - 2]);
        var destination = Integer.parseInt(lines[lines.length - 1]);
        return new Task(source, destination, matrix);
    }

    public static int[][] getDefaultMatrix(int count) {
        var matrix = new int[count][count];
        IntStream.range(0, count).forEach(i -> Arrays.fill(matrix[i], Integer.MAX_VALUE));
        return matrix;
    }

    public record Task(int source, int destination, int[][] adjacencyMatrix) {

        public Task(int source, int destination, int[][] adjacencyMatrix) {
            this.source = source - 1;
            this.destination = destination - 1;
            this.adjacencyMatrix = adjacencyMatrix;
        }
    }
}
