package ua.sumdu.j2se.artem;

import java.util.*;


public abstract class TaskList implements Cloneable, Iterable<Task> {

    protected Task[] list = new Task[10];
    protected int size;

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public int size() {
        return size;
    }

    public abstract Task getTask(int index);

    public TaskList incoming(Date from, Date to) {
        TaskList incomingList;
        if ((this instanceof ArrayTaskList)) {
            incomingList = new ArrayTaskList();
        } else {
            incomingList = new LinkedTaskList();
        }
        for (int i = 0; i < size(); i++) {
            if (getTask(i).isActive()) {
                if (getTask(i).nextTimeAfter(from).after(from) && (getTask(i).nextTimeAfter(from) == to || getTask(i).nextTimeAfter(from).before(to)))
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
                //count++;

            }
        };
    }


}
