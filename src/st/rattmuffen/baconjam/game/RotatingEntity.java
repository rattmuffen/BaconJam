package st.rattmuffen.baconjam.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import it.marteEngine.ME;
import it.marteEngine.entity.Entity;

public class RotatingEntity extends Entity {

	
	Level level;
	
	float xPos,yPos = 0;
	
	float radius;
	
	float rotation = 0;
	
	float angle = 0;
	
	public RotatingEntity(float x, float y, Level l) {
		super(x, y);
		

		
		this.collidable = true;
		this.level = l;
		this.centered = true;
		this.radius = level.rainbowRadius;
		radius+=y;
	}
	
	
	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		super.update(container, delta);
		
		
		angle = 0 - (float) Math.toDegrees(Math.atan( (y - container.getHeight()) / (x - container.getWidth()/2)));
		setAngle((int) angle);
		
		
		yPos = (container.getHeight() + ((float) Math.cos(rotation) * radius));
		xPos = (container.getWidth()/2 + ((float) Math.sin(rotation) * radius));

		y = yPos;
		x = xPos;
		
		rotation-=level.rotationSpeed/100;
		
		if ((y + height) > container.getHeight() && rotation > 5)
			ME.world.remove(this);
	}

}
