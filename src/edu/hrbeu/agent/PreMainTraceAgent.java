/**
 * author:lenovo
 * create_date:2018/07/13
 * project:asm
 **/
package edu.hrbeu.agent;


import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 *class_name:PreMainTraceAgent
 *usage:
 **/
public class PreMainTraceAgent {
   public static void premain(String agentArgs, Instrumentation inst) {
      System.out.println("agentArgs:"+agentArgs);
      inst.addTransformer(new ClassFileTransformer() {
         @Override
         public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
            System.out.println("load Class:"+className);
            return classfileBuffer;
         }
      });

   }
}
