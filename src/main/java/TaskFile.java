import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskFile {
    private String filePath;
    private Path directory;

    public TaskFile(String filePath) {
        this.filePath = filePath;
        this.directory = Paths.get(filePath).getParent();
    }

    public List<Task> loadTask() throws FileNotFoundException {
        List<Task> list = new ArrayList<>();

        File f = new File(this.filePath);
        // Scanner s = new Scanner(f);
        try(BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = new Task(line);
                list.add(task);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public  void saveTask(List<Task> list) throws IOException {
        File file = new File(this.filePath);
        file.getParentFile().mkdirs();
        FileWriter fw = new FileWriter(this.filePath);

        try (BufferedWriter writer = new BufferedWriter(fw)) {
            for (Task task : list) {
                writer.write(task.toString());
                writer.newLine();
            }
        }
        
        fw.close();

    }
}
