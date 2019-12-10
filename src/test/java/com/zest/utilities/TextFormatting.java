package com.zest.utilities;

public class TextFormatting {

	public int removeSpecialCharacterAndReturnIntVal(String s) {
		String removedSpl = s.replaceAll("[^a-zA-Z0-9]", "");
		return Integer.parseInt(removedSpl);
	}

	public String removeSpecialCharacterFromStringl(String s) {
		String removedSpl = s.replaceAll("[^a-zA-Z0-9]", "");
		return removedSpl;
	}

	public int ComparePrice(int aPrice, int fProce) {
		if (aPrice < fProce) {
			System.out.println("Amazon Price is Lesser");
			return aPrice;
		} else {
			System.out.println("FlipKart Price is Lesser");
			return fProce;
		}
	}
}
