import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.sl.usermodel.SlideShowFactory;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.junit.Test;

import java.io.*;

/**
 * @ProjectName: foodie-dev
 * @Package: PACKAGE_NAME
 * @ClassName: RenameTest
 * @Author: albert.fang
 * @Description: 将文件夹下有规律的杂乱的文件名重命名
 * @Date: 2021/9/28 17:56
 */
public class RenameTest {

//    @Test
    public void createPPTByXSLF() throws IOException {
        String parentFilePath = "E:\\IdeaProjects\\foodie-dev\\doc\\ppt\\第二阶段\\";
        new File(parentFilePath).mkdirs();
        for (int i = 10; i < 25; i++) {
            String finalFile = parentFilePath + (i < 10 ? "0" + i : i) + " .pptx";
//        String finalFile = parentFilePath + "test.pptx";
        // create a new empty slide show
        XMLSlideShow ppt = new XMLSlideShow();
        // add first slide
        XSLFSlide blankSlide = ppt.createSlide();
        // add second slide
        XSLFSlide anotherSlide = ppt.createSlide();
        FileOutputStream out = new FileOutputStream(finalFile);
        ppt.write(out);
        out.close();
        }

    }

//    @Test
    public void createPPTByHSLF() throws IOException {
        String parentFilePath = "E:\\IdeaProjects\\foodie-dev\\doc\\ppt\\第六阶段\\";
//        for (int i = 1; i < 2; i++) {
//            String finalFile = parentFilePath + (i < 10 ? "0" + i : i) + " .pptx";
            String finalFile = parentFilePath + "test.pptx";
            //create a new empty slide show
            HSLFSlideShow ppt = new HSLFSlideShow();
            //add first slide
            HSLFSlide s1 = ppt.createSlide();
            //add second slide
            HSLFSlide s2 = ppt.createSlide();
            //save changes in a file
            FileOutputStream out = new FileOutputStream(finalFile);
            ppt.write(out);
            out.close();
//        }

    }


//    @Test
    public void createPPT() throws IOException {
        String parentFilePath = "E:\\IdeaProjects\\foodie-dev\\doc\\ppt\\第六阶段\\";
        new File(parentFilePath).mkdirs();
        for (int i = 1; i < 25; i++) {
            String finalFile = parentFilePath + (i < 10 ? "0" + i : i) + " .pptx";
            File file = new File(finalFile);
            file.createNewFile();
        }
    }

//    @Test
    public void pdfRenameTest(){
        File targetFile = new File("E:\\study\\阶段三：逐个击破分布式核心问题（9~17周）\\第9周 分布式会话与单点登录SSO");
        for (File file : targetFile.listFiles()) {
            int index = file.getName().indexOf("-慕课网就业班");
            // 替换广告字符
            String fixedName = file.getName().substring(0,index) + ".mp4";
            // 得到这个文件所在的文件夹
            String parentPath = file.getParent();
            file.renameTo(new File(parentPath + File.separator + fixedName));
        }
    }

//    @Test
    public void pdfRenameDelete(){
        File targetFile = new File("E:\\study1\\阶段三：逐个击破分布式核心问题（9~17周）\\第9周 分布式会话与单点登录SSO");
        for (File file : targetFile.listFiles()) {
            if (file.getName().endsWith(".pdf")){
                file.delete();
            }
        }
    }

//    @Test
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
