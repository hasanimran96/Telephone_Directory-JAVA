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
public interface HashTable {

    public abstract void insert(DoublyLinkedList d);

    public abstract void display();

    public abstract void update(String str);

    public abstract void delete(String str);

    public abstract void find(String str);

}
