package test;

import io.FileInput;
import kmer_contig.SeqContig;

import java.io.File;
import java.util.Set;

public class testSeqContig {

    public static void main(String[] args) throws InterruptedException {
        File[] files = FileInput.getFiles("/home/yangfang/PPFeature/kmer_profile/test_seq2/");
        SeqContig foo = new SeqContig("AA");
        int k=6;
        for (int i = 0; i < files.length; i++) {

            System.out.println(String.valueOf(i) + ": " + files[i].getName());
            foo.contigTable(files[i].getAbsolutePath(),k);
            Set<String> contigs = foo.getContigs();
//            for (int j = 10; j>=0; j--) {
//                Thread.sleep(1000);  //1000毫秒就是1秒
//                System.out.println(j);
//            }
//            k -=3;
        }

//        File[] files2 = FileInput.getFiles("/home/yangfang/PPFeature/kmer_profile/test_seq/");
//
//        for (int i = 0; i < files2.length; i++) {
//            SeqContig foo2 = new SeqContig("AA");
//            System.out.println(String.valueOf(i) + ": " + files2[i].getName());
//            foo2.contigTable(files2[i].getAbsolutePath(),5);
//            Set<String> contigs = foo2.getContigs();
//
//        }
    }

}
