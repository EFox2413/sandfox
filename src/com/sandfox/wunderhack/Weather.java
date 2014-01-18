
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class Weather {
    public static void main(String[] args) throws Exception {

        WeatherReader wr = new WeatherReader("462b49facf06d4b1");
        
        System.out.println(wr.cityState(42.3314,-83.0458));

        /*ArrayList<HourWeather> hwList = wr.parsedHourly10Day("mi","detroit");
        
        for(HourWeather hw: hwList) {
            //if(hw.getCondition().equals("Clear")) {
                System.out.println(hw.getCondition() + " " + hw.getHour() + " " + hw.getAMPM() + " " + hw.getDay());
            //}
        }*/


    }
}

