package william.miranda.poker.model;

/*
 * Classe que implementa funcoes utilitarias
 */
public class Utils
{
	public static long fatorial(int n)
	{
		long res = 1;
		
		for (long i=1 ; i <= n ; i++)
		{
			res *= i;
		}
		
		return res;
	}
	
	//faz a Combinacao(n,p)
	public static long combinacao(int n, int p)
	{
		//obtem a maior parte do denominador, para fazer a simplificacao
		int valorCortado;
		int valorNaoCortado;
		
		if ( (n-p) > p)
		{
			valorCortado = n-p;
			valorNaoCortado = p;
		}
		else
		{
			valorCortado = p;
			valorNaoCortado = n-p;
		}
		
		//sabemos qual parte iremos "cortar", entao reduzimos o numerador, que vai de N ate o valorCortado
		long numerador = 1;
		
		for (int i=n ; i>valorCortado ; i--)
		{
			numerador *= i;
		}
		
		//calcula o fatorial do que sobrou no denominador 
		long denominador = fatorial(valorNaoCortado);
		
		//retorna o valor da divisao
		return numerador / denominador;
	}
}
