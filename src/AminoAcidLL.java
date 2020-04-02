class AminoAcidLL{
  char aminoAcid;
  String[] codons;
  int[] counts;
  AminoAcidLL next;

  AminoAcidLL(){
  }



  /********************************************************************************************/
  /* Creates a new node, with a given amino acid/codon 
   * pair and increments the codon counter for that codon.
   * NOTE: Does not check for repeats!! */
  AminoAcidLL(String inCodon){
     this.aminoAcid = AminoAcidResources.getAminoAcidFromCodon(inCodon);
     this.codons = AminoAcidResources.getCodonListForAminoAcid(aminoAcid);
     this.counts = new int[codons.length];
     for(int i = 0; i < codons.length; i++) {
       if (codons[i].equals(inCodon)) {
         counts[i]++;
       }
     }
     next = null;
  }// refrence the node construction because this needs to be class node

  /********************************************************************************************/
  /* Recursive method that increments the count for a specific codon:
   * If it should be at this node, increments it and stops, 
   * if not passes the task to the next node. 
   * If there is no next node, add a new node to the list that would contain the codon. 
   */
  private void addCodon(String inCodon){
       if(AminoAcidResources.getAminoAcidFromCodon(inCodon) == aminoAcid){
           for(int i = 0; i < codons.length; i++){
               if(codons[i].equals(inCodon.toUpperCase())){
                    counts[i]++;
               }
           }
       }
       else if(next != null){
           next.addCodon(inCodon);
       }
       else{
           AminoAcidLL node = new AminoAcidLL(inCodon);
           next = node;
       }
  }


  /********************************************************************************************/
  /* Shortcut to find the total number of instances of this amino acid */
  private int totalCount(){

    return 0;
  }

  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int totalDiff(AminoAcidLL inList){
    return Math.abs(totalCount() - inList.totalCount());
  }


  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int codonDiff(AminoAcidLL inList){
    int diff = 0;
    for(int i=0; i<codons.length; i++){
      diff += Math.abs(counts[i] - inList.counts[i]);
    }
    return diff;
  }

  /********************************************************************************************/
  /* Recursive method that finds the differences in **Amino Acid** counts. 
   * the list *must* be sorted to use this method */
  public int aminoAcidCompare(AminoAcidLL inList){
      if(inList.isSorted() != true){

      }

    return 0;
  }

  /********************************************************************************************/
  /* Same ad above, but counts the codon usage differences
   * Must be sorted. */
  public int codonCompare(AminoAcidLL inList){

      return 0;
  }


  /********************************************************************************************/
  /* Recursively returns the total list of amino acids in the order that they are in in the linked list. */
  public char[] aminoAcidList(){
    return new char[]{};
  }

  /********************************************************************************************/
  /* Recursively returns the total counts of amino acids in the order that they are in in the linked list. */
  public int[] aminoAcidCounts(){
    return new int[]{};
  }


  /********************************************************************************************/
  /* recursively determines if a linked list is sorted or not */
  public boolean isSorted(){
    return false;
  }


  /********************************************************************************************/
  /* Static method for generating a linked list from an RNA sequence */
  public static AminoAcidLL createFromRNASequence(String inSequence){
    AminoAcidLL list = null;
    if(inSequence.length() >= 3 && AminoAcidResources.getAminoAcidFromCodon(inSequence.substring(0,2)) != '*'){
        list = new AminoAcidLL(inSequence.substring(0,3));

        for(int i = 3; i < inSequence.length() -2; i+=3){
            if(AminoAcidResources.getAminoAcidFromCodon(inSequence.substring(i, i + 3)) != '*'){
                list.addCodon(inSequence.substring(i, i + 3));
            }
            else{
                break;
            }
        }
    }
      return list;
  }


  /********************************************************************************************/
  /* sorts a list by amino acid character*/
  public static AminoAcidLL sort(AminoAcidLL inList){
    return null;
  }
}