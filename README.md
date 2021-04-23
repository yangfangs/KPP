# **KPP: constructing phylogenetic profile and extracting feature for the prediction of PHI.**
The KPP (Kmer Phylogenetic Profile) is a new algorithm for constructing phylogenetic profile by compressing sequences to contig and generating evolutionary information features. 
Using this feature or concatenating other features with the ML framework for the prediction of PHI (pathogen-host interaction).

# The KPP algorithm and ML framework:

![Workflow](https://github.com/yangfangs/KPP/blob/master/algorithm/KPP.png)

# Step1: contig index table.

## Method:
* Index: build contig index table by kmer set.
 
### options

  | option  |  Description                                                                                             |
  |:------- |:---------------------------------------------------------------------------------------------------------|
  |  -i     |  The AA file path of species proteome.                                                                   |
  |  -o     |  Setting output file path.                                                                               |
  |  -k     |  Setting the parameter of kmer value (kmer length).                                                      |
  |  -s     |  Max split file number, default is 1 (parameter for large proteome).                                     |
  |  -m     |  Max add species number, default is 1 (equal input proteome).                                            |
  |  -d     |  Choose profile model, default is AA(AA, CH, PO, CHP, HY).                                               |


### Usage: 

```angular2html
$ java -Xmx10190m -jar KPP.jar Index -i protein -k 19 -s 1 -m 4 -o k19_ch_index.txt -d CH
```



# Step2: phylogenetic profile

## Method:
* Profile: Constructing phylogenetic profile by contig index table.
   
### options
    
| option |  Description                                                                                                |
|:------- |:-----------------------------------------------------------------------------------------------------------|
|  -i     |  The species proteome for constructing phylogenetic profile.                                               |
|  -o     |  Setting output file path.                                                                                 |
|  -k     |  Setting the parameter of kmer value (kmer length).                                                        |
|  -c     |  The path of contig index.                                                                                 |
|  -d     |  Choose profile model, default is AA(AA, CH, PO, CHP, HY).                                                 |

### Usage:

```angular2html
$ java -Xmx10190m -jar KPP.jar Profile -i abb_species_503 -c Index -o Profile -k 19 -n 503 -d CH

```
# Step3: extracting phylogenetic profile feature.

## Method:
* Feature: Extracting feature form phylogenetic profile.
   
### options
    
| option |  Description                                                                                                |
|:------- |:-----------------------------------------------------------------------------------------------------------|
|  -i     |  The species proteome for extract feature.                                                                 |
|  -o     |  Setting output file path.                                                                                 |
|  -k     |  Setting the parameter of kmer value (kmer length).                                                        |
|  -p     |  The phylogenetic profile path.                                                                            |
|  -d     |  Choose profile model, default is AA(AA, CH, PO, CHP, HY).                                                 |


### Usage:

```angular2html
$ java -Xmx10190m -jar KPP.jar Feature -i ath_psy_hpa_gor_sequence.fasta -k 19 -p Profile_k19_ch_index0.txt -o KPP_feature.txt -d CH
```

# Example

* Here we provide an example about using KPP algorithm create contig index, construct phylogenetic profile and extract feature by command line.

## Download test data
* Test data: [Test_data.tar.gz](https://bit.ly/3ejeRBJ)

> If you can not clink the hyperlink, you can download test data from `ftp://74.120.168.50`

## Uncompress test data

```angular2html

$ tar -zxvf Test_data.tar.gz

```
* This test data contains:

```
abb_species_503/  
                aaf.fasta
                abi.fasta
                acan.fasta
                acf.fasta
                ...
protein/
        ath.fasta
        Gor.fasta
        Hpa.fasta
        Psy.fasta
ath_psy_hpa_gor_sequence.fasta
KPP.jar
Readme.txt

```
* **abb_species_503**: The folder contains proteome of 503 species for constructing phylogenetic profile. 
* **protein**: The folder contains the proteome of 4 species for build contig index.
* **ath_psy_hpa_gor_sequence.fasta**: The protein sequence for extract feature.
* **KPP.jar**: The jar package for KPP algorithm.
* **Readme.txt**: command line.
