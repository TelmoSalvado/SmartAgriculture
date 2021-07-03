package pt.ipg.smartagriculture;

import com.google.gson.annotations.SerializedName;

public class Post {

    private int userid;
    private int id;


    private String title;

    private String description;

   @SerializedName("body")
    private String text;

    public int getId() {
        return id;
    }

    public int getUserid() {
        return userid;
    }

    public String getTitle() {
        return title;
    }

   public String getText() {
        return text;
    }
}
