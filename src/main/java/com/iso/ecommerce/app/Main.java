package com.iso.ecommerce.app;

import com.iso.ecommerce.exception.ISOStoreException;
import com.iso.ecommerce.model.User;
import com.iso.ecommerce.model.enums.Role;
import com.iso.ecommerce.services.CustomerService;
import com.iso.ecommerce.services.UserService;
import com.iso.ecommerce.util.PasswordUtil;

import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private  static final UserService userService = new UserService();
    public static void main(String[] args) {


        while (true) {
            getMainMenu();
            String choice = in.nextLine();
            
            try {
                switch (choice) {
                    case "1":
                        getUserMenu();
                        break;
                    case "2":
                        getCustomerMenu();
                        break;
                    case "0":
                        System.out.println("Bye Bye!");
                        return;
                    default:
                        System.out.println("Please select a valid option!");
                }
            } catch (ISOStoreException e){
                System.out.println(e.getMessage());;
            }
        }

    }

    private static void getCustomerMenu() throws ISOStoreException {
        while (true){
            System.out.println("--- CUSTOMER PANEL ---");
            System.out.println("1 - Register");
            System.out.println("2 - Login");
            System.out.println("0 - Previous menu");
            System.out.print("Select your login type: ");
            String choice = in.nextLine();

            switch (choice){
                case "1":
                    registerCustomer();
                    break;
                case "2":
                    loginCustomer();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Please select a valid option!");
            }
        }
    }

    private static void getUserMenu() throws ISOStoreException {
        while (true){
            System.out.println("--- USER PANEL ---");
            System.out.println("1 - Register");
            System.out.println("2 - Login");
            System.out.println("0 - Previous menu");
            System.out.print("Select your login type: ");
            String choice = in.nextLine();

            switch (choice){
                case "1":
                    registerUser();
                    break;
                case "2":
                    loginUser();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Please select a valid option!");
            }
        }
    }

    private static void loginUser() throws ISOStoreException {
        System.out.print("Enter your username: ");
        String username = in.nextLine();
        System.out.print("Enter your password: ");
        String password = in.nextLine();

        User loginedUser = userService.login(username, password);

        if (loginedUser != null && loginedUser.getActive()){

            getLoginedUserMenu();
        }
    }

    private static void getLoginedUserMenu() {
        System.out.println("--- ADMIN PANEL ---");
        System.out.println("1 - Create category");
        System.out.println("2 - Delete category");
        System.out.println("3 - Create product");
        System.out.println("4 - Delete product");
        System.out.println("0 - Previous");
        System.out.print("Select your login type: ");
    }

    private static void registerUser() throws ISOStoreException {
        System.out.print("Enter your username: ");
        String username = in.nextLine();
        System.out.print("Enter your password: ");
        String password = in.nextLine();
        System.out.print("Select a role(ADMIN, SUPPORT): ");
        String roleStr = in.nextLine().toUpperCase();

        Role role = Role.valueOf(roleStr);
        User user = new User(username, PasswordUtil.hash(password), role);
        userService.save(username,password,role);
    }

    private static void getMainMenu() {
        System.out.println("--- SELECT LOGIN SECTION ---");
        System.out.println("1 - User login (ADMIN, SUPPORT)");
        System.out.println("2 - Customer login");
        System.out.println("0 - Exit");
        System.out.print("Select your login type: ");
    }

    public static void loginCustomer() throws ISOStoreException {
        System.out.print("Enter your email: ");
        String email = in.nextLine();
        System.out.print("Enter your password: ");
        String password = in.nextLine();

        CustomerService customerService = new CustomerService();
        customerService.login(email, password);
    }

    public static void registerCustomer() throws ISOStoreException {
        System.out.print("Enter your name: ");
        String firstName = in.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = in.nextLine();
        System.out.print("Enter your email: ");
        String email = in.nextLine();
        System.out.print("Enter your password: ");
        String password = in.nextLine();

        CustomerService customerService = new CustomerService();
        customerService.save(firstName, lastName, email, password);
    }
}
