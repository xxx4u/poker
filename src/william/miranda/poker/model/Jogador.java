package william.miranda.poker.model;

import java.util.ArrayList;
import java.util.List;

import william.miranda.poker.controller.Utils;

/**
 * Armazena as informações relevantes de um jogador
 * 
 */
public class Jogador
{
    private String nome;
    private List<Carta> cartas = null;
    
    //diz se o jogador deu Fold na rodada
    private boolean isFold;
    
    //quantas fichas o jogador possui no total
    private int dinheiro;
      
    //flag que define se o jogador está em All In
    private boolean allIn = false;
        
    /* Construtor do Jogador
     * Preenche o nome do Jogador
     */
    public Jogador(String nome, int dinheiro)
    {
        this.nome = nome;
        this.dinheiro = dinheiro;
    }
    
    /* Adiciona uma carta na mao do jogador
     * Esta classe nao trata o numero de cartas passadas pela mesa
     */
    public void addCarta(Carta c)
    {
    	//se ainda nao inicializou o vetor de cartas
    	if (cartas == null)
    	{
    		cartas = new ArrayList<Carta>();
    	}
    	
        cartas.add(c);
    }
    
    /* Limpa as cartas do Jogador
     * Utilizada para iniciar a proxima rodada
     */
    public void limparCartas()
    {
        cartas = null;
    }
    
    /* Metodo chamado quando o jogador precisa fazer uma jogada
     * No poker, uma jogada consiste de uma Acao e uma APOSTA
     */
    public Jogada jogar(Mesa mesa)
    {
    	Utils.Log(this.getNome());
    	Jogada jogada = null;
    	
    	jogada = new Jogada(Jogada.TipoJogada.CHECK, 0);
    	
        return jogada;
    }
    
    /*
     * Aposta um determinado valor na Rodada
     * Usada tambem para pagar Blinds
     * O valor apostado fica na Mesa ate o fim do turno,
     * indo para o Pot ao fim do turno
     * 
     * retorna o quanto foi apostado (para o caso de estar em All In)
     */
    public int apostar(int valor)
    {
    	return 0;
    }
    
    //adiciona fichas... usado quando o jogador vence
    public void addDinheiro(int dinheiro) {
		this.dinheiro += dinheiro;
	}
    
    /* Retorna o vetor de cartas
     * Utilizado para tratar a mao do jogador
     */
    public List<Carta> getCartas()
    {
        return this.cartas;
    }
    
    public boolean isBot()
	{
		return false;
	}
    
    public String getNome()
    {
        return this.nome;
    }
    
    public boolean isAllIn()
    {
    	return this.allIn;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append(nome);
        
        if (cartas != null)
        	sb.append(cartas.toString());
        
        return sb.toString();
    }

	public int getDinheiro() {
		return dinheiro;
	}

	public void setAllIn(boolean allIn) {
		this.allIn = allIn;
	}
	
	public void setIsFold(boolean isFold) {
		this.isFold = isFold;
	}
	
	public boolean getIsFold() {
		return this.isFold;
	}
	
	public void reset()
	{
		cartas = null;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof Jogador))
			return false;
		
		Jogador j = (Jogador) o;
		
		if (j.nome.equals(this.nome))
			return true;
		
		return false;
	}
}
a\