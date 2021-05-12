import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler {
    public String ReadFile(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filename)));
    }

    public void WriteFile(String filename, String answer) throws IOException {
        Files.write(Paths.get(filename), answer.getBytes());
    }

    public Task GetTaskInformationFromString(String adj) {
        adj = adj.replace("\r", "");
        var lines = adj.split("\n");
        var count = Integer.parseInt(lines[0]);
        var A = GetDefaultMatrix(count);
        for (var i = 0; i < count; i++) {
            var line = lines[i + 1];
            if (line.equals("0"))
                continue;
            for (var j = 0; j < line.length(); j += 2) {
                var lineSplit = line.split(" ");
                if (lineSplit[j].equals("")) continue;
                var vertex = Integer.parseInt(lineSplit[j]);
                if (vertex == 0) break;
                var weight = Integer.parseInt(lineSplit[j + 1]);
                A[i][vertex - 1] = weight;
            }
        }

        var source = Integer.parseInt(lines[lines.length - 2]);
        var destination = Integer.parseInt(lines[lines.length - 1]);
        return new Task(source, destination, A);
    }

    private int[][] GetDefaultMatrix(int count) {
        var matrix = new int[count][count];
        for (var i = 0; i < count; i++) {
            for (var j = 0; j < count; j++) {
                matrix[i][j] = Integer.MAX_VALUE;
            }
        }
        return matrix;
    }
}
