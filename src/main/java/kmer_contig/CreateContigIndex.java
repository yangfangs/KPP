package kmer_contig;

import io.FileInput;
import io.FileOutput;
import utils.TransAA;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class CreateContigIndex {
    private String spePath;
    private String outFilePath;
    private int k;
    private int max;
    private int splitNum;
    private String model;
    private String[] s;

    public CreateContigIndex(int k,
                             String spePath,
                             String outFilePath,
                             int splitNum,
                             int max,
                             String model) {
        this.k = k;
        this.spePath = spePath;
        this.outFilePath = outFilePath;
        this.splitNum = splitNum;
        this.max = max;
        this.model = model;

        if (model.equals("AA")) {
            this.s = new String[]{"A", "C", "D", "E", "F",
                    "G", "H", "I", "K", "L",
                    "M", "N", "P", "Q", "R",
                    "S", "T", "V", "W", "Y"};
        } else if (model.equals("HY")) {
            this.s = new String[]{"H", "Y"};
        } else if (model.equals("PO")) {
            this.s = new String[]{"P", "O"};
        }else if (model.equals("CH")) {
            this.s = new String[]{"C", "H"};
        }else if (model.equals("CHP")) {
            this.s = new String[]{"C", "H", "P"};
        }
    }

    public List<String> readSeqs() {
        List<String> allSeq = new ArrayList<>();
        File[] files = FileInput.getFiles(spePath);

        if (model.equals("AA")) {
            for (int i = 0; i < max; i++) {
                System.out.println(files[i].getName());
                allSeq.addAll(FileInput.read(files[i].getAbsolutePath()));
            }
        } else if (model.equals("HY")) {
            for (int i = 0; i < max; i++) {
                System.out.println(files[i].getName());
                List<String> seqs = FileInput.read(files[i].getAbsolutePath());
                for (String tem : seqs) {
                    allSeq.add(TransAA.tarnsToHY(tem));
                }
            }
        }else if (model.equals("PO")) {
            for (int i = 0; i < max; i++) {
                System.out.println(files[i].getName());
                List<String> seqs = FileInput.read(files[i].getAbsolutePath());
                for (String tem : seqs) {
                    allSeq.add(TransAA.tarnsToPO(tem));
                }
            }
        }else if (model.equals("CH")) {
            for (int i = 0; i < max; i++) {
                System.out.println(files[i].getName());
                List<String> seqs = FileInput.read(files[i].getAbsolutePath());
                for (String tem : seqs) {
                    allSeq.add(TransAA.tarnsToCH(tem));
                }
            }
        }else if (model.equals("CHP")) {
            for (int i = 0; i < max; i++) {
                System.out.println(files[i].getName());
                List<String> seqs = FileInput.read(files[i].getAbsolutePath());
                for (String tem : seqs) {
                    allSeq.add(TransAA.tarnsToCHP(tem));
                }
            }
        }

        return allSeq;
    }


    public void create() throws IOException {

        dbg foo = new dbg(k, s);

        List<String> allSeq = readSeqs();
        for (String tem : allSeq) {
            foo.getKmer(tem);
        }
        Set<String> kmers = foo.getKmerSet();

        System.out.println("Kmers: " + String.valueOf(kmers.size()));

        long startTime = System.currentTimeMillis();
        foo.allContigs();
        Map<String, String> contigs = foo.getContigs();
        Set<String> tem = new HashSet<>();
        for (Map.Entry<String, String> entry : contigs.entrySet()) {
            tem.add(entry.getValue());
        }
        FileOutput write = new FileOutput(outFilePath);
        write.writeContigSplit(tem, splitNum);
        System.out.println("Contigs: " + String.valueOf(tem.size()));

        long endTime = System.currentTimeMillis();


        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        System.out.println("Used time: " + decimalFormat.format((endTime - startTime) / (float)1000) + "S");
        System.out.println();


    }
}