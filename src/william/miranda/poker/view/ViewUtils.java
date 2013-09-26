package william.miranda.poker.view;

import william.miranda.poker.model.Carta;

public class ViewUtils
{
	/* constantes */
	public static final String FOLDER_CARTAS = "cartas/";
	
	/* tamanho da tela */
	private int largura;
	private int altura;
	
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
	
	public int getLargura() {
		return largura;
	}
	
	public void setLargura(int largura)
	{
		if (largura > 600)
			this.largura = 600;
		else
			this.largura = largura;
	}
	
	public int getAltura() {
		return altura;
	}
	
	public void setAltura(int altura)
	{
		if (altura > 1000)
			this.altura = 1000;
		else
			this.altura = altura;
	}
}
