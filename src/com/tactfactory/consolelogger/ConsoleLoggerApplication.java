package com.tactfactory.consolelogger;

public class ConsoleLoggerApplication {

    public static void main(String[] args) {

        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 2000000000; i++) {
                    GlobalLogger.getConsoleLogger().Log("debug data " + i, Options.DEBUG);
                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 2000000000; i++) {
                    GlobalLogger.getConsoleLogger().Log("error data " + i, Options.ERROR);
                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 2000000000; i++) {
                    GlobalLogger.getConsoleLogger().Log("release data " + i, Options.RELEASE);
                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 2000000000; i++) {
                    GlobalLogger.getConsoleLogger().Log("warning data " + i, Options.WARNING);
                }
            }
        }).start();
    }

}
