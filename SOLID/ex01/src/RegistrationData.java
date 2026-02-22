public class RegistrationData 
{
    // final keyword is kinda like "const" in C++/JS, once a value is set, you can't change it. 
    public final String name;
    public final String email;
    public final String phone;
    public final String program;

    public RegistrationData(String name, String email, String phone, String program) 
    {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.program = program;
    }
}