package nl.avans.android.todos.domain;

import org.joda.time.DateTime;

/**
 * Created by Matthijs on 16-6-2017.
 */

public class Customer {



    private String firstname;
    private String lastname;
    private String email;
    private DateTime createdAt;
    private String password;

    public Customer(String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = null;
        this.email = null;
        this.createdAt = new DateTime();
    }

    public Customer(String firstname, String lastname, String email, String password,DateTime createdAt) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "email='" + email + '\'' +
                ", password='" + password +
                '}';
    }
}
