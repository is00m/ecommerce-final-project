package com.iso.ecommerce.app;

import com.iso.ecommerce.exception.ISOStoreException;
import com.iso.ecommerce.services.CustomerService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("--- Welcome To ISO-Store ---");
            System.out.println("1 - Customer add");
            System.out.println("2 - Login");
            System.out.println("0 - Exit");
            System.out.print("Select the action you want to perform: ");

            String choice = in.nextLine();
            try {
                switch (choice) {
                    case "1":
                        saveCustomer(in);
                        break;
                    case "2":
                        loginCustomer(in);
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

    public static void loginCustomer(Scanner in) throws ISOStoreException {
        System.out.print("Enter your email: ");
        String email = in.nextLine();
        System.out.print("Enter your password: ");
        String password = in.nextLine();

        CustomerService customerService = new CustomerService();
        customerService.login(email, password);
    }

    public static void saveCustomer(Scanner in) throws ISOStoreException {
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
