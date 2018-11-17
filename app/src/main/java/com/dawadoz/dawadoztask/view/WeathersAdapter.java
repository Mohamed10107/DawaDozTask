package com.dawadoz.dawadoztask.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.dawadoz.dawadoztask.R;
import com.dawadoz.dawadoztask.model.entity.Weather;
import com.dawadoz.dawadoztask.utils.Views;
import java.util.ArrayList;

public class WeathersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
	private Context context;
	private ArrayList<Weather> weathers = new ArrayList<>();

	WeathersAdapter(Context context)
	{
		this.context = context;
	}

	public void setData(ArrayList<Weather> weathers)
	{
		this.weathers = new ArrayList<>();
		this.weathers.addAll(weathers);
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_weather_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
	{
		ViewHolder viewHolder = (ViewHolder) holder;
		Weather item = getItem(position);

		Views.ImageLoader.load(context, viewHolder.image, item.icon);
		viewHolder.main.setText(item.main);
		viewHolder.temperature.setText(item.minTemperature.concat("°C - ".concat(item.maxTemperature.concat("°C"))));
		viewHolder.wind.setText(item.speed.concat("km/h"));
		viewHolder.humidity.setText(item.humidity);
	}

	@Override
	public int getItemCount()
	{
		return weathers.size();
	}

	private Weather getItem(int position)
	{
		return weathers.get(position);
	}

	public class ViewHolder extends RecyclerView.ViewHolder
	{
		ImageView image;
		TextView main, temperature, wind, humidity;

		ViewHolder(View itemView)
		{
			super(itemView);
			image = itemView.findViewById(R.id.image);
			main = itemView.findViewById(R.id.main);
			temperature = itemView.findViewById(R.id.temperature);
			wind = itemView.findViewById(R.id.wind);
			humidity = itemView.findViewById(R.id.humidity);
		}
	}
}
