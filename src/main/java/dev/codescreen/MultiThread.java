package dev.codescreen;

public class MultiThread extends Thread{
    public void run(){
        System.out.println("Thread "+Thread.currentThread().getId()+" is running");
    }
    public static void main(String args[]) 
 {   
    MultiThread obj1=new MultiThread();  
        obj1.start(); 
        MultiThread obj2=new MultiThread();  
        obj2.start();  
  }  
}
