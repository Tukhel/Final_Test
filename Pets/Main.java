public class Main {
    public static void main(String[] args) {
        Registry registry = new Registry();
        Menu menu = new Menu(registry);
        menu.displayMenu();
    }
}