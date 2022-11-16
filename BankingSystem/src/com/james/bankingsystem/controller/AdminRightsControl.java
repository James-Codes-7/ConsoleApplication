package bankingalter.controller;

import bankingalter.model.AccountTransfer;
import bankingalter.model.BankDatabase;
import bankingalter.model.Customer;
import bankingalter.model.Deposit;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AdminRightsControl {
    private static AdminRightsControl adminRightsControl;
    private BankDatabase bankDatabase;
    private Scanner scanner=new Scanner(System.in);
    public void adminWrites()
    {
        boolean bool=true;
        byte option=1;

        while (bool)
        {
            try {

                System.out.println("\nView CustomerList                  Press 1");
                System.out.println("Single Cusotmer Profile              Press 2");
                System.out.println("View Whole Transactions              Press 3");
                System.out.println("View Single user Transanction        Press 4");
                System.out.println("Exit                                 Press 5");
                System.out.println("Enter the option");
                option = scanner.nextByte();
                if (option == 5)return;

                switch (option) {
                    case 1:
                        viewCustomerList();
                        break;
                    case 2:
                        singleCustomerProfile();
                        break;
                    case 3:
                        viewWholeTransactions();
                        break;
                    case 4:
                        singleUserTransactionHistory();
                        break;
                    default:
                        System.out.println("Enter the valid one");
                }
            }catch (InputMismatchException e){
                System.out.println("Input Mismatch");
                scanner.next();
            }
        }
    }
    private void singleUserTransactionHistory()
    {
        long AccountNo=0;
        try {
            System.out.println("Enter the account Number");
            AccountNo = scanner.nextLong();
            System.out.println("Deposit History\n");
        }catch (InputMismatchException e){
            System.out.println("Input Mismatch");return;}


        List<Deposit> deposits=bankDatabase.getDeposits();
        for(Deposit depositeDetails:deposits)
        {
            if(AccountNo==depositeDetails.getSelfaccountno())
            {
                System.out.println("Account No:     "+depositeDetails.getSelfaccountno());
                System.out.println("Deposite Amount:"+depositeDetails.getDepositAmount());
                System.out.println("Deposite Time:  "+depositeDetails.getDepositDate());
                System.out.println("Account balance:"+depositeDetails.getSelfaccountBalance());
                System.out.println("Status:         "+depositeDetails.getTransferType());


            }
            System.out.println();
        }
        System.out.println("Account Transfer History");
        System.out.println();
        List<AccountTransfer> accountTransfer=bankDatabase.getAccountTransferList();
        for(AccountTransfer accountTransferHistory:accountTransfer)
        {
            if(AccountNo==accountTransferHistory.getUserAccountNo())
            {
                System.out.println("Account No:       "+accountTransferHistory.getUserAccountNo());
                System.out.println("ReceiverAccountNo:"+accountTransferHistory.getReceiverAccountNo());
                System.out.println("Transfer Amount:  "+accountTransferHistory.getTransferAmount());
                System.out.println("Withdraw Date:    "+accountTransferHistory.getTransferDate());
                System.out.println("Account balance:  "+accountTransferHistory.getTransferbalanceAmount());
                System.out.println("Status:           "+accountTransferHistory.getStatus());


            }
            System.out.println();
        }
    }
    private void  viewWholeTransactions()
    {
        System.out.println("Deposit History");
        System.out.println();

        List<Deposit> deposits=bankDatabase.getDeposits();
        for(Deposit depositeDetails:deposits)
        {
            System.out.println("Account No:     "+depositeDetails.getSelfaccountno());
            System.out.println("Deposite Amount:"+depositeDetails.getDepositAmount());
            System.out.println("Deposite Time:  "+depositeDetails.getDepositDate());
            System.out.println("Account balance:"+depositeDetails.getSelfaccountBalance());
            System.out.println("Status:         "+depositeDetails.getTransferType());
            System.out.println();
        }
        System.out.println("Account Transfer History");
        System.out.println();
        List<AccountTransfer> accountTransfer=bankDatabase.getAccountTransferList();
        for(AccountTransfer accountTransferHistory:accountTransfer)
        {

            System.out.println("Account No:       "+accountTransferHistory.getUserAccountNo());
            System.out.println("ReceiverAccountNo:"+accountTransferHistory.getReceiverAccountNo());
            System.out.println("Transfer Amount:  "+accountTransferHistory.getTransferAmount());
            System.out.println("Withdraw Date:    "+accountTransferHistory.getTransferDate());
            System.out.println("Account balance:  "+accountTransferHistory.getTransferbalanceAmount());
            System.out.println("Status:           "+accountTransferHistory.getStatus());
            System.out.println();
        }
    }
    private void viewCustomerList()
    {
        List<Customer> customers=bankDatabase.getCustomers();
        for(Customer customer:customers)
        {
            System.out.println("Account no:             "+customer.getAccountNo());
            System.out.println("User Name:              "+customer.getUserName());
            System.out.println("Mobile No:              "+customer.getMobileNo());
            System.out.println("Account Balance:        "+customer.getAccountBalance());
            System.out.println("Account Create Date     "+customer.getDate());
            System.out.println();
        }
    }
    private void singleCustomerProfile()
    {
        System.out.println("Enter the Account No");
        long accountNo=scanner.nextLong();
        List<Customer> customers=bankDatabase.getCustomers();
        for(Customer customer:customers)
        {
            if(accountNo==customer.getAccountNo()) {
                System.out.println();
                System.out.println("Account no:             " + customer.getAccountNo());
                System.out.println("User Name:              " + customer.getUserName());
                System.out.println("Mobile No:              " + customer.getMobileNo());
                System.out.println("Account Balance:        " + customer.getAccountBalance());
                System.out.println("Account Create Date     " + customer.getDate());
                return;
            }
        }
        System.out.println("Wrong Account No");

    }

    public static AdminRightsControl getInstance()
    {
        if(adminRightsControl==null)adminRightsControl=new AdminRightsControl();
        return adminRightsControl;
    }
    public AdminRightsControl()
    {
        bankDatabase=BankDatabase.getInstance();
    }
}
