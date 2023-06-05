package sg.edu.nus.iss.ssf13_workshoprevision;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ssf13WorkshoprevisionApplication {

    public static void main(String[] args) {
		
		SpringApplication app = new SpringApplication(Ssf13WorkshoprevisionApplication.class);
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);

		List<String> opsValues = appArgs.getOptionValues("dataDir");
		String dataDir;

		if (opsValues == null || opsValues.get(0) == null) {
			System.out.println("args[0]: directory");
			System.exit(0);
		}

		dataDir = opsValues.get(0);

		app.setDefaultProperties(Collections.singletonMap("data.dir", dataDir));

		File newDir = new File(dataDir);

		if (!newDir.exists())
			newDir.mkdirs();

		app.run(args);
	}
}
