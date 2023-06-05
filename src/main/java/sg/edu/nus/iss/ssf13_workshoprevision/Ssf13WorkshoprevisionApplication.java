package sg.edu.nus.iss.ssf13_workshoprevision;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ssf13WorkshoprevisionApplication {

    public static void main(String[] args) {
		
		//instantiate
		SpringApplication app = new SpringApplication(Ssf13WorkshoprevisionApplication.class);
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);

		List<String> opsValues = appArgs.getOptionValues("dataDir");
		String dataDir;

		//if --dataDir argument is not specified, print error message and stop the program
		if (opsValues == null || opsValues.get(0) == null) {
			System.out.println("args[0]: directory");
			System.exit(1);
		}

		//map the argument --dataDir to 
		dataDir = opsValues.get(0);

		//set default environment properties
		app.setDefaultProperties(Collections.singletonMap("data.dir", dataDir));

		// create new directory
		File newDir = new File(dataDir);

        if (!newDir.exists())
            newDir.mkdirs();

		app.run(args);
	}
}
