package org.genednaapp;

public class GenomicData {
  
	/*This method findStop with three parameters returns the index of the first occurrence of stopCodon 
	 * that appears past startIndex and is a multiple of 3 away from startIndex. 
	 * If there is no such stopCodon,this method returns the length of the dna strand.
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

	
}
