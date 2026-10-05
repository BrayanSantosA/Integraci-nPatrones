package chain;

import java.util.ArrayList;
import java.util.List;

public class ReporteProceso {
    private final List<String> log = new ArrayList<>();
    private boolean critico;

    public void info(String m) { log.add("  [INFO]  " + m); }
    public void error(String m) { log.add("  [ERROR] " + m); critico = true; }
    public boolean isCritico() { return critico; }
    public List<String> getLog() { return log; }
}
