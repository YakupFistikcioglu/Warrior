import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject{

	private Handler handler ; 
	
	public Bullet(int x, int y, ID id , Handler handler ,int mx ,int my) {
		super(x, y, id);
		this.handler = handler ; 
		//mermi hizi ayarlama
		velX = (mx - x) / 30;
		velY = (my - y) / 30; 
	}

	@Override
	public void tick() {
		x += velX ; 
		y += velY ; 
		
		collision() ; 
		
	}
	
	public void collision() {
		
		for(int i = 0 ; i < handler.object.size() ; i++) {
			
			GameObject tempObject = handler.object.get(i) ; 
			
			if(tempObject.getId() == ID.Block) {
				
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(this);
				}
				
			}
			
		}
		
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x , y , 10 , 10) ; 
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x , y , 10 , 10);
	}
	
}
