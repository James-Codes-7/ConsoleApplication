package schoolattendancealter.controler;

import java.util.regex.Pattern;

public class CheckList {
    private static CheckList checkList;
    public static CheckList getInstance() {
        if (checkList == null) checkList = new CheckList();
        return checkList;
    }
    public boolean dateCheck(String date) {
        return Pattern.matches("[0-2][0-9]{3}[/-][0-1][0-9][/-][0-3][0-9]", date);
    }
}
