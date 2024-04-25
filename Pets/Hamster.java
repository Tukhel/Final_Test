import java.time.LocalDate;
import java.util.List;

class Hamster extends Pet {
    private List<String> commands;

    public Hamster(String name, LocalDate birthday, List<String> commands) {
        super("Хомяк", name, birthday);
        this.commands = commands;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void addCommand(String command) {
        commands.add(command);
    }
}