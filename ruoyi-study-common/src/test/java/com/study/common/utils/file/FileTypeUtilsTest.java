package com.study.common.utils.file;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileTypeUtilsTest {

    @Test
    public void getFileTypeTest() throws IOException {
        File file = new File("E:\\test\\a.png");
        String fileType = FileTypeUtils.getFileType(file);
        System.out.println(fileType);
        FileInputStream in = new FileInputStream(file);
        byte[] bytes = new byte[128];
        in.read(bytes);
       in.close();
       fileType = FileTypeUtils.getFileExtendName(bytes);
        System.out.println(fileType);
    }
}
