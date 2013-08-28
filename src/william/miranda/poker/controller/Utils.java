package william.miranda.poker.controller;

import android.util.Log;

public class Utils
{
	public static final String TAG = "BRUTUS";
	public static final boolean isAndroid = true;
	
	
	public static void Log(String message)
	{
		if (isAndroid)
			Log.d(TAG, message);
		else
			System.out.println(message);
	}
	
	public static void Log(Object obj)
	{
		if (isAndroid)
			Log.d(TAG, obj.toString());
		else
			System.out.println(obj.toString());
	}
}
