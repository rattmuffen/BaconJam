package st.rattmuffen.baconjam.game;

import it.marteEngine.entity.Entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends Entity {

	public int width = 32;
	public int height = 32;
	
	public float yDelta = 4f;
	
	public float angle = 0.0f;
	
	public float radius = 300f;
	public float rotation = 0f;
	
	Level level;
	
	public int score = 0;
	
	int stopDuration = 500;
	int stopTime = 0;
	
	int fasterDuration = 1000;
	int fasterTime = 0;
	
	int time;
	boolean stopped = false;
	boolean faster = false;
	
	public float rotationSpeed;
	
	public float maxStamina = 100f;
	public float currentStamina = maxStamina;
	
	public Player(float x, float y, Level l) {
		super(x, y);
		
		centered = true;
		collidable = true;
		
		addType(PLAYER);
		setHitBox(0, 0, 32, 32);
		
		this.level = l;
		this.radius = level.radius;
		this.rotationSpeed = level.rotationSpeed;
		
		radius+=y;
	}
	
	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		
		time+=delta;

		collideInto("ENEMY", x, y);
		collideInto("POWERUP",x,y);
		
		angle = (float) Math.toDegrees(Math.atan( (y - container.getHeight()) / (x - container.getWidth()/2)));
		setAngle((int) angle);
		
	
		if (angle < 0 && rotation > 5) {
			rotation = 1;
		}
		
		
		
		Input i = container.getInput();
		

		if (i.isKeyDown(Input.KEY_UP) && radius < (level.rainbowRadius + level.rainbowWidth)) {
			radius+=yDelta;
		}
		
		if (i.isKeyDown(Input.KEY_DOWN) && radius > level.rainbowRadius) {
			radius-=yDelta;
		}

		y = (container.getHeight() + ((float) Math.cos(rotation) * radius));
		x = (container.getWidth()/2 + ((float) Math.sin(rotation) * radius));

		if (stopped) {
			rotationSpeed = level.rotationSpeed*2f;
			
			if (Math.abs(stopTime - time) >= stopDuration) {
				stopped = false;
				rotationSpeed = level.rotationSpeed;
			}
		} 
		
		
		if (faster && !stopped) {
			rotationSpeed = -level.rotationSpeed/2f;
			
			if (Math.abs(fasterTime - time) >= fasterDuration) {
				faster = false;
				rotationSpeed = level.rotationSpeed;
			}
		}

		if (i.isKeyDown(Input.KEY_LSHIFT) && currentStamina > 0) {
			currentStamina -= 1f;
			
			if (rotationSpeed >0)
				rotationSpeed = -rotationSpeed;
			
			rotation-=rotationSpeed/300;
		} else {
			if (currentStamina<maxStamina) {
				currentStamina+=0.5f;
			}
			
			rotation-=rotationSpeed/200;
		}
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.rotate(x, y, angle);
		g.fillRect(x, y, width, height);
		g.resetTransform();
	}

	public void setStop() {
		stopTime = time;
		stopped = true;
	}

	public void setFaster() {
		fasterTime = time;
		faster = true;
	}
}
