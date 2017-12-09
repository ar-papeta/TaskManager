package ua.sumdu.j2se.artem;

import java.util.*;


public abstract class TaskList implements Cloneable, Iterable<Task> {

    protected Task[] list = new Task[10];
    public static int EXTRA_SIZE = 5;//x0.75
    protected int size;

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public int size() {
        return size;
    }

    public abstract Task getTask(int index);

    public TaskList incoming(int from, int to) {
        TaskList incomingList;
        if ((this instanceof ArrayTaskList)) {
            incomingList = new ArrayTaskList();
        } else {
            incomingList = new LinkedTaskList();
        }
        for (int i = 0; i < size(); i++) {
            if (getTask(i).isActive()) {
                if (getTask(i).nextTimeAfter(from) > from && getTask(i).nextTimeAfter(from) <= to)
                    incomingList.add(getTask(i));
            }
        }
        return incomingList;
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            int count = 0;

            @Override
            public boolean hasNext() {
                return size() > count && getTask(count) != null;
            }

            @Override
            public Task next() {
                return getTask(count++);
            }

            @Override
            public void remove() {
                if (count == 0) {
                    throw new IllegalStateException("Use remove() before next()!");
                }
                TaskList.this.remove(getTask(--count));
                //count--;

            }
        };
    }

}
