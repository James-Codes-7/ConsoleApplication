package bankingalter.model;
import bankingalter.connection.JDBCOperation;
import java.util.ArrayList;
import java.util.List;

public class BankDatabase {

    private static BankDatabase bankDatabase;
    private JDBCOperation jdbcOperation;

    private List<AccountTransfer> accountTransferList;

    private  List<Customer> customers;
    private List<Deposit> deposits;

    public static BankDatabase getInstance()
    {
        if(bankDatabase==null)bankDatabase=new BankDatabase();
        return bankDatabase;
    }
    public  BankDatabase()
    {
        accountTransferList=new ArrayList<>();
        customers=new ArrayList<>();
        deposits=new ArrayList<>();
        jdbcOperation=JDBCOperation.getInstance();
    }
    public void accountCreateInDatabase(Customer customer)
    {
      jdbcOperation.updateQuery("insert into customerdetails values('"+customer.getUserName()+"','"+customer.getPassword()+
              "',"+customer.getAccountNo()+","+customer.getAccountBalance()+","+customer.getDate()+",'"+customer.getMobileNo()+"')");
    }
    public void accountTransferUpdateInDatabase(AccountTransfer accountTransfer)
    {
        jdbcOperation.updateQuery("insert into accounttransfer values("+accountTransfer.getUserAccountNo()+
                ","+accountTransfer.getReceiverAccountNo()+","+accountTransfer.getTransferAmount()+","+accountTransfer.getTransferbalanceAmount()+","
        +"'"+accountTransfer.getStatus()+"',"+accountTransfer.getTransferDate()+")");
    }
    public void accountDepositUpdateInDatabase(Deposit deposit)
    {
        jdbcOperation.updateQuery("insert into deposit values("+deposit.getSelfaccountno()+","+deposit.getDepositAmount()+"," +
                ""+deposit.getDepositDate()+",'2"+deposit.getTransferType()+"',"+deposit.getSelfaccountBalance()+")");
    }
    public List<AccountTransfer> getAccountTransferList() {
        return accountTransferList;
    }

    public void setAccountTransferList(List<AccountTransfer> accountTransferList) {
        this.accountTransferList = accountTransferList;
    }
    public void setAccountTransferList(AccountTransfer accountTransfer)
    {
        this.accountTransferList.add(accountTransfer);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
    public void setCustomers(Customer customer)
    {
        this.customers.add(customer);
    }
    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }
    public void setDeposits(Deposit deposit)
    {
        this.deposits.add(deposit);
    }

}
