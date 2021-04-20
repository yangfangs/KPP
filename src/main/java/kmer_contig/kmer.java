package kmer_contig;

public class kmer {

    private String kmerStr;
    private String contigStr;

    public void kmer(String kmerStr,String contigStr){
        this.kmerStr = kmerStr;
        this.contigStr = contigStr;
    }

    public String getKmerStr() {
        return kmerStr;
    }

    public String getContigStr() {
        return contigStr;
    }
}
