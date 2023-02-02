package Phone;

import Phone.Directory;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class DisplayGroups implements ActionListener {

  JFrame f1;
  JPanel p1,p2;
  JRadioButton arr[];
  JLabel l1;
  JButton b1,b2,b3;
  ButtonGroup bGroup;
  LinkedList<String> temp;
  int count;

  public void displaygroup(int count) {
      this.count = count;
    Directory d = new Directory();
    f1 = new JFrame();
    Container c = f1.getContentPane();
    f1.setLayout(new BorderLayout());
    l1 = new JLabel();
    b1 = new JButton();
    b2 = new JButton();
    b3 = new JButton();
    bGroup = new ButtonGroup();
    p2 = new JPanel(new GridLayout(0,1));
    if (count == -4) {
      d.readfile();
      temp = d.list;
      p1 = new JPanel(new GridLayout(d.list.size(), 1));
      arr = new JRadioButton[d.list.size()];
      l1.setText("                                   EDIT CONTACTS");
      b1.setText("Edit");
      b2.setText("Edit-Search");
      b3.setText("Close");
    } else {
      temp = new LinkedList<>();
      temp = d.Displaygroups();

      p1 = new JPanel(new GridLayout(temp.size(), 1));

      arr = new JRadioButton[temp.size()];
      l1.setText("                                   Open Groups");
      b1.setText("Open");
      b2.setText("Add Members");
      b3.setText("Close");
    }
    for (int i = 0; i < arr.length; i++) {
      arr[i] = new JRadioButton(temp.get(i));
      p1.add(arr[i]);
      bGroup.add(arr[i]);
    }
    l1.setFont(new java.awt.Font("Segoe UI Black", 1, 14));
    l1.setBorder(
      javax.swing.BorderFactory.createMatteBorder(
        2,
        2,
        2,
        2,
        new java.awt.Color(0, 0, 0)
      )
    );
    b1.setBackground(new java.awt.Color(0, 102, 153));
    b1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    b1.setForeground(new java.awt.Color(255, 255, 255));
    b1.addActionListener(this);
    b2.setBackground(new java.awt.Color(0, 102, 153));
    b2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    b2.setForeground(new java.awt.Color(255, 255, 255));
    b2.addActionListener(this);
    b3.setBackground(new java.awt.Color(0, 102, 153));
    b3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    b3.setForeground(new java.awt.Color(255, 255, 255));
    b3.addActionListener(this);
    p2.add(b1);
    p2.add(b2);
    p2.add(b3);
    c.add(p2, BorderLayout.WEST);
    c.add(l1, BorderLayout.NORTH);
    c.add(BorderLayout.CENTER, new JScrollPane(p1));
    f1.setDefaultCloseOperation(f1.EXIT_ON_CLOSE);
    f1.setSize(400, 250);
    f1.setVisible(true);
    f1.setResizable(false);
  }

  public void actionPerformed(ActionEvent event) {
    int n = 0;
    DisplayGroupMemb obj = new DisplayGroupMemb();
    Directory d = new Directory();
    LinkedList<String> memb = new LinkedList<>();
    String str = "";
    for (int i = 0; i < arr.length; i++) {
      if (arr[i].isSelected()) {
        str = temp.get(i) + "";
        n++;
      }
    }
    if (event.getSource() == b1) {
      if(n==0){
        JOptionPane.showMessageDialog(null,"Not Selected!");
      }
     else if(count == -4){
      Edit e = new Edit();
      e.initComponents(str,f1);        
      }
      else{
      memb = d.displayGroupmember(str);
      obj.members(memb, str);
      }
    }
    if(event.getSource() == b3){
      f1.setVisible(false);
    }

    if(event.getSource() == b2){
     Delete de = new Delete(); 
     de.GroupString(str);
    de.deleteDelete(-5);
    }


  }

  public static void main(String args[]) {
    DisplayGroups obj = new DisplayGroups();
    obj.displaygroup(12);
  }
}
