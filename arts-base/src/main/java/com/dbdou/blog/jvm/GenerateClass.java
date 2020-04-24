package com.dbdou.blog.jvm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by dentalulcer.
 */
public class GenerateClass {

    public static void main(String[] args) {

        File file = new File("/Users/a0000/test/java_test/TestField.java");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fw = new FileWriter(file);
            fw.write("public class TestField {\n");
            for (int i = 0; i < 65535; i++) {
                fw.write("\tprivate String param" + i + ";\n");
            }
            fw.write("}");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
