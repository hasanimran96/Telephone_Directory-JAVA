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
public class NumberHashTable implements HashTable {

    HashTableNode[] NumberTable;
    int defaultSize=100;

    /*hashtable for country,city and region*/
    public NumberHashTable() {
        NumberTable = new HashTableNode[defaultSize];
        for (int i = 0; i < NumberTable.length; i++) {
            NumberTable[i] = new HashTableNode();
        }
    }

    @Override
    public void insert(DoublyLinkedList list) {
        Node temp = list.head;
        while (temp != null) {
            String phNo = temp.myContact.phoneNumber;
            int countryCode = Integer.parseInt(phNo.substring(1, 3));
            int cityCode = Integer.parseInt(phNo.substring(3, 5));
            int regionCode = Integer.parseInt(phNo.substring(5, 7));
            /*
         case1 new country new city new region
         case2 same country same city new region
         case3 same country new city new region
         case4 same country same city same region
             */
            //case1
            if (NumberTable[countryCode].number == 0) {
                NumberTable[countryCode].number = countryCode;
                if (NumberTable[countryCode].next == null) {
                    NumberTable[countryCode].next = new HashTableNode[defaultSize];
                    for (int i = 0; i < NumberTable[countryCode].next.length; i++) {// city table
                        NumberTable[countryCode].next[i] = new HashTableNode();
                    }
                    NumberTable[countryCode].next[cityCode].number = cityCode;
                }
                if (NumberTable[countryCode].next[cityCode].next == null) {
                    NumberTable[countryCode].next[cityCode].next = new HashTableNode[defaultSize];
                    for (int i = 0; i < NumberTable[countryCode].next[cityCode].next.length; i++) {
                        NumberTable[countryCode].next[cityCode].next[i] = new HashTableNode();
                    }
                    NumberTable[countryCode].next[cityCode].next[regionCode].number = regionCode;
                    NumberTable[countryCode].next[cityCode].next[regionCode].nextTree = new Tree();
                    NumberTable[countryCode].next[cityCode].next[regionCode].nextTree.insert(temp.myContact);
                }
            } // case2 && case4
            else if (NumberTable[countryCode].number == countryCode && NumberTable[countryCode].next[cityCode].number == cityCode) {
                if (NumberTable[countryCode].next[cityCode].next[regionCode].number == 0) {
                    NumberTable[countryCode].next[cityCode].next[regionCode].number = regionCode;
                    NumberTable[countryCode].next[cityCode].next[regionCode].nextTree = new Tree();
                    NumberTable[countryCode].next[cityCode].next[regionCode].nextTree.insert(temp.myContact);
                } else {
                    NumberTable[countryCode].next[cityCode].next[regionCode].nextTree.insert(temp.myContact);
                }
            } // case3
            else if (NumberTable[countryCode].number == countryCode) {
                if (NumberTable[countryCode].next[cityCode].number == 0) {
                    NumberTable[countryCode].next[cityCode].number = cityCode;
                }
                if (NumberTable[countryCode].next[cityCode].next == null) {
                    NumberTable[countryCode].next[cityCode].next = new HashTableNode[defaultSize];
                    for (int i = 0; i < NumberTable[countryCode].next[cityCode].next.length; i++) {
                        NumberTable[countryCode].next[cityCode].next[i] = new HashTableNode();
                    }
                    NumberTable[countryCode].next[cityCode].next[regionCode].number = regionCode;
                    NumberTable[countryCode].next[cityCode].next[regionCode].nextTree = new Tree();
                    NumberTable[countryCode].next[cityCode].next[regionCode].nextTree.insert(temp.myContact);
                }
            }
            temp = temp.next;
        }
        display();
    }

    @Override
    public void display() {
        System.out.println("Number Table: ");
        for (int i = 0; i < NumberTable.length; i++) {
            if (NumberTable[i] == null) {
                i++;
            } else {
                if (NumberTable[i].next != null) {
                    for (int j = 0; j < NumberTable[i].next.length; j++) {
                        if (NumberTable[i].next[j] == null) {
                            j++;
                        } else {
                            if (NumberTable[i].next[j].next != null) {
                                for (int k = 0; k < NumberTable[i].next[j].next.length; k++) {
                                    if (NumberTable[i].next[j].next[k] == null) {
                                        k++;
                                    } else {
                                        if (NumberTable[i].next[j].next[k].number != 0) {
                                            System.out.print("Contact: ");
                                            NumberTable[i].next[j].next[k].nextTree.display(NumberTable[i].next[j].next[k].nextTree.Root);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void find(String number) {
        int countryCode = Integer.parseInt(number.substring(1, 2));
        int cityCode = Integer.parseInt(number.substring(2, 4));
        int regionCode = Integer.parseInt(number.substring(4, 6));
        if (NumberTable[countryCode].number == countryCode) {
            if (NumberTable[countryCode].next[cityCode].number == cityCode) {
                if (NumberTable[countryCode].next[cityCode].next[regionCode].number == regionCode) {
                    NumberTable[countryCode].next[cityCode].next[regionCode].nextTree.find(number);
                } else {
                    System.out.println(" Contact not present ");
                }
            } else {
                System.out.println(" Contact not present");
            }
        } else {
            System.out.println(" Contact not present ");
        }
    }

    @Override
    public void update(String number) {
        int countryCode = Integer.parseInt(number.substring(1, 2));
        int cityCode = Integer.parseInt(number.substring(2, 4));
        int regionCode = Integer.parseInt(number.substring(4, 6));
        if (NumberTable[countryCode].number == countryCode) {
            if (NumberTable[countryCode].next[cityCode].number == cityCode) {
                if (NumberTable[countryCode].next[cityCode].next[regionCode].number == regionCode) {
                    NumberTable[countryCode].next[cityCode].next[regionCode].nextTree.updateNumber(number);
                } else {
                    System.out.println(" Contact not present, Cannot update the contact ");
                }
            } else {
                System.out.println(" Contact not present,  Cannot update the contact");
            }
        } else {
            System.out.println(" Contact not present,  Cannot update the contact ");
        }
    }

    @Override
    public void delete(String number) {
        int countryCode = Integer.parseInt(number.substring(1, 2));
        int cityCode = Integer.parseInt(number.substring(2, 4));
        int regionCode = Integer.parseInt(number.substring(4, 6));
        if (NumberTable[countryCode].number == countryCode) {
            if (NumberTable[countryCode].next[cityCode].number == cityCode) {
                if (NumberTable[countryCode].next[cityCode].next[regionCode].number == regionCode) {
                    NumberTable[countryCode].next[cityCode].next[regionCode].nextTree.delete(number);
                } else {
                    System.out.println(" Contact not present, cannot delete ");
                }
            } else {
                System.out.println(" Contact not present, cannot delete ");
            }
        } else {
            System.out.println(" Contact not present, cannot delete ");
        }
    }

}
