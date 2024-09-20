package se.edu.streamdemo;

import se.edu.streamdemo.data.Datamanager;
import se.edu.streamdemo.task.Deadline;
import se.edu.streamdemo.task.Task;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Task manager (using streams)");
        Datamanager dataManager = new Datamanager("./data/data.txt");
        ArrayList<Task> tasksData = dataManager.loadData();
        // C:\Users\glend\OneDrive\Documents\Tasks\data
        // dont use absolute path as it may not exist when using application on a different computer
        System.out.println("Printing all data ...");
        printAllData(tasksData);
        printDataWithStream(tasksData);

        System.out.println("Printing deadlines ...");
        printDeadlines(tasksData);
        printDataWithStream(tasksData);

        System.out.println("Total number of deadlines (iterating): " + countDeadlines(tasksData));
        System.out.println("Total number of deadlines (stream): " + countDeadlinesWithStream(tasksData));

    }

    private static int countDeadlines(ArrayList<Task> tasksData) {

        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static void printAllData(ArrayList<Task> tasksData) {
        System.out.println("Printing data with iteration");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataWithStream(ArrayList<Task> tasks) {
        System.out.println("Printing data with stream");
        tasks.stream()                         //creating the stream
                .forEach(System.out::println); //terminal operator
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        System.out.println("Printing deadlines with iteration");
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesUsingStream(ArrayList<Task> tasks) {
        System.out.println("Printing deadlines with stream");
        tasks.stream()
                .filter(t -> t instanceof Deadline)
                .forEach(System.out::println);
    }

    public static int countDeadlinesWithStream(ArrayList<Task> tasks) {
        int count = (int) tasks.stream()
                .filter((t) -> t instanceof Deadline)
                .count(); //terminal operation; aggregate function

        return count;
    }
}
