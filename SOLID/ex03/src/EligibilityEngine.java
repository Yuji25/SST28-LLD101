import java.util.*;

public class EligibilityEngine 
{
    private final List<EligibilityRule> rules;
    private final EligibilityRepository repository;
    private final ReportPrinter printer;

    public EligibilityEngine(List<EligibilityRule> rules, 
                            EligibilityRepository repository,
                            ReportPrinter printer) 
    {
        this.rules = rules;
        this.repository = repository;
        this.printer = printer;
    }

    public void runAndPrint(StudentProfile student)
    {
        EligibilityEngineResult result = evaluate(student);
        
        printer.print(student, result);
        
        repository.save(student.rollNo, result.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile student) 
    {
        List<String> reasons = new ArrayList<>();
        String status = "ELIGIBLE";

        for (EligibilityRule rule : rules) {
            if (!rule.isSatisfied(student)) {
                status = "NOT_ELIGIBLE";
                reasons.add(rule.getReason());
                break;
            }
        }

        return new EligibilityEngineResult(status, reasons);
    }
}

class EligibilityEngineResult 
{
    public final String status;
    public final List<String> reasons;

    public EligibilityEngineResult(String status, List<String> reasons) 
    {
        this.status = status;
        this.reasons = reasons;
    }
}