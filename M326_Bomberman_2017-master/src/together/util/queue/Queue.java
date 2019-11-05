package together.util.queue;

/**
 * Minimalistic Queue implementation
 *
 * @param <T> Generic to define what type of data is saved inside the Queue
 * @author Tobias Maier <tma109970@iet-gibb.ch>
 */
public class Queue<T> {
    private ListItem<T> start;
    private int size = 0;

    /**
     * Add a value to the end of the queue or set as first element if the queue is empty
     * @param value that is being added to the Queue
     */
    public void push(T value) {
        if (start == null) {
            this.start = new ListItem<>(value);
        } else {
            this.start.add(value);
        }
        this.size += 1;
    }

    /**
     * First element in the queue gets removed and is returned
     * @return First element in the queue
     */
    public T poll() {
        if (start == null) {
            return null;
        }
        T item = start.value;
        this.start = this.start.after;
        this.size -= 1;
        return item;
    }

    /**
     * Returns the size of the queue
     * @return Size of the queue
     */
    public int size() {
        return this.size;
    }
}
