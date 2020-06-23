
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window {
    
    public Window(int width , int height , String title , Game game){
        
        JFrame jframe = new JFrame(title);
        
        jframe.add(game) ;
    //  Pencere Boyutlandýrma
        jframe.setPreferredSize(new Dimension(width , height)) ; 
        jframe.setMaximumSize(new Dimension(width , height)) ; 
        jframe.setMinimumSize(new Dimension(width , height)) ;
        jframe.setLocationRelativeTo(null);// Baþlangiçi merkez olarak ayarlar.
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setVisible(true);
        
    }
    
}
