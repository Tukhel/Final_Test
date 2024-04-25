import java.time.LocalDate;
import java.util.List;

class Dog extends Pet {
    private List<String> commands;

    public Dog(String name, LocalDate birthday, List<String> commands) {
        super("Собака", name, birthday);
        this.commands = commands;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void addCommand(String command) {
        commands.add(command);
    }
}