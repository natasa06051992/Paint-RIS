package logging;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.*;
/* *
 * * The LogWriter class that set up file for logging
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public class LogWriter {
    static private FileHandler fileHandler;
    static private SimpleFormatter formatterTxt;
    static private final DateFormat sdf = new SimpleDateFormat("HH-mm-ss dd.MM.yyyy");

    /**
     * Setup file for logging
     * @throws IOException throw a failure in Input & Output operations
     */
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

        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");
        formatterTxt = new SimpleFormatter();

        fileHandler.setFormatter(formatterTxt);
        logger.addHandler(fileHandler);
    }
}
