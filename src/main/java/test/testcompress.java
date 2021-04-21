package test;

import io.FileInput;
import kmer_contig.dbg;

import java.io.File;
import java.util.*;

public class testcompress {


    public static void main(String[] args) {
        String filePath = "wwww";
        String[] s = {"A", "C", "D", "E", "F",
                "G", "H", "I", "K", "L",
                "M", "N", "P", "Q", "R",
                "S", "T", "V", "W", "Y"};
        dbg foo = new dbg(6, s);
//        List<String> filelist = read("/home/yangfang/PPFeature/test2.fasta");
        List<String> filelist = FileInput.read("/home/yangfang/PPFeature/kmer_profile/abb_seqs/acan.fasta");
//        List<String> filelist = read("/home/yangfang/PPFeature/kmer_profile/test.txt");
//        System.out.println(filelist.toString());
        List<String> allSeq = new ArrayList<>();
        File[] files = FileInput.getFiles("/home/yangfang/PPFeature/kmer_profile/abb_seqs/");

        for (int i = 0; i < 4; i++) {
            System.out.println(files[i].getName());
            allSeq.addAll(FileInput.read(files[i].getAbsolutePath()));

        }

        for (String tem : allSeq) {
            foo.getKmer(tem);
        }
        Set<String> kmers = foo.getKmerSet();

        System.out.println(kmers.size());
        long startTime = System.currentTimeMillis();
        foo.allContigs();
        Map<String, String> contigs = foo.getContigs();
        Set<String> tem = new HashSet<>();
        for (Map.Entry<String, String> entry : contigs.entrySet()) {
            tem.add(entry.getValue());
        }
        System.out.println(tem.size());
        long endTime = System.currentTimeMillis();

//        foo.printContigs();
        System.out.println("Used time:" + (endTime - startTime) / 1000);
    }

}
