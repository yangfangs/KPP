package test;

import kmer_contig.contig;

public class testcontig {
    public static void main(String[] args) {
        contig foo =  new contig(6,"/home/yangfang/PPFeature/kmer_profile/test_java2/",4,"AA");
        foo.printCompress();
    }
}
