package queues;

import java.util.LinkedList;

public class LinkedQueue<T> implements IQueue<T>{

    LinkedList<T> cuteLine = new LinkedList<T>();

    //returns the size of the linked list
    public int size(){
        return cuteLine.size();
    }

    //checks if the line is empty
    public boolean isEmpty(){
        if (cuteLine.size() == 0){
            return true;
        }
        else
            return false;
    }

    //gets the first item from the line
    public T front(){
        return (cuteLine.getFirst());
    }

    //adds a goat into the line
    public void enqueue(T addGoat){
        cuteLine.add(addGoat);
    }

    //takes out and returns the top value in line
    public T dequeue(){
        return cuteLine.pop();

    }

}
