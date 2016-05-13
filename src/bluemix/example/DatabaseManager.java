package bluemix.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DatabaseManager {

	private String url = "jdbc:postgresql://jumbo.db.elephantsql.com:5432/???";
	private String username = "???";
	private String password = "???";

	public JsonArray getCities() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		Connection db = DriverManager.getConnection(url, username, password);
		Statement st = null;
		ResultSet rs = null;
		JsonArray cities = new JsonArray();
		try {
			st = db.createStatement();
			rs = st.executeQuery("select name, latitude, longitude from city");
			while (rs.next()) {
				JsonObject city = new JsonObject();
				city.addProperty("name", rs.getString("name"));
				city.addProperty("latitude", rs.getFloat("latitude"));
				city.addProperty("longitude", rs.getFloat("longitude"));
				cities.add(city);
			}
		} finally {
			if (st != null) {
				st.close();
			}
			if (rs != null) {
				rs.close();
			}
			db.close();
		}

		return cities;
	}
}
