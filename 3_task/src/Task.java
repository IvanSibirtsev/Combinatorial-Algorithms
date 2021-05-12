public class Task {

    private final int source;
    private final int destination;
    private final int[][] adjacencyMatrix;

    public Task(int source, int destination, int[][] A) {
        this.source = source - 1;
        this.destination = destination - 1;
        adjacencyMatrix = A;
    }

    public int[][] getAdjectivesMatrix() {
        return adjacencyMatrix;
    }

    public int getDestination() {
        return destination;
    }

    public int getSource() {
        return source;
    }
}
