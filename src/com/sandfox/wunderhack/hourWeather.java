package com.sandfox.wunderhack;

import java.util.Calendar;

public class hourWeather {
        private Calendar dateTime;
        private String condition;
        public hourWeather(Calendar c, String condition) {
                dateTime = c;
                this.condition = condition;

        }

        public int getYear() {
                return dateTime.get(Calendar.YEAR);
        }
        public int getDay() {
                return dateTime.get(Calendar.DATE);
        }
        public int getHour() {
                return dateTime.get(Calendar.HOUR);
        }
        public String getCondition() {
                return condition;
        }
        public int getAMPM() {
                return dateTime.get(Calendar.AM_PM);
        }

        //getters go here
}