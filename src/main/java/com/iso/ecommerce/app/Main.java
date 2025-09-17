package com.iso.ecommerce.app;

import com.iso.ecommerce.exception.ISOStoreException;
import com.iso.ecommerce.model.Category;
import com.iso.ecommerce.model.Order;
import com.iso.ecommerce.model.Product;
import com.iso.ecommerce.model.User;
import com.iso.ecommerce.model.enums.Role;
import com.iso.ecommerce.services.*;
import com.iso.ecommerce.util.PasswordUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final UserService userService = new UserService();
    private static final CategoryService categoryService = new CategoryService();
    private static final ProductService productService = new ProductService();
    private static final OrderService orderService = new OrderService();
    private static User LOGINED_USER;

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
            } catch (ISOStoreException e) {
                System.out.println(e.getMessage());
                ;
            }
        }

    }

    private static void getCustomerMenu() throws ISOStoreException {
        while (true) {
            System.out.println("--- CUSTOMER PANEL ---");
            System.out.println("1 - Register");
            System.out.println("2 - Login");
            System.out.println("0 - Previous menu");
            System.out.print("Select your login type: ");
            String choice = in.nextLine();

            switch (choice) {
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
        while (true) {
            System.out.println("--- USER PANEL ---");
            System.out.println("1 - Register");
            System.out.println("2 - Login");
            System.out.println("0 - Previous menu");
            System.out.print("Select your login type: ");
            String choice = in.nextLine();

            switch (choice) {
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

        if (loginedUser != null && loginedUser.getActive()) {

            LOGINED_USER = loginedUser;

            getLoginedUserMenu();
        }
    }

    private static void getLoginedUserMenu() throws ISOStoreException {
        while (true) {
            System.out.println("--- ADMIN PANEL ---");
            System.out.println("1 - Create category");
            System.out.println("2 - Delete category");
            System.out.println("3 - Category List");
            System.out.println("4 - Create product");
            System.out.println("5 - Delete product");
            System.out.println("6 - Product list");
            System.out.println("7 - Order list");
            System.out.println("0 - Previous");
            System.out.print("Select your login type: ");
            String choice = in.nextLine();

            switch (choice) {
                case "1":
                    createCategory();
                    break;
                case "2":
                    deleteCategory();
                    break;
                case "3":
                    listCategory();
                    break;
                case "4":
                    createProduct();
                    break;
                case "5":
                    deleteProduct();
                    break;
                case "6":
                    listProduct();
                    break;
                case "7":
                    listOrder();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Please select a valid option!");
            }
        }
    }

    private static void listOrder() {
        List<Order> orderList = orderService.getAll();
    }

    private static void listProduct() {
        List<Product> productList = productService.getAll();
        productList.forEach(product ->
                System.out.printf("%s : %s : %s \n", product.getCategory().getName(), product.getName(), product.getPrice()));
    }

    private static void deleteProduct() {
        System.out.print("Enter product ID to delete: ");
        String productId = in.nextLine();
        productService.deleteId(Long.parseLong(productId));
    }

    private static void createProduct() throws ISOStoreException {
        System.out.print("Enter product name: ");
        String productName = in.nextLine();
        System.out.print("Enter product price: ");
        String productPrice = in.nextLine();
        System.out.print("Enter product stock: ");
        String productStock = in.nextLine();
        System.out.print("Enter category ID: ");
        String categoryId = in.nextLine();

        Category category = categoryService.getById(Long.parseLong(categoryId));
        Product product = new Product(productName, new BigDecimal(productPrice), Integer.parseInt(productStock), category);
        productService.save(product, LOGINED_USER);
    }

    private static void listCategory() {
        List<Category> categoryList = categoryService.getall();
        categoryList.forEach(System.out::println);
    }

    private static void deleteCategory() {
        System.out.print("Enter category ID: ");
        String categoryId = in.nextLine();

        categoryService.deleteById(Long.parseLong(categoryId));
    }

    private static void createCategory() throws ISOStoreException {
        throw new ISOStoreException("NOT IMPLEMENTED!");
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
        userService.save(username, password, role);
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
