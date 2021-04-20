package io;

import sun.java2d.pipe.BufferedRenderPipe;


import java.io.*;
import java.util.*;

public class FileInput {
    private int FeatureSize;


    public static List<String> read(String file) {
        List seq = new ArrayList();
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));

            StringBuffer buffer = new StringBuffer();
            String line = in.readLine();


            if (line.charAt(0) != '>')
                throw new IOException("First line of " + file + " should start with '>'");

            for (line = in.readLine().trim(); line != null; line = in.readLine()) {
                if (line.length() > 0 && line.charAt(0) == '>') {
                    seq.add(buffer.toString());
                    buffer = new StringBuffer();
                } else
                    buffer.append(line.trim());
            }
            if (buffer.length() != 0)
                seq.add(buffer.toString());
        } catch (FileNotFoundException e) {
            System.out.println("Error when reading " + file);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error when reading " + file);
            e.printStackTrace();
        }
        return seq;

    }


    public static List<String> readContig(String filePath) {
        List<String> contigs = new ArrayList<>();

        try {
            BufferedReader in = new BufferedReader(new FileReader(filePath));
            String str;

            while ((str = in.readLine()) != null) {

                contigs.add(str.trim());

//                System.out.println(Arrays.toString(arr));
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contigs;

    }


    public static File[] getFiles(String path) {
        File file = new File(path);
        File[] files = new File[0];
        if (file.isDirectory()) {
            files = file.listFiles();

        }
        return files;
    }

    public static Map<String, int[]> readIndex(String path) {
        Map<String, int[]> map = new HashMap<>();

        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            String str;


            while ((str = in.readLine()) != null) {

                String[] tem = str.trim().split("\t");

                String[] feature = tem[1].split(",");

                int[] featureNum = new int[feature.length];
//                System.out.println(feature.length);
                for (int i = 0; i < feature.length; i++) {
                    featureNum[i] = Integer.parseInt(feature[i]);
                }
//                System.out.println(tem[0]);
//                System.out.println(Arrays.toString(featureNum));
                map.put(tem[0], featureNum);
//                System.out.println(Arrays.toString(arr));

            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static Map<String, Long> readSimHashTable(String path) {
        Map<String, Long> map = new HashMap<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            String str;

            while ((str = in.readLine()) != null) {

                String[] tem = str.trim().split("\t");
                String speName = tem[0];
                Long fingerPrint = Long.parseLong(tem[1]);
                map.put(speName, fingerPrint);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    return map;
    }

    public static List<String> readPairs(String path){
        List<String> dbModels = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            String str;

            while ((str = in.readLine()) != null) {

                String tem = str.trim();

                dbModels.add(tem);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dbModels;


    }

}
