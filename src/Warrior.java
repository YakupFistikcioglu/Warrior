
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JOptionPane;


public class Warrior extends GameObject{
    
    Handler handler ;
    Game game ; 
    
    public Warrior(int x, int y, ID id, Handler handler , Game game) {
        super(x, y, id);
        this.game = game ;
        this.handler = handler ;
    }

    @Override
    public void tick() {
        x += velX ; 
        y += velY ; 
        
        collision();
        
        //Geçiþlerin gecikmesiz olmasý için
        if(handler.isUp() == true) velY = -5 ;  
        else if(handler.isDown() == false) velY = 0 ;
        
        
        if(handler.isDown() == true) velY = 5 ; 
        else if(handler.isUp() == false) velY = 0 ; 
        
        if(handler.isLeft() == true) velX = -5 ; 
        else if(handler.isRight() == false) velX = 0 ; 
        
        if(handler.isRight() == true) velX = 5 ; 
        else if(handler.isLeft() == false) velX = 0 ; 
    }
    
    public void collision() {
    	for(int i = 0 ; i < handler.object.size() ; i++) {
    		
    		GameObject tempObject = handler.object.get(i) ; 
    		
    		if(tempObject.getId() == ID.Block) {
    			
    			if(getBounds().intersects(tempObject.getBounds())) {
    				x += velX * -1 ; 
    				y += velY * -1 ; 
    			}
    		}
    		if(game.hp == 0) {
    			JOptionPane.showMessageDialog(game , "Dead") ; 
    	        System.exit(0) ; 
    		}
    		if(tempObject.getId() == ID.Enemy) {
    			
    			if(getBounds().intersects(tempObject.getBounds())) {
    				game.hp-- ;
    				}
    			}
    		}
    	}
 
    
    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE) ; 
        g.fillRect(x , y , 32 , 48) ; 
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x , y , 32 , 48) ; 
    }
    
}
