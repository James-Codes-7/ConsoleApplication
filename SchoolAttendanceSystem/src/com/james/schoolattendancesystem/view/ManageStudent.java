package schoolattendancealter.View;

import schoolattendancealter.controler.StudentControl;
import schoolattendancealter.model.Student;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ManageStudent {
    private static ManageStudent manageStudent;
    private StudentControl studentControl;
    private Scanner scanner = new Scanner(System.in);

    public static ManageStudent getInstance() {
        if (manageStudent == null) manageStudent = new ManageStudent();
        return manageStudent;
    }

    public void studentLogin() {
        boolean bool = true;
        byte option = 0;
        int studentId = 0;
        String password = "";
        label:
        while (bool) {
            try {
                System.out.println("Enter the student id");
                studentId = scanner.nextInt();
                while (studentControl.verification().containsKey(studentId)) {
                    System.out.println("Enter the password");
                    password = scanner.next();
                    if (studentControl.verification().get(studentId).equals(password)) {
                        System.out.println("Student Login successfully");
                        break label;
                    } else System.out.println("Enter the corect password");

                }
                System.out.println("Enter the correct id");
            } catch (InputMismatchException e) {
                System.out.println("Enter the number Integer format");
                scanner.next();
            }
        }
        bool = true;
        while (bool) {
            try {
                System.out.println();
                System.out.println("View Your Profile             Press 1");
                System.out.println("View attendance History       Press 2");
                System.out.println("Leave Apply                   Press 3");
                System.out.println("Exit                          Press 4");
                option = scanner.nextByte();
                if (option == 4) return;
                switch (option) {
                    case 1:
                        viewProfile(studentId);
                        break;
                    case 2:
                        viewAttendanceHistory(studentId);
                        break;
                    case 3:
                        leaveApply(studentId);
                        break;
                }
                System.out.println();
            } catch (InputMismatchException e) {
                System.out.println("Wrong input format");
                scanner.next();
            }
        }
    }

    public void setAttendance(String date) {
        int size = studentControl.getStudentId().size();
        String attendanceStatus[] = new String[size];
        byte option = 0;
        List<Integer> studentId = studentControl.getStudentId();
        for (int i = 0; i < size; i++) {
            try {
                System.out.println("The ID Number:" + studentId.get(i) + " if present Press 1 else 0");
                option = scanner.nextByte();
                if (option == 1) {
                    attendanceStatus[i] = "Present";
                } else attendanceStatus[i] = "Absent";
                studentControl.updateData("insert into schoolattendance(enrollid,atdate,Astatus)"
                        + "  values(" + studentId.get(i) + ",'" + date + "','" + attendanceStatus[i] + "')");
            } catch (InputMismatchException e) {
                System.out.println("Enter the input Byte Format");
                i--;
                scanner.next();
            }
        }
        System.out.println("Success Fully Completed");
    }

    private void leaveApply(int studentId) {
        System.out.println("Enter how many days you want");
        int totalLeave = scanner.nextInt();
        studentControl.leaveApplyControl(studentId, totalLeave);
    }

    public void viewAttendanceHistory(int studentId) {
        studentControl.studentAttendanceHistory(studentId);
    }

    public void alert(String message) {
        System.out.println(message);
    }

    private void viewProfile(int studentId) {
        List<Student> studentList = studentControl.getStudentList();
        for (Student student : studentList) {
            if (student.getStudentId() == studentId) {
                System.out.println("Student Id:   " + student.getStudentId());
                System.out.println("Student Name: " + student.getStudentName());
                System.out.println("Student Dob:  " + student.getDateOfBirth() + "\n");
                return;
            }
        }
        System.out.println("Enter the correct student Id");
    }

    public ManageStudent() {
        studentControl = new StudentControl(this);
    }
}
