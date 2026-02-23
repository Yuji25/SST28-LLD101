import java.nio.charset.StandardCharsets;

public class JsonExporter extends Exporter 
{
    @Override
    protected ExportResult doExport(ExportRequest req) 
    {
        String json = "{\"title\":" + escapeJson(req.title) + 
                     ",\"body\":" + escapeJson(req.body) + "}";
        return new ExportResult("application/json", json.getBytes(StandardCharsets.UTF_8));
    }

    private String escapeJson(String s) 
    {
        if (s == null || s.isEmpty()) 
        {
            return "\"\"";
        }
        
        String escaped = s
            .replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n")
            .replace("\r", "\\r")
            .replace("\t", "\\t");
        
        return "\"" + escaped + "\"";
    }
}