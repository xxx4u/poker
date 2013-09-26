package william.miranda.poker.view;

import java.util.ArrayList;
import java.util.List;

import william.miranda.poker.model.Carta;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *  Esta Classe define o que será mostrado na posição do Jogador */
public class PlayerSlot
{
	private List<Texture> texturaCartas;
	
	private int aposta;
	private Texture avatar;
	
	private short posicao;
	
	public PlayerSlot(List<Carta> cartas, int aposta)
	{
		texturaCartas = getTexturas(cartas);
		this.aposta = aposta;
	}
	
	
	/* obtem as imagens a partir do objetos Carta */
	public List<Texture> getTexturas(List<Carta> cartas)
	{
		List<Texture> texturas = new ArrayList<Texture>();
		
		for (Carta c : cartas)
		{
			FileHandle handle = Gdx.files.internal(ViewUtils.getResourceName(c));
			texturas.add(new Texture(handle));
		}
		
		return texturas;
	}
	
	//desenha os objetos na tela
	public void desenhar(SpriteBatch batch)
	{
		//desenha as cartas
		for (int i=0 ; i<texturaCartas.size() ; i++)
		{
			Texture t = texturaCartas.get(i);
			batch.draw(t, 100*i, 0);
		}
	}
}
