package william.miranda.poker.model;

import java.util.ArrayList;
import java.util.List;

/* 
 * Container que guarda a analise um jogo de dadas 5 cartas
 */

public class Mao
{
	//quanto menor o numero do ordinal, melhor a mao
	public static enum TipoMao{ROYAL_FLUSH, STRAIGHT_FLUSH, FOUR_OF_A_KIND, FULL_HOUSE, FLUSH, STRAIGHT, THREE_OF_A_KIND, TWO_PAIR, ONE_PAIR, HIGH_CARD};
	
	private TipoMao tipoMao;
	private ArrayList<Carta> highCards = null;
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(tipoMao.toString());
		
		if (highCards != null)
		{
			sb.append(highCards.toString());
		}
		
		return sb.toString();
	}
	
	//Compara diversas maos e retorna a melhor, ou um conjunto caso tenha empate
	public static List<Mao> comparar(ArrayList<Mao> m)
	{
		List<Mao> melhorMao = new ArrayList<>();
		
		for (int i=0 ; i<m.size() ; i++)
		{
			if (melhorMao.isEmpty())//se ainda nao inicializou
			{
				melhorMao.add(m.get(i));//carrega a primeira
			}
			else
			{
				List<Mao> listTmp = comparar(melhorMao.get(0), m.get(i));//faz as comparacoes, guardando sempre a melhor
				
				if (listTmp.size() == 1)//se veio apenas 1 melhor, continua comparando
				{
					melhorMao = listTmp;
				}
				else//se temos um conjunto de melhores, adicionamos o elemento da vez
				{
					melhorMao.add(m.get(i));
				}
			}
		}
		
		return melhorMao;
	}
	
	//Compara duas maos e retorna a melhor... quando menor o ordinal do enum, melhor eh a Mao
	public static List<Mao> comparar(Mao m1, Mao m2)
	{
		List<Mao> ret = new ArrayList<>();
		
		if (m1.getTipoMao().ordinal() < m2.getTipoMao().ordinal())//se m1 eh melhor
		{
			ret.add(m1);
		}
		else if (m1.getTipoMao().ordinal() > m2.getTipoMao().ordinal())//se m2 eh melhor
		{
			ret.add(m2);
		}
		else//se eh do mesmo valor, verifica o desempate
		{
			/* varre os vetores de desempate, comparando carta a carta
			 * Como sao do mesmo tipo, os tamanhos dos vetores sao iguais
			 */
			for (int i=0 ; i<m1.getHighCards().size() ; i++)
			{
				//se m1 eh melhor
				if ( Carta.comparar(m1.getHighCards().get(i), m2.getHighCards().get(i)) == 1 )
				{
					ret.add(m1);
					break;
				}
				else if ( Carta.comparar(m1.getHighCards().get(i), m2.getHighCards().get(i)) == -1 )//se m2 eh melhor
				{
					ret.add(m2);
					break;
				}
				else//se sao iguais, passa para a proxima carta do desempate
					continue;
			}
		}
		
		//se as duas maos sao exatamente iguais ou seja, tem o mesmo valor... retorna as duas
		if (ret.isEmpty())
		{
			ret.add(m1);
			ret.add(m2);
		}
		
		return ret;
	}
	
	//seta diretamente a lista de highcards
	public void setHighCard(ArrayList<Carta> cartas)
	{
		if (highCards == null)
		{
			this.highCards = new ArrayList<Carta>();
		}
		
		this.highCards = cartas;
	}
	
	//adiciona uma carta no fim da lista de highcards
	public void addHighCard(Carta carta)
	{
		if (highCards == null)
		{
			this.highCards = new ArrayList<Carta>();
		}
		
		this.highCards.add(carta);
	}
	
	//gets and sets
	public TipoMao getTipoMao() {
		return tipoMao;
	}
	public void setTipoMao(TipoMao tipoMao) {
		this.tipoMao = tipoMao;
	}

	public ArrayList<Carta> getHighCards() {
		return highCards;
	}
}
