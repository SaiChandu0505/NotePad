import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.plaf.*;
import java.awt.print.PrinterException;
import java.io.*;

import static javax.swing.JOptionPane.showMessageDialog;

class Notepad extends JFrame implements ActionListener {

    JTextArea t ;

    JFrame f;

    JScrollPane sp ;
     Notepad() {
         f = new JFrame("Notepad");
         t = new JTextArea(4,20);

         sp = new JScrollPane(t);

         JMenuBar menu = new JMenuBar();

         JMenu file = new JMenu("file");
         JMenuItem f1 = new JMenuItem("new");
         JMenuItem f2 = new JMenuItem("open");
         JMenuItem f3 = new JMenuItem("save");
         JMenuItem f4 = new JMenuItem("print");
         f1.addActionListener(this);
         f2.addActionListener(this);
         f3.addActionListener(this);
         f4.addActionListener(this);

         file.add(f1);
         file.add(f2);
         file.add(f3);
         file.add(f4);

         JMenu edit = new JMenu("edit");
         JMenuItem f5 = new JMenuItem("cut");
         JMenuItem f6 = new JMenuItem("copy");
         JMenuItem f7 = new JMenuItem("paste");

         f5.addActionListener(this);
         f6.addActionListener(this);
         f7.addActionListener(this);


         edit.add(f5);
         edit.add(f6);
         edit.add(f7);

         JMenuItem close = new JMenuItem("close");


         menu.add(file);
         menu.add(edit);
         menu.add(close);


         f.getContentPane().add(sp);
          f.setJMenuBar(menu);
          //f.add(t);
          f.setSize(720,500);
          f.setResizable(true);
          f.show();




     }
        public void actionPerformed(ActionEvent e){
         String s = e.getActionCommand() ;
         switch (s){
             case "new":
                 t.setText("");
                 break;
             case "open":
                 JFileChooser j = new JFileChooser("E:");

                 int r = j.showOpenDialog(null);

                 if(r == JFileChooser.APPROVE_OPTION){

                     File fi = new File(j.getSelectedFile().getAbsolutePath());

                     String s1,s2;

                     FileReader fr = null;
                     try {
                         fr = new FileReader(fi);

                         BufferedReader br = new BufferedReader(fr);

                         s1 = br.readLine();

                         while((s2 = br.readLine())!=null){
                             s1 = s1 + "\n"  + s2 ;
                         }

                         t.setText(s1);

                     } catch (FileNotFoundException ex) {
                         throw new RuntimeException(ex);
                     }
                     catch (IOException ex) {
                         throw new RuntimeException(ex);
                     }


                 }
                 else{
                     JOptionPane.showMessageDialog(f,"operation cancelled");
                 }
                 break;
             case "save":
                 JFileChooser ji = new JFileChooser("E:");

                 int ri = ji.showOpenDialog(null);

                 if(ri == JFileChooser.APPROVE_OPTION){

                     File fi = new File(ji.getSelectedFile().getAbsolutePath());



                     FileWriter fr = null;
                     try {
                         fr = new FileWriter(fi);

                         BufferedWriter br = new BufferedWriter(fr);



                         br.write(t.getText());
                         br.flush();
                         br.close();

                     } catch (FileNotFoundException ex) {
                         throw new RuntimeException(ex);
                     }
                     catch (IOException ex) {
                         throw new RuntimeException(ex);
                     }


                 }
                 else{
                     JOptionPane.showMessageDialog(f,"operation cancelled");
                 }
                 break ;
             case "print":
                 try {
                     t.print();
                 }catch (PrinterException ex){
                     throw new RuntimeException(ex);
                 }
                 break;
             case "cut":
                 t.cut();
                 break;
             case "copy":
                 t.copy();
                 break;
             case "paste":
                 t.paste();
                 break ;
             case "close":
                 f.setVisible(false);
                 break;
            }

}
}


public class Main {
    public static void main(String args[]) {
        Notepad note = new Notepad();
    }
}