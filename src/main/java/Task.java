public class Task {
    private String name;
    private boolean isDone = false;

    public Task(String name) {
        this.name = name;
    }

    /**
     * Marks task as done and prints out status of task
     */
    public void mark() {
        this.isDone = true;
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(this.toString());
    }

    /**
     * Marks task as not done yet and print out status of task
     */
    public void unmark() {
        this.isDone = false;
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println(this.toString());
    }

    /**
     * Check status of task. Returns corresponding icon
     * @return "X" if task is done else returns " "
     */
    private String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Prints the task and status of task
     * @return indication of status followed by task name
     */
    @Override
    public String toString() {
        return String.format(" [%s] %s", this.getStatusIcon(), this.name);
    }

}
