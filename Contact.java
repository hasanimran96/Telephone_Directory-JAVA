/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonedirectory;

/**
 *
 * @author hasan
 */
public class Contact {

    String name, phoneNumber;

    Contact(String Name, String number) {
        name = Name;
        phoneNumber = number;
    }

    public Contact() {

    }

    void display() {
        System.out.println("Name " + name + " Number " + phoneNumber);
    }

}
