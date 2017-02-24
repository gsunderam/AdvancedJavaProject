package examples;

import static log.Logger.stdout;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadTest {
	public static void main(String[] args) {
		stdout(Runtime.getRuntime().availableProcessors());
		stdout(Integer.parseInt("00117"));
		String nullstr= null;
		stdout((String)nullstr);

    //Use URI instead od new URL as URL designed in 1993 has its equals and hashcode impls broken
    URI uri = URI.create("http://www.google.com");
    stdout("Host is " + uri.getHost());

		String s = "Test";
		Object o = new Object();
		o = s;
		stdout((String)o);
		//stdout(Integer.parseInt("500.00")); //NFE, a runtime exception
		stdout(500 == 500.00);
		stdout(String.valueOf(Math.round(Math.random() * 1000)).getBytes());
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		try {
			Date dt = simpledateformat.parse("2012-07-27 10:38:20.000216");
			Date sys = sf.parse("2012-07-27 10:38:20.289");
			stdout("Millis TMS Time: " + dt.getTime() + " Sys time " + sys.getTime());
			stdout(dt.getTime() + 4000 - sys.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
