package bankingalter.model;

import java.time.LocalDate;

public class Deposit {

    private long Selfaccountno;
    private long depositAmount;
    private long depositDate;
    private String transferType;
    private long selfaccountBalance;

    public long getSelfaccountno() {
        return Selfaccountno;
    }

    public void setSelfaccountno(long selfaccountno) {
        Selfaccountno = selfaccountno;
    }

    public long getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(long depositAmount) {
        this.depositAmount = depositAmount;
    }

    public long getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(long depositDate) {
        this.depositDate = depositDate;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public long getSelfaccountBalance() {
        return selfaccountBalance;
    }

    public void setSelfaccountBalance(long selfaccountBalance) {
        this.selfaccountBalance = selfaccountBalance;
    }
}
