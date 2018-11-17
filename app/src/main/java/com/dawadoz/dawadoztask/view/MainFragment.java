package com.dawadoz.dawadoztask.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.dawadoz.dawadoztask.R;
import com.dawadoz.dawadoztask.utils.Views;
import com.dawadoz.dawadoztask.view_model.MainViewModel;

public class MainFragment extends Fragment
{
	private MainViewModel viewModel;
	private Views.LoadingView loading;
	public static final String tag = "MainFragment";

	public static MainFragment newInstance()
	{
		MainFragment instance = new MainFragment();
		Bundle bundle = new Bundle();
		instance.setArguments(bundle);
		return instance;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		loading = new Views.LoadingView(getActivity());
		viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
		initListeners();
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
	{
		initView(view);
	}

	private void initView(View view)
	{
		Button cairo = view.findViewById(R.id.cairo);
		cairo.setOnClickListener(v -> getWeather(cairo.getTag().toString()));

		Button alex = view.findViewById(R.id.alex);
		alex.setOnClickListener(v -> getWeather(alex.getTag().toString()));

		Button giza = view.findViewById(R.id.giza);
		giza.setOnClickListener(v -> getWeather(giza.getTag().toString()));

		Button aswan = view.findViewById(R.id.aswan);
		aswan.setOnClickListener(v -> getWeather(aswan.getTag().toString()));

		Button luxor = view.findViewById(R.id.luxor);
		luxor.setOnClickListener(v -> getWeather(luxor.getTag().toString()));

		FloatingActionButton about = view.findViewById(R.id.about);
		about.setOnClickListener(v -> {
			if (getActivity() != null)
				new ContactUsDialog(getActivity()).show();
		});
	}

	private void initListeners()
	{
		viewModel.weather().observe(this, weathers -> {
			loading.dismiss();
			if (weathers != null && !weathers.isEmpty())
			{
				if (getActivity() != null)
					((MainActivity) getActivity()).addFragment(WeatherFragment.newInstance(weathers), WeatherFragment.tag);
			}
			else
			{
				Toast.makeText(getActivity(), R.string.error, Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void getWeather(String city)
	{
		loading.show();
		viewModel.getWeather(getActivity(), city);
	}
}
