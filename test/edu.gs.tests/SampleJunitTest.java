package edu.gs.tests;

import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;

import java.io.File;

import static log.Logger.stdout;
import static org.junit.Assert.assertNotNull;

public class SampleJunitTest {

  private static final Logger logger = Logger.getLogger(SampleJunitTest.class);

  @Test
  public void firstTest() {
    try {
     DOMConfigurator.configure("./log4j/log4j.xml");
     String s = "success";
     assertNotNull(s);
     logger.info(s);
     //Logger.getLogger("fileAppender").info("GS Success on log4j");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
}
