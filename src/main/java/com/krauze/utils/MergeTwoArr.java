package com.krauze.utils;

import java.util.ArrayList;
import java.util.List;

public class MergeTwoArr {

    public static List<Comparable> merge(ArrayList<Comparable> arrA, ArrayList<Comparable> arrB, boolean sort) {

        List<Comparable> arrC = new ArrayList<>(arrA.size() + arrB.size());
        int posA = 0;
        int posB = 0;
        Comparable tmpPosA = 0;
        Comparable tmpPosB = 0;
        Comparable tmpPosC = 0;

        for (int i = 0; i < arrC.size(); i++) {
            tmpPosA = arrA.get(i - posA);
            tmpPosB = arrB.get(i - posB);
            tmpPosC = arrC.get(i - 1);

            if (sort) {
                if (i != 0 && (tmpPosA.compareTo(tmpPosB) > 0 && tmpPosA.compareTo(tmpPosC) < 0) ||
                        i != 0 && (tmpPosA.compareTo(tmpPosC) < 0 && posB == arrB.size())) {
                    System.out.println("Элемент - " + tmpPosA +
                            " не отсортирован!!! Данный элемент не будет включен в общий файл!!!");
                    continue;
                } else if (i != 0 && (tmpPosB.compareTo(tmpPosA) > 0 && tmpPosB.compareTo(tmpPosC) < 0) ||
                        i != 0 && (tmpPosB.compareTo(tmpPosC) < 0 && posA == arrA.size())) {
                    System.out.println("Элемент - " + tmpPosB +
                            " не отсортирован!!! Данный элемент не будет включен в общий файл!!!");
                    continue;
                }
                if (posA == arrA.size()) {
                    arrC.add(tmpPosB);
                    posB++;
                } else if (posB == arrB.size()) {
                    arrC.add(tmpPosA);
                    posA++;
                } else if (tmpPosA.compareTo(tmpPosB) < 0) {
                    arrC.add(arrA.get(i - posA));
                    posB++;
                } else if (tmpPosB.compareTo(tmpPosA) < 0) {
                    arrC.add(tmpPosB);
                    posA++;
                }
            } else {
                if (i != 0 && (tmpPosA.compareTo(tmpPosB) < 0 && tmpPosA.compareTo(tmpPosC) > 0) ||
                        i != 0 && (tmpPosA.compareTo(tmpPosC) > 0 && posB == arrB.size())) {
                    System.out.println("Элемент - " + tmpPosA +
                            " не отсортирован!!! Данный элемент не будет включен в общий файл!!!");
                    continue;
                } else if (i != 0 && (tmpPosB.compareTo(tmpPosA) < 0 && tmpPosB.compareTo(tmpPosC) > 0) ||
                        i != 0 && (tmpPosB.compareTo(tmpPosC) > 0 && posA == arrA.size())) {
                    System.out.println("Элемент - " + tmpPosB +
                            " не отсортирован!!! Данный элемент не будет включен в общий файл!!!");
                    continue;
                }
                if (posA == arrA.size()) {
                    arrC.add(tmpPosB);
                    posB++;
                } else if (posB == arrB.size()) {
                    arrC.add(tmpPosA);
                    posA++;
                } else if (tmpPosA.compareTo(tmpPosB) > 0) {
                    arrC.add(arrA.get(i - posA));
                    posB++;
                } else if (tmpPosB.compareTo(tmpPosA) > 0) {
                    arrC.add(tmpPosB);
                    posA++;
                }
            }
        }
        return arrC;
    }

    public static ArrayList<Comparable> ddd(boolean sort, ArrayList<Comparable> ... arrayLists) {
        int lengthOutArr = 0;
        for (int i = 0; i < arrayLists.length; i++) {
            lengthOutArr += arrayLists[i].size();
        }
        List<Comparable> arrC = new ArrayList<>(lengthOutArr);
        return null;
    }
}
