package com.hadenwatne.realrockets.utils;

import java.util.Random;

public class RandomUtil {
    private static Random r;

    public static void init(){
        r = new Random();
    }

    public static int getInt(int bound){
        return r.nextInt(bound);
    }
}
