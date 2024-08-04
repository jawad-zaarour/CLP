package cli;

import org.junit.jupiter.api.Tag;

@Tag("cli")
public interface CliTests {

      String FILE_CSV = "src/test/resources/cookie_log.csv";
      String FILE_NOT_EXIST = "src/test/resources/no_exist_file.csv";
}
