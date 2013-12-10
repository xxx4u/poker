package william.miranda.poker.view;

import java.util.List;

import william.miranda.poker.model.Carta;
import william.miranda.poker.model.Mesa;

/*
 * Esta Classe irá cuidar dos desenhos da mesa
 */
public class MesaView implements Desenhavel
{
	private Mesa mesa;
	
	//cria o objeto para cuidar da mesa
	public MesaView(Mesa mesa)
	{
		this.mesa = mesa;
	}
	
	/* A mesa sabe desenhar suas cartas */
	public void desenhar()
	{
		List<Carta> cartas = mesa.getCartas();
		
		if (cartas == null)
			return;
		
		// desenha cada uma das cartas
		for (int i=0 ; i<cartas.size() ; i++)
		{
			Carta carta = cartas.get(i);
		}
	}
}
