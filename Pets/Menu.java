import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Registry registry;
    private Scanner scanner;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Menu(Registry registry) {
        this.registry = registry;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("Выберите действие:\n" +
                                "1. Добавить нового питомца в реестр\n" +
                                "2. Вывести список всех питомцев\n" +
                                "3. Вывести список команд питомца\n" +
                                "4. Обучить новым командам питомца\n" +
                                "5. Вывести список питомцев по дате рождения\n" +
                                "6. Вывести количество добавленных питомцев\n" +
                                "0. Выход");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createNewPet();
                    break;
                case 2:
                    displayAllPets();
                    break;
                case 3:
                    displayPetCommands();
                    break;
                case 4:
                    teachNewPetCommands();
                    break;
                case 5:
                    displayPetsByBirthday();
                    break;
                case 6:
                    displayTotalPetCount();
                    break;
                case 0:
                    System.out.println("Программа завершена.");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void createNewPet() {
        System.out.println("Введите номер класса питомца:\n" +
                            "1. Собака\n" +
                            "2. Кошка\n" +
                            "3. Хомяк");
        
        int petClass = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                petClass = Integer.parseInt(scanner.nextLine());
                if (petClass >= 1 && petClass <= 3) {
                    validInput = true;
                } else {
                    System.out.println("Число должно быть от 1 до 3");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите целое число от 1 до 3");
            }
        }

        System.out.println("Введите имя питомца:");
        String name = scanner.nextLine();
        System.out.println("Введите дату рождения в формате DD.MM.YYYY:");
        LocalDate birthday = LocalDate.now();
        try {
            birthday = LocalDate.parse(scanner.nextLine(), formatter);
        } catch (Exception e) {
            System.out.println("Ошибка: неправильный формат даты");
            createNewPet();
        }

        List<String> commands = new ArrayList<>();
        System.out.println("Введите список команд через запятую:");
        String[] commandArray = scanner.nextLine().split(",");
        commands.addAll(Arrays.asList(commandArray));

        Pet pet;
        switch (petClass) {
            case 1:
                pet = new Dog(name, birthday, commands);
                break;
            case 2:
                pet = new Cat(name, birthday, commands);
                break;
            case 3:
                pet = new Hamster(name, birthday, commands);
                break;
            default:
                System.out.println("Неверный выбор класса питомца.");
                return;
        }

        registry.addPet(pet);
        System.out.println("Питомец успешно добавлено в реестр.");
    }

    private void displayAllPets() {
		List<Pet> pets = registry.getAllPets();
		if (pets.isEmpty()) {
			System.out.println("В реестре не найдены питомцы.");
		} else {
			System.out.println("Все питомцы в реестре:");
			for (Pet pet : pets) {
				System.out.println("Имя: " + pet.getName() + "| Класс: " + pet.getPetClass() 
                                    + "| Дата рождения: " + pet.getBirthday() + "| Список команд:  " + pet.getCommands());
			}
		}
	}

    private void displayPetCommands() {
        System.out.println("Введите имя питомца:");
        String name = scanner.nextLine();

        List<String> commands = registry.getPetCommands(name);
        if(commands.isEmpty()) {
            System.out.println("Питомец с таким именем не найден.");
        } else {
            System.out.println("Список команд для питомца " + name + ": " + String.join(", ", commands));
        }
    }

    private void teachNewPetCommands() {
        System.out.println("Введите имя питомца:");
        String name = scanner.nextLine();
        System.out.println("Введите новые команды через запятую:");
        String command = scanner.nextLine();

        registry.teachNewCommand(name, command);
        System.out.println("Команда успешно добавлена для питомца.");
    }

    private void displayPetsByBirthday() {
        System.out.println("Введите дату рождения для поиска:");
        String birthdayString = scanner.nextLine();
        LocalDate birthday = LocalDate.parse(birthdayString, formatter);

        List<Pet> pets = registry.getPetsByBirthday(birthday);
        if(pets.isEmpty()) {
            System.out.println("Питомцы с такой датой рождения не найдены.");
        } else {
            System.out.println("Питомцы с датой рождения " + birthday + ": ");
            for(Pet pet : pets) {
                System.out.println(pet.getPetClass() + " " + pet.getName());
            }
        }
    }

    private void displayTotalPetCount() {
        int totalPetCount = registry.getTotalPetCount();
        System.out.println("Общее количество добавленных животных: " + totalPetCount);
    }
}