package pt.ipg.smartagriculture;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.se.omapi.Channel;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

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
    int n = 0;
    String Temp, Rega;

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
                for (int i = valores.size() -1; i>= valores.size() - 15; i--){
                    int rega = Integer.parseInt(valores.get(i).getField5());
                    if (rega == 1){
                        n++;
                    }
                }
                for (int i = valores.size() - 1; i >= valores.size() - 1; i--) {
                    x = Integer.parseInt(valores.get(i).getField5());
                    Temp = valores.get(i).getField3();

                    if(x==1){
                        Rega = getString(R.string.Ligado);
                    }else if (x == 0){
                        Rega = getString(R.string.Desligado);
                    }
                    createNotificationChannel();
                    createNotification(Rega, Temp);
                    if (x == 1 && n >= 8) {
                        LayoutInflater inflater = LayoutInflater.from(getContext());
                        View subView = inflater.inflate(R.layout.dialog_layout, null);
                        final ImageView subImageView = (ImageView)subView.findViewById(R.id.image);
                        Drawable drawable = getResources().getDrawable(R.drawable.planta2);
                        subImageView.setImageDrawable(drawable);

                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle(getString(R.string.EstadoRega));
                        builder.setIcon(R.drawable.regar);
                        builder.setMessage(getString(R.string.Text10) + " " + n + " " + getString(R.string.Text12) );
                        builder.setView(subView);
                        builder.setPositiveButton("OK", null);
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    } else if (x == 0 && n <= 8) {
                        LayoutInflater inflater = LayoutInflater.from(getContext());
                        View subView = inflater.inflate(R.layout.dialog_layout, null);
                        final ImageView subImageView = (ImageView)subView.findViewById(R.id.image);
                        Drawable drawable = getResources().getDrawable(R.drawable.plantaboa);
                        subImageView.setImageDrawable(drawable);

                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle(getString(R.string.EstadoRega));
                        builder.setIcon(R.drawable.ads);
                        builder.setMessage(getString(R.string.Text11) + " " + n + " " + getString(R.string.Text12) );
                        builder.setView(subView);
                        builder.setPositiveButton("OK", null);
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }else if ( x == 0 && n >= 8 ){
                        LayoutInflater inflater = LayoutInflater.from(getContext());
                        View subView = inflater.inflate(R.layout.dialog_layout, null);
                        final ImageView subImageView = (ImageView)subView.findViewById(R.id.image);
                        Drawable drawable = getResources().getDrawable(R.drawable.planta2);
                        subImageView.setImageDrawable(drawable);

                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle(getString(R.string.EstadoRega));
                        builder.setIcon(R.drawable.ads);
                        builder.setMessage(getString(R.string.Text11) + " " + n + " " + getString(R.string.Text12) );
                        builder.setView(subView);
                        builder.setPositiveButton("OK", null);
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }else if (x == 1  && n <=8 ){
                        LayoutInflater inflater = LayoutInflater.from(getContext());
                        View subView = inflater.inflate(R.layout.dialog_layout, null);
                        final ImageView subImageView = (ImageView)subView.findViewById(R.id.image);
                        Drawable drawable = getResources().getDrawable(R.drawable.plantaboa);
                        subImageView.setImageDrawable(drawable);

                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle(getString(R.string.EstadoRega));
                        builder.setIcon(R.drawable.regar);
                        builder.setMessage(getString(R.string.Text10) + " " + n + " " + getString(R.string.Text12) );
                        builder.setView(subView);
                        builder.setPositiveButton("OK", null);
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }


                }
            }

            @Override
            public void onFailure(Call<Feeds> call, Throwable t) {
            }
        });

    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel_name"; // getString(R.string.channel_name);
            String description = "channel_description"; // getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void createNotification(String EstadoRega, String Temperatura) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "CHANNEL_ID") //.Builder(this, "CHANNEL_ID")
                .setSmallIcon(R.drawable.regar) // .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(getString(R.string.EstadoRega))
                .setContentText(getString(R.string.Text8) + " " + EstadoRega + " " + getString(R.string.Text9) + " " + Temperatura + " ??C ")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity());

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(0, builder.build());
    }
}

