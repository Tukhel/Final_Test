import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class Pet {
	private String petClass;
	private String name;
	private LocalDate birthday;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	public Pet(String petClass, String name, LocalDate birthday) {
		this.petClass = petClass;
		this.name = name;
		this.birthday = birthday;
	}

	public String getBirthday() {
		return birthday.format(formatter);
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public Pet(String name, LocalDate birthday) {
		this.name = name;
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getPetClass() {
        return petClass;
    }

	public abstract List<String> getCommands();

	
}