package test;

import kmer_contig.GetFeature;

import java.io.IOException;

public class testGetFeature {
    public static void main(String[] args) throws IOException {
        GetFeature foo = new GetFeature("/home/yangfang/PPFeature/kmer_profile/contig_idx/seq/ath.fasta",
                "/home/yangfang/PPFeature/kmer_profile/test_hy/k15_hy_profile/k15_hy_split0.txt",
                "/home/yangfang/PPFeature/kmer_profile/test_hy/k15_hy_feature_/test.txt",
                15,"HY");
        foo.createContig();
        foo.eachContig(0);
//        foo.writeFeature();
    }
}
