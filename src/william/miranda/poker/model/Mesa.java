package william.miranda.poker.model;

import java.util.ArrayList;

/**
 * Classe que contem as coisas intrinsecas da Mesa, tais como as Cartas, Pot
 * Funciona basicamente como um container para a Classe Rodada
 * É usada pelo Jogador (no caso Bot) para analisar a jogada
 * 
 * A cada Rodada, eh criado um novo objeto desta classe
 */
public class Mesa
{
	public static enum TurnosMesa{PRE_FLOP, FLOP, TURN, RIVER, SHOWDOWN};
	
	private int pot = 0;
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
	
	//adiciona uma quantidade no pot
	public void addPot(int valor)
	{
		this.pot += valor;
	}
	
	//gets and sets
	public ArrayList<Carta> getCartas()	{
		return this.cartas;
	}
	
	public int getPot() {
		return pot;
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
