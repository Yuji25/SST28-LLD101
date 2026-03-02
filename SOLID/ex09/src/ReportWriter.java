public class ReportWriter implements ReportWritable {
    @Override
    public String write(Submission s, int plag, int code) {
        return "report-" + s.roll + ".txt";
    }
}

// implements their lean interface to maintain abstraction and DIP
