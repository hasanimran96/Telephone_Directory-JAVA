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
public class DoublyLinkedList {

    Node head;

    public void insertAtHead(String name, String number) {
        Node newNode = new Node(name, number);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    public void display() {
        Node Temp = head;
        while (Temp != null) {
            Temp.myContact.display();
            Temp = Temp.next;
        }
    }

    public void delete(String name) {
        Node Temp = head;
        while (Temp != null && Temp.myContact.name.compareTo(name) != 0) {
            Temp = Temp.next;
        }
        if (Temp != null && Temp.myContact.name.compareTo(name) == 0) {// delete middle node
            Node Temp2 = Temp;
            if (Temp == head) {
                head = Temp.next;
                Temp.next.prev = null;
            } else if (Temp.next == null) {
                Temp.prev.next = null;
            } else {
                Node Temp1 = Temp.prev;
                Temp.prev.next = Temp.next;
                Temp.next.prev = Temp1;
            }
        } else {
            System.out.println("Data Not found");
        }
    }

}
