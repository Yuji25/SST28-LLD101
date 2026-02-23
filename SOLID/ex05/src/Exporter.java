public abstract class Exporter 
{
    public final ExportResult export(ExportRequest req) 
    {
        if (req == null) {
            throw new NullPointerException("ExportRequest cannot be null");
        }
        if (req.title == null) {
            throw new NullPointerException("ExportRequest.title cannot be null");
        }
        if (req.body == null) {
            throw new NullPointerException("ExportRequest.body cannot be null");
        }

        return doExport(req);
    }

    protected abstract ExportResult doExport(ExportRequest req);

    // Ye keywrod kyu hai ? 
    // Only subclasses can see this method. External users cannot call it.
}