/*
 * DiceRolling.java
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DiceRoller extends JFrame
{
  //class-level declarations
  
  JMenuBar exampleMenuBar = new JMenuBar();
  JMenu fileMenu = new JMenu("File");
  JMenuItem quitMenuItem = new JMenuItem("Quit");
  
  JLabel die1 = new JLabel();
  JLabel die2 = new JLabel();
  JButton rollButton = new JButton();
  
  ImageIcon image0 = new ImageIcon("DICE1.gif");
  ImageIcon image1 = new ImageIcon("DICE2.gif");
  ImageIcon image2 = new ImageIcon("DICE3.gif");
  ImageIcon image3 = new ImageIcon("DICE4.gif");
  ImageIcon image4 = new ImageIcon("DICE5.gif");
  ImageIcon image5 = new ImageIcon("DICE6.gif");
  
  Timer diceTimer1;
  Timer diceTimer2;
  Timer flashTimer;
  
  
  int randomDelay1 =(int) (Math.random()*3000)+500;
  int randomDelay2 =(int) (Math.random()*3000)+500;

  public static void main(String args[])
  {
    // create frame
    new DiceRoller().setVisible(true);
  }

  public DiceRoller()
  {
    // frame constructor
    setTitle("Dice Rolling");
    setResizable(false);
    getContentPane().setBackground(Color.BLUE);
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent evt)
      {
        exitForm(evt);
      }
    });
    
    setJMenuBar(exampleMenuBar);
    
    quitMenuItem.setAccelerator(KeyStroke.getKeyStroke('Q'));
    quitMenuItem.setMnemonic('Q');
    exampleMenuBar.add(fileMenu);
    fileMenu.add(quitMenuItem);
    
    getContentPane().setLayout(new GridBagLayout());
    // position controls

    GridBagConstraints gridConstraints = new GridBagConstraints();
    
    //exitMenuItem
    quitMenuItem.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        quitMenuItemActionPerformed(e);
      }
    });
    
    die1.setPreferredSize(new Dimension(image0.getIconWidth(), image0.getIconHeight()));
    die1.setIcon(image0);
    gridConstraints.gridx = 0;
    gridConstraints.gridy = 0;
    gridConstraints.insets = new Insets(10, 10, 10, 10);
    getContentPane().add(die1, gridConstraints);

    
    die2.setPreferredSize(new Dimension(image0.getIconWidth(), image0.getIconHeight()));
    die2.setIcon(image0);
    gridConstraints.gridx = 1;
    gridConstraints.gridy = 0;
    gridConstraints.insets = new Insets(10, 10, 10, 10);
    getContentPane().add(die2, gridConstraints);

    
    rollButton.setText("Roll Dice");
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 0;
    gridConstraints.gridy = 1;
    gridConstraints.gridwidth=2;
    gridConstraints.insets = new Insets(5, 5, 5, 5);
    getContentPane().add(rollButton, gridConstraints);
    rollButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        rollButtonActionPerformed(e);
      }
    });
    

    
    flashTimer = new Timer(0, new ActionListener(){
        public void actionPerformed(ActionEvent e){
            flashTimerActionPerformed(e);
        }
    });
    
   //pack and center
    pack();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height - getHeight())), getWidth(), getHeight());
  }

  private void quitMenuItemActionPerformed(ActionEvent e)
  {
    if(JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit Program", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
    {
        System.exit(0);  
    }
  }
  
  private void rollButtonActionPerformed(ActionEvent e)
  {
    randomDelay1 = (int)(Math.random()*3000)+500;
    randomDelay2 = (int)(Math.random()*3000)+500;
    //timer
    diceTimer1 = new Timer(randomDelay1, new ActionListener(){
        public void actionPerformed(ActionEvent e){
            diceTimer1ActionPerformed(e);
        }
    });

    diceTimer2 = new Timer(randomDelay2, new ActionListener(){
        public void actionPerformed(ActionEvent e){
            diceTimer2ActionPerformed(e);
        }
    });
    diceTimer1.start();
    diceTimer2.start();
    flashTimer.start();
    rollButton.setEnabled(false);

  }
  private void flashTimerActionPerformed(ActionEvent e)
  {
    if(diceTimer1.isRunning())
    {
        die1.setIcon(randomDots());
    }
        if(diceTimer2.isRunning())
    {
        die2.setIcon(randomDots());
    }
  }
  
    private ImageIcon randomDots()
  {
      ImageIcon newImage = new ImageIcon("DICE1.gif");
      if(flashTimer.isRunning())
      {
          int random =(int) (Math.random()*6)+1;
          if(random == 1)
          {
            newImage = new ImageIcon("DICE1.gif");  
          }
          else if (random == 2)
          {
              newImage = new ImageIcon("DICE2.gif");  
          }
          else if (random == 3)
          {
              newImage = new ImageIcon("DICE3.gif");  
          }
          else if (random == 4)
          {
              newImage = new ImageIcon("DICE4.gif");  
          }
          else if (random == 5)
          {
              newImage = new ImageIcon("DICE5.gif");  
          }
          else{
              newImage = new ImageIcon("DICE6.gif");  
          }
      }
      return newImage;
  }
  
  private void diceTimer1ActionPerformed(ActionEvent e)
  {
      diceTimer1.stop(); 
      if(!diceTimer2.isRunning() && !diceTimer1.isRunning())
      {
          flashTimer.stop();
        rollButton.setEnabled(true);
      }
  }
  
  private void diceTimer2ActionPerformed(ActionEvent e)
  {
      diceTimer2.stop();
      if(!diceTimer1.isRunning() && !diceTimer2.isRunning())
      {
          flashTimer.stop();
          rollButton.setEnabled(true);
      }
  }
  

  private void exitForm(WindowEvent evt)
  {
    System.exit(0);
  }
}