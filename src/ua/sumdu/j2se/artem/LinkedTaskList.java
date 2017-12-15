package ua.sumdu.j2se.artem;


import java.util.Iterator;

/**
 * Class which work with list of task objects
 *
 * @author Papeta Artem
 */
public class LinkedTaskList extends TaskList implements Cloneable, Iterable<Task> {
    private Element tail = null;
    private Element head = null;

    /**
     * Add task to list
     *
     * @param task Task to add
     */
    @Override
    public void add(Task task) {
        Element element = new Element(task, null);
        if (task == null) {
            throw new NullPointerException("Task can not be null");
        } else if (head == null) {
            head = element;
            tail = head;
            size++;
        } else {
            tail.next = element;
            tail = tail.next;
            size++;
        }
    }

    /**
     * Remove task from list
     *
     * @param task Task to remove
     */
    public boolean remove(Task task) {
        if (head == null)                     //список пустой
            return false;
        if (head.equals(tail)) {                  //в списке 1 елемент
            if (head.value.equals(task)) {
                head = null;
                tail = null;
                size--;
                return true;
            }
        }
        if (head.value.equals(task)) {
            head = head.next;
            size--;
            return true;
        }
        Element t = head;
        while (t.next != null) {
            if (t.next.value.equals(task)) {
                if (tail.equals(t.next))      //если он последний
                {
                    tail = t;           //то переключаем указатель на последний элемент на текущий
                }
                t.next = t.next.next;
                size--;                     //найденный элемент выкидываем
                return true;                 //и выходим
            }
            t = t.next;                //иначе ищем дальше
        }
        return false;
    }

    /**
     * @param index Index of element in list
     * @return Task which have index "index"
     */
    public Task getTask(int index) {
        if ((index < 0) || (index >= size())) {
            throw new IllegalArgumentException(" mast be: 0 < index <=size");
        } else {
            Element current = this.head;
            for (int i = 0; i < index; i++) {
                if (current.next != null) {
                    current = current.next;
                }
            }
            return current.value;
        }
    }

    /**
     * @return Size of list
     */
    public int size() {
        return size;
    }


    /**
     * Class which helps in implementing of linked list
     */
    private class Element {
        Element next;
        Task value;

        public Element(Task value, Element next) {
            if (value == null)
                throw new NullPointerException("Task(value) can not be null.");
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        Iterator<Task> it = iterator();
        if (!it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (; ; ) {
            Task e = it.next();
            sb.append(e.equals(this) ? "(this Collection)" : e);
            if (!it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }

    @Override
    public LinkedTaskList clone(){
        //       LinkedTaskList out = new LinkedTaskList();
        //       if(size() > 0)
        //           for (Task task : this)
        //               out.add(task);

        try {
            return (LinkedTaskList)super.clone();
        }
        catch( CloneNotSupportedException ex ) {
            throw new InternalError();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        for (int i = 0; i < size(); i++)
            result = prime * result + getTask(i).hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {

        boolean eql = false;
        if (o == null || getClass() != o.getClass()) return false;

        LinkedTaskList list = (LinkedTaskList) o;
        if (size() != list.size())
            return false;

        for (int i = 0; i < size(); i++) {
            if (getTask(i).equals(list.getTask(i)))
                eql = true;
        }
        return eql;
    }

}
