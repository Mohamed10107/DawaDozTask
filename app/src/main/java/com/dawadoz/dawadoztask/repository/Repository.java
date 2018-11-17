package com.dawadoz.dawadoztask.repository;

import android.content.Context;
import com.dawadoz.dawadoztask.database.DataBase;
import com.dawadoz.dawadoztask.model.entity.Weather;
import com.dawadoz.dawadoztask.view.MainActivity;
import com.dawadoz.dawadoztask.webservice.Response;
import com.dawadoz.dawadoztask.webservice.Webservices;
import java.util.ArrayList;
import java.util.HashMap;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository
{
	private Webservices webservices;
	private HashMap<String, ArrayList<Weather>> weathers = new HashMap<>();

	// region singleton implementation
	private static class Loader
	{
		static Repository INSTANCE = new Repository();
	}

	public static Repository getInstance()
	{
		return Loader.INSTANCE;
	}

	private Repository()
	{
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

		String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/";
		Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
		webservices = retrofit.create(Webservices.class);
	}
	// endregion

	public Single<ArrayList<Weather>> getWeather(Context context, String city)
	{
		return Single.create(emitter -> {
			if (MainActivity.connected)
			{
				try
				{
					getWeatherFromAPI(context, city, emitter);
				}
				catch (Exception ex)
				{
					getWeatherFromLocal(context, city, emitter);
				}
			}
			else
			{
				getWeatherFromLocal(context, city, emitter);
			}
		});
	}

	private void getWeatherFromAPI(Context context, String city, SingleEmitter<ArrayList<Weather>> emitter)
	{
		webservices.getWeather(city, "5", "metric", "92c8fc9f16eeee3a87ce33bffc3d939a").subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).map(response -> mapResponse(response, city)).subscribe(new SingleObserver<ArrayList<Weather>>()
		{
			@Override
			public void onSubscribe(Disposable d)
			{

			}

			@Override
			public void onSuccess(ArrayList<Weather> weather)
			{
				weathers.put(city, weather);
				DataBase.getInstance(context).weatherDAO().delete(city);
				DataBase.getInstance(context).weatherDAO().insert(weather);
				emitter.onSuccess(weather);
			}

			@Override
			public void onError(Throwable ex)
			{
				ex.printStackTrace();
				getWeatherFromLocal(context, city, emitter);
			}
		});
	}

	private void getWeatherFromLocal(Context context, String city, SingleEmitter<ArrayList<Weather>> emitter)
	{
		try
		{
			if (weathers != null && weathers.containsKey(city))
			{
				emitter.onSuccess(weathers.get(city));
			}
			else
			{
				ArrayList<Weather> weather = new ArrayList<>(DataBase.getInstance(context).weatherDAO().getWeathers(city));
				if (!weather.isEmpty())
				{
					weathers.put(city, weather);
				}
				emitter.onSuccess(weather);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			emitter.onError(ex);
		}
	}

	private ArrayList<Weather> mapResponse(Response response, String city)
	{
		ArrayList<Weather> weathers = new ArrayList<>();
		if (response.list != null && !response.list.isEmpty())
		{
			for (int i = 0; i < response.list.size(); i++)
			{
				Weather weather = new Weather();
				weather.city = city;
				weather.date = response.list.get(i).dt;
				weather.pressure = response.list.get(i).pressure;
				weather.humidity = response.list.get(i).humidity;
				weather.speed = response.list.get(i).speed;
				weather.degree = response.list.get(i).deg;
				weather.clouds = response.list.get(i).clouds;
				weather.minTemperature = response.list.get(i).temp.min;
				weather.maxTemperature = response.list.get(i).temp.max;
				weather.dayTemperature = response.list.get(i).temp.day;
				weather.nightTemperature = response.list.get(i).temp.night;
				weather.eveningTemperature = response.list.get(i).temp.eve;
				weather.morningTemperature = response.list.get(i).temp.morn;
				weather.main = response.list.get(i).weather.get(0).main;
				weather.description = response.list.get(i).weather.get(0).description;
				weather.icon = "http://openweathermap.org/img/w/" + response.list.get(i).weather.get(0).icon + ".png";
				weathers.add(weather);
			}
		}
		return weathers;
	}
}
