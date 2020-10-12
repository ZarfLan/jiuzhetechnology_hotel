package com.jiuzhe.app.hotel.utils;

import java.util.Random;

public class RandomUtil {
    private static Random random = new Random();

    public static String getMessageCode(){
        return "holle";
    }

    public static Integer getRandom(int max,int min){
        return random.nextInt(max)+min;
    }
}
