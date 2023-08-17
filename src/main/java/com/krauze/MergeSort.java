package com.krauze;

import com.krauze.utils.MergeTwoArr;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {
    public static void main(String[] args) {
        List<Comparable> out = new ArrayList<>();
        ArrayList<ArrayList<Comparable>> in = new ArrayList<>();
        ArrayList<Comparable> one = new ArrayList<>();
        ArrayList<Comparable> two = new ArrayList<>();
        ArrayList<Comparable> tree = new ArrayList<>();
        one.addAll(Arrays.asList(1,2,8,4,5,56));
        two.addAll(Arrays.asList(4,5,10,7,8));
        tree.addAll(Arrays.asList(4,5,10,7,11));
        in.add(one);
        in.add(two);
        in.add(tree);

        out = MergeTwoArr.merge(true, in);

        System.out.println(out);
//        File outFile = null;
//        List<String> inFiles = new ArrayList<>();
//        boolean type = false;
//        boolean sort = true;
//
//        if (args.length == 0) {
//            System.out.println("Не заданы входные данные!");
//            return;
//        }
//
//        for (int i = 0; i < args.length; i++) {
//            switch (args[i]) {
//                case "-a" -> sort = true;//  up
//                case "-d" -> sort = false;// down
//                case "-i" -> type = false;// int
//                case "-s" -> type = true;//  String
//                default -> {
//                    if (outFile == null) {
//                        outFile = new File(args[i]);
//                    } else {
//                        inFiles.add(args[i]);
//                    }
//                }
//            }
//        }
    }


    static long getAvailableMemory() {
        System.gc();
        Runtime runtime =Runtime.getRuntime();
        return runtime.maxMemory() - runtime.totalMemory() - runtime.freeMemory();
    }


//    private static int[] mergeArrayInt(int [] arrA, int [] arrB) {
//
//        int [] arrC = new int[arrA.length + arrB.length];
//        int posA = 0;
//        int posB = 0;
//
//        //проверка на то сортированный ли массив, если нет, сообщаем что элемент не отсортирован и пропускаем его
//        for (int i = 0; i < arrC.length; i++) {
//            if (i != 0 && (arrA[i - posA] > arrB[i - posB] && arrA[i - posA] < arrC[i - 1]) ||
//                    i != 0 && (arrA[i - posA] < arrC[i - 1] && posB == arrB.length)) {
//                System.out.println("Элемент - " + arrA[i - posA] + ", из файла - " + "fileName.txt" +
//                        " не отсортирован!!! Данный элемент не будет включен в общий файл!!!");
//                continue;
//            } else if (i != 0 && (arrB[i - posB] > arrA[i - posA] && arrB[i - posB] < arrC[i - 1]) ||
//                    i != 0 && (arrB[i - posB] < arrC[i - 1] && posA == arrA.length)) {
//                System.out.println("Элемент - " + arrB[i - posB] + ", из файла - " + "fileName.txt" +
//                        " не отсортирован!!! Данный элемент не будет включен в общий файл!!!");
//                continue;
//            }
//            if (posA == arrA.length) {
//                arrC[i] = arrB[i - posB];
//                posB++;
//            } else if (posB == arrB.length) {
//                arrC[i] = arrA[i - posA];
//                posA++;
//            } else if (arrA[i - posA] < arrB[i - posB]) {
//                arrC[i] = arrA[i -posA];
//                posB++;
//            } else if (arrB[i - posB] < arrA[i - posA]){
//                arrC[i] = arrB[i - posB];
//                posA++;
//            }
//        }
//        return arrC;
//    }
}