package bankingalter.controller;

import bankingalter.model.BankDatabase;
import bankingalter.model.Customer;
import bankingalter.model.UserLogin;
import java.util.List;
import java.util.Scanner;

public class UserPasswordChangeControl {

    private static UserPasswordChangeControl userPasswordChangeControl;
    private BankDatabase bankDatabase;

    private CheckList checkList;
    private UserLogin userLogin;
    private Scanner scanner=new Scanner(System.in);

    public static UserPasswordChangeControl getInstance()
    {
        if(userPasswordChangeControl==null)userPasswordChangeControl=new UserPasswordChangeControl();
        return userPasswordChangeControl;
    }
    private UserPasswordChangeControl()
    {
        checkList= CheckList.getInstance();
        bankDatabase=BankDatabase.getInstance();
        userLogin=UserLogin.getInstance();
    }
    public void goToPasswordChange(long accountno) {
        System.out.println("Please Change your password");
        System.out.println();
        String phoneno = "";
        boolean bool = true;
        String password = "";
        System.out.println("Enter Your phone number");
        while (bool) {
            phoneno =scanner.next();
            while (userLogin.getPasswordChange().get(accountno).equals(phoneno)) {
                System.out.println("Enter the new password");
                password ="k"; //scanner.next();
                if (checkList.passwordCheck(password)) {
                    passwordChanging(accountno, password);
                    System.out.println("Password Succesfully Changed");
                    return;
                } else {
                    System.out.println("Re enter Password");
                    System.out.println();
                }
                System.out.println("Enter the correct phone number");
            }
        }
    }
    private void passwordChanging (long accountno, String password)
    {
        List<Customer> customerDetails=bankDatabase.getCustomers();
        for (Customer changeProcess : customerDetails) {
            if (changeProcess.getAccountNo() == accountno) {
                changeProcess.setPassword(password);
                userLogin.setLoginCheck(accountno,password);
                return;
            }
        }
    }
}
