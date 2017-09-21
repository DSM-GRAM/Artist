package gram_zico.artist.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root1 on 2017. 9. 21..
 */

public class CategoryIDModel {
    @SerializedName("_id")
    private String categoryID;

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }
}
