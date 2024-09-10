# Super simple Secured Login  Project

# Use this to get basics of Spring Security coded. This only covers basic hardcoded login

================================================= <br>
# RoleBased Branch implements Simple role based security <br>

example covers  and admin user (susan)   and superuser (wilbur) <br>
Will provide graceful error msg  if role is not admin.  Try using wilbur to access admin page <br>

   @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')") // Only allow admins to access this method
    public String adminPage() {
        // ... admin specific logic
        return "admin";
