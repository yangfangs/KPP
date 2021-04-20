package kmer_contig;

import java.io.*;
import java.util.*;

import io.FileInput;
import utils.TransAA;

public class contig {
    private int k;
    private String spePath;
    private int maxIter;
    private int seqNum = 0;
    private String model;
    private String[] s;

    public contig(int k, String spePath, int maxIter, String model) {
        this.k = k;
        this.spePath = spePath;
        this.maxIter = maxIter;
        this.model = model;
    }

    private void searchContigs(Set<String> allkmer) {
        if (model.equals("AA")) {
            this.s = new String[]{"A", "C", "D", "E", "F",
                    "G", "H", "I", "K", "L",
                    "M", "N", "P", "Q", "R",
                    "S", "T", "V", "W", "Y"};
        } else if (model.equals("HY")) {
            this.s = new String[]{"H", "Y"};
        } else if (model.equals("PO")) {
            this.s = new String[]{"P", "O"};
        } else if (model.equals("CH")) {
            this.s = new String[]{"C", "H"};
        } else if (model.equals("CHP")) {
            this.s = new String[]{"C", "H", "P"};
        }

        dbg foo = new dbg(k, s);
//        for (String tem : allSeq) {
//            foo.getKmer(tem);
//        }
        foo.setKmerSet(allkmer);
//        Set<String> kmers = foo.getKmerSet();
        System.out.print(String.valueOf(allkmer.size()) + ",");
//        long startTime = System.currentTimeMillis();
//        foo.allContigs();
        foo.compressAllContigs();
//        Map<String, String> contigs = foo.getContigs();
//        Set<String> tem = new HashSet<>();
//        for (Map.Entry<String, String> entry : contigs.entrySet()) {
//            tem.add(entry.getValue());
//        }

        System.out.print(String.valueOf(foo.getEqualK()) + ",");
        System.out.print(String.valueOf(foo.getGraterK()) + ",");
        System.out.println(String.valueOf(foo.getContigNum()));

//        System.out.println(String.valueOf(allkmer.size()));
//        long endTime = System.currentTimeMillis();

//        foo.printContigs();
//        System.out.println("Time:" + (endTime - startTime) / 1000);


    }


    public void printCompress() {
        File[] files = FileInput.getFiles(spePath);
        for (int i = 1; i <= maxIter; i++) {
            int seqNum = 0;
            System.out.print(String.valueOf(i) + ",");
            Set<String> kmerSet = new HashSet<>();
//            System.out.println(allSeq.size());

            for (int j = 0; j < i; j++) {

                List<String> allSeq;
//                System.out.println(files[j].getName());
                allSeq = FileInput.read(files[j].getAbsolutePath());
                seqNum += allSeq.size();

                for (String tem : allSeq) {
                    String transSeq = null;
                    if (model.equals("AA")) {
                        transSeq = tem;
                    } else if (model.equals("HY")) {
                        transSeq = TransAA.tarnsToHY(tem);
                    } else if (model.equals("PO")) {
                        transSeq = TransAA.tarnsToPO(tem);
                    }else if (model.equals("CH")) {
                        transSeq =TransAA.tarnsToCH(tem);
                    } else if (model.equals("CHP")) {
                        transSeq = TransAA.tarnsToCHP(tem);
                    }
                    int len = transSeq.length();
                    for (int ii = 0; ii <= len - k; ii++) {
                        String kmer = transSeq.substring(ii, ii + k);
                        kmerSet.add(kmer);
                    }
                }
            }

            System.out.print(String.valueOf(seqNum) + ",");
//            System.out.println(allSeq.size());
            searchContigs(kmerSet);
        }


    }


}
