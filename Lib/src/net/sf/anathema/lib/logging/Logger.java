package net.sf.anathema.lib.logging;

import com.google.common.base.Strings;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class Logger {

  private static final String DEBUG_PREFIX = "[DEBUG] "; //$NON-NLS-1$
  private static final String ERROR_PREFIX = "[ERROR] "; //$NON-NLS-1$
  private static final String WARN_PREFIX = "[WARN] "; //$NON-NLS-1$
  private static final String INFO_PREFIX = "[INFO] "; //$NON-NLS-1$
  private static final Map<Class< ? >, Logger> loggers = new HashMap<Class< ? >, Logger>();

  public synchronized static Logger getLogger(Class< ? > logClass) {
    if (loggers.containsKey(logClass)) {
      return loggers.get(logClass);
    }
    Logger logger = new Logger();
    loggers.put(logClass, logger);
    return logger;
  }

  private final PrintStream printStream = System.err;

  private Logger() {
    // nothing to do
  }

  public void debug(String message, Throwable throwable) {
    printMessage(DEBUG_PREFIX, message);
    printThrowable(DEBUG_PREFIX, throwable);
  }

  public void debug(Throwable throwable) {
    debug(null, throwable);
  }

  public void error(String message, Throwable exception) {
    printMessage(ERROR_PREFIX, message);
    printThrowable(ERROR_PREFIX, exception);
  }

  public void error(Throwable throwable) {
    error(null, throwable);
  }

  public void info(String message) {
    printMessage(INFO_PREFIX, message);
  }

  private void printMessage(String prefix, String message) {
    if (!Strings.isNullOrEmpty(message)) {
      printStream.println(prefix + message);
    }
  }

  private void printThrowable(String prefix, Throwable throwable) {
    if (throwable == null) {
      return;
    }
    printStream.print(prefix);
    throwable.printStackTrace(printStream);
  }

  public void warn(String message) {
    printMessage(WARN_PREFIX, message);
  }

  public void warn(String message, Throwable exception) {
    printMessage(WARN_PREFIX, message);
    printThrowable(WARN_PREFIX, exception);
  }
}