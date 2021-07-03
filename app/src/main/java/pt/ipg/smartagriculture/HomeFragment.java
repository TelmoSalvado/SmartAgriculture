package pt.ipg.smartagriculture;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.se.omapi.Channel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);


    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://thingspeak.com").addConverterFactory(GsonConverterFactory.create()).build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


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

                for(int i = valores.size() - 1 ; i >= valores.size()- 1 ; i-- ){
                      x = Integer.parseInt(valores.get(i).getField5());
                      if (x == 1) {
                           AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                          alert.setTitle("Estado da Rega");
                          alert.setMessage("A rega está ligada!");
                           alert.setPositiveButton("OK",null);
                           alert.show();
                       }else if (x == 0){
                           AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                           alert.setTitle("Estado da Rega");
                           alert.setMessage("A rega está desligada!");
                           alert.setPositiveButton("OK",null);
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
