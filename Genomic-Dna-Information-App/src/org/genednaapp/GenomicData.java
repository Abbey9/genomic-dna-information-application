package org.genednaapp;

import edu.duke.StorageResource;

public class GenomicData {
  
	/*This method findStop with three parameters returns the index of the first occurrence of stopCodon 
	 * that appears past startIndex and is a multiple of 3 away from startIndex. 
	 * If there is no such stopCodon,this method returns -1.
	 */
	
	public int findStopCodon(String dnaStr, int startIndex, String stopCodon) {

		// finding stopCodon starting from startIndex+3
		
		int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);

		while (currIndex != -1) {

			int diff = currIndex - startIndex;

			if (diff % 3 == 0) { // checking if currIndex-startIndex is a multiple of 3
				
				return currIndex;
				
			} else {
				
				// updating currIndex to index of next stopCodon
				currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
			}
		}

		return -1;

	}

	/* Method findGene with String parameter DNA to return the gene found between startCodon and stopCodons. 
	 * If there is no stopCodon or startCodon, this method returns empty gene string.
	 */
	public String findGene(String dna, int where) {

		// finding first occurrence of start Codon ATG
		int startIndex = dna.indexOf("ATG", where);

		// if no ATG found, returns empty strings
		if (startIndex == -1) {
			return "";
		}

		// finding occurrences all three stopCodons TAA, TAG, TGA index by calling findStopCodon method
		int taaIndex = findStopCodon(dna, startIndex, "TAA");
		int tagIndex = findStopCodon(dna, startIndex, "TAG");
		int tgaIndex = findStopCodon(dna, startIndex, "TGA");

		// finding minIndex by finding smallest of taaIndex, tgaIndex and tagIndex

		int minIndex = 0;

		/*if stopCodon TAA or TGA not found and if TGA index is less than
		 * TAA index, assign minIndex the value of TGA Index else TAA Index
		*/
		if ((taaIndex == -1) || (tgaIndex != -1 && tgaIndex < taaIndex)) {

			minIndex = tgaIndex;

		} else {

			minIndex = taaIndex;
		}

		/*if minimum of TAA AND TGA Index is greater than TAG Index or TAG Index
		 * is not found, assign minIndex the value of TAG Index
		 */
		if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {

			minIndex = tagIndex;
		}

		/*if smallest of three StopCodon's index is equal to length of Dna,
		 * return empty string
		 */
		if (minIndex == dna.length()) {

			return " ";
		}

		//returns the gene found in dna string between startCodon's index and stopCodon's index
		return "Gene found:"+dna.substring(startIndex, minIndex + 3);

	}
	
	/*Method getAllGenes with String parameter DNA to store all genes using edu.duke custom class StorageResource
	 * which returns stored genes which are found in DNA contained in a file
	 *It also counts total no.of genes found in DNA Strand.
	 */
	
	public StorageResource getAllGenes(String  dna) {

		//Created StorageResource object geneList, thus creating an empty list
		StorageResource geneList = new StorageResource();

		int startIndex = 0;

		int countGenes = 0;

		while (true) {

			// finding gene using findGene method starting from startIndex
			String currentGene = findGene(dna, startIndex);

			if (currentGene.isEmpty()) {// if no gene found
				System.out.println("No gene found");
				break;
			}

			//System.out.println("Current Gene:" + currentGene);
			
            //adding string value of gene found in plain file named dnastrand.txt to the StorageResource empty list
			geneList.add(currentGene);

			//updating startIndex and set it to just past end of gene found
			startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();

			//counting genes found
			countGenes = countGenes + 1;
		}
        System.out.println(" All Genes stored Successfully");
		System.out.println("Total No. of Genes:" + countGenes);
		return geneList;
	}
}
