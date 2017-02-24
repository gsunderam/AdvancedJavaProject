package examples;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class JBossQueueGenerator {
	public static void main(String[] args) throws Exception {
		/* ------------------------------------------------------------------- */    
        /* You should do this ONLY ONCE in the whole application life-cycle:   */    
    
        /* Create and adjust the configuration */
        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(new File("templates"));
        cfg.setObjectWrapper(new DefaultObjectWrapper());

        /* ------------------------------------------------------------------- */    
        /* You usually do these for many times in the application life-cycle:  */    
                
        /* Get or create a template */
        Template temp = cfg.getTemplate("jbossQueueTemplate.ftl");

        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        String line = null;
        boolean first = true;

        while ((line = reader.readLine()) != null) {
            /* Create a data-model */
            Map<String, Object> root = new HashMap<String, Object>();
            root.put("queueName", line);
            root.put("jndiName", line);

            /* Merge data-model with template */
            Writer fos = new OutputStreamWriter(new FileOutputStream("gems.gems.s1", true));
            if (first) {
              fos.write("<!-- Gems Queues created via Freemarker tremplates by G Sunderam -->\n");
              first = false;
            }
            temp.process(root, fos); //Merge template with model data
            fos.append("\n\n");
            fos.flush();
        }
	}
}

