package utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Settings;

public class LoadConfig {

	private static String adresse;
	private static String login;
	private static String pass;
	private static String port;
	private static String route;
	
    public static Settings loadSettings(){

		String home =  System.getProperty("user.home");
		File settings_file = new File(home, "seafile.conf");
		
        FileReader fr = null;
    	
		try {
			fr = new FileReader(settings_file);

	    	BufferedReader br = new BufferedReader(fr);
	    	
	    	String s = br.readLine();
			
	    	while(s != null){
	    		
	    		if (s.startsWith("#") || s.trim().equals("")){
	    		}
	
	    		else {
	    		
		    		String key = s.split("=")[0].trim();
		    		String value = s.split("=")[1].trim();
		    		
		    		switch (key){
		    		
		    		case "adresse" :adresse = value; 
		    			            Settings.setAdresse(value);
		    		                break;
		    		               
		    		case "port"    :port = value; 
		    			            Settings.setPort(value);
	                                break;
		    		case "login"   :login = value; 
		    			            Settings.setLogin(value);
		    		                break;
		    		case "route"    :route = value; 
		                            Settings.setRoute(value);
		                            break;
		    		case "pass"    :pass = value; 
		    			            Settings.setPass(value);
		    			            break;
	
		    		}
	    		}

				s = br.readLine();
	    	}
//	    	
//	    	System.out.println(adresse);
//	    	System.out.println(login);
//	    	System.out.println(pass);
//	    	System.out.println(port);
//	    	System.out.println(route);
	    	
	    	
	    	Settings settings = new Settings(adresse, login, pass, port, route);
			fr.close();
			
		} catch (IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		
		return null;
		
	}

}