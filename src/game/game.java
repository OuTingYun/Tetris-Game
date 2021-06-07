package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class game extends KeyAdapter implements ActionListener {
	game() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}

class All_Layer {
	public JPanel bg_img = new JPanel();
	public JLabel img_l;
	public ImageIcon image;

	All_Layer() {
		bg_img.setLayout(new GridLayout(1, 1));
	}

	public JPanel set_bg(String location) {
		image = new ImageIcon(System.getProperty("user.dir")+"\\src\\game\\picture\\"+location);
		img_l = new JLabel(image);
		bg_img.add(img_l);
		bg_img.setOpaque(false);
		return bg_img;
	}

}

class Frame extends JFrame {
	public JLayeredPane layeredPane;
	int x;
	int y;
	int width;
	int height;
	String Name = "";
	int de;

	// ����layerPane
	public void add_jp(JPanel jp, Integer Layer) {
		layeredPane.add(jp, Layer);
	}

	public void add_jp(JPanel jp, Integer Layer, int i) {
		layeredPane.add(jp, Layer, i);
	}
	Frame(int x, int y, int width, int height, String Name, int de, JPanel bg_img) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.Name = Name;
		this.de = de;
		layeredPane = new JLayeredPane();
		layeredPane.add(bg_img, JLayeredPane.DEFAULT_LAYER);

		// ��閮剖��
		this.setLayeredPane(layeredPane);
		setTitle(Name);
		setDefaultCloseOperation(de);
		setBounds(x, y, width, height);
		setVisible(false);
	}
}

class canvas_place extends JPanel {
	int Now_Score = 0;
	int x;
	int y;
	int width;
	int height;
	int Plus_Score = 10;
	int k = 0;
	int special_int;
	int special_row;
	int special_colunm;
	int lines = 0;
	int combo_m = 0;
	public canvas_place(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		setBounds(x, y, width, height);
		this.setLayout(null);
	}
}

class His {
	int count;
	int Do;
	String count_s;
	String Do_s;

	His(int count, int Do) {
		this.count = count;
		this.Do = Do;
		count_s = Integer.toString(count);
		Do_s = Integer.toString(Do);
	}

	His(int Do) {
		this.Do = Do;
		Do_s = Integer.toString(Do);
	}
}

class Transparent_area extends canvas_place {
	public Transparent_area(int x, int y, int width, int height) {
		super(x, y, width, height);

		this.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.15f));
	}

	public Transparent_area(int x, int y, int width, int height, int i) {
		super(x, y, width, height);
		this.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.4f));
	}
}

class Demo_all extends canvas_place {
	List<Tetris> try_demo_all;

	public Demo_all(int x, int y, int width, int height, List<Tetris> try_demo_all) {
		super(x, y, width, height);
		this.try_demo_all = try_demo_all;
		this.setOpaque(false);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < try_demo_all.size() - 1; i++) {
			try_demo_all.get(i).con_te.stream().forEach(y -> y.draw(g));
		}

	}

	public void add(Home_btn i) {
		// TODO Auto-generated method stub

	}
}

class Record {
	int grade;// 0~5蝑� 0-1(3000���) 1-2(3500���) 2-3(4000���) 3-4(4500) 4-5(5000)
	int tetris_num = 8;
	int[][] color_num;
	public List<Tetris_id> id = new ArrayList<Tetris_id>();
	public String best_score = "0";
	public int best_score_int = 0;
	public String Name = "Other";
	public String pwd = " ";
	boolean login = false;
	int arc = 0;
	String[] Score;

	Record(String[] Score) {
		color_num = new int[tetris_num][3];
		this.Score = Score;
		for (int i = 0; i < tetris_num; i++) {
			id.add(new Tetris_id());
		}
		for (int i = 0; i < tetris_num; i++) {
			for (int j = 0; j < 3; j++) {
				color_num[i][j] = Integer.valueOf(Score[3 * (i + 1) + j]);

			}
			id.get(i).setFirstColor(color_num[i][0], color_num[i][1], color_num[i][2]);
		}
		grade = Integer.parseInt(Score[Score.length - 1]);
		arc = Integer.parseInt(Score[Score.length - 2]);
		id.stream().forEach(x -> x.arc = this.arc);
		Name = Score[0];
		best_score = Score[1];
		best_score_int = Integer.parseInt(best_score);
		pwd = Score[2];
	}

	Record() {
		for (int i = 0; i < tetris_num; i++) {
			id.add(new Tetris_id());
		}
		color_num = new int[][] { { 0, 255, 255 }, { 250, 128, 114 }, { 0, 191, 255 }, { 255, 215, 0 },
				{ 255, 130, 171 }, { 131, 111, 255 }, { 152, 251, 152 }, { 0, 0, 0 } };
		for (int i = 0; i < tetris_num; i++) {
			id.get(i).setFirstColor(color_num[i][0], color_num[i][1], color_num[i][2]);
		}
	}

}

//瘥�憛�D
class Tetris_id {
	int arc;
	public Color color;

	public void RGB(Color color) {
		this.color = color;
	}

	public void setFirstColor(int R, int G, int B) {
		color = new Color(R, G, B);
	}

	public void setarc(int arc) {
		this.arc = arc;
	}

}

//Button
class Home_btn extends JButton {
	Home_btn(int x, int y, int width, int height, String Commend, String Name) {
		this.setSize(width, height);
		this.setLocation(x, y);
		this.setActionCommand(Commend);
		this.setText(Name);
		this.setVisible(true);
	}
}
