package sk.itsovy.kaufland.API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class RequestCaller {

	public double getUdsRate() throws IOException, ParseException {
		URL url = new URL("https://api.exchangeratesapi.io/latest");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			    content.append(inputLine);
		}
		in.close();
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(content.toString());
		JSONObject rates = (JSONObject) json.get("rates");
		return (double) rates.get("USD");
	}

}
