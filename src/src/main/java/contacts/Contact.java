package contacts;

public class Contact {

    protected String contactName;

    protected String contactPhone;
    public Contact(String name, String phoneNumber) {
        contactName = name;
        contactPhone = phoneNumber;
    }

    @Override
    public String toString() {
        return contactName + "," + contactPhone;
    }

    public static Contact stringToObject (String fileString){
        String [] pieces = fileString.split(",");
        return new Contact(pieces[0], pieces[1]);
    }

    public static String objectToString (Contact fileObject){
        return String.format("%s, %s", fileObject.contactName, fileObject.contactPhone);
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
