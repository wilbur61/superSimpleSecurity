package demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// put your code here
@Controller
public class YourController {

    @GetMapping("/")
    public String showHome() {

        return "home";
    }
}
/*  to do:  Vett this code:
 Remember to enable @EnableMethodSecurity on your main security
 configuration class for method-level security to work.

@Controller
public class MyController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')") // Only allow admins to access this method
    public String adminPage() {
        // ... admin specific logic
        return "admin";
    }

    @GetMapping("/management")
    @PreAuthorize("hasAnyRole('MANAGER', 'SUPERUSER')") // Managers and Superusers can access this method
    public String managementPage() {
        // ... management specific logic
        return "management";
    }

    // ... other methods
}
 */