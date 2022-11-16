package bankingalter.view;


import bankingalter.controller.AccountCreateControl;
import bankingalter.controller.AccountTransactionControl;
import bankingalter.controller.UserLoginControl;
import bankingalter.controller.UserPasswordChangeControl;
import bankingalter.model.BankDatabase;
import bankingalter.model.Customer;


import java.util.HashMap;
import java.util.Scanner;

public class ManageCustomer {
    private static  ManageCustomer manageCustomer;
    private AccountCreateControl accountCreateControl;
    private UserLoginControl userLoginControl;
    private Scanner scanner=new Scanner(System.in);

    public  void clientView()
    {
        boolean iterate=true;
        int check=0,option=0;
        while(iterate)
        {
            System.out.println("Account Create    Press 1");
            System.out.println("Login             Press 2");
            System.out.println("Exit              Press 3");
            System.out.println("Enter the option");
            option=scanner.nextInt();
            if(option==3)return;
            switch(option)
            {
                case 1:check=accountCreateControl.accountCreate();
                    break;
                case 2:if(check!=0)
                {userLoginControl.customerLogin();
                    break;}
                default :System.out.println("Enter the correct option");
                    System.out.println();
            }

        }
    }
    public static ManageCustomer getInstance()
    {
        if(manageCustomer==null)manageCustomer=new ManageCustomer();
        return manageCustomer;
    }
    public ManageCustomer()
    {
        accountCreateControl=AccountCreateControl.getInstance();
        userLoginControl=UserLoginControl.getInstance();
    }

}
