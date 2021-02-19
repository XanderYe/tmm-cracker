package cn.xanderye.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created on 2020/11/5.
 *
 * @author XanderYe
 */
public class SystemUtil {

    /**
     * 空格
     */
    public static final String BREAK = " ";
    /**
     * Tab
     */
    public static final String TAB = "    ";
    /**
     * Windows换行符
     */
    public static final String WINDOWS_LINE_BREAK = "\r\n";
    /**
     * UNIX换行符
     */
    public static final String UNIX_LINE_BREAK = "\r";

    public static final String[] REG_RESULT = new String[]{"操作成功完成。"};

    /**
     * 调用cmd方法，默认GBK编码
     * @param cmdStr
     * @return java.lang.String
     * @author XanderYe
     * @date 2020/11/5
     */
    public static String execStr(String cmdStr) {
        return execStr(getCharset(), cmdStr);
    }

    /**
     * 调用cmd方法
     * @param charset
     * @param cmds
     * @return java.lang.String
     * @author XanderYe
     * @date 2020/11/5
     */
    public static String execStr(Charset charset, String...cmds) {
        if (1 == cmds.length) {
            if (cmds[0] == null || "".equals(cmds[0])) {
                throw new NullPointerException("Empty command !");
            }
            cmds = cmds[0].split(BREAK);
        }
        Process process = null;
        try {
            process = new ProcessBuilder(cmds).redirectErrorStream(true).start();
            InputStream is = process.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(is, charset));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = buffer.readLine()) != null) {
                sb.append(line).append(getLineBreak());
            }
            is.close();
            return sb.toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != process) {
                process.destroy();
            }
        }
        return null;
    }

    /**
     * 增加注册表
     * @param path 注册表路径
     * @param key 键
     * @param type 类型 常用 REG_SZ/REG_DWORD
     * @param value 值
     * @return boolean
     * @author XanderYe
     * @date 2020/11/15
     */
    public static boolean addReg(String path, String key, String type, String value) {
        String command = "reg add \"" + path + "\" /v " + key + " /t " + type + " /d " + value + " /f";
        String res = execStr(command).replaceAll("[\\r|\\n|\\\\s]", "");
        return Arrays.asList(REG_RESULT).contains(res);
    }

    /**
     * 删除注册表
     * @param path 注册表路径
     * @param key 键
     * @return boolean
     * @author XanderYe
     * @date 2020/11/15
     */
    public static boolean deleteReg(String path, String key) {
        String command = "reg delete \"" + path + "\" /v " + key + " /f";
        System.out.println(command);
        String res = execStr(command).replaceAll("[\\r|\\n|\\\\s]", "");
        System.out.println(res);
        return Arrays.asList(REG_RESULT).contains(res);
    }

    /**
     * 判断系统环境
     * @param
     * @return boolean
     * @author XanderYe
     * @date 2020/11/5
     */
    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    public static boolean isMac() {
        return System.getProperty("os.name").toLowerCase().contains("mac");
    }

    /**
     * 获取系统字符编码
     * @param
     * @return java.nio.charset.Charset
     * @author XanderYe
     * @date 2020/11/5
     */
    public static Charset getCharset() {
        return isWindows() ? Charset.forName("GBK") : Charset.defaultCharset();
    }

    /**
     * 获取系统换行符
     * @param
     * @return java.lang.String
     * @author XanderYe
     * @date 2020/11/5
     */
    public static String getLineBreak() {
        return isWindows() ? WINDOWS_LINE_BREAK : UNIX_LINE_BREAK;
    }
}
