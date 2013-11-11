package william.miranda.poker.view;

import java.util.List;

import william.miranda.poker.model.Carta;
import william.miranda.poker.model.Desenhavel;
import william.miranda.poker.model.Mesa;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
		SpriteBatch batch = ViewUtils.batch;
		List<Carta> cartas = mesa.getCartas();
		
		if (cartas == null)
			return;
		
		for (int i=0 ; i<cartas.size() ; i++)
		{
			Carta carta = cartas.get(i);
			FileHandle fileHandle = Gdx.files.internal(ViewUtils.getResourceName(carta));
			Texture t = new Texture(fileHandle);
			
			batch.draw(t, 100*i, ViewUtils.getAltura()/2+60);
		}
	}
}
