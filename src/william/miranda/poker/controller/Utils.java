package william.miranda.poker.controller;

import java.util.ArrayList;
import java.util.List;

import william.miranda.poker.model.Jogador;
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
	
	public static List<Jogador> gerarJogadores(int num)
	{
		List<Jogador> tmp = new ArrayList<Jogador>();
		
		for (int i=0 ; i<num ; i++)
		{
			Jogador j = new Jogador("Jogador "+i, 1000);
			tmp.add(j);
		}
		
		return tmp;
	}
}
