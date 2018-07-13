/**
 * author:yinweicheng
 */
package edu.hrbeu.asm;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.InvocationTargetException;

/**
 * 继承classloader，方便立即被系统加载
 * 实现Opcodes，方便访问全局变量
 */
public class AsmHelloWorld extends ClassLoader implements Opcodes{
   /**
    *
    * @param args 程序参数
    * @throws InvocationTargetException 类加载异常
    * @throws IllegalAccessException 初始化实例失败
    */
   public static void main(final String[] args) throws InvocationTargetException, IllegalAccessException {
      ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
      cw.visit(V1_7, ACC_PUBLIC, "Example", null, "java/lang/Object", null);
      //设置类的基本信息
      MethodVisitor mw = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
      //生成example的构造函数
      mw.visitVarInsn(ALOAD, 0);
      mw.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
      mw.visitInsn(RETURN);
      mw.visitMaxs(0, 0);
      mw.visitEnd();
      //生成main主函数
      mw = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
      mw.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
      mw.visitLdcInsn("hello world!");
      mw.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
      mw.visitInsn(RETURN);
      mw.visitMaxs(0,0);
      mw.visitEnd();
      //生成class文件流的二进制表示
      byte[] code = cw.toByteArray();
      //将生成的文件流载入系统，并通过反射调用main方法
      AsmHelloWorld loader = new AsmHelloWorld();
      Class exampleClass = loader.defineClass("Example", code,0, code.length);
      exampleClass.getMethods()[0].invoke(null, new Object[]{null});
   }
}
