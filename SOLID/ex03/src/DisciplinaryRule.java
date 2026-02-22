public class DisciplinaryRule implements EligibilityRule 
{
    @Override
    public boolean isSatisfied(StudentProfile student) 
    {
        return student.disciplinaryFlag == LegacyFlags.NONE;
    }

    @Override
    public String getReason() 
    {
        return "disciplinary flag present";
    }
}