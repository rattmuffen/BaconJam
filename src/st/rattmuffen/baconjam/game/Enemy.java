package st.rattmuffen.baconjam.game;

import it.marteEngine.ME;
import it.marteEngine.entity.Entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Enemy extends RotatingEntity {

	public Enemy(float x, float y, Level l) {
		super(x, y, l);
		
		setHitBox(0, 0, 32, 32);
		width = 32;
		height = 32;

		addType("ENEMY");
		
	}
	
	
	@Override
	public void render(GameContainer c, Graphics g) throws SlickException {
		g.rotate(x, y, -angle);
		
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
		g.setColor(Color.white);
		
		g.drawString(rotation+"",x,y-20);
		
		g.resetTransform();
	}
	
	@Override
	public void collisionResponse(Entity other) {
		if (other instanceof Player) {
			Player p = (Player) other;
			
			if (!p.stopped)
				p.setStop();
			
			ME.world.remove(this);
		}
	}

}
