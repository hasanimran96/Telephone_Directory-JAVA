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
public class HashTableNode {

    int number;
    HashTableNode[] next;
    Tree nextTree;

    HashTableNode() {

    }

    HashTableNode(int num) {
        number = num;
    }

}
