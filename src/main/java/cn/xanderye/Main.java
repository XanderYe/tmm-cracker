package cn.xanderye;

import cn.xanderye.util.SystemUtil;
import cn.xanderye.util.TmmCrackUtil;
import javassist.*;

import java.io.*;

/**
 * @author XanderYe
 * @description:
 * @date 2020/11/29 20:24
 */
public class Main {
    public static void main(String[] args) {
        String workDir = System.getProperty("user.dir");
        if (SystemUtil.isWindows()) {
            throw new RuntimeException("tmm.jar中包含Windows系统保留关键字，无法完成此操作，请在linux下运行");
        }
        if (SystemUtil.isMac()) {
            System.out.println("macOS默认大小写不敏感，会导致生成的程序报错类丢失，请知悉");
        }
        String tmmJarPath = workDir + File.separator + "tmm.jar";
        try {
            File file = new File(tmmJarPath);
            System.out.println("解压jar");
            TmmCrackUtil.uncompress(tmmJarPath);
            ClassPool.getDefault().insertClassPath(file.getAbsolutePath());
            System.out.println("去除授权校验");
            CtClass nulClass = ClassPool.getDefault().getCtClass("org.tinymediamanager.license.Nul");
            CtMethod a = nulClass.getDeclaredMethod("a");
            a.setBody("{return true;}");
            nulClass.writeFile(workDir);
            System.out.println("去除检测更新");
            CtClass updateClass = ClassPool.getDefault().getCtClass("org.tinymediamanager.updater.UpdateCheck");
            CtMethod update = updateClass.getDeclaredMethod("isUpdateAvailable");
            update.setBody("{return false;}");
            updateClass.writeFile(workDir);
            // 修改翻译文件
            rewriteProp(workDir);
            System.out.println("压缩jar");
            TmmCrackUtil.compress();
            System.out.println("删除临时文件");
            TmmCrackUtil.remove();
            System.out.println("破解成功，文件名：tmm-cracked.jar");
        }  catch (FileNotFoundException e) {
            System.out.println("文件不存在");
            e.printStackTrace();
        } catch (IOException | CannotCompileException | NotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void rewriteProp(String propDir) {
        String zhProp = propDir + "/messages_zh.properties";
        try {
            FileReader zhFr = new FileReader(zhProp);
            BufferedReader zhBr = new BufferedReader(zhFr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = zhBr.readLine()) != null) {
                if (line.startsWith("tmm.license.unregistered")) {
                    line = "tmm.license.unregistered=Crack by XanderYe.";
                } else if (line.startsWith("wizard.disclaimer.long")) {
                    line += "\\n\\ntinyMediaManager由XanderYe破解，去除了50部电影、10部电视剧限制。\\n仅供学习交流，严禁用于商业用途，请于24小时内删除。";
                }
                sb.append(line).append("\r\n");
            }
            zhBr.close();
            zhFr.close();
            FileWriter zhFw = new FileWriter(zhProp);
            BufferedWriter zhBw = new BufferedWriter(zhFw);
            zhBw.write(sb.toString());
            zhBw.close();
            zhFw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String prop = propDir + "/messages.properties";
        try {
            FileReader fr = new FileReader(prop);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("tmm.license.unregistered")) {
                    line = "tmm.license.unregistered=Crack by XanderYe.";
                }
                sb.append(line).append("\r\n");
            }
            br.close();
            fr.close();
            FileWriter fw = new FileWriter(prop);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(sb.toString());
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
