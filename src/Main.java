import java.sql.*;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/ANDREWDB";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "Password1!";

    public static void main(String[] args) {


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            // Create
            String createQuery = "INSERT INTO users (user_id, username, email) VALUES (?, ?, ?)";
            try (PreparedStatement createStatement = connection.prepareStatement(createQuery)) {
                createStatement.setInt(1, 8);
                createStatement.setString(2, "Mama Mia");
                createStatement.setString(3, "mama.mia@mamas.com");
                createStatement.executeUpdate();
                System.out.println("Record created successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Read
            String readQuery = "SELECT * FROM users";
            try (Statement readStatement = connection.createStatement();
                ResultSet resultSet = readStatement.executeQuery(readQuery)) {
                while (resultSet.next()) {
                    System.out.println("ID: " + resultSet.getInt("user_id"));
                    System.out.println("USERNAME: " + resultSet.getString("username"));
                    System.out.println("Email: " + resultSet.getString("email"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Update
            String updateQuery = "UPDATE users SET email = ? WHERE user_id = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                updateStatement.setString(1, "updated@update.com");
                updateStatement.setInt(2, 1);
                updateStatement.executeUpdate();
                System.out.println("Record updated successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Delete
            String deleteQuery = "DELETE FROM users WHERE user_id = ?";
            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                deleteStatement.setInt(1, 8);
                deleteStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}