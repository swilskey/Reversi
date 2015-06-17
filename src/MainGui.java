import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by source41 on 4/28/2015.
 */
public class MainGui extends JFrame implements ActionListener {

   private JButton easyButton, hardButton, twoPlayer, resetButton;
   private JPanel topPanel, scorePanel, statusPanel, centerPanel;
   private int wScore;
   private int bScore;
   private File img;
   private Reversi r;
   private Board b;


   public JTextArea wText = new JTextArea();
   public JTextArea bText = new JTextArea();
   public JTextArea status = new JTextArea();

   private final int WIDTH = 1200;
   private final int HEIGHT = 800;

   public MainGui(Board b) {
      super("Reversi");
      this.b = b;
      try {
         initGui();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void initGui() throws IOException {
      topPanel = new JPanel();
      topPanel.setLayout(new FlowLayout());

      final File backgroundFile = new File("images/background.png");
      add(centerPanel = new JPanel() {
         BufferedImage background = ImageIO.read(backgroundFile);


         public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(background, 180, 0, getWidth()-360, getHeight(), null);
         }
      }, BorderLayout.CENTER);

      topPanel.add(easyButton = new JButton("Easy CPU"));
      topPanel.add(hardButton = new JButton("Hard CPU"));
      topPanel.add(twoPlayer = new JButton("P v P"));
      easyButton.addActionListener(this);
      hardButton.addActionListener(this);
      twoPlayer.addActionListener(this);
      add(topPanel, BorderLayout.NORTH);


      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(WIDTH, HEIGHT);
      setVisible(true);
   }

   public void resetBoard() {
      remove(scorePanel);
      remove(r);
      remove(statusPanel);
      status.setText("BLACK'S Move");
      setVisible(false);
      try {
         initGui();
      } catch(IOException e) {
         e.printStackTrace();
      }

   }
   
   public void initBoard() {
      remove(topPanel);
      remove(centerPanel);
      resetButton = new JButton("Reset");
      resetButton.addActionListener(this);
      add(r = new Reversi(b), BorderLayout.CENTER);

      scorePanel = new JPanel();
      scorePanel.add(bText);
      scorePanel.add(resetButton);
      scorePanel.add(wText);

      statusPanel = new JPanel();
      statusPanel.add(status);
      status.setText("BLACK'S Move");
      status.setEditable(false);
      //status.setFont(new Font("Cracked", Font.PLAIN, 16));

      wText.setText("White: " + wScore);
      bText.setText("Black: " + bScore);

      //wText.setFont(new Font("Cracked", Font.PLAIN, 16));
      //bText.setFont(new Font("Cracked", Font.PLAIN, 16));

      wText.setEditable(false);
      bText.setEditable(false);

      add(scorePanel, BorderLayout.NORTH);
      add(statusPanel, BorderLayout.SOUTH);
      pack();
      setLocationRelativeTo(null);
   
   }

   public void manualRepaint() {
      r.repaint();
   }

   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == easyButton) {
         GameController.getInstance().setPlayer1(new HumanPlayer(Cell.BLACK));
         GameController.getInstance().setPlayer2(new EasyComputerPlayer(Cell.WHITE));
         initBoard();      
      } 
      else if (e.getSource() == hardButton) {
         GameController.getInstance().setPlayer1(new HumanPlayer(Cell.BLACK));
         GameController.getInstance().setPlayer2(new HardComputerPlayer(Cell.WHITE));
         initBoard();
      
      } 
      else if (e.getSource() == twoPlayer) {
         GameController.getInstance().setPlayer1(new HumanPlayer(Cell.BLACK));
         GameController.getInstance().setPlayer2(new HumanPlayer(Cell.WHITE));
         initBoard();
      }
      else if(e.getSource() == resetButton) {
         GameController.getInstance().reset();
      }
   
   }
   
   public void setWScore(int wScore) {
      this.wScore = wScore;
   }
   
   public void setBScore(int bScore) {
      this.bScore = bScore;
   }

   /*public static void main(String[] args) {
       MainGui m = new MainGui();
   }*/
}
