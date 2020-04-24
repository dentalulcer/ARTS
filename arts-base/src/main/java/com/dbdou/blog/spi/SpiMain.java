package com.dbdou.blog.spi;

import java.util.ServiceLoader;

public class SpiMain {

    public static void main(String[] args) {
        ServiceLoader<Robot> loader = ServiceLoader.load(Robot.class);
        loader.forEach(Robot::say);
    }

}
