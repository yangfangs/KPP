package kmer_contig;

import io.FileInput;
import utils.TransAA;

import java.text.DecimalFormat;
import java.util.*;

public class SeqContig {

    private Set<String> contigs;
    private Map<String, String> contigsMap;
    private String model;
    private String[] s;

    public SeqContig(String model) {
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
        } else if (model.equals("CH")) {
            this.s = new String[]{"C", "H"};
        } else if (model.equals("CHP")) {
            this.s = new String[]{"C", "H", "P"};
        }
    }


    public Set<String> getContigs() {
        return contigs;
    }

    public Map<String, String> getContigsMap() {
        return contigsMap;
    }


    public void contigTable(String speFile, int k) {
        List<String> seqs = new ArrayList<>();
        dbg foo = new dbg(k, s);
        List<String> filelist = FileInput.read(speFile);

        if (model.equals("AA")) {
            seqs = filelist;
        } else if (model.equals("HY")) {
            for (String x : filelist) {
                seqs.add(TransAA.tarnsToHY(x));
            }
        }else if (model.equals("PO")) {
            for (String x : filelist) {
                seqs.add(TransAA.tarnsToPO(x));
            }
        }else if (model.equals("CH")) {
            for (String x : filelist) {
                seqs.add(TransAA.tarnsToCH(x));
            }
        }else if (model.equals("CHP")) {
            for (String x : filelist) {
                seqs.add(TransAA.tarnsToCHP(x));
            }
        }

        Set<String> kmerSet = new HashSet<>();

        for (String tem : seqs) {
            int len= tem.length();
            for (int i = 0; i <= len - k; i++){
                String kmer = tem.substring(i,i+k);
                kmerSet.add(kmer);
            }
        }
        foo.setKmerSet(kmerSet);

//        Set<String> kmers = foo.getKmerSet();
        System.out.println("Founding kerms: " + kmerSet.size());
        long startTime = System.currentTimeMillis();
        foo.allContigs();
        Map<String, String> contigs = foo.getContigs();
        this.contigsMap = contigs;
        Set<String> tem = new HashSet<>();
//        for (Map.Entry<String, String> entry : contigs.entrySet()) {
//            contigsMapFuzzy.putFuzzy(entry.getKey(), entry.getValue());
//            tem.add(entry.getValue());
//        }

        for (Map.Entry<String, String> entry : contigs.entrySet()) {
            tem.add(entry.getValue());
        }

        this.contigs = tem;


        System.out.println("Searching and compressing to contigs: " + tem.size());
        long endTime = System.currentTimeMillis();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        System.out.println("Used time: " + decimalFormat.format((endTime - startTime) / (float)1000) + "S");
        System.out.println("============================================");

    }

}
