package com.morton.juc.c_025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author MortonShaw
 * @date 2021/8/1 16:03
 */
public class T03_SynchronizedList {

    public static void main(String[] args) {
        List<String> strs = new ArrayList<>();
        List<String> strsSync = Collections.synchronizedList(strs);
    }

}
