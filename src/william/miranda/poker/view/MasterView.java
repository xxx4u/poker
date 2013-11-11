package william.miranda.poker.view;

import java.util.ArrayList;
import java.util.List;

import william.miranda.poker.model.PokerGame;

/*
 * Esta classe será responsável por conter todos os objetos que serão desenhados
 * na tela.
 * Fazemos os desenhos aki, para não contaminar o Model com as funções de desenho.
 */
public class MasterView implements Desenhavel
{
	//variavel do jogo
	private PokerGame pokerGame;
	
	//lista dos objetos View dos jogadores
	private List<JogadorView> jogadoresView;
	
	//View da Mesa
	private MesaView mesaView;
	
	public MasterView(PokerGame pokerGame)
	{
		this.pokerGame = pokerGame;
		mesaView = new MesaView(pokerGame.getMesa());
		
		jogadoresView = new ArrayList<JogadorView>();
		
		for (int i=0 ; i<pokerGame.getRodada().getJogadores().size() ; i++)
		{
			jogadoresView.add(new JogadorView(pokerGame.getRodada().getJogadores().get(i), i));
		}
	}
	
	public void desenhar()
	{
		//desenha a mesa
		mesaView.desenhar();
		
		//desenha cada jogador individualmente
		for (JogadorView jv : jogadoresView)
		{
			jv.desenhar();
			
			if (pokerGame.getRodada().getDealer().equals(jv.getJogador()))
			{
				jv.desenharDealerButton();
			}
		}
	}
}
