package test;

import kmer_contig.dbg;

import java.util.HashSet;
import java.util.Set;

public class testDbg {
    public static void main(String[] args) {
        String[] s = {"A","T","C","G"};
        dbg foo = new dbg(3,s);
        String seq = "ATCGAAC";
        String seq2 = "ACCACTT";
        String seq3 = "ACCCAAT";

        foo.getKmer(seq);
//        foo.getKmer(seq2);
//        foo.getKmer(seq3);

        foo.printKmer();

        foo.allContigs();
        foo.printContigs();
    }
}
