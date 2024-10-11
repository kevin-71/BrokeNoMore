import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    public String[] GetDbInfo(){
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("../config.properties")) { // get file
            props.load(fis);
        } catch (IOException e) { // check errors
            e.printStackTrace();
        }

        String dbUrl = props.getProperty("db.url");
        String dbUser = props.getProperty("db.user");
        String dbPass = props.getProperty("db.password");

        String[] dbInfo = {dbUrl, dbUser, dbPass}; // return all info in a string

        return dbInfo;
    }

    public Connection setDB() {
        String[] auth = GetDbInfo();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(auth[0], auth[1], auth[2]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public double getMoney() throws SQLException {
        try {
            double money = 0;
            Connection connection = setDB();
            Statement statement = connection.createStatement();

            String query = "SELECT money FROM user";

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                money = resultSet.getFloat("money");
            }
            return money;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addMoney(double moneyAmount) throws SQLException {
        try {
            Connection connection = setDB();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE user SET money = money + ?"
            );
            preparedStatement.setDouble(1, moneyAmount);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
