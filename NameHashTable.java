/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonedirectory;

import java.util.Scanner;

/**
 *
 * @author hasan
 */
public class NameHashTable implements HashTable {

    Contact[] NameTable;

    /*hashtable for name*/
    public NameHashTable(int size) {
        int primeSize = size + (size / 3);
        NameTable = new Contact[primeSize];
        for (int i = 0; i < NameTable.length; i++) {
            NameTable[i] = new Contact();
        }
    }

    public int toInt(String symbol) {
        int sum = 0, i = 0;
        while (i < symbol.length()) {
            int ch = (int) symbol.charAt(i);
            sum = sum + ch;
            i++;
        }
        return sum;
    }

    public int Hash(String symbol) {
        int hashVal = (toInt(symbol)) % NameTable.length;
        return hashVal;
    }

    @Override
    public void insert(DoublyLinkedList list) {
        Node Temp = list.head;
        int i = 0;
        while (Temp != null) {
            String name = Temp.myContact.name;
            int hashVal = Hash(name);
            if (NameTable[hashVal] == null) {
                NameTable[hashVal] = Temp.myContact;
            } else {
                while (NameTable[hashVal] != null && i < NameTable.length) {
                    hashVal = (hashVal + (i + 1)) % NameTable.length;
                    i++;
                }
                NameTable[hashVal] = Temp.myContact;
            }
            Temp = Temp.next;
        }
    }

    @Override
    public void display() {
        System.out.println("NameTable: ");
        for (int i = 0; i < NameTable.length; i++) {
            if (NameTable[i] == null) {
            } else {
                System.out.print(" Contact: ");
                NameTable[i].display();
            }
        }
    }

    @Override
    public void find(String name) {
        // hash function on name.
        // arr[hash].mycontact.name==name, if equal display contact. else rehash function called.
        // until arr[rehash].name==name
        int hash = Hash(name);
        if (NameTable[hash].name.equals(name)) {
            System.out.println(" Contact found ");
            NameTable[hash].display();
        } else {
            int i = 0;
            while (i < NameTable.length && !(NameTable[hash].name.equals(name))) {
                hash = (hash + (i + 1)) % NameTable.length;
                i++;
            }
            if (i == NameTable.length) {
                System.out.println(" Not Found ");
            } else {
                System.out.println(" Contact found ");
                NameTable[hash].display();
            }
        }
    }

    @Override
    public void delete(String name) {
        // find name in hashtable. 
        int hash = Hash(name);
        if (NameTable[hash].name.equals(name)) {
            NameTable[hash] = null;
        } else {
            int i = 0;
            while (i < NameTable.length && !(NameTable[hash].name.equals(name))) {
                hash = (hash + (i + 1)) % NameTable.length;
                i++;
            }
            if (i == NameTable.length) {
                System.out.println(" Contact Not Found, can not delete. ");
            } else {
                NameTable[hash] = null;
            }
        }
    }

    @Override
    public void update(String name) {
        int hash = Hash(name);
        if (NameTable[hash].name.equals(name)) {
            System.out.println(" Update your phone no ");
            Scanner scanner = new Scanner(System.in);
            String no = scanner.nextLine();
            NameTable[hash].phoneNumber = no;
            NameTable[hash].display();
        } else {
            int i = 0;
            while (i < NameTable.length && !(NameTable[hash].name.equals(name))) {
                hash = (hash + (i + 1)) % NameTable.length;
                i++;
            }
            if (i == NameTable.length) {
                System.out.println(" Not Found ");
            } else {
                System.out.println(" Update your phone no ");
                Scanner scanner = new Scanner(System.in);
                String no = scanner.nextLine();
                NameTable[hash].phoneNumber = no;
                NameTable[hash].display();
            }
        }
    }

}
