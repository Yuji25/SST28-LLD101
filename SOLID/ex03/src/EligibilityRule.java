public interface EligibilityRule 
{
    boolean isSatisfied(StudentProfile student);
    String getReason();
}