package io;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class FileOutput {
    private String fileOutputPath;

    public FileOutput(String fileOutputPath) {
        this.fileOutputPath = fileOutputPath;
    }

    public void writeContig(Set<String> contigs) throws IOException {
        File file = new File(this.fileOutputPath);
        if (!file.isFile()) {
            file.createNewFile();

        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileOutputPath));

        for (String line : contigs) {
            writer.write(line + "\n");

        }
        writer.close();

    }

    public void writeContigSplit(Set<String> contigs, int splitNum) throws IOException {
        File file = new File(this.fileOutputPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();

        }

        String[] tem2 = file.getName().split("\\.");
        String name = tem2[0];


        List<String> contigsList = new ArrayList<>();
        List<Integer> allIndex = new ArrayList<>();
        // trans set to list
        for (String line : contigs) {
            contigsList.add(line);
        }
        int length = contigsList.size();
        int tl = length % splitNum == 0 ? length / splitNum : (length
                / splitNum + 1);
        for (int i = 0; i < length; i++) {
            allIndex.add(i);
        }
        for (int i = 0; i < splitNum; i++) {
            String w_files = file.getParent() + "/" + name + String.valueOf(i) + ".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(w_files));
            int end = (i + 1) * tl;
            final List<Integer> list1 = allIndex.subList(i * tl, end > length ? length : end);
            for (int j : list1) {
                writer.write(contigsList.get(j) + "\n");
            }
            writer.close();
        }

    }

    private static String aryToString(List<Integer> ary) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ary.size(); i++) {
            sb.append(ary.get(i)).append(",");
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    private static String aryToString2(List<Float> ary) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ary.size(); i++) {
            sb.append(ary.get(i)).append(",");
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    private String listWithCommon(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public void writeSpeName(List<String> speName) throws IOException {
        File file = new File(this.fileOutputPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();

        }
        if (!file.isFile()) {
            file.createNewFile();

        }
        String str = listWithCommon(speName);

        BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileOutputPath));
        writer.write("Name" + "\t" + str);
        writer.close();
    }


    public void writeSpeTable2(Map<String, List<Integer>> speTable) throws IOException {
        File file = new File(this.fileOutputPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();

        }
        if (!file.isFile()) {
            file.createNewFile();

        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileOutputPath));

        for (Map.Entry<String, List<Integer>> entry : speTable.entrySet()) {
            writer.write(entry.getKey() + "\t" + aryToString(entry.getValue()) + "\n");

        }
        writer.close();
    }


    public void writeSpeTable(List<String> allContigs, boolean[][] profileMatrix) throws IOException {
        File file = new File(this.fileOutputPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();

        }
        if (!file.isFile()) {
            file.createNewFile();

        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileOutputPath));

        for (int i = 0, len = allContigs.size(); i < len; i++) {
            List<Integer> tem = new ArrayList<>();
            for (int j = 0; j < profileMatrix[i].length; j++) {
                tem.add(profileMatrix[i][j] ? 1 : 0);
            }

            writer.write(allContigs.get(i) + "\t" + aryToString(tem) + "\n");
        }

//        for (Map.Entry<String,List<Integer>> entry: speTable.entrySet()){
//            writer.write(entry.getKey()+"\t" + aryToString(entry.getValue()) +"\n");
//
//        }
        writer.close();
    }

    public void writeSpeTableContinue(Map<String, List<Float>> speTable) throws IOException {
        File file = new File(this.fileOutputPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();

        }
        if (!file.isFile()) {
            file.createNewFile();

        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileOutputPath));

        for (Map.Entry<String, List<Float>> entry : speTable.entrySet()) {
            writer.write(entry.getKey() + "\t" + aryToString2(entry.getValue()) + "\n");

        }
        writer.close();
    }

    public void writeSimHashTable(Map<String,Long> speSimHashTable) throws IOException {
        File file = new File(this.fileOutputPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();

        }
        if (!file.isFile()) {
            file.createNewFile();

        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileOutputPath));

        for (Map.Entry<String, Long> entry : speSimHashTable.entrySet()) {
            writer.write(entry.getKey() + "\t" + String.valueOf(entry.getValue()) + "\n");
        }
        writer.close();
    }
    public void writeSimResultTable(Map<String,Integer> speSimHashTable) throws IOException {
        File file = new File(this.fileOutputPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();

        }
        if (!file.isFile()) {
            file.createNewFile();

        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileOutputPath));
        List<Map.Entry<String, Integer>> list = new ArrayList<>(speSimHashTable.entrySet());
        Collections.sort(list, Comparator.comparing(Map.Entry::getValue));
//        Collections.sort(list, (o2, o1) -> o1.getValue().compareTo(o2.getValue()));
        for (Map.Entry<String, Integer> entry : list) {
            writer.write(entry.getKey() + "\t" + String.valueOf(entry.getValue()) + "\n");
        }
        writer.close();
    }

    public void writeSimResultTableDouble(Map<String,Double> speSimHashTable) throws IOException {
        File file = new File(this.fileOutputPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();

        }
        if (!file.isFile()) {
            file.createNewFile();

        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileOutputPath));
        List<Map.Entry<String, Double>> list = new ArrayList<>(speSimHashTable.entrySet());
        Collections.sort(list, (o2, o1) -> o1.getValue().compareTo(o2.getValue()));
        for (Map.Entry<String, Double> entry : list) {
            writer.write(entry.getKey() + "\t" + String.valueOf(entry.getValue()) + "\n");
        }
        writer.close();
    }
    public void writeSimResultTableFloat(Map<String,Float> speSimHashTable) throws IOException {
        File file = new File(this.fileOutputPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();

        }
        if (!file.isFile()) {
            file.createNewFile();

        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileOutputPath));
        List<Map.Entry<String, Float>> list = new ArrayList<>(speSimHashTable.entrySet());
        Collections.sort(list, (o2, o1) -> o1.getValue().compareTo(o2.getValue()));
        DecimalFormat decimalFormat=new DecimalFormat("0.00");

        for (Map.Entry<String, Float> entry : list) {
            writer.write(entry.getKey() + "\t" + decimalFormat.format(entry.getValue()) + "\n");
        }
        writer.close();
    }
    }

