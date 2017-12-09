package ua.sumdu.j2se.artem;

public class Main {

    public static void main(String[] args) {
	Task t1 = new Task("Task1",1);
	Task t2 = new Task("Task2",2);
	Task t3 = new Task("Task3",3);
	Task t4 = new Task("Task4",4);
	Task t5 = new Task("Task5",1,10,2);
    Task t6 = new Task("Task6",1,100,1);

    LinkedTaskList l = new LinkedTaskList();
    ArrayTaskList a = new ArrayTaskList();
    l.add(t1);
    l.add(t2);
    l.add(t3);
    l.add(t4);
    l.add(t5);
    l.add(t6);

    a.add(t1);
    a.add(t2);
    a.add(t3);
    a.add(t4);
    a.add(t5);
    a.add(t6);
        a.incoming(0,100);
        l.incoming(0,100);







    }
}
