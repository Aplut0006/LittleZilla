package app.littlezilla.littlezilla.Models;

public class ContentModel {

    private String image,title;

    public ContentModel(String image, String title) {
        this.image = image;
        this.title = title;
    }

    public ContentModel() {
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}
