import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Contacts {
    public static String directory = "contacts";
    public static String contactsFile = "contacts.txt";
    public static Path contactsPath = Paths.get(directory, contactsFile);

    public static void main(String[] args) {
        //while loop that prompts users for a number
        //exit when 5 is entered
        //call method that correlates with the number input
        boolean running = true;
        while (running) {
            System.out.println("1. View contacts.\n" +
                    "2. Add a new contact.\n" +
                    "3. Search a contact by name.\n" +
                    "4. Delete an existing contact.\n" +
                    "5. Exit.\n" +
                    "Enter an option (1, 2, 3, 4 or 5):");

            System.out.println("Enter your selection between 1-5");
            System.out.println();
            Scanner scanner = new Scanner(System.in);
            int userResponse = scanner.nextInt();
            if (userResponse == 5) {
                running = false;
            } else if (userResponse == 1) {
                viewContacts();
            } else if (userResponse == 2) {
                addContact();
            } else if (userResponse == 3) {
                searchContact();
            } else if(userResponse == 4) {
                deleteContact();
            }
        }
    }

    public static void makeDir() {
//        if(Files.notExists())
    }

    public static void viewContacts() {
        //print contacts

        try {
            List<String> contactList = Files.readAllLines(contactsPath);

            for (int i = 0; i < contactList.size(); i += 1) {
                System.out.println((i + 1) + ": " + contactList.get(i));
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("There was an issue reading the contact list");
            e.printStackTrace();
        }
    }

    public static void addContact() {
        //prompt and take in name + number
        // append a contact to a file.
        try {
            System.out.println("Enter full name ");
            Scanner scanner = new Scanner(System.in);
            String userResponse = scanner.nextLine();
            Files.write(contactsPath, userResponse.getBytes(), StandardOpenOption.APPEND);
            System.out.println();
        } catch (IOException e) {
            System.out.println("There was an issue adding the contact");
            e.printStackTrace();
        }
    }

    public static void searchContact() {
        try {
            List<String> contactList = Files.readAllLines(contactsPath);
            //find and print if a contact exists
            //if contact exists, print it
            System.out.println("Enter name or number: ");
            Scanner scan = new Scanner(System.in);
            String userResponse = scan.nextLine();
            for (String contact : contactList) {
                if(contact.toLowerCase().contains(userResponse.toLowerCase())) {
                    System.out.println(contact);
                }
            }
        } catch (IOException e) {
            System.out.println("There was an issue searching contact");
            e.printStackTrace();
        }

    }

    public static void deleteContact() {
        //read in contacts as list
        //remove contact if it matches
        //update file
        System.out.println("Delete Contact: ");
        try {
            List<String> contactList = Files.readAllLines(contactsPath);
            List<String> updatedContacts = new ArrayList<>();
            System.out.println("Delete name or number: ");
            Scanner scan = new Scanner(System.in);
            String userResponse = scan.nextLine();
            for (String contact : contactList) {
                if(!contact.toLowerCase().contains(userResponse.toLowerCase())) {
                    updatedContacts.add(contact);
                }
            }
            Files.write(contactsPath, updatedContacts, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("There was an issue searching contact");
            e.printStackTrace();
        }
    }
}
