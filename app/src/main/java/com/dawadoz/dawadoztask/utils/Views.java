package com.dawadoz.dawadoztask.utils;

import android.content.Context;
import android.widget.ImageView;
import com.dawadoz.dawadoztask.view.LoadingDialog;
import com.squareup.picasso.Picasso;

public class Views
{
	public static class LoadingView
	{
		private LoadingDialog loading;

		public LoadingView(Context context)
		{
			loading = new LoadingDialog(context);
		}

		public void show()
		{
			if (loading != null && !loading.isShowing())
			{
				loading.show();
			}
		}

		public void dismiss()
		{
			if (loading != null && loading.isShowing())
			{
				loading.dismiss();
			}
		}
	}

	public static class ImageLoader
	{
		public static void load(Context context, ImageView imageView, String imagePath)
		{
			Picasso.with(context).load(imagePath).into(imageView);
		}
	}
}
