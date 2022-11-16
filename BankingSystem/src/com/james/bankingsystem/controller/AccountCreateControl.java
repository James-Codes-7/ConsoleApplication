package bankingalter.controller;


import bankingalter.model.BankDatabase;
import bankingalter.model.Customer;
import bankingalter.model.UserLogin;
import org.w3c.dom.ls.LSOutput;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountCreateControl {

    private static AccountCreateControl accountCreateControl;
    private UserLogin userLogin;

    private Scanner scanner=new Scanner(System.in);

    private CheckList checkList;
    private BankDatabase  bankDatabase;

    private  boolean bool=true;

    private byte option=1;
    private static long ACCOUNTNOSTART=10000;
    private  long  generateAccountNumber=1;
    public   int accountCreate()
    {

        generateAccountNumber=ACCOUNTNOSTART+bankDatabase.getCustomers().size();
        String userName=namePage();
        String password=passwordPage();
        String mobileno=mobileNoPage();
        long initialAmount=initialAmountPage();
        System.out.println("Your Account number is:"+generateAccountNumber);

        HashMap<Long,String> loginCheck=new HashMap<>();
        loginCheck.put(generateAccountNumber,password);
        userLogin.setLoginCheck(loginCheck);

        HashMap<Long,String> passwordChangeReferance=new HashMap<>();
        passwordChangeReferance.put(generateAccountNumber,mobileno);
        userLogin.setPasswordChange(passwordChangeReferance);
        Customer customer=new Customer();
        customer.setAccountNo(generateAccountNumber++);
        customer.setUserName(userName);
        customer.setPassword(password);
        customer.setMobileNo(mobileno);
        customer.setAccountBalance(initialAmount);
        customer.setDate(System.currentTimeMillis());
        bankDatabase.accountCreateInDatabase(customer);
        bankDatabase.setCustomers(customer);
        System.out.println("Account Created SuccessFully");
        return 1;

    }

    public String namePage()
    {
        System.out.println("Enter the customer name");
        System.out.println("The First letter must be Captital And only use letters");
        String userName="";
        option=1;
        bool=true;
        label:while(bool)
        {

            userName=scanner.nextLine();
            while(checkList.nameCheck(userName)&&option==1)
            {
                System.out.println("Your Name is:"+userName);
                System.out.println("If you want change name    Press 1 else 0");
                option=scanner.nextByte();
                if(option==1) {System.out.println("Re -Enter the name");scanner.nextLine();continue label;}
                else {bool=false ;continue label;}
            }
            System.out.println("Enter the Correct format");

        }
        return userName;
    }
    public  String passwordPage()
    {
        bool=true;
        String password="";
        System.out.println("Enter the Password");
        System.out.println("The Password must be above 8 charcters");
        while(bool)
        {
            password=scanner.next();
            if(checkList.passwordCheck(password))
            {
                bool=false;
                break;
            }
            else
                System.out.println("Re-Enter the password");
        }
        return  password;
    }
    public String mobileNoPage()
    {
        String mobileno="";
        bool=true;
        option=1;
        System.out.println("Enter the Mobileno");
        System.out.println("Only put numbers and 10 digits only allowed");
        while(bool)
        {
            mobileno=scanner.next();
            if(checkList.mobileNumberCheck(mobileno))
            {
                System.out.println("Your mobile no:"+mobileno);
                System.out.println("If you want change   Press 1 else 0");
                option=scanner.nextByte();
                if(option==1) {System.out.println("Re -enter the mobile no");continue;}
                else {bool=false;break;}
            }
            System.out.println("Enter the correct format");
        }
        return mobileno;
    }
    public long initialAmountPage()
    {
        long initialAmount=0;
        bool=true;
        option=0;
        System.out.println("Enter the initial Amount");
        System.out.println("Only accepted if amount greater than five hundred");
        while(bool) {
            try {
                initialAmount = scanner.nextLong();
                if (initialAmount >= 500) {
                    System.out.println("If you want change intial amount    Press 1 else 0");
                    option = scanner.nextByte();
                    if (option == 1) {
                        System.out.println("Re -Enter the initial amount");
                        continue;
                    } else {
                        break;
                    }

                } else {
                    System.out.println("Enter the valid amount");
                }
            }catch (InputMismatchException e)
            {
                System.out.println("Input Mismatch ");
                scanner.next();
            }
        }
        return initialAmount;
    }
    public static AccountCreateControl getInstance()
    {
        if(accountCreateControl==null)accountCreateControl=new AccountCreateControl();
        return accountCreateControl;
    }

    public AccountCreateControl()
    {
        checkList=CheckList.getInstance();
        bankDatabase=BankDatabase.getInstance();
        userLogin =UserLogin.getInstance();
    }

}
