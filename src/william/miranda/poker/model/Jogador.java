package william.miranda.poker.model;

import java.util.ArrayList;

/**
 * Armazena as informações relevantes de um jogador
 * 
 */
public class Jogador
{
    private String nome;
    private ArrayList<Carta> cartas = null;
    
    //diz se o jogador deu Fold na rodada
    private boolean isFold;
    
    //quantas fichas o jogador possui no total
    private int dinheiro;
    
    /* quantas fichas o jogador possui apostado na mesa... ao final da Rodada, este valor eh adicionado
     * no Pot e esta variavel eh zerada */
    private int valorApostado;
    
    //flag que define se o jogador est� em All In
    private boolean allIn = false;
    
    //define em que posicao o jogador esta sentado na Mesa
    public int posicaoMesa;
    
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
    	//salva quanto pode ser apostado
		int valorAposta = valor;
		
    	//se precisa apostar mais do que tem, aposta tudo e marca como ALL IN
    	if (valor >= this.dinheiro)
    	{
    		//aposta tudo o que tem
    		valorAposta = this.dinheiro;
    		
    		//marca como ALL IN
    		setAllIn(true);
    		
    		//zera as fichas
    		this.dinheiro = 0;
    	}
    	else//se a aposta "cabe no bolso" 
    	{
    		//apenas remove o valor
    		this.dinheiro -= valorAposta;
    	}

		//adiciona as fichas na mesa
		this.valorApostado += valorAposta;
    	
    	//retorna quanto foi apostado
		return valorAposta;
    }
    
    //adiciona fichas... usado quando o jogador vence
    public void addDinheiro(int dinheiro) {
		this.dinheiro += dinheiro;
	}
    
    /* Retorna o vetor de cartas
     * Utilizado para tratar a mao do jogador
     */
    public ArrayList<Carta> getCartas()
    {
        return this.cartas;
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

	public int getValorApostado() {
		return valorApostado;
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
}
