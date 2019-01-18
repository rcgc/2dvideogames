import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

public class Obstaculos implements Observer {
	private ArrayList<Obstaculo> obstaculos;
	
	public Obstaculos() {
		obstaculos = new ArrayList<Obstaculo>();
		Juanito.getInstance().addObserver(this);
	}
	
	public void addObstaculo(int x, int y, int width, int height, String path){
		obstaculos.add(new Obstaculo(x, y, width, height, path));
	}
	
	@Override
	public synchronized void update(Observable o, Object arg) {
		if(o == Juanito.getInstance()) {
			ListIterator<Obstaculo> itr = obstaculos.listIterator();
			while(itr.hasNext()) {
				Obstaculo obs = itr.next();
				if(obs.isColliding()) {
					obs.setCollisions();
					return;
				}
				Juanito.getInstance().setCr(false);
				Juanito.getInstance().setCl(false);
				Juanito.getInstance().setCd(false);
			}
		}	
	}
	
	public void draw(Graphics g) {
		ListIterator<Obstaculo> apt = obstaculos.listIterator(); 
		while(apt.hasNext()) {
			Obstaculo obstaculo = apt.next();
			obstaculo.draw(g);
		}//While
	}
	public void avanzar(int n) {
		ListIterator<Obstaculo> apt = obstaculos.listIterator(); 
		
		while(apt.hasNext()) {
			Obstaculo obstaculo = apt.next();
			obstaculo.setX(obstaculo.getX()+n);
		}//While		
	}
	public void eliminar (int index) {
		ListIterator<Obstaculo> apt = obstaculos.listIterator(); 
		while(apt.hasNext()) {
			Obstaculo obstaculo = apt.next();
			if(obstaculo instanceof Obstaculo) {obstaculos.remove(index);}
		}//While
	}
	
	public boolean level2done() {
		ListIterator<Obstaculo> apt = obstaculos.listIterator(); 
		
		Obstaculo wall = null;
		
		while(apt.hasNext()) {
			wall = apt.next();
		}
		
		if(wall!=null && wall.getVida()==0) {
			wall.setY(800);
			return true;
		}
		return false;
		
	}
	
	public void eliminarTodos () {
		obstaculos.clear();
	}
	
	//Getters & setters
	public ArrayList<Obstaculo> getObstaculos() {
		return obstaculos;
	}

	public void setObstaculos(ArrayList<Obstaculo> obstaculos) {
		this.obstaculos = obstaculos;
	}
}