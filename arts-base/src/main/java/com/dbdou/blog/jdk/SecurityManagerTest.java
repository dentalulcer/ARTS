package com.dbdou.blog.jdk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.AccessControlException;

/**
 * Created by dentalulcer
 */
public class SecurityManagerTest {
    private static class MySecurityManager extends SecurityManager {
        @Override
        public void checkRead(String file) {
            if ("java.policy".contains(file)) {
                throw new AccessControlException("cannot read file:" + file);
            }
            super.checkRead(file);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        // install
        System.setSecurityManager(new MySecurityManager());
        // read
        InputStream in = new FileInputStream(new File("java.policy"));
        // uninstall
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            System.setSecurityManager(null);
        }
    }
}
