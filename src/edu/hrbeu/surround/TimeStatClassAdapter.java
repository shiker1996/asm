/**
 * author:lenovo
 * create_date:2018/07/12
 * project:asm
 **/
package edu.hrbeu.surround;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * class_name:TimeStatClassAdapter
 * usage:
 **/
public class TimeStatClassAdapter extends ClassVisitor {

   public TimeStatClassAdapter(ClassVisitor classVisitor) {
      super(Opcodes.ASM5, classVisitor);
   }

   public MethodVisitor visitMethod(final int access, final String name, final String desc,
                                    final String signature, final String[] exceptions) {
      MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
      MethodVisitor wrappedMv = mv;
      if (mv != null) {
         if (name.equals("operation")) {
            wrappedMv = new TimeStatMethodAdapter(mv);
         }
      }
      return wrappedMv;
   }
}
