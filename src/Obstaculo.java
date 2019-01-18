import java.awt.Graphics;
import java.awt.Rectangle;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;

public class Obstaculo implements Observer {
	private int x;
	private int y;
	private int width;
	private int height;
	private int vida;
	private String path;
	private ImageIcon image;
	private Rectangle rectangulo;
	
	public Obstaculo(int x, int y, int width, int height, String path){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		vida=10;
		this.path=path;
		image = createImageIcon(path);
		rectangulo = new Rectangle(x,y,width,height);
		
		if(path=="/wall2.png")
			Juanito.getInstance().getMychancla().addObserver(this);
	}	
	
	//Collision methods
	public boolean isColliding() {
		if(rectangulo.intersects(Juanito.getInstance().getRectangulo())) {			
			return true;
		}
		return false;
	}
	
	public void setCollisions() {
        if(Juanito.getInstance().getRectangulo().getX() <= x+width && Juanito.getInstance().getRectangulo().getX() >= x)
    		Juanito.getInstance().setCl(true);
        else
        	Juanito.getInstance().setCl(false);
        if(Juanito.getInstance().getRectangulo().getX()+Juanito.getInstance().getRectangulo().getWidth() >= x && Juanito.getInstance().getRectangulo().getX()+Juanito.getInstance().getRectangulo().getWidth() <= x+width)
    		Juanito.getInstance().setCr(true);
        else
        	Juanito.getInstance().setCr(false);
        if(path!="/wall2.png") {
        	if(Juanito.getInstance().getRectangulo().getY()+Juanito.getInstance().getRectangulo().getHeight() >= y && Juanito.getInstance().getRectangulo().getY()+Juanito.getInstance().getRectangulo().getHeight() <= y+height) {
        		Juanito.getInstance().setCd(true); 
        		Juanito.getInstance().setCl(false);
        		Juanito.getInstance().setCr(false);
            }
            else
            	Juanito.getInstance().setCd(false);
        }
	}
	
	
	//Drawing method
	public void draw(Graphics g) {
		image.paintIcon(null, g, this.x, this.y);
		//g.drawRect(x, y, width, height);
	}
	
	//Getter & setters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		rectangulo.setLocation(this.x, this.y);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		rectangulo.setLocation(x, y);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ImageIcon getImage() {
		return image;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}

	public Rectangle getRectangulo() {
		return rectangulo;
	}
	
	public void setRectangulo(Rectangle rectangulo) {
		this.rectangulo = rectangulo;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o==Juanito.getInstance().getMychancla()) {
			if(Juanito.getInstance().getMychancla().colliding(rectangulo)) {
				y+=30;
				vida--;	
				Juanito.getInstance().setPuntaje(Juanito.getInstance().getPuntaje()+500);
				rectangulo.setLocation(x, y);
			}
		}
		
	}
	
	//JAR adapted imageicon creator
	protected ImageIcon createImageIcon(String path) {
		URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}