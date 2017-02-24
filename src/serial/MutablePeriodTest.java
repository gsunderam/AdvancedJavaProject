package serial;

import java.io.*;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Sep 10, 2013
 */
public class MutablePeriodTest {
  public static void main(String[] args) throws IOException, ClassNotFoundException {
    DateFormat format = DateFormat.getDateInstance();
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, -11);
    Date start = new Date();
    Date end = cal.getTime();

    MutablePeriod period = new MutablePeriod(end, start);

    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("mutable.ser"));
    oos.writeObject(period);
    oos.close();

    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("mutable.ser"));
    MutablePeriod newPeriod = (MutablePeriod) ois.readObject();

    Date startDate = newPeriod.startDate;
    Date endDate = newPeriod.endDate;
    ois.close();

    stdout("Start: " + format.format(startDate) + " End: " + format.format(endDate));
  }
}
