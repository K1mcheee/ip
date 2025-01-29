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
                String[] split = line.split(" ");

                Task task;

                switch (split[1]) {
                case "todo":
                    task = Todo.handle(line,2);

                    if (split[0].equals("1")) {
                        task.update();
                    }
                    break;
                case "deadline":
                    task = Deadline.handle(line,2);

                    if (split[0].equals("1")) {
                        task.update();
                    }
                    break;
                case "event":
                    task = Event.handle(line,2);

                    if (split[0].equals("1")) {
                        task.update();
                    }
                    break;
                default:
                    task = new Task(line);
                    break;
                }

                list.add(task);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public void saveTask(String input, boolean status) throws IOException {
        File file = new File(this.filePath);
        file.getParentFile().mkdirs();
        FileWriter fw = new FileWriter(this.filePath, true);
        String tmp = status ? "1 " : "0 ";

        try (BufferedWriter writer = new BufferedWriter(fw)) {
            writer.write(tmp + input);
            writer.newLine();
        }
        
        fw.close();

    }

    public void updateTask(List<Task> list) throws IOException {
        File file = new File(this.filePath);
        file.getParentFile().mkdirs();
        FileWriter fw = new FileWriter(this.filePath);

            try (BufferedWriter writer = new BufferedWriter(fw)) {
                for (Task task : list) {
                    String tmp = task.isDone() ? "1 " : "0 ";
                    writer.write(tmp + task.handle());
                    writer.newLine();
                }
            }

    }
}
