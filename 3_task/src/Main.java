import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        var fileHandler = new FileHandler();
        var stringTaskInformation = fileHandler.ReadFile("in.txt");
        var task = fileHandler.GetTaskInformationFromString(stringTaskInformation);
        var fordBellman = new FordBellman();
        var answer = fordBellman.Algorithm(task.getSource(), task.getDestination(), task.getAdjectivesMatrix());
        fileHandler.WriteFile("out.txt", answer);
    }
}
