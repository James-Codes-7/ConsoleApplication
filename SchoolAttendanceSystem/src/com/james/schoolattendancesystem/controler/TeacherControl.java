package schoolattendancealter.controler;

import schoolattendancealter.View.ManageTeacher;
import schoolattendancealter.repository.SchoolAttendanceDatabase;
import schoolattendancealter.repository.Teacher;

import java.util.HashMap;
import java.util.List;

public class TeacherControl {

    private ManageTeacher manageTeacher;

    public SchoolAttendanceDatabase schoolAttendanceDatabase;

    public TeacherControl(ManageTeacher manageTeacher) {
        this.manageTeacher = manageTeacher;
        schoolAttendanceDatabase=SchoolAttendanceDatabase.getInstance();
    }
    public HashMap<Integer,String> getTeacherId()
    {
      return   schoolAttendanceDatabase.getTecherLogin();
    }
    public List<Teacher> getTeacherList()
    {
        return schoolAttendanceDatabase.getTeacherList();
    }
}
