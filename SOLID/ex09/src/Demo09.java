public class Demo09 {
    public static void main(String[] args) {
        System.out.println("=== Evaluation Pipeline ===");
        Submission sub = new Submission("23BCS1007", "public class A{}", "A.java");

        Rubric rubric = new Rubric();
        PlagiarismCheckable checker = new PlagiarismChecker();
        CodeGradable grader = new CodeGrader(rubric);
        ReportWritable writer = new ReportWriter();

        new EvaluationPipeline(checker, grader, writer).evaluate(sub);
    }
}

// by passing the correct implementation in the constructor, we are doing Dependency Injection.
// hence we are enforcing DIP !
