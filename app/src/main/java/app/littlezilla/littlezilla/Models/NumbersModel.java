package app.littlezilla.littlezilla.Models;

public class NumbersModel {
    private String title,description,imageLink;

    public NumbersModel(String title, String description, String imageLink) {
        this.title = title;
        this.description= description;
        this.imageLink = imageLink;
    }

    public NumbersModel() {
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageLink() {
        return imageLink;
    }
}


