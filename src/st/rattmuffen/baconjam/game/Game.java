package st.rattmuffen.baconjam.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {

	public Game(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		Level l = new Level(1, container);
		
		addState(l);
	}
	
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new Game("BaconGameJam"));
			
			app.setDisplayMode(800, 600, false);
			app.setAlwaysRender(true);
			app.setUpdateOnlyWhenVisible(false);
			
			app.setShowFPS(true);
			app.setTargetFrameRate(60);
			app.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
