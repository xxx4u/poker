package william.miranda.poker.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import william.miranda.poker.controller.Utils;
import william.miranda.poker.model.Carta.Naipe;
import william.miranda.poker.model.Mao.TipoMao;

/**
 * Esta classe implementa as Regras do jogo, tais como
 * Analise das cartas, Comparacao dos Jogos, etc
 * As funcoes sao estaticas de modo que podem ser chamadas diretamente
 * pelas classes do Jogo.
 */
public class Poker
{
	
	/*
	 * Cada Jogador possui 5 cartas da mesa e 2 cartas proprias
	 * O que resulta em Combinacao(7,5) = 21 jogos possiveis
	 * Este m�todo retorna um Array contendo os 21 jogos do jogador
	 */
	public static List<List<Carta>> obtemJogos(Jogador j, Mesa m)
	{
		List<Carta> cartas = new ArrayList<Carta>();
		List<List<Carta>> res = new ArrayList<List<Carta>>();
		
		//obtem todas as cartas em um unico vetor
		cartas.addAll(m.getCartas());
		cartas.addAll(j.getCartas());
		
		//faz a permutacao
		for (int i=0 ; i<cartas.size() ; i++)
		{
			for (int k=i ; k<cartas.size() ; k++)
			{
				if (i == k)
					continue;
				
				List<Carta> tmp = new ArrayList<Carta>();
				
				//remove os elementos i e k e retorna os 5 que sobram
				for (int l=0 ; l<cartas.size() ; l++)
				{
					if (l != i && l != k)
					{
						//insere a carta
						tmp.add(cartas.get(l));
					}
				}
				
				//adiciona o conjunto de cartas
				res.add(tmp);
			}
		}
		
		return res;
	}
	
	/*
	 * Este metodo faz a analise de um conjunto de 5 cartas
	 * e retorna o objeto Mao, contendo o jogo formado a o vetor de cartas de desempate
	 */
	public static Mao analisaJogo(List<Carta> cartas)
	{
		Mao mao;
		
		mao = isRoyalFlush(cartas);
		if (mao != null)
			return mao;
		
		mao = isStraightFlush(cartas);
		if (mao != null)
			return mao;
		
		mao = isFourOfAKind(cartas);
		if (mao != null)
			return mao;
		
		mao = isFullHouse(cartas);
		if (mao != null)
			return mao;
		
		mao = isFlush(cartas);
		if (mao != null)
			return mao;
		
		mao = isStraight(cartas);
		if (mao != null)
			return mao;
		
		mao = isThreeOfAKind(cartas);
		if (mao != null)
			return mao;
		
		mao = isTwoPair(cartas);
		if (mao != null)
			return mao;
		
		mao = isOnePair(cartas);
		if (mao != null)
			return mao;
		
		mao = isHighCard(cartas);
		if (mao != null)
			return mao;
		
		return mao;
	}
	
	//Verifica a HighCard
	public static Mao isHighCard(List<Carta> cartas)
	{
		//duplica e ordena o vetor
		ArrayList<Carta> tmp = new ArrayList<Carta>();
		tmp.addAll(cartas);
		Collections.sort(tmp);
		
		//joga os As no final se existirem
		while (tmp.get(0).getNumero() == 1)
		{
			tmp.add(tmp.size(), tmp.get(0));
			tmp.remove(0);
		}
		
		Mao mao = new Mao();
		mao.setTipoMao(TipoMao.HIGH_CARD);
		
		for (int j=tmp.size()-1 ; j>=0 ; j--)
		{
			mao.addHighCard(tmp.get(j));
		}
		
		return mao;
	}
	
	//Verifica se eh One Pair
	public static Mao isOnePair(List<Carta> cartas)
	{
		//inicializa os contadores
		int[] count;
		
		//faz a contagem das cartas
		count = contaCartas(cartas);
		
		//varre novamente o vetor para ver o resultado da contagem
		for (int i=0 ; i<cartas.size() ; i++)
		{
			if (count[i] == 2) //se achou 2 da mesma
			{
				Mao mao = new Mao();
				mao.setTipoMao(TipoMao.ONE_PAIR);
				mao.addHighCard(cartas.get(i));
				
				ArrayList<Carta> kickers = new ArrayList<Carta>();
				
				//busca os kickers
				for (int j=0 ; j<cartas.size() ; j++)
				{
					if (count[j] == 1)//se achou, insere na tmp
					{
						kickers.add(cartas.get(j));
					}
				}
				
				//ordena os kickers
				Collections.sort(kickers);
				
				//joga o As no final (carta mais valiosa) se existir
				if (kickers.get(0).getNumero() == 1)
				{
					kickers.add(kickers.size(), kickers.get(0));
					kickers.remove(0);
				}
				
				//compara os kickers e insere do maior pro menor
				for (int j=kickers.size()-1 ; j>=0 ; j--)
				{
					mao.addHighCard(kickers.get(j));
				}
				
				return mao;
			}//end if
		}//end for
		
		return null;
	}
	
	
	//Verifica se eh Two Pair
	public static Mao isTwoPair(List<Carta> cartas)
	{
		//inicializa os contadores
		int[] count;
		
		//faz a contagem das cartas
		count = contaCartas(cartas);
		
		//varre novamente o vetor para ver o resultado da contagem
		for (int i=0 ; i<cartas.size() ; i++)
		{
			if (count[i] == 2) //se achou 2 da mesma, precisa ver se achou 2x pares
			{
				Carta tmp = cartas.get(i);//salva na tmp
				
				//busca pelo outro par
				for (int j=0 ; j<cartas.size() ; j++)
				{
					//se achou 2 da mesma, e se eh diferente da primeira
					if ((count[j] == 2) && (tmp.getNumero() != cartas.get(j).getNumero()))
					{
						Mao mao = new Mao();
						mao.setTipoMao(TipoMao.TWO_PAIR);
						
						if (Carta.comparar(tmp, cartas.get(j)) == 1)
						{
							mao.addHighCard(tmp);
							mao.addHighCard(cartas.get(j));
						}
						else
						{
							mao.addHighCard(cartas.get(j));
							mao.addHighCard(tmp);
						}
						
						//agora procura pelo kicker restante
						for (int k=0 ; k<cartas.size() ; k++)
						{
							if (count[k] == 1)
								mao.addHighCard(cartas.get(k));
						}
						
						return mao;
					}//end if
				}//end for
			}//end if
		}//end for
		
		return null;
	}
	
	//Verifica se eh Three of A kind
	public static Mao isThreeOfAKind(List<Carta> cartas)
	{
		//inicializa os contadores
		int[] count;
		
		//faz a contagem das cartas
		count = contaCartas(cartas);
		
		//varre novamente o vetor para ver o resultado da contagem
		for (int i=0 ; i<cartas.size() ; i++)
		{
			if (count[i] == 3) //se achou 3 da mesma
			{
				Mao mao = new Mao();
				mao.setTipoMao(TipoMao.THREE_OF_A_KIND);
				mao.addHighCard(cartas.get(i));//adiciona a carta tripla
				
				//procura pelos kickers (sao as outras duas cartas que aparecem 1x da maior pra menor)
				Carta tmp = null;
				for (int j=0 ; j<cartas.size() ; j++)
				{
					if (count[j] == 1)//achou um, precisa comparar com o outro para saber qual eh o maior
					{
						if (tmp == null)//se eh o primeiro, salva no temp
						{
							tmp = cartas.get(j);
						}
						else//se eh o segundo, compara e insere na ordem
						{
							if (Carta.comparar(tmp, cartas.get(j)) == 1)
							{
								mao.addHighCard(tmp);
								mao.addHighCard(cartas.get(j));
							}
							else
							{
								mao.addHighCard(cartas.get(j));
								mao.addHighCard(tmp);
							}
						}//end else
							
					}
				}//end for
				
				return mao;
			}
		}
		
		return null;
	}
	
	//Verifica se eh Full House
	public static Mao isFullHouse(List<Carta> cartas)
	{
		//inicializa os contadores
		int[] count;
		
		//faz a contagem das cartas
		count = contaCartas(cartas);
		
		//varre novamente o vetor para ver o resultado da contagem
		for (int i=0 ; i<cartas.size() ; i++)
		{
			if (count[i] == 3) //se achou 3 da mesma
			{
				//procura pelo par
				for (int j=0 ; j<cartas.size() ; j++)
				{
					if (count[j] == 2)
					{
						Mao mao = new Mao();
						mao.setTipoMao(TipoMao.FULL_HOUSE);
						mao.addHighCard(cartas.get(i));//adiciona a carta da trinca
						mao.addHighCard(cartas.get(j));//adiciona a carta do par
						return mao;
					}
				}
			}
		}
		
		return null;
	}
	
	//Verifica se eh FourOfAKind
	public static Mao isFourOfAKind(List<Carta> cartas)
	{
		//inicializa os contadores
		int[] count;
		
		//faz a contagem das cartas
		count = contaCartas(cartas);
		
		//varre novamente o vetor para ver o resultado da contagem
		for (int i=0 ; i<cartas.size() ; i++)
		{
			if (count[i] == 4) //se achou 4 da mesma
			{
				Mao mao = new Mao();
				mao.setTipoMao(TipoMao.FOUR_OF_A_KIND);
				mao.addHighCard(cartas.get(i));//adiciona a carta quadra
				
				//procura pelo kicker
				for (int j=0 ; j<cartas.size() ; j++)
				{
					if (count[j] == 1)
					{
						mao.addHighCard(cartas.get(j));//adiciona o kicker						
					}
				}
				
				return mao;
			}
		}
		
		return null;
	}
	
	//Verifica se eh um Royal Flush
	public static Mao isRoyalFlush(List<Carta> cartas)
	{
		Mao ehStraightFlush = isStraightFlush(cartas);
		
		if (ehStraightFlush != null) //se eh Straight Flush, verifica a carta mais alta
		{
			if (ehStraightFlush.getHighCards().get(0).getNumero() == 1)//se a carta mais alta eh um As, eh Royal
			{
				Mao mao = new Mao();
				mao.setTipoMao(TipoMao.ROYAL_FLUSH);
				mao.setHighCard(ehStraightFlush.getHighCards());
				return mao;
			}
		}
		
		return null;
	}
	
	//Verifica se eh um Straight Flush
	public static Mao isStraightFlush(List<Carta> cartas)
	{
		Mao ehFlush = isFlush(cartas);
		Mao ehStraight = isStraight(cartas);

		//se eh um flush e um straight
		if (ehFlush != null && ehStraight != null)
		{
			Mao mao = new Mao();
			mao.setTipoMao(TipoMao.STRAIGHT_FLUSH);
			mao.setHighCard(ehStraight.getHighCards());
			return mao;
		}
		
		return null;
	}
	
	//Verifica se eh um Flush
	public static Mao isFlush(List<Carta> cartas)
	{
		Naipe naipe = cartas.get(0).getNaipe();
		
		List<Carta> highCard = new ArrayList<Carta>();		
		highCard.add(cartas.get(0));
		
		//verifica se sao do mesmo naipe
		for (int i=1 ; i<cartas.size() ; i++)
		{
			//se nao sao do mesmo naipe, retorna null
			if (naipe != cartas.get(i).getNaipe())
				return null;
			
			//salva as cartas para o desempate... todas as cartas sao contadas na ordem reversa
			highCard.add(cartas.get(i));
		}
		
		Collections.sort(highCard);

		//se eh um Flush, retorna
		Mao mao = new Mao();
		mao.setTipoMao(TipoMao.FLUSH);
		
		//adiciona as cartas em ordem reversa para desempate
		for (int i=highCard.size()-1 ; i>=0 ; i--)
		{
			mao.addHighCard(highCard.get(i));
		}
		
		return mao;
	}
	
	//verifica se eh um Straight
	public static Mao isStraight(List<Carta> cartas)
	{
		ArrayList<Carta> tmp = new ArrayList<Carta>();
		boolean formaSequencia = true;
		
		//duplica o vetor de cartas
		for (int i=0 ; i<cartas.size() ; i++)
		{
			tmp.add(cartas.get(i));
		}
		
		//ordena o vetor pelo numero da carta
		Collections.sort(tmp);
			
		/* verificamos se os numeros ordenados formam uma sequencia
		 * precisamos tratar os casos em que o As pode vir antes do 2 ou depois do K
		 */
		
		//primeiro trata o caso normal, em que a sequencia est� na ordem
		for (int i=tmp.size()-1 ; i>0 ; i--)
		{
			//se nao formar a sequencia
			if (tmp.get(i).getNumero() - tmp.get(i-1).getNumero() != 1)
			{
				formaSequencia = false;
				break;
			}
		}
		
		//se saiu do loop formando a sequencia, formou Straight... retorna
		if (formaSequencia)
		{
			Mao mao = new Mao();
			mao.setTipoMao(TipoMao.STRAIGHT);
			mao.addHighCard(tmp.get(tmp.size()-1));
			return mao;
		}
		else //se nao formou sequencia, ainda podemos ter o caso 10-11-12-13-1
		{
			int[] vet = new int[]{1,10,11,12,13};
			
			//verifica se os numeros batem com o array acima
			for (int i=0 ; i<tmp.size() ; i++)
			{
				if (vet[i] != tmp.get(i).getNumero())
					return null;
			}
			
			
		}
		
		//se chegou aqui eh sequencia terminada em As (10-11-12-13-AS)
		Mao mao = new Mao();
		mao.setTipoMao(TipoMao.STRAIGHT);
		mao.addHighCard(tmp.get(0));//o AS eh mais forte
		return mao;
	}
	
	//Retorna um vetor contendo a contagem das cartas
	public static int[] contaCartas(List<Carta> cartas)
	{
		int[] count = new int[]{0,0,0,0,0};
		
		//varre as cartas
		for (int i=0 ; i<cartas.size() ; i++)
		{
			Carta c = cartas.get(i);//seleciona o pivo

			//verifica quantas cartas existem de cada uma
			for (int j=0 ; j<cartas.size() ; j++)
			{
				if (c.getNumero() == cartas.get(j).getNumero())
					count[i]++;
			}
		}
		
		return count;
	}
}
