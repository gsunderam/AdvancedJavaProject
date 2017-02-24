package stax;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileReader;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 8, 2015
 */
public class Targets {
	public static void main(String[] args) throws Exception {
				stdout(System.getProperty("user.dir")); //Gets the base directory the JVM is looking at.
        for (String arg : args) {
            XMLEventReader xsr = XMLInputFactory.newInstance().createXMLEventReader(new FileReader(arg));
            while (xsr.hasNext()) {
                XMLEvent evt = xsr.nextEvent();
                switch (evt.getEventType()) {
                    case XMLEvent.START_ELEMENT: {
                        StartElement se = evt.asStartElement();
                        if (se.getName().getLocalPart().equals("target")) {
                            Attribute targetName = se.getAttributeByName(new QName("name"));
                            // Found a target!
                            stdout(targetName.getValue());
                        }
                        break;
                    }
                    // Ignore everything else
                }
            }
        }
    }
}
