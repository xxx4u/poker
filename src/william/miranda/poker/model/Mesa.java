package william.miranda.poker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que contem as coisas intrinsecas da Mesa, tais como as Cartas, Pot
 * Funciona basicamente como um container para a Classe Rodada
 * é usada pelo Jogador (no caso Bot) para analisar a jogada
 */
public class Mesa
{
	public static enum TurnosMesa{PRE_FLOP, FLOP, TURN, RIVER, SHOWDOWN};
	
	private List<Carta> cartas;
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
	public List<Carta> getCartas()	{
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
}
