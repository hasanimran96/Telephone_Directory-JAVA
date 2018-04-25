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
public class Tree {

    NodeTree Root;
    Contact myContact;
    private int NoOFPeople = 0;

    public int GetNoOfPeople() {
        return NoOFPeople;
    }

    public void insert(Contact temp) {
        NoOFPeople++;
        NodeTree newNode = new NodeTree();
        if (Root == null) {
            Root = newNode;
            Root.myContact = temp;
        } else {
            NodeTree current = Root;
            NodeTree Parent = current;
            while (current != null) {
                if (current.myContact.phoneNumber.compareTo(temp.phoneNumber) > 0) {
                    Parent = current;
                    current = current.rightChild;
                } else {
                    Parent = current;
                    current = current.leftChild;
                }
            }
            if (Parent.myContact.phoneNumber.compareTo(temp.phoneNumber) > 0) {
                Parent.rightChild = newNode;
                Parent.myContact = temp;
            } else {
                Parent.leftChild = newNode;
                Parent.myContact = temp;
            }
        }
    }

    public void display(NodeTree Temp) {
        Temp = Root;
        this.LNR(Temp);
    }

    public void LNR(NodeTree Temp) {
        if (Temp != null) {
            LNR(Temp.leftChild);
            if (Temp.myContact != null) {
                Temp.myContact.display();
            }
            LNR(Temp.rightChild);
        }
    }

    public NodeTree getSuccessor(NodeTree delNode) {
        NodeTree successorParent = delNode;
        NodeTree successor = delNode;
        NodeTree current = delNode.rightChild;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }
        if (successor != delNode.rightChild) {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }

    public void delete(String number) {
        NodeTree current = Root;
        NodeTree parent = Root;
        boolean isLeftChild = true;
        while (current != null && current.myContact.phoneNumber.compareTo(number) != 0) {
            parent = current;
            if (current.myContact.phoneNumber.compareTo(number) < 0) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }
        }
        if (current == null) {
            System.out.println(" Contact not found, Cannot delete. ");
        } else {
            // if no children
            if (current.leftChild == null && current.rightChild == null) {
                if (current == Root) {
                    Root = null;
                } else if (isLeftChild) {
                    parent.leftChild = null;
                } else {
                    parent.rightChild = null;
                }
            } // One child case1: if no right child, replace with left subtree;
            else if (current.rightChild == null) {
                if (current == Root) {
                    Root = current.leftChild;
                } else if (isLeftChild) {
                    parent.leftChild = current.leftChild;
                } else {
                    parent.rightChild = current.leftChild;
                }
            } //One child case2: if no left child, replace it with right subtree;
            else if (current.leftChild == null) {
                if (current == Root) {
                    Root = current.rightChild;
                } else if (isLeftChild) {
                    parent.leftChild = current.rightChild;
                } else {
                    parent.rightChild = current.rightChild;
                }
            } // two children, so replace with in order successor.
            else {
                NodeTree Successor = getSuccessor(current);
                if (current == Root) {
                    Root = Successor;
                } else if (isLeftChild) {
                    parent.leftChild = Successor;
                } else {
                    parent.rightChild = Successor;
                }
                Successor.leftChild = current.leftChild;
            }
        }
    }

    public void updateNumber(String number) {
        NodeTree Temp = Root;
        while (Temp != null && Temp.myContact.phoneNumber.compareTo(number) != 0) {
            if (Temp.myContact.phoneNumber.compareTo(number) > 0) {
                Temp = Temp.rightChild;
            } else {
                Temp = Temp.leftChild;
            }
        }
        if (Temp == null) {
            System.out.println(" Number not in phone directory. ");
        } else {
            System.out.println(" Update your phone number  ");
            Scanner scanner = new Scanner(System.in);
            String updatedNumber = scanner.nextLine();
            Temp.myContact.phoneNumber = updatedNumber;
            Temp.myContact.display();
        }
    }

    public void find(String number) {
        NodeTree Temp = Root;
        while (Temp != null && Temp.myContact.phoneNumber.compareTo(number) != 0) {
            if (Temp.myContact.phoneNumber.compareTo(number) > 0) {
                Temp = Temp.rightChild;
            } else {
                Temp = Temp.leftChild;
            }
        }
        if (Temp == null) {
            System.out.println(" Number not in phone directory. ");
        } else {
            System.out.println(" Contact: ");
            Temp.myContact.display();
        }
    }

}
