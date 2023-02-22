package contacts;

import util.Input;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        Path datapath = Paths.get("data.txt");


        if(!Files.exists(datapath)) {
        try {
            Files.createFile(datapath);
            //System.out.println("The file has been created.");
        } catch(FileAlreadyExistsException e) {
            //System.out.println("File exists");

        } catch (IOException e) {
            System.out.println("createFile exception: " + e.getMessage());
            e.printStackTrace();//tells us which exception is thrown
        }
        }

        try{
            List<String> stringedData = new ArrayList<>();
            stringedData = Files.readAllLines(datapath);
            for (String stringedDatum : stringedData) {
                contacts.add(Contact.stringToObject(stringedDatum));
            }
        } catch (Exception e){
            System.out.println("Exception occurred");
        }
    }

    private static void printMenu () {
        System.out.print("What would you like to do?\n" +
                "1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact.\n" +
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
        String nameOrNum = null;
        String search = null;
        do {
            nameOrNum = Input.getString("Would you like to search by: (Enter 1 or 2)\n1. Name\n2. Number");
        } while(!"1".equals(nameOrNum) && !"2".equals(nameOrNum));
        if("1".equals(nameOrNum)){
            search = Input.getString("Name Search: ");
            for (Contact contact : contacts) {
                if(contact.getContactName().contains(search)){
                    System.out.println(contact);
                }
            }
        } else {
            search = Input.getString("Number Search: ");
            for (Contact contact : contacts) {
                if(contact.getContactName().contains(search)){
                    System.out.println(contact);
                }
            }
        }

    }

    private static void addContacts() throws IOException {
        String contactName = Input.getString("New contact name: ");
        String contactNumber = Input.getString("New contact number: ");
        Contact newContact = new Contact(contactName, contactNumber);
        contacts.add(newContact);
        System.out.println("Added contact: " + newContact);
    }

    private static void printContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }


}
