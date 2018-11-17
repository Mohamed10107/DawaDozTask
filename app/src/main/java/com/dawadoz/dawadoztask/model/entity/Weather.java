package com.dawadoz.dawadoztask.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "weather")
public class Weather
{
	@PrimaryKey
	@ColumnInfo(name = "id")
	@NonNull
	public String id = "";

	@ColumnInfo(name = "city")
	@NonNull
	public String city = "";

	@ColumnInfo(name = "date")
	@NonNull
	public String date = "";

	@ColumnInfo(name = "pressure")
	public String pressure;

	@ColumnInfo(name = "humidity")
	public String humidity;

	@ColumnInfo(name = "speed")
	public String speed;

	@ColumnInfo(name = "degree")
	public String degree;

	@ColumnInfo(name = "clouds")
	public String clouds;

	@ColumnInfo(name = "min_temp")
	public String minTemperature;

	@ColumnInfo(name = "max_temp")
	public String maxTemperature;

	@ColumnInfo(name = "day_temp")
	public String dayTemperature;

	@ColumnInfo(name = "night_temp")
	public String nightTemperature;

	@ColumnInfo(name = "evening_temp")
	public String eveningTemperature;

	@ColumnInfo(name = "morning_temp")
	public String morningTemperature;

	@ColumnInfo(name = "main")
	public String main;

	@ColumnInfo(name = "description")
	public String description;

	@ColumnInfo(name = "icon")
	public String icon;
}
