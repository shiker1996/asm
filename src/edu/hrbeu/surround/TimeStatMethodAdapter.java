/**
 * author:lenovo
 * create_date:2018/07/12
 * project:asm
 **/
package edu.hrbeu.surround;

import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * class_name:TimeStatMethodAdapter
 * usage:
 **/
public class TimeStatMethodAdapter extends MethodVisitor implements Opcodes {
   public TimeStatMethodAdapter(MethodVisitor mv) {
      super(Opcodes.ASM5, mv);
   }

   public void visitCode() {
      visitMethodInsn(Opcodes.INVOKESTATIC, "edu/hrbeu/surround/TimeStat", "start", "()V");
      super.visitCode();
   }

   public void visitInsn(int opcode) {
      if (opcode >= IRETURN && opcode <= RETURN) {
         visitMethodInsn(Opcodes.INVOKESTATIC, "edu/hrbeu/surround/TimeStat", "end", "()V");
      }
      mv.visitInsn(opcode);
   }
}
