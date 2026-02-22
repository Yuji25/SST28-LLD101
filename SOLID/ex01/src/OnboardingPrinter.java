import java.util.*;

public class OnboardingPrinter {
    
    public void printInput(String raw) 
    {
        System.out.println("INPUT: " + raw);
    }

    public void printValidationErrors(List<String> errors) 
    {
        System.out.println("ERROR: cannot register");
        for (String e : errors) 
        {
            System.out.println("- " + e);
        }
    }

    public void printSuccess(String id, int totalCount) 
    {
        System.out.println("OK: created student " + id);
        System.out.println("Saved. Total students: " + totalCount);
        System.out.println("CONFIRMATION:");
    }

    public void printStudentRecord(StudentRecord record) 
    {
        System.out.println(record);
    }

    public void printDbDump(StudentRepository students) 
    {
        System.out.println();
        System.out.println("-- DB DUMP --");
        System.out.println(TextTable.render3(students));
    }
}

/*
    - Kal ko printing logic changes toh we don't need to change the Service class
*/