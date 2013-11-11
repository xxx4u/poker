package william.miranda.poker.view;

import java.util.List;

import william.miranda.poker.model.Carta;
import william.miranda.poker.model.Jogador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
 * Esta Classe irá cuidar do desenho de um jogador específico
 * MesaView irá chamar o desenhar() para cada Jogador
 */
public class JogadorView implements Desenhavel
{
	private Jogador jogador;
	private PlayerSlot playerSlot;
	
	public JogadorView(Jogador jogador, int pos)
	{
		this.jogador = jogador;
		this.playerSlot = PlayerSlot.getSlot(pos);
	}
	
	public void desenhar()
    {
		SpriteBatch batch = ViewUtils.batch;
		List<Carta> cartas = jogador.getCartas();
		
		if (cartas == null)
			return;
		
		for (int j=0 ; j<cartas.size() ; j++)
		{
			Carta carta = cartas.get(j);
			FileHandle fileHandle = Gdx.files.internal(ViewUtils.getResourceName(carta));
			Texture t = new Texture(fileHandle);
			
			batch.draw(t, playerSlot.getX()+80*j , playerSlot.getY());
		}
    }
	
	public void desenharDealerButton()
	{
		SpriteBatch batch = ViewUtils.batch;
		
		FileHandle fileHandle = Gdx.files.internal(ViewUtils.getDealerButton());
		Texture t = new Texture(fileHandle);
		batch.draw(t, playerSlot.getDealerX(), playerSlot.getDealerY());
	}
	
	public Jogador getJogador() {
		return this.jogador;
	}
}
