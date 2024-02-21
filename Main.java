import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private int rollNumber;
    private double[] subjectMarks;

    public Student(String name, int rollNumber, double[] subjectMarks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.subjectMarks = subjectMarks;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public double[] getSubjectMarks() {
        return subjectMarks;
    }
}

class GradeManagementSystem {
    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(String name, int rollNumber, double[] subjectMarks) {
        Student student = new Student(name, rollNumber, subjectMarks);
        students.add(student);
        System.out.println("Student added successfully!");
    }

    public void updateStudent(int rollNumber, double[] subjectMarks) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                student = new Student(student.getName(), rollNumber, subjectMarks);
                System.out.println("Student information updated successfully!");
                return;
            }
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }

    public void deleteStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                students.remove(student);
                System.out.println("Student with Roll Number " + rollNumber + " deleted successfully!");
                return;
            }
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }

    public void displayStudentDetails() {
        for (Student student : students) {
            System.out.println("Name: " + student.getName());
            System.out.println("Roll Number: " + student.getRollNumber());
            System.out.println("Subject Marks: " + java.util.Arrays.toString(student.getSubjectMarks()));
            System.out.println("Overall Percentage: " + calculatePercentage(student.getSubjectMarks()) + "%");
            System.out.println("Grade: " + calculateGrade(calculatePercentage(student.getSubjectMarks())));
            System.out.println("--------------");
        }
    }

    private double calculatePercentage(double[] subjectMarks) {
        double totalMarks = 0;
        for (double marks : subjectMarks) {
            totalMarks += marks;
        }
        return (totalMarks / subjectMarks.length);
    }

    private char calculateGrade(double percentage) {
        if (percentage >= 90) return 'A';
        else if (percentage >= 80) return 'B';
        else if (percentage >= 70) return 'C';
        else if (percentage >= 60) return 'D';
        else return 'F';
    }
}

public class Main {
    public static void main(String[] args) {
        GradeManagementSystem gradeManagementSystem = new GradeManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Student\n2. Update Student\n3. Delete Student\n4. Display Students\n5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.next();
                    System.out.print("Enter roll number: ");
                    int rollNumber = scanner.nextInt();
                    System.out.print("Enter number of subjects: ");
                    int numSubjects = scanner.nextInt();
                    double[] subjectMarks = new double[numSubjects];
                    for (int i = 0; i < numSubjects; i++) {
                        System.out.print("Enter marks for Subject " + (i + 1) + ": ");
                        subjectMarks[i] = scanner.nextDouble();
                    }
                    gradeManagementSystem.addStudent(name, rollNumber, subjectMarks);
                    break;
                case 2:
                    System.out.print("Enter roll number to update: ");
                    int rollToUpdate = scanner.nextInt();
                    System.out.print("Enter number of subjects: ");
                    int numSubjectsToUpdate = scanner.nextInt();
                    double[] updatedSubjectMarks = new double[numSubjectsToUpdate];
                    for (int i = 0; i < numSubjectsToUpdate; i++) {
                        System.out.print("Enter updated marks for Subject " + (i + 1) + ": ");
                        updatedSubjectMarks[i] = scanner.nextDouble();
                    }
                    gradeManagementSystem.updateStudent(rollToUpdate, updatedSubjectMarks);
                    break;
                case 3:
                    System.out.print("Enter roll number to delete: ");
                    int rollToDelete = scanner.nextInt();
                    gradeManagementSystem.deleteStudent(rollToDelete);
                    break;
                case 4:
                    gradeManagementSystem.displayStudentDetails();
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }
}
