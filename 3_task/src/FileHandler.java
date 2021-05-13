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
}
