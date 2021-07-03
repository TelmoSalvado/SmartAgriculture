package pt.ipg.smartagriculture;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Feeds {
    @SerializedName("feeds")
    private List<Valores> valor;


    public List<Valores> getvalor() {
        return valor;
    }
}
