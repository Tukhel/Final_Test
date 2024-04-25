import java.time.LocalDate;
import java.util.List;

class Cat extends Pet {
    private List<String> commands;

    public Cat(String name, LocalDate birthday, List<String> commands) {
        super("Кошка", name, birthday);
        this.commands = commands;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void addCommand(String command) {
        commands.add(command);
    }
}