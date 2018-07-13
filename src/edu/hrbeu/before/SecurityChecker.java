/**
 * author:lenovo
 * create_date:2018/07/11
 * project:asm
 **/
package edu.hrbeu.before;

/**
 * class_name:SecurityChecker
 * usage: 对account进行安全检查
 **/
public class SecurityChecker {
   /**
    * 进行安全检查的方法
    *
    * @return 是否安全
    */
   public static boolean checkSecurity() {
      System.out.println("SecurityChecker.checkSercurity...");
      if ((System.currentTimeMillis() & 0x1) == 0) {
         System.out.println("check fail");
         return false;
      } else
         return true;
   }
}
