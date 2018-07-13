/**
 * author:lenovo
 * create_date:2018/07/13
 * project:asm
 **/
package edu.hrbeu.asm;

/**
 *class_name:RunLoopAccountMain
 *usage:
 **/
public class RunLoopAccountMain {
   public static void main(String[] args) throws InterruptedException {
      Account account = new Account();
      while (true){
         account.operation();
         try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }
}
