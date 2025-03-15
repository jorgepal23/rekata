package utilities;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.logging.Logger;

public class EnvironmentValuesTask {
    private Logger logger = Logger.getLogger("MyLogs");
    private String github = "GITHUB_ACTIONS";

    private Dotenv dotenv;

    public EnvironmentValuesTask() {
        if (System.getenv(github) == null) {
            dotenv = Dotenv.configure().load();
            logger.info(dotenv.toString());
        }
    }

    public String getEnv(String key) {
        if (System.getenv(github) == null) {
            return dotenv.get(key);
        } else {
            return System.getenv(key);
        }
    }
}
