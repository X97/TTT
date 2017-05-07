// Namir Hassan

// 5/1/13

// Game.java

// This program is a game called "Tic-Tac-Toe".



import java.awt.BorderLayout;

import java.awt.CardLayout;

import java.awt.Color;

import java.awt.Container;

import java.awt.FlowLayout;

import java.awt.Font;

import java.awt.Graphics;

import java.awt.GridLayout;

import java.awt.Image;

import java.awt.Label;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.io.File;

import java.io.IOException;



import javax.imageio.ImageIO;

import javax.swing.ButtonGroup;

import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JRadioButton;

import javax.swing.JScrollBar;



public class Game {

    private int[][] winCombinations = new int[][] { { 0, 1, 2 }, { 3, 4, 5

},

            { 6, 7, 8 }, // horizontal wins

            { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, // Vertical wins

            { 0, 4, 8 }, { 2, 4, 6 } // diagonal wins

    };

    private JFrame frame;

    private JPanel gamePanel; // This JPanel will contain the cardLayout

    private JButton buttons[] = new JButton[9];

    JButton jump;

    private Container c;

    private DrawPanel0 panel0;

    private DrawPanel1 panel1;

    private DrawPanel2 panel2;

    private DrawPanel3 panel3;

    private DrawPanel4 panel4;

    private DrawPanel5 panel5;

    private StatusPanel statusPanel;

    private Label lx, ly;

    private Font font;

    private int fontSize;

    private String imageName1;

    private Image image1, imageX, imageO, imageS, menuImage;

    static String x = "X";

    static String y = "O";

    private int count = 0, imagepx, imagepy;

    private int[] xs = new int[9];

    private int[] ys = new int[9];

    private int[] xo = new int[9];

    private int questionCount = 0;

    private Label quesiton, ansA, ansB, ansC;

    private String[] questionsAnswers= {
    		// QUestion, Answer a, Answere b, Answer c, Correct Answer
    		"Where is DNA located?", "Nucleus", "Newtron", "Proton", "a",
    		"What was Bob Marleys Record Label?", "Island Record", "UKF Music", "Ultra Records", "b",
    		"What is the current month?", "Jan", "Apr", "May", "c",
    		"What year is it?", "2014", "2013", "2012", "b"
    		};

    private String letter = "";

    private String statusStr = "Status: ";

    private int scorex, scorey;

    private boolean correctAnswer = false;

    private String correctQ;

    private boolean win = false;

    private String sx = "Score of X: ";

    private String sy = "Score of O: ";



    public Game() {

        frame = new JFrame("Tic-Tac-Toe");

        frame.setSize(500, 500);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        fontSize = 10;

        font = new Font("Arial", Font.PLAIN, fontSize);

        imageName1 = "mm.png";

        try {

            image1 = ImageIO.read(new File(imageName1));

            imageX = ImageIO.read(new File("x.png"));

            imageO = ImageIO.read(new File("o.png"));

            menuImage = ImageIO.read(new File("TICTACTOE.jpg"));



        } catch (IOException e) {

            e.printStackTrace();

        }



        // Let's divide the JFrame - the Container into three parts - Status

        // panel, gamePanel and

        // bottomPanel/commandPanel



        frame.setLayout(new BorderLayout());

        statusPanel = new StatusPanel();

        gamePanel = new GamePanel();

        frame.add(statusPanel, BorderLayout.PAGE_START);

        frame.add(gamePanel, BorderLayout.CENTER);

        frame.add(new BottomPanel(), BorderLayout.PAGE_END);



        // Add different Panels to the gamePanel's cardLayout

        panel0 = new DrawPanel0();

        panel0.setBackground(Color.black);

        gamePanel.add(panel0, "Panel 0");



        panel1 = new DrawPanel1();

        panel1.setBackground(Color.black);

        gamePanel.add(panel1, "Panel 1");



        panel2 = new DrawPanel2();

        gamePanel.add(panel2, "Panel 2");



        panel3 = new DrawPanel3();

        panel3.setBackground(Color.BLACK);

        gamePanel.add(panel3, "Panel 3");



        panel4 = new DrawPanel4();

        panel4.setBackground(Color.WHITE);

        gamePanel.add(panel4, "Panel 4");



        panel5 = new DrawPanel5();

        panel5.setBackground(Color.LIGHT_GRAY);

        gamePanel.add(panel5, "Panel 5");



        // Make it visible

        frame.setVisible(true);

    }



    public static void main(String[] args) {

        Game game = new Game();

        game.run();

    }



    public void run() {



    }



    class GamePanel extends JPanel implements ActionListener {

        public GamePanel() {

            super(new CardLayout());

        }



        @Override

        public void actionPerformed(ActionEvent e) {



        }

    }



    class StatusPanel extends JPanel {

        JLabel label = new JLabel();



        public StatusPanel() {

            setBackground(Color.GRAY);

            label.setText(statusStr);

            add(label);

        }



        public void paintComponent(Graphics g) {

            super.paintComponent(g);

            g.setColor(Color.RED);

        }

    }



    class DrawPanel0 extends JPanel {



        public DrawPanel0() {

            setBackground(Color.BLUE);

        }



        public void paintComponent(Graphics g) {

            super.paintComponent(g);

            g.drawImage(menuImage, 0, 0, 490, 400, this);

                        g.setColor(Color.black);

                        g.fillOval(30, 50, 150, 160);

            g.setColor(Color.green);

                        g.drawString("Main Menu", 75, 135);



        }

    }



    class DrawPanel1 extends JPanel {



        public DrawPanel1() {

            setBackground(Color.BLUE);

        }



        public void paintComponent(Graphics g) {

            super.paintComponent(g);

            g.drawImage(image1, 150, 25, 200, 200, this);

                        g.setColor(Color.green);

            g.drawString("Controls: Click on any square. X will always go first. O always will go second.", 10, 290);

            g.drawString("Objective: Place the letters vertically, horizontally, or diagonally in the same order.", 10, 305);

            g.drawString("Rules: The winner must answer the question to continue. ", 10, 320);

            g.drawString("*Points will be earned when a question is answered correctly. ", 70, 350);

            g.drawString("*All points will be lost if a question is answered incorrectly. ", 70, 365);

        }

    }



    class DrawPanel2 extends JPanel implements ActionListener {



        private JScrollBar rectSize;

        private int size;



        public DrawPanel2() {

            setLayout(new GridLayout(3, 3));

            // Add Buttons To The Window

            for (int i = 0; i <= 8; i++) {

                buttons[i] = new JButton();

                add(buttons[i]);

                buttons[i].addActionListener(this);

            }

        }



        public void paintComponent(Graphics g) {

            super.paintComponent(g);

            g.setColor(Color.RED);

            for (int i = 0; i <= 8; i++) {

                if (xo[i] == 1)

                    g.drawImage(imageO, xs[i] + 65, ys[i] + 45, this);

                else if (xo[i] == 2)

                    g.drawImage(imageX, xs[i] + 65, ys[i] + 45, this);



            }

            // g.setFont(font);

            // g.drawString("NITEX", 110, 300);

        }



        // public void adjustmentValueChanged(AdjustmentEvent e) {

        // size = rectSize.getValue();

        // panel2.repaint();

        // }



        @Override

        public void actionPerformed(ActionEvent a) {

            Object source = a.getSource();

            count++;



            /* Calculate whose turn it is */

            if (count % 2 == 0) {

                letter = "O";

                // imageS = imageO;

                xo[count - 1] = 1;

            } else {

                letter = "X";

                // imageS = imageX;

                xo[count - 1] = 2;

            }



            /* Write the letter to the button and deactivate it */

            JButton pressedButton = (JButton) source;

            xs[count - 1] = pressedButton.getX();

            ys[count - 1] = pressedButton.getY();

            pressedButton.setText(letter);

            // pressedButton.setForeground(Color.GREEN);

            // pressedButton.setBackground(Color.RED);

            // pressedButton.setIcon(new ImageIcon(imageS));

            pressedButton.setEnabled(false);



            this.repaint();

            /* Determine who won */

            for (int i = 0; i <= 7; i++) {

                if (buttons[winCombinations[i][0]].getText().equals(

                        buttons[winCombinations[i][1]].getText())

                        && buttons[winCombinations[i][1]].getText().equals(

                                buttons[winCombinations[i][2]].getText())

                        && buttons[winCombinations[i][0]].getText() != "") {

                    win = true;

                }

            }



            /* Show a dialog when game is over */
            String statusStr1;
            if (win == true) {

                if (letter.equals("X")) {

                    letter = x;

                    scorex = scorex + 25;

                    lx.setText(sx + scorex + " (Level: " + scorex /25 + ")");

                    statusStr1 = statusStr + letter + " wins! " + "(Level: " + scorex / 25 + ")";

                } else {

                    letter = y;

                    scorey = scorey + 25;

                    ly.setText(sy + scorey + " (Level: " + scorey /25 + ")");
                    statusStr1 = statusStr + letter + " wins! " + "(Level: " + scorey / 25 + ")";
                }

                //String statusStr1 = statusStr + letter + " wins! ";

                statusPanel.label.setText(statusStr1);

                jump.setText("Question");

            } else if (count == 9 && win == false) {

                String statusStr2 = statusStr + "The game was tie!";

                statusPanel.label.setText(statusStr2);

                jump.setText("Reset");

            }

            if (win == true || count == 9) {

                for (int i = 0; i <= 8; i++) {

                    buttons[i].setEnabled(false);

                }

            }



        }



        public void clearIt() {



            // = window.setVisible(false);

            // this.window = null;

            // = this.startIt();

        }

    }



    class DrawPanel3 extends JPanel {

        private CenterPanel center;



        public DrawPanel3() {

            this.setLayout(new BorderLayout());

            center = new CenterPanel();

            this.add(center, BorderLayout.CENTER);

        }



        class CenterPanel extends JPanel {

            private InnerPanel inner1;



            public CenterPanel() {

                this.setLayout(null); // No Layout, so need to manage each

                                        // component in this Panel

                setBackground(Color.black);



                inner1 = new InnerPanel();

                inner1.setLocation(100, 100);

                inner1.setSize(200, 200);

                inner1.setBackground(Color.BLACK);



                this.add(inner1);

            }



        }



        class InnerPanel extends JPanel implements ActionListener {

            private JRadioButton hit, kick, exit;



            public InnerPanel() {



                this.setLayout(new GridLayout(2, 1));



                lx = new Label(sx + scorex);

                lx.setBackground(Color.black);

                this.add(lx);

                ly = new Label(sy + scorey);

                ly.setBackground(Color.black);

                this.add(ly);

            }



            public void actionPerformed(ActionEvent e) {

                String command = e.getActionCommand();



                if (command.equals("1")) {

                    System.out.println(command);

                }

            }

        }



    }



    class DrawPanel4 extends JPanel implements ActionListener {

        private JRadioButton a, b, c;

        private ButtonGroup fight;



        public DrawPanel4() {
        	JPanel jp1, jp2, jp3;
        	 jp1 = new JPanel();
        	 jp2 = new JPanel();
        	 jp3 = new JPanel();


        	this.setLayout(new GridLayout(4, 1));
            quesiton = new Label(questionsAnswers[0]);

            this.add(quesiton);





            correctQ = questionsAnswers[4]; // this keeps the correct answer

            fight = new ButtonGroup();

            a = new JRadioButton("a");
            a.setHorizontalTextPosition(a.LEADING);
            fight.add(a);
            a.addActionListener(this);
            jp1.add(a);
            ansA = new Label(questionsAnswers[1]);
            jp1.add(ansA);
            this.add(jp1);



            // this.add(fla.);

            b = new JRadioButton("b");

            b.setHorizontalTextPosition(b.LEADING);

            fight.add(b);

            b.addActionListener(this);

            jp2.add(b);
            ansB = new Label(questionsAnswers[2]);
            jp2.add(ansB);
            this.add(jp2);



            c = new JRadioButton("c");

            c.setHorizontalTextPosition(c.LEADING);

            fight.add(c);

            c.addActionListener(this);

            jp3.add(c);
            ansC = new Label(questionsAnswers[3]);
            jp3.add(ansC);
            this.add(jp3);

        }



        public void actionPerformed(ActionEvent evt) {

            String command = evt.getActionCommand();



            if (command.equals(correctQ)) {

                correctAnswer = true;

                jump.setText("Continue");

            } else {

                jump.setText("Continue");

            }

        }

    }



    class DrawPanel5 extends JPanel {



        public DrawPanel5() {



        }



        public void paintComponent(Graphics g) {

            super.paintComponent(g);

            setBackground(Color.black);

            g.setColor(Color.green);

            g.drawString("Game created by Namir Hassan", 70, 190);



        }

    }



    class BottomPanel extends JPanel implements ActionListener {

        JButton move, menu, credit;

        CardLayout c;



        public BottomPanel() {



            // Play Button

            setBackground(Color.black);

            menu = new JButton("Main Menu");

            menu.addActionListener(this);

            this.add(menu);



            // Instruction Button

            move = new JButton("Instructions");

            move.addActionListener(this);

            this.add(move);



            // Play Button

            setBackground(Color.black);

            jump = new JButton("Play");

            jump.addActionListener(this);

            this.add(jump);



            // Control Button

            move = new JButton("Score");

            move.addActionListener(this);

            this.add(move);



            // Credit

            setBackground(Color.black);

            credit = new JButton("Credit");

            credit.addActionListener(this);

            this.add(credit);

        }



        private void reset() {

            statusPanel.label.setText("Status: ");

            System.out.println("Count: " + count + " win: " + win);

            count = 0;

            jump.setText("Play");

            panel2 = new DrawPanel2();

            gamePanel.add(panel2, "Panel 2");

            c.show(gamePanel, "Panel 2");

            win = false;

            correctAnswer = false;



            // reset image and x, y tract

            for (int i = 0; i <= 8; i++) {

                xs[i] = 0;

                ys[i] = 0;

                xo[i] = 0;

            }

        }



        public void actionPerformed(ActionEvent evt) {

            String command = evt.getActionCommand();

            c = (CardLayout) gamePanel.getLayout();

            if (command.equals("Instructions")) {

                c.show(gamePanel, "Panel 1");

            } else if (command.equals("Play")) {

                c.show(gamePanel, "Panel 2");

            } else if (command.equals("Score")) {

                c.show(gamePanel, "Panel 3");

            } else if (command.equals("Main Menu")) {

                c.show(gamePanel, "Panel 0");

            } else if (command.equals("Question")) {
System.out.println(questionCount);
            	//Let set the question based on what question we are in, question1, 2, or 3
            	quesiton.setText(questionsAnswers[0 + 5 * questionCount]);
            	ansA.setText(questionsAnswers[1 + 5 * questionCount]);
            	ansB.setText(questionsAnswers[2 + 5 * questionCount]);
            	ansC.setText(questionsAnswers[3 + 5 * questionCount]);
            	correctQ = questionsAnswers[4 + 5 * questionCount];

                c.show(gamePanel, "Panel 4");

                if(questionCount >= 3) questionCount = 0;
                else questionCount++;

            } else if (command.equals("Credit")) {

                c.show(gamePanel, "Panel 5");

            } else if (command.equals("Continue")) {



                if (correctAnswer) {



                    if (letter.equals("X"))

                        scorex = scorex + 10;

                    else

                        scorey = scorey + 10;

                    lx.setText(sx + scorex + " (Level: " + scorex /25 + ")");

                    ly.setText(sy + scorey + " (Level: " + scorey /25 + ")");

                    c.show(gamePanel, "Panel 2");

                } else {
                	if (letter.equals("X"))

                        scorex = scorex - 10;

                    else

                        scorey = scorey - 10;

                    lx.setText(sx + scorex + " (Level: " + scorex /25 + ")");

                    ly.setText(sy + scorey + " (Level: " + scorey /25 + ")");

                    c.show(gamePanel, "Panel 3");

                }

                reset();

            } else if (command.equals("Reset")) {

                scorex = 0;

                scorey = 0;

                reset();

            }

        }

    }



}
