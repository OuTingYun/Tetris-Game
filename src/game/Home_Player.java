package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Home_Player implements ActionListener, KeyListener {

//	this.id_record=id_record;;;;;;;;;;;;;;;;;;;;;;   ;;;;;; ;;  ;;    ;;;;;; ;;;;;; ;;    ;; ;;;;;;    ;;    ;; ;;  ;; ;;    ;;    ;;; ;;; ;;; ;
//	acc_pwd.setLayout(new GridLayout(5,5));;;;;;;;   ;;  ;; ;;  ;;      ;;     ;;   ;;;;  ;; ;;         ;;  ;;  ;;  ;; ;;;;  ;;    ;   ; ; ; ; ;
//	sep.setBounds(0,200 , this.getWidth(), 20);;;;   ;;  ;; ;;  ;;      ;;     ;;   ;; ;; ;; ;; ;;;      ;;;;   ;;  ;; ;; ;; ;;    ;   ; ; ; ; ;
//	acc_pwd.setBounds(0, 0, this.getWidth(), 180);   ;;  ;; ;;  ;;      ;;     ;;   ;;  ;;;; ;;  ;;       ;;    ;;  ;; ;;  ;;;;    ;   ; ; ; ; ;
//	rate.setBounds(0, 210, this.getWidth(), 200);;   ;;;;;;  ;;;;       ;;   ;;;;;; ;;    ;; ;;;;;;       ;;     ;;;;  ;;    ;;    ;;; ;;; ;;; ;;;   

	// 嚙踝蕭�謒蕭嚙踝蕭
	Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	public All_Layer all_layer_player = new All_Layer();
	All_Layer wel=new All_Layer();
	// JPanel Container
	public JPanel acc_pwd = new JPanel();
	public JPanel acc_pwd_new = new JPanel();
	public JPanel contentPane_accpwd = new JPanel();
	public JPanel contentPane = new JPanel();
	public JPanel[][] contentPane_ap;
	public JPanel[][] contentPane_new;

	// ��嚙踐嚙踝嚙踝蕭
	public JLabel warn = new JLabel();
	public JLabel newwarn_acc = new JLabel();
	public JLabel newwarn_pwd = new JLabel();
	public JLabel acc_l = new JLabel("Account");
	public JLabel pwd_l = new JLabel("Password");
	public JLabel acc_l1 = new JLabel("New Account");
	public JLabel pwd_l1 = new JLabel("New Password");

	// ��嚙踝��蕭�
	public JComboBox acc;
	public JTextField newacc = new JTextField(30);
	public JTextField newpwd = new JTextField(30);
	public JPasswordField pwd = new JPasswordField(12);

	// �蝞蕭謍梧蕭�嚙踝蕭
	public Home_btn ok = new Home_btn(0, 0, 50, 30, "ok", "ok");
	public Home_btn create = new Home_btn(0, 50, 10, 30, "new", "new");
	public Home_btn ok_new = new Home_btn(0, 0, 50, 30, "ok_new", "ok");
	public Home_btn back_new = new Home_btn(0, 0, 50, 30, "back_new", "back");
	// �頛魂�蕭嚙踐�蕭�嚙踝蕭
	public Record player;
	public List<Record> id_record = new ArrayList<Record>();// 嚙踝蕭嚙踝蕭�����蕭�嚙踝蕭

	// 嚙踝���蕭
	public JSeparator sep = new JSeparator();
	Home home;
	String[] Score;
	String line = "";
	public Frame home_player;

	// ��蕭嚙踝蕭謘踐�蕭嚙踝��蕭���嚙踝蕭
	String NewAcc;
	String NewPwd;
	String[] NewScore;
	public static void main(String[] args) {
		new Home_Player();
	}
	Home_Player() {

		// ��蕭嚙踝��嚙踝蕭
		try { // 嚙踐��撓嚙踐�蕭�����蕭謘選蕭嚙踝蕭謘潔�蕭嚙踐不嚙踝��atch嚙踝蕭��蕭������蕭嚙踐���嚙踐���hrow
			/* ��蕭嚙踝TXT���蕭嚙� */
			String pathname = System.getProperty("user.dir")+"\\src\\game\\id.txt"; // ���蕭����嚙踐▽謆������蕭����蕭�嚙踐��蕭謢����蕭����嚙踝蟡蕭����蕭��蕭蹇蕭��嚙踐����嚙�
			File filename = new File(pathname); // �蹓橘蕭嚙踝蕭謘餉�����嚙踝nput嚙踐�t���蕭嚙�
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // �蝞蕭��蕭嚙踝��蕭���播����ader
			BufferedReader br = new BufferedReader(reader); // �蝞蕭��蕭嚙踝����蕭�嚙踝嚙踝�蕭��蕭���撖∴蕭�嚙踐�蕭��蕭謅蕭賹��蕭嚙踝蕭蹇蕭��蕭豯改蕭
			while ((line = br.readLine()) != null) {
				Score = line.split(" ");
				id_record.add(new Record(Score));
			} // ��蕭���蕭嚙踝��蕭��嚙踝嚙踝蕭
			/* ���Txt���蕭嚙� */
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("exception");
		}
		String[] account_num = new String[id_record.size()];
		for (int i = 0; i < id_record.size(); i++) {
			account_num[i] = id_record.get(i).Name;
		}

		// 嚙踝蕭�謒�蕭
		wel.bg_img.setBounds(170, 270, 500, 100);
		all_layer_player.bg_img.setBounds(0, 0,900,650);
		home_player = new Frame(250, 10, 800, 650, "Player", 2, all_layer_player.set_bg("color55.jpg"));
		home_player.setUndecorated(true);
		home_player.setVisible(true);
		// 嚙踐貝嚙踝��蕭
		contentPane.setLayout(null);
		contentPane.setBounds(0, 0, 800, 650);
		contentPane.setOpaque(false);
		acc_pwd.setBounds(home_player.getWidth()/2-200, 450, 400, 80);
		acc_pwd.setOpaque(false);
		acc_pwd.setLayout(new GridLayout(3, 3, 5, 0));
		contentPane_ap = new JPanel[3][3];
		acc = new JComboBox(account_num);
		
		// 嚙踐�蕭���蕭
		contentPane_accpwd.setBorder(null);
		contentPane_accpwd.setBounds(home_player.getWidth()/2-250, 450, 500, 80);
		contentPane_accpwd.setBackground(new Color(1.0f, 1.0f, 01.0f, 0.5f));

		// �擗蕭�����
		acc_pwd_new.setBounds(home_player.getWidth()/2-200, 450, 400, 80);
		acc_pwd_new.setOpaque(false);
		acc_pwd_new.setLayout(new GridLayout(3, 3, 5, 0));
		contentPane_new = new JPanel[3][3];

		// Grid 嚙踐�蕭謓梢��蕭嚙�
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				contentPane_ap[i][j] = new JPanel();
				contentPane_ap[i][j].setLayout(new GridLayout(1, 1));
				contentPane_ap[i][j].setOpaque(false);
				acc_pwd.add(contentPane_ap[i][j]);
				contentPane_new[i][j] = new JPanel();
				contentPane_new[i][j].setLayout(new GridLayout(1, 1));
				contentPane_new[i][j].setOpaque(false);
				acc_pwd_new.add(contentPane_new[i][j]);
			}
		}
		// 嚙踐貝嚙踝��蕭
		acc.addActionListener(this);
		acc.setBackground(Color.white);
		pwd.setEchoChar('*');
		Font f = new Font("Tekton Pro", Font.BOLD, 18);
		
		acc_l.setFont(f);
		pwd_l.setFont(f);
		acc_l1.setFont(f);
		pwd_l1.setFont(f);
		warn.setFont(f);
		newwarn_acc.setFont(f);
		newwarn_pwd.setFont(f);
		acc_l.setForeground(Color.black);
		pwd_l.setForeground(Color.black);
		warn.setForeground(Color.black);
		acc_l1.setForeground(Color.black);
		pwd_l1.setForeground(Color.black);
		
		newwarn_acc.setForeground(Color.black);
		newwarn_pwd.setForeground(Color.black);
		contentPane_ap[0][0].add(acc_l);
		contentPane_ap[0][1].add(pwd_l);
		contentPane_ap[1][0].add(acc);
		contentPane_ap[1][1].add(pwd);
		contentPane_ap[1][2].setLayout(new GridLayout(1, 2, 5, 10));
		contentPane_ap[1][2].add(ok);
		contentPane_ap[1][2].add(create);
		contentPane_ap[2][1].add(warn);

		// �擗蕭�����
		ok_new.addActionListener(this);
		contentPane_new[0][0].add(acc_l1);
		contentPane_new[0][1].add(pwd_l1);
		contentPane_new[1][0].add(newacc);
		contentPane_new[1][1].add(newpwd);
		contentPane_new[1][2].setLayout(new GridLayout(1, 2, 5, 10));
		contentPane_new[1][2].add(ok_new);
		contentPane_new[1][2].add(back_new);
		contentPane_new[2][0].add(newwarn_acc);
		contentPane_new[2][1].add(newwarn_pwd);

		// 嚙踐�蕭蹓��麾
		ok.addActionListener(this);
		create.addActionListener(this);
		back_new.addActionListener(this);
		contentPane.add(acc_pwd);
		contentPane.add(acc_pwd_new);
		acc_pwd_new.setVisible(false);
		contentPane.add(contentPane_accpwd);
		contentPane.add(wel.set_bg("wel.png"));

		// �摮蛔�蕭��蕭������
		home_player.add_jp(contentPane, JLayeredPane.MODAL_LAYER);
		pwd.addKeyListener(this);
		newacc.addKeyListener(this);
		newpwd.addKeyListener(this);
		acc.addKeyListener(this);
	
	}

	// 嚙踐�蕭蹓�
	public void OK() {
		String account;
		String password;
		account = (String) acc.getSelectedItem();// 嚙踝嚙踝蕭謘潘蕭嚙�
		password = String.valueOf(pwd.getPassword());
		if (!check(account, password)) {
			warn.setText("error");
		} else {
			player = id_record.stream().filter(x -> x.login == true).collect(Collectors.toList()).get(0);
			home = new Home(id_record, player,this);
			home_player.setVisible(false);
		}
	}

	// �擗蕭���嚙踝蕭嚙�
	public void create() {
		NewScore = new String[] { NewAcc, "0", NewPwd, "0", "255", "255", "250", "128", "144", "0", "191", "255", "255",
				"215", "0", "255", "130", "171", "131", "111", "255", "152", "251", "152", "0", "0", "0" };
		id_record.add(new Record(NewScore));
		String[] account_num = new String[id_record.size()];
		for (int i = 0; i < id_record.size(); i++) {
			account_num[i] = id_record.get(i).Name;
		}
		contentPane_ap[1][0].remove(acc);
		acc = new JComboBox(account_num);
		contentPane_ap[1][0].add(acc);
	}

	// 嚙踐��嚙踐�蕭�heck
	public boolean Newcheck() {
		if (NewAcc.length() == 0 || NewPwd.length() == 0)
			return false;
		for (int i = 0; i < NewAcc.length(); i++) {
			if (NewAcc.charAt(i) == ' ') {
				newwarn_acc.setText("Don't input space");
				return false;
			}
			newwarn_acc.setText("OK!");
		}

		for (int i = 0; i < NewPwd.length(); i++) {
			if (NewPwd.charAt(i) == ' ') {
				newwarn_pwd.setText("Don't input space");
				return false;
			}
			newwarn_pwd.setText("OK!");
		}
		for (Record i : id_record) {
			if (i.Name.contentEquals(NewAcc)) {
				newwarn_acc.setText("Be Used");
				return false;
			}
		}
		return true;

	}

	// 嚙踐貝嚙踝check
	public boolean check(String account, String password) {
		for (Record i : id_record) {
			if (i.Name.equals(account) && (i.pwd.equals(password))) {
//				System.out.println("ok");
				i.login = true;
				warn.setText("ok");
				return true;
			}
		}
		return false;
	}

	// ��謒�
	public void clear() {
		newpwd.setText("");
		newacc.setText("");
		newwarn_pwd.setText("");
		newwarn_acc.setText("");
		pwd.setText("");
		warn.setText("");
	}

	// 嚙踝�嚙�
	public void output() {
		try {

			File writename = new File(System.getProperty("user.dir")+"\\src\\game\\ID.txt"); // 嚙踐�����嚙踝嚙踐�蕭謚恬蕭��蕭�嚙踝嚙踐撒����蕭嚙踝��蕭嚙踝utput嚙踐�t���蕭嚙�
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			for (Record i : id_record) {
				out.write(i.Name + " " + i.best_score + " " + i.pwd);
				for (int j = 3; j < 27; j++) {
					out.write(" " + i.Score[j]);
				}
				out.write("0 0");
				out.write("\r\n");
			}
			out.flush(); // 嚙踝蕭��蝧堆蕭嚙踐□嚙踝蕭��摮蛛蕭�����蕭嚙�
			out.close(); // 嚙踝蕭��嚙踐朱嚙踐�蕭謚殷蕭�嚙踐�蕭嚙�
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String commend = e.getActionCommand();
		if (commend.contentEquals("ok")) {
			OK();
			clear();
		} else if (commend.contentEquals("new")) {
			acc_pwd_new.setVisible(true);
			acc_pwd.setVisible(false);
			clear();
		} else if (commend.contentEquals("ok_new")) {
			NewAcc = newacc.getText();
			NewPwd = newpwd.getText();
			if (Newcheck()) {
				create();
				output();
				
				acc_pwd_new.setVisible(false);
				acc_pwd.setVisible(true);
			}
		}else if(commend.contentEquals("back_new")){
			clear();
			acc_pwd_new.setVisible(false);
			acc_pwd.setVisible(true);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			NewAcc = newacc.getText();
			NewPwd = newpwd.getText();
			if (acc_pwd.isVisible())
				OK();
			else if (Newcheck()) {
				create();
				output();
				acc_pwd_new.setVisible(false);
				acc_pwd.setVisible(true);
			}
			clear();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
