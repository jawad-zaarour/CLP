package dev.profitisle.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * Command-line parameters for the CLP application.
 *
 * @author Jawad Zaarour
 */
@Command(
        name = "java -jar clp.jar",
        description = "Cookie Log Processor with configurable output"
)
public class CliParams {

    @Option(names = {"-f", "--file"}, required = true, description = "Path to the log file")
    private String filename;

    @Option(names = {"-d", "--date"}, required = true, description = "Date in format yyyy-MM-dd")
    private String date;

    @Option(names = {"-h", "--help"}, description = "Print usage help", usageHelp = true)
    private boolean usageHelpRequested;

    public String filename() {
        return filename;
    }

    public String date() {
        return date;
    }

    public boolean help() {
        return usageHelpRequested;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
