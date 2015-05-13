package dialogs;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

public class progress {
 
 static Container pane;
 static JFrame frmMain;
 static JProgressBar barDo;
 
 public static JFrame  openProgressBar(){

   try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
   
         catch (Exception e) {}
        
   frmMain = new JFrame("Progress ");
   frmMain.setSize(390, 140); //Window size 390x140 pixels
   
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
          int a = frmMain.getSize().width;
          int b = frmMain.getSize().height;
          int c = (dim.width - a) / 2;
          int d = (dim.height - b) / 2;
          frmMain.setLocation(c, d);
         
         pane = frmMain.getContentPane();
         pane.setLayout(null); 
       //Exit when X is clicked
         frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
       // Max value: 100 Min value: 0
        barDo = new JProgressBar(0, 100); 
        pane.add(barDo);
       // postion  
        barDo.setBounds(20, 25, 275, 15);    
        
        frmMain.setResizable(false); 
        frmMain.setVisible(true);
         new Thread(new thread1()).start();
         return frmMain;
 
     }
  
     //The Thread 
      public static class thread1 implements Runnable{
          public void run(){
             for (int i=0; i<=80; i++){ 
                  
            	 barDo.setValue(i); 
                 barDo.repaint(); 
                 try{Thread.sleep(20);} 
 
                 catch (InterruptedException err){}
 
             }            
          }
     }
     
     public static void closeProgressBar(JFrame frmMain){
      
      frmMain.dispose();
     }
}
 