package game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



import com.sun.tools.javac.Main;

//��蕭嚙踝���嚙踝�蕭�嚙踝蕭

public class Home extends game {

	// ��蕭嚙踝蕭謘選蕭謘橘蕭謅��蕭
	Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	
	// Button
	public Home_btn start = new Home_btn((int) (screenSize.getWidth() / 2) + 400, 25, 200, 200, "start", "");
	public Home_btn change_color = new Home_btn(50, 130, 180, 180, "change_color", "");
	public Home_btn select_player = new Home_btn(30, 500, 350, 150, "sel_player", "");
	public Home_btn Two = new Home_btn((int) (screenSize.getWidth() / 2) + 80, 490, 150, 150, "Two", "");
	public Home_btn machine = new Home_btn(270, 325, 190, 190, "Machine", "");

	// Frame
	public Frame home_frame;
	public Home_Color home_color;
	public Tetris_main_game t;
	
	// background picture
	public All_Layer all_layer = new All_Layer();
	public All_Layer all_layer_color = new All_Layer();
	All_Layer all_layer_start = new All_Layer();
	All_Layer all_layer_warn = new All_Layer();

	// 嚙踐���蕭�嚙踝蕭

	List<Record> id_record;
	JLabel player_name;
	JLabel player_score;
	JLabel[][] RateLabel;
	
	Record player;

	// �摮蛔��
	private JPanel contentPane = new JPanel();
	private JPanel RatecontentPane = new JPanel();
	private JScrollPane RatecontentPane_b = new JScrollPane(RatecontentPane);

	// 嚙踝���蕭
	String location=System.getProperty("user.dir")+"\\src\\game\\picture\\";
	String lo_music=System.getProperty("user.dir")+"\\src\\game\\other\\";
	Home_Player home_player;
	Teach teach;
	ImageIcon single = new ImageIcon(location+"rr.png");
	ImageIcon sign_out = new ImageIcon(location+"bb.png");
	ImageIcon ddouble = new ImageIcon(location+"love.png");
	ImageIcon computer = new ImageIcon(location+"vs.png");
	ImageIcon style = new ImageIcon(location+"style.png");
	ImageIcon tetris = new ImageIcon(location+"tetris.png");
	JLabel Tetris=new JLabel(tetris);
	AePlayWave apw=new AePlayWave(0,true);
	AePlayWave apw2=new AePlayWave("C:\\Users\\User\\Desktop\\java\\final\\mouse.wav");
	

	Runnable runn = () -> {                  
		float i = 0f;
		while (i <= 1f) {
			try {
				Thread.sleep(8);
			} catch (InterruptedException e1) {
			}
			teach.setOpacity(i);
			i = i + 0.05f;
		}
	};
	Runnable ruun = () -> {
		float i = 0f;
		while (i <= 1f) {
			try {
				Thread.sleep(8);
			} catch (InterruptedException e1) {
			}
			t.frame.setOpacity(i);
			i = i + 0.01f;
		}
	};

	// Constructor
	Home(List<Record> id_record, Record player, Home_Player home_player) {
//		System.out.println(System.getProperty("user.dir"));
//		System.out.println(Home.class.getResource(""));
//		System.out.println(lo_music+"mouse.wav");
		this.home_player = home_player;
		this.id_record = id_record;
		this.player = player;
		apw.start();
		Font f = new Font("Tekton Pro", Font.BOLD, 35);
		player_name = new JLabel(player.Name);
		player_name.setFont(f);
		player_name.setForeground(Color.white);
		player_name.setBounds(80, 50, player.Name.length()*20, 50);
		
		player_score = new JLabel(player.best_score);
		player_score.setFont(f);
		player_score.setForeground(Color.white);
		player_score.setBounds(80, 100, player.Score.length*5, 50);

		all_layer_color.bg_img.setSize(620, 620);
		all_layer.bg_img.setSize(screenSize);
		all_layer_start.bg_img.setSize(screenSize);

		// Frame
		home_frame = new Frame(0, 0, screenSize.width, screenSize.height, "Home", 3, all_layer.set_bg("ffff.jpg"));
		home_frame.setVisible(true);
		// Listener

		// ���蕭謍湛蕭�嚙踐�蕭��蕭嚙�
		start.setOpaque(false);
		// 嚙踐嚙踝蕭�嚙踝謒蕭蹌穿蕭�嚙踝蕭��蕭嚙�
		start.setContentAreaFilled(false);
		// 嚙踐嚙踝蕭�嚙踐垓��嚙�
		start.setFocusPainted(false);
		// 嚙踐嚙踝蕭���嚙�
		start.setBorder(null);
		// ���嚙踐�蝞蕭��郎嚙踝蕭嚙�
		start.setIcon(single);
		start.addActionListener(this);
		start.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				apw2=new AePlayWave(lo_music+"mouse.wav",15);
				apw2.start();
				single = new ImageIcon(location+"rr1.png");
				start.setIcon(single);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {

				single = new ImageIcon(location+"rr.png");
				start.setIcon(single);
			}
		});

		select_player.setOpaque(false);
		select_player.setContentAreaFilled(false);
		select_player.setFocusPainted(false);
		select_player.setBorder(null);
		select_player.setIcon(sign_out);
		select_player.addActionListener(this);
		select_player.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				apw2=new AePlayWave(lo_music+"mouse.wav",15);
				apw2.start();
				sign_out = new ImageIcon(location+"bb1.png");
				select_player.setIcon(sign_out);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {

				sign_out = new ImageIcon(location+"bb.png");
				select_player.setIcon(sign_out);
			}
		});

		Two.setOpaque(false);
		Two.setContentAreaFilled(false);
		Two.setFocusPainted(false);
		Two.setBorder(null);
		Two.setIcon(ddouble);
		Two.addActionListener(this);
		Two.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				apw2=new AePlayWave(lo_music+"mouse.wav",15);
				apw2.start();
				ddouble = new ImageIcon(location+"love1.png");
				Two.setIcon(ddouble);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {

				ddouble = new ImageIcon(location+"love.png");
				Two.setIcon(ddouble);
			}
		});

		machine.setOpaque(false);
		machine.setContentAreaFilled(false);
		machine.setFocusPainted(false);
		machine.setBorder(null);
		machine.setIcon(computer);
		machine.addActionListener(this);
		machine.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				apw2=new AePlayWave(lo_music+"mouse.wav",15);
				apw2.start();
				computer = new ImageIcon(location+"vs1.png");
				machine.setIcon(computer);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {

				computer = new ImageIcon(location+"vs.png");
				machine.setIcon(computer);
			}
		});

		change_color.setOpaque(false);
		change_color.setContentAreaFilled(false);
		change_color.setFocusPainted(false);
		change_color.setBorder(null);
		change_color.setIcon(style);
		change_color.addActionListener(this);
		change_color.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				apw2=new AePlayWave(lo_music+"mouse.wav",15);
				apw2.start();
				style = new ImageIcon(location+"style1.png");
				change_color.setIcon(style);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {

				style = new ImageIcon(location+"style.png");
				change_color.setIcon(style);
			}
		});

		home_frame.addKeyListener(this);

		// �摮蛔���蕭嚙�
		contentPane.setLayout(null);
		contentPane.setSize(screenSize);
		contentPane.setLocation(0, 0);
		contentPane.setOpaque(false);
		RatecontentPane_b.setBounds(screenSize.width - 350, 330, 300,200);
		RatecontentPane_b.setOpaque(false);
		RatecontentPane_b.getViewport().setOpaque(false);
		RatecontentPane_b.setBorder(null);
		RatecontentPane.setLayout(new GridLayout(6, 3));
		RatecontentPane.setSize(300, id_record.size() * 15);

		RatecontentPane.setOpaque(false);
		RateLabel = new JLabel[id_record.size() + 1][3];

		WriteRate();
		all_layer_warn.bg_img.setSize(650, 500);
		teach = new Teach((int) ((screenSize.getWidth() / 2) - 325), 100);
		Tetris.setBounds(320, 150, 700, 150);
		// 嚙踝蕭蹎button
		contentPane.add(Two);
		contentPane.add(select_player);
		contentPane.add(player_score);
		contentPane.add(change_color);
		contentPane.add(start);
		contentPane.add(RatecontentPane_b);
		contentPane.add(machine);

		contentPane.add(player_name);
		contentPane.add(Tetris);
		home_frame.add_jp(contentPane, JLayeredPane.PALETTE_LAYER);
		home_color = new Home_Color(screenSize.width / 2 - 310, 40, 620, 600, "Player", 2,
				all_layer_color.set_bg("color41.jpg"));

		home_color.setResizable(false);
		home_color.setUndecorated(true);

	}

	Font f3 = new Font("Tekton Pro", Font.BOLD, 17);

	public void WriteRate() {
		player_score.setText(player.best_score); 
		RatecontentPane.removeAll();
		id_record = id_record.stream().sorted((s1, s2) -> (s1.best_score_int > s2.best_score_int) ? -1 : 1)
				.collect(Collectors.toList());
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				RateLabel[i][j] = new JLabel((i == 0) ? ((j == 0) ? "Ranking" : (j == 1) ? "Name" : "BestScore")
						: ((j == 0) ? Integer.toString(i)
								: (j == 1) ? id_record.get(i - 1).Name
										: Integer.toString(id_record.get(i - 1).best_score_int)));
				RateLabel[i][j].setFont(f3);
				RateLabel[i][j].setOpaque(false);
				RateLabel[i][j].setForeground(new Color(204, 255, 255));
				RatecontentPane.add(RateLabel[i][j]);
			}
		}
	}


	Thread tteach;
	int player_num = 100;
	@Override
	public void actionPerformed(ActionEvent e) {
		String commend = e.getActionCommand();
		if (commend.contentEquals("start")) {
			apw.Stop();
			home_frame.setVisible(false);

			player_num = 1;
			t = new Tetris_main_game(this, id_record, player, 1);
			teach.one();
			tteach = new Thread(runn);
			tteach.start();
			
		} else if (commend.contentEquals("Two")) {
			apw.Stop();
			player_num = 2;
			home_frame.setVisible(false);
			t = new Tetris_main_game(this, id_record, player, 2);
			teach.two();
			tteach = new Thread(runn);
			tteach.start();
		} else if (commend.equals("change_color")) {
			apw.Stop();
			home_frame.setVisible(false);
			home_color.apw1=new AePlayWave(1,true);
			home_color.apw1.start();
			home_color.setVisible(true);
		} else if (commend.equals("sel_player")) {
			apw.Stop();
			home_frame.setVisible(false);
			player.login = false;
			home_player.home_player.setVisible(true);

		} else if (commend.equals("Machine")) {
			apw.Stop();
			player_num = 0;
			home_frame.setVisible(false);
			t = new Tetris_main_game(this, id_record, player, 0);
			teach.zero();
			tteach = new Thread(runn);
			tteach.start();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	class Home_Color extends Frame implements ActionListener,KeyListener{
 
		public JPanel main =new JPanel();
		List<Tetris> try_demo = new ArrayList<Tetris>();
		List<Tetris> try_demo_all = new ArrayList<Tetris>();
		public Home_btn I=new Home_btn(40,160,45,25,"I","I");
		public Home_btn T=new Home_btn(150,120,45,25,"T","T");
		public Home_btn J=new Home_btn(290,120,45,25,"J","J");
		public Home_btn L=new Home_btn(400,120,45,25,"L","L");
		public Home_btn O=new Home_btn(40,280,45,25,"O","O");
		public Home_btn Z=new Home_btn(150,280,45,25,"Z","Z");
		public Home_btn S=new Home_btn(290,280,45,25,"S","S");
		public int row;
		public Home_btn Store=new Home_btn(445,490,80,40,"Store","Back");
		public Home_btn First=new Home_btn(445,440,80,40,"First","Preset");
		JSlider R;
		JSlider G;
		JSlider B; 
		JSlider ArcInp;
		JLabel R_num; 
		JLabel G_num;
		JLabel B_num; 
		JLabel Arc; 
		public int R_value;
		public int G_value;
		public int B_value;
		public Color color;
		int index=0;
		private JLabel ArcLabel=new JLabel("Set the arc");
		private Transparent_area  contentPane_demo  = new Transparent_area (50,250,500,300,0); 
		private Transparent_area  contentPane_change  = new Transparent_area (50,50,500,170,0); 
		JPanel contentPane=new JPanel();
		JPanel demo_all_con=new JPanel();
		Font f = new Font("Tekton Pro",Font.BOLD,15); 
		AePlayWave apw1=new AePlayWave("C:\\Users\\User\\Desktop\\java\\final\\color3.wav",true);
		int mu_int=0;
		boolean musss=true;
		public Demo_all demo_all= new Demo_all(50,230,500,330,try_demo_all);

		Home_Color(int x, int y, int width, int height, String Name, int de,JPanel pp) {
			super(x, y, width,  height, Name, de, pp);
			//嚙踐嚙踐��蕭謒蕭嚙�
			contentPane.setLayout(null);
			contentPane.setLocation(0, 0);;
			contentPane.setSize(width,height);
			contentPane.setOpaque(false);
			demo_all_con.setLayout(null);
			demo_all_con.setOpaque(false);
			demo_all_con.setBounds(50, 230, 500, 330);
			
			//
			try_demo_all.add(new I(40,120,player.id.get(0)));
			try_demo_all.add(new T(130,60,player.id.get(1)));
			try_demo_all.add(new J(260,60,player.id.get(2)));
			try_demo_all.add(new L(370,60,player.id.get(3)));
			try_demo_all.add(new O(46,240,player.id.get(4)));
			try_demo_all.add(new Z(152,240,player.id.get(5)));
			try_demo_all.add(new S(288,240,player.id.get(6)));
			try_demo_all.add(new JC(400,240,player.id.get(7)));
			
			//ARC嚙踐���嚙�

			ArcLabel.setBounds(410, 70, 150, 20);
			ArcLabel.setFont(f);
			ArcLabel.setForeground(Color.WHITE);

			
			//Slider嚙踝蕭
			R_num = new JLabel("R:"+player.id.get(0).color.getRed());
			G_num = new JLabel("G:"+player.id.get(0).color.getGreen());
			B_num = new JLabel("B:"+player.id.get(0).color.getBlue());
			Arc = new JLabel("Arc:"+player.id.get(0).arc);
			Arc.setBounds(490,80,100,20);
			Arc.setFont(f3);
			Arc.setForeground(Color.white);
			R = new JSlider(0, 255, 1);
			G = new JSlider(0, 255, 1);
			B = new JSlider(0, 255, 1);
			ArcInp=new JSlider(0,30,1);
			R.setOpaque(false);
			R.setValue(player.id.get(0).color.getRed());
			R.setBounds(50, 50, 300, 50);
			R_num.setBounds(350, 50, 70, 50);
			R_num.setFont(f);
			R_num.setForeground(Color.WHITE);
			R.setSnapToTicks(true);// ���蕭謚秋冪true��嚙踐嚙踝�蕭��蕭����嚙踝蕭���嚙踝蕭��蕭瞏蕭��嚙踝蕭��冪嚙踝蕭嚙踝蕭蹎∴蕭���蕭��蕭���嚙踐嚙踝�蕭�嚙踝��銵蕭謕蕭謢賂蕭��蕭瞏佗蕭蹇蕭謢對蕭��蕭�嚙踐撩嚙踝�冪false嚙踝蕭
			R.addChangeListener(new ChangeListener() {
			      public void stateChanged(ChangeEvent event) {	
			        R_value = R.getValue();
			        R_num.setText("R:"+R_value);
			        G_value=player.id.get(index).color.getGreen();
			        B_value=player.id.get(index).color.getBlue();
			        resetColor();
			        }
			    });
			G.setOpaque(false);
			G.setValue(player.id.get(0).color.getGreen());
			G.setBounds(50, 110, 300, 50);
			G_num.setBounds(350, 110, 70, 50);
			G_num.setFont(f);
			G_num.setForeground(Color.WHITE);
			G.setSnapToTicks(true);
			G.addChangeListener(new ChangeListener() {
			      public void stateChanged(ChangeEvent event) {
			        G_value = G.getValue();
			        G_num.setText("G:"+G_value);
			        R_value=player.id.get(index).color.getRed();
			        B_value=player.id.get(index).color.getBlue();
			        resetColor();
			        
			        }
			    });
			
			B.setOpaque(false);
			B.setValue(player.id.get(0).color.getBlue());
			B.setBounds(50,170, 300, 50);
			B_num.setBounds(350, 170, 70, 50);
			B_num.setFont(f);
			B_num.setForeground(Color.WHITE);
			B.setSnapToTicks(true);
			B.addChangeListener(new ChangeListener() {
			      public void stateChanged(ChangeEvent event) {
			        B_value = B.getValue();
			        B_num.setText("B:"+B_value);
			        G_value=player.id.get(index).color.getGreen();
			        R_value=player.id.get(index).color.getRed();
			        resetColor();
			        }
			    });
		
			ArcInp.setOpaque(false);
			ArcInp.setValue(player.id.get(0).arc);
			ArcInp.setBounds(410, 80, 70, 20);
			ArcInp.setSnapToTicks(true);
			ArcInp.addChangeListener(new ChangeListener() {
			      public void stateChanged(ChangeEvent event) {
			    	  Arc.setText("Arc :"+ArcInp.getValue());
			    	  charc();
			    
			        }
			 });
			
			
			//demo 嚙踝蕭�tn
			I.addActionListener(this);
			demo_all_con.add(I);
			T.addActionListener(this);
			demo_all_con.add(T);
			J.addActionListener(this);
			demo_all_con.add(J);
			L.addActionListener(this);
			demo_all_con.add(L);
			O.addActionListener(this);
			demo_all_con.add(O);
			Z.addActionListener(this);
			demo_all_con.add(Z);
			S.addActionListener(this);
			demo_all_con.add(S);
			
			//嚙踝����n
			contentPane.add(Arc);
			Store.addActionListener(this);
			contentPane.add(Store);
			First.addActionListener(this);
			contentPane.add(First);	

			//嚙踐�蕭��摮蛔�蕭嚙�
			this.add_jp(contentPane, JLayeredPane.MODAL_LAYER);
			this.add_jp(demo_all_con, JLayeredPane.POPUP_LAYER);
			contentPane.add(demo_all);
			contentPane.add(R_num);
			contentPane.add(R);
			contentPane.add(B_num);
			contentPane.add(B);
			contentPane.add(G_num);
			contentPane.add(G);
			contentPane.add(ArcInp);
			contentPane.add(ArcLabel);
			contentPane.add(contentPane_demo);
			contentPane.add(contentPane_change);
			
		}
	
		
		public void resetColor() {
			color=new Color(R_value,G_value,B_value);
			player.id.get(index).RGB(color);
			try_demo_all.get(index).color=color;
			try_demo_all.get(index).ResetColor();
			demo_all.repaint();			
		}
		public void FirstColor() {
			if(index==0) player.id.get(0).setFirstColor(0 ,255, 255);
			else if(index==1) player.id.get(1).setFirstColor(250 ,128, 114);
			else if(index==2) player.id.get(2).setFirstColor(0 ,191, 255);			
			else if(index==3) player.id.get(3).setFirstColor(255 ,215 ,0);
			else if(index==4) player.id.get(4).setFirstColor(255, 130 ,171);
			else if(index==5) player.id.get(5).setFirstColor(131 ,111 ,255);	
			else if(index==6) player.id.get(6).setFirstColor(152, 251, 152);
			else if(index==7) player.id.get(7).setFirstColor(0,0,0);	
			try_demo_all.get(index).color=player.id.get(index).color;
			try_demo_all.get(index).ResetColor();
			demo_all.repaint();

			}

		@Override
		public void actionPerformed(ActionEvent e) {
			String get=e.getActionCommand();
			if(get.contentEquals("I")) index=0;
			else if(get.contentEquals("T")) index=1;
			else if(get.contentEquals("J")) index=2;			
			else if(get.contentEquals("L")) index=3;				
			else if(get.contentEquals("O")) index=4;
			else if(get.contentEquals("Z")) index=5;				
			else if(get.contentEquals("S")) index=6;
			else if(get.contentEquals("JC")) index=7;
			else if(get.contentEquals("Store")) {
				try {			
					player.id.stream().forEach(x->x.setarc(ArcInp.getValue()));
					try_demo_all.stream().forEach(x->x.con_te.stream().forEach(y->y.arc=ArcInp.getValue()));
					File writename = new File(System.getProperty("user.dir")+"\\src\\game\\ID.txt"); // 嚙踐�����嚙踝嚙踐�蕭謚恬蕭��蕭�嚙踝嚙踐撒����蕭嚙踝��蕭嚙踝utput嚙踐�t���蕭嚙�
					BufferedWriter out = new BufferedWriter(new FileWriter(writename));
					for(Record i:id_record) {
						out.write(i.Name+" "+i.best_score+" "+i.pwd);
						for(int j=0;j<24;j++) {
							out.write(" "+((j%3==0)?i.id.get(j/3).color.getRed():((j%3==1)?i.id.get(j/3).color.getGreen():i.id.get(j/3).color.getBlue())));
						}
						out.write(" "+Integer.toString(i.id.get(0).arc)+" "+Integer.toString(i.grade));
						out.write("\r\n");
					} 
					out.flush(); // 嚙踝蕭��蝧堆蕭嚙踐□嚙踝蕭��摮蛛蕭�����蕭嚙�
					out.close(); // 嚙踝蕭��嚙踐朱嚙踐�蕭謚殷蕭�嚙踐�蕭嚙�
					} catch (Exception e1) {
					e1.printStackTrace();
					}
				this.setVisible(false);
				apw1.Stop();
				apw=new  AePlayWave(0,true);
				apw.start();
				home_frame.setVisible(true);
				
			}	
			else if(get.contentEquals("First")) {
				FirstColor();
			}

			
			
			R.setValue(player.id.get(index).color.getRed());
			G.setValue(player.id.get(index).color.getGreen());
			B.setValue(player.id.get(index).color.getBlue());

		}
		@Override
		public void keyTyped(KeyEvent e) {			
		}
		@Override
		public void keyPressed(KeyEvent e) {
		}
		public void charc() {
			player.id.stream().forEach(x->x.setarc(ArcInp.getValue()));
			try_demo_all.stream().forEach(x->x.con_te.stream().forEach(y->y.arc=ArcInp.getValue()));
			demo_all.repaint();
		}


		@Override
		public void keyReleased(KeyEvent e) {
			
		}


	}
	class Teach extends Frame implements MouseListener, KeyListener {
		public JPanel contentPane = new JPanel();

		All_Layer One_key;
		All_Layer Two_key;
		boolean two = false;

		Teach(int x, int y) {
			super(x, y, 650, 500, "Gameover", 2, all_layer_warn.set_bg("6636.jpg"));
			contentPane.setBounds(0, 0, 650, 500);
			contentPane.setOpaque(false);
			contentPane.setLayout(null);
			contentPane.add(new JPanel());
			this.setUndecorated(true);
			this.addMouseListener(this);
			this.addKeyListener(this);
			this.add_jp(contentPane, JLayeredPane.MODAL_LAYER);
		}

		public void one() {
			contentPane.removeAll();
			One_key = new All_Layer();
			One_key.bg_img.setBounds(30, 0, 650, 500);
			contentPane.add(One_key.set_bg("6636.png"));
			teach.setOpacity(0f);
			this.setVisible(true);

		}

		public void two() {
			contentPane.removeAll();
			One_key = new All_Layer();
			One_key.bg_img.setBounds(30, 0, 650, 500);
			contentPane.add(One_key.set_bg("tt.png"));
			teach.setOpacity(0f);
			this.setVisible(true);
		}

		public void two2() {
			contentPane.removeAll();
			two = true;
			One_key = new All_Layer();
			One_key.bg_img.setBounds(0, 0, 650, 500);
			contentPane.add(One_key.set_bg("tt1.png"));
			this.setVisible(true);
		}

		public void zero() {
			contentPane.removeAll();
			One_key = new All_Layer();
			One_key.bg_img.setBounds(0, 0, 650, 500);
			contentPane.add(One_key.set_bg("kk.png"));
			teach.setOpacity(0f);
			this.setVisible(true);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		public void intogame() {
			if (player_num == 2 && two == false) {
				two2();
			} else if (player_num == 2 && two == true) {
				two = false;
				this.setVisible(false);
				t.frame.setVisible(true);
				t.running();
				t.basic1.start();
				t.basic2.start();
				t.apw.start();
			}

			if (player_num == 1) {
				this.setVisible(false);
				t.frame.setVisible(true);
				t.running();
				t.basic1.start();
				t.apw.start();
			} else if (player_num == 0) {
				this.setVisible(false);
				t.frame.setVisible(true);
				t.running();
				t.basic1.start();
				t.basic2.start();
				t.apw.start();
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			intogame();
		}
		@Override
		public void mouseReleased(MouseEvent e) {

		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {
			intogame();
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}

}
