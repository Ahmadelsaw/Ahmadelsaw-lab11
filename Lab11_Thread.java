import java.util.*;

public class Lab11_Thread extends Thread {
    private static ArrayList<String> data = new ArrayList<String>();
    private String name;
    private int runs; 

    public Lab11_Thread(String name, int runs) {
      this.name = name;
      this.runs = runs;
    }

    public void run() {
      for(int i = 0; i < runs; i++){
        try{
          Thread.sleep(50);
        } catch (Exception e){
            e.printStackTrace();
        }
        this.addItem( name + " " + i);
      }
    }

    public synchronized void addItem(String s){
      System.out.println(s);
      data.add(s);
    }

    public ArrayList<String> getData(){
      return data;
    }

    public void setData(ArrayList<String> data){
      this.data = data;
    }
}