import java.util.HashMap;
import java.util.stream.IntStream;

public class FordBellman {
    private final HashMap<Integer, Integer> distance = new HashMap<>();
    private final HashMap<Integer, Integer> previous = new HashMap<>();

    public String algorithm(int start, int finish, int[][] A) {
        distance.put(start, 0);
        previous.put(start, 0);
        var edges = IntStream.range(0, A.length).toArray();
        for (var v : edges) {
            if (v == start) continue;
            distance.put(v, A[start][v]);
            previous.put(v, start);
        }

        for (var k = 1; k <= A.length - 2; k++) {
            for (var v : edges) {
                if (v == start) continue;
                for (var w : edges) {
                    if (isValid(v, w, A)) {
                        distance.put(v, distance.get(w) + A[w][v]);
                        previous.put(v, w);
                    }
                }
            }
        }
        return getPath(start, finish);
    }

    private boolean isValid(int v, int w, int[][] A) {
        return distance.get(w) + A[w][v] < distance.get(v) &&
                distance.get(w) < Integer.MAX_VALUE && A[w][v] < Integer.MAX_VALUE;
    }

    private String getPath(int start, int finish) {
        if (distance.get(finish) < Integer.MAX_VALUE) {
            var path = new StringBuilder();
            path.append(finish + 1).append(" ");
            var length = distance.get(finish);
            var u = finish;
            while (previous.get(u) != 0) {
                u = previous.get(u);
                path.append(u + 1).append(" ");
            }
            return path.append(start + 1).append("\nY").reverse().append("\n").append(length).toString();
        }
        return "N";
    }
}
