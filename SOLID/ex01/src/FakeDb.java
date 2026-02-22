import java.util.*;

public class FakeDb implements StudentRepository 
{
    private final List<StudentRecord> students = new ArrayList<>();

    @Override
    public void save(StudentRecord record) 
    {
        students.add(record);
    }

    @Override
    public int count() 
    {
        return students.size();
    }

    @Override
    public List<StudentRecord> all() 
    {
        return Collections.unmodifiableList(students);
    }
}


/*
    - Agar kabhi future me SQL me shift hona hoga toh, 
    - we will just make another SQLDb class that will implement the same interface.
*/