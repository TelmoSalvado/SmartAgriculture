package pt.ipg.smartagriculture;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GraphsFragment extends Fragment {

    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private List<Feeds> feeds;
    int w=0;
    WebView webView;
    int width = 450;
    int height = 260;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_graphs, container, false);



    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://thingspeak.com").addConverterFactory(GsonConverterFactory.create()).build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


        webView = view.findViewById(R.id.webViewHumidadeSolo);
        String unencodedHtml = "<iframe width= " + width + " + height=" + height + " style='border: 5px solid #cccccc;' src=" + "https://thingspeak.com/channels/1424295/charts/1?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&type=line&update=15" + " ></iframe>";
        String encodedHtml = Base64.encodeToString(unencodedHtml.getBytes(), Base64.NO_PADDING);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(encodedHtml, "text/html", "base64");

        webView = view.findViewById(R.id.webViewHumidadeAr);
        String unencodedHtml2 = "<iframe width= " + width + " + height=" + height + " style='border: 5px solid #cccccc;' src=" + "https://thingspeak.com/channels/1424295/charts/2?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&type=line&update=15" + " ></iframe>";
        String encodedHtml2 = Base64.encodeToString(unencodedHtml2.getBytes(), Base64.NO_PADDING);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(encodedHtml2, "text/html", "base64");

        webView = view.findViewById(R.id.webViewTemperatura);
        String unencodedHtml3 = "<iframe width= " + width + " + height=" + height + " style='border: 5px solid #cccccc;' src=" + "https://thingspeak.com/channels/1424295/charts/3?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&type=line&update=15" + " ></iframe>";
        String encodedHtml3 = Base64.encodeToString(unencodedHtml3.getBytes(), Base64.NO_PADDING);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(encodedHtml3, "text/html", "base64");

        webView = view.findViewById(R.id.webViewMonóxido);
        String unencodedHtml4 = "<iframe width= " + width + " + height=" + height + " style='border: 5px solid #cccccc;' src=" + "https://thingspeak.com/channels/1424295/charts/4?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&type=line&update=15" + " ></iframe>";
        String encodedHtml4 = Base64.encodeToString(unencodedHtml4.getBytes(), Base64.NO_PADDING);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(encodedHtml4, "text/html", "base64");

        webView = view.findViewById(R.id.webViewLuminosidade);
        String unencodedHtml5 = "<iframe width= " + width + " + height=" + height + " style='border: 5px solid #cccccc;' src=" + "https://thingspeak.com/channels/1424295/charts/6?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&type=line&update=15" + " ></iframe>";
        String encodedHtml5 = Base64.encodeToString(unencodedHtml5.getBytes(), Base64.NO_PADDING);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(encodedHtml5, "text/html", "base64");

        webView = view.findViewById(R.id.webViewAtuador);
        String unencodedHtml6 = "<iframe width= " + width + " + height=" + height + " style='border: 5px solid #cccccc;' src=" + "https://thingspeak.com/channels/1424295/charts/5?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&type=line&update=15" + " ></iframe>";
        String encodedHtml6 = Base64.encodeToString(unencodedHtml6.getBytes(), Base64.NO_PADDING);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(encodedHtml6, "text/html", "base64");

        webView = view.findViewById(R.id.webViewTemperaturaHumidade);
        String unencodedHtml7 = "<iframe width= " + width + " + height=" + height + " style='border: 5px solid #cccccc;' src=" + "https://thingspeak.com/apps/matlab_visualizations/416492" + " ></iframe>";
        String encodedHtml7 = Base64.encodeToString(unencodedHtml7.getBytes(), Base64.NO_PADDING);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(encodedHtml7, "text/html", "base64");

        webView = view.findViewById(R.id.webViewAtuador);
        String unencodedHtml8 = "<iframe width= " + width + " + height=" + height + " style='border: 5px solid #cccccc;' src=" + "https://thingspeak.com/apps/matlab_visualizations/416490" + " ></iframe>";
        String encodedHtml8 = Base64.encodeToString(unencodedHtml8.getBytes(), Base64.NO_PADDING);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(encodedHtml8, "text/html", "base64");

        }
    }
