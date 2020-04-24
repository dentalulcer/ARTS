package com.dbdou.arts.easy.url.test;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.HashUtil;
import cn.hutool.core.util.StrUtil;
import com.dbdou.arts.easy.url.entity.EasyUrl;
import com.dbdou.arts.easy.url.enums.KeyEnum;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dentalulcer
 */
public class TestMain {

    private static final String LETTER_SOURCE = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {

//        randomSeeds();

//        genTable4KeySeq();

//        genTable4Url();

//        hashGen();

//        testRegex();

        testStream();

    }

    private static void testStream() {
        List<Object> result = new ArrayList<>(3);
        result.add(false);
        result.add(false);
        result.add(false);
        System.out.println(result.stream().allMatch((item) -> (boolean) item));
    }

    private static String encode(EasyUrl easyUrl) {
        long id = easyUrl.getId();

        KeyEnum keyEnum = KeyEnum.getByKey(easyUrl.getKey());
        String seeds = keyEnum.getSeeds();
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(seeds.charAt((int) (id % seeds.length())));
            id = id / seeds.length();
        } while (id > 0);
        return StrUtil.fillBefore(StrUtil.reverse(sb.toString()), seeds.charAt(0), 6);
    }

    private static long decode(EasyUrl easyUrl) {
        String code = easyUrl.getCode();
        KeyEnum keyEnum = KeyEnum.getByKey(easyUrl.getKey());
        String seeds = keyEnum.getSeeds();
        long id = 0L;
        for (int i = 0; i < code.length(); i++) {
            id += (seeds.indexOf(code.charAt(i)) * Math.pow(seeds.length(), code.length() - i - 1));
        }
        return id;
    }

    private static void randomSeeds() {
        for (int i = 0; i < LETTER_SOURCE.length(); i++) {
            String seeds = LETTER_SOURCE;
            int length = seeds.length();
            char[] result = new char[length];

            Random random = new Random();
            for (int j = 0; j < length; j++) {
                int index = random.nextInt(seeds.length());
                result[j] = seeds.charAt(index);
                seeds = seeds.replace(seeds.charAt(index) + "", "");
            }
            String item = StrUtil.fillBefore(String.valueOf(i), '0', 2);
            System.out.println("K_" + item + "(\"" + item + "\", \"" + new String(result) + "\", \"" + LETTER_SOURCE.charAt(i) + "\"),");
        }
    }

    private static void genTable4KeySeq() {
        System.out.println("CREATE TABLE `key_seq` (`key` varchar(8) NOT NULL,`incr_factor` int(11) NOT NULL," +
                "`current_val` bigint(18) NOT NULL, PRIMARY KEY (`key`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
        for (KeyEnum keyEnum : KeyEnum.values()) {
            System.out.println("insert into `key_seq` values('" + keyEnum.getKey() + "', 100, 1);");
        }
    }

    private static void genTable4Url() {
        for (KeyEnum keyEnum : KeyEnum.values()) {
            System.out.println("CREATE TABLE `url_" + keyEnum.getKey()
                    + "` (`id` bigint(18) NOT NULL,`code` varchar(6) NOT NULL,"
                    + "`url` varchar(1024) NOT NULL, PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;                                                                                                        ");
        }
    }

    private static void hashGen() {
        System.out.println(HashUtil.additiveHash("http://www.baidu.com", KeyEnum.values().length));
        System.out.println(HashUtil.additiveHash("http://www.baidu.com", KeyEnum.values().length));
        System.out.println(HashUtil.additiveHash("http://www.baidu.com/", KeyEnum.values().length));
        System.out.println(HashUtil.additiveHash("http://www.baidu.com/?a=1", KeyEnum.values().length));
        System.out.println(HashUtil.additiveHash("http://www.baidu.com/?a=2", KeyEnum.values().length));
        System.out.println(HashUtil.additiveHash("http://www.baidu.com/?a=3", KeyEnum.values().length));
    }

    private static void clearData() {
        System.out.println("update key_seq set current_val=1;");
        for (int i = 0; i < LETTER_SOURCE.length(); i++) {
            String item = StrUtil.fillBefore(String.valueOf(i), '0', 2);
            System.out.println("truncate table url_" + item + ";");
        }
    }

    private static void testRegex() {
        String url = "http%3A%2F%2Fwww.baidu.com%2F%3Fk";
        try {
            url = URLDecoder.decode(url, "utf-8");
            System.out.println(url);
            System.out.println(Validator.isMactchRegex(Validator.URL_HTTP, url));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
