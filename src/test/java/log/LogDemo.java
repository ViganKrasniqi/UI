package log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is used to setup logs
 * Different methods print different messages
 */
public class LogDemo
{
    private static Logger Log = LogManager.getLogger(LogDemo.class.getName());

    // This is to print log for the beginning of the test case.
    public static void startTestCase()
    {
        Log.info("========================================================================================");
        Log.info("========================================================================================");
        Log.info("--------------------------       START OF TEST CASE        -----------------------------");
        Log.info("========================================================================================");
        Log.info("========================================================================================");
    }

    //This is to print log for the ending of the test case
    public static void endTestCase()
    {
        Log.info("========================================================================================");
        Log.info("========================================================================================");
        Log.info("--------------------------       END OF TEST CASE        -------------------------------");
        Log.info("========================================================================================");
        Log.info("========================================================================================");
    }

    public static void info(String message)
    {
        Log.info("--------------------------          " + message);
    }

    public static void info(int message)
    {
        Log.info("--------------------------          " + message);
    }

    public static void warn(String message)
    {
        Log.warn("**************************          " + message.toUpperCase());
    }

    public static void error(String message)
    {
        Log.error("//////////////////////////          " + message + "          /////////////////////////////////");
    }

    public static void fatal(String message)
    {
        Log.fatal("          " + message);
    }

    public static void debug(String message)
    {
        Log.debug("          " + message);
    }
}
