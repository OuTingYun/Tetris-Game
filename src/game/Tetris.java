package game;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
abstract class Tetris {
	public Tetris_id id; 
	int id_num=0;
	int row_posi;
	public int intial_x=0;
	public int intial_y=0;
	public int r_center=1;
	public Color color=new Color(0,0,0) ;
	public List<conponent_xy> con_te;
	public int size=30;
	public int r_count=1;
	abstract public void rotate();
	public void move_down(){
		con_te.stream().forEach(x->x.setY(x.getY()+size));
	}
	public void move_l() {
		con_te.stream().forEach(x->x.setX(x.getX()-size));
	}
	public void move_r() {
		con_te.stream().forEach(x->x.setX(x.getX()+size));
		
	}
	public void draw(Graphics g,JPanel panel) {
		con_te.stream().forEach(x->x.draw(g));
		}
	public  void ResetColor() {
		con_te.forEach(x->x.setColor(color));
	}

}
class JC extends Tetris {
	JC(int initial_x,int initial_y,Tetris_id id){
		id_num=7;
		this.id=id;
		color=id.color;
		this.intial_x=initial_x;
		this.intial_y=initial_y;
		con_te= new ArrayList<conponent_xy>();
		con_te.add(new conponent_xy(initial_x,initial_y-size,color,id.arc));
		con_te.add(new conponent_xy(initial_x,initial_y-size,color,id.arc));
		con_te.add(new conponent_xy(initial_x,initial_y-size,color,id.arc));
		con_te.add(new conponent_xy(initial_x,initial_y-size,color,id.arc));
		
	}
	public void rotate() {
		
	}
	
}

class Z extends Tetris{
	Z(int initial_x,int initial_y,Tetris_id id){
		id_num=5;
		this.id=id;
		color=id.color;
		this.intial_x=initial_x;
		this.intial_y=initial_y;
		
		con_te= new ArrayList<conponent_xy>();
		con_te.add(new conponent_xy(initial_x,initial_y-size,color,id.arc));
		con_te.add(new conponent_xy(initial_x+30,initial_y-size,color,id.arc));
		con_te.add(new conponent_xy(initial_x+30,initial_y,color,id.arc));
		con_te.add(new conponent_xy(initial_x+60,initial_y,color,id.arc));
	}

	public void rotate() {
		int x=con_te.get(r_center).getX();
		int y=con_te.get(r_center).getY();
		if(r_count==1) {
			con_te.get(0).setX(x);
			con_te.get(0).setY(y-size);
			con_te.get(2).setX(x-size);
			con_te.get(2).setY(y);
			con_te.get(3).setX(x-size);
			con_te.get(3).setY(y+size*1);
			r_count=2;
		}
		else if(r_count==2){
			con_te.get(0).setX(x-size);
			con_te.get(0).setY(y);
			con_te.get(2).setX(x);
			con_te.get(2).setY(y+size);
			con_te.get(3).setX(x+size);
			con_te.get(3).setY(y+size);
			r_count=1;
		}
	}
}
class S extends Tetris{
	S(int initial_x,int initial_y,Tetris_id id){	
		id_num=6;
		this.id=id;
		color=id.color;
		this.intial_x=initial_x;
		this.intial_y=initial_y;
		con_te= new ArrayList<conponent_xy>();
		con_te.add(new conponent_xy(initial_x,initial_y,color,id.arc));
		con_te.add(new conponent_xy(initial_x+30,initial_y,color,id.arc));
		con_te.add(new conponent_xy(initial_x+30,initial_y-size,color,id.arc));
		con_te.add(new conponent_xy(initial_x+60,initial_y-size,color,id.arc));
	
	}
	public void rotate() {
		int x=con_te.get(r_center).getX();
		int y=con_te.get(r_center).getY();
		if(r_count==1) {
			con_te.get(0).setX(x);
			con_te.get(0).setY(y-size);
			con_te.get(2).setX(x+size);
			con_te.get(2).setY(y);
			con_te.get(3).setX(x+size);
			con_te.get(3).setY(y+size*1);
			r_count=2;
		}
		else if(r_count==2){
			con_te.get(0).setX(x-size);
			con_te.get(0).setY(y);
			con_te.get(2).setX(x);
			con_te.get(2).setY(y-size);
			con_te.get(3).setX(x+size);
			con_te.get(3).setY(y-size);
			r_count=1;
		}
	}
}
class J extends Tetris{

	J(int initial_x,int initial_y,Tetris_id id){	
	id_num=2;
		this.id=id;
		color=id.color;	
		this.intial_x=initial_x;
		this.intial_y=initial_y;
		con_te= new ArrayList<conponent_xy>();
		con_te.add(new conponent_xy(initial_x,initial_y-size*1,color ,id.arc));
		con_te.add(new conponent_xy(initial_x,initial_y,color,id.arc));
		con_te.add(new conponent_xy(initial_x+30,initial_y,color,id.arc));
		con_te.add(new conponent_xy(initial_x+60,initial_y,color,id.arc));
	
	}
	public void rotate() {
		int x=con_te.get(r_center).getX();
		int y=con_te.get(r_center).getY();
		if(r_count==1) {
			con_te.get(0).setX(x+size);
			con_te.get(0).setY(y);
			con_te.get(2).setX(x);
			con_te.get(2).setY(y+size);
			con_te.get(3).setX(x);
			con_te.get(3).setY(y+size*2);
			r_count=2;
		}
		else if(r_count==2){
			con_te.get(0).setX(x);
			con_te.get(0).setY(y+size);
			con_te.get(2).setX(x-size);
			con_te.get(2).setY(y);
			con_te.get(3).setX(x-size*2);
			con_te.get(3).setY(y);
			r_count=3;
		}
		else if(r_count==3){
			con_te.get(0).setX(x-size);
			con_te.get(0).setY(y);
			con_te.get(2).setX(x);
			con_te.get(2).setY(y-size);
			con_te.get(3).setX(x);
			con_te.get(3).setY(y-size*2);
			r_count=4;
		}	
		else if(r_count==4){
			con_te.get(0).setX(x);
			con_te.get(0).setY(y-size);
			con_te.get(2).setX(x+size);
			con_te.get(2).setY(y);
			con_te.get(3).setX(x+size*2);
			con_te.get(3).setY(y);
			r_count=1;
		}
	}
}
class L extends Tetris{

	L(int initial_x,int initial_y,Tetris_id id){	
		id_num=3;
		this.id=id;
		color=id.color;		
		this.intial_x=initial_x;
		this.intial_y=initial_y;
		con_te= new ArrayList<conponent_xy>();
		con_te.add(new conponent_xy(initial_x,initial_y,color,id.arc));
		con_te.add(new conponent_xy(initial_x+30,initial_y,color,id.arc));
		con_te.add(new conponent_xy(initial_x+60,initial_y,color,id.arc));
		con_te.add(new conponent_xy(initial_x+60,initial_y-size*1,color,id.arc));
	}
	public void rotate() {
		int x=con_te.get(r_center).getX();
		int y=con_te.get(r_center).getY();
		if(r_count==1) {
			con_te.get(0).setX(x-size);
			con_te.get(0).setY(y);
			con_te.get(2).setX(x+size);
			con_te.get(2).setY(y);
			con_te.get(3).setX(x+size);
			con_te.get(3).setY(y-size);
			r_count=2;
		}
		else if(r_count==2){
			con_te.get(0).setX(x);
			con_te.get(0).setY(y-size);
			con_te.get(2).setX(x);
			con_te.get(2).setY(y+size);
			con_te.get(3).setX(x+size);
			con_te.get(3).setY(y+size);
			r_count=3;
		}
		else if(r_count==3){
			con_te.get(0).setX(x+size);
			con_te.get(0).setY(y);
			con_te.get(2).setX(x-size);
			con_te.get(2).setY(y);
			con_te.get(3).setX(x-size);
			con_te.get(3).setY(y+size);
			r_count=4;
		}	
		else if(r_count==4){
			con_te.get(0).setX(x);
			con_te.get(0).setY(y+size);
			con_te.get(2).setX(x);
			con_te.get(2).setY(y-size);
			con_te.get(3).setX(x-size);
			con_te.get(3).setY(y-size);
			r_count=1;
		}
	}
}
class T extends Tetris{	
	
	T(int initial_x,int initial_y,Tetris_id id){	
		id_num=1;
		this.id=id;
		color=id.color;		
		this.intial_x=initial_x;
		this.intial_y=initial_y;
		con_te= new ArrayList<conponent_xy>();
		con_te.add(new conponent_xy(initial_x,initial_y,color,id.arc));
		con_te.add(new conponent_xy(initial_x+30,initial_y,color,id.arc));
		con_te.add(new conponent_xy(initial_x+60,initial_y,color,id.arc));
		con_te.add(new conponent_xy(initial_x+30,initial_y-size*1,color,id.arc));
	}
	public void rotate() {
		int x=con_te.get(r_center).getX();
		int y=con_te.get(r_center).getY();
		if(r_count==1) {
			con_te.get(0).setX(x);
			con_te.get(0).setY(y+size);
			r_count=2;
		}
		else if(r_count==2){
			con_te.get(3).setX(x-size);
			con_te.get(3).setY(y);
			r_count=3;
		}
		else if(r_count==3){
			con_te.get(2).setX(x);
			con_te.get(2).setY(y-size);
			r_count=4;
		}	
		else if(r_count==4){
			con_te.get(2).setX(x+size);
			con_te.get(2).setY(y);
			con_te.get(0).setX(x-size);
			con_te.get(0).setY(y);
			con_te.get(3).setX(x);
			con_te.get(3).setY(y-size);
			r_count=1;
		}
	}
}
class O extends Tetris{
	O(int initial_x,int initial_y,Tetris_id id){	
		id_num=4;
		this.id=id;
		color=id.color;		
		this.intial_x=initial_x;
		this.intial_y=initial_y;
		con_te= new ArrayList<conponent_xy>();
		con_te.add(new conponent_xy(initial_x,initial_y,color,id.arc));
		con_te.add(new conponent_xy(initial_x+30,initial_y,color,id.arc));
		con_te.add(new conponent_xy(initial_x,initial_y-size*1,color,id.arc));
		con_te.add(new conponent_xy(initial_x+30,initial_y-size*1,color,id.arc));
	}
	public void rotate() {}
}
class I extends Tetris{
	I(int initial_x,int initial_y,Tetris_id id){	
		id_num=0;
		this.id=id;
		color=id.color;		
		this.intial_x=initial_x;
		this.intial_y=initial_y;
		con_te= new ArrayList<conponent_xy>();
		con_te.add(new conponent_xy(initial_x,intial_y,color,id.arc));
		con_te.add(new conponent_xy(initial_x,intial_y-size,color,id.arc));
		con_te.add(new conponent_xy(initial_x,intial_y-size*2,color,id.arc));
		con_te.add(new conponent_xy(initial_x,intial_y-size*3,color,id.arc));
	}
	public void rotate() {
		int x=con_te.get(r_center).getX();
		int y=con_te.get(r_center).getY();
		if(r_count==1) {
			for(int i=0;i<con_te.size();i++) {
				if(i==0) con_te.get(i).setX(x-size);
				else con_te.get(i).setX(x+size*(i-1));
				con_te.get(i).setY(y);
			}
			r_count=2;
		}
		else if(r_count==2){
			for(int i=0;i<con_te.size();i++) {
				if(i==0)con_te.get(i).setY(y+size);
				else con_te.get(i).setY(y-size*(i-1));
				con_te.get(i).setX(x);
			}
			r_count=1;
		}
	}
}
class conponent_xy{
	private boolean use; 
	private int x;
	private int y; 
	private Color color;
	public int arc;
	conponent_xy(int x,int y){
		this.x=x;
		this.y=y;
	}
	conponent_xy(int x,int y,Color color,int arc){
		this.arc=arc;
		this.x=x;
		this.y=y;
		this.color=color;
	}
	public void setColor(Color color) {
		this.color=color;
	}
	
	public Color getColor() {
		return color;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x=x;
	}
	public void setY(int y) {
		this.y=y;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRoundRect(x, y, 30, 30,arc,arc);
		g.setColor(Color.black);
		g.drawRoundRect(x, y, 30, 30, arc, arc);

	}

	public void sec_draw(Graphics g) {
		g.drawRoundRect(x, y, 30, 30, arc, arc);
	}
	public void setUse(boolean n) {
		use=n;
	}
	public boolean getUse() {
		return use;
	}
}
