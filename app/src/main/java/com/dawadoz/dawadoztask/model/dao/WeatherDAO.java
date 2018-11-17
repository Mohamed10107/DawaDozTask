package com.dawadoz.dawadoztask.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.dawadoz.dawadoztask.model.entity.Weather;
import java.util.ArrayList;
import java.util.List;

@Dao
public interface WeatherDAO
{
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insert(ArrayList<Weather> weathers);

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insert(Weather weathers);

	@Update
	void update(Weather weathers);

	@Query("DELETE FROM weather WHERE city = :city;")
	void delete(String city);

	@Query("DELETE FROM weather;")
	void clear();

	@Query("SELECT * FROM weather WHERE city = :city;")
	List<Weather> getWeathers(String city);
}
