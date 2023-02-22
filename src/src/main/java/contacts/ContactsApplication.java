package contacts;

import util.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ContactsApplication {

    static ArrayList <Contact> contacts = new ArrayList<>();

    public static void main(String[] args) {

        while(true) {

            populateContacts();

            printMenu();

            Input user = new Input();

            int selection = Input.getInt(1, 5);

            // Clear buffer
            Input.clearBuffer();

            if (selection == 5) {
                System.out.println("Program exited.");
                break;
            } else {
                try{
                    chooseOption(selection);
                } catch (IOException e){
                    System.out.println("IOException Happened");
                }
            }
        }
        try{
            refreshData();
        } catch (IOException e){
            System.out.println("IOException Happened in refresh");
        }
    }

    private static void refreshData() throws IOException {
        for (Contact contact : contacts) {
            Files.write(
                    Paths.get( "data.txt"),
                    Collections.singletonList(contact.getContactName() + "," + contact.getContactPhone()),
                    StandardOpenOption.APPEND
            );
        }
    }

    private static void populateContacts() {

    }

    private static void printMenu () {
        System.out.print("What would you like to do?\n" +
                "1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "\n" +
                "Enter your choice (1 - 5): ");
    }

    private static void chooseOption(int selection) throws IOException {
        switch (selection){
            case 1 -> printContacts();
            case 2 -> addContacts();
            case 3 -> searchContact();
            case 4 -> deleteContact();
        }
    }

    private static void deleteContact() {
    }

    private static void searchContact() {
    }

    private static void addContacts() throws IOException {
        String contactName = Input.getString("New contact name: ");
        String contactNumber = Input.getString("New contact number: ");
        Contact newContact = new Contact(contactName, contactNumber);
        contacts.add(newContact);
        System.out.println("Added contact: " + newContact);
    }

    private static void printContacts() {
    }


}
