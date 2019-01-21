package hr.foi.watchme.POJO;

public class User {
    public int ID;
    public String Name;
    public String Surname;
    public String email;
    public String Password;

    public int getId() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        this.Surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String emajl) {
        this.email = emajl;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}
