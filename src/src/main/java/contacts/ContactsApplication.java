package contacts;

import java.util.concurrent.TimeUnit;
import util.Input;
import java.io.IOException;
import java.nio.file.*;
import java.security.KeyStore;
import java.util.*;

public class ContactsApplication {


    public static final String TEXT_CYAN = "\u001B[36m";
    public static final String TEXT_BLUE = "\u001B[34m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_PURPLE = "\u001B[35m";
    public static final String TEXT_WHITE = "\u001B[37m";

    public static final String ANSI_BRIGHT_BLACK  = "\u001B[90m";
    public static final String ANSI_BRIGHT_RED    = "\u001B[91m";
    public static final String ANSI_BRIGHT_GREEN  = "\u001B[92m";
    public static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";
    public static final String ANSI_BRIGHT_BLUE   = "\u001B[94m";
    public static final String ANSI_BRIGHT_PURPLE = "\u001B[95m";
    public static final String ANSI_BRIGHT_CYAN   = "\u001B[96m";
    public static final String ANSI_BRIGHT_WHITE  = "\u001B[97m";

    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_BG_BLACK  = "\u001B[40m";

    public static final String ANSI_BRIGHT_BG_BLACK  = "\u001B[100m";
    public static final String ANSI_BRIGHT_BG_RED    = "\u001B[101m";
    public static final String ANSI_BRIGHT_BG_GREEN  = "\u001B[102m";
    public static final String ANSI_BRIGHT_BG_YELLOW = "\u001B[103m";
    public static final String ANSI_BRIGHT_BG_BLUE   = "\u001B[104m";
    public static final String ANSI_BRIGHT_BG_PURPLE = "\u001B[105m";
    public static final String ANSI_BRIGHT_BG_CYAN   = "\u001B[106m";
    public static final String ANSI_BRIGHT_BG_WHITE  = "\u001B[107m";

    protected static final Path DATA_PATH = Paths.get("data.txt");


    static ArrayList<Contact> contacts = new ArrayList<>();

    public static void main(String[] args) {

        Input user = new Input();
        checkFileExists();
        
        populateContacts();

        howdy();
        while(true) {
            printMenu();
            int selection = Input.getInt(1, 5);
            // Clear buffer
            Input.clearBuffer();
            if (selection == 5) {

                System.out.println("Program exited.");
                bye();

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


    private static void bye() {
        System.out.print(ANSI_BRIGHT_CYAN + ANSI_PURPLE_BACKGROUND + "" +
                "_____/\\\\\\\\\\\\\\\\\\______/\\\\\\\\\\\\\\\\\\\\\\\\______/\\\\\\\\\\\\\\\\\\\\\\________/\\\\\\\\\\___________/\\\\\\\\\\\\\\\\\\\\\\___        \n" +
                " ___/\\\\\\\\\\\\\\\\\\\\\\\\\\___\\/\\\\\\////////\\\\\\___\\/////\\\\\\///_______/\\\\\\///\\\\\\_______/\\\\\\/////////\\\\\\_       \n" +
                "  __/\\\\\\/////////\\\\\\__\\/\\\\\\______\\//\\\\\\______\\/\\\\\\________/\\\\\\/__\\///\\\\\\____\\//\\\\\\______\\///__      \n" +
                "   _\\/\\\\\\_______\\/\\\\\\__\\/\\\\\\_______\\/\\\\\\______\\/\\\\\\_______/\\\\\\______\\//\\\\\\____\\////\\\\\\_________     \n" +
                "    _\\/\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\__\\/\\\\\\_______\\/\\\\\\______\\/\\\\\\______\\/\\\\\\_______\\/\\\\\\_______\\////\\\\\\______    \n" +
                "     _\\/\\\\\\/////////\\\\\\__\\/\\\\\\_______\\/\\\\\\______\\/\\\\\\______\\//\\\\\\______/\\\\\\___________\\////\\\\\\___   \n" +
                "      _\\/\\\\\\_______\\/\\\\\\__\\/\\\\\\_______/\\\\\\_______\\/\\\\\\_______\\///\\\\\\__/\\\\\\______/\\\\\\______\\//\\\\\\__  \n" +
                "       _\\/\\\\\\_______\\/\\\\\\__\\/\\\\\\\\\\\\\\\\\\\\\\\\/_____/\\\\\\\\\\\\\\\\\\\\\\_____\\///\\\\\\\\\\/______\\///\\\\\\\\\\\\\\\\\\\\\\/___ \n" +
                "        _\\///________\\///___\\////////////______\\///////////________\\/////__________\\///////////____\n" +
                "\n" +
                "\n" +
                "\n" +
                "                      /^--^\\     /^--^\\     /^--^\\\n" +
                "                      \\____/     \\____/     \\____/\n" +
                "                     /      \\   /      \\   /      \\\n" +
                "                    |        | |        | |        |\n" +
                "                     \\__  __/   \\__  __/   \\__  __/\n" +
                "|^|^|^|^|^|^|^|^|^|^|^|^\\ \\^|^|^|^/ /^|^|^|^|^\\ \\^|^|^|^|^|^|^|^|^|^|^|^|\n" +
                "| | | | | | | | | | | | |\\ \\| | |/ /| | | | | | \\ \\ | | | | | | | | | | |\n" +
                "| | | | | | | | | | | | / / | | |\\ \\| | | | | |/ /| | | | | | | | | | | |\n" +
                "| | | | | | | | | | | | \\/| | | | \\/| | | | | |\\/ | | | | | | | | | | | |\n" +
                "#########################################################################\n" +
                "| | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | |\n" +
                "| | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | |\n");

    }

    private static void howdy() {

        System.out.print(ANSI_BRIGHT_CYAN + ANSI_PURPLE_BACKGROUND + "" +

                "__/\\\\\\________/\\\\\\________/\\\\\\\\\\________/\\\\\\______________/\\\\\\___/\\\\\\\\\\\\\\\\\\\\\\\\______/\\\\\\________/\\\\\\_        \n" +
                " _\\/\\\\\\_______\\/\\\\\\______/\\\\\\///\\\\\\_____\\/\\\\\\_____________\\/\\\\\\__\\/\\\\\\////////\\\\\\___\\///\\\\\\____/\\\\\\/__       \n" +
                "  _\\/\\\\\\_______\\/\\\\\\____/\\\\\\/__\\///\\\\\\___\\/\\\\\\_____________\\/\\\\\\__\\/\\\\\\______\\//\\\\\\____\\///\\\\\\/\\\\\\/____      \n" +
                "   _\\/\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\___/\\\\\\______\\//\\\\\\__\\//\\\\\\____/\\\\\\____/\\\\\\___\\/\\\\\\_______\\/\\\\\\______\\///\\\\\\/______     \n" +
                "    _\\/\\\\\\/////////\\\\\\__\\/\\\\\\_______\\/\\\\\\___\\//\\\\\\__/\\\\\\\\\\__/\\\\\\____\\/\\\\\\_______\\/\\\\\\________\\/\\\\\\_______    \n" +
                "     _\\/\\\\\\_______\\/\\\\\\__\\//\\\\\\______/\\\\\\_____\\//\\\\\\/\\\\\\/\\\\\\/\\\\\\_____\\/\\\\\\_______\\/\\\\\\________\\/\\\\\\_______   \n" +
                "      _\\/\\\\\\_______\\/\\\\\\___\\///\\\\\\__/\\\\\\________\\//\\\\\\\\\\\\//\\\\\\\\\\______\\/\\\\\\_______/\\\\\\_________\\/\\\\\\_______  \n" +
                "       _\\/\\\\\\_______\\/\\\\\\_____\\///\\\\\\\\\\/__________\\//\\\\\\__\\//\\\\\\_______\\/\\\\\\\\\\\\\\\\\\\\\\\\/__________\\/\\\\\\_______ \n" +
                "        _\\///________\\///________\\/////_____________\\///____\\///________\\////////////____________\\///________\n\n");
    }



    public static void slowPrint(String output, String textColor, String bgColor) {
        if (textColor == null){
            System.out.print(output);
        } else {
            System.out.print(textColor + bgColor + output);
        }
        try {
            TimeUnit.MILLISECONDS.sleep(220);
        }
        catch (Exception e) {
            System.out.println("exception occured");
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
        System.out.print(ANSI_BRIGHT_CYAN + ANSI_PURPLE_BACKGROUND + "" +
                "What would you like to do?\n" +
                "1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "\n" +
                "Enter your choice (1 - 5): \n" + ANSI_BRIGHT_CYAN + ANSI_PURPLE_BACKGROUND);
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
        int count = 0;
        String nameString = "Name";
        String numberString = "Phone Number";

        System.out.printf(ANSI_BRIGHT_BG_YELLOW + ANSI_BRIGHT_RED +"-------------------------------------\n");

        System.out.printf("| %-18s | %-12s |\n", nameString, numberString);
        System.out.print("-------------------------------------\n");
        for (Contact contact : contacts) {
            if(contact.getContactName().contains(search) || contact.getContactPhone().contains(search) || contact.getContactPhone().replaceAll("-", "").contains(search)){
                System.out.printf("| %-18s | %-12s |\n", contact.contactName, contact.contactPhone);
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No matching search results found.");
        }
        System.out.printf("-------------------------------------\n");

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

        wolf();
        
        System.out.println(ANSI_BRIGHT_BG_YELLOW + ANSI_BRIGHT_RED + "\n-------------------------------------------");

        System.out.printf("\n" +
                "| %-18s |    %-15s |\n", nameString, numberString);
        System.out.println(ANSI_BRIGHT_BG_YELLOW + ANSI_BRIGHT_RED + "-------------------------------------------\n");

        for (Contact contact : contacts) {
            slowPrint(String.format("| %-18s |    %-15s |\n", contact.contactName, contact.contactPhone), ANSI_BRIGHT_BG_YELLOW, ANSI_BRIGHT_RED);
        }
        System.out.println(ANSI_BRIGHT_BG_YELLOW + ANSI_BRIGHT_RED + "-------------------------------------------\n\n");

    }

    private static void wolf() {
        System.out.println(ANSI_BRIGHT_BG_WHITE + "\n" +
                "                      .\n" +
                "                     / V\\\n" +
                "                  / `   /\n" +
                "                 <<     |\n" +
                "                 /      |\n" +
                "               /        |\n" +
                "             /          |\n" +
                "           /      \\ \\   /\n" +
                "          (        ) | |\n" +
                "  ________|    _ /__ | |\n" +
                "< _________\\_________)\\__)\n" );

    }


}