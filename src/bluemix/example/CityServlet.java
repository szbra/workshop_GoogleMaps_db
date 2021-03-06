package bluemix.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Path("/city")
public class CityServlet {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get() throws Exception {
		// BEGIN Comment to demo DB service
		JsonObject city = new JsonObject();
		city.addProperty("name", "Bialystok");
		city.addProperty("latitude", 53.1325);
		city.addProperty("longitude", 23.1688);

		JsonArray cities = new JsonArray();
		cities.add(city);
		// END Comment to demo DB service

		// BEGIN Unomment to demo DB service
		// JsonArray cities = new DatabaseManager().getCities();
		// Remember to update credentials in DatabaseManager!
		// END Unomment to demo DB service

		String responseBody = cities.toString();
		return Response.ok(responseBody).build();
	}
}
