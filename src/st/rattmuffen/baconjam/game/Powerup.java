package st.rattmuffen.baconjam.game;

import it.marteEngine.ME;
import it.marteEngine.entity.Entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Powerup extends RotatingEntity {

	public Powerup(float x, float y, Level l) {
		super(x, y, l);
		
		
		width = 16;
		height = 16;
		addType("POWERUP");
		
		setHitBox(0, 0, width, height);
	}
	
	
	@Override
	public void render(GameContainer c, Graphics g) throws SlickException {
		g.rotate(x, y, -angle);
		
		g.setColor(Color.green);
		g.fillRect(x, y, width, height);
		g.setColor(Color.white);
		
		g.resetTransform();
	}
	
	@Override
	public void collisionResponse(Entity other) {
		if (other instanceof Player) {
			Player p = (Player) other;
			
			p.setFaster();			
			ME.world.remove(this);
			
		}
	}
	
	

}
