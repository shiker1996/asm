/**
 * author:lenovo
 * create_date:2018/07/13
 * project:asm
 **/
package edu.hrbeu.agent;

import edu.hrbeu.asm.Account;
import edu.hrbeu.surround.TimeStatClassAdapter;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;

/**
 *class_name:PreMainAddTimeStatAgent
 *usage:
 **/
public class PreMainAddTimeStatAgent {
   public static void premain(String agentArgs, Instrumentation inst) {
      System.out.println("agentArgs:"+agentArgs);
      inst.addTransformer(new ClassFileTransformer() {
         @Override
         public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
            if(className.equals("edu/hrbeu/asm/Account")){
               System.out.println("meet edu/hrbeu/asm/Account");
               ClassReader cr = new ClassReader(classfileBuffer);
               ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS|ClassWriter.COMPUTE_FRAMES);
               TimeStatClassAdapter classAdapter = new TimeStatClassAdapter(cw);
               cr.accept(classAdapter,ClassReader.SKIP_DEBUG);
               return cw.toByteArray();
            }else{
               System.out.println(className);
               return classfileBuffer;
            }
         }
      },true);
   }

   public static void agentmain(String agentArgs,Instrumentation inst) throws UnmodifiableClassException {
      System.out.println("Agent Main Called");
      System.out.println("agentArgs:"+agentArgs);
      premain(agentArgs, inst);
      inst.retransformClasses(Account.class);
   }
}
