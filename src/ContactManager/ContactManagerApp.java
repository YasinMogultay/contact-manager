package ContactManager;
import java.util.Scanner;

public class ContactManagerApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("***Welcome to Contacts Manager***\n");
        System.out.println(ContactTools.menuDisplay());
        int userInput = scanner.nextInt();
        ContactTools.executeChoice(userInput);
    }

}
