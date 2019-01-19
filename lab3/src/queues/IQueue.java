package queues;

public interface IQueue<T> {

    public int size();

    public boolean isEmpty();

    public T front();

    public void enqueue(T addGoat);

    public T dequeue();

}
