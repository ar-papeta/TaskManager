package ua.sumdu.j2se.artem;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.AbstractList;


public class ArrayTaskList extends TaskList implements Iterable<Task> {
    public static final int EXTRA_SIZE = 5;//x0.75
    Task[] taskArray = new Task[size];

    @Override
    public void add(Task task) {
        if (task == null)
            throw new NullPointerException("Task can not be null.");
        else {
            if (size() >= taskArray.length) {
                taskArray = Arrays.copyOf(taskArray, taskArray.length + EXTRA_SIZE);
            }
            taskArray[size++] = task;
        }
    }

    @Override
    public boolean remove(Task task) {
        if (task == null)
            throw new NullPointerException("Task can not be null.");
        for (int i = 0; i < size(); i++) {
            if (task.equals(taskArray[i])) {
                while (i < size - 1) {
                    taskArray[i] = taskArray[++i];
                }
                taskArray[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public Task getTask(int index) {
        if ((index < 0) || (index >= size()))
            throw new ArrayIndexOutOfBoundsException("Index is out of the array size range.");
        else
            return taskArray[index];
    }

    @Override
    public ArrayTaskList clone()
            throws CloneNotSupportedException {
        ArrayTaskList result = (ArrayTaskList) super.clone();
        taskArray = ((Task[])taskArray.clone());

        for (int i = 0; i < size(); i++) {
            taskArray[i] = taskArray[i].clone();
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrayTaskList tasks = (ArrayTaskList) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(taskArray, tasks.taskArray);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(taskArray);
    }

    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "list=" + Arrays.toString(taskArray) +
                '}';
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            private int count;

            public boolean hasNext() {
                return size() > count && getTask(count) != null;
            }

            public Task next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Out of list");
                }

                Task result = getTask(count);
                count++;

                return result;
            }

            public void remove() {
                if (count == 0) {
                    throw new IllegalStateException("Use remove() before next()!");
                }

                for (int i = count - 1; i < size(); i++) {
                    taskArray[i] = taskArray[(i + 1)];
                }
                count--;
            }
        };
    }
}
