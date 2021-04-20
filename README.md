# **KPP: constructing phylogenetic profile and extracting feature for the prediction of PHI.**

# Step1: contig index table.

## Method:
* Index: build contig index table by kmer set.
 
### options
  
  | option |  Description                                                                                                                            |
  |:------- |:---------------------------------------------------------------------------------------------------------------------------------------|
  |  -i     |  Species AA file path.                                                                                                         |
  |  -o     |  Setting store path.                                                                                                               |
  |  -k     |  setting kmer parameter value k.                                                                                                               |
  |  -s     |  Max split file number.                                                                                 |
  |  -m    |   Max add species number.                                                      |
  |  -d     |  Choose profile model (AA,CH,PO,CHP,HY)                                                              |


### Usage: 

```angular2html
java -Xmx10190m -jar KPP.jar Index -i /home/yangfang/PPFeature/kmer_profile/test_jar/seq/ -k 19 -s 1 -m 4 -o /home/yangfang/PPFeature/kmer_profile/test_hy/k19_ch_index/k19_ch_split.txt -d CH
```



# Step2: phylogenetic profile

## Method:
* Profile: Constructing phylogenetic profile by contig index table.
   
### options
    
| option |  Description                                                                                                                            |
|:------- |:---------------------------------------------------------------------------------------------------------------------------------------|
|  -i     |  Species AA file path.                                                                                                         |
|  -o     |  Setting store path.                                                                                                               |
|  -k     |  setting kmer parameter value k.                                                                                                               |
|  -c     |  The path of contig index.                                                                                 |
|  -d     |  Choose profile model (AA,CH,PO,CHP,HY)                                                              |

### Usage:

```angular2html
java -Xmx10190m -jar KPP.jar Profile -i /home/yangfang/PPFeature/kmer_profile/abb_seqs500/ -k 19 -c /home/yangfang/PPFeature/kmer_profile/test_hy/k19_ch_index/ -o /home/yangfang/PPFeature/kmer_profile/test_hy/k19_ch_profile/ -d CH

```
# Step3: extracting phylogenetic profile feature.

## Method:
* Feature: Extracting feature form phylogenetic profile.
   
### options
    
| option |  Description                                                                                                                            |
|:------- |:---------------------------------------------------------------------------------------------------------------------------------------|
|  -i     |  Species AA file path.                                                                                                         |
|  -o     |  Setting store path.                                                                                                               |
|  -k     |  setting kmer parameter value k.                                                                                                               |
|  -p     |  The profile path.                                                                                 |
|  -d     |  Choose profile model (AA,CH,PO,CHP,HY)                                                              |


### Usage:

```angular2html
java -Xmx10190m -jar KPP.jar Feature -i /home/yangfang/PPFeature/kmer_profile/test_jar/seq_all.fasta -k 19 -p /home/yangfang/PPFeature/kmer_profile/test_hy/k19_ch_profile/k19_ch_split0.txt -o /home/yangfang/PPFeature/kmer_profile/test_hy/k19_ch_feature/combine_contig.txt -d CH
```

