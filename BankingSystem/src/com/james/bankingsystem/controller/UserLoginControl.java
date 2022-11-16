package bankingalter.controller;

import bankingalter.model.UserLogin;

import java.util.HashMap;
import java.util.Scanner;

public class UserLoginControl {

    private static UserLoginControl userLoginControl;
    private UserPasswordChangeControl userPasswordChangeControl;
    private AccountTransactionControl accountTransactionControl;
    private UserLogin userLogin;
    private Scanner scanner = new Scanner(System.in);

    public void customerLogin() {
        long accountNo = 1;
        String password = "";
        boolean bool = true;
        int limit = 3;
        byte passwordwrongcheck = 0;
        System.out.println("Enter the Account number");
        f1:
        while (bool) {
            accountNo = scanner.nextLong();
            while (userLogin.getLoginCheck().containsKey(accountNo)) {
                System.out.println("Enter the correct password here");
                password = scanner.next();
                if (password.equals(userLogin.getLoginCheck().get(accountNo))) {
                    bool = false;
                    break f1;
                }
                passwordwrongcheck++;
                if (passwordwrongcheck == limit) {
                    userPasswordChangeControl.goToPasswordChange(accountNo);
                    passwordwrongcheck = 0;
                }
            }
            System.out.println("Enter the correct account no");
        }
        accountTransactionControl.transferPage(accountNo);

    }
    public UserLoginControl() {
        userLogin = UserLogin.getInstance();
        userPasswordChangeControl = UserPasswordChangeControl.getInstance();
        accountTransactionControl = AccountTransactionControl.getInstance();
    }

    public static UserLoginControl getInstance() {
        if (userLoginControl == null) userLoginControl = new UserLoginControl();
        return userLoginControl;
    }
}
