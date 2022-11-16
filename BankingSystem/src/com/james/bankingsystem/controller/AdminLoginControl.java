package bankingalter.controller;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminLoginControl {
    private static AdminLoginControl adminLoginControl;
    private   HashMap<Long,String> adminCheck;
    private CheckList checkList;
    private Scanner scanner=new Scanner(System.in);

    public boolean adminIdCheck(long adminId)
    {
        return getAdminCheck().containsKey(adminId) ;
    }
    public void adminlogin() {
        boolean bool = true;
        byte option = 0;
        boolean iterate = true;
        while (iterate) {
            try {
                System.out.println("Enter the Admin Id");
                long adminId = scanner.nextLong();
                if (!adminIdCheck(adminId)) {
                    continue;
                }

                System.out.println("Enter the Admin Password");
                String adminPassword = scanner.next();
                while (!adminPasswordCheck(adminId, adminPassword)) {
                    System.out.println("Enter the correct password here");
                    adminPassword = scanner.next();
                }
                return;
            } catch (InputMismatchException e) {
                System.out.println("Input Mismatch");
                scanner.next();
            }
            catch (NullPointerException l)
            {
                System.out.println(l.getMessage());
            }
        }
    }
    public boolean adminPasswordCheck(long adminId,String adminPassword)
    {

        if(getAdminCheck().get(adminId).equals(adminPassword))
        {
            return true;
        }
        else System.out.println("Password Wrong");
        return false;


    }
    public void addAdmin()
    {
        long adminId=0;
        String adminPassword;
        System.out.println("Enter the admin Id");
        adminId=scanner.nextLong();
        System.out.println("Enter the admin Password");
        adminPassword=scanner.next();
        if(getAdminCheck().containsKey(adminId)){
            System.out.println("Already Here");return;}
        setAdminCheck(adminId,adminPassword);
        System.out.println("Admin Added SuccessFully");

    }
    public HashMap<Long, String> getAdminCheck() {
        return adminCheck;
    }

    public void setAdminCheck(HashMap<Long, String> adminCheck) {
        this.adminCheck.putAll(adminCheck);
    }

    public static AdminLoginControl getInstance()
    {
        if(adminLoginControl==null)adminLoginControl=new AdminLoginControl();
        return adminLoginControl;
    }
    public void setAdminCheck(Long adminId, String adminPassword)
    {
        this.adminCheck.putIfAbsent(adminId,adminPassword);
    }
    private AdminLoginControl()
    {
        adminCheck=new HashMap<>();
    }
    public  void adminSets()
    {

        this.adminCheck.put(12345l,"james");
    }
}
