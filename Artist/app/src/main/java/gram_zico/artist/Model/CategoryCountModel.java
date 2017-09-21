package gram_zico.artist.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root1 on 2017. 9. 20..
 */

public class CategoryCountModel {
    @SerializedName("1")
    private int count1;
    @SerializedName("2")
    private int count2;
    @SerializedName("3")
    private int count3;
    @SerializedName("4")
    private int count4;
    @SerializedName("5")
    private int count5;
    @SerializedName("6")
    private int count6;
    @SerializedName("7")
    private int count7;
    @SerializedName("8")
    private int count8;
    @SerializedName("9")
    private int count9;
    @SerializedName("10")
    private int count10;

    public int getData(int count){
        int arr[] = new int[]{count1, count2, count3, count4, count5, count6, count7, count8, count9, count10};
        return arr[count];
    }
}
