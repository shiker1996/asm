/**
 * author:lenovo
 * create_date:2018/07/11
 * project:asm
 **/
package edu.hrbeu.asm;

import edu.hrbeu.asm.Account;
import edu.hrbeu.before.AddSecurityCheckClassAdapter;
import edu.hrbeu.surround.TimeStatClassAdapter;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * class_name:SecurityWeaveGenerator
 * usage:
 **/
public class SecurityWeaveGenerator {
   /**
    * 通过asm生成检查类account的class文件
    *
    * @param args args
    * @throws IOException 读写异常
    */
   public static void main(final String[] args) throws IOException {
      String classname = Account.class.getName();
      ClassReader cr = new ClassReader(classname);
      ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
//      AddSecurityCheckClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);
      TimeStatClassAdapter classAdapter = new TimeStatClassAdapter(cw);
      cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
      byte[] data = cw.toByteArray();
      File file = new File("out/production/asm/" + classname.replaceAll("\\.", "/") + ".class");
      FileOutputStream fout = new FileOutputStream(file);
      fout.write(data);
      fout.close();
   }
}
