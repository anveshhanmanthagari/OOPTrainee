package Introduction;

import java.util.Scanner;

public class StudentIntroduction {

  /**
   * The main entry point of the application.
   *
   * <p>This method interacts with the user through the console to collect student information such
   * as name, ID, enrollment date, major, completed courses, and current class. After gathering the
   * input, it formats and displays the student details using the {@code printDetails} method.
   *
   * <p>The method demonstrates basic input handling, string processing, and console output in a
   * structured Java program.
   *
   * @param args command-line arguments (not used in this program)
   */
  public static void main(String[] args) {
    // Create a Scanner object to read input from the console
    Scanner sc = new Scanner(System.in);
    printDetails("Please enter Student Name:");
    String name = sc.nextLine();
    printDetails("Please enter Student ID:");
    String id = sc.nextLine();
    printDetails("Please enter Date Enrolled (e.g., January 5,2026): ");
    String dateEnrolled = sc.nextLine();
    printDetails("Enter Major:");
    String major = sc.nextLine();
    printDetails("Enter Completed Courses (comma separated):");
    String completedCourses = sc.nextLine();
    printDetails("Current Class : ");
    String currentClass = sc.nextLine();
    printDetails("Student Details :");
    printDetails("Name: " + name.toUpperCase());
    printDetails("ID: " + id);
    printDetails("Date Enrolled: " + dateEnrolled);
    printDetails("Major: " + major);
    printDetails("Completed Courses: " + completedCourses);
    printDetails("Current Class: " + currentClass);
  }
  /**
   * Prints the provided message to the console.
   *
   * <p>This method is used to display informational or debugging messages during program execution.
   * It helps improve code readability by centralizing output logic in a reusable method.
   *
   * @param message the text message to be printed to the console
   */
  public static void printDetails(String message) {
    System.out.println(message);
  }
}
