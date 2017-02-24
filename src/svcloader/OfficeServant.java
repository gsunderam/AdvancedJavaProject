package svcloader;

import java.io.File;
import java.io.IOException;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 12, 2015
 */
public class OfficeServant implements IPersonalServant {
	@Override
	public void process(File f) throws IOException {
		stdout("Success");
	}

	@Override
	public boolean can(String command) {
		stdout("command fetch");
		return true;
	}
}
