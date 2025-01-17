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
     * Checks whether tasks is done and return accordingly
     * @return Task name and indication of status
     */
    @Override
    public String toString() {
        String mark = this.isDone ? "X" : " ";
        return String.format(" [%s] %s", mark, this.name);
    }

}
