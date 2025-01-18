public class Event extends Task{
    private String to;
    private String from;

    public Event(String name, String to, String from) {
        super(name);
        this.to = to;
        this.from = from;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }
}
