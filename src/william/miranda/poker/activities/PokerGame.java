package william.miranda.poker.activities;

import william.miranda.poker.controller.Utils;
import william.miranda.poker.model.Carta;
import william.miranda.poker.view.ViewUtils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class PokerGame extends Game
{
	OrthographicCamera camera;
	Rectangle bucket;
	SpriteBatch batch;
	
	Texture carta;
	
	AssetManager aManager;
	
	public void bla()
	{
		aManager = new AssetManager();
		Texture.setEnforcePotImages(false);
	}
	
	@Override
	public void create()
	{
		bla();
		
		//create the camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 480, 800);
		
		batch = new SpriteBatch();

		Carta c = new Carta(13, Carta.Naipe.OUROS);
		
		FileHandle handle = Gdx.files.internal(ViewUtils.getResourceName(c));
		Utils.Log(handle.exists());
		
		carta = new Texture(handle);
	}

	@Override
	public void dispose()
	{
		batch.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render()
	{
		super.render();
		
		Gdx.gl.glClearColor(0, 0.2f, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		//tell the camera to update its matrices.
		camera.update();
		
		//tell the SpriteBatch to render in the
		//coordinate system specified by the camera.
		batch.setProjectionMatrix(camera.combined);
		
		//begin a new batch and draw the bucket and all drops
		batch.begin();
		
		batch.draw(carta, 230, 100);
		
		batch.end();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

}
