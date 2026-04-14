import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.sound.midi.*;
import java.util.Stack;

class Tuixiangzi {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new mainFrame());
    }
}

class mainFrame extends JFrame implements ActionListener, ItemListener {
    JLabel lb;
    JLabel lb2;
    JButton btnrenew, btnlast, btnnext, btnchoose, btnfirst, btnover, btnmuc, btnback;
    mainpanel panel;
    Sound sound;
    JComboBox jc = new JComboBox();
    MenuItem renew = new MenuItem("    重新开始");
    MenuItem back = new MenuItem("    悔一步");
    MenuItem last = new MenuItem("    上一关");
    MenuItem next = new MenuItem("    下一关");
    MenuItem choose = new MenuItem("    选关");
    MenuItem exit = new MenuItem("    退出");
    MenuItem qin = new MenuItem("    琴萧合奏");
    MenuItem po = new MenuItem("    泡泡堂");
    MenuItem guang = new MenuItem("    灌篮高手");
    MenuItem nor = new MenuItem("    默认");
    MenuItem eye = new MenuItem("    eyes on me");
    MenuItem about = new MenuItem("    关于推箱子...");

    mainFrame() {
        super("推箱子v2.0");
        setSize(720, 720);
        setResizable(false);
        setLocation(300, 20);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container cont = getContentPane();
        cont.setLayout(null);
        cont.setBackground(Color.black);

        Menu choice = new Menu("    选项");
        choice.add(renew);
        choice.add(last);
        choice.add(next);
        choice.add(choose);
        choice.add(back);
        choice.addSeparator();
        choice.add(exit);

        Menu setmuc = new Menu("    设置音乐");
        setmuc.add(nor);
        setmuc.add(qin);
        setmuc.add(po);
        setmuc.add(guang);
        setmuc.add(eye);

        Menu help = new Menu("    帮助");
        help.add(about);

        MenuBar bar = new MenuBar();
        bar.add(choice);
        bar.add(setmuc);
        bar.add(help);
        setMenuBar(bar);

        renew.addActionListener(this);
        last.addActionListener(this);
        next.addActionListener(this);
        choose.addActionListener(this);
        exit.addActionListener(this);
        back.addActionListener(this);
        nor.addActionListener(this);
        qin.addActionListener(this);
        po.addActionListener(this);
        guang.addActionListener(this);
        eye.addActionListener(this);
        about.addActionListener(this);

        nor.setEnabled(false);

        lb = new JLabel("灰太狼古堡推箱子！", SwingConstants.CENTER);
        lb2 = new JLabel("更换音乐", SwingConstants.CENTER);
        add(lb);
        add(lb2);
        lb.setBounds(100, 20, 400, 20);
        lb.setForeground(Color.white);
        lb2.setBounds(625, 500, 55, 20);
        lb2.setForeground(Color.white);

        btnrenew = new JButton("重玩");
        btnback = new JButton("悔一步");
        btnlast = new JButton("上一关");
        btnnext = new JButton("下一关");
        btnchoose = new JButton("选关");
        btnfirst = new JButton("第１关");
        btnover = new JButton("最终关");
        btnmuc = new JButton("音乐关");

        add(btnrenew);
        add(btnlast);
        add(btnnext);
        add(btnchoose);
        add(btnfirst);
        add(btnover);
        add(btnmuc);
        add(btnback);

        btnrenew.setBounds(625, 100, 80, 30);
        btnback.setBounds(625, 150, 80, 30);
        btnfirst.setBounds(625, 200, 80, 30);
        btnlast.setBounds(625, 250, 80, 30);
        btnnext.setBounds(625, 300, 80, 30);
        btnover.setBounds(625, 350, 80, 30);
        btnchoose.setBounds(625, 400, 80, 30);
        btnmuc.setBounds(625, 450, 80, 30);

        btnrenew.addActionListener(this);
        btnback.addActionListener(this);
        btnfirst.addActionListener(this);
        btnlast.addActionListener(this);
        btnnext.addActionListener(this);
        btnover.addActionListener(this);
        btnchoose.addActionListener(this);
        btnmuc.addActionListener(this);

        jc.setBounds(625, 530, 80, 20);
        jc.addItem("默认");
        jc.addItem("琴萧合奏");
        jc.addItem("泡泡堂");
        jc.addItem("灌篮高手");
        jc.addItem("eyes on me");
        jc.addItemListener(this);
        cont.add(jc);

        sound = new Sound();
        sound.loadSound();
        panel = new mainpanel();
        add(panel);
        panel.Tuixiangzi(panel.level);
        panel.requestFocus();

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnrenew || e.getSource() == renew) {
            panel.Tuixiangzi(panel.level);
            panel.requestFocus();
            panel.clearStack();
        } else if (e.getSource() == btnlast || e.getSource() == last) {
            panel.level--;
            if (panel.level < 1) {
                panel.level++;
                JOptionPane.showMessageDialog(this, "本关是第一关");
            } else {
                panel.Tuixiangzi(panel.level);
            }
            panel.requestFocus();
            panel.clearStack();
        } else if (e.getSource() == btnnext || e.getSource() == next) {
            panel.level++;
            if (panel.level > panel.maxlevel()) {
                panel.level--;
                JOptionPane.showMessageDialog(this, "本关已是最后一关");
            } else {
                panel.Tuixiangzi(panel.level);
            }
            panel.requestFocus();
            panel.clearStack();
        } else if (e.getSource() == exit) {
            sound.mystop();
            System.exit(0);
        } else if (e.getSource() == about) {
            JOptionPane.showMessageDialog(this, "JAVA推箱子游戏！(version 3.0)");
        } else if (e.getSource() == btnchoose || e.getSource() == choose) {
            String lel = JOptionPane.showInputDialog(this, "请输入您要转到的关卡号：(1~50)");
            if (lel == null || lel.trim().isEmpty()) return;
            try {
                panel.level = Integer.parseInt(lel);
                if (panel.level > panel.maxlevel() || panel.level < 1) {
                    JOptionPane.showMessageDialog(this, "没有这一关！！！");
                } else {
                    panel.Tuixiangzi(panel.level);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "请输入有效数字！");
            }
            panel.requestFocus();
            panel.clearStack();
        } else if (e.getSource() == btnfirst) {
            panel.level = 1;
            panel.Tuixiangzi(panel.level);
            panel.requestFocus();
            panel.clearStack();
        } else if (e.getSource() == btnover) {
            panel.level = panel.maxlevel();
            panel.Tuixiangzi(panel.level);
            panel.requestFocus();
            panel.clearStack();
        } else if (e.getSource() == btnmuc) {
            if (sound.isplay()) {
                sound.mystop();
                btnmuc.setText("音乐开");
            } else {
                sound.loadSound();
                btnmuc.setText("音乐关");
            }
            panel.requestFocus();
        } else if (e.getSource() == btnback || e.getSource() == back) {
            if (panel.isMystackEmpty()) {
                JOptionPane.showMessageDialog(this, "您还未移动！！！");
            } else {
                int dir = panel.back();
                switch (dir) {
                    case 10: panel.backup(10); break;
                    case 11: panel.backup(11); break;
                    case 20: panel.backdown(20); break;
                    case 21: panel.backdown(21); break;
                    case 30: panel.backleft(30); break;
                    case 31: panel.backleft(31); break;
                    case 40: panel.backright(40); break;
                    case 41: panel.backright(41); break;
                }
            }
            panel.requestFocus();
        } else if (e.getSource() == nor) {
            jc.setSelectedIndex(0);
        } else if (e.getSource() == qin) {
            jc.setSelectedIndex(1);
        } else if (e.getSource() == guang) {
            jc.setSelectedIndex(3);
        } else if (e.getSource() == eye) {
            jc.setSelectedIndex(4);
        } else if (e.getSource() == po) {
            jc.setSelectedIndex(2);
        }
    }

    public void itemStateChanged(ItemEvent ie) {
        if (ie.getStateChange() != ItemEvent.SELECTED) return;
        int no = jc.getSelectedIndex();
        switch (no) {
            case 0: sound.setMusic("nor.mid"); break;
            case 1: sound.setMusic("qin.mid"); break;
            case 2: sound.setMusic("popo.mid"); break;
            case 3: sound.setMusic("guang.mid"); break;
            case 4: sound.setMusic("eyes on me.mid"); break;
        }
        if (sound.isplay()) sound.mystop();
        sound.loadSound();
        btnmuc.setText("音乐关");
        nor.setEnabled(no != 0);
        qin.setEnabled(no != 1);
        po.setEnabled(no != 2);
        guang.setEnabled(no != 3);
        eye.setEnabled(no != 4);
        panel.requestFocus();
    }
}

class mainpanel extends JPanel implements KeyListener {
    int max = 50;
    int[][] map, maptmp;
    int manX, manY, boxnum;
    Image[] myImage;
    Readmap Levelmap;
    Readmap Levelmaptmp;
    int len = 30;
    public int level = 1;
    Stack<Integer> mystack = new Stack<>();

    mainpanel() {
        setBounds(15, 50, 600, 600);
        setBackground(Color.white);
        setFocusable(true);
        addKeyListener(this);
        myImage = new Image[10];

        // 图片路径（你的真实路径）
        for (int i = 0; i < 10; i++) {
            myImage[i] = Toolkit.getDefaultToolkit().getImage("C:\\Users\\14225\\IdeaProjects\\PushBoxGame\\src\\pic\\" + i + ".gif");
        }
    }

    void Tuixiangzi(int i) {
        Levelmap = new Readmap(i);
        Levelmaptmp = new Readmap(i);
        map = Levelmap.getmap();
        manX = Levelmap.getmanX();
        manY = Levelmap.getmanY();
        maptmp = Levelmaptmp.getmap();
        clearStack();
        repaint();
    }

    int maxlevel() { return max; }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                g.drawImage(myImage[map[j][i]], i * len, j * len, this);
            }
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("楷体", Font.BOLD, 30));
        g.drawString("第", 240, 40);
        g.drawString(String.valueOf(level), 310, 40);
        g.drawString("层", 360, 40);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP: moveup(); break;
            case KeyEvent.VK_DOWN: movedown(); break;
            case KeyEvent.VK_LEFT: moveleft(); break;
            case KeyEvent.VK_RIGHT: moveright(); break;
        }
        repaint();
        if (iswin()) handleWin();
    }

    private void handleWin() {
        if (level == max) {
            JOptionPane.showMessageDialog(this, "恭喜您通过最后一关！！！");
        } else {
            int choice = JOptionPane.showConfirmDialog(null,
                    "恭喜您通过第"+level+"关！\n是否进入下一关？",
                    "过关", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                level++;
                Tuixiangzi(level);
            } else {
                System.exit(0);
            }
        }
        clearStack();
    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    boolean isMystackEmpty() { return mystack.isEmpty(); }
    int back() { return mystack.pop(); }
    void clearStack() { mystack.clear(); }

    void moveup() {
        if (manY-1 <0) return;
        if (map[manY-1][manX]==2 || map[manY-1][manX]==4) {
            map[manY][manX] = (maptmp[manY][manX]==4||maptmp[manY][manX]==9) ?4:2;
            map[manY-1][manX]=8; manY--; mystack.push(10);
        } else if (map[manY-1][manX]==3 || map[manY-1][manX]==9) {
            if (manY-2>=0 && (map[manY-2][manX]==4||map[manY-2][manX]==2)) {
                map[manY][manX] = (maptmp[manY][manX]==4||maptmp[manY][manX]==9) ?4:2;
                map[manY-1][manX]=8;
                map[manY-2][manX] = (map[manY-2][manX]==4)?9:3;
                manY--; mystack.push(11);
            }
        }
    }

    void backup(int t) {
        if (t==10) map[manY][manX] = (maptmp[manY][manX]==4||maptmp[manY][manX]==9)?4:2;
        if (t==11) {
            map[manY][manX] = (maptmp[manY][manX]==4||maptmp[manY][manX]==9)?9:3;
            map[manY-1][manX] = (maptmp[manY-1][manX]==4||maptmp[manY-1][manX]==9)?4:2;
        }
        map[manY+1][manX]=8; manY++; repaint();
    }

    void movedown() {
        if (manY+1>=20) return;
        if (map[manY+1][manX]==2||map[manY+1][manX]==4) {
            map[manY][manX] = (maptmp[manY][manX]==4||maptmp[manY][manX]==9)?4:2;
            map[manY+1][manX]=5; manY++; mystack.push(20);
        } else if (map[manY+1][manX]==3||map[manY+1][manX]==9) {
            if (manY+2<20 && (map[manY+2][manX]==4||map[manY+2][manX]==2)) {
                map[manY][manX] = (maptmp[manY][manX]==4||maptmp[manY][manX]==9)?4:2;
                map[manY+1][manX]=5;
                map[manY+2][manX] = (map[manY+2][manX]==4)?9:3;
                manY++; mystack.push(21);
            }
        }
    }

    void backdown(int t) {
        if (t==20) map[manY][manX] = (maptmp[manY][manX]==4||maptmp[manY][manX]==9)?4:2;
        if (t==21) {
            map[manY][manX] = (maptmp[manY][manX]==4||maptmp[manY][manX]==9)?9:3;
            map[manY+1][manX] = (maptmp[manY+1][manX]==4||maptmp[manY+1][manX]==9)?4:2;
        }
        map[manY-1][manX]=5; manY--; repaint();
    }

    void moveleft() {
        if (manX-1<0) return;
        if (map[manY][manX-1]==2||map[manY][manX-1]==4) {
            map[manY][manX] = (maptmp[manY][manX]==4||maptmp[manY][manX]==9)?4:2;
            map[manY][manX-1]=6; manX--; mystack.push(30);
        } else if (map[manY][manX-1]==3||map[manY][manX-1]==9) {
            if (manX-2>=0 && (map[manY][manX-2]==4||map[manY][manX-2]==2)) {
                map[manY][manX] = (maptmp[manY][manX]==4||maptmp[manY][manX]==9)?4:2;
                map[manY][manX-1]=6;
                map[manY][manX-2] = (map[manY][manX-2]==4)?9:3;
                manX--; mystack.push(31);
            }
        }
    }

    void backleft(int t) {
        if (t==30) map[manY][manX] = (maptmp[manY][manX]==4||maptmp[manY][manX]==9)?4:2;
        if (t==31) {
            map[manY][manX] = (maptmp[manY][manX]==4||maptmp[manY][manX]==9)?9:3;
            map[manY][manX-1] = (maptmp[manY][manX-1]==4||maptmp[manY][manX-1]==9)?4:2;
        }
        map[manY][manX+1]=6; manX++; repaint();
    }

    void moveright() {
        if (manX+1>=20) return;
        if (map[manY][manX+1]==2||map[manY][manX+1]==4) {
            map[manY][manX] = (maptmp[manY][manX]==4||maptmp[manY][manX]==9)?4:2;
            map[manY][manX+1]=7; manX++; mystack.push(40);
        } else if (map[manY][manX+1]==3||map[manY][manX+1]==9) {
            if (manX+2<20 && (map[manY][manX+2]==4||map[manY][manX+2]==2)) {
                map[manY][manX] = (maptmp[manY][manX]==4||maptmp[manY][manX]==9)?4:2;
                map[manY][manX+1]=7;
                map[manY][manX+2] = (map[manY][manX+2]==4)?9:3;
                manX++; mystack.push(41);
            }
        }
    }

    void backright(int t) {
        if (t==40) map[manY][manX] = (maptmp[manY][manX]==4||maptmp[manY][manX]==9)?4:2;
        if (t==41) {
            map[manY][manX] = (maptmp[manY][manX]==4||maptmp[manY][manX]==9)?9:3;
            map[manY][manX+1] = (maptmp[manY][manX+1]==4||maptmp[manY][manX+1]==9)?4:2;
        }
        map[manY][manX-1]=7; manX--; repaint();
    }

    boolean iswin() {
        for (int i=0;i<20;i++)
            for (int j=0;j<20;j++)
                if ((maptmp[i][j]==4||maptmp[i][j]==9) && map[i][j]!=9)
                    return false;
        return true;
    }
}

class Sound {
    // 音乐路径（你的真实路径）
    String path = "C:\\Users\\14225\\IdeaProjects\\PushBoxGame\\src\\music\\";
    String file = "nor.mid";
    Sequence seq;
    Sequencer midi;
    boolean sign;

    void loadSound() {
        try {
            if (midi != null && midi.isOpen()) midi.close();
            seq = MidiSystem.getSequence(new File(path + file));
            midi = MidiSystem.getSequencer();
            midi.open();
            midi.setSequence(seq);
            midi.start();
            midi.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            sign = true;
        } catch (Exception ex) {
            sign = false;
        }
    }

    void mystop() {
        if (midi != null && midi.isOpen()) {
            midi.stop();
            midi.close();
        }
        sign = false;
    }

    boolean isplay() { return sign; }
    void setMusic(String e) { file = e; }
}

class Readmap {
    private int level, mx, my;
    private int[][] mymap = new int[20][20];

    Readmap(int k) {
        level = k;
        try {
            // 地图路径（你的真实路径）
            String path = "C:\\Users\\14225\\IdeaProjects\\PushBoxGame\\src\\maps\\" + level + ".map";
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = br.readLine()) != null) sb.append(s.trim());
            br.close();

            String bb = sb.toString();
            int c = 0;
            for (int i=0;i<20;i++) {
                for (int j=0;j<20;j++) {
                    mymap[i][j] = bb.charAt(c)-'0';
                    if (mymap[i][j]==5) { mx=j; my=i; }
                    c++;
                }
            }
        } catch (Exception e) {
            for (int i=0;i<20;i++)
                for (int j=0;j<20;j++)
                    mymap[i][j] = (i==0||i==19||j==0||j==19) ? 1 : 2;
            mymap[10][10]=5; mx=10; my=10;
        }
    }

    int[][] getmap() { return mymap; }
    int getmanX() { return mx; }
    int getmanY() { return my; }
}