package ua.sumdu.j2se.artem;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayTaskList extends TaskList {
    public static final int EXTRA_SIZE = 5;//x0.75
    Task[] taskArray = new Task[size];

    @Override
    public void add(Task task){
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
    public boolean remove(Task task){
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


    public ArrayTaskListIterator iterator() { return new ArrayTaskListIterator(); }

    public class ArrayTaskListIterator implements Iterator<Task> {
        private int count;

        public ArrayTaskListIterator() {}

        public Task next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Out of list");
            }

            Task result = getTask(count);
            count ++;

            return result;
        }

        public void remove() {
            if (count == 0) {
                throw new IllegalStateException("Use remove() before next()!");
            }

            for (int i = count - 1; i < size(); i++) {
                taskArray[i] = taskArray[(i + 1)];
            }
            count --;
        }

        public boolean hasNext()
        {
            if (size() > count) {
                return true;
            }
            return false;
        }
    }
}