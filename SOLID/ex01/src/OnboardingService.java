import java.util.*;

public class OnboardingService 
{
    private final StudentRepository repository;
    private final RegistrationParser parser;
    private final RegistrationValidator validator;
    private final OnboardingPrinter printer;

    public OnboardingService(StudentRepository repository, 
                             RegistrationParser parser,
                             RegistrationValidator validator,
                             OnboardingPrinter printer) 
    {
        this.repository = repository;
        this.parser = parser;
        this.validator = validator;
        this.printer = printer;
    }

    public void registerFromRawInput(String raw) 
    {
        printer.printInput(raw);
        
        // Parse karenge
        RegistrationData data = parser.parse(raw);
        
        // Validate karenge
        List<String> errors = validator.validate(data);
        if (!errors.isEmpty()) {
            printer.printValidationErrors(errors);
            return;
        }
        
        // Generate ID ( phele se sahi tha )
        String id = IdUtil.nextStudentId(repository.count());
        
        // Create Record
        StudentRecord record = new StudentRecord(id, data.name, data.email, data.phone, data.program);
        
        // Save ( currently in-memory )
        repository.save(record);
        
        // Printing Success
        printer.printSuccess(id, repository.count());
        printer.printStudentRecord(record);
    }
}