package bankingalter.model;

import bankingalter.connection.JDBCOperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class BankSetup {

    private static  BankSetup bankSetup;
    private JDBCOperation jdbcOperation;
    private UserLogin userLogin;
    private BankDatabase bankDatabase;

    public static  BankSetup getInstance()
    {
        if(bankSetup==null)bankSetup=new BankSetup();
        return bankSetup;
    }
    private BankSetup()
    {
        jdbcOperation=JDBCOperation.getInstance();
        bankDatabase=BankDatabase.getInstance();
        userLogin=UserLogin.getInstance();
    }
    public void setBankSetup()
    {
        ResultSet resultSet=jdbcOperation.retriveQuery("select * from customerdetails");
        try {
            while (resultSet.next()) {
                Customer customer=new Customer();
                customer.setUserName(resultSet.getString(1));
                customer.setPassword(resultSet.getString(2));
                customer.setAccountNo(resultSet.getLong(3));
                customer.setAccountBalance(resultSet.getLong(4));
                customer.setDate(resultSet.getLong(5));
                customer.setMobileNo(resultSet.getString(6));
                userLogin.setLoginCheck(resultSet.getLong(3),resultSet.getString(2));
                bankDatabase.setCustomers(customer);
            }
            resultSet=jdbcOperation.retriveQuery("select * from deposit");
            while (resultSet.next())
            {
                Deposit deposit=new Deposit();
                deposit.setSelfaccountno(resultSet.getLong(1));
                deposit.setDepositAmount(resultSet.getLong(2));
                deposit.setDepositDate(resultSet.getLong(3));
                deposit.setTransferType(resultSet.getString(4));
                deposit.setSelfaccountBalance(resultSet.getLong(5));
                bankDatabase.setDeposits(deposit);
            }
            resultSet=jdbcOperation.retriveQuery("select * from accounttransfer");
            while (resultSet.next())
            {
                AccountTransfer accountTransfer=new AccountTransfer();
                accountTransfer.setUserAccountNo(resultSet.getLong(1));
                accountTransfer.setReceiverAccountNo(resultSet.getLong(2));
                accountTransfer.setTransferAmount(resultSet.getLong(3));
                accountTransfer.setTransferbalanceAmount(resultSet.getLong(4));
                accountTransfer.setStatus(resultSet.getString(5));
                accountTransfer.setTransferDate(resultSet.getLong(6));
                bankDatabase.setAccountTransferList(accountTransfer);
            }
            System.out.println("Bank Successfully Setuped.");
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

}
