package csm.cis255;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Course course = new Course("CIS 255", 10, 2);
        System.out.println("Welcome to course " + course.getName() + ". The maximum amount of students in this course roster is " + course.getMaximumStudentsOnRoster()+ ", and the maximum waitlist seats are " + course.getMaximumStudentsOnWaitlist() + ".");

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        while(!quit){
            System.out.println("To use the menu, select:\n" +
                    "\t1. To add a student, entering their name, ID, and paid tuition status.\n" +
                    "\t2. To drop a student, entering their ID.\n" +
                    "\t3. To view a text representation of the course.\n" +
                    "\t4. To quit the program.\n \n"
            );
            int menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu){
                case 1:
                    System.out.println("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter student ID: ");
                    String id = scanner.nextLine();
                    System.out.println("True/False, Enter the student tuition payment status:");
                    boolean tuitionPaid = scanner.nextBoolean();

                    Student newStudent = new Student(name, id, tuitionPaid);
                    course.addStudent(newStudent);
                    System.out.println("Student " + id + " added.\n");
                    break;

                case 2:
                    System.out.println("Enter student ID: ");
                    String dropID = scanner.nextLine();

                    Student dropStudent = new Student("", dropID, false);
                    course.dropStudent(dropStudent);
                    System.out.println("Student " + dropID + " dropped.\n");
                    break;

                case 3:
                    System.out.println(course.toString());
                    System.out.println("\n");
                    break;

                case 4:
                    quit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println("\n");
                    break;
            }
        }
    }
}
