package bankingalter.view;

import bankingalter.controller.AdminLoginControl;
import bankingalter.controller.AdminRightsControl;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ManageAdmin {

    private static ManageAdmin manageAdmin;
    private AdminLoginControl adminLoginControl;
    private AdminRightsControl adminRightsControl;
    private Scanner scanner=new Scanner(System.in);

    public void adminView()
    {
        adminLoginControl.adminSets();
        boolean bool=true;
        byte option=0;
        boolean iterate=true;
          adminLoginControl.adminlogin();
            while (bool) {
                try {
                        System.out.println("View Customer Info          Press 1");
                        System.out.println("Add admin                   Press 2");
                        System.out.println("Exit                        Press 3");
                        option = scanner.nextByte();
                        if (option == 3) return;
                        switch (option) {
                            case 1:
                                adminRightsControl.adminWrites();
                                break;
                            case 2:
                                adminLoginControl.addAdmin();
                                break;
                            default:
                                System.out.println("Enter the valid one");

                        }
                    }catch (InputMismatchException e) {
                    System.out.println("Input Mismatch");scanner.next();
                }
            }
        }

    public static ManageAdmin getInstance()
    {
        if(manageAdmin==null)manageAdmin=new ManageAdmin();
        return manageAdmin;
    }
    public ManageAdmin()
    {
        adminLoginControl=AdminLoginControl.getInstance();
        adminRightsControl=AdminRightsControl.getInstance();

    }
}
