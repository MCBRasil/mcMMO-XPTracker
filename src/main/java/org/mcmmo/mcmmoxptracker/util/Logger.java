package org.mcmmo.mcmmoxptracker.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.mcmmo.mcmmoxptracker.mcMMOXPTracker;

public class Logger {
    private static Logger instance;
    private static final mcMMOXPTracker plugin = mcMMOXPTracker.p;
    private File file;
    private String fileName;
    private PrintWriter out;

    private Logger() {
        fileName = "experience.log";
        file = new File(plugin.getDataFolder(), fileName);
        loadFile();
    }

    void loadFile() {
        if (!file.exists()) {
            plugin.debug("Creating mcMMOXPTracker " + fileName + " File...");

            try {
                file.createNewFile();
            }
            catch (IOException e) {
                plugin.debug("Could not create file!");
            }
        }
        else {
            plugin.debug("Loading mcMMOXPTracker " + fileName + " File...");
        }

        try {
            out = new PrintWriter(new FileWriter(file, true));
        }
        catch (IOException e) {
            plugin.debug("Errors");
        }
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }

        return instance;
    }

    public void log(String message) {
        out.println(getTimeStamp() + " [INFO] " + message);
        out.flush();
    }

    public void close() {
        out.close();
    }

    public String getDateStamp() {
        GregorianCalendar date = new GregorianCalendar();
        int day = date.get(Calendar.DAY_OF_MONTH);
        int month = date.get(Calendar.MONTH);
        int year = date.get(Calendar.YEAR);

        return (day + "-" + month + "-" + year);
    }

    public String getTimeStamp() {
        GregorianCalendar date = new GregorianCalendar();
        int second = date.get(Calendar.SECOND);
        int minute = date.get(Calendar.MINUTE);
        int hour = date.get(Calendar.HOUR);

        return (hour + ":" + minute + ":" + second);
    }
}
