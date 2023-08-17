package com.krauze.utils;

import java.util.ArrayList;
import java.util.List;

public class MergeTwoArr {

    private static List<Comparable> arrOut = new ArrayList<>();

    public static List<Comparable> merge(boolean sort, ArrayList<ArrayList<Comparable>>arraysIn) {

        if (arraysIn.size() == 0) {
            return arrOut;
        }

            ArrayList<Comparable> arrA;
            ArrayList<Comparable> arrB;
            int posA = 0;
            int posB = 0;
            int lengthOutArr;

            lengthOutArr = arraysIn.get(0).size() + arrOut.size();
            List<Comparable> arrC = new ArrayList<>();

            arrA = (ArrayList<Comparable>) arrOut;
            arrB = arraysIn.remove(0);
            int buf = Integer.MIN_VALUE;

            for (int i = 0; i < lengthOutArr; i++) {
                if (sort) {
                    if (arrB.size() != 0 && arrB.size() != posB && (int)arrB.get(posB) < buf) {
                        posB++;
                        System.out.println("Unsorted element! Skip!");
                        continue;
                    } else if(arrA.size() != 0 && arrA.size() != posA && (int)arrA.get(posA) < buf) {
                        posA++;
                        System.out.println("Unsorted element! Skip!");
                        continue;
                    }
                    if (posA >= arrA.size() && (int)arrB.get(posB) >= buf) {
                        int a = (int)arrB.get(posB);
                        buf = a;
                        arrC.add(a);
                        posB++;
                    } else if (posB >= arrB.size() && (int)arrA.get(posA) > buf) {
                        int a = (int)arrA.get(posA);
                        buf = a;
                        arrC.add(a);
                        posA++;
                    } else if (arrA.get(posA).compareTo(arrB.get(posB))
                            < 0 && (int)arrA.get(posA) > buf) {
                        int a = (int)arrA.get(posA);
                        buf = a;
                        arrC.add(a);
                        posA++;
                    } else if (arrB.get(posB).compareTo(arrA.get(posA)) < 0 && (int)arrB.get(posB) >= buf) {
                        int a = (int)arrB.get(posB);
                        buf = a;
                        arrC.add(a);
                        posB++;
                    } else if (arrA.get(posA).compareTo(arrB.get(posB)) == 0 && (int)arrA.get(posA) >= buf){
                        int a = (int)arrA.get(posA);
                        buf = a;
                        arrC.add(a);
                        posA++;
                    }
                } else {
//                    if (i != 0 && (tmpPosA.compareTo(tmpPosB) < 0 && tmpPosA.compareTo(tmpPosC) > 0) ||
//                            i != 0 && (tmpPosA.compareTo(tmpPosC) > 0 && posB == arrB.size())) {
//                        System.out.println("Элемент - " + tmpPosA +
//                                " не отсортирован!!! Данный элемент не будет включен в общий файл!!!");
//                        continue;
//                    } else if (i != 0 && (tmpPosB.compareTo(tmpPosA) < 0 && tmpPosB.compareTo(tmpPosC) > 0) ||
//                            i != 0 && (tmpPosB.compareTo(tmpPosC) > 0 && posA == arrA.size())) {
//                        System.out.println("Элемент - " + tmpPosB +
//                                " не отсортирован!!! Данный элемент не будет включен в общий файл!!!");
//                        continue;
//                    }
                    if (posA == arrA.size()) {
                        arrC.add(arrB.get(i - posB));
                        posB++;
                    } else if (posB == arrB.size()) {
                        arrC.add(arrA.get(i - posA));
                        posA++;
                    } else if (arrA.get(i - posA).compareTo(arrB.get(i - posB)) > 0) {
                        arrC.add(arrA.get(i - posA));
                        posB++;
                    } else if (arrB.get(i - posB).compareTo(arrA.get(i - posA)) > 0) {
                        arrC.add(arrB.get(i - posB));
                        posA++;
                    }
                }
            }
            arrOut = arrC;
            return merge(sort, arraysIn);
        }
}
