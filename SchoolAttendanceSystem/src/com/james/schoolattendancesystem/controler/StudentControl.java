package schoolattendancealter.controler;

import schoolattendancealter.View.ManageStudent;
import schoolattendancealter.View.ManageTeacher;
import schoolattendancealter.connect.JDBCOperations;
import schoolattendancealter.repository.LeaveApply;
import schoolattendancealter.repository.SchoolAttendanceDatabase;
import schoolattendancealter.repository.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;

public class StudentControl {

    private ManageStudent manageStudent;
    private ManageTeacher manageTeacher;
    private SchoolAttendanceDatabase schoolAttendanceDatabase;
    private final int STUDENTIDSTART = 10101;
    private JDBCOperations jdbcOperations;

    public StudentControl(ManageStudent manageStudent) {
        this.manageStudent = manageStudent;
        schoolAttendanceDatabase = SchoolAttendanceDatabase.getInstance();
        jdbcOperations = JDBCOperations.getInstance();
    }

    public StudentControl(ManageTeacher manageTeacher) {
        this.manageTeacher=manageTeacher;
        schoolAttendanceDatabase = SchoolAttendanceDatabase.getInstance();
        jdbcOperations = JDBCOperations.getInstance();
    }

    public void updateData(String data) {
        jdbcOperations.updateQuery(data);
    }

    public List<Integer> getStudentId() {
        return schoolAttendanceDatabase.getStudentId();
    }

    public HashMap<Integer, String> verification() {
        return schoolAttendanceDatabase.getStudentLogin();
    }

    public void studentAttendanceHistory(int studentId) {
        try {
            System.out.println();
            ResultSet result = jdbcOperations.retriveQuery("select * from schoolattendance where enrollid=" + studentId);
            while (result.next()) {
                manageStudent.alert("StudentId:" + result.getInt(1)
                        + " Date:" + result.getString(2) + " Status:" + result.getString(3) + " Total Leaves:" +
                        "" + result.getInt(4)
                        + " leave Permit:" + result.getInt(5) + "\n");
            }
        } catch (SQLException e) {
            System.out.println("Cannot retrieve");
        }
    }
     public void viewAttendanceUseDate(String date)
     {
         try {
             ResultSet result = jdbcOperations.retriveQuery("select * from schoolattendance where atdate='" + date + "'");
             manageTeacher.alert("\nFull Attendance Sheet\n");
             while (result.next()) {
                 manageTeacher.alert("StudentId:" + result.getInt(1)
                         + " Date:" + result.getString(2) + " Status:" + result.getString(3) +
                         " Total Leaves:" + result.getInt(4)
                         + "  leave Permit:" + result.getInt(5) + "\n");
             }
         } catch (Exception e) {
             manageTeacher.alert("Cannot retireve");
         }
     }
    public void leaveApplyControl(int studentId, int totalLeave) {
        while (true) {
            try {

                LeaveApply checkLeave = schoolAttendanceDatabase.getLeaveApplyList().get(studentId - STUDENTIDSTART);

                if (checkLeave.getStudentid() != studentId) {
                    manageStudent.alert("Enter the corect");
                    return;
                }
                if ((checkLeave.getTotalLeave() + totalLeave) <= checkLeave.getRemainingLeave()) {

                    jdbcOperations.updateQuery("update schoolattendance set" +
                            " totalleaves=" + (checkLeave.getTotalLeave() + totalLeave) + " where enrollid=" + studentId);
                    checkLeave.setTotalLeave(checkLeave.getTotalLeave() + totalLeave);
                    manageStudent.alert("Permission granted\n");
                } else {
                    manageStudent.alert("Already your leave compleated");
                    break;
                }
                break;
            } catch (InputMismatchException e) {
                manageStudent.alert("Input mismatch");
                leaveApplyControl(studentId, totalLeave);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                leaveApplyControl(studentId, totalLeave);
            }
        }
    }
    public void viewAttendanceUseDateAndId(String date,int studentId)
    {
        try {
            ResultSet result = jdbcOperations.retriveQuery("select * from schoolattendance where atdate='" + date + "'");
            manageTeacher.alert("\nFull Attendance Sheet\n");
            while (result.next()) {
                if (result.getInt(1) == studentId)
                    manageTeacher.alert("StudentId:" + result.getInt(1)
                            + " Date:" + result.getString(2) + " Status:" + result.getString(3) +
                            " Total Leaves:" + result.getInt(4)
                            + "  leave Permit:" + result.getInt(5) + "\n");
            }
        } catch (Exception e) {
            System.out.println("Cannot retireve");
        }
    }
    public void viewFullAttendance()
    {
        try {
            ResultSet result = jdbcOperations.retriveQuery("select * from schoolattendance");
            while (result.next()) {
                System.out.println("StudentId:" + result.getInt(1)
                        + " Date:" + result.getString(2) + " Status:" + result.getString(3) + " " +
                        "Total Leaves:" + result.getInt(4)
                        + "  leave Permit:" + result.getInt(5) + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Student> getStudentList() {
        return schoolAttendanceDatabase.getStudentList();
    }
}
