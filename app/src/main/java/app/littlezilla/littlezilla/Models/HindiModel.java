package app.littlezilla.littlezilla.Models;

public class HindiModel {
    private String capitalLetter,textDescription,image;

    public HindiModel(String capitalLetter, String textDescription, String image) {
        this.capitalLetter = capitalLetter;
        this.textDescription = textDescription;
        this.image = image;
    }

    public HindiModel() {
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
