package william.miranda.poker.model;

import java.util.ArrayList;

/**
 * Guarda os dados de uma Rodada e as operacoes basicas
 * Uma Rodada vai desde dar as Cartas ate ver quem venceu
 */
public class Rodada
{
	private int valorSmallBlind;
	private int valorBigBlind;
	private Jogador dealer;
	private Jogador jogadorSmallBlind;
	private Jogador jogadorBigBlind;
	private ArrayList<Jogador> jogadores;

	public void pagarBlinds()
	{
		//faz os jogadores apostarem o valor das Blinds nesta Rodada
		jogadorSmallBlind.apostar(valorSmallBlind);
		jogadorBigBlind.apostar(valorBigBlind);
	}
	
	public void setJogadores(ArrayList<Jogador> jogadores)
	{
		if (this.jogadores == null)
		{
			this.jogadores = new ArrayList<Jogador>();
		}
			
		this.jogadores.addAll(jogadores);
	}
	
	//gets and sets
	public Jogador getDealer() {
		return dealer;
	}
	public void setDealer(Jogador dealer) {
		this.dealer = dealer;
	}
	public Jogador getJogadorSmallBlind() {
		return jogadorSmallBlind;
	}
	public void setJogadorSmallBlind(Jogador jogadorSmallBlind) {
		this.jogadorSmallBlind = jogadorSmallBlind;
	}
	public Jogador getJogadorBigBlind() {
		return jogadorBigBlind;
	}
	public void setJogadorBigBlind(Jogador jogadorBigBlind) {
		this.jogadorBigBlind = jogadorBigBlind;
	}

	public int getValorSmallBlind() {
		return valorSmallBlind;
	}

	public void setValorSmallBlind(int valorSmallBlind) {
		this.valorSmallBlind = valorSmallBlind;
	}

	public int getValorBigBlind() {
		return valorBigBlind;
	}

	public void setValorBigBlind(int valorBigBlind) {
		this.valorBigBlind = valorBigBlind;
	}
	
	public ArrayList<Jogador> getJogadores() {
		return this.jogadores;
	}
}
