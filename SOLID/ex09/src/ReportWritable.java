public interface ReportWritable {
    String write(Submission s, int plag, int code);
}

// absraction for respective concrete classes