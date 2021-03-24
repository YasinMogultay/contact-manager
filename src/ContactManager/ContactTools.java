package ContactManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactTools {
    private static final Path cmp = Paths.get("src", "ContactManager", "peopleContacts.txt");

    //Prompting user for a recursion.
    private static void promptUserForChoice() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Do you want to continue (y/n)\n");
        String userInput = sc.nextLine();
        if (userInput.equalsIgnoreCase("y")) {
            System.out.println(menuDisplay());
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Your Choice!");
            int userInput1 = scanner.nextInt();
            System.out.println(executeChoice(userInput1));
        } else {
            System.out.println("* * * Thanks for using! * * *");
        }
    }


    //Displaying main menu
    public static String menuDisplay() {
        return "Would you like to?\n\n" +
                "1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search contacts by name.\n" +
                "4. Delete Existing contact.\n" +
                "5. Exit" +
                "\n\n" +
                "Select 1, 2, 3, 4, or 5";
    }


    //Iterating through .txt file and returning all contacts
    public static void viewContacts() {
        List<String> contacts = new ArrayList<>();

        try {
            contacts = Files.readAllLines(cmp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("--------------------------------------------");
        for (String contact : contacts) {
            String[] splitContact = contact.split(" ", 3);
            System.out.println("Name: " + splitContact[0] + " " + splitContact[1] + " | " + "Number: " + splitContact[2]);
        }
        System.out.println("--------------------------------------------");
    }

    //Taking user input and adding it to .txt file
    public static void addNewContact() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name and number");
        String c = sc.nextLine();
        List<String> contacts = new ArrayList<>();
        contacts.add(c);
        try {
            Files.write(cmp, contacts, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Taking user input and adding it to the existing ArrayList and then displaying it
    public static void searchContact() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a name");
        List<String> contacts = new ArrayList<>();
        List<String> contactToSearch = new ArrayList<>();

        try {
            contacts = Files.readAllLines(cmp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String userInput = sc.nextLine();
        for (String contact : contacts) {
            if (contact.contains(userInput)) {
                contactToSearch.add(contact);
                String[] splitContact = contact.split(" ", 3);
                System.out.println("Name: " + splitContact[0] + " " + splitContact[1] + " | " + "Number: " + splitContact[2]);
            }
        }
        if (contactToSearch.size() == 0) {
            System.out.println("Person doesn't match");
        }
    }


    //According to user input deleting existing data
    public static void deleteExistingContact() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a name that you want to delete");
        List<String> contacts = new ArrayList<>();
        List<String> contactsToDelete = new ArrayList<>();

        try {
            contacts = Files.readAllLines(cmp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String userInput = sc.nextLine();
        for (String contact : contacts) {
            if (contact.contains(userInput)) {
                contactsToDelete.add(contact);
                System.out.println("Contact has been deleted");
            }
        }
        if (contactsToDelete.size() == 0) {
            System.out.println("Person doesn't match");
        }
        contacts.removeAll(contactsToDelete);
        try {
            Files.write(cmp, contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //User selections and recursion for all above
    public static boolean executeChoice(int choice) {
        boolean keepRunning = true;
        switch (choice) {
            case 1:
                viewContacts();
                promptUserForChoice();
                break;
            case 2:
                addNewContact();
                promptUserForChoice();
                break;
            case 3:
                searchContact();
                promptUserForChoice();
                break;
            case 4:
                deleteExistingContact();
                promptUserForChoice();
                break;
            case 5:
                System.out.println("* * * Thanks for using! * * *");
                keepRunning = false;
                break;

        }
        return keepRunning;
    }

}

