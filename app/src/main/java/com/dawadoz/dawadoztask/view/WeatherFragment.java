package com.dawadoz.dawadoztask.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dawadoz.dawadoztask.R;
import com.dawadoz.dawadoztask.model.entity.Weather;
import java.util.ArrayList;

public class WeatherFragment extends Fragment
{
	RecyclerView list;
	WeathersAdapter adapter;
	private ArrayList<Weather> weathers = new ArrayList<>();
	public static final String tag = "WeatherFragment";

	public static WeatherFragment newInstance(ArrayList<Weather> weathers)
	{
		WeatherFragment instance = new WeatherFragment();
		Bundle bundle = new Bundle();
		instance.setArguments(bundle);
		instance.weathers = weathers;
		return instance;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_weather, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
	{
		initView(view);
	}

	private void initView(View view)
	{
		list = view.findViewById(R.id.weathers);
		list.setHasFixedSize(true);
		list.setLayoutManager(new LinearLayoutManager(getActivity()));
		adapter = new WeathersAdapter(getActivity());
		list.setAdapter(adapter);
		adapter.setData(weathers);
	}
}