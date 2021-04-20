package kmer_contig;

import io.FileInput;
import io.FileOutput;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CreateSpeciesTable {

    private String contigPath;
    private String speFilePath;
    private String speContigIndexPath;
    private Map<String, List<Integer>> contigTable;
    private List<String> speName = new ArrayList<>();
    private String model;
    private int speNum = 1;
    private boolean[][] profileMatrix;
    private List<String> allContigs;
    //    private SeqContig foo;
    public CreateSpeciesTable(String contigPath,
                              String speFilePath,
                              String speContigIndexPath,
                              String model,
                              int speNum) {
        this.contigPath = contigPath;
        this.speFilePath = speFilePath;
        this.speContigIndexPath = speContigIndexPath;
        this.model = model;
        this.speNum = speNum;
        File file = new File(speContigIndexPath);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
//        this.foo = new SeqContig();
    }

    public void readContig(String path) {
//        Map<String, List<Integer>> contigTable = FileInput.readContig(path);
        List<String> allContigs = FileInput.readContig(path);
        this.profileMatrix = new boolean[allContigs.size()][speNum];
        this.allContigs = allContigs;
    }


    private void exactMatch(Set<String> contigs,int speIndex) {


        for (int i = 0,len = allContigs.size(); i < len; i++) {

            if (contigs.contains(allContigs.get(i))){
                this.profileMatrix[i][speIndex] = true;
            }
        }
    }

    public void createSpeEach(int k) throws IOException {
        File[] files = FileInput.getFiles(contigPath);
        for (int i = 0; i < files.length; i++) {
            System.out.println("Start " + files[i].getAbsolutePath());

            readContig(files[i].getAbsolutePath());

            createSpe(k);
            String w_path = speContigIndexPath + files[i].getName();
            writeSpeTable(w_path);

        }


    }

    public void createOne(int k) throws IOException {
        File file = new File(contigPath);
        System.out.println("Start " + file.getAbsolutePath());
        readContig(contigPath);

        createSpe(k);
        String w_path = speContigIndexPath + file.getName();
        writeSpeTable(w_path);

    }

    public void createSpe(int k) {

        File[] files = FileInput.getFiles(speFilePath);

        for (int i = 0; i < files.length; i++) {
            SeqContig foo = new SeqContig(this.model);
            System.out.println(String.valueOf(i) + ": " + files[i].getName());
            String name = files[i].getName();

            String subName = name.substring(0, name.length()-6);
            this.speName.add(subName);

            foo.contigTable(files[i].getAbsolutePath(), k);


            Set<String> contigs = foo.getContigs();

            exactMatch(contigs,i);

            foo = null;
        }


    }



    public void writeSpeTable(String writePath) throws IOException {
        FileOutput file = new FileOutput(writePath);
        file.writeSpeTable(this.allContigs,this.profileMatrix);
    }

    public void writeSpeName(String speNamePath) throws IOException {
        FileOutput file = new FileOutput(speNamePath);
        file.writeSpeName(this.speName);
    }


}
