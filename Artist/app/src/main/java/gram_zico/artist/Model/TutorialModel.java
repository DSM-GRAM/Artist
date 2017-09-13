package gram_zico.artist.Model;

/**
 * Created by root1 on 2017. 9. 13..
 */

public class TutorialModel {
    private int imageID;
    private String content;

    public TutorialModel(int imageID, String content) {
        this.imageID = imageID;
        this.content = content;
    }


    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
