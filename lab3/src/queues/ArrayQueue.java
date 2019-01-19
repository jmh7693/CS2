package queues;

import java.util.ArrayList;

public class ArrayQueue<T> implements IQueue<T> {

    ArrayList<T> cuteLine = new ArrayList<T>();
    int startIdx = 0;
    int endIdx = cuteLine.size();

    //returns the size of the array list
    public int size(){
        return cuteLine.size();
    }

    //checks if the line is empty or not
    public boolean isEmpty(){
        if (cuteLine.size() == 0){
            return true;
        }
        else
            return false;
    }

    //returns the first item in the line
    public T front(){
        return (cuteLine.get(startIdx));
    }

    //adds a goat to the line and changes the end index variable
    public void enqueue(T addGoat){
        cuteLine.add(addGoat);
        endIdx = cuteLine.size();
    }

    //takes out the start index in line and returns it
    public T dequeue(){
        T x = cuteLine.get(startIdx);
        cuteLine.remove(startIdx);
        return x;

    }

}
