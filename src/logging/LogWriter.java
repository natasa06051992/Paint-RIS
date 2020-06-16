package logging;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.*;

public class LogWriter {
    static private FileHandler fileHandler;
    static private SimpleFormatter formatterTxt;
    static private final DateFormat sdf = new SimpleDateFormat("HH-mm-ss dd.MM.yyyy");

    static public void setup() throws IOException {
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        Logger rootLogger = Logger.getLogger("");
        java.util.logging.Handler[] handlers = rootLogger.getHandlers();
        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }

        logger.setLevel(Level.FINE);
        Calendar cal = Calendar.getInstance();
        String dateString=sdf.format(cal.getTime());

        fileHandler = new FileHandler("Log " + dateString +".log");

        //System.setProperty("java.util.logging.SimpleFormatter.format", "%2$s: %5$s%6$s%n");
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");
        formatterTxt = new SimpleFormatter();

        fileHandler.setFormatter(formatterTxt);
        logger.addHandler(fileHandler);
    }
}
