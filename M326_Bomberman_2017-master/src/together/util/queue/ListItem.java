package together.util.queue;

/**
 * List-item class which is used to create a linked-list which is used in the queue class
 *
 * @param <T> Type of data that is stored in the ListItem
 * @author Tobias Maier <tma109970@iet-gibb.ch>
 */
class ListItem<T> {
    ListItem<T> after;
    T value;

    /**
     * Create a ListItem that holds the given data
     * @param value of type T
     */
    ListItem(T value) {
        this.value = value;
    }

    /**
     * Recursively adds values to a linked list
     * @param value of type T that should be added to the list
     */
    void add(T value) {
        if (this.after == null) {
            this.after = new ListItem<>(value);
        } else {
            this.after.add(value);
        }
    }

}
