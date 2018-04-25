/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonedirectory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author hasan
 */
public class PhoneDirectory {

    DoublyLinkedList directory = new DoublyLinkedList();
    HashTable nameTable = new NameHashTable(1000);
    HashTable numberTable = new NumberHashTable();

    public void addDataInDoublyLinkedList() throws FileNotFoundException, IOException {
        BufferedReader namesBR = new BufferedReader(new FileReader("Name List.txt"));
        BufferedReader numbersBR = new BufferedReader(new FileReader("Number List.txt"));
        String nameStr, numberStr;

        List<String> nameList = new ArrayList<>();
        List<String> numberList = new ArrayList<>();
        while ((nameStr = namesBR.readLine()) != null && (numberStr = numbersBR.readLine()) != null) {
            nameList.add(nameStr);
            numberList.add(numberStr);
        }

        String[] nameListArr = nameList.toArray(new String[0]);
        String[] numberListArr = numberList.toArray(new String[0]);

        String[] names = nameListArr[0].split(",");
        String[] numbers = numberListArr[0].split(",");

        for (int i = 0; (i < names.length) && (i < numbers.length); i++) {
            directory.insertAtHead(names[i], numbers[i]);
        }

    }

    public void menu() throws IOException {
        addDataInDoublyLinkedList();
        directory.display();
        System.out.println("---------------------------------------------");
        nameTable.insert(directory);
        nameTable.display();
        System.out.println("---------------------------------------------");
        numberTable.insert(directory);
//        numberTable.display();
        interactiveMenu();
    }

    public void interactiveMenu() {

        boolean exitPhoneBook = false;
        Scanner userInput = new Scanner(System.in);

        while (exitPhoneBook == false) {

            System.out.println("What do you want to do?");
            System.out.println("1. Add a contact");
            System.out.println("2. Show a contact");
            System.out.println("3. Delete a contact");
            System.out.println("4. Show all contacts");
            System.out.println("5. Exit");
            System.out.print("Select a number: ");

            int action = userInput.nextInt();

            switch (action) {
                case 1:
                    //addContact();
                    break;

                case 2:
                    //showContact();
                    break;

                case 3:
                    //deleteContact();
                    break;

                case 4:
                    //showAll();
                    break;

                case 5:
                    System.out.println("Goodbye!");
                    exitPhoneBook = true;
                    break;

                default:
                    System.out.println("Invalid option.");
                    System.out.print("Select a number: ");
                    break;
            }
        }
    }

}