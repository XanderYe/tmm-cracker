package cn.xanderye.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author XanderYe
 * @description:
 * @date 2020/11/29 20:24
 */
public class TmmCrackUtil {
    /**
     * 解压jar包
     * @param path
     * @return void
     * @author XanderYe
     * @date 2020/11/29
     */
    public static void uncompress(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        String[] cmds = {"jar", "xvf", path};
        SystemUtil.execStr(Charset.defaultCharset(), cmds);
    }

    /**
     * 压缩jar
     * @return void
     * @author XanderYe
     * @date 2020/11/29
     */
    public static void compress() {
        String[] cmds = new String[]{"sh", "-c", "jar cfm tmm-cracked.jar META-INF/MANIFEST.MF META-INF org logback.xml *.txt *.properties"};
        SystemUtil.execStr(Charset.defaultCharset(), cmds);
    }

    public static void remove() {
        String[] cmds = new String[]{"sh","-c", "rm -rf META-INF org logback.xml *.txt *.properties"};
        SystemUtil.execStr(Charset.defaultCharset(), cmds);
    }
}
