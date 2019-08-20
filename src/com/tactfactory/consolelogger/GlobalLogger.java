package com.tactfactory.consolelogger;

/**
 * ConsoleLogger logger1 = new ConsoleLogger(Options.DEBUG);
 * logger1.Log("coucou", Options.DEBUG); logger1.Log("coucou", Options.ERROR);
 * logger1.Log("coucou", Options.RELEASE); logger1.Log("coucou",
 * Options.WARNING);
 *
 * System.out.println("-----------------");
 *
 * ConsoleLogger logger2 = new ConsoleLogger(Options.ERROR);
 * logger2.Log("coucou", Options.DEBUG); logger2.Log("coucou", Options.ERROR);
 * logger2.Log("coucou", Options.RELEASE); logger2.Log("coucou",
 * Options.WARNING);
 *
 * System.out.println("-----------------");
 *
 * ConsoleLogger logger3 = new ConsoleLogger(Options.WARNING);
 * logger3.Log("coucou", Options.DEBUG); logger3.Log("coucou", Options.ERROR);
 * logger3.Log("coucou", Options.RELEASE); logger3.Log("coucou",
 * Options.WARNING);
 *
 * System.out.println("-----------------");
 *
 * ConsoleLogger logger4 = new ConsoleLogger(Options.RELEASE);
 * logger4.Log("coucou", Options.DEBUG); logger4.Log("coucou", Options.ERROR);
 * logger4.Log("coucou", Options.RELEASE); logger4.Log("coucou",
 * Options.WARNING);
 *
 * System.out.println("-----------------");
 *
 * ConsoleLogger logger5 = new ConsoleLogger(Options.RELEASE);
 * logger5.Log("coucou", Options.DEBUG); logger5.Log("coucou", Options.ERROR);
 * logger5.Log("coucou", Options.RELEASE); logger5.Log("coucou",
 * Options.WARNING);
 *
 * System.out.println("////////////////////");
 * logger5.changeOption(Options.DEBUG);
 *
 * logger5.Log("coucou", Options.DEBUG); logger5.Log("coucou", Options.ERROR);
 * logger5.Log("coucou", Options.RELEASE); logger5.Log("coucou",
 * Options.WARNING);
 *
 * logger5.Log("coucou", Options.DEBUG, true); logger5.Log("coucou",
 * Options.ERROR, true); logger5.Log("coucou", Options.RELEASE, true);
 * logger5.Log("coucou", Options.WARNING, true);
 *
 * @author antoine.cronier
 *
 */
public class GlobalLogger {

    private static ConsoleLogger consoleLogger;

    private GlobalLogger() {
        consoleLogger = new ConsoleLogger("Application Logger", Options.DEBUG);
    }

    private static GlobalLogger INSTANCE = null;

    public static ConsoleLogger getConsoleLogger() {
        if (INSTANCE == null) {
            synchronized (GlobalLogger.class) {
                if (INSTANCE == null) {
                    INSTANCE = new GlobalLogger();
                }
            }
        }
        return consoleLogger;
    }
}
