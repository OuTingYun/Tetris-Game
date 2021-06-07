package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class Basic {
	// 方塊生產
	public LinkedList<Integer> int_ls = new LinkedList<Integer>();
	public LinkedList<Tetris> ls = new LinkedList<Tetris>();
	public LinkedList<Tetris> sec_ls = new LinkedList<Tetris>();
	public LinkedList<Tetris> nextls = new LinkedList<Tetris>();
	public LinkedList<Tetris> hold_ls = new LinkedList<Tetris>();
	// 背景陣列
	public conponent_xy[][] bg_xy = new conponent_xy[600][300];
	// 半透明區
	public Transparent_area t_game = new Transparent_area(150, 70, 300, 600);
	public Transparent_area t_next = new Transparent_area(460, 70, 100, 180);
	public Transparent_area t_hold = new Transparent_area(40, 70, 100, 180);
	// 各種物件
	public JLabel Score = new JLabel();
	public JLabel Best_Score = new JLabel();
	public JLabel Player = new JLabel();
	public JLabel Combo = new JLabel();
	public JLabel Lines = new JLabel();
	public JLabel Last_Time = new JLabel();
	// 基本資料
	LinkedList<His> History_tetris = new LinkedList<His>();
	List<His> history_tetris;
	Random ran = new Random();
	Record player;
	// 變數
	int player_num = 100;
	int tetris = 0;
	int hold_num = 0;
	int ch_num = 0;
	int same_count = 100;
	int all_count = 1;
	// 遊戲畫面
	public Gameplace gameplace;
	public canvas_place gameplace_bg=new canvas_place(150,70,300,600){public void paintComponent(Graphics g){super.paintComponent(g);delete();for(int i=0;i<600;i+=30){for(int j=0;j<300;j+=30){if(bg_xy[i][j].getUse()){bg_xy[i][j].draw(g);}}}}

	// 判斷刪行
	public void delete(){int combo=0;boolean delete;if(special_int==7){for(int j=0;j<300;j+=30){bg_xy[special_row][j].setUse(false);}Now_Score+=Plus_Score;for(int j=0;j<600;j+=30){bg_xy[j][special_colunm].setUse(false);}for(int j=special_row;j>=0;j-=30){if(j==0){bg_xy[j][k].setUse(false);continue;}for(int k=0;k<300;k+=30){bg_xy[j][k].setUse(bg_xy[j-30][k].getUse());}}special_int=0;special_row=0;return;}for(int i=570;i>0;i-=30){delete=true;for(int j=0;j<300;j+=30){if(!bg_xy[i][j].getUse()){delete=false;}}if(delete){lines++;combo++;for(int j=0;j<300;j+=30){bg_xy[i][j].setUse(false);

	}Now_Score+=Plus_Score;for(int j=i;j>=0;j-=30){if(j==0){bg_xy[j][k].setUse(false);continue;}for(int k=0;k<300;k+=30){bg_xy[j][k].setUse(bg_xy[j-30][k].getUse());bg_xy[j][k].setColor(bg_xy[j-30][k].getColor());}}Lines.setText("<html>"+"Lines: "+"<br>"+lines+"</html>");Combo.setText("<html>"+"Best Combo: "+"<br>"+((combo>combo_m)?combo:combo_m)+"</html>");combo_m=(combo>combo_m)?combo:combo_m;}}Score.setText("<html>"+"Score: "+"<br>"+Now_Score+"</html>");if(Integer.parseInt(player.best_score)<Now_Score){Best_Score.setText("<html>"+"Best Score: "+"<br>"+(""+Now_Score)+"</html>");player.best_score_int=Now_Score;player.best_score=Integer.toString(Now_Score);}}};
	public canvas_place hold=new canvas_place(40,70,100,180){public void paintComponent(Graphics g){super.paintComponent(g);hold_ls.getFirst().con_te.stream().forEach(x->x.draw(g));}};
	public canvas_place next=new canvas_place(460,70,100,180){public void paintComponent(Graphics g){super.paintComponent(g);nextls.getFirst().con_te.stream().forEach(x->x.draw(g));}};

	Basic(Record player, List<His> history_tetris, int player_num) {// 0
		this.player = player;
		this.player_num = player_num;
		if (player_num == 0) {
			history_tetris.stream().forEach(x -> this.History_tetris.add(new His(x.count, x.Do)));
		} else {
			this.history_tetris = history_tetris;
		}
		base();
	}

	Basic(Record player) {// 2
		this.player = player;
		base();
	}

	public void base() {
		// 目前玩家資料+所有玩家資料
		Best_Score.setText("<html>" + "Best Score:" + "<br>" + player.best_score + "</html>");
		Player.setText("<html>" + "Player： " + "<br>" + player.Name + "</html>");
		Score.setText("Score: 0");
		Combo.setText("<html>" + "Best Combo: " + "<br>" + "0" + "</html>");
		Lines.setText("<html>" + "Lines: " + "<br>" + "0" + "</html>");
		// Label的資料設定
		Score.setBounds(60, 300, 100, 50);
		Best_Score.setBounds(460, 400, 100, 50);
		Player.setBounds(460, 500, 100, 50);
		Combo.setBounds(60, 400, 100, 50);
		Lines.setBounds(60, 500, 100, 50);
		Font f3 = new Font("Tekton Pro", Font.BOLD, 80);
		Last_Time.setBounds(125, 225, 150, 100);
		Last_Time.setFont(f3);
		Last_Time.setForeground(Color.red);
		Score.setOpaque(false);
		Combo.setOpaque(false);
		Lines.setOpaque(false);
		Best_Score.setOpaque(false);
		Player.setOpaque(false);
		// list預設
		hold_ls.add(new I(35, 120, player.id.get(0)));
		tetris = (int) (Math.random() * (6 - 0 + 1));
		if (player_num != 0) {
			int_ls.add(tetris);
			if (player_num == 1)
				history_tetris.add(new His(tetris));
		} else {
			int k = History_tetris.getFirst().Do;
			int_ls.add(k);
			History_tetris.removeFirst();
		}
		for (int i = 0; i < 600; i += 30) {
			for (int j = 0; j < 300; j += 30) {
				bg_xy[i][j] = new conponent_xy(j, i);
				bg_xy[i][j].arc = player.id.get(0).arc;// 重新放入arc的改變執值
			}
		}
		// 版面的設定
		gameplace_bg.setOpaque(false);
		next.setOpaque(false);
		hold.setOpaque(false);
	}

	// 創建方塊
	public void ch_hold() {
		ch_num = hold_num;
		hold_num = int_ls.getFirst();
		if (int_ls.getFirst() == 0)
			hold_ls.add(new I(35, 120, player.id.get(0)));
		else if (int_ls.getFirst() == 1)
			hold_ls.add(new T(5, 100, player.id.get(1)));
		else if (int_ls.getFirst() == 2)
			hold_ls.add(new J(2, 100, player.id.get(2)));
		else if (int_ls.getFirst() == 3)
			hold_ls.add(new L(2, 100, player.id.get(3)));
		else if (int_ls.getFirst() == 4)
			hold_ls.add(new O(15, 100, player.id.get(4)));
		else if (int_ls.getFirst() == 5)
			hold_ls.add(new Z(5, 90, player.id.get(5)));
		else if (int_ls.getFirst() == 6)
			hold_ls.add(new S(5, 90, player.id.get(6)));
		else if (int_ls.getFirst() == 7)
			hold_ls.add(new JC(35, 50, player.id.get(7)));
		int_ls.removeFirst();
		int_ls.addFirst(ch_num);
		sec_ls.removeFirst();
		ls.removeFirst();
		hold_ls.removeFirst();
		Create();
	}

	public void add_intls() {
		if (player_num != 0) {
			while (true) {
				if (all_count % 5 == 0)
					tetris = 7;
				else
					tetris = ran.nextInt(7);
				if (same_count != tetris) {
					int_ls.add(tetris);
					if (player_num == 1)
						history_tetris.add(new His(tetris));
					same_count = tetris;
					all_count++;
					break;
				}
			}
		} else {
			int k = History_tetris.getFirst().Do;
			int_ls.add(k);
			History_tetris.removeFirst();
		}
	}

	public void add_nextls() {
		if (int_ls.get(1) == 0)
			nextls.addFirst(new I(35, 120, player.id.get(0)));
		else if (int_ls.get(1) == 1)
			nextls.addFirst(new T(5, 100, player.id.get(1)));
		else if (int_ls.get(1) == 2)
			nextls.addFirst(new J(2, 100, player.id.get(2)));
		else if (int_ls.get(1) == 3)
			nextls.addFirst(new L(2, 100, player.id.get(3)));
		else if (int_ls.get(1) == 4)
			nextls.addFirst(new O(15, 100, player.id.get(4)));
		else if (int_ls.get(1) == 5)
			nextls.addFirst(new Z(5, 90, player.id.get(5)));
		else if (int_ls.get(1) == 6)
			nextls.addFirst(new S(5, 90, player.id.get(6)));
		else if (int_ls.get(1) == 7)
			nextls.addFirst(new JC(35, 50, player.id.get(7)));
	}

	public void Create() {
		if (int_ls.getFirst() == 0) {
			ls.addFirst(new I(120, 0, player.id.get(0)));
			sec_ls.addFirst(new I(120, 0, player.id.get(0)));
		} else if (int_ls.getFirst() == 1) {
			ls.addFirst(new T(90, 0, player.id.get(1)));
			sec_ls.addFirst(new T(90, 0, player.id.get(1)));
		} else if (int_ls.getFirst() == 2) {
			ls.addFirst(new J(90, 0, player.id.get(2)));
			sec_ls.addFirst(new J(90, 0, player.id.get(2)));
		} else if (int_ls.getFirst() == 3) {
			ls.addFirst(new L(90, 0, player.id.get(3)));
			sec_ls.addFirst(new L(90, 0, player.id.get(3)));
		} else if (int_ls.getFirst() == 4) {
			ls.addFirst(new O(120, 0, player.id.get(4)));
			sec_ls.addFirst(new O(120, 0, player.id.get(4)));
		} else if (int_ls.getFirst() == 5) {
			ls.addFirst(new Z(90, 0, player.id.get(5)));
			sec_ls.addFirst(new Z(0, 0, player.id.get(5)));
		} else if (int_ls.getFirst() == 6) {
			ls.addFirst(new S(90, 0, player.id.get(6)));
			sec_ls.addFirst(new S(90, 0, player.id.get(6)));
		} else if (int_ls.getFirst() == 7) {
			ls.addFirst(new JC(120, 0, player.id.get(7)));
			sec_ls.addFirst(new JC(120, 0, player.id.get(7)));
		}
	}

	public void start() {
		Thread gp = new Thread(gameplace);
		gp.start();
	}

	class Gameplace_bg extends canvas_place {
		int Now_Score = 0;
		int Plus_Score = 100;
		int k = 0;
		int special_int;
		int special_row;
		int special_colunm;
		int lines = 0;
		int combo_m = 0;

		Gameplace_bg() {
			super(150, 70, 300, 600);
			this.setOpaque(false);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			delete();
			for (int i = 0; i < 600; i += 30) {
				for (int j = 0; j < 300; j += 30) {
					if (bg_xy[i][j].getUse()) {
						bg_xy[i][j].draw(g);
					}
				}
			}
		}

		// 判斷刪行
		public void delete() {

			int combo = 0;
			boolean delete;
			if (special_int == 7) {
				for (int j = 0; j < 300; j += 30) {
					bg_xy[special_row][j].setUse(false);
				}
				Now_Score += Plus_Score;
				for (int j = 0; j < 600; j += 30) {
					bg_xy[j][special_colunm].setUse(false);
				}
				for (int j = special_row; j >= 0; j -= 30) {
					if (j == 0) {
						bg_xy[j][k].setUse(false);
						continue;
					}
					for (int k = 0; k < 300; k += 30) {
						bg_xy[j][k].setUse(bg_xy[j - 30][k].getUse());
						bg_xy[j][k].setColor(bg_xy[j - 30][k].getColor());
					}
				}
				
				special_int = 0;
				special_row = 0;
				return;
			}
			for (int i = 570; i > 0; i -= 30) {
				delete = true;
				for (int j = 0; j < 300; j += 30) {
					if (!bg_xy[i][j].getUse()) {
						delete = false;
					}
				}
				if (delete) {
					lines++;
					combo++;
					for (int j = 0; j < 300; j += 30) {
						bg_xy[i][j].setUse(false);
					}
					Now_Score += Plus_Score;
					for (int j = i; j >= 0; j -= 30) {
						if (j == 0) {
							bg_xy[j][k].setUse(false);
							continue;
						}
						for (int k = 0; k < 300; k += 30) {
							bg_xy[j][k].setUse(bg_xy[j - 30][k].getUse());
							bg_xy[j][k].setColor(bg_xy[j - 30][k].getColor());
						}
					}
					Lines.setText("<html>" + "Lines: " + "<br>" + lines + "</html>");
					Combo.setText(
							"<html>" + "Best Combo: " + "<br>" + ((combo > combo_m) ? combo : combo_m) + "</html>");
					combo_m = (combo > combo_m) ? combo : combo_m;
				}
			}
			Score.setText("<html>" + "Score: " + "<br>" + Now_Score + "</html>");
			if (Integer.parseInt(player.best_score) < Now_Score) {

				Best_Score.setText("<html>" + "Best Score: " + "<br>" + ("" + Now_Score) + "</html>");
				player.best_score_int = Now_Score;
				player.best_score = Integer.toString(Now_Score);
			}
		}
	}
}

public class Tetris_main_game extends game {
	AePlayWave apw = new AePlayWave(2, true);
	AePlayWave apw2 = new AePlayWave(System.getProperty("user.dir")+"\\src\\game\\other\\press");
	// 容器放入所有物件
	private JPanel contentPane = new JPanel();
	private JPanel contentPane_static = new JPanel();
	// 玩家資料
	public List<Record> id_record;
	public Record player;
	public List<His> history = new ArrayList<His>();
	public List<His> history_tetris = new ArrayList<His>();
	public LinkedList<List<His>> history_all = new LinkedList<List<His>>();
	public LinkedList<List<His>> history_tetris_all = new LinkedList<List<His>>();
	// 0~7方塊
	// 101(上)102(下)104(左)105(右)106(空白)107(保存) 100(新增)
	public Frame frame;
	Warn warn;
	Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	All_Layer all_layer_start = new All_Layer();
	All_Layer all_layer_warn = new All_Layer();
	// 基本資料
	public Frame home_frame;
	public Basic basic1;
	public Basic basic2;
	public Record player2 = new Record();
	public int player_num;
	public Home home;
	// RUN
	Time time = new Time();;
	Run run_left;
	Thread left;
	Run run_right;
	Thread right;
	Run run_down;
	Thread down;
	Run run_rotate;
	Thread rotate;
	Run run_a;
	Thread a;
	Run run_d;
	Thread d;
	Run run_s;
	Thread s;
	Run run_w;
	Thread w;

	Tetris_main_game(Home home, List<Record> id_record, Record player, int player_num) {
		this.id_record = id_record;
		this.player = player;
		this.player_num = player_num;
		this.home = home;
		apw = new AePlayWave(2, true);
		apw.setVolume(10);
		if (player_num == 1) {
			get();
			basic1 = new Basic(player, history_tetris, 1);
			basic1.gameplace = new Gameplace(basic1, id_record, player_num, this);
			base_all();
		} else if (player_num == 2) {
			basic1 = new Basic(player);
			basic1.gameplace = new Gameplace(basic1, id_record, player_num, this);
			player2.id.stream().forEach(x -> x.arc = player.id.get(0).arc);
			basic2 = new Basic(player2);
			base_all();
			base_2();
		} else if (player_num == 0) {
			get();
			random();
			basic1 = new Basic(player);
			basic1.gameplace = new Gameplace(basic1, id_record, player_num, this);
			player2.id.stream().forEach(x -> x.arc = player.id.get(0).arc);
			basic2 = new Basic(player2, history_tetris, 0);
			base_all();
			base_2();
		}
	}

	public void get() {
		try { // 防止檔案建立或讀取失敗，用catch捕捉錯誤並列印，也可以throw
			/* 讀入TXT檔案 */
			String[] sep;
			String[] creat;
			String[] Do;
			String[] Do_sep;
			String line = "";
			String pathname = System.getProperty("user.dir")+"\\src\\game\\game.txt"; // 絕對路徑或相對路徑都可以，這裡是絕對路徑，寫入檔案時演示相對路徑
			File filename = new File(pathname); // 要讀取以上路徑的input。txt檔案
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // 建立一個輸入流物件reader
			BufferedReader br = new BufferedReader(reader); // 建立一個物件，它把檔案內容轉成計算機能讀懂的語言

			while ((line = br.readLine()) != null) {
				sep = line.split("b");
				Do = sep[0].split("a");
				creat = sep[1].split("c");
				for (int i = 0; i < Do.length; i++) {
					Do_sep = Do[i].split(" ");
					history.add(new His(Integer.parseInt(Do_sep[0]), Integer.parseInt(Do_sep[1])));
				}
				for (int i = 0; i < creat.length; i++) {
					history_tetris.add(new His(Integer.parseInt(creat[i])));
				}

				history_all.add(new ArrayList<His>());
				history_tetris_all.add(new ArrayList<His>());
				for (int i = 0; i < history.size(); i++) {
					history_all.getLast().add(new His(history.get(i).count, history.get(i).Do));
				}
				for (int i = 0; i < history_tetris.size(); i++) {
					history_tetris_all.getLast().add(new His(history_tetris.get(i).Do));
				}
				history.clear();
				history_tetris.clear();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("exception");
		}
	}
 
	public void random() {
		int tetris = (int) (Math.random() * (history_all.size()));
		System.out.println("right :"+tetris);
		history.clear();
		history_tetris.clear();
		for (His i : history_all.get(tetris)) {
			history.add(new His(i.count, i.Do));
		}
		for (His i : history_tetris_all.get(tetris)) {
			history_tetris.add(new His(i.Do));
		}
	}

	public void base_all() {
		// Frame的資料設定
		basic1.gameplace.add(basic1.Last_Time);
		all_layer_start.bg_img.setSize(screenSize);
		frame = new Frame((screenSize.width / 2) - 300, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight(),
				"Game", 2, all_layer_start.set_bg("5499.jpg"));
		frame.setSize(600, 700);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.getInputContext().dispose();
		all_layer_warn.bg_img.setSize(500, 300);
		warn = new Warn((int) (screenSize.getWidth() / 2) - 250, 150, all_layer_warn.set_bg("6636.jpg"), this);
		Font f2 = new Font("Tekton Pro", Font.BOLD, 40);
		time.setBounds(250, 15, 150, 60);
		time.setFont(f2);
		time.setForeground(Color.white);
		// 容器屬性
		contentPane.setSize(600, 700);
		contentPane.setLayout(null);
		contentPane.setLocation(0, 0);
		contentPane_static.setSize(600, 700);
		contentPane_static.setLayout(null);
		contentPane_static.setLocation(0, 0);
		contentPane_static.setOpaque(false);
		contentPane.setOpaque(false);
		Font f = new Font("Tekton Pro", Font.BOLD, 15);
		basic1.Score.setFont(f);
		basic1.Best_Score.setFont(f);
		basic1.Player.setFont(f);
		basic1.Combo.setFont(f);
		basic1.Lines.setFont(f);
		// 容器放入物件
		contentPane.add(basic1.gameplace);
		contentPane_static.add(basic1.gameplace_bg);
		contentPane_static.add(basic1.t_game);
		contentPane_static.add(basic1.t_hold);
		contentPane.add(basic1.hold);
		contentPane.add(basic1.next);
		contentPane_static.add(basic1.t_next);
		contentPane.add(basic1.Score);
		contentPane.add(basic1.Best_Score);
		contentPane.add(basic1.Player);
		contentPane.add(time);
		contentPane.add(basic1.Lines);
		frame.add_jp(contentPane, JLayeredPane.MODAL_LAYER);
		frame.add_jp(contentPane_static, JLayeredPane.MODAL_LAYER);
		this.frame.addKeyListener(this);
	}

	public void base_2() {
		Font f = new Font("Tekton Pro", Font.BOLD, 15);
		time.setBounds((screenSize.width / 2) - 50, 0, 150, 60);
		basic2.Score.setFont(f);
		basic2.Best_Score.setFont(f);
		basic2.Player.setFont(f);
		basic2.Combo.setFont(f);
		basic2.Lines.setFont(f);

		basic1.Score.setForeground(Color.WHITE);
		basic1.Best_Score.setForeground(Color.WHITE);
		basic1.Player.setForeground(Color.WHITE);
		basic1.Combo.setForeground(Color.WHITE);
		basic1.Lines.setForeground(Color.WHITE);

		basic2.Score.setForeground(Color.BLACK);
		basic2.Best_Score.setForeground(Color.BLACK);
		basic2.Player.setForeground(Color.BLACK);
		basic2.Combo.setForeground(Color.BLACK);
		basic2.Lines.setForeground(Color.BLACK);

		basic1.Score.setBounds(screenSize.width - 540, 300, 100, 50);
		basic1.Best_Score.setBounds(screenSize.width - 140, 400, 100, 50);
		basic1.Player.setBounds(screenSize.width - 140, 500, 100, 50);
		basic1.Combo.setBounds(screenSize.width - 540, 400, 100, 50);
		basic1.Lines.setBounds(screenSize.width - 540, 500, 100, 50);

		basic1.t_game.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.15f));
		basic1.t_hold.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.15f));
		basic1.t_next.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.15f));

		basic1.gameplace.setBounds(screenSize.width - 450, 70, 300, 600);
		basic1.next.setBounds(screenSize.width - 140, 70, 100, 180);
		basic1.hold.setBounds(screenSize.width - 560, 70, 100, 180);
		basic1.gameplace_bg.setBounds(screenSize.width - 450, 70, 300, 600);
		basic1.t_game.setBounds(screenSize.width - 450, 70, 300, 600);
		basic1.t_hold.setBounds(screenSize.width - 560, 70, 100, 180);
		basic1.t_next.setBounds(screenSize.width - 140, 70, 100, 180);
		frame.setLocation(0, 0);
		basic2.gameplace = new Gameplace(basic2, id_record, player_num, this);
		basic2.gameplace.add(basic2.Last_Time);
		contentPane.setSize(screenSize);
		contentPane_static.setSize(screenSize);
		contentPane.add(basic2.gameplace);
		contentPane_static.add(basic2.gameplace_bg);
		contentPane.add(basic2.hold);
		contentPane.add(basic2.next);
		contentPane_static.add(basic2.t_game);
		contentPane_static.add(basic2.t_hold);
		contentPane_static.add(basic2.t_next);
		contentPane.add(basic2.Lines);
		contentPane.add(basic2.Score);
		contentPane.add(basic2.Best_Score);
		contentPane.add(basic2.Player);
		frame.setSize(screenSize.width, screenSize.height);
	}

	public void running() {
		Thread gp = new Thread(time);
		gp.start();
		run_left = new Run("left", basic1.gameplace);
		left = new Thread(run_left);
		left.start();
		run_right = new Run("right", basic1.gameplace);
		right = new Thread(run_right);
		right.start();
		run_down = new Run("down", basic1.gameplace);
		down = new Thread(run_down);
		down.start();
		run_rotate = new Run("rotate", basic1.gameplace);
		rotate = new Thread(run_rotate);
		rotate.start();
		if (player_num == 2 || player_num == 0) {
			run_a = new Run("a", basic2.gameplace);
			a = new Thread(run_a);
			a.start();
			run_d = new Run("d", basic2.gameplace);
			right = new Thread(run_d);
			right.start();
			run_s = new Run("s", basic2.gameplace);
			s = new Thread(run_s);
			s.start();
			run_w = new Run("w", basic2.gameplace);
			w = new Thread(run_w);
			w.start();
		}
		if (player_num == 0) {
			time.set();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	// (1,2)101(上)102(下)104(左)105(右)106(空白)107(保存)
	@Override
	public void keyReleased(KeyEvent e) {
		if (player_num == 2) {
			if (e.getKeyCode() == KeyEvent.VK_T) {
				run_w.press = false;
			} else if (e.getKeyCode() == KeyEvent.VK_F) {
				run_a.press = false;
			} else if (e.getKeyCode() == KeyEvent.VK_H) {
				run_d.press = false;
			} else if (e.getKeyCode() == KeyEvent.VK_G) {
				run_s.press = false;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			if (player_num == 1)
				history.add(new His(time.his_time, 1042));
			run_left.press = false;

		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			if (player_num == 1)
				history.add(new His(time.his_time, 1052));
			run_right.press = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

			if (player_num == 1)
				history.add(new His(time.his_time, 1022));

			run_down.press = false;
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {

			if (player_num == 1)
				history.add(new His(time.his_time, 1012));
			run_rotate.press = false;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		apw2 = new AePlayWave(System.getProperty("user.dir")+"\\src\\game\\other\\press.wav", 10);
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (player_num == 1 && (history.size() == 0 || history.get(history.size() - 1).Do != 1011)) {
				history.add(new His(time.his_time, 1011));
			}
			run_rotate.press = true;
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			apw2.start();
			if (player_num == 1)
				history.add(new His(time.his_time, 106));
			basic1.gameplace.Space();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (player_num == 1 && (history.size() == 0 || history.get(history.size() - 1).Do != 1041)) {
				history.add(new His(time.his_time, 1041));
			}
			run_left.press = true;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (player_num == 1 && (history.size() == 0 || history.get(history.size() - 1).Do != 1051)) {
				history.add(new His(time.his_time, 1051));
			}
			run_right.press = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (player_num == 1 && (history.size() == 0 || history.get(history.size() - 1).Do != 1021)) {
				history.add(new His(time.his_time, 1021));
			}
			run_down.press = true;
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			stop();
		}
		if (player_num != 2 && e.getKeyCode() == KeyEvent.VK_SHIFT) {
			if (player_num == 1 && (history.size() == 0 || history.get(history.size() - 1).Do != 1021)) {
				history.add(new His(time.his_time, 107));
			}
			basic1.gameplace.Shift();
		}

		if (player_num == 2) {
			if (e.getKeyCode() == KeyEvent.VK_T) {
				run_w.press = true;
			} else if (e.getKeyCode() == KeyEvent.VK_F) {
				run_a.press = true;
			} else if (e.getKeyCode() == KeyEvent.VK_H) {
				run_d.press = true;
			} else if (e.getKeyCode() == KeyEvent.VK_G) {
				run_s.press = true;
			} else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
				apw2.start();
				basic2.gameplace.Space();
			} else if (e.getKeyCode() == KeyEvent.VK_Z) {
				basic2.gameplace.Shift();
			} else if (e.getKeyCode() == KeyEvent.VK_M) {

				basic1.gameplace.Shift();
			}
		}
	}

	int r = 0;
	boolean stoptimes = true;

	public void stop() {

		if (stoptimes) {
			apw.Stop();
			stoptimes = false;
		}
		time.run = false;
		basic1.gameplace.run_g = false;
		if (player_num != 1)
			basic2.gameplace.run_g = false;
		if (time.min < 0 || (time.min == 0 && time.sec == 0)) {
			warn.timesup();
			warn.setFocusable(true);
			if (player_num == 1) {
				home.WriteRate();
				write();
			}
			if (player_num != 1) {
				basic2.gameplace.run_g = false;
			}
		}
		if (time.min >= 0 && r == 0) {
			warn.stopgame();

			r = 0;
		}
		r++;
	}

	boolean winn = false;
	boolean write = false;

	public void write() {
		if (!write) {
			if (player_num == 1) {
				history_all.add(new ArrayList<His>());
				history_tetris_all.add(new ArrayList<His>());
				for (int i = 0; i < history.size(); i++) {
					history_all.getLast().add(new His(history.get(i).count, history.get(i).Do));
				}
				for (int i = 0; i < history_tetris.size(); i++) {
					history_tetris_all.getLast().add(new His(history_tetris.get(i).Do));
				}
			}
			try {
				File writename = new File(System.getProperty("user.dir")+"\\src\\game\\ID.txt"); // 相對路徑，如果沒有則要建立一個新的output。txt檔案
				BufferedWriter out = new BufferedWriter(new FileWriter(writename));
				for (Record i : id_record) {
					out.write(i.Name + " " + i.best_score + " " + i.pwd);
					for (int j = 3; j < 27; j++) {
						out.write(" " + i.Score[j]);
					}
					out.write(" " + Integer.toString(i.arc) + " " + Integer.toString(i.grade));
					out.write("\r\n");
				}
				out.flush(); // 把快取區內容壓入檔案
				out.close(); // 最後記得關閉檔案
				File writename1 = new File(System.getProperty("user.dir")+"\\src\\game\\game.txt"); // 相對路徑，如果沒有則要建立一個新的output。txt檔案
				BufferedWriter out1 = new BufferedWriter(new FileWriter(writename1));
				for (int j = 0; j < history_all.size(); j++) {
					for (int i = 0; i < history_all.get(j).size(); i++) {
						out1.write(history_all.get(j).get(i).count_s + " " + history_all.get(j).get(i).Do_s
								+ ((i == history_all.get(j).size() - 1) ? "b" : "a"));
					}
					for (int i = 0; i < history_tetris_all.get(j).size(); i++) {
						out1.write(history_tetris_all.get(j).get(i).Do_s
								+ ((i == history_tetris_all.get(j).size() - 1) ? "" : "c"));
					}
					out1.write("\r\n");
				}
				out1.flush(); // 把快取區內容壓入檔案
				out1.close();
				write = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	class Run implements Runnable {
		boolean run = true;
		boolean press = false;
		String use;
		Gameplace game;

		Run(String use, Gameplace game) {
			this.use = use;
			this.game = game;
		}

		@Override
		public void run() {
			try {
				while (run) {
					while (press) {
						if (use.equals("right")) {
							game.right();
						} else if (use.equals("left")) {
							game.left();

						} else if (use.equals("down")) {
							game.down();
						} else if (use.equals("rotate")) {
							game.rotate();
						} else if (use.equals("w")) {
							game.rotate();
						} else if (use.equals("a")) {
							game.left();
						} else if (use.equals("d")) {
							game.right();
						} else if (use.equals("s")) {
							game.down();
						}
						for (int i = 0; i < 50; i++) {
							if (press == false)
								break;
							Thread.sleep(1);
						}
						Thread.sleep(75);
					}
					Thread.sleep(1);
				}
			} catch (Exception e) {
			}

		}

	}

	class Time extends JLabel implements Runnable {
		int time = 0;
		boolean run = true;
		int min = 1;
		int sec = 0;
		int count = 0;
		int his_time = 0;
		public LinkedList<His> History = new LinkedList<His>();

		public Time() {
			this.setText(min + " : " + ((sec > 9) ? sec : "0" + sec));
		}

		int k = 0;

		public void set() {
			for (His i : history) {
				this.History.add(new His(i.count, i.Do));
			}
		}

		@Override
		public void run() {
			try {
				while (run) {
					for (int i = 0; i < 1000; i++) {
						if (run == false)
							break;
						Thread.sleep(1);
						if (player_num == 0) {
							if (his_time == History.getFirst().count) {
								while (true) {
									int k = History.getFirst().Do;
									if (k == 1011)
										run_w.press = true;
									else if (k == 1021)
										run_s.press = true;
									else if (k == 1041)
										run_a.press = true;
									else if (k == 1051)
										run_d.press = true;
									else if (k == 1012)
										run_w.press = false;
									else if (k == 1022)
										run_s.press = false;
									else if (k == 1042)
										run_a.press = false;
									else if (k == 1052)
										run_d.press = false;
									else if (k == 106) {
										basic2.gameplace.Space();
									} else if (k == 107)
										basic2.gameplace.Shift();
									History.removeFirst();
									if (History.size() == 0) {
										run = false;
										break;
									}
									if (his_time != History.getFirst().count)
										break;
								}
							}
							if (basic2.gameplace.Upcheck()) {
								run = false;
								break;
							}
						}
						his_time++;
					}
					count++;
					sec--;
					if (sec < 0) {
						sec = 59;
						min--;

						if (min < 0)
							break;
					}
					if (min == 0 && sec <= 5) {
						basic1.Last_Time.setText(Integer.toString(sec));
						if (player_num != 1)
							basic2.Last_Time.setText(Integer.toString(sec));
					}
					this.setText(min + " : " + ((sec > 9) ? sec : "0" + sec));
				}
//				System.out.println("game.stop() time");
				stop();
			} catch (Exception e) {
				System.out.print("Time exception");
				e.printStackTrace();
				stop();
			}
		}
	}

	class Warn extends Frame implements MouseListener, KeyListener {
		Font f2 = new Font("Tekton Pro", Font.BOLD, 40);
		public JLabel win_l = new JLabel(" ", SwingConstants.CENTER);
		public JPanel contentPane = new JPanel();
		public Frame frame;
		Tetris_main_game game;

		Warn(int x, int y, JPanel pp, Tetris_main_game game) {
			super(x, y, 500, 300, "Gameover", 2, pp);
			this.addMouseListener(this);
			this.frame = game.frame;
			this.game = game;
			win_l.setOpaque(false);
			win_l.setBounds(0, 30, 500, 200);
			win_l.setFont(f2);
			win_l.setForeground(Color.WHITE);
			contentPane.setBounds(0, 0, 500, 300);
			contentPane.setOpaque(false);
			contentPane.setLayout(null);
			contentPane.add(win_l);
			this.setUndecorated(true);
			this.add_jp(contentPane, JLayeredPane.MODAL_LAYER);
		}

		public void winner(String winner) {
			win_l.setText("<html>" + winner + "<br>" + " win the game ！" + "</html>");
			this.setVisible(true);
		}

		public void lose(String winner) {
			win_l.setText("<html>" + winner + "<br>" + " lose the game ！" + "</html>");
			this.setVisible(true);
		}

		public void equal() {
			win_l.setText("Both are winner");
			this.setVisible(true);
		}

		public void timesup() {
			win_l.setText("Time's Up");
			this.setVisible(true);
		}

		public void stopgame() {
			win_l.setText("Exit The Game !");
			this.setVisible(true);
		}

		public void win() {
			if (game.player_num != 1) {
				if (game.basic1.gameplace_bg.Now_Score > game.basic2.gameplace_bg.Now_Score) {
//					System.out.println("player1 win");
					winner(game.player.Name);
				} else if (game.basic1.gameplace_bg.Now_Score == game.basic2.gameplace_bg.Now_Score) {
//					System.out.println("both win");
					equal();
				} else {
//					System.out.println("player2 win");
					lose(Name);
				}
				game.winn = true;
				one = false;
			}
		}

		boolean one = true;
		boolean music = true;

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		public void music() {
			game.home.home_frame.setVisible(true);
			game.home.apw = new AePlayWave(0, true);
			game.home.apw.start();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (!one) {
				frame.dispose();
				this.setVisible(false);
				music();
				music = false;
			} else {
				if (game.player_num == 1) {
					frame.dispose();
					this.setVisible(false);
					music();
					game.home.home_frame.setVisible(true);
				} else {
					win();
				}
			}

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
			if (!one) {
				frame.dispose();
				this.setVisible(false);
				music();
				music = false;
			} else {
				if (game.player_num == 1) {
					frame.dispose();
					this.setVisible(false);
					music();
					game.home.home_frame.setVisible(true);
				} else {
					win();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

	}
}

class Gameplace extends canvas_place implements Runnable {
	boolean pressdown = false;
	boolean run_g = true;
	List<Integer> dis_y = new ArrayList<Integer>();
	List<conponent_xy> old = new ArrayList<conponent_xy>();
	Basic basic;
	int out_x = 0;
	int out_y = 0;
	int player_num;
	List<Record> id_record;
	Tetris_main_game game;
	Gameplace(Basic basic, List<Record> id_record, int player_num, Tetris_main_game game) {
		super(150, 70, 300, 600);
		this.player_num = player_num;
		this.id_record = id_record;
		this.basic = basic;
		this.setOpaque(false);
		this.game = game;
		gameplace();

	}

	public void gameplace() {
		basic.add_intls();
		basic.Create();
		basic.add_nextls();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		sec_tetris();
		basic.ls.getFirst().draw(g, this);
		sec_tetris();
		basic.sec_ls.getFirst().con_te.stream().forEach(x -> x.sec_draw(g));
	}

	public void rotate() {
		for (conponent_xy i : basic.ls.getFirst().con_te) {
			old.add(new conponent_xy(i.getX(), i.getY()));
		}
		basic.ls.getFirst().rotate();
		recheck(old, true);
		old.clear();
		repaint();
	}

	public void down() {
		pressdown = true;
		for (conponent_xy i : basic.ls.getFirst().con_te) {
			old.add(new conponent_xy(i.getX(), i.getY()));
		}
		if (recheck(old, false)) {

			basic.ls.getFirst().move_down();
		}
		recheck(old, false);
		old.clear();
		repaint();
	}

	public void right() {
		for (conponent_xy i : basic.ls.getFirst().con_te) {
			old.add(new conponent_xy(i.getX(), i.getY()));
		}
		basic.ls.getFirst().move_r();
		recheck(old, false);
		old.clear();
		repaint();
	}

	public void left() {
		for (conponent_xy i : basic.ls.getFirst().con_te) {
			old.add(new conponent_xy(i.getX(), i.getY()));
		}
		basic.ls.getFirst().move_l();
		recheck(old, false);
		old.clear();
		repaint();
	}

	public void Space() {

		for (int i = 0; i < basic.ls.getFirst().con_te.size(); i++) {
			basic.ls.getFirst().con_te.get(i).setX(basic.sec_ls.getFirst().con_te.get(i).getX());
			basic.ls.getFirst().con_te.get(i).setY(basic.sec_ls.getFirst().con_te.get(i).getY());
		}
		if (check()) {
//			System.out.println("chchchcc11111");
			run_g = false;
			run();
		}

		repaint();
	}

	public void Shift() {
		basic.ch_hold();
		basic.hold.repaint();
		repaint();
	}

	boolean sec_ls_boolean = false;

	public void sec_tetris() {
		if (sec_ls_boolean)
			return;
		dis_y.clear();
		for (int i = 0; i < basic.sec_ls.getFirst().con_te.size(); i++) {
			basic.sec_ls.getFirst().con_te.get(i).setX(basic.ls.getFirst().con_te.get(i).getX());
		}
		for (conponent_xy i : basic.ls.getFirst().con_te) {
			int bg_y = 600;
			int min_y = 600;
			for (int j = (i.getY() > 0) ? i.getY() : 0; j < 600; j += 30) {
				if (basic.bg_xy[j][i.getX()].getUse() == true) {
					bg_y = basic.bg_xy[j][i.getX()].getY();
					break;
				}
			}
			min_y = bg_y - i.getY() - 30;
			if (min_y >= 0)
				dis_y.add(min_y);
		}
		if (run_g == false)
			return;
		for (int i = 0; i < basic.sec_ls.getFirst().con_te.size(); i++) {
			basic.sec_ls.getFirst().con_te.get(i).setY(basic.ls.getFirst().con_te.get(i).getY()
					+ dis_y.stream().min((s1, s2) -> (s1 > s2) ? 1 : -1).get());

		}

	}

	public boolean Press(int x, int y) {
		if (x >= 0 && x < 300 && y < 600 && y >= 0 && basic.bg_xy[y][x].getUse() == true)
			return true;
		return false;
	}

	public void change_ls() {
		basic.ls.getFirst().con_te.stream().forEach(x -> x.setX(x.getX() + out_x));
		basic.ls.getFirst().con_te.stream().forEach(x -> x.setY(x.getY() + out_y));
	}

	// 判斷是可往下一步移動
	public boolean recheck(List<conponent_xy> old, boolean rotate) {
		out_x = 0;
		out_y = 0;
		for (conponent_xy i : basic.ls.getFirst().con_te) {

			if (i.getX() < 0) {
				out_x = basic.ls.getFirst().con_te.stream().min((s1, s2) -> (s1.getX() > s2.getX()) ? 1 : -1).get()
						.getX();
				out_x = 0 - out_x;
				if (rotate) {
				}
				change_ls();
			} else if (i.getX() > 270) {
				out_x = basic.ls.getFirst().con_te.stream().max((s1, s2) -> (s1.getX() > s2.getX()) ? 1 : -1).get()
						.getX();
				out_x = 270 - out_x;
				if (rotate) {
				}
				change_ls();
			} else if (i.getY() > 570) {
				out_y = basic.ls.getFirst().con_te.stream().max((s1, s2) -> (s1.getY() > s2.getY()) ? 1 : -1).get()
						.getY();
				out_y = 570 - out_y;
				if (rotate) {
				}
				change_ls();
			}
			if (Press(i.getX(), i.getY())) {
				for (int j = 0; j < basic.ls.getFirst().con_te.size(); j++) {
					basic.ls.getFirst().con_te.get(j).setX(old.get(j).getX());
					basic.ls.getFirst().con_te.get(j).setY(old.get(j).getY());
				}
				return false;
			}

		}
		return true;
	}

	public boolean Upcheck() {
		// 判斷最上面是否為true
		for (int i = 0; i < 300; i += 30) {
			if (basic.bg_xy[0][i].getUse() == true) {
//				System.out.println("Gameover");
				if (player_num != 0) {
					game.basic2.gameplace.run_g = false;
					game.basic2.gameplace.run();
				}
				return true;
			}
		}
		return false;
	}

	// 如果check為true停止
	public boolean check() {
		if (stop()) {

			// 停止的位置BG[][]改成true
			sec_tetris();
			for (conponent_xy i : basic.sec_ls.getFirst().con_te) {
				if (i.getY() < 0 || i.getX() < 0)
					return true;
				basic.bg_xy[i.getY()][i.getX()].setUse(true);
				basic.bg_xy[i.getY()][i.getX()].setColor(i.getColor());

			}
			if (Upcheck())
				return true;
//			// 判斷最上面是否為true
//			for (int i = 0; i < 300; i += 30) {
//				if (basic.bg_xy[0][i].getUse() == true) {
//					System.out.println("Gameover");
//					if(player_num!=0) {game.basic2.gameplace.run_g=false;
//					game.basic2.gameplace.run();
//					}
//					return true;
//				}
//			}

			// 處理特殊方塊
			if (basic.ls.getFirst().id_num == 7) {
				basic.gameplace_bg.special_int = 7;
			}

			// 更新全部東西
			basic.ls.removeFirst();
			basic.sec_ls.removeFirst();
			basic.int_ls.removeFirst();
			basic.nextls.removeFirst();
			basic.add_intls();
			basic.add_nextls();
			basic.Create();
			basic.gameplace_bg.repaint();
			basic.next.repaint();
			sec_ls_boolean = false;
			return false;
		}
		return false;
	}

	// 如果stop成立 check執行
	public boolean stop() {

		// 強制先製造sec_ls
		sec_tetris();
		boolean[] stop = new boolean[basic.sec_ls.getFirst().con_te.size()];
		for (int i = 0; i < basic.sec_ls.getFirst().con_te.size(); i++) {
			stop[i] = ((basic.sec_ls.getFirst().con_te.get(i).getX() != basic.ls.getFirst().con_te.get(i).getX())
					|| (basic.sec_ls.getFirst().con_te.get(i).getY() != basic.ls.getFirst().con_te.get(i).getY()))
							? false
							: true;
		}

		// 判斷是否停止
		for (boolean i : stop) {
			if (i == false) {
				return false;
			}
		}

		// 處理特殊書方塊
		if ((basic.sec_ls.getFirst().id_num == 7)) {
			basic.gameplace_bg.special_row = basic.sec_ls.getFirst().con_te.get(0).getY();
			basic.gameplace_bg.special_colunm = basic.sec_ls.getFirst().con_te.get(0).getX();
		}
		return true;
	}

	@Override
	public void run() {

		// 重繪面板
		basic.hold.repaint();
		basic.next.repaint();
		try {
			while (run_g) {
				down();
				for (int i = 0; i < 100; i++) {

					if (pressdown == true) {
						i = 0;
						pressdown = false;
					}
					Thread.sleep(8);
				}

				// 查看是否結束
				if (check()) {
//					System.out.println("chchchcc");
					run_g = false;
					break;
				}
				repaint();
			}
			game.stop();
//			System.out.println("game.stop");

		} catch (Exception e) {
			System.out.println("excrption game" + player_num + basic.player.Name);
			game.stop();
		}

	}

}
