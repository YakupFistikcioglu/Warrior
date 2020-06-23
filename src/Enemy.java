import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends GameObject{

	private Handler handler ;
	Random r = new Random() ; 
	int choose = 0  ;
	int hp = 100 ;
	
	public Enemy(int x, int y, ID id , Handler handler) {
		super(x, y, id);
		this.handler = handler; 
		     
	}

	@Override
	public void tick() {
		x += velX ; 
		y += velY ; 
		
		//random olarak hareket etmelerini saglar...
		
		choose = r.nextInt(10) ; 
		
		for(int i = 0 ; i < handler.object.size() ; i++) {
			
			GameObject tempObject = handler.object.get(i); 
			
			if(tempObject.getId() == ID.Block) {
				// blocklar ile enemyler çarpýþma...
				if(getBoundsBig().intersects(tempObject.getBounds())) {
					x += (velX * 5) * -1 ; //Algoritma
					y += (velY * 5) * -1 ; //Algoritma
					
					velX *= -1 ; //Algoritma
					velY *= -1 ;  //Algoritma
				}
				else if(choose == 0) {
					velX = (r.nextInt(4 - -4) + -4) ; //Algoritma
					velY = (r.nextInt(4 - -4) + -4) ; //Algoritma
				}
			}
			if(tempObject.getId() == ID.Bullet) {
				if(getBounds().intersects(tempObject.getBounds())) {
					hp -= 50 ; 
					if(hp <= 0) {
						handler.removeObject(this);
					}
					handler.removeObject(tempObject);
				}
			}
		}
		
		
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x , y , 32 , 32) ;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x , y , 32 , 32);
	}
	public Rectangle getBoundsBig() {
		return new Rectangle(x - 16 , y - 16 , 64 , 64) ;
	}

}
