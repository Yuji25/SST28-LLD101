import java.util.*;

public class RegistrationValidator 
{
    // static : belongs to the class, not the object. Can globally be accessed using class names, all objs can see the changed value
    private static final Set<String> ALLOWED_PROGRAMS = Set.of("CSE", "AI", "SWE");

    public List<String> validate(RegistrationData data) 
    {
        List<String> errors = new ArrayList<>();
        
        if (data.name.isBlank()) 
            errors.add("name is required");
        if (data.email.isBlank() || !data.email.contains("@")) 
            errors.add("email is invalid");
        if (data.phone.isBlank() || !data.phone.chars().allMatch(Character::isDigit)) 
            errors.add("phone is invalid");
        if (!ALLOWED_PROGRAMS.contains(data.program)) 
            errors.add("program is invalid");
        
        return errors;
    }
}

/*
    - Same reason as Parser and Data class.
    - De-coupled things to make our life easier :)
*/