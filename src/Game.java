
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;


public class Game extends Canvas implements Runnable {
    
    private static final long serialVersionUID = 1; 
    
    private boolean isRunning = false ; 
    private Thread thread ; 
    private Handler handler ;
    private Camera camera ; 
    private BufferedImage level = null ;
    
    
    public int hp = 100;  
    public int ammo = 100; 
    
    public Game(){
        new Window(1000 , 563 , "Warrior Game" , this);
        
        start();
        
        handler = new Handler() ;
        camera = new Camera(100 , 0) ; 
        
        this.addKeyListener(new KeyInput(handler)) ; 
                        
        BufferedImageLoader loader = new BufferedImageLoader(); 
        level = loader.loadImage("/WarriorLevel.png") ; 
        
        this.addMouseListener(new MouseInput(handler , camera , this));
        
        loadLevel(level);
        
    }
    
    
    private void start(){
        isRunning = true ; 
        thread = new Thread(this) ; 
        thread.start();
    }
     
    private void stop() {
        isRunning = false ; 
    //  Bir olayin bitmeden digerine geçmesini engeller  
        try{
            thread.join();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        this.requestFocus() ; 
        long lastTime = System.nanoTime() ; 
        double amountOfTicks = 60.0 ; 
        double ns = 1000000000 / amountOfTicks ; 
        double delta = 0 ; 
        long timer = System.currentTimeMillis();
        int frames = 0 ; 
        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns ;
            lastTime = now ; 
            while(delta >= 1){
                tick();
                delta--;
            }
            render();
            frames++;
            if(((System.currentTimeMillis()) - timer) > 1000){
                timer += 1000 ; 
                frames = 0 ; 
            }
        }
        stop();
    }
    //Render Ve Tick Yordamlarý Her 60 Milisaniyede bir çagirýlacaklar...
    public void tick(){
    	
    	for(int i = 0 ; i < handler.object.size() ; i++) {
    		if(handler.object.get(i).getId() == ID.Player) {
    			camera.tick(handler.object.get(i)) ; 
    		}
    	}
    	
        handler.tick();
    }
    
    public void render() {
    //  Kutularý savaþçýya göre hareket ettirme ve çizimlerin yapýlacagi yer
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3) ; 
            return ; 
        }
    //  Çizimlerin yapýlacagý yer O
        Graphics g = bs.getDrawGraphics();
    //  grafikleri 2 boyutluya çevirme
        Graphics2D g2d = (Graphics2D) g ; 
        
        g.setColor(Color.red);
        g.fillRect(0 , 0 , 1000 , 563);
        
        g2d.translate(-camera.getX(), -camera.getY());
        
        
        
        handler.render(g);
        
        g2d.translate(camera.getX(), camera.getY());
        
        g.setColor(Color.gray);
        g.fillRect(5, 5, 200, 32);
        g.setColor(Color.green);
        g.fillRect(5, 5, hp*2, 32);
        g.setColor(Color.black);
        g.drawRect(5, 5, 200, 32);
        
        g.setColor(Color.white);
        g.drawString("Can : "+hp, 5, 50);
        
        g.setColor(Color.white);
        g.drawString("Mermi: "+ammo, 5, 60);
     
        
        g.dispose();//  ?
        bs.show();
    }
    
    private void loadLevel(BufferedImage image) {
    	int w = image.getWidth(); 
    	int h = image.getHeight(); 
    	
    	for(int xx = 0 ; xx < w ; xx++) {
    		for(int yy = 0 ;  yy < h ; yy++) {
    			int pixel = image.getRGB(xx, yy) ; 
    			int red = (pixel >> 16) & 0xff ; 
    			int green = (pixel >> 8) & 0xff ; 
    			int blue = (pixel) & 0xff ; 
    			
    			if(red == 255) {
    				handler.addObject(new Block(xx*32 , yy*32 , ID.Block ));
    			}
    			if(blue == 255 ) {
    				handler.addObject(new Warrior(xx*32 , yy*32 , ID.Player , handler , this));
    			}
    			if(green == 255) {
    				handler.addObject(new Enemy(xx*32 , yy*32 , ID.Enemy , handler));
    			}
    		}
    	}
    }
   
    public static void main(String args[]){
        new Game() ;
    }

    
}
