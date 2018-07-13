/**
 * author:lenovo
 * create_date:2018/07/12
 * project:asm
 **/
package edu.hrbeu.surround;

/**
 * class_name:TimeStat
 * usage:
 **/
public class TimeStat {
   /**
    * 计时
    */
   static ThreadLocal<Long> t = new ThreadLocal<Long>();

   /**
    * 设置开始时间
    */
   public static void start() {
      t.set(System.currentTimeMillis());
   }

   /**
    *输出结束时间
    */
   public static void end() {
      long time = System.currentTimeMillis() - t.get();
      System.out.println(Thread.currentThread().getStackTrace()[2] + "spend:" + time);
   }
}
