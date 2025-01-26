import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskFile {
    private String filePath;

    public TaskFile(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTask(String filePath) throws FileNotFoundException {
        ArrayList<Task> list = new ArrayList<>();

        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            Task task = new Task(s.nextLine());
            list.add(task);
        }
        s.close();
        return list;
    }

    public void saveTask(ArrayList<Task> list, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (Task task : list) {
            fw.write(task.toString());
        }
        
        fw.close();

    }
}
