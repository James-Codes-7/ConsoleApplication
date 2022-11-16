package bankingalter.model;

import bankingalter.controller.AccountTransactionControl;
import bankingalter.controller.UserPasswordChangeControl;

import java.util.HashMap;

public class UserLogin {

    private static UserLogin userLogin;
    public static UserLogin getInstance() {
        if (userLogin == null) userLogin = new UserLogin();
        return userLogin;
    }
    private HashMap<Long, String> loginCheck = new HashMap<>();
    private HashMap<Long, String> passwordChange = new HashMap<>();
    public HashMap<Long, String> getLoginCheck() {
        return loginCheck;
    }
    public void setLoginCheck(HashMap<Long, String> loginCheck) {
        this.loginCheck.putAll(loginCheck);
    }
    public void setLoginCheck(long accountNo, String password) {
        this.loginCheck.put(accountNo, password);
    }
    public HashMap<Long, String> getPasswordChange() {
        return passwordChange;
    }
    public void setPasswordChange(HashMap<Long, String> passwordChange) {
        this.passwordChange.putAll(passwordChange);
    }
}
