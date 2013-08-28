package william.miranda.poker.model;

/**
 * Define uma Carta, contendo o numero e o naipe
 * É gerada a partir de um numero entre [0;51]
 */
public class Carta implements Comparable<Object>
{
    public static enum Naipe {COPAS, OUROS, PAUS, ESPADAS};
    
    private byte numero;
    private Naipe naipe;
    
    /**
     * construtor da classe Carta
     * Recebe um numero que pertence ao intervalo [0,51]
     * e faz a divisao por 4
     * O resto da o Naipe e o Quociente da o valor
     */
    public Carta(int num)
    {
        int q = (int) (num/4);
        int r = num%4;
        
        numero = (byte) (q+1);
        
        switch (r)
        {
            case 0:
                naipe = Naipe.COPAS;
                break;
            
            case 1:
                naipe = Naipe.OUROS;
                break;
                
            case 2:
                naipe = Naipe.PAUS;
                break;
                    
            case 3:
                naipe = Naipe.ESPADAS;
                break;
        }
    }
    
    //Sobrecarga para criar a carta dado a numero e o naipe
    public Carta(int numero, Naipe naipe)
    {
        this.numero = (byte) numero;
        this.naipe = naipe;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Carta))//se nao eh uma carta
        {
            return false;
        }
        
        Carta c = (Carta) o;
        
        if ((this.getNumero() == c.getNumero()) && (this.getNaipe().ordinal() == c.getNaipe().ordinal()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /*
     * Retorna ao numero gerador da Carta, entre [0;51]
     */
    @Override
    public int hashCode()
    {
        int num = ((this.numero-1) * 4) + this.naipe.ordinal();
        return num;
    }
    
    @Override
    public String toString()
    {
        return numero + " de " + naipe.toString();
    }

	@Override
	//usado para ordenacao
	public int compareTo(Object o) throws ClassCastException
	{
		//se nao for uma carta, da erro
		if (!(o instanceof Carta))
			throw new ClassCastException();
		
		//se for carta
		Carta tmp = (Carta) o;
		
		if (this.numero > tmp.getNumero())
			return 1;
		else if (this.numero < tmp.getNumero())
			return -1;
		else
			return 0;
	}
	
	/* usado para comparar o valor das cartas na ordem de "valor" => A > K > Q > J > ... > 3 > 2
	 * Se c1 > c2, retorna +1
	 * Se c1 = c2, retorna  0
	 * Se c1 < c2, retorna -1
	 */
	public static int comparar(Carta c1, Carta c2)
	{
		if (c1.getNumero() == c2.getNumero())
			return 0;
		
		if (c1.getNumero() == 1)
			return 1;
		
		if (c2.getNumero() == 1)
			return -1;
		
		if (c1.getNumero() > c2.getNumero())
			return 1;
		else
			return -1;
	}
	
	
	//gets
    public byte getNumero()
    {
        return this.numero;
    }
    
    public Naipe getNaipe()
    {
        return this.naipe;
    }
}
