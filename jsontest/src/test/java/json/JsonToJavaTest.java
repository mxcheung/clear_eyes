package json;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.maxcheung.domain.User;

public class JsonToJavaTest {

	@Test
	public void loadJsonTest() {
		ObjectMapper mapper = new ObjectMapper();

		try {

			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("json/user.json").getFile());
			System.out.println(file.getAbsolutePath());

			// read from file, convert it to user class
			User user = mapper.readValue(file, User.class);

			assertEquals("blah", user.getName());
			assertEquals(3, user.getMessages().size());
			assertEquals(29, user.getAge());

			// display to console
			System.out.println(user);

		} catch (JsonGenerationException e) {

			e.printStackTrace();

		} catch (JsonMappingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
