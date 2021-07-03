package pt.ipg.smartagriculture;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Valores {

    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("entry_id")
    @Expose
    private Integer entryId;
    @SerializedName("field1")
    @Expose
    private String field1;
    @SerializedName("field2")
    @Expose
    private String field2;
    @SerializedName("field3")
    @Expose
    private String field3;
    @SerializedName("field4")
    @Expose
    private String field4;
    @SerializedName("field5")
    @Expose
    private String field5;
    @SerializedName("field6")
    @Expose
    private String field6;

    public String getCreatedAt() {
        return createdAt;
    }



    public Integer getEntryId() {
        return entryId;
    }



    public String getField1() {
        return field1;
    }



    public String getField2() {
        return field2;
    }



    public String getField3() {
        return field3;
    }



    public String getField4() {
        return field4;
    }

    public String getField5() {
        return field5;
    }


    public String getField6() {
        return field6;
    }


}