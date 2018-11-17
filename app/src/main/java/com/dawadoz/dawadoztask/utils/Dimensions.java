package com.dawadoz.dawadoztask.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

class Dimensions
{
	static int dp_to_px(int dp)
	{
		return Math.round(dp * (Resources.getSystem().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));
	}

	int px_to_dp(int px)
	{
		return Math.round(px / (Resources.getSystem().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));
	}
}
