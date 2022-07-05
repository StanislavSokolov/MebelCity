import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class SQL {
    public static void createBD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = getConnection()) {
                Statement statement = conn.createStatement();
                System.out.println("Connection to Store DB succesfull!");
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }

    public static Connection getConnection() throws SQLException, IOException {
        return DriverManager.getConnection("url", "user","password");
    }

    public static ArrayList getArrayList(String request) {
        ArrayList<Product> arrayList = null;
        try {
            arrayList = new ArrayList<>();
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = getConnection()) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(request);
                while (resultSet.next()) {
                    Product product = new Product(resultSet.getInt("Id"),
                            resultSet.getString("name"),
                            resultSet.getString("category"),
                            resultSet.getString("description"),
                            resultSet.getString("manufacturer"),
                            resultSet.getString("manufacturerPartNumber"),
                            resultSet.getString("mainPhoto"),
                            resultSet.getString("photo1"),
                            resultSet.getString("photo2"),
                            resultSet.getString("photo3"),
                            resultSet.getInt("price"),
                            resultSet.getInt("discount"),
                            resultSet.getInt("rating"),
                            resultSet.getInt("ratingBusket"),
                            resultSet.getInt("ratingOrder"),
                            resultSet.getString("teg1"),
                            resultSet.getString("teg2"),
                            resultSet.getString("teg3"));
                    arrayList.add(product);
                }

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return arrayList;
    }

    public static void setValue(String request) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = getConnection()) {
                Statement statement = conn.createStatement();
                statement.executeUpdate(request);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}



