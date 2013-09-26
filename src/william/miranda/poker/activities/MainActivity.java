package william.miranda.poker.activities;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
        initialize(new PokerGame(), setConfig());
	}
	
	private AndroidApplicationConfiguration setConfig()
	{
		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		
        cfg.useGL20 = false;
        cfg.useAccelerometer = false;
        cfg.useCompass = false;
        
        return cfg;
	}

}
