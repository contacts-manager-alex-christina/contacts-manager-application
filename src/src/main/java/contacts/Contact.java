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
