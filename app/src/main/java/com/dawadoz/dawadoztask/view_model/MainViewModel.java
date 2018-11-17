package com.dawadoz.dawadoztask.view_model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import com.dawadoz.dawadoztask.model.entity.Weather;
import com.dawadoz.dawadoztask.repository.Repository;
import java.util.ArrayList;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel
{
	private MutableLiveData<ArrayList<Weather>> weather = new MutableLiveData<>();

	public MutableLiveData<ArrayList<Weather>> weather()
	{
		return weather;
	}

	public void getWeather(Context context, String city)
	{
		Repository.getInstance().getWeather(context, city).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new SingleObserver<ArrayList<Weather>>()
		{
			@Override
			public void onSubscribe(Disposable d)
			{

			}

			@Override
			public void onSuccess(ArrayList<Weather> weathers)
			{
				weather.postValue(weathers);
			}

			@Override
			public void onError(Throwable ex)
			{
				weather.postValue(new ArrayList<>());
			}
		});
	}
}
