package william.miranda.poker.model;

import java.util.ArrayList;

import william.miranda.poker.model.Jogada.TipoJogada;
import william.miranda.poker.model.Mesa.TurnosMesa;

/*
 * Classe que implementa os Bots.
 * Basta sobrecarregar o m�todo jogar()
 */
public class Bot extends Jogador
{
	public Bot(String nome, int dinheiro)
    {
        super(nome, dinheiro);
    }
	
	@Override
	public Jogada jogar(Mesa mesa)
    {
		//obtem as variaveis
        TurnosMesa turnoMesa = mesa.getTurnoMesa();
        
        switch (turnoMesa)
        {
        	case PRE_FLOP:
        		return jogarPreFlop(mesa);
		case FLOP:
        		return jogarFlop(mesa);
        		
        	case TURN:
        		return jogarTurn(mesa);
        		
        	case RIVER:
        		return jogarRiver(mesa);
        	
        	default:
        		break;
        }
        
        //nunca deve chegar neste ponto
        return null;
    }
	
	//verifica se o jogador possui um Par
	private boolean isPair()
	{
		ArrayList<Carta> cartas = getCartas();
		
		if (cartas.get(0).getNumero() == cartas.get(1).getNumero())
			return true;
		else
			return false;
	}
	
	//verifica se as cartas sao do mesmo naipe
	private boolean isSameNaipe()
	{
		ArrayList<Carta> cartas = getCartas();
		
		if (cartas.get(0).getNaipe() == cartas.get(1).getNaipe())
			return true;
		else
			return false;
	}
	
	//IA usada para analisar a Mao antes do FLOP
	private Jogada jogarPreFlop(Mesa mesa)
	{
		//se temos um par "grande", vamos apostar
		if (isPair() && this.getCartas().get(0).getNumero() > 6)
		{
			//if (mesa.get)
			return new Jogada(TipoJogada.BET, 100);
		}
					
		return new Jogada(TipoJogada.CHECK, 0);
	}
	
	//IA usada para analisar a Mao no FLOP
	private Jogada jogarFlop(Mesa mesa)
	{
		return new Jogada(TipoJogada.CHECK, 0);
	}
	
	//IA usada para analisar a Mao no TURN
	private Jogada jogarTurn(Mesa mesa)
	{
		return new Jogada(TipoJogada.CHECK, 0);
	}
	
	//IA usada para analisar a Mao no RIVER
	private Jogada jogarRiver(Mesa mesa)
	{
		return new Jogada(TipoJogada.CHECK, 0);
	}
}
