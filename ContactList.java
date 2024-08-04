import java.util.ArrayList;

public class ContactList {
    public ArrayList<Contact> contacts;

    public ContactList(){
        contacts = new ArrayList<>();
    }
    public Contact findContact(Contact c){
        Contact found = null;
        for (Contact contact:contacts) {
            if(contact.equals(c)){
                found = contact;
            }
        }
        return found;
    }
    public boolean exists(Contact c){
        boolean found = false;
        for (Contact contact:contacts) {
            if(contact.equals(c)){
                found = true;
                break;
            }
        }
        return found;
    }
    public void addContact(Contact c){
        contacts.add(c);
    }
    public int numContacts(){
        return contacts.size();
    }

    public void removeContact(Contact c) {
        if(exists(c)){
            contacts.remove(c);
        }
        else{
            System.out.println("Contact does not exist");
        }
    }

    public ArrayList<Contact> kindaFindContact(String c){
        ArrayList<Contact> contactsFound = new ArrayList<>();
        Contact found = null;
        for (Contact contact : contacts) {
            if(contact.kindaEquals(c)){
                found = contact;
                contactsFound.add(found);
            }
        }
        return contactsFound;
    }


}
