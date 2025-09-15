package com.iso.ecommerce.dao.constants;

public class SqlScriptConstants {

    private SqlScriptConstants() {
    }

    public static final String CUSTOMER_SAVE = """
            INSERT INTO customer (first_name, last_name, email, passwrd)
            VALUES (?,?,?,?)
            """;

    public static final String CUSTOMER_FIND_BY_ID = """
            SELECT * FROM customer
            WHERE id = ?
            """;

    public static final String CUSTOMER_EXIST_BY_EMAIL = """
            SELECT * FROM customer
            WHERE email = ?
            LIMIT 1
            """;

    public static final String ORDER_SAVE = """
            INSERT INTO \"order\" (customer_id, total_amount, order_date)
            VALUES (?,?,?)
            """;

    public static final String PAYMENT_SAVE = """
            INSERT INTO payment (order_id, payment_method, amount)
            VALUE (?,?,?)
            """;

    public static final String PRODUCT_SAVE = """
            INSERT INTO product (name, price, stock, category_id, created_by, updated_by)
            VALUE (?,?,?,?,?,?)
            """;

    public static final String PRODUCT_SEARCH_BY_NAME = """
            SELECT * FROM product
            WHERE name LIKE ?
            """;

    public static final String USER_SAVE = """
            INSERT INTO users (username, passwrd, role, active)
            VALUES (?,?,?,?)
            """;

    public static final String USER_FIND_BY_USERNAME = """
            SELECT * FROM users
            WHERE username = ?
            """;

    public static final String CATEGORY_SAVE = """
            INSERT INTO category (name, created_by, updated_by)
            VALUES (?,?,?)
            """;
}
