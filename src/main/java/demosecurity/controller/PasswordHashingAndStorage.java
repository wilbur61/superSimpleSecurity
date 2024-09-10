package demosecurity.controller;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/*
make sure this table is created in your DB.

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    roles VARCHAR(255)  NULL
);
 */
public class PasswordHashingAndStorage {
    public static void main(String[] args) throws SQLException {

        String userName = "elmer2";
        String passwd = "fudd";
        //String rstr = registerUser(userName, passwd);
        //System.out.println("reguser returns: " + rstr);
        List<UserDetails> users = getUsersFromDB();
        // Create InMemoryUserDetailsManager
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(users);
    }

    public static String registerUser(String uname, String passwd) {

        //============================================================
        //  Change these to your requirements for roles.  must be in sync with
        //  your controller
        String defaultRoles = "ADMIN,REG_USER,MANAGER";
        //============================================================
        String returnString = "OK";
        // Load properties from application.properties file
        Properties properties = new Properties();
        try {
            properties.load(PasswordHashingAndStorage.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            //e.printStackTrace();
            returnString = e.getMessage();
        }
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Database connection details from properties file
        String url = properties.getProperty("spring.datasource.url");
        String dbusername = properties.getProperty("spring.datasource.username");
        String dbpassword = properties.getProperty("spring.datasource.password");


        // Create a BCryptPasswordEncoder
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Hash the password
        String hashedPassword = passwordEncoder.encode(passwd);

        try {
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(url, dbusername, dbpassword);

            // SQL statement to insert the hashed password into a table
            String sql = "INSERT INTO users (username, password, roles) VALUES (?, ?, ?)";

            // Create a prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set the parameters
            preparedStatement.setString(1, uname);
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.setString(3, defaultRoles);

            // Execute the statement
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Password hashed and stored successfully.");
            } else {
                System.out.println("Failed to store the password.");
                returnString = "Failed to store the password.";
            }

            // Close the connection
            connection.close();
        } catch (SQLException e) {
            //System.out.println("Error: " + e.getMessage());
            returnString = e.getMessage();
        }
        return returnString;
    }

    //=====================================================
    public static List<UserDetails> getUsersFromDB()  {


        // Load properties from application.properties file
        Properties properties = new Properties();
        try {
            properties.load(PasswordHashingAndStorage.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Database connection details from properties file
        String url = properties.getProperty("spring.datasource.url");
        String dbusername = properties.getProperty("spring.datasource.username");
        String dbpassword = properties.getProperty("spring.datasource.password");

        List<UserDetails> users = new ArrayList<>();
        try {

            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(url, dbusername, dbpassword);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT username, password, roles FROM users");
            {



                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String roles = resultSet.getString("roles");

                    System.out.println("USER= "+username);
                    System.out.println("PASS= "+password);
                    System.out.println("ROLE= "+roles);
                    //===========================================

                        UserDetails user = User.withUsername(username)
                                .password("{bcrypt}"+password)
                                .roles(roles)
                                .build();
                        users.add(user);

                    //============================================

                }

            }
            // Close the connection
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // dump the array..
        // Print the contents of the list
        for (UserDetails user : users) {
            System.out.println("DUMPING!!!="+user);
        }
        return(users);
    }
}
