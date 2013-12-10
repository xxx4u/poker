package william.miranda.poker.view;

import william.miranda.poker.model.Jogador;

/*
 * Esta Classe irá cuidar do desenho de um jogador específico
 * MesaView irá chamar o desenhar() para cada Jogador
 */
public class JogadorView implements Desenhavel
{
	//armazena o Jogador que será desenhado
	private Jogador jogador;
	
	public JogadorView(Jogador jogador, int pos)
	{
		this.jogador = jogador;
	}
	
	/* desenha o objeto do jogador e o DealerButton caso o jogador seja o Dealer */
	public void desenhar(boolean isDealer)
	{
		//primeiro desenha as cartas
	}
	
	/* metodo utilizado para desenhar os objetos do jogador */
	public void desenhar()
    {
		desenhar(false);
    }
	
	public Jogador getJogador() {
		return this.jogador;
	}
}
