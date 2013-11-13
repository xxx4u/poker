package william.miranda.poker.view;

import java.util.List;

import william.miranda.poker.model.Carta;
import william.miranda.poker.model.Jogador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
 * Esta Classe irá cuidar do desenho de um jogador específico
 * MesaView irá chamar o desenhar() para cada Jogador
 */
public class JogadorView implements Desenhavel
{
	//armazena o Jogador que será desenhado
	private Jogador jogador;
	
	//armazena a Posição em que o Jogador será desenhado
	private PlayerSlot playerSlot;
	
	public JogadorView(Jogador jogador, int pos)
	{
		this.jogador = jogador;
		this.playerSlot = PlayerSlot.getSlot(pos);
	}
	
	/* desenha o objeto do jogador e o DealerButton caso o jogador seja o Dealer */
	public void desenhar(boolean isDealer)
	{
		//primeiro desenha as cartas
		desenharCartasTmp();
		
		//desenhar o nome
		desenharNome();
		
		//desenha o Dealer Button
		if (isDealer)
			desenharDealerButton();
	}
	
	public void desenharNome()
	{
		SpriteBatch batch = ViewUtils.batch;

		BitmapFont font = new BitmapFont();		
		font.draw(batch, "TEXTO", 200, 500);
	}
	
	/* metodo utilizado para desenhar os objetos do jogador */
	public void desenhar()
    {
		desenhar(false);
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
	
	public void desenharCartasTmp()
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
}
