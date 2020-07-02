package app.littlezilla.littlezilla.Models;

public class EnglishModel {
    private String smallLetter,capitalLetter,textDescription,image;

    public EnglishModel(String smallLetter, String capitalLetter, String textDescription, String image) {
        this.smallLetter = smallLetter;
        this.capitalLetter = capitalLetter;
        this.textDescription = textDescription;
        this.image = image;
    }

    public EnglishModel() {
    }

    public String getSmallLetter() {
        return smallLetter;
    }

    public String getCapitalLetter() {
        return capitalLetter;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public String getImage() {
        return image;
    }
}
