/**
 * author:lenovo
 * create_date:2018/07/11
 * project:asm
 **/
package edu.hrbeu.before;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * class_name:AddSecurityCheckClassAdapter
 * useage: 添加类检查
 **/
public class AddSecurityCheckClassAdapter extends ClassVisitor {
   /**
    * 继承构造方法
    *
    * @param classVisitor :定义类观察器
    */
   public AddSecurityCheckClassAdapter(final ClassVisitor classVisitor) {
      super(Opcodes.ASM5, classVisitor);
   }

   /**
    * @param access     : 访问标志
    * @param name       : 名称索引
    * @param desc       :描述符索引
    * @param signature  :标志位
    * @param exceptions :异常表
    * @return :返回MethodVisitor
    */
   public MethodVisitor visitMethod(final int access, final String name, final String desc,
                                    final String signature, final String[] exceptions) {
      MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
      MethodVisitor wrappedMv = mv;
      if (mv != null) {
         if (name.equals("operation")) {
            wrappedMv = new AddSecurityCheckMethodAdapter(mv);
         }
      }
      return wrappedMv;
   }
}
