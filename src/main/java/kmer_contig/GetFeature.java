package kmer_contig;

import io.FileInput;
import io.FileOutput;
import io.ParserSeq;
import utils.TransAA;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class GetFeature {
    private String seqPath;
    private String indexPath;
    private String writeFeaturePath;
    private Map<String,String> contigsMap;
    private Map<String,List<Integer>> feature = new HashMap<>();
    private Map<String,List<Float>> featureNor = new HashMap<>();
    private Map<String,int[]> eachIndex;
    private int k;
    private int featureSize;
    private String model;

    public GetFeature(String seqPath,
                      String indexPath,
                      String writeFeaturePath,
                      int k,
                      String model){
        this.seqPath = seqPath;
        this.indexPath = indexPath;
        this.writeFeaturePath = writeFeaturePath;
        this.k = k;
        this.model = model;
        File file = new File(this.indexPath);
        this.eachIndex = FileInput.readIndex(file.getAbsolutePath());
        for (Map.Entry<String, int[]> entry : eachIndex.entrySet()) {
            int[] tem = entry.getValue();
            this.featureSize = tem.length;
            break;
        }

    }

    public Set<String> getKmers(String seq) {
        Set<String> allKmers = new HashSet<>();
        int len= seq.length();
        for (int i = 0; i <= len - k; i++){
            String kmer = seq.substring(i,i+k);
            allKmers.add(kmer);
        }
        return allKmers;
    }
    public List<String> getKmersOrder(String seq){
        List<String> allKmers = new ArrayList<>();
        int len= seq.length();
        for (int i = 0; i <= len - k; i++){
            String kmer = seq.substring(i,i+k);
            allKmers.add(kmer);
        }
        return allKmers;
    }

    public void createContig(){
        SeqContig foo = new SeqContig(this.model);
        foo.contigTable(this.seqPath,k);
        this.contigsMap = foo.getContigsMap();
    }

    public List<String> matchContigOrder(String seqs){
        List<String> eachContigs = new ArrayList<>();
        List<String> kmerSet = getKmersOrder(seqs);
        for (String eachKmer:kmerSet){
            if (this.contigsMap.containsKey(eachKmer)) {
                if (!eachContigs.contains(this.contigsMap.get(eachKmer)))
                    eachContigs.add(this.contigsMap.get(eachKmer));
            }
        }
        return eachContigs;
    }


    public Set<String> matchContig(String seqs){
        Set<String> eachContigs = new HashSet<>();
        Set<String> kmerSet = getKmers(seqs);
        for (String eachKmer:kmerSet){
            if (this.contigsMap.containsKey(eachKmer))
                eachContigs.add(this.contigsMap.get(eachKmer));
        }
        return eachContigs;


    }

    public int count(int[] x){
        int res = 0;
        for (int i = 1; i < x.length; i++) {
            if (x[i] !=x[i-1])
                res += 1;
        }
        return res/2;

//        List<Integer> arr = new ArrayList<>();
//        int count1 =0;
//        int sum=0;
//        for (int i = 0; i < x.length; i++) {
//            sum += x[i];
//            if (x[i] ==1 ){
//                count1 +=1;
//                if(i==x.length-1)
//                    arr.add(count1);
//            }else {
//                arr.add(count1);
//                count1 = 0;
//            }
//        }
//        for (int y: arr){
//            if (y>0){
//                res +=1;
//            }
//        }
//        return res;
    }



    public int[] matchIndexTableOrder(List<String> contigs){
        int[] arr = new int[this.featureSize];
        List<int[]> match = new ArrayList<>();
//        File[] files = FileInput.getFiles(this.indexPath);

        // get feature
        for (String line:contigs){
            System.out.println("Start: " + line);
            if (eachIndex.containsKey(line)) {
                System.out.println("match: " + line);
                match.add(eachIndex.get(line));
            }
        }
        // combine feature
        if (!match.isEmpty()){
            for (int i = 0; i < arr.length; i++) {
                int[] tem = new int[match.size()];
                for (int j = 0; j < match.size(); j++) {
                    tem[j] = match.get(j)[i];
                }
                int res = count(tem);
                arr[i] = res;
            }
        }


        System.out.println(Arrays.toString(arr));
        return arr;
    }

    public int[] matchIndexTable(Set<String> contigs){
        int[] arr = new int[this.featureSize];
        List<int[]> match = new ArrayList<>();
//        File[] files = FileInput.getFiles(this.indexPath);

        // get feature
        for (String line:contigs){
            System.out.println("Start: " + line);
            if (eachIndex.containsKey(line)) {
                System.out.println("match: " + line);
                match.add(eachIndex.get(line));
            }
        }
        // combine feature
        if (!match.isEmpty()){
            for (int[] x:match){
                for (int i = 0; i < x.length; i++) {
                    arr[i] = arr[i] + x[i];

                }
            }
        }

        System.out.println(Arrays.toString(arr));
        return arr;
    }


    public int sum(int[] arr){
        int sum =0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }


    public float[] matchIndexTableFloat(Set<String> contigs){
        int count =0;
        int[] arr = new int[this.featureSize];
        List<int[]> match = new ArrayList<>();
//        File[] files = FileInput.getFiles(this.indexPath);

        // get feature
        for (String line:contigs){
            System.out.println("Start: " + line);
            if (eachIndex.containsKey(line)) {
                System.out.println("match: " + line);
                match.add(eachIndex.get(line));
                if (sum(eachIndex.get(line)) ==0)
                    count +=1;
            }
        }
//        // combine feature
//        if (!match.isEmpty()){
//            for (int[] x:match){
//                for (int i = 0; i < x.length; i++) {
//                    arr[i] = arr[i] + x[i];
//
//                }
//            }
//        }

        // combine feature
        if (!match.isEmpty()){
            for (int i = 0; i < arr.length; i++) {
                int[] tem = new int[match.size()];
                for (int j = 0; j < match.size(); j++) {
                    tem[j] = match.get(j)[i];
                }
                int res = this.count(tem);
                arr[i] = res;
            }
        }

        //normal
        int weight = contigs.size() - count;
        float [] nor = new float[arr.length];
        for (int j = 0; j < arr.length; j++) {
            nor[j] = (float) arr[j] / contigs.size();
//            if (weight - count >=0){
//                nor[j] = (float) 1;
//            }else {
//                nor[j] = (float) 0;
//            }
        }



        System.out.println(Arrays.toString(arr));
        return nor;
    }
    private List<Integer> transArrToListInt(int[] arr){
        List<Integer> tem = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            tem.add(arr[i]);

        }
        return tem;
    }
    private List<Float> transArrToListFloat(float[] arr){
        List<Float> tem = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            tem.add(arr[i]);

        }
        return tem;
    }

    public void eachContig(int w) throws IOException {
        ParserSeq seq = new ParserSeq(this.seqPath);
        for (int i = 0; i < seq.size(); i++) {
            String seqName = seq.getDescription(i).substring(1);
            String seqs=null;
            if (model.equals("AA")){
                seqs = seq.getSequence(i);
            } else if (model.equals("HY")){
                seqs = TransAA.tarnsToHY(seq.getSequence(i));
            }else if (model.equals("PO")){
                seqs = TransAA.tarnsToPO(seq.getSequence(i));
            }else if (model.equals("CH")){
                seqs = TransAA.tarnsToCH(seq.getSequence(i));
            }else if (model.equals("CHP")){
                seqs = TransAA.tarnsToCHP(seq.getSequence(i));
            }

//            Set<String> contigs = getKmers(seqs);

//            for (String con:contigs){
//                System.out.println(con);
//            }

            if (w == 1) {
                Set<String> contigs = matchContig(seqs);

                float[] match = matchIndexTableFloat(contigs);
                this.featureNor.put(seqName, transArrToListFloat(match));
                System.out.println(seqName + " contains: " + String.valueOf(contigs.size()) + " contigs");
                System.out.println(Arrays.toString(match));
            } else {

//                Set<String> contigs = matchContig(seqs);
//                int[] match = matchIndexTable(contigs);

                List<String> contigs = matchContigOrder(seqs);
                int[] match = matchIndexTableOrder(contigs);

                System.out.println(seqName + " contains: " + String.valueOf(contigs.size()) + " contigs");
                this.feature.put(seqName,transArrToListInt(match));
                System.out.println(Arrays.toString(match));
            }


//            this.feature.put(seqName,transArrToList(match));
        }
        if (w==1){
            writeFeatureContinue();
        }else {
            writeFeature();
        }
        
    }

    public void writeFeatureContinue() throws IOException {
        FileOutput out = new FileOutput(this.writeFeaturePath);
        out.writeSpeTableContinue(this.featureNor);
    }


    public void writeFeature() throws IOException {
        FileOutput out = new FileOutput(this.writeFeaturePath);
        out.writeSpeTable2(this.feature);
        
    }


}
