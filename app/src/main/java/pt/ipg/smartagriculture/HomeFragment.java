package pt.ipg.smartagriculture;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.se.omapi.Channel;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static pt.ipg.smartagriculture.NotificationView.CHANNEL_1_ID;

public class HomeFragment extends Fragment {
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private List<Feeds> feeds;
    int x = 0;
    WebView webView;
    int width = 450;
    int height = 260;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);



    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://thingspeak.com").addConverterFactory(GsonConverterFactory.create()).build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        webView = view.findViewById(R.id.thingspeak1);
        String unencodedHtml = "<iframe width= " + width + " + height=" + height + " style='border: 5px solid #cccccc;' src=" + "https://thingspeak.com/channels/1431133/widgets/334688" + " ></iframe>";
        String encodedHtml = Base64.encodeToString(unencodedHtml.getBytes(), Base64.NO_PADDING);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(encodedHtml, "text/html", "base64");

        webView = view.findViewById(R.id.thingspeak2);
        String unencodedHtml2 = "<iframe width= " + width + " + height=" + height + " style='border: 5px solid #cccccc;' src=" + "https://thingspeak.com/channels/1431133/widgets/334689" + " ></iframe>";
        String encodedHtml2 = Base64.encodeToString(unencodedHtml2.getBytes(), Base64.NO_PADDING);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(encodedHtml2, "text/html", "base64");

        webView = view.findViewById(R.id.thingspeak3);
        String unencodedHtml3 = "<iframe width= " + width + " + height=" + height + " style='border: 5px solid #cccccc;' src=" + "https://thingspeak.com/channels/1431133/widgets/334690" + " ></iframe>";
        String encodedHtml3 = Base64.encodeToString(unencodedHtml3.getBytes(), Base64.NO_PADDING);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(encodedHtml3, "text/html", "base64");

        webView = view.findViewById(R.id.thingspeak4);
        String unencodedHtml4 = "<iframe width= " + width + " + height=" + height + " style='border: 5px solid #cccccc;' src=" + "https://thingspeak.com/channels/1431133/widgets/334691" + " ></iframe>";
        String encodedHtml4 = Base64.encodeToString(unencodedHtml4.getBytes(), Base64.NO_PADDING);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(encodedHtml4, "text/html", "base64");

        webView = view.findViewById(R.id.thingspeak5);
        String unencodedHtml5 = "<iframe width= " + width + " + height=" + height + " style='border: 5px solid #cccccc;' src=" + "https://thingspeak.com/channels/1431133/widgets/334692" + " ></iframe>";
        String encodedHtml5 = Base64.encodeToString(unencodedHtml5.getBytes(), Base64.NO_PADDING);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(encodedHtml5, "text/html", "base64");



        getEstadoRega();

    }

    private void getEstadoRega() {

        Call<Feeds> feedsCall = jsonPlaceHolderApi.getAllFeeds();
        feedsCall.enqueue(new Callback<Feeds>() {
            @Override
            public void onResponse(Call<Feeds> call, Response<Feeds> response) {
                Feeds body = response.body();
                List<Valores> valores = body.getvalor();
                valores.size();

                for (int i = valores.size() - 1; i >= valores.size() - 1; i--) {
                    x = Integer.parseInt(valores.get(i).getField5());
                    if (x == 1) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                        alert.setTitle("Estado da Rega");
                        alert.setMessage("A rega está ligada!");
                        alert.setPositiveButton("OK", null);
                        alert.show();
                    } else if (x == 0) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                        alert.setTitle("Estado da Rega");
                        alert.setMessage("A rega está desligada!");
                        alert.setPositiveButton("OK", null);
                        alert.show();
                    }


                }
            }

            @Override
            public void onFailure(Call<Feeds> call, Throwable t) {
            }
        });

    }
}

