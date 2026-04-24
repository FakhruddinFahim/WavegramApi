package com.fakhruddin.mtproto.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_DODGER_BLUE = "\033[94m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\033[93m"; //\033[38;5;226m
  public static final String ANSI_DEEP_BLUE = "\033[94m";

  public enum LogType {
    NONE,
    DEBUG,
    INFO,
    WARN,
    ERR
  }

  public enum LogOutput {
    NONE(0),
    CONSOLE(1 << 1),
    FILE(1 << 2);

    private final int value;

    LogOutput(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
  }

  public LogOutput logOutput = LogOutput.CONSOLE;
  OutputStream outputStream;

  public static Logger logger;

  public Logger(OutputStream outputStream) {
    this.outputStream = outputStream;
  }

  public void log(LogType logType, String message) {
    log(logType, null, message, Thread.currentThread().getStackTrace());
  }

  public void log(LogType logType, String color, String message) {
    log(logType, color, message, Thread.currentThread().getStackTrace());
  }

  public void log(LogType logType, String color, String message, StackTraceElement[] traceElements) {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
    String date = now.format(formatter);
    String str = null;
    if ((logOutput.getValue() | LogOutput.CONSOLE.getValue()) != 0) {
      if (traceElements == null) {
        traceElements = Thread.currentThread().getStackTrace();
      }
      StackTraceElement stackTraceElement = traceElements[2];
      String location = stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName();
      if (logType == LogType.DEBUG) {
        str = date + " [DEBUG] " + location + " " + message;
        if (color == null) {
          System.out.print(str);
        } else {
          System.out.print(color + str + ANSI_RESET);
        }
      } else if (logType == LogType.INFO) {
        str = date + " [INFO] " + location + " " + message;
        System.out.print(ANSI_DEEP_BLUE + str + ANSI_RESET);
      } else if (logType == LogType.WARN) {
        str = date + " [WARN] " + location + " " + message;
        System.out.print(ANSI_YELLOW + str + ANSI_RESET);
      } else if (logType == LogType.ERR) {
        str = date + " [ERR] " + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() +
          " " + message;
        System.out.print(ANSI_RED + str + ANSI_RESET);
      }
    }

    if ((logOutput.getValue() | LogOutput.FILE.getValue()) != 0) {
      try {
        assert str != null;
        outputStream.write(str.getBytes());
      } catch (IOException ignore) {
      }
    }
  }

  public void logd(String message) {
    log(LogType.DEBUG, null, message, Thread.currentThread().getStackTrace());
  }

  public void logi(String message) {
    log(LogType.INFO, null, message, Thread.currentThread().getStackTrace());
  }

  public void logw(String message) {
    log(LogType.WARN, null, message, Thread.currentThread().getStackTrace());
  }

  public void loge(String message) {
    log(LogType.ERR, null, message, Thread.currentThread().getStackTrace());
  }
}
