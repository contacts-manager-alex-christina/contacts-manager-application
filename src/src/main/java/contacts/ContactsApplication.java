package contacts;

import util.Input;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class ContactsApplication {
    protected static final Path DATA_PATH = Paths.get("data.txt");

    static ArrayList<Contact> contacts = new ArrayList<>();

    public static void main(String[] args) {
        Input user = new Input();
        checkFileExists();
        populateContacts();
        while(true) {
            printMenu();
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
            System.out.println("IOException Happened in refreshData()");
        }
    }

    private static void refreshData() throws IOException {
        ArrayList<String> newCollection = new ArrayList<>();
        for (Contact contact : contacts) {
            newCollection.add(Contact.objectToString(contact));
        }
        Files.write(
                DATA_PATH,
                newCollection
        );
    }

    private static void checkFileExists() {
        if(!Files.exists(DATA_PATH)) {
            try {
                Files.createFile(DATA_PATH);
                System.out.println("File readied..");
            } catch(FileAlreadyExistsException e) {
                System.out.println("File is ready..");
            } catch (IOException e) {
                System.out.println("createFile exception: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

        private static void populateContacts() {
        try{
            List<String> stringedData;
            stringedData = Files.readAllLines(DATA_PATH);
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
        for (int i = 0; i < contacts.size(); i++) {
            System.out.printf("%-4d| %-18s | %-12s |\n", (i +1), contacts.get(i).contactName, contacts.get(i).contactPhone);
        }
        int delete = Input.getInt(1, contacts.size(), "What is the number of the contact you'd like to delete?: ");
        Input.clearBuffer();
        boolean yn = Input.yesNo("Are you sure you would like to delete the contact for " + contacts.get(delete -1).contactName + " " + contacts.get(delete -1).contactPhone + "[yes/no]? ");
        if (yn) {
            contacts.remove(delete - 1);
            System.out.println("Contact has been deleted.");
        }
    }

    private static void searchContact() {
        int searchBy = Input.getInt(1, 2 ,"Would you like to search by: (Enter 1 or 2)\n1. Name\n2. Number");
        Input.clearBuffer();
        String search;
        if(searchBy == 1){
            search = Input.getString("Name Search: ");
            printSearch(search);
        }
        if(searchBy == 2){
            search = Input.getString("Number Search: ");
            printSearch(search);
        }
    }

    private static void printSearch(String search) {
        String nameString = "Name";
        String numberString = "Phone Number";
        System.out.println("\n");
        System.out.print("-------------------------------------");
        System.out.printf("\n" +
                "| %-18s | %-12s |\n", nameString, numberString);
        System.out.print("-------------------------------------\n");
        for (Contact contact : contacts) {
            if(contact.getContactName().contains(search) || contact.getContactPhone().contains(search) || contact.getContactPhone().replaceAll("-", "").contains(search)){
                System.out.printf("| %-18s | %-12s |\n", contact.contactName, contact.contactPhone);
            }
        }
        System.out.print("-------------------------------------\n\n");
    }

    private static boolean checkDuplicates(String contactName) {
        for (Contact contact : contacts) {
            if(contact.getContactName().equalsIgnoreCase(contactName)){
                return true;
            }
        }
        return false;
    }

    private static boolean validifyDigits (String phoneNumber){
        if(phoneNumber.length() == 10){
            return true;
        }
        System.out.println("Invalid Number\nPlease input a 10 digit phone number");
        return false;
    }

    private static void addContacts() {
        String contactName = Input.getString("New contact name: ");
        if(checkDuplicates(contactName)){
            boolean overrideResponse = Input.yesNo("Contact name already exists\nContinue and override? [y/n]");
            if(!overrideResponse){
                return;
            }
        }
        contacts.removeIf(contact -> contact.getContactName().equalsIgnoreCase(contactName));
        String contactNumber;
        do {
            contactNumber = Input.getString("New contact number: ");
        } while(!validifyDigits(contactNumber));
        Contact newContact = new Contact(contactName, formatNumber(contactNumber));
        contacts.add(newContact);
        System.out.println("Added contact: " + newContact.contactName);
    }

    private static String formatNumber(String contactNum) {
        return contactNum.substring(0,3) + "-" + contactNum.substring(3,6) + "-" + contactNum.substring(6,10);
    }

    private static void printContacts() {
        String nameString = "Name";
        String numberString = "Phone Number";
        System.out.println("\n");
        System.out.print("-------------------------------------");
        System.out.printf("\n" +
                "| %-18s | %-12s |\n", nameString, numberString);
        System.out.print("-------------------------------------\n");
        for (Contact contact : contacts) {
            System.out.printf("| %-18s | %-12s |\n", contact.contactName, contact.contactPhone);
        }
        System.out.print("-------------------------------------\n");
        System.out.print("\n");
    }
}