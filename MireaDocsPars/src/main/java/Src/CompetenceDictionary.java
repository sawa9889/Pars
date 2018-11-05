package Src;

public class CompetenceDictionary {
    String dictionaryId;
    String dictionaryName;

    public String getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(String dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
    }

    public CompetenceDictionary(String dictionaryId, String dictionaryName) {

        this.dictionaryId = dictionaryId;
        this.dictionaryName = dictionaryName;
    }
}
