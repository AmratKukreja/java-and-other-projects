package Phone;

import Phone.Directory;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Delete implements ActionListener {

  String groupname;
  JLabel jLabel4;
  LinkedList<String> del, temp1;
  JFrame f1;
  JPanel p1, p2;
  JCheckBox arr[];
  JScrollPane jScrollBar1;
  JButton dele, close, search;
  int count = 0, temp = 0;

  public void GroupString(String name) {
    groupname = name;
  }

  public void DialogBox(LinkedList<String> del) {
    Directory d = new Directory();
    int res = JOptionPane.showConfirmDialog(
      f1,
      "Sure? You want to Delete?",
      "Swing Tester",
      JOptionPane.YES_NO_OPTION,
      JOptionPane.QUESTION_MESSAGE
    );
    if (res == JOptionPane.YES_OPTION) {
      d.delete(del);
      JOptionPane.showMessageDialog(null, "Contact was Deleted!");
      deleteDelete(4);
    }
  }

  public void deleteDelete(int count1) {
    Directory Obj = new Directory();
    Obj.readfile();
    jLabel4 = new JLabel();
    temp = count1;
    if (count1 == -1 || count1 == -5) {
      jLabel4.setText(" Add Members in Group");
      dele = new JButton("ADD");
      f1 = new JFrame("Groups Section");
      search = new JButton("Add by Search");
    } else {
      search = new JButton("Search");
      jLabel4.setText(" Delete Contacts");
      dele = new JButton("Delete");
      f1 = new JFrame("Delete Section");
    }

    jLabel4.setBorder(
      new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true)
    );
    close = new JButton();
    close.setBackground(new java.awt.Color(204, 255, 204));
    close.setText("Close");

    p2 = new JPanel(new GridLayout(0, 1));
    p1 = new JPanel(new GridLayout(Obj.list.size(), 1));
   LinkedList<Integer> inti = new LinkedList<>();
   LinkedList<String> tem = new LinkedList<>();  
   tem =  Obj.list;
   try{
    if (temp == -5) {
      temp1 = Obj.displayGroupmember(groupname);
      arr = new JCheckBox[(Obj.list.size()) - temp1.size()];
      int n=0;
      for(int i=0;i<temp1.size();i++){
        for(int k=0;k<Obj.list.size();k++){
          if(temp1.get(i).equals(Obj.list.get(k))){
            inti.add(k);
            break;
          }
        }
      }
        for(int i=0;i<inti.size();i++){
          n = inti.get(0);
       tem.remove(n);
        }
 
    }
      arr = new JCheckBox[tem.size()];
      for (int i = 0; i < arr.length; i++) {
        arr[i] = new JCheckBox(tem.get(i));
       p1.add(arr[i]);
      }
    } 
  catch(Exception e ){
    System.out.println(e);
}
    p2.add(dele);
    p2.add(close);
    p2.add(search);
    dele.setSize(150, 150);
    Container c = f1.getContentPane();
    f1.setLayout(new BorderLayout());
    c.add(jLabel4, BorderLayout.NORTH);
    c.add(p2, BorderLayout.WEST);
    c.add(BorderLayout.CENTER, new JScrollPane(p1));

    dele.addActionListener(this);
    search.addActionListener(this);
    close.addActionListener(this);

    f1.setDefaultCloseOperation(f1.EXIT_ON_CLOSE);
    f1.setSize(400, 250);
    f1.setVisible(true);
    f1.setResizable(false);
  }

  public void actionPerformed(ActionEvent event) {
    del = new LinkedList<>();

    if (event.getSource() == dele) {
      for (int i = 0; i < arr.length; i++) {
        if (arr[i].isSelected()) {
          del.add(arr[i].getText());
          count++;
        }
      }
      if (count == 0) {
        JOptionPane.showMessageDialog(null, "Not Selected any option!");
      } else if (temp == -5) {
        Directory d = new Directory();
        d.addmembers(groupname, del);
        JOptionPane.showMessageDialog(null, "Member are added!");
        f1.setVisible(false);

      } 
        else if (temp == -1) {
        Directory d = new Directory();
        d.addmembers(groupname, del);
        JOptionPane.showMessageDialog(null, "Member are added!");
        f1.setVisible(false);
      } else {
        f1.setVisible(false);
        DialogBox(del);
      }
    }

    Search s = new Search();
    if (event.getSource() == search) {
      s.myFunc(s);
      s.myFunc(-2);
      s.sframe.setVisible(true);
      f1.setVisible(false);
    }
    if (event.getSource() == close) {
      f1.setVisible(false);
    }
  }

  public static void main(String args[]) {
    //    Directory Obj = new Directory();
    //  Obj.Menu();
    Delete obj = new Delete();
    obj.deleteDelete(2);
  }
}
