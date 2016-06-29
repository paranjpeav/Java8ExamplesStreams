package com.rresino.java8.examples.streams.data;

/**
 * Created by rresino on 04/03/2016.
 */
public class User {

    String name;
    String lastName;
    int age;
    String email;
    String login;


    String country;

    public User() {
    }

    public User(String name, String lastName, int age, String country) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.login = name.toLowerCase();
        this.email = name.toLowerCase()+'.'+lastName.toLowerCase()+"@gmail.com";
        this.country = country;
    }

    public User(String name, String lastName, int age, String email, String login, String country) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.login = login;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (country != null ? !country.equals(user.country) : user.country != null) return false;
        return login != null ? login.equals(user.login) : user.login == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
