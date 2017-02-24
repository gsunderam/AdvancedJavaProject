package serial;

import java.io.*;
import java.util.Date;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Sep 10, 2013
 */
public class MutablePeriod implements Serializable {
  Date startDate;
  Date endDate;

  public MutablePeriod(Date startDate, Date endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public Date getStartDate() {
    return (Date) startDate.clone();
  }

  public Date getEndDate() {
    return (Date) endDate.clone();
  }

  private void writeObject(ObjectOutputStream oos) throws IOException {
     stdout("Serializing...");
     oos.defaultWriteObject();
     oos.writeObject(startDate);
     oos.writeObject(endDate);    
  }

  private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
      stdout("Deserializing");
      ois.defaultReadObject();

      startDate = new Date(startDate.getTime());
      endDate = new Date(endDate.getTime());

      if (startDate.compareTo(endDate) > 0)
        throw new InvalidObjectException(startDate + " is greater than " + endDate);
  }
}
