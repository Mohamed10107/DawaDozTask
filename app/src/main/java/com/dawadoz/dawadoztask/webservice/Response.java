package com.dawadoz.dawadoztask.webservice;

import java.util.ArrayList;

public class Response
{
	public ArrayList<WeatherModel> list = new ArrayList<>();

	public static class WeatherModel
	{
		public String dt;
		public Temperature temp = new Temperature();
		public String pressure;
		public String humidity;
		public ArrayList<Weather> weather = new ArrayList<>();
		public String speed;
		public String deg;
		public String clouds;
	}

	public static class Temperature
	{
		public String day;
		public String min;
		public String max;
		public String night;
		public String eve;
		public String morn;
	}

	public static class Weather
	{
		public String id;
		public String main;
		public String description;
		public String icon;
	}
}
