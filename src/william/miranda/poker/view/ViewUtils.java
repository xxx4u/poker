package william.miranda.poker.view;

import william.miranda.poker.model.Carta;

public class ViewUtils
{
	/* constantes */
	public static final String FOLDER_CARTAS = "cartas/";
	public static final String FOLDER_FICHAS = "chips/";
	
	/* tamanho da tela */
	private static int largura;
	private static int altura;
	
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
	
	public static String getDealerButton()
	{
		return FOLDER_FICHAS + "/dealer1.png";
	}
	
	public static int getLargura() {
		return largura;
	}
	
	public static void setLargura(int largura)
	{
		if (largura > 600)
			ViewUtils.largura = 600;
		else
			ViewUtils.largura = largura;
	}
	
	public static int getAltura() {
		return altura;
	}
	
	public static void setAltura(int altura)
	{
		if (altura > 1000)
			ViewUtils.altura = 1000;
		else
			ViewUtils.altura = altura;
	}
}
