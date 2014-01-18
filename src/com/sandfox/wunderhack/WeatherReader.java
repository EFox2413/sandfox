import java.net.URL;
import java.net.URLConnection;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.lang.Exception;
import java.util.ArrayList;
import java.util.Calendar;
public class WeatherReader {
	private String key;
	public WeatherReader(String key) {
		this.key = key;
	}
	public WeatherReader() {
		this("462b49facf06d4b1");
	}

	public String getJSON(String url) {
		URL weatherURL;
		URLConnection uc;
		Scanner in;
		try {
			weatherURL = new URL(url);
	        uc = weatherURL.openConnection();
	        in = new Scanner(
	                                new InputStreamReader(
	                                uc.getInputStream()));
    	} catch(Exception e) {
    		System.out.println(e.getMessage());
    		System.out.println("Shits been done");
    		return "JSON ERROR 0x000hg&j4jf8_Uynfhs";
    	}

        String inputLine="";

        while (in.hasNext()) { 
            inputLine += in.nextLine() ;
        }
        in.close();
        
        return inputLine;

        //String[] x = inputLinse.plit(",");
        //for(int i = 0; i<x.length; i++) {
		//System.out.println(x[i]);
	}

	public String hourly10Day(String state, String city) {
		//http://api.wunderground.com/api/Your_Key/hourly10day/q/CA/San_Francisco.json
		String url = "http://api.wunderground.com/api/" + key;
		url += "/hourly10day/q/" + state + "/" + city + ".json"; 
		return getJSON(url);
	}

	public String cityState(double latitude, double longitude ) {
		String url = "http://api.wunderground.com/api/"+key+"/geolookup/q/" + latitude +","+longitude+".json";
		String response = getJSON(url);
		String city = getJSONField(response,"city");
		String state = getJSONField(response,"state");
		return city+":"+state;
	}


	public ArrayList<HourWeather> parsedHourly10Day(String state, String city) {
		ArrayList<HourWeather> listOfHours = new ArrayList<HourWeather>();
		String toBeParsed = hourly10Day(state,city);
		int length = toBeParsed.length();
		int i1 = toBeParsed.indexOf("FCTTIME",0);
		int i2 = 1;

		while ( i1 < length) {
			i2 =  toBeParsed.indexOf("FCTTIME",i1+1);
			if( i2 == -1) {
				i2 = length;
			}
			String onehour = toBeParsed.substring(i1,i2);
			Calendar dateTime = Calendar.getInstance();
			int year    = Integer.parseInt(getJSONField(onehour,"year"));
			int month   = Integer.parseInt(getJSONField(onehour,"mon"));
			int day     = Integer.parseInt(getJSONField(onehour,"mday"));
			int hour    = Integer.parseInt(getJSONField(onehour,"hour"));
			int minute  = Integer.parseInt(getJSONField(onehour,"min"));
			String condition =getJSONField(onehour,"condition");
			//System.out.println(hour + " " + minute + " " + day + " " + month + " " + year);			
			dateTime.set(year, month, day,  hour,  minute);
			listOfHours.add(new HourWeather(dateTime,condition));
			i1=i2;


		}

		return listOfHours;

	}

	private String getJSONField(String jsonText, String tag) {
		int tagi = jsonText.indexOf(tag);
		int semicolon = jsonText.indexOf(":",tagi);
		int q1 = jsonText.indexOf("\"",semicolon);
		int q2 = jsonText.indexOf("\"",q1+1);
		return jsonText.substring(q1+1,q2);

		}

}
