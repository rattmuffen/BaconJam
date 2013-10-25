package st.rattmuffen.baconjam.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import it.marteEngine.World;

public class Level extends World {

	public Level(int id, GameContainer container) {
		super(id, container);
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.init(container, game);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		super.update(container, game, delta);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		super.render(container, game, g);
		
		
		g.drawString("OMG IT'S ALIVE!", this.container.getWidth()/2, this.container.getHeight()/2);
	}

}
