package hello;

import org.springframework.web.bind.annotation.RestController;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
    
	@RequestMapping("/hello/{player:.+}")
	public Record message(@PathVariable String player) {//REST Endpoint.
		Record msg = pageSourceBased(player);
		return msg;
	}

	private Record pageSourceBased(String name) {
		URL url;
		InputStream is = null;
		BufferedReader br;
		String line;
		StringBuilder pageSource = new StringBuilder();
		try {
			
			String spec = "https://www.instagram.com/" + name + "/";
			System.out.println(spec);
			url = new URL(spec);
			is = url.openStream(); // throws an IOException
			br = new BufferedReader(new InputStreamReader(is));

			while ((line = br.readLine()) != null) {
//				System.out.println(line);
				pageSource.append(line);
			}
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException ioe) {
				// nothing to see here
			}
		}
		String result = pageSource.toString().split("end_cursor")[0];
		Record record = new Record(name, result);

		return record;
	}
    
}
