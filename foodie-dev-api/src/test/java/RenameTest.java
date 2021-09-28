import org.junit.Test;

import java.io.File;

/**
 * @ProjectName: foodie-dev
 * @Package: PACKAGE_NAME
 * @ClassName: RenameTest
 * @Author: albert.fang
 * @Description: 将文件夹下有规律的杂乱的文件名重命名
 * @Date: 2021/9/28 17:56
 */
public class RenameTest {

    @Test
    public void videoRenameTest(){
        File targetFile = new File("E:\\study\\阶段一：单体电商项目架构，开发与上线（1~5周）");
        recursionUpdateFileNames(targetFile);
    }

    private void recursionUpdateFileNames(File file){
        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (File s : files) {
                if (s.isDirectory()){
                    recursionUpdateFileNames(s);
                }
                else {
                    // 找到所有 MP4 文件
                    if (s.getName().endsWith(".mp4")) {
                        // 替换广告字符
                        String fixedName = s.getName().replace("【猿人部落www.97yrbl.com】", "");
                        // 得到这个文件所在的文件夹
                        String parentPath = s.getParent();
                        s.renameTo(new File(parentPath + File.separator + fixedName));
                    }
                    // 不是pdf和mp4的文件，删除处理
                    if (!s.getName().endsWith(".mp4") && !s.getName().endsWith(".pdf")){
                        s.delete();
                    }
                }
            }
        }
    }

}
