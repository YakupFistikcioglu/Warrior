
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window {
    
    public Window(int width , int height , String title , Game game){
        
        JFrame jframe = new JFrame(title);
        
        jframe.add(game) ;
    //  Pencere Boyutland�rma
        jframe.setPreferredSize(new Dimension(width , height)) ; 
        jframe.setMaximumSize(new Dimension(width , height)) ; 
        jframe.setMinimumSize(new Dimension(width , height)) ;
        jframe.setLocationRelativeTo(null);// Ba�langi�i merkez olarak ayarlar.
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setVisible(true);
        
    }
    
}
