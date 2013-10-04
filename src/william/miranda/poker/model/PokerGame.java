package william.miranda.poker.model;

import william.miranda.poker.model.Jogada.TipoJogada;
import william.miranda.poker.view.PlayerSlot;
import william.miranda.poker.view.ViewUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Classe principal, onde o jogo começa 
 *
 */
public class PokerGame implements Desenhavel
{
	public static boolean vitoria = true;
	
	//fazemos um singleton para o jogo
	private static PokerGame instance = null;
	
	//guarda a instance do SpriteBatch
	public static SpriteBatch batch;
	
	//cria a o jogo com jogadores dummy
	private PokerGame()
	{
		mesaFisica = new MesaFisica();
		
		mesaFisica.adicionarJogador(new Jogador("Jogador 1", 1000), 0);
		mesaFisica.adicionarJogador(new Bot("Bot 2", 1000), 1);
		mesaFisica.adicionarJogador(new Bot("Bot 3", 1000), 2);
		mesaFisica.adicionarJogador(new Bot("Bot 4", 1000), 3);
		mesaFisica.adicionarJogador(new Bot("Bot 5", 1000), 4);
		mesaFisica.adicionarJogador(new Bot("Bot 6", 1000), 5);
		mesaFisica.adicionarJogador(new Bot("Bot 7", 1000), 6);
		mesaFisica.adicionarJogador(new Bot("Bot 8", 1000), 7);
		
		/*
		mesaFisica.adicionarJogador(new Jogador("Jogador 2", 1000), 1);
		mesaFisica.adicionarJogador(new Jogador("Jogador 3", 1000), 2);
		mesaFisica.adicionarJogador(new Jogador("Jogador 4", 1000), 3);
		mesaFisica.adicionarJogador(new Jogador("Jogador 5", 1000), 4);
		mesaFisica.adicionarJogador(new Jogador("Jogador 6", 1000), 5);
		mesaFisica.adicionarJogador(new Jogador("Jogador 7", 1000), 6);
		mesaFisica.adicionarJogador(new Jogador("Jogador 8", 1000), 7);
		*/
	}
	
	public static PokerGame getInstance()
	{
		if (instance == null)
		{
			instance = new PokerGame();
		}
		
		return instance;
	}
	
	//declara a MesaFisica
	private MesaFisica mesaFisica;
	
	//variaveis que controlarao a rodada da vez
	public static Rodada rodada;
	public static Mesa mesa;
	public static Baralho baralho;
	
	public static Jogador dealer = null;//mantemos para nao perder na hora de mudar de rodada
	
	/* variaveis que controlarao o GameLoop */
	private static Jogador jogadorDaVez;
	private static Jogador jogadorInicio;
	private static Jogador jogadorFim;
	
	/* reinicializa as variaveis preparando o jogo para uma nova Rodada */
	public void prepararNovaRodada()
	{
		//inicializa o baralho
		baralho = new Baralho();
		
		//inicializa o objeto mesa (para a rodada)
		mesa = new Mesa();
		
		//limpa as cartas dos jogadores
		for (Jogador j : mesaFisica.getJogadores())
		{
			if (j != null)
			{
				j.reset();
			}
		}
		
		//inicializa a jogada passando os jogadores que estao sentados na mesaFisica
		rodada = new Rodada(mesaFisica.getJogadores());
	}
	
	/* GameLoop */
	public void rodarJogo()
	{
		//define dealer, smallBlind e bigBlind, jogadorInicio e jogadorFim
		setPosicoes();
		
		//da duas cartas para cada jogador
		darCartas();
		
		/* inicia o jogo de fato
		 * Faz cada Jogador jogar
		 * Se for um Bot, basta apenas coletar o retorno do método Jogar()
		 * Se é um Player, precisa mostrar os botões de ação para então tratar o input */
		rodarGameLoop();
	}
	
	public void setPosicoes()
	{
		//se nao existia dealer, sorteia um
		if (dealer == null)
		{
			dealer = mesaFisica.getRandom();
		}
		else//se ja existia, passa pra frente
		{
			dealer = mesaFisica.proximoJogador(dealer);
		}
		
		//define os objetos da rodada
		rodada.setDealer(dealer);
		rodada.setJogadorSmallBlind(mesaFisica.proximoJogador(dealer));
		rodada.setJogadorBigBlind(mesaFisica.proximoJogador(rodada.getJogadorSmallBlind()));
		
		//define as posicoes iniciais de quem começa o jogo
		jogadorInicio = mesaFisica.proximoJogador(rodada.getJogadorBigBlind());
		jogadorFim = rodada.getJogadorBigBlind();
	}
	
	/* Da DUAS cartas para cada jogador, respeitando a ordem */
	public void darCartas()
	{
		for (int i=0 ; i<2 ; i++)
		{
			Jogador j = jogadorInicio;
			
			j.addCarta(baralho.sortearCarta());
			
			while (!j.equals(jogadorFim))
			{
				j = mesaFisica.proximoJogador(j);
				j.addCarta(baralho.sortearCarta());
			}
		}
	}
	
	public void desenhar(SpriteBatch batch)
	{
		//desenha a mesa
		mesa.desenhar(batch);
		
		//desenha os jogadores
		mesaFisica.desenhar(batch);
		
		//desenha o dealer button
		desenhaDealerButton(batch);
	}
	
	public void desenhaDealerButton(SpriteBatch batch)
	{
		int posDealer = mesaFisica.getJogadores().indexOf(rodada.getDealer());
		
		FileHandle fileHandle = Gdx.files.internal(ViewUtils.getDealerButton());
		Texture t = new Texture(fileHandle);
		
		batch.draw(t, PlayerSlot.getSlot(posDealer).getDealerX(), PlayerSlot.getSlot(posDealer).getDealerY());
	}
	
	//chama os metodos para criar a mesa e iniciar a rodada
	public static void prepararIniciarRodada()
	{
		batch = new SpriteBatch();
		
		vitoria = true;
		PokerGame pokerGame = PokerGame.getInstance();
		pokerGame.prepararNovaRodada();
		pokerGame.rodarJogo();
	}
	
	/* método que implementa o GameLoop
	 * Se um bot for Jogar, apenas chama a funcao, pega o resultado e passa para o proximo
	 * se for um Jogador, precisa mostrar os botoes e aguardar a resposta da UI (ou um timeout) */
	public void rodarGameLoop()
	{
		jogadorDaVez = jogadorInicio;
		
		jogadorDaVez.jogar(mesa);
		
		while (!jogadorDaVez.equals(jogadorFim))
		{
			jogadorDaVez = mesaFisica.proximoJogador(jogadorDaVez);
			jogadorDaVez.jogar(mesa);
		}
	}
}
