package log;

import static java.lang.System.out;


/**
 * Created by IntelliJ IDEA.
 * User: gsunderam
 * Date: Nov 4, 2010
 * An abstraction over java util logger
 */
public final class Logger {

    private static final java.util.logging.Logger log = java.util.logging.Logger.getAnonymousLogger();

    public static void log(String name, Object o) {
       java.util.logging.Logger logger = java.util.logging.Logger.getLogger(name);
       logger.info(o.toString());
    }

    public static void log(Object o) {
       log.info(o.toString());
    }

    public static void stdout(Object o) {
       if (o != null ) out.println(o.toString());
       else out.println(o);
    }

    public static void print(Object o) {
       if (o != null ) out.print(o.toString());
       else out.print(o);
    }


    
}
