package st.rattmuffen.baconjam.game;

import it.marteEngine.ME;
import it.marteEngine.World;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Level extends World {

	
	
	Image rainbow,bg;
	float rainbowRadius = 300f;

	Player p;

	float rotation = 0;
	float rotationSpeed = -1.0f;
	
	float speed = 1.0f;
	float radius = 300.0f;
	public float rainbowWidth = 100f;
	
	public int time = 0;
	public int lastAddedTime = 0;

	public Level(int id, GameContainer container) {
		super(id, container);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.init(container, game);

		rainbow = new Image("res/gfx/rainbow.png");
		bg = new Image("res/gfx/bg.png");

		p = new Player(100, 0, this);
		
		Enemy e = new Enemy(200, 50, this);

		this.add(p);
		this.add(e);

		ME.world = this;
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)	throws SlickException {
		super.update(container, game, delta);
		time+=delta;
		
		rotationSpeed-=0.0001f;
		
		if (Math.abs(lastAddedTime-time)>Math.abs(2000/rotationSpeed)) {
			if (Math.random() < 0.2) {
				Enemy e = new Enemy(0, (float)Math.random()*rainbowWidth, this);
				this.add(e);
			} else {
				Powerup p = new Powerup(0, (float)Math.random()*rainbowWidth, this);
				this.add(p);
			}
			
			lastAddedTime = time;
		}
		
		Input i = container.getInput();
		
		if (i.isKeyPressed(Input.KEY_F1)) {
			Enemy e = new Enemy(0, (float)Math.random()*rainbowWidth, this);
			this.add(e);
		}
		
		if (i.isKeyPressed(Input.KEY_F2)) {
			Powerup p = new Powerup(0, (float)Math.random()*rainbowWidth, this);
			this.add(p);
		}

		if (i.isKeyPressed(Input.KEY_W)) {
			rotationSpeed*=1.2f;
		} else if (i.isKeyPressed(Input.KEY_S)) {
			rotationSpeed*=0.8f;
		}


		rotation = rainbow.getRotation() + rotationSpeed;

		rainbow.setRotation(0);
		rainbow.setRotation(rotation);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		g.drawImage(bg,0,0);
		
		
		g.setColor(Color.white);
		
		g.drawString("FPS: " + container.getFPS(), 0, 0);
		g.drawString("Rainbow speed: " + rotationSpeed, 0, 15);
		
		g.drawString("player rotation: " + p.rotation, 0, 45);

		g.drawString("Player x: " + p.x, 0, 60);
		g.drawString("Player y: " + p.y, 0, 75);
		g.drawString("Player angle: " + p.angle, 0, 90);
		g.drawString("Player radius: " + radius, 100, 105);
		
		g.drawString("player time: " + p.time, 0, 135);
		g.drawString("player stop: " + p.stopped, 0, 150);
		g.drawString("player faster: " + p.faster, 0, 165);
		
		rotation+=rotationSpeed;

//		g.rotate(container.getWidth()/2, container.getHeight(), rotation);
		
//		p.angle = rotation/4;
		
		
		g.drawImage(rainbow, 0, 200);



		g.drawLine(p.x, p.y, container.getWidth()/2,container.getHeight());
		
		g.setColor(Color.green);
		g.fillRect(container.getWidth() - 100, 20, (p.currentStamina/p.maxStamina)*100f, 20);
		


		super.render(container, game, g);
		

//		g.resetTransform();
	}

}
