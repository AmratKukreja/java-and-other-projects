package Phone;

import java.io.*;
import java.util.*;
import javax.swing.*;
public class Directory {

  public LinkedList<String> list = new LinkedList<>();
  LinkedList<String> list1 = new LinkedList<>();
  LinkedList<String> groups = new LinkedList<>();
  LinkedList<String> memb = new LinkedList<>();
  int k = 0;
  Scanner sc = new Scanner(System.in);

  public void displayMenu() {
    System.out.println("-----------Phone Directory------------");
    System.out.println("1-Insert");
    System.out.println("2-Delete");
    System.out.println("3-Show Contacts");
    System.out.println("4-Update");
    System.out.println("5-SearchNumber");
    System.out.println("6-Create Groups");
    System.out.println("7-Display Groups");
    System.out.println("8-Exit");
  }
  public LinkedList<String> displayGroupmember(String name){ 
    try {
      File myObj1 = new File(name + ".txt");
      Scanner myReader = new Scanner(myObj1);
      while (myReader.hasNextLine()) {
        memb.add(myReader.nextLine());
      }
    } catch (Exception e) {
      System.out.println("Group is Empty.");
    }
    return memb;
  }

  public LinkedList<String> Displaygroups() {
    groups.removeAll(groups);
    try {
      File myObj = new File("Groups.txt");
      try (Scanner myReader = new Scanner(myObj)) {
        while (myReader.hasNextLine()) {
          groups.add(myReader.nextLine());
        }
      }
    } catch (Exception e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return groups;
      
  }

  public String getdetails() {
    System.out.print("Enter new firstname : ");
    String fname = sc.nextLine();
    System.out.print("Enter new lastname : ");
    String lname = sc.nextLine();
    return (fname + "" + lname);
  }

  /* 
  public void Menu() {
    char choose = 'y';
    while (choose != 'n') {
      displayMenu();
      readfile();
      System.out.print("Enter your choice : ");
      int choice = sc.nextInt();
      if (choice == 1) {
        System.out.println("-----------Inserting-----------");
        sc.nextLine();
        insertInput();
      } else if (choice == 2) {
        System.out.println("-----------Deletion------------");
        sc.nextLine();
        String name = getdetails();
        delete(name);
      } else if (choice == 3) {
        showContacts();
      } else if (choice == 4) {
        System.out.println("-----------Updating------------");
        sc.nextLine();
        Update();
      } else if (choice == 5) {
        System.out.println("-----------Search Numbers------------");
        sc.nextLine();
        search();
      } else if (choice == 6) {
        System.out.println("-----------Create Groups------------");
        sc.nextLine();
        creategroups();
      }
      if (choice == 7) {
        System.out.println("--------------Groups--------------------");
        Displaygroups();
      }
      if (choice == 8) {
        System.out.println("-----------Program End------------");
        break;
      }
      System.out.print("Do you want to Continue(y/n): ");
      choose = sc.next().charAt(0);
    }
  }
*/
  public void updateNum(String str,String name) {
    readfile();
    int count=-1;
    for(int i=0;i<list.size();i++){
      if(str.equals(list.get(i))){
        count = i;
        break;
      }
    }
    list.set(count, name);
    sortList();
    rewritetext();
      }

  public void showContacts() {
    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i));
    }
  }

  public void addmembers(String file,LinkedList<String> arr) {
          try {
        FileWriter fos = new FileWriter(file + ".txt", true);
        for(int i=0;i<arr.size();i++){
        fos.write(arr.get(i) + "\n");
        }
        fos.close();
    }
     catch (Exception e) {
      System.out.println("Error");
    }
  }

  public void creategroupsfile(String file) {
    try {
      File myObj = new File(file + ".txt");
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
        JOptionPane.showMessageDialog(null, "Group is Created!");
        FileWriter fos = new FileWriter("Groups.txt", true);
        fos.write(file + "\n");
        fos.close();
      } else JOptionPane.showMessageDialog(null, "this name of group is already created!");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public void creategroups() {
    System.out.print("Enter the GroupName : ");
    String group = sc.nextLine();
    creategroupsfile(group);
  }

  public void insertInput() {
    System.out.print("Enter the first name : ");
    String fname = sc.nextLine();
    System.out.print("Enter the last name : ");
    String lname = sc.nextLine();
    System.out.print("Enter the Phone number : ");
    String num = sc.nextLine();

    insert(fname, lname, num);
  }

  public void insert(String fname, String lname, String num) {
    readfile();
    String name = fname + " " + lname + " " + num;
    name = name.toLowerCase();
    WriteFile(name);
    sortList();
    rewritetext();
    System.out.println("Inserting Sucessfully");
  }

  public void readfile() {
    try {
      list.removeAll(list);
      File myObj = new File("PhoneDirectory.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        list.add(myReader.nextLine());
      }
      list1 = list;
      myReader.close();
    } catch (Exception e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public void rewritetext() {
    try {
      String save = "";
      String file = "PhoneDirectory.txt";
      FileWriter fos = new FileWriter(file);
      for (int i = 0; i < list.size(); i++) {
        save += list.get(i) + "\n";
      }
      for (int j = list.size() - 1; j >= 0; j--) {
        save = save.replaceAll(list1.get(j), list.get(j));
      }
      fos.append(save);
      fos.flush();
    } catch (Exception e) {
      System.out.println("Error");
    }
  }

  public void sortList() {
    // it is pre defined sorting algorithm for LinkedList
    list.sort(Comparator.naturalOrder());
  }

  public void WriteFile(String name) {
    try {
      list.add(name);
      FileWriter fw = new FileWriter("PhoneDirectory.txt", true);
      fw.write(name);
      fw.close();
      System.out.println("The content is successfully appended to the file.");
    } catch (Exception ioe) {
      System.out.print("\nSomething went wrong!");
    }
  }

  public void print() {
    System.out.println(list.get(3));
  }

  public void delete(LinkedList<String> name) {
    readfile();
    int n = list.size();
    for (int k = 0; k < name.size(); k++) {
      String str1 = name.get(k);  
      for (int j = 0; j < n; j++) {
        String str = list.get(j);
        if (str.equals(str1)) {
          list.remove(j);
          rewritetext();
          readfile();
          n = list.size();
        }
      }
    }
    sortList();
    rewritetext();
  }

  public int search(String str) {
    Search s = new Search();
    
    int count = 0,count1=-1; 
   
    readfile();
      for(int i=0;i<list.size();i++){
        String temp = s.meth(list.get(i));
        count=0;
        for(int j=0;j<str.length();j++){
          if(temp.charAt(j) == str.charAt(j)){
            count++;
          }
          if(count == str.length()){
            count1 = i;
            break;
          }
        }
        if(count == str.length()){
          break;
        }
      }    
        return count1;
      
      
    
  }

  public static void main(String args[]) {
    Directory Obj = new Directory();
    //    Obj.Menu();
  }
}
