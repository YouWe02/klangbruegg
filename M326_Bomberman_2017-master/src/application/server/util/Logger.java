package application.server.util;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Logger class, class used for logging strings into System.out with a prefix and timestamp
 *
 * @author Tobias Maier <tma109970@iet-gibb.ch>
 */
public class Logger {
    private static Logger instance;
    private final static String logFilePath = "src/application/server/log.txt";
    private FileWriter fileWriter;

    /**
     * Creates Logger instance with a filewriter set to a specified path
     */
    private Logger() {
        try{
            fileWriter = new FileWriter(Logger.logFilePath, true);
            StringBuilder builder = new StringBuilder();
            builder.append("------------------------------------------------------------------------------------------------------------------------\n");
            builder.append(String.format("| %-5s | %-10s | %s", "NEW", getTimestamp(), "New Logger Instance"));
            fileWriter.write(builder.toString() + "\n");
            fileWriter.flush();
        } catch (
                IOException e) {
            //exception handling left as an exercise for the reader
            e.printStackTrace();
        }

    }

    /**
     * Singleton getInstance logic
     * @return instance of Logger Singleton
     */
    public static Logger getInstance() {
        /**
         * Code from: https://www.geeksforgeeks.org/singleton-design-pattern/
         */
        if (instance == null) {
            // To make thread safe
            synchronized (Logger.class) {
                // check again as multiple threads
                // can reach above step
                if (instance == null)
                    instance = new Logger();
            }
        }
        return instance;
    }

    /**
     * Log a String with info prefix
     *
     * @param str
     */
    public void info(String str) {
        log("INFO", str);
    }

    /**
     * Log a String with debug prefix
     *
     * @param str
     */
    public void debug(String str) {
        log("DEBUG", str);
    }

    /**
     * Log a String with error prefix
     *
     * @param str
     */
    public void error(String str) {
        log("ERROR", str);
    }

    /**
     * Print a prefix, timestamp and string in a
     *
     * @param prefix for the string
     * @param str    string to be logged
     */
    private void log(String prefix, String str) {
        String output = String.format("| %-5s | %-10s | %s", prefix, getTimestamp(), str);
        System.out.println(output);
        try {
            fileWriter.write(output + "\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get timestamp in format HH:mm:ss.SSS
     *
     * @return timestamp
     */
    private String getTimestamp() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
        return dateFormat.format(date);
    }

}
