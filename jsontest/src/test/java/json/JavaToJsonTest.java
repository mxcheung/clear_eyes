package json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import com.maxcheung.domain.User;


public class JavaToJsonTest {

	String expected = "{\"age\":29,\"name\":\"blah\",\"messages\":[\"msg 1\",\"msg 2\",\"msg 3\"]}";

	@Test
	public void LoadJsonTest() throws JSONException {
		User user = new User();
		ObjectMapper mapper = new ObjectMapper();
			
		try {

			// convert user object to json string, and save to a file
		//	mapper.writeValue(new File("c:\\temp\\user.json"), user);

			// display to console
			System.out.println(mapper.writeValueAsString(user));
			String actual = mapper.writeValueAsString(user);
			
			JSONAssert.assertEquals(expected, actual, false);

		} catch (JsonGenerationException e) {

			e.printStackTrace();

		} catch (JsonMappingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

}
