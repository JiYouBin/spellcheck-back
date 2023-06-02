package spellCheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@SpringBootApplication
@RestController
public class SpellCheckApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpellCheckApplication.class, args);
	}

	
	@PostMapping("/checker")
	public List<String> checker(@RequestBody Map<String, String> sentence) throws Exception {
		
		 final String command = "node C:\\Temp\\java\\eclipse-workspace\\spellcheck-back\\hanspell-0.9.7\\lib\\cli.js \"" + sentence.get("sentence") + "\"";
		
//		final String command = "node /my-app/hanspell-0.9.7/lib/cli.js \"" + sentence.get("sentence") + "\"";
//		 C:\Temp\java\eclipse-workspace\spellcheck-back
		System.out.println(">>>>>>>>>>>>> " + command);
		
		List<String> result = null;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(command);
			result = new BufferedReader(new InputStreamReader(process.getInputStream()))
					.lines()
					.toList();
			System.out.println(">>>>>>>>>>>>>>>>>> " + result);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

}
