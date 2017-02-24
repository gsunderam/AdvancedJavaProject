package svcloader;

import java.io.File;
import java.io.IOException;
import java.util.ServiceLoader;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 12, 2015
 *
 * Client code is a lot simpler with the service
 */
public class Servant {
    public static void main(String[] args) throws IOException {
				PersonalAssistantService service = PersonalAssistantService.getInstance();

        /** Clients just need to know the command they want processed */
			  if (service.can("fetch tea")) stdout(service.process(null));
    }

}
