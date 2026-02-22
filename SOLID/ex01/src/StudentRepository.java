import java.util.*;

public interface StudentRepository
{
    void save(StudentRecord record);
    int count();
    List<StudentRecord> all();
}

/*
    - Kyu ? 
    - Cuz Decouples OnboardingService from concrete FakeDb 
    - It removes hard-coded dependency.
*/