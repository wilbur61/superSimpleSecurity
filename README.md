# Super simple Secured Login  Project

# Use this to get basics of Spring Security coded. This only covers basic hardcoded login

example is login:  wilbur <br>
password: 123

it will then route to to YourController  ( add your code to: public class YourController {} )
see href in home page to enable clean logout



  public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails wilbur = User.builder()
                .username("wilbur")
                .password("{noop}123")
                .roles("SUPERUSER")
                .build();
=================================================
RoleBased Branch implements Simple role based security
