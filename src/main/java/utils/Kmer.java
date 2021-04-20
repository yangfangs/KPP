package utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Kmer {
    public static List<String> kmerList(String seq,int k){
        List<String> tem = new ArrayList<>();
        int len = seq.length();
        for (int ii = 0; ii <= len - k; ii++) {
            String kmer = seq.substring(ii, ii + k);
            tem.add(kmer);
        }
        return tem;
    }

    public static Set<String> kmerSet(String seq, int k){
        Set<String> tem = new HashSet<>();
        int len = seq.length();
        for (int ii = 0; ii <= len - k; ii++) {
            String kmer = seq.substring(ii, ii + k);
            tem.add(kmer);
        }
        return tem;
    }
}
