package com.krauze.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static List<Comparable> arrOut = new ArrayList<>();

    public static List<Comparable> merge(boolean sort, boolean type,ArrayList<ArrayList<Comparable>>arraysIn) {

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
        //В зависимости от типа присваиваем переменной Min'e значение в нужном формате
        Comparable buf = "";
        if (!type && sort) {
            buf = Integer.MIN_VALUE;
        }else if(!type && !sort) {
            buf = Integer.MAX_VALUE;
        }

        if (sort) {
            for (int i = 0; i < lengthOutArr; i++) {
                if (arrB.size() != 0 && arrB.size() != posB && arrB.get(posB).compareTo(buf) < 0) {
                    posB++;
                    System.out.println("Unsorted element! Skip!");
                    continue;
                } else if (arrA.size() != 0 && arrA.size() != posA && arrA.get(posA).compareTo(buf) < 0) {
                    posA++;
                    System.out.println("Unsorted element! Skip!");
                    continue;
                }
                if (posA >= arrA.size() && arrB.get(posB).compareTo(buf) >= 0) {
                    Comparable a = arrB.get(posB);
                    buf = a;
                    arrC.add(a);
                    posB++;
                } else if (posB >= arrB.size() && arrA.get(posA).compareTo(buf) >= 0) {
                    Comparable a = arrA.get(posA);
                    buf = a;
                    arrC.add(a);
                    posA++;
                } else if (arrA.get(posA).compareTo(arrB.get(posB)) < 0 && arrA.get(posA).compareTo(buf) >= 0) {
                    Comparable a = arrA.get(posA);
                    buf = a;
                    arrC.add(a);
                    posA++;
                } else if (arrB.get(posB).compareTo(arrA.get(posA)) < 0 && arrB.get(posB).compareTo(buf) >= 0) {
                    Comparable a = arrB.get(posB);
                    buf = a;
                    arrC.add(a);
                    posB++;
                } else if (arrA.get(posA).compareTo(arrB.get(posB)) == 0 && arrA.get(posA).compareTo(buf) >= 0) {
                    Comparable a = arrA.get(posA);
                    buf = a;
                    arrC.add(a);
                    posA++;
                }
            }
        } else {
            for (int i = 0; i < lengthOutArr; i++) {
                if (arrB.size() != 0 && arrB.size() != posB && arrB.get(posB).compareTo(buf) > 0) {
                    posB++;
                    System.out.println("Unsorted element! Skip!");
                    continue;
                } else if (arrA.size() != 0 && arrA.size() != posA && arrA.get(posA).compareTo(buf) > 0) {
                    posA++;
                    System.out.println("Unsorted element! Skip!");
                    continue;
                }
                if (posA >= arrA.size() && arrB.get(posB).compareTo(buf) <= 0) {
                    Comparable a = arrB.get(posB);
                    buf = a;
                    arrC.add(a);
                    posB++;
                } else if (posB >= arrB.size() && arrA.get(posA).compareTo(buf) <= 0) {
                    Comparable a = arrA.get(posA);
                    buf = a;
                    arrC.add(a);
                    posA++;
                } else if (arrA.get(posA).compareTo(arrB.get(posB)) > 0 && arrA.get(posA).compareTo(buf) <= 0) {
                    Comparable a = arrA.get(posA);
                    buf = a;
                    arrC.add(a);
                    posA++;
                } else if (arrB.get(posB).compareTo(arrA.get(posA)) > 0 && arrB.get(posB).compareTo(buf) <= 0) {
                    Comparable a = arrB.get(posB);
                    buf = a;
                    arrC.add(a);
                    posB++;
                } else if (arrA.get(posA).compareTo(arrB.get(posB)) == 0 && arrA.get(posA).compareTo(buf) <= 0) {
                    Comparable a = arrA.get(posA);
                    buf = a;
                    arrC.add(a);
                    posA++;
                }
            }
        }
        arrOut = arrC;
        return merge(sort, type, arraysIn);
    }


    private static void writeOutFile(File outFile, ArrayList lines) {
        if(lines.size() == 0) return;
        try (BufferedWriter fbw = new BufferedWriter(new FileWriter(outFile, true))) {
            for (int i = 0; i < lines.size(); i++) {
                fbw.write(lines.get(i).toString() + "\n");
            }
        } catch (FileNotFoundException err) {
            System.out.println("Файл" + outFile.getName() + "не найден" + '\n' + err.toString());
        } catch (IOException err) {
            System.out.println("Ошибка записи данных!" + '\n' + err.toString());
        }
    }


    private static File createOutFile(String fileName){
        try {
            File outFile = new File (fileName);
            if (outFile.isFile()){
                FileWriter fw = new FileWriter(fileName);
                fw.close();
                return outFile;
            }
            if (outFile.createNewFile()) return outFile;
        } catch (IOException err) {
            System.out.println("Файл" + fileName + "не найден" + '\n' + err.toString());
        }
        return null;
    }


    private static void reWriteTmpFile(String fileName,
                                       ArrayList<Comparable> tmp,
                                       int size) {
        try (BufferedWriter fbw = new BufferedWriter(new FileWriter(fileName));){
            for (int i = size; i < tmp.size(); i++) {
                fbw.write(tmp.get(i).toString() + "\n");
            }
            int h = tmp.size(), j = 0;
            for (int i = size; i < h; i++) {
                tmp.remove(h-1-j);
                j++;
            }
        } catch (FileNotFoundException err) {
            System.out.println("Файл" + fileName + "не найден" + '\n' + err);
        } catch (IOException err) {
            System.out.println("Ошибка записи данных!" + '\n' + err.toString());
        }
    }


    private static long sizeOfTempBlocks(long sizeofFile, int  maxTmpFiles, long maxMemory) {
        long blocksize = sizeofFile / maxTmpFiles + (sizeofFile % maxTmpFiles == 0 ? 0 : 1);
        if (blocksize < maxMemory / 2) blocksize = maxMemory / 2;
        return blocksize;
    }


    private static int overHead(){
        int objHeader;      /*заголовок объекта*/
        int arrHeader;      /*заголовок массива т.к. строка массив символов*/
        int intFields = 12; /*поле типа int*/
        int multiple;       /*выравнивание для кратности пока не понятно*/
        int objRef;         /*ссылка на массив*/
        int objOverhead;
        boolean is64bitJVM = true;
        String arch = System.getProperty("sun.arch.data.model");
        if (arch != null) {
            if (arch.contains("32")) is64bitJVM = false;
        }
        objHeader = is64bitJVM ? 16 : 8;
        arrHeader = is64bitJVM ? 24 : 12;
        objRef    = is64bitJVM ? 8  : 4;
        multiple  = is64bitJVM ? 4  : 2;
        objOverhead = objHeader + intFields + multiple + objRef + arrHeader;
        return objOverhead;
    }


    private static void deleteTempFile(List<File> tmpFile) {
        int i = 0;
        while (i < tmpFile.size()){
            tmpFile.get(i).deleteOnExit();
            i++;
        }
    }


    private static File saveTempFile(List<String> tmplist,
                                     File tmpdir) {
        File newtmpfile = null;
        BufferedWriter fbw = null;
        try {
            newtmpfile = File.createTempFile("BlockTempFile", ".tmp", tmpdir);
            OutputStream out = new FileOutputStream(newtmpfile);
            fbw = new BufferedWriter(new OutputStreamWriter(out, Charset.defaultCharset()));
            for (String r : tmplist) {
                fbw.write(r);
                fbw.newLine();
            }
        } catch (FileNotFoundException err) {
            System.out.println("Файл" + newtmpfile.getName() + "не найден" + '\n' + err);
        } catch (IOException err) {
            System.out.println("Ошибка записи временного файла! " + '\n' + err);
        } finally {
            try {
                if (fbw != null) {
                    fbw.flush();
                    fbw.close();
                };
            } catch (IOException err) {
                System.out.println(err);
            }
        }
        return newtmpfile;
    }


    private static List<File> createBlockTempFile(BufferedReader fbr, long dataLength, int maxTmpFiles,
                                                  long maxMemory, File tmpdir, int objOverhead) {
        List<File> files = new ArrayList<>();
        long blockSize = sizeOfTempBlocks(dataLength, maxTmpFiles, maxMemory);
        List<String> tmpList = new ArrayList<>();
        String line = "";
        try {
            while (line != null) {
                long currentBlockSize = 0;
                while ((currentBlockSize < blockSize) && ((line = fbr.readLine()) != null)) {
                    if (line.length() != 0) {
                        tmpList.add(line);
                        currentBlockSize += (line.length() * 2) + objOverhead;
                    }
                }
                files.add(saveTempFile(tmpList, tmpdir));
                tmpList.clear();
            }
        } catch (EOFException err) {
            if (tmpList.size() > 0) {
                files.add(saveTempFile(tmpList, tmpdir));
                tmpList.clear();
            }
        } catch (IOException err) {
            System.out.println("Ошибка "  + '\n' + err);
        }
        return files;
    }


    private static long getSizeFile(String filename) {
        long sizeFile = 0;
        File outFile = new File (filename);
        sizeFile     = outFile.length();
        return sizeFile;
    }


//    private static void objMerge(ArrayList <Comparable> tmpFile,
//                                 int size,
//                                 boolean sort) {
//
//        ArrayList <Comparable> tmp = new ArrayList <Comparable>();
//        int hi = tmpFile.size();
//        int i = 0;
//        int j = size;
//        try {
//            if (checkArraySort(tmpFile, 0, size) == sort){
//                for (int k = size - 1; k >= 0; k--) tmp.add(tmpFile.get(k));
//            } else {
//                for (int k = 0; k < size; k++) tmp.add(tmpFile.get(k));
//            }
//            if (checkArraySort(tmpFile, size, hi) == sort){
//                for (int k = hi - 1; k >= size; k--) tmp.add(tmpFile.get(k));
//            } else {
//                for (int k = size; k < hi; k++)  tmp.add(tmpFile.get(k));
//            }
//            for (int k = 0; k < hi; k++) {
//                if      (i >= size)                                {tmpFile.set(k, tmp.get(j)); j++;}
//                else if (j >= hi)                                  {tmpFile.set(k, tmp.get(i)); i++;}
//                else if (objCompare(tmp.get(j), tmp.get(i), sort)) {tmpFile.set(k, tmp.get(j)); j++;}
//                else                                               {tmpFile.set(k, tmp.get(i)); i++;}
//            }
//        } catch (Exception err) {
//            System.out.println("Ошибка " + "mergefilesjava.objMerge() " + err.toString());
//        }
//    }
}

