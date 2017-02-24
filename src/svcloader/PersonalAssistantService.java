package svcloader;

import java.io.File;
import java.util.ServiceLoader;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 13, 2015
 *
 * Decouples the client from service loading details which is boiler plate
 */
public class PersonalAssistantService {
	private static final PersonalAssistantService instance = new PersonalAssistantService();
	private ServiceLoader<IPersonalServant> loader;

	private PersonalAssistantService() {
	 loader = ServiceLoader.load(IPersonalServant.class);
	}

	public static PersonalAssistantService getInstance() {
		return instance;
	}

	public String process(File f) {
		return "Success";
	}

	public boolean can(String cmd) {
		stdout("Cmd is " + cmd);
		IPersonalServant servant = null;

		servant = getPersonalServant(servant, cmd);

		return true;
	}

	private IPersonalServant getPersonalServant(IPersonalServant servant, String cmd) {
		for (IPersonalServant personalServant : loader)
				if (personalServant.can(cmd))
						servant = personalServant;

		if (servant == null)
				throw new IllegalArgumentException("No suitable servant found");

		return servant;
	}
}
