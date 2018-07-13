/**
 * author:lenovo
 * create_date:2018/07/12
 * project:asm
 **/
package edu.hrbeu.before;

import jdk.internal.org.objectweb.asm.Label;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * class_name:AddSecurityCheckMethodAdapter
 * usage: 添加方法检查
 **/
public class AddSecurityCheckMethodAdapter extends MethodVisitor {
   /**
    * 继承构造方法
    *
    * @param mv ：methodvisitor
    */
   public AddSecurityCheckMethodAdapter(final MethodVisitor mv) {
      super(Opcodes.ASM5, mv);
   }

   public void visitCode() {
      Label continueLabel = new Label();
      visitMethodInsn(Opcodes.INVOKESTATIC, "edu/hrbeu/asm/SecurityChecker", "checkSecurity", "()Z");
      visitJumpInsn(Opcodes.IFNE, continueLabel);
      visitInsn(Opcodes.RETURN);
      visitLabel(continueLabel);
      super.visitCode();
   }
}
