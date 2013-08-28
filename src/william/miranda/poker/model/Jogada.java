package william.miranda.poker.model;

/**
 * Container que guarda uma Jogada
 * No Poker uma jogada consiste de uma Ação e uma Aposta
 */
public class Jogada
{
	//possiveis tipos para a Jogada
	public static enum TipoJogada {FOLD, CHECK, CALL, BET, RAISE};
	
	//armazena a escolha do jogador
	private TipoJogada tipoJogada;
	private int valorAposta;
	
	//os parametros da Jogada devem ser passados na hora de construir
	public Jogada(TipoJogada tipoJogada, int valorAposta)
	{
		this.tipoJogada = tipoJogada;
		this.valorAposta = valorAposta;
	}
	
	//gets
	public TipoJogada getTipoJogada()
	{
		return this.tipoJogada;
	}
	
	public int getAposta()
	{
		return this.valorAposta;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(this.tipoJogada.toString());
		
		if (this.valorAposta != 0)
		{
			sb.append(" - ");
			sb.append(this.valorAposta);
		}
		
		return sb.toString();
	}
}
