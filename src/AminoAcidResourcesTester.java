import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

class AminoAcidResourcesTester{

  @Test
  public void allCodons(){
    char[] rna = {'A','C','U','G'};
    char[] aa = {'A','C','D','E','F','G','H','I','K','L','M','N','P','Q','R','S','T','V','W'};
    for(int i=0; i<4; i++){
      for(int j=0; j<4; j++){
        for(int k=0; k<4;k++){
          String s = new String(new char[]{rna[i],rna[j],rna[k]});
          char aaOut = AminoAcidResources.getAminoAcidFromCodon(s);
          if(aaOut != '*'){
            String[] codonList = AminoAcidResources.getCodonListForAminoAcid(aaOut);
            boolean found = false;
            for(int l=0; l<codonList.length; l++){
              found |= (codonList[l].equals(s));
            }
            if(!found) System.err.println("Codon " + s + " not found, said AA was " + aaOut);
          }

          aaOut = AminoAcidResources.getAminoAcidFromCodon(s.toLowerCase());
          if(aaOut != '*'){
            String[] codonList = AminoAcidResources.getCodonListForAminoAcid(aaOut);
            boolean found = false;
            for(int l=0; l<codonList.length; l++){
              found |= (codonList[l].equals(s));
            }
            if(!found) System.err.println("Codon " + s + " not found, said AA was " + aaOut);
          }
        }
      }
    }

  }

  @Test
  public void allAAs(){

    char[] aa = {'A','C','D','E','F','G','H','I','K','L','M','N','P','Q','R','S','T','V','W'};
    for(int i=0; i<aa.length; i++){
      String[] codonList = AminoAcidResources.getCodonListForAminoAcid(aa[i]);
      for(int l=0; l<codonList.length; l++){
        if(aa[i] != AminoAcidResources.getAminoAcidFromCodon(codonList[l])){
          System.err.println("AA " + aa[i] + " not found, said codon was " + codonList[l]);
        }
      }

      codonList = AminoAcidResources.getCodonListForAminoAcid(Character.toLowerCase(aa[i]));
      for(int l=0; l<codonList.length; l++){
        if(aa[i] != AminoAcidResources.getAminoAcidFromCodon(codonList[l])){
          System.err.println("AA " + aa[i] + " not found, said codon was " + codonList[l]);
        }
      }
    }
  }

  //these first two test cases should translate the string to a linklist and return the correct charatcer array
  @Test
  public void test1(){//uppercase as it should wolk
    char[] e = {'A','T','G','L','A'};
    AminoAcidLL a = AminoAcidLL.createFromRNASequence("GCUACGGAGCUUCGGAGCUAG");
    assertArrayEquals(e, a.aminoAcidList());
  }

  @Test
  public void test2(){//lower case
    char[] e = {'A','T','G','L','A'};
    AminoAcidLL a = AminoAcidLL.createFromRNASequence("GCUACGGAGCUUCGGAGCUAG");
    assertArrayEquals(e, a.aminoAcidList());
  }

  //these two test cases should test if the list is sorted or if it needs to be sorted one test case shsould be true and the other should be
  @Test
  public void test3(){//this list is not sorted
    AminoAcidLL a = AminoAcidLL.createFromRNASequence("GCUACGGAGCUUCGGAGCUAG");
    assertEquals(false, a.isSorted());
  }

  @Test
  public void test4(){//This list is sorted
    AminoAcidLL a = AminoAcidLL.createFromRNASequence("GCGGACUAG");
    assertEquals(true, a.isSorted());
  }

  @Test
  public void test5(){//this is going to test if the method sort was implemented correctly with an unsorted list
    AminoAcidLL a = AminoAcidLL.createFromRNASequence("GCUACGGAGCUUCGGAGCUAG");
    a = AminoAcidLL.sort(a);
    assertEquals(true, a.isSorted());
  }

  @Test
  public void test6(){//this is going to test if the method sort is implemented correctly with an already sorted list
    AminoAcidLL a = AminoAcidLL.createFromRNASequence("GCGGACUAG");
    a = AminoAcidLL.sort(a);
    assertEquals(true, a.isSorted());
  }

  @Test
  public void test7(){//this count the amino acids
    int[] e = {2,1,1,1,1};
    AminoAcidLL a = AminoAcidLL.createFromRNASequence("GCUACGGAGCUUCGGAGCUAG");
    assertEquals(e, a.aminoAcidCounts());
  }

  @Test
  public void test8(){// counts the amino acids
    int[] e = {1,1,1,1,1};
    AminoAcidLL a = AminoAcidLL.createFromRNASequence("ACGGAGCUUCGGAGCUAG");
    assertEquals(e, a.aminoAcidCounts());
  }

  @Test
  public void test9(){//does it count a stop codon
    int[] e = {0};
    AminoAcidLL a = AminoAcidLL.createFromRNASequence("UAG");
    assertEquals(e, a.aminoAcidCounts());
  }

  @Test// I tried this last method after talking to michell as to how to compare
  public void test10(){
    String str = "ATGLAS";
    AminoAcidLL a = AminoAcidLL.createFromRNASequence("GCUACGGAGCUUCGGAGCUAG");
    for(int i = 0; i < str.length() - 1; i++){
      assertEquals(str.charAt(i), a.aminoAcid);
      a = a.next;
    }
  }









}