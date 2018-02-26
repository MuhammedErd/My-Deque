/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dshw1;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class MyDeque<Item> implements Iterable<Item> {
    private int n;
    private Node front;
    private Node rear;
    
    public MyDeque(){
        
    }
    private class Node{
        Item item;
        Node next;
        Node previous;
    }
    public boolean isEmpty(){
        return front==null;
    }
    public int size(){
        return n;
    }
    private void throwIfEmpty(){
        if(front==null){
            throw new NoSuchElementException();
        }
    }
    private void throwIfNull(Item item){
        if(item==null){
            throw new NullPointerException();
        }
    }
    public void push(Item item){
        throwIfNull(item);
        Node newfront = new Node();
        newfront.item=item;
        if(front != null){
            newfront.next=front;
            front.previous=newfront;
        }
        front=newfront;
        if(rear == null){
            rear=front;
        }
        n++;
    }
    public Item pop(){
        throwIfEmpty();
        Node oldfront=front;
        front=front.next;
        if(front==null){
            rear=null;
        }
        else{
        front.previous=null;
        
        }
        n--;
        return oldfront.item;
    }
    public void inject(Item item){
        throwIfNull(item);
        Node newrear = new Node();
        newrear.item=item;
        if(rear != null){
            newrear.previous=rear;
            rear.next=newrear;
        }
        rear=newrear;
        if(front==null){
            front=rear;
        }
        n++;        
    }
    public Item eject(){
        throwIfEmpty();
        Node oldrear =rear;
        rear=rear.previous;
        if(rear==null){
            front=null;
        }
        else
            rear.next=null;
        n--;
        return oldrear.item;
    }
    
    @Override
    public Iterator<Item> iterator(){
        return new ItemsIterator();
    }
    private class ItemsIterator implements Iterator<Item>{
        private Node current;
        public ItemsIterator(){
            current = front;
        }
        @Override
        public boolean hasNext(){
            return current !=null;
        }
        @Override
        public Item next(){
            if(current == null){
                throw new NoSuchElementException();
            }
            Item item =current.item;
            current = current.next;
            return item;
        }
        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }
    
    
}
