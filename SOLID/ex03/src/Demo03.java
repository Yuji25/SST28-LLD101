import java.util.*;

public class Demo03 
{
    public static void main(String[] args) {
        System.out.println("=== Placement Eligibility ===");

        // same order rakhna padega
        List<EligibilityRule> rules = List.of(
            new DisciplinaryRule(),
            new CgrRule(),
            new AttendanceRule(),
            new CreditsRule()
        );

        EligibilityRepository repository = new FakeEligibilityStore();
        ReportPrinter printer = new ReportPrinter();

        EligibilityEngine engine = new EligibilityEngine(rules, repository, printer);

        StudentProfile student = new StudentProfile(
            "23BCS1001", "Ayaan", 8.10, 72, 18, LegacyFlags.NONE
        );

        engine.runAndPrint(student);
    }
}