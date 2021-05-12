import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        var fileHandler = new FileHandler();
        var o = fileHandler.ReadFile("in.txt");
        var task = fileHandler.GetTaskInformationFromString(o);
        var alg = new FordBellman();
        var ans = alg.Algorithm(task.getSource(), task.getDestination(), task.getAdjectivesMatrix());
        fileHandler.WriteFile("out.txt", ans);
    }
}
