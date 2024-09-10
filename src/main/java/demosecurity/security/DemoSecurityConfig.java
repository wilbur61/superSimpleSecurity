package demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

import static demosecurity.controller.PasswordHashingAndStorage.getUsersFromDB;

@Configuration
@EnableMethodSecurity
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        /*

        List<UserDetails> users = Arrays.asList(
        User.withUsername("user1").password("password").roles("USER").build(),
        User.withUsername("admin").password("admin").roles("ADMIN").build()
        );
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(users);
         */

        System.out.println("Entry InMemoryUserDetailsManager ");
        /*
         UserDetails wilbur = User.builder()
                .username("wilbur")
                .password("{noop}123")
                .roles("SUPERUSER")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(wilbur, mary, susan);
*/

        List<UserDetails> users = getUsersFromDB();
        // Create InMemoryUserDetailsManager
        return (new InMemoryUserDetailsManager(users));
    }
}












