package william.miranda.poker.view;

import java.io.IOException;
import java.io.InputStream;

import william.miranda.poker.model.Carta;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

public class ViewUtils
{
	/* constantes */
	public static final String FOLDER_CARTAS = "cartas/";
	public static final String FOLDER_FICHAS = "chips/";
	
	/* Contexto */
	public static Context context;
	
	public static String getResourceName(Carta carta)
    {
    	StringBuilder sb = new StringBuilder(FOLDER_CARTAS);
    	
    	switch (carta.getNaipe())
    	{
    		case COPAS:
    			sb.append("h");
    			break;
    		
    		case OUROS:
    			sb.append("d");
    			break;
    			
    		case ESPADAS:
    			sb.append("s");
    			break;
    			
    		case PAUS:
    			sb.append("c");
    			break;
    	}
    	
    	sb.append(carta.getNumero());
    	sb.append(".jpg");
    	
    	return sb.toString();
    }
	
	public static Drawable getDrawable(Carta carta)
	{
        try
        {
        	//obtem o nome do asset
        	String res = getResourceName(carta);
        	
        	//abre o stream
        	InputStream ims = context.getAssets().open(res);
        	
        	//carrega o stream como um Drawable
        	return Drawable.createFromStream(ims, null);
        }
        catch(IOException ex)
        {
            return null;
        }
	}
	
	public static Bitmap getBitmap(Carta carta)
	{
        try
        {
        	//obtem o nome do asset
        	String res = getResourceName(carta);
        	
        	//abre o stream
        	InputStream ims = context.getAssets().open(res);
        	
        	//carrega o stream como um Bitmap
        	return BitmapFactory.decodeStream(ims);
        }
        catch(IOException ex)
        {
            return null;
        }
	}
	
	public static String getDealerButton()
	{
		return FOLDER_FICHAS + "/dealer1.png";
	}
	
}
