package william.miranda.poker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Guarda os dados de uma Rodada e as operacoes basicas. Eh utilizada em conjunto com a Classe Mesa
 * Uma Rodada vai desde dar as Cartas ate ver quem venceu
 */
public class Rodada
{
	private int valorSmallBlind;
	private int valorBigBlind;
	private Jogador dealer;
	private Jogador jogadorSmallBlind;
	private Jogador jogadorBigBlind;
	private List<Jogador> jogadores;
	private int pot;

	public Rodada(List<Jogador> jogadores)
	{
		this.jogadores = jogadores;
	}
	
	public void pagarBlinds()
	{
		//faz os jogadores apostarem o valor das Blinds nesta Rodada
		jogadorSmallBlind.apostar(valorSmallBlind);
		jogadorBigBlind.apostar(valorBigBlind);
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
	
	public List<Jogador> getJogadores() {
		return this.jogadores;
	}
	
	public int getPot() {
		return this.pot;
	}
	
	public void addPot(int valor) {
		this.pot += valor;
	}
}
