package william.miranda.poker.activities;

import william.miranda.poker.R;
import william.miranda.poker.view.ViewUtils;
import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		/* Armazena o Contexto estaticamente */
		ViewUtils.context = this;
		
		//teremos um Singleton da classe poker game
		//PokerGame pokerGame = PokerGame.getInstance();
		
		//para cada rodada, inicializaremos os atributos de PokerGame
		//pokerGame.prepararNovaRodada();
		
		setContentView(R.layout.layout_mesa);
	}
}
