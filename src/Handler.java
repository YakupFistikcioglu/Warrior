
import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
    
    LinkedList<GameObject> object = new LinkedList<GameObject>() ;
    
    private boolean up = false , down = false , right = false , left = false ; 
    
    //Yeni nesneleri olu�turma
    public void tick() {
        for(int i = 0 ; i < object.size() ; i++){
            GameObject tempObject = object.get(i) ; 
            
            tempObject.tick();
        }
    }
    //Yeni nesneleri �izme
    public void render(Graphics g) {
        for(int i = 0 ; i < object.size() ; i++){
            GameObject tempObject = object.get(i) ; 
            
            tempObject.render(g);
        }    
    }
    //Yeni nesneleri ekleme
    public void addObject(GameObject tempObject){
        object.add(tempObject) ; 
    }
    //Eklenen veya olu�turulan nesneleri silme
    public void removeObject(GameObject tempObject){
        object.remove(tempObject) ; 
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
    
}
