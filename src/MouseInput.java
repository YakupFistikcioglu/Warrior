import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class MouseInput extends MouseAdapter{
	
	private Handler handler ; 
	private Camera camera ;
	private Game game ; 
	
	public MouseInput(Handler handler , Camera camera  , Game game) {
		this.handler = handler ;
		this.camera = camera ;
		this.game = game ; 
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		int mx = (int) (e.getX() + camera.getX()) ; 
		int my = (int) (e.getY() + camera.getY()) ; 
		
		for(int i = 0 ; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i) ; 
			if(game.ammo == 0) {
				JOptionPane.showMessageDialog(game , "Mermin bitti") ;
				System.exit(0);
			}
			if(tempObject.getId() == ID.Player && game.ammo >= 1) {
				handler.addObject(new Bullet(tempObject.getX() + 16 , tempObject.getY() + 24, ID.Bullet , handler , mx , my)) ;
				game.ammo-- ; 
			}
		}
	}
}
