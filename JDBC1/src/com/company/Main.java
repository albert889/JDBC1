package com.company;

import java.sql.*;

public class Main {

    private static Connection conn = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    private static final String TABLE_LAPTOP = "laptop";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_BRAND = "brand";
    private static final String COLUMN_MODEL = "model";
    private static final String COLUMN_QTY = "qty";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_YEARS = "year";

    public static void main(String[] args) {

        try{
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/computer_shop", "root","");
            statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS laptop (id INT KEY, brand VARCHAR(30), " +
                    "model VARCHAR(30), qty INT, price FLOAT)");

//            String alterTable = "ALTER TABLE laptop ADD " + COLUMN_YEARS + " VARCHAR(4) AFTER " + COLUMN_MODEL;
//            statement.execute(alterTable);

            String updateRow1 = "UPDATE laptop SET " + COLUMN_YEARS + "='2020' WHERE id=1";
            statement.execute(updateRow1);

            String deleteRow4 = "DELETE FROM laptop WHERE id=4";
            statement.execute(deleteRow4);

            String insertRow4 = "INSERT INTO laptop VALUES(4, 'Asus', 'Zenbook 14 UX435', '2021', 3, 17399000.00)";
            statement.execute(insertRow4);

            resultSet = statement.executeQuery("SELECT * FROM " + TABLE_LAPTOP);

            System.out.println("ID" + " || " + "BRAND" + " || " + "MODEL" + " || " + "YEARS" + " || " + "QTY" + " || " +
                    "PRICE");
            while (resultSet.next()){
                System.out.println(resultSet.getInt(COLUMN_ID) + " || " +
                        resultSet.getString(COLUMN_BRAND) + " || " +
                        resultSet.getString(COLUMN_MODEL) + " || " +
                        resultSet.getString(COLUMN_YEARS) + " || " +
                        resultSet.getInt(COLUMN_QTY) + " || " +
                        resultSet.getFloat(COLUMN_PRICE));
            }

        }catch (SQLException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }finally {
            try {
                resultSet.close();
                statement.close();
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }
}
