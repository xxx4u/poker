package william.miranda.poker.activities;

import william.miranda.poker.R;
import william.miranda.poker.model.Carta;
import william.miranda.poker.model.Carta.Naipe;
import william.miranda.poker.view.ViewUtils;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

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
		
		
		//cria o corpo do layout
		ScrollView sv = new ScrollView(this);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		
		//cria um ImageView com um drawable
		TextView tv = new TextView(this);
		tv.setText("Texto de teste!!!");
		
		ImageView iv1 = new ImageView(this);
		Bitmap bmp = ViewUtils.getBitmap(new Carta(5, Naipe.COPAS));
		iv1.setImageBitmap(bmp);
		iv1.setScaleX(2);
		iv1.setScaleY(2);
		
		ImageView iv2 = new ImageView(this);
		iv2.setImageResource(R.drawable.ic_launcher);
		
		sv.addView(ll);
		ll.addView(tv);
		ll.addView(iv1);
		ll.addView(iv2);
				
		setContentView(sv);
	}
}
