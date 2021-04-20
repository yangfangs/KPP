package test;

import kmer_contig.CreateContigIndex;
import kmer_contig.CreateSpeciesTable;

import java.io.IOException;

public class testCreateSpeciesTable {
    public static void main(String[] args) throws IOException {

        CreateSpeciesTable foo = new CreateSpeciesTable("/home/yangfang/PPFeature/kmer_profile/test_index/",
                "/home/yangfang/PPFeature/kmer_profile/test_java/",
                "/home/yangfang/PPFeature/kmer_profile/test_java_contig/",
                "AA",
                20);
        foo.createSpeEach(3);
    }
}
