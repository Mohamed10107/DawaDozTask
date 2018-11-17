package com.dawadoz.dawadoztask.view;

import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.dawadoz.dawadoztask.R;
import com.dawadoz.dawadoztask.listeners.ConnectionChangingListener;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity
{
	public static boolean connected = true;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		networkListener();

		addFragment(MainFragment.newInstance(), MainFragment.tag);
	}

	private void networkListener()
	{
		// registering connection listener
		IntentFilter netFilter = new IntentFilter();
		netFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
		registerReceiver(ConnectionChangingListener.getInstance(), netFilter);

		// Listening to network state
		ConnectionChangingListener.observable.subscribe(new Observer<Boolean>()
		{
			@Override
			public void onSubscribe(Disposable d)
			{

			}

			@Override
			public void onNext(Boolean value)
			{
				connected = value;
			}

			@Override
			public void onError(Throwable e)
			{

			}

			@Override
			public void onComplete()
			{

			}
		});
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		unregisterReceiver(ConnectionChangingListener.getInstance());
	}

	public void addFragment(Fragment fragment, String tag)
	{
		getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).addToBackStack(tag).commit();
	}

	public void popFragment()
	{
		getSupportFragmentManager().popBackStackImmediate();
		if (getSupportFragmentManager().getBackStackEntryCount() == 0)
		{
			finish();
		}
	}

	@Override
	public void onBackPressed()
	{
		popFragment();
	}
}
