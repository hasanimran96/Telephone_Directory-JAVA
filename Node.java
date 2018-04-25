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
public class Node {

    Contact myContact;
    Node next;
    Node prev;

    Node(String name, String number) {
        myContact = new Contact(name, number);
        next = prev = null;
    }

}
