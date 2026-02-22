import java.util.*;

public class RegistrationParser 
{
    public RegistrationData parse(String raw) 
    {
        // LinkedHashMap maintains insertion order (or access order) of keys using a doubly-linked list, while HashMap offers no ordering guarantees
        Map<String, String> kv = new LinkedHashMap<>();
        
        String[] parts = raw.split(";");
        for (String p : parts) 
            {
            String[] t = p.split("=", 2);
            if (t.length == 2) kv.put(t[0].trim(), t[1].trim());
        }
        
        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");
        
        return new RegistrationData(name, email, phone, program);
    }
}

/*
    - Kuch nahi, jo kaam apan starting part of OnboardingService.java me kar rahe the,
    - ab wo ek separate class me kar rahe hai.
    - To de-couple things and make our design SRP enabled.
*/