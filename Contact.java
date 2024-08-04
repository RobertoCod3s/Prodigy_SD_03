public class Contact {
    public String name;
    public String surname;
    public String phoneNumber;
    public String email;

    public Contact(String n, String sn, String pH, String e) {
        name = n;
        surname = sn;
        phoneNumber = pH;
        email = e;
    }

    public boolean equals(Contact o) {
        if(this.name.equals(o.name) && this.surname.equals(o.surname) && this.phoneNumber.equals(o.phoneNumber) && this.email.equals(o.email)) {return true;}

        return false;
    }

    public boolean kindaEquals(String o) {
        if(this.name.equals(o) || this.surname.equals(o) || this.phoneNumber.equals(o) || this.email.equals(o)) {return true;}
        return false;
    }
}
