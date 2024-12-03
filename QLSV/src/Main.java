import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private String studentId;
    private double marks;
    private String rank;

    public Student(String name, String studentId, double marks) {
        this.name = name;
        this.studentId = studentId;
        this.marks = marks;
        this.rank = assignRank();
    }

    private String assignRank() {
        if (marks < 5.0) {
            return "Fail";
        } else if (marks < 6.5) {
            return "Medium";
        } else if (marks < 7.5) {
            return "Good";
        } else if (marks < 9.0) {
            return "Very Good";
        } else {
            return "Excellent";
        }
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public String getRank() {
        return rank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(double marks) {
        this.marks = marks;
        this.rank = assignRank();
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Student ID: " + studentId + ", Marks: " + marks + ", Rank: " + rank;
    }
}

class StudentManagement {
    private ArrayList<Student> students;

    public StudentManagement() {
        students = new ArrayList<>();
    }

    public boolean isNameValid(String name) {
        return name.matches("[a-zA-Z ]+");
    }

    public void addStudent(String name, String studentId, double marks) {
        if (!isNameValid(name)) {
            System.out.println("Invalid name! Name should only contain letters and spaces.");
            return;
        }

        Student student = new Student(name, studentId, marks);
        students.add(student);
        System.out.println("Student added successfully.");
    }

    public void editStudent(String studentId, String newName, Double newMarks) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                if (newName != null) {
                    if (isNameValid(newName)) {
                        student.setName(newName);
                    } else {
                        System.out.println("Invalid name! Name should only contain letters and spaces.");
                        return;
                    }
                }
                if (newMarks != null) {
                    student.setMarks(newMarks);
                }
                System.out.println("Student information updated.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void deleteStudent(String studentId) {
        students.removeIf(student -> student.getStudentId().equals(studentId));
        System.out.println("Student removed if found.");
    }

    // bubble sort
    public void sortStudentsByMarks() {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students.get(j).getMarks() < students.get(j + 1).getMarks()) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }

    public Student searchStudent(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public void displayStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        StudentManagement management = new StudentManagement();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. Sort Students by Marks");
            System.out.println("6. Display All Students");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline left after nextInt()

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter student marks: ");
                    double marks = scanner.nextDouble();
                    management.addStudent(name, id, marks);
                    break;
                case 2:
                    System.out.print("Enter student ID to edit: ");
                    String editId = scanner.nextLine();
                    System.out.print("Enter new name (or press Enter to skip): ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new marks (or press Enter to skip): ");
                    Double newMarks = scanner.hasNextDouble() ? scanner.nextDouble() : null;
                    management.editStudent(editId, newName, newMarks);
                    break;
                case 3:
                    System.out.print("Enter student ID to delete: ");
                    String deleteId = scanner.nextLine();
                    management.deleteStudent(deleteId);
                    break;
                case 4:
                    System.out.print("Enter student ID to search: ");
                    String searchId = scanner.nextLine();
                    Student foundStudent = management.searchStudent(searchId);
                    if (foundStudent != null) {
                        System.out.println(foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    management.sortStudentsByMarks();
                    System.out.println("Students sorted by marks:");
                    management.displayStudents(); // Display sorted students
                    break;
                case 6:
                    management.displayStudents();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
