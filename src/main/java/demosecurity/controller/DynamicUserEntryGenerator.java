package demosecurity.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class DynamicUserEntryGenerator {
    public static void main(String[] args) {
        // Dynamically generate user entries based on your criteria
        // UserDetails ud = new UserDetails();

        // to be run at a login request
        // Select user name ,pass ,roles ( roles are comma delimited MUST map to YourController)
        // build out this data structure
        // an array of UserDetails objects

        UserDetails user0 = User.withUsername("elmer")
                .password("fudd")
                .roles("ADMIN")
                .build();

        UserDetails user1 = User.withUsername("bugs")
                .password("bunny")
                .roles("EMPLOYEE")
                .build();

        System.out.println("SINGLE USER0="+user0.getAuthorities());
        System.out.println("SINGLE USER0="+user0.isEnabled());
        System.out.println("SINGLE USER1="+user1.getAuthorities());

        List<UserDetails> users = generateUsers();

        System.out.println(users.get(0));

        // Create InMemoryUserDetailsManager
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(users);

        // Use userDetailsManager for authentication and authorization
        // ...
    }

    private static List<UserDetails> generateUsers() {
        List<UserDetails> users = new ArrayList<>();

        // Example: Generate users based on a configuration or database
        for (int i = 1; i <= 2; i++) {
            String username = "user" + i;
            String password = "password" + i;
            String role = "USER"; // Adjust the role as needed

            UserDetails user = User.withUsername(username)
                    .password(password)
                    .roles(role)
                    .build();
            users.add(user);
        }

        return users;
    }


}