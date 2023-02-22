package contacts;

public class Contact {

    protected String contactName;

    protected Long contactPhone;
    public Contact(String name, Long phoneNumber) {
        contactName = name;
        contactPhone = phoneNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Long getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(Long contactPhone) {
        this.contactPhone = contactPhone;
    }
}
