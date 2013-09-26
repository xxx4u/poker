package william.miranda.poker.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Baralho utilizado na partida....
 * Sorteia objetos do tipo Carta
 */
public class Baralho
{
    public static final short MAX_CARTAS = 52;
    private ArrayList<Integer> cartasDisponiveis;
    
    public Baralho()
    {
        cartasDisponiveis = new ArrayList<Integer>();
        
        //adiciona todas as cartas no vetor
        for (int i = 0; i < MAX_CARTAS ; i++)
        {
            cartasDisponiveis.add(i);
        }
    }
    
    public ArrayList<Integer> getCartasDisponiveis()
    {
        return cartasDisponiveis;
    }
    
    public Carta sortearCarta()
    {
        if (cartasDisponiveis.isEmpty())
        {
            return null;
        }
        
        Random r = new Random();
        
        //sorteia a posicao no vetor de cartas disponiveis
        byte randomPos = (byte) r.nextInt(cartasDisponiveis.size());
        
        //obtem o elemento armazenado na posicao sorteada
        int tmp = cartasDisponiveis.get(randomPos);
        
        //remove a carta sorteada
        cartasDisponiveis.remove(randomPos);
        
        //cria e retorna a nova Carta
        return new Carta(tmp);
    }
}
