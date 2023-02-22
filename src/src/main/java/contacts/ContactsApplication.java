package contacts;

public class ContactsApplication {

    public static void main(String[] args) {

        while(true) {

            printMenu();

            int selection = input.getInt(1, 5);

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
        if (selection == 1) {
            printContacts();
        }
        if (selection == 2) {
            addContact();
        }
        if (selection == 3) {
            searchContact();
        }
        if (selection == 4) {
            deleteContact();
        }

    }







}
