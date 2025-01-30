public abstract class Command {
    private boolean isRunning = true;

    public abstract void execute(TaskList tasks, Ui ui, TaskFile storage);

    public boolean isRunning() {
        return isRunning;
    }

    protected void goodbye() {
        isRunning = false;
    }
}
