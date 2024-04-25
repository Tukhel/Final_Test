import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Registry {

	private String PATH = "./Pets/registry/Pets.txt";
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public void addPet(Pet pet) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH, true))) {
            writer.write(pet.getPetClass() + "|" + pet.getName() + "|" + pet.getBirthday() + "|" + pet.getCommands());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public List<Pet> getAllPets() {
		List<Pet> pets = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("\\|");
				String name = parts[1];
				LocalDate birthday = LocalDate.parse(parts[2], formatter);
				List<String> commands = Arrays.asList(parts[3].split(","));
				switch (parts[0]) {
					case "Собака":
						pets.add(new Dog(name, birthday, commands));
						break;
					case "Кошка":
						pets.add(new Cat(name, birthday, commands));
						break;
					case "Хомяк":
						pets.add(new Hamster(name, birthday, commands));
						break;
					default:
						throw new IllegalArgumentException("Неизвестный класс питомца");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pets;
	}

    public List<String> getPetCommands(String name) {
        List<String> commands = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[1].equals(name)) {
                    commands = Arrays.asList(parts[3].split(","));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commands;
    }

    public void teachNewCommand(String name, String command) {
		try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
			List<String> lines = new ArrayList<>();
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("\\|");
				if (parts[1].equals(name)) {
					parts[3] = parts[3] + "," + command;
				}
				lines.add(String.join("|", parts));
			}
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH))) {
				for (String l : lines) {
					writer.write(l);
					writer.newLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Pet> getPetsByBirthday(LocalDate birthday) {
		List<Pet> pets = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("\\|");
				LocalDate petBirthday = LocalDate.parse(parts[2], formatter);
				if (petBirthday.equals(birthday)) {
					List<String> commands = Arrays.asList(parts[3].split(","));
					Pet pet;
					switch (parts[0]) {
						case "Собака":
							pet = new Dog(parts[1], petBirthday, commands);
							break;
						case "Кошка":
							pet = new Cat(parts[1], petBirthday, commands);
							break;
						case "Хомяк":
							pet = new Hamster(parts[1], petBirthday, commands);
							break;
						default:
							throw new IllegalArgumentException("Неизвестный тип питомца");
					}
					pets.add(pet);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pets;
	}
	
	public int getTotalPetCount() {
		int count = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
			while (reader.readLine() != null) {
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return count;
	}
}