package william.miranda.poker.model;

import java.util.ArrayList;

import william.miranda.poker.view.ViewUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Classe que contem as coisas intrinsecas da Mesa, tais como as Cartas, Pot
 * Funciona basicamente como um container para a Classe Rodada
 * é usada pelo Jogador (no caso Bot) para analisar a jogada
 */
public class Mesa implements Desenhavel
{
	public static enum TurnosMesa{PRE_FLOP, FLOP, TURN, RIVER, SHOWDOWN};
	
	private ArrayList<Carta> cartas;
	private TurnosMesa turnoMesa;
	
	/* Adiciona uma carta na mesa */
	public void addCarta(Carta c)
    {
    	//se ainda nao inicializou o vetor de cartas
    	if (cartas == null)
    	{
    		cartas = new ArrayList<Carta>();
    	}
    	
        cartas.add(c);
    }
	
	//gets and sets
	public ArrayList<Carta> getCartas()	{
		return this.cartas;
	}
	
	public TurnosMesa getTurnoMesa() {
		return turnoMesa;
	}

	public void setTurnoMesa(TurnosMesa turnoMesa) {
		this.turnoMesa = turnoMesa;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		if (cartas != null)
			sb.append(cartas.toString());
		
		return sb.toString();
	}
	
	/* A mesa sabe desenhar suas cartas */
	public void desenhar(SpriteBatch batch)
	{
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
