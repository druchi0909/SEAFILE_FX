package models;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Settings {
	
	private static String adresse;
	private static String login;
	private static String pass;
	private static String port;
	private static String route;
	private static String token;
	
	private final String USER_AGENT = "Mozilla/5.0";
	
	public Settings(){
		this(null, null, null, null, null);
	}
		
	public Settings(String adresse, String login, String pass, String port, String route) {
		Settings.adresse = adresse;
		Settings.login = login;
		Settings.pass = pass;
		Settings.route = route;
		Settings.port = port;
		Settings.token = generateToken();
	}

	public static String getAdresse() {
		return adresse;
	}

	public static void setAdresse(String adresse) {
		Settings.adresse = adresse;
	}

	public static String getLogin() {
		return login;
	}

	public static void setLogin(String login) {
		Settings.login = login;
	}

	public static String getPass() {
		return pass;
	}

	public static void setPass(String pass) {
		Settings.pass = pass;
	}

	public static String getPort() {
		return port;
	}

	public static void setPort(String port) {
		Settings.port = port;
	}

	public static String getToken() {
		return token;
	}

	public static void setToken(String token) {
		Settings.token = token;
	}

	public static String getRoute() {
		return route;
	}

	public static void setRoute(String route) {
		Settings.route = route;
	}
	
	private String generateToken(){
		
		HttpClient client = HttpClientBuilder.create().build();
		
		String stringRequest = String.format("%s:%s/%s", adresse, port , route);
		
		HttpPost request = new HttpPost(stringRequest);
		request.setHeader("User-Agent", USER_AGENT);

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("username", login));
		urlParameters.add(new BasicNameValuePair("password", pass));
		
		try {
			request.setEntity(new UrlEncodedFormEntity(urlParameters));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ObjectMapper mapper = new ObjectMapper();

		
		
		try {
			HttpResponse response = client.execute(request);
			BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
            String reponse = rd.readLine();

            JsonNode root = mapper.readTree(reponse);
            token = root.path("token").asText();
            
            System.out.println("token = " + token);
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;	
	}

}
