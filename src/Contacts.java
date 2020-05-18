import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Contacts {
    public static String directory = "contacts";
    public static String contactsFile = "contacts.txt";
    public static Path contactsPath = Paths.get(directory, contactsFile);

    public static void viewContacts() {
        //print contacts

        try {
            List<String> contactList = Files.readAllLines(contactsPath);

            for (int i = 0; i < contactList.size(); i += 1) {
                System.out.println((i + 1) + ": " + contactList.get(i));
            }
            System.out.println();
        } catch (Exception IOException){
            System.out.println("f this");
        }
    }

    public static void addContact() {
        //prompt and take in name + number
        // append a contact to a file.
        try {
            System.out.println("Enter full name ");
            Scanner scanner = new Scanner(System.in);
            String userResponse = scanner.nextLine();
            Files.write(contactsPath, Arrays.asList(userResponse), StandardOpenOption.APPEND);
            System.out.println();
        } catch (IOException e) {
            System.out.println("There was an issue adding the contact");
            e.printStackTrace();
        }
    }


//    public searchContact() {
//        //find and print if a contact exists
//        //if contact exists, print it
//    }
//
//    public deleteContact() {
//        //read in contacts as list
//        //remove contact if it matches
//        //update file
//    }

    public static void main(String[] args) {


        //while loop that prompts users for a number
        //exit when 5 is entered
        //call method that correlates with the number input

        boolean running = true;
        while(running) {
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
            if(userResponse == 5){
                running = false;
            } else if (userResponse == 1){
                viewContacts();
            } else if (userResponse == 2){
                addContact();
            }
        }
    }
}
