package ua.sumdu.j2se.artem;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Task t1 = new Task("Task1", new Date(1));
        Task t2 = new Task("Task2", new Date(2));
        Task t3 = new Task("Task3", new Date(3));
        Task t4 = new Task("Task4", new Date(5));
        Task t5 = new Task("Task5", new Date(4), new Date(10), 2);
        Task t6 = new Task("Task6", new Date(7), new Date(100), 1);

        LinkedTaskList l = new LinkedTaskList();
        ArrayTaskList a = new ArrayTaskList();
        l.add(t1);
        l.add(t2);
        l.add(t3);
        l.add(t4);
        l.add(t5);
        l.add(t6);
    //    System.out.println(l.toString());
    //    System.out.println("************");

        a.add(t1);
        a.add(t2);
        a.add(t3);
        a.add(t4);
        a.add(t5);
        a.add(t6);
 //       System.out.println(a.toString());
//        a.incoming(0, 100);
//        l.incoming(0, 100);
//        Iterator<Task> iter = a.iterator();
//        System.out.println("-----------------------------------");
//        System.out.println(iter.next());
//        iter.remove();
////        while(iter.hasNext()){
////            System.out.println(iter.next());
////        }
////        System.out.println("-----------------------------------");
//
//        System.out.println("-----------------------------------");
//        while (iter.hasNext()) {
//            System.out.println(iter.next().toString());
//        }
//        System.out.println("-----------------------------------");


    }
}
