import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
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
        try {
            List<String> contactList = Files.readAllLines(contactsPath);


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
                    //write to file
                    running = false;
                } else if (userResponse == 1) {
                    viewContacts(contactList);
                } else if (userResponse == 2) {
                    contactList.add(getNewContact());
                } else if (userResponse == 3) {
                    List<String> searchResults = searchContact(contactList);
                    for (String result : searchResults) {
                        System.out.println(result);
                    }
                } else if(userResponse == 4) {
                    contactList = deleteContact(contactList);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void makeDir() {
////        if(Files.notExists())
//    }


    // Files.write(contactsPath, updatedContacts, StandardOpenOption.TRUNCATE_EXISTING);


    public static void viewContacts(List<String> contactList) {
        //print contacts
        for (int i = 0; i < contactList.size(); i += 1) {
            System.out.println((i + 1) + ": " + contactList.get(i));
        }
    }

    public static String getNewContact() {
        //prompt and take in name + number
        // add a contact to a file.
        System.out.println("Enter full name ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

//    public static List<String>  addContact(List<String> contactList) {
//        //prompt and take in name + number
//        // add a contact to a file.
//        System.out.println("Enter full name ");
//        Scanner scanner = new Scanner(System.in);
//        String userResponse = scanner.nextLine();
//        //add and return to contact list
//        contactList.add(userResponse);
//        return contactList;
//    }

    public static List<String> searchContact(List<String> contactList) {
        List<String> updatedContacts = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        //find and print if a contact exists
        //if contact exists, print it
        System.out.println("Enter name or number to search: ");
        String userResponse = scan.nextLine();
        for (String contact : contactList) {
            if(contact.toLowerCase().contains(userResponse.toLowerCase())) {
                updatedContacts.add(contact);
            }
        }
        return updatedContacts;
    }

    //Search Method not changing the list, therefore, void here and does not need to return something
//    public static void searchContact(List<String> contactList) {
//            Scanner scan = new Scanner(System.in);
//
//            //find and print if a contact exists
//            //if contact exists, print it
//            System.out.println("Enter name or number to search: ");
//            String userResponse = scan.nextLine();
//            for (String contact : contactList) {
//                if(contact.toLowerCase().contains(userResponse.toLowerCase())) {
//                    System.out.println(contact);
//                }
//            }
//        }


    public static List<String> deleteContact(List<String> contactList) {
        List<String> updatedContacts = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter name or number to delete: ");
        String userResponse = scan.nextLine();
        for (String contact : contactList) {
            if(!contact.toLowerCase().contains(userResponse.toLowerCase())) {
                updatedContacts.add(contact);
            }
        }
        return updatedContacts;
    }
}
