package com.krauze;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    public static void main(String[] args) {
        File outFile = null;
        List<String> inFiles = new ArrayList<>();
        boolean type = false;
        boolean sort = true;

        if (args.length == 0) {
            System.out.println("Не заданы входные данные!");
            return;
        }

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-a" -> sort = true;//  up
                case "-d" -> sort = false;// down
                case "-i" -> type = false;// int
                case "-s" -> type = true;//  String
                default -> {
                    if (outFile == null) {
                        outFile = new File(args[i]);
                    } else {
                        inFiles.add(args[i]);
                    }
                }
            }
        }
    }


    static long getAvailableMemory() {
        System.gc();
        Runtime runtime =Runtime.getRuntime();
        return runtime.maxMemory() - runtime.totalMemory() - runtime.freeMemory();
    }
}

//        List<Comparable> out = new ArrayList<>();
//        ArrayList<ArrayList<Comparable>> in = new ArrayList<>();
//        ArrayList<Comparable> one = new ArrayList<>();
//        ArrayList<Comparable> two = new ArrayList<>();
//        ArrayList<Comparable> tree = new ArrayList<>();
//-s -a
//        one.addAll(Arrays.asList("a", "A", "B", "b", "C", "c"));
//        two.addAll(Arrays.asList("D", "f"));
//        tree.addAll(Arrays.asList("H", "j"));
//-i -a
//        one.addAll(Arrays.asList(1,2,3,4,5));
//        two.addAll(Arrays.asList(2,5,4,6,8));
//        tree.addAll(Arrays.asList(10,3,28));
//-i -d
//        one.addAll(Arrays.asList(5,4,3,2,1));
//        two.addAll(Arrays.asList(3,2,4,1));
//        tree.addAll(Arrays.asList(11,4,10));
//
//
//        in.add(one);
//        in.add(two);
//        in.add(tree);
//
//        out = MergeTwoArr.merge(false, false, in);
//
//        System.out.println(out);