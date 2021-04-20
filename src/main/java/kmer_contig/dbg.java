package kmer_contig;

import java.util.*;

public class dbg {
    private Set<String> kmerSet = new HashSet<>();
    private Map<String,String> contigs = new HashMap<>();
    private Set<String> done = new HashSet<>();
    private int k;
    private String[] s;
    private int contigNum = 0;
    private int graterK = 0;
    private int equalK =0;
    public dbg(int k,String[] s){
        this.k = k;
        this.s = s;
    }

    public Map<String, String> getContigs() {
        return contigs;
    }

    public void setKmerSet(Set<String> kmerSet) {
        this.kmerSet = kmerSet;
    }


    public void getKmer(String seq) {
        int len= seq.length();
        for (int i = 0; i <= len - k; i++){
            String kmer = seq.substring(i,i+k);
            this.kmerSet.add(kmer);
        }
    }

    public void  printContigs(){
        for (Map.Entry<String, String> entry : contigs.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= "
                    + entry.getValue());
        }
    }

    public Set<String> getKmerSet() {
        return kmerSet;
    }

    public void printKmer(){
        for (String kmer:kmerSet){
            System.out.println(kmer);
        }
    }

    public Set<String> fw(String km){
        Set<String> fwSet = new HashSet<>();
        String subKm = km.substring(1);
//        StringBuffer   buffer = new StringBuffer(km.substring(1));
        for (String tem: s){
            fwSet.add(subKm +tem);
//            fwSet.add(buffer.append(tem).toString());
        }
        return fwSet;
    }
    public Set<String> bw(String km){
        Set<String> bwSet = new HashSet<>();
        String subKm = km.substring(0,km.length()-1);
//        StringBuffer   buffer = new StringBuffer(km.substring(0,km.length()-1));
        for (String tem:s){
            bwSet.add(tem+subKm);
//            bwSet.add(buffer.append(tem).toString());
        }
        return bwSet;
    }
    public List<String> getContigForward(String km){
        List<String> contig = new ArrayList<>();
        contig.add(km);
//        int i =0;
        while (true){
            List<String> temKmer = new ArrayList<>();
            Set<String> fwSet = fw(km);
            for (String tem: fwSet){
                if (this.kmerSet.contains(tem)&& !this.done.contains(tem)){
                    temKmer.add(tem);
                }
                if (temKmer.size()>1)
                    break;
            }
            if (temKmer.size() ==1){
                contig.add(temKmer.get(0));
                done.add(temKmer.get(0));
            }else {
                break;
            }
            km = temKmer.get(0);
//            System.out.println(i);
//            System.out.println(km);
//            i+=1;
//            if(i ==200)
//                break;
        }
        return contig;
    }

    public List<String> getContigBackward(String km){
        List<String> contig = new ArrayList<>();
//        contig.add(km);
        while (true){
            List<String> temKmer = new ArrayList<>();
            Set<String> bwSet = bw(km);
            for (String tem: bwSet){
                if (this.kmerSet.contains(tem) && !this.done.contains(tem)){
                    temKmer.add(tem);
                }
                if (temKmer.size()>1)
                    break;
            }
            if (temKmer.size() ==1){
                contig.add(temKmer.get(0));
                done.add(temKmer.get(0));

            }else {
                break;
            }
            km = temKmer.get(0);

        }
        return contig;
    }

    public String list2String(List<String> list){
        String str = list.get(0);
//        list.remove(0);
//        for (String eachKm:list){
//            str += eachKm.substring(eachKm.length()-1);
//        }
        for (int i = 1; i < list.size(); i++) {
            str += list.get(i).substring(k-1);

        }

        return str;
    }

    public void getContig(String km){
        List<String> fwContig = getContigForward(km);
        List<String> bwContig = getContigBackward(km);
        bwContig.addAll(fwContig);
        String contig = list2String(bwContig);
//        System.out.println(contig);
        for(String eachKmer: bwContig){
            contigs.put(eachKmer,contig);
        }
    }

    public void compressContigs(String km){
        List<String> fwContig = getContigForward(km);
        List<String> bwContig = getContigBackward(km);
        bwContig.addAll(fwContig);
        String contig = list2String(bwContig);
//        System.out.println(contig);
        if (contig.length() > this.k){
            this.graterK +=1;
        }else {
            this.equalK +=1;
        }
        this.contigNum +=1;
    }


    public void compressAllContigs(){


        for (String kmer:kmerSet){
//            System.out.println(done.size());
            if (!done.contains(kmer)){

                compressContigs(kmer);
                done.add(kmer);
//                System.out.println(done.size());
            }
        }
    }
//



    public void allContigs(){

//        while (!kmerSet.isEmpty()) {
//            Iterator<String> kmeriter = kmerSet.iterator();
//            String startkmer = kmeriter.next();
//            kmerSet.remove(startkmer);
//            getContig(startkmer);
//        }
        for (String kmer:kmerSet){
//            System.out.println(done.size());
            if (!done.contains(kmer)){

//                System.out.println(kmer);
//                String kmer = "GTDHAQLQLVLQQQQPTTSDGLGVTSTSNS";
                getContig(kmer);
                done.add(kmer);
//                System.out.println(done.size());
            }
        }
    }

    public int getContigNum() {
        return contigNum;
    }

    public int getGraterK() {
        return graterK;
    }

    public int getEqualK() {
        return equalK;
    }
//
}