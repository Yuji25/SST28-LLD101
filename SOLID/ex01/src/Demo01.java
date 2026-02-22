public class Demo01 {
    public static void main(String[] args) {
        System.out.println("=== Student Onboarding ===");
        
        StudentRepository db = new FakeDb();
        RegistrationParser parser = new RegistrationParser();
        RegistrationValidator validator = new RegistrationValidator();
        OnboardingPrinter printer = new OnboardingPrinter();
        
        OnboardingService service = new OnboardingService(db, parser, validator, printer);
        
        service.registerFromRawInput("name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE");
        
        printer.printDbDump(db);
    }
}