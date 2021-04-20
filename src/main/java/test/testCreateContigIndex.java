package test;

import kmer_contig.CreateContigIndex;

import java.io.IOException;

public class testCreateContigIndex {
    public static void main(String[] args) throws IOException {
        CreateContigIndex foo = new CreateContigIndex(3,
                "/home/yangfang/PPFeature/kmer_profile/test_create/",
                "/home/yangfang/PPFeature/kmer_profile/out/idx3.txt",
                3,
                6,
                "AA");
        foo.create();
    }
}
