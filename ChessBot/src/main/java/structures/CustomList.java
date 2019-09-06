package structures;

import java.util.Random;

/**
 * A simple list with some core functionality. Table is the array in which the
 * values are stored Length is the number of entries in the table AbsoluteSize
 * is the size of the array
 *
 * @author kevin
 * @param <Type> The Object type in the list
 */
public class CustomList<Type> {

    private Object[] table;
    private int length;
    private int absoluteSize;
    private int startingSize = 20;

    public CustomList() {
        this.length = 0;
        this.absoluteSize = startingSize;
        this.table = new Object[startingSize];
    }

    /**
     * Adds a new object to the table and increases the size of the table if it
     * is full
     *
     * @param object Object to be added
     */
    public void add(Type object) {
        if (length == absoluteSize) {
            lengthenTable();
        }
        table[length] = object;
        length++;
    }

    /**
     * Doubles the table length
     */
    public void lengthenTable() {
        absoluteSize = 2 * absoluteSize;
        Object[] temp = new Object[absoluteSize];
        for (int i = 0; i < length; i++) {
            temp[i] = table[i];
        }
        table = temp;
    }

    /**
     * Gets the item in the table by index.
     *
     * @throws IllegalArgumentException for out of bounds index
     * @param index ranging from 0 - (length - 1)
     * @return the object in index
     */
    public Type get(int index) throws IllegalArgumentException {
        if (index < 0 || index >= length) {
            throw new IllegalArgumentException("index " + index + " out of bounds for array length " + length);
        }
        return (Type) table[index];
    }

    /**
     * Remove the last item of the table and shorten the table if the length in
     * use is less than a quarter of the actual size
     */
    public void removeLast() {
        if (length != 0) {
            length--;
            table[length] = null;
        }
        if (absoluteSize / length >= 4) {
            shortenTable();
        }
    }

    /**
     * Shorten the table by half, but not under the starting value
     */
    public void shortenTable() {
        if (absoluteSize > startingSize) {
            absoluteSize = absoluteSize / 2;
            Object[] temp = new Object[absoluteSize];
            for (int i = 0; i < length; i++) {
                temp[i] = table[i];
            }
            table = temp;
        }
    }

    /**
     * The current length.
     *
     * @return The number of elements in the list
     */
    public int length() {
        return length;
    }

    /**
     * The current absolute size of the array. Used for testing
     *
     * @return The size of the array
     */
    public int getAbsoluteSize() {
        return absoluteSize;
    }
    /**
     * Tests for empty list
     * @return true for empty list, false otherwise
     */
    public boolean isEmpty() {
        return length == 0;
    }
    
    /**
     * Adds a list of the same type together with the desired list.
     * @param list list to be added
     */
    public void addAll(CustomList<Type> list) {
        for (int i = 0; i < list.length(); i++) {
            add(list.get(i));
        }
    }

    /**
     * Shuffles the list by swapping two random elements.
     * Does as many swaps as there are elements in the list
     */
    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int first = random.nextInt(length);
            int second = random.nextInt(length);
            Type temp = (Type) table[first];
            table[first] = table[second];
            table[second] = temp;
        }
    }

}
