package Phone;

import Phone.Main;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.text.html.ObjectView;

public class DisplayGroupMemb implements ActionListener{

  JFrame frame;
  JPanel p;
  JLabel l1;
  JLabel label[];
  JTable jTable;
  JScrollPane jScrollPane2;
  JButton clos;

  public void members(LinkedList<String> mem, String str) {
    Main m = new Main();
    Object arr[][] = new Object[1][1];
    arr = m.setlogic(mem);
    Object arr1[] = { "NO", "First name", "Last name", "PNO" };
    clos = new JButton();
    label = new JLabel[mem.size()];
    frame = new JFrame("Group Members");
    p = new JPanel(new GridLayout(mem.size(), 1));
    l1 = new JLabel();
    l1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
    l1.setText("                                   Group Name : " + str);
    l1.setBorder(
      javax.swing.BorderFactory.createMatteBorder(
        2,
        2,
        2,
        2,
        new java.awt.Color(0, 0, 0)
      )
    );
    p.setForeground(Color.GREEN);
    p.setBackground(Color.GRAY);
    clos.setText("Close");
    clos.setBackground(Color.BLACK);
    clos.setForeground(Color.BLACK);
    jScrollPane2 = new JScrollPane();
    jTable = new JTable(arr, arr1);
    jTable.setForeground(new java.awt.Color(0, 255, 255));
    jTable.setBackground(new java.awt.Color(51, 0, 51));
    jScrollPane2.setViewportView(jTable);

    p.add(jTable);
    clos.addActionListener(this);

    Container c = frame.getContentPane();
    frame.setLayout(new BorderLayout());
    //c.add(jLabel4, BorderLayout.NORTH);
    //c.add(p2, BorderLayout.WEST);
    c.add(l1, BorderLayout.NORTH);
    c.add(BorderLayout.CENTER, new JScrollPane(p));
    c.add(clos,BorderLayout.SOUTH);
    frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    frame.setSize(400, 250);
    frame.setVisible(true);
    //    frame.setResizable(false);
  }
  public  void actionPerformed(ActionEvent event){
    if(event.getSource() == clos){
      frame.setVisible(false);
    } 
  }
}
