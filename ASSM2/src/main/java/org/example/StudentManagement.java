package org.example;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Student {
    private String id;
    private String name;
    private int age;

    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class StudentManagement {
    private static List<Student> studentList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. View All Students");
            System.out.println("6. Sort and Measure Time (QuickSort and BubbleSort)");
            System.out.println("7. Add Multiple Students Automatically");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> editStudent();
                case 3 -> deleteStudent();
                case 4 -> searchStudent();
                case 5 -> viewAllStudents();
                case 6 -> sortAndMeasureTime();
                case 7 -> addMultipleStudentsAutomatically();
                case 8 -> {
                    System.out.println("Exiting program...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Student Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        studentList.add(new Student(id, name, age));
        System.out.println("Student added successfully!");
    }

    private static void editStudent() {
        System.out.print("Enter Student ID to edit: ");
        String id = scanner.nextLine();

        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                System.out.print("Enter new name (leave blank to keep current): ");
                String newName = scanner.nextLine();
                if (!newName.isEmpty()) {
                    student.setName(newName);
                }

                System.out.print("Enter new age (leave blank to keep current): ");
                String newAge = scanner.nextLine();
                if (!newAge.isEmpty()) {
                    student.setAge(Integer.parseInt(newAge));
                }

                System.out.println("Student updated successfully!");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    private static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        String id = scanner.nextLine();

        studentList.removeIf(student -> student.getId().equals(id));

        System.out.println("Student deleted successfully!");
    }

    private static void searchStudent() {
        System.out.print("Enter Student ID to search: ");
        String id = scanner.nextLine();

        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                System.out.println("Found: " + student);
                return;
            }
        }

        System.out.println("Student not found.");
    }

    private static void viewAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students available.");
        } else {
            System.out.println("\n--- List of Students ---");
            for (Student student : studentList) {
                System.out.println(student);
            }
        }
    }

    private static void sortAndMeasureTime() {
        if (studentList.isEmpty()) {
            System.out.println("No students to sort.");
            return;
        }

        // Shuffle the list to avoid best or worst case scenarios
        List<Student> shuffledList = new ArrayList<>(studentList);
        Collections.shuffle(shuffledList);

        // Measure time for QuickSort
        List<Student> quickSortList = new ArrayList<>(shuffledList);
        long quickStart = System.nanoTime();
        quickSort(quickSortList, 0, quickSortList.size() - 1);
        long quickEnd = System.nanoTime();

        // Measure time for BubbleSort
        List<Student> bubbleSortList = new ArrayList<>(shuffledList);
        long bubbleStart = System.nanoTime();
        bubbleSort(bubbleSortList);
        long bubbleEnd = System.nanoTime();

        System.out.println("\n--- Sorting Results ---");
        System.out.println("QuickSort Time: " + (quickEnd - quickStart) + " nanoseconds");
        System.out.println("BubbleSort Time: " + (bubbleEnd - bubbleStart) + " nanoseconds");
    }

    private static void quickSort(List<Student> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }

    private static int partition(List<Student> list, int low, int high) {
        Student pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).getId().compareTo(pivot.getId()) < 0) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, high);
        return i + 1;
    }

    private static void bubbleSort(List<Student> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).getId().compareTo(list.get(j + 1).getId()) > 0) {
                    Collections.swap(list, j, j + 1);
                }
            }
        }
    }

    private static void addMultipleStudentsAutomatically() {
        System.out.print("Enter the number of students to add automatically: ");
        int count = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= count; i++) {
            String id = "S" + String.format("%03d", i);
            String name = "Student" + i;
            int age = 18 + (i % 5); // Randomized age between 18 and 22
            studentList.add(new Student(id, name, age));
        }

        System.out.println(count + " students added automatically!");
    }
}
