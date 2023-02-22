package contacts;

import util.Input;

public class ContactsApplication {

    public static void main(String[] args) {

        while(true) {

            printMenu();

            int selection = Input.getInt(1, 5);

            if (selection == 5) {
                System.out.println("Program exited.");
                break;
            } else {
                chooseOption(selection);
            }

        }


    }

    private static void printMenu () {
        System.out.printf("What would you like to do?" +
                "1. View contacts.\n" +
                "\n" +
                "2. Add a new contact.\n" +
                "\n" +
                "3. Search a contact by name.\n" +
                "\n" +
                "4. Delete an existing contact.\n" +
                "\n" +
                "5. Exit.\n" +
                "\n" +
                "Enter your choice: ");
    }

    private static void chooseOption(int selection) {
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

    private static void addContacts() {
    }

    private static void printContacts() {
    }


}
