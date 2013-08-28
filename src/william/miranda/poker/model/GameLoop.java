package william.miranda.poker.model;

import william.miranda.poker.model.Jogada.TipoJogada;
import william.miranda.poker.model.Mesa.TurnosMesa;

/*
 * Classe que controla o Loop do Jogo
 * Apos um objeto do tipo Rodada ser preenchido, esta classe de Loop eh chamada para fazer o jogo acontecer
 * GameLoop obtem as informacoes que precisa do objeto Rodada e Mesa.
 * 
 * Aqui são definidos os objetos dinamicos, tais como o loop dos jogadores, de quem eh a vez e o controle
 * de quando o turno termina.
 */
public class GameLoop implements Runnable
{
	//containers do jogo
	private Rodada rodada;
	private Mesa mesa;
	
	//variaveis dinamicas
	private Jogador jogadorDaVez;
	private Jogador jogadorInicio;  //funciona como marcador do Fim, pois Fim = Inicio-1
	//private Jogador jogadorFim;
	
	private Baralho baralho;
	
	/* Construtor do GameLoop */
	public GameLoop(Rodada rodada)
	{
		this.rodada = rodada;
		this.mesa = new Mesa();
		this.baralho = new Baralho();
	}
	
	@Override
	//roda o loop do jogo
	public void run()
	{
		System.out.println("Jogadores: " + rodada.getJogadores());
		System.out.println("Dealer: " + rodada.getDealer());
		
		//chama os metodos para jogar cada turno
		preFlop();
		flop();
		turn();
		river();
	}

	//prepara as variaveis do turno
	private void prepararTurno(TurnosMesa turno)
	{
		//seta o estado inicial da mesa
		mesa.setTurnoMesa(turno);
		
		//faz os jogadores pagarem as Blinds
		rodada.pagarBlinds();
		
		/* define quem inicialmente onde comeca e onde termina
		 * Comeca a esquerda da BigBlind e termina na BigBlind (para o Pre Flop) */
		if (turno == TurnosMesa.PRE_FLOP)
		{
			jogadorInicio = Jogo.getProximoJogador(rodada.getJogadores(), rodada.getJogadorBigBlind());
			//jogadorFim = rodada.getJogadorBigBlind();
		}
		else
		/* Comeca a esquerda do Dealer e termina no Dealer BigBlind (para os demais turnos) */
		{
			jogadorInicio = Jogo.getProximoJogador(rodada.getJogadores(), rodada.getDealer());
			//jogadorFim = rodada.getDealer();
		}
	}
	
	private void processarLoopTurno()
	{
		jogadorDaVez = jogadorInicio;
		System.out.println("Mesa: " + mesa);
		
		do
		{
			//faz o jogador jogar
			Jogada j = jogadorDaVez.jogar(mesa);
			System.out.println("Jogando: " + jogadorDaVez + " - " + j);
			
			//se apostou, muda o fim do Loop
			if (j.getTipoJogada() == TipoJogada.BET)
			{
				jogadorInicio = jogadorDaVez;
				jogadorDaVez = Jogo.getProximoJogador(rodada.getJogadores(), jogadorDaVez);
				continue;
			}
			
			//passa para o proximo
			jogadorDaVez = Jogo.getProximoJogador(rodada.getJogadores(), jogadorDaVez);
		} while (jogadorDaVez != jogadorInicio);
	}
	
	private void preFlop()
	{
		System.out.println("Rodando Pre-Flop");
		
		//prepara as variaveis dinamicas
		prepararTurno(TurnosMesa.PRE_FLOP);
		
		//da as duas cartas para cada jogador
		distribuiCartasJogadores();
		
		//comeca de fato o loop
		processarLoopTurno();
	}
	
	private void flop()
	{
		System.out.println("Rodando Flop");
		
		//prepara as variaveis dinamicas
		prepararTurno(TurnosMesa.FLOP);
		
		//dar as cartas da mesa
		distribuiCartasMesa(3);
						
		//comeca de fato o loop
		processarLoopTurno();
	}
	
	private void turn()
	{
		System.out.println("Rodando Turn");
		
		//prepara as variaveis dinamicas
		prepararTurno(TurnosMesa.TURN);
		
		//dar as cartas da mesa
		distribuiCartasMesa(1);
						
		//comeca de fato o loop
		processarLoopTurno();
	}
	
	private void river()
	{
		System.out.println("Rodando River");
		
		//prepara as variaveis dinamicas
		prepararTurno(TurnosMesa.RIVER);
		
		//dar as cartas da mesa
		distribuiCartasMesa(1);
						
		//comeca de fato o loop
		processarLoopTurno();
	}
	
	private void showdown()
	{
		System.out.println("Rodando Showdown");
	}
	
	//da duas cartas para cada Jogador
	public void distribuiCartasJogadores()
	{
		for (Jogador j : rodada.getJogadores())
		{
			j.addCarta(baralho.sortearCarta());
			j.addCarta(baralho.sortearCarta());
		}
	}
	
	//distribui N cartas para a mesa
	public void distribuiCartasMesa(int numeroCartas)
	{
		for (int i=0 ; i<numeroCartas ; i++)
		{
			mesa.addCarta(baralho.sortearCarta());
		}
	}
}
