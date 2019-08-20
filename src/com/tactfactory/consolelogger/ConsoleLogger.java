package com.tactfactory.consolelogger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class ConsoleLogger {

    private Options option;
    private String TAG;
    private long maxFileSize = 32000;
    private String folder = "./logs/";

    public Options getOption() {
        return option;
    }

    public void changeOption(Options option) {
        this.option = option;
    }

    public ConsoleLogger(Options option) {
        TAG = "Logger: ";
        this.option = option;
    }

    public ConsoleLogger(String tag, Options option) {
        TAG = tag + ": ";
        this.option = option;
    }

    public ConsoleLogger(String tag, Options option, long maxFileSize, String folder) {
        TAG = tag + ": ";
        this.option = option;
        this.maxFileSize = maxFileSize;
        this.folder = folder;
    }

    /**
     * Print in command line with color depending of Options select when object
     * created.
     *
     * @param data   String to println in sysout.
     * @param option Option to print with conditional increment RELEASE < WARNING <
     *               ERROR < DEBUG.
     */
    public void Log(String data, Options option) {
        switch (this.option) {
        case RELEASE:
            switch (option) {
            case RELEASE:
                LogForce(data, option);
            case WARNING:
            case ERROR:
            case DEBUG:
            }
            break;
        case WARNING:
            switch (option) {
            case RELEASE:
            case WARNING:
                LogForce(data, option);
            case ERROR:
            case DEBUG:
            }
            break;
        case ERROR:
            switch (option) {
            case RELEASE:
            case WARNING:
            case ERROR:
                LogForce(data, option);
            case DEBUG:
            }
            break;
        case DEBUG:
            switch (option) {
            case RELEASE:
            case WARNING:
            case ERROR:
            case DEBUG:
                LogForce(data, option);
            }
            break;
        }
    }

    public void Log(String data, Options option, boolean important) {
        switch (this.option) {
        case RELEASE:
            switch (option) {
            case RELEASE:
                LogForce(data, option, important);
            case WARNING:
            case ERROR:
            case DEBUG:
            }
            break;
        case WARNING:
            switch (option) {
            case RELEASE:
            case WARNING:
                LogForce(data, option, important);
            case ERROR:
            case DEBUG:
            }
            break;
        case ERROR:
            switch (option) {
            case RELEASE:
            case WARNING:
            case ERROR:
                LogForce(data, option, important);
            case DEBUG:
            }
            break;
        case DEBUG:
            switch (option) {
            case RELEASE:
            case WARNING:
            case ERROR:
            case DEBUG:
                LogForce(data, option, important);
            }
            break;
        }
    }

    /**
     * Print in command line with color.
     *
     * @param data   String to println in sysout.
     * @param option Option to print.
     */
    public void LogForce(String data, Options option) {
        LogForce(data, option, false);
    }

    /**
     * Print in command line with color.
     *
     * @param data      String to println in sysout.
     * @param option    Option to print.
     * @param important is data have to be important.
     */
    public void LogForce(String data, Options option, boolean important) {
        StringBuilder builder = new StringBuilder();

        switch (option) {
        case DEBUG:
            if (!important) {
                builder.append(ConsoleColors.BLUE + TAG);
            } else {
                builder.append(ConsoleColors.BLUE_BACKGROUND_BRIGHT + TAG);
            }

            break;
        case ERROR:
            if (!important) {
                builder.append(ConsoleColors.RED + TAG);
            } else {
                builder.append(ConsoleColors.RED_BACKGROUND_BRIGHT + TAG);
            }

            break;
        case RELEASE:
            if (!important) {
                builder.append(ConsoleColors.BLACK + TAG);
            } else {
                builder.append(ConsoleColors.BLACK_BACKGROUND_BRIGHT + TAG);
            }

            break;
        case WARNING:
            if (!important) {
                builder.append(ConsoleColors.YELLOW + TAG);
            } else {
                builder.append(ConsoleColors.YELLOW_BACKGROUND_BRIGHT + TAG);
            }

            break;
        }
        builder.append(data);
        builder.append(ConsoleColors.RESET);
        System.out.println(builder.toString());
        writeToFile(TAG + data, option.name());
    }

    private void writeToFile(String data, String file) {
        new File(this.folder).mkdirs();
        File tempFile = new File(this.folder + file);

        if (!tempFile.exists()) {
            try {
                File.createTempFile(this.folder + file, "");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int i = 0;
        while (tempFile.length() / 1024 > maxFileSize) {
            i++;
            tempFile = new File(this.folder + file + i);
        }

        if (!tempFile.exists()) {
            try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(tempFile.getAbsolutePath(), true),
                    StandardCharsets.UTF_8)) {
                writer.append(LocalDateTime.now().toString());
                writer.append("\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            };
        }

        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(tempFile.getAbsolutePath(), true),
                StandardCharsets.UTF_8)) {
            writer.append(data);
            writer.append("\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
    }
}
