package pt.ipg.smartagriculture;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ValoresFragment extends Fragment {
    private TextView textViewResult;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private List<Feeds> feeds;
    int x = 0;
   // private Context context;

   /* @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
        Log.d("context" , "" +context);
    }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_valores, container, false);


    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://thingspeak.com").addConverterFactory(GsonConverterFactory.create()).build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        textViewResult = view.findViewById(R.id.text_view_result);
        //getPosts();
        getValores();
    }

    private void getPosts() {

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<Post> posts = response.body();

                for (Post post : posts) {
                    String content = "";
                    content += "UserId: " + post.getUserid() + "\n";
                    content += "ID: " + post.getId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getValores() {

        Call<Feeds> feedsCall = jsonPlaceHolderApi.getAllFeeds();
        feedsCall.enqueue(new Callback<Feeds>() {
            @Override
            public void onResponse(Call<Feeds> call, Response<Feeds> response) {
                Feeds body = response.body();
                List<Valores> valores = body.getvalor();
                valores.size();

                for(int i = valores.size() -1 ; i >= 0 ; i-- ){
                        String content = "";
                        content += " "  + "\n";
                        content += "Data: " + valores.get(i).getCreatedAt() + "\n";
                        content += "ID: " + valores.get(i).getEntryId() + "\n";
                        content += "Humidade Solo: " + valores.get(i).getField1() + "\n";
                        content += "Humidade Ar (%): " + valores.get(i).getField2() + "\n";
                        content += "Temperatura ºC: " + valores.get(i).getField3() + "\n";
                        content += "Monoxido de Carbono: " + valores.get(i).getField4() + "\n";
                        content += "Atuador: " + valores.get(i).getField5() + "\n";
                      /*  x = Integer.parseInt(valores.get(i).getField5());
                        Log.d("context", "" + getContext());
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
                    }*/
                        content += "-------------------------------------------------------------"  + "\n";
                        textViewResult.append(content);

                }
            }

            @Override
            public void onFailure(Call<Feeds> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }
}







