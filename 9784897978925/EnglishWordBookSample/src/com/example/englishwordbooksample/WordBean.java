package com.example.englishwordbooksample;

public class WordBean {
	
	private String englishword;
	private String japaneseword;
	
	public WordBean(String englishword, String japaneseword) {
		this.englishword = englishword;
		this.japaneseword = japaneseword;
	}

	public String getEnglishword() {
		return englishword;
	}

	public void setEnglishword(String englishword) {
		this.englishword = englishword;
	}

	public String getJapaneseword() {
		return japaneseword;
	}

	public void setJapaneseword(String japaneseword) {
		this.japaneseword = japaneseword;
	}

}
