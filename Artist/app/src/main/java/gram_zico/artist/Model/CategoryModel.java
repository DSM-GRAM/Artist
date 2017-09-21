package gram_zico.artist.Model;

/**
 * Created by root1 on 2017. 9. 20..
 */

public class CategoryModel {
    private int count;
    private String title;
    private String color;

    public CategoryModel(String title, int count, String color) {
        this.count = count;
        this.title = title;
        this.color = color;
    }

    public String getCount() {
        return count + "명이 선택";
    }

    public String getTitle() {
        return title;
    }

    public String getColor() {
        return color;
    }
}
