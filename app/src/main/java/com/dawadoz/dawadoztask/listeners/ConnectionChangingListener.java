package com.dawadoz.dawadoztask.listeners;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class ConnectionChangingListener extends BroadcastReceiver
{
	public static Observable<Boolean> observable;
	private ObservableEmitter<Boolean> emitter;
	private static final String ACTION_DATA_STATE_CHANGED = "android.intent.action.ANY_DATA_STATE";
	private static ConnectionChangingListener connectionChangingListener;

	public static ConnectionChangingListener getInstance()
	{
		if (connectionChangingListener == null)
			connectionChangingListener = new ConnectionChangingListener();
		return connectionChangingListener;
	}

	public ConnectionChangingListener()
	{
		observable = Observable.create(emitter -> ConnectionChangingListener.this.emitter = emitter);
	}

	@Override
	public void onReceive(Context context, Intent intent)
	{
		try
		{
			if (intent.getAction().equals(ACTION_DATA_STATE_CHANGED) || intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION) || intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED))
			{
				ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo ni = connectivityManager.getActiveNetworkInfo();
				if (ni == null || ni.getState() != NetworkInfo.State.CONNECTED)
				{
					emitter.onNext(false);
				}
				else
				{
					emitter.onNext(true);
				}
			}
		}
		catch (Exception exc)
		{
			exc.printStackTrace();

		}
	}
}
