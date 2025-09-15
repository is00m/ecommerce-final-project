package com.iso.ecommerce.dao.constants;

public class SqlScriptConstants {

    private SqlScriptConstants() {
    }

    public static String CUSTOMER_SAVE = """
            INSERT INTO customer (first_name, last_name, email, passwrd)
            VALUES (?,?,?,?)
            """;

    public static String CUSTOMER_FIND_BY_ID = """
            SELECT * FROM customer
            WHERE id = ?
            """;

    public static String CUSTOMER_EXIST_BY_EMAIL = """
            SELECT * FROM customer
            WHERE email = ?
            LIMIT 1
            """;

    public static String ORDER_SAVE = """
            INSERT INTO \"order\" (customer_id, total_amount, order_date)
            VALUES (?,?,?)
            """;

    public static String PAYMENT_SAVE = """
            INSERT INTO payment (order_id, payment_method, amount)
            VALUE (?,?,?)
            """;

    public static String PRODUCT_SEARCH_BY_NAME = """
            SELECT * FROM product
            WHERE name LIKE ?
            """;

}
