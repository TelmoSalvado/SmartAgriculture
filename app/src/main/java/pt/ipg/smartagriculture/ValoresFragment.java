package pt.ipg.smartagriculture;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ValoresFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private TextView textViewResult;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private List<Feeds> feeds;
    Spinner spinnerFiltros;
    String Filtro;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_valores, container, false);


    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://thingspeak.com").addConverterFactory(GsonConverterFactory.create()).build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        spinnerFiltros = view.findViewById(R.id.spinner);
        textViewResult = view.findViewById(R.id.text_view_result);
        spinnerFiltros = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.filtros,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFiltros.setAdapter(adapter);
        spinnerFiltros.setOnItemSelectedListener(this);
        Log.d("fiLTR", " " + Filtro);
        //getPosts();
        //getValores();
    }
    public void updateFiltro(){

         Filtro = ((String) spinnerFiltros.getSelectedItem());
         if(Filtro.equals(getString(R.string.Todos))){
             textViewResult.setText(" ");
             Call<Feeds> feedsCall = jsonPlaceHolderApi.getAllFeeds();
             feedsCall.enqueue(new Callback<Feeds>() {
                 @Override
                 public void onResponse(Call<Feeds> call, Response<Feeds> response) {
                     Feeds body = response.body();
                     List<Valores> valores = body.getvalor();
                     valores.size();
                     for (int i = valores.size() - 1; i >= 0; i--) {
                         String content = "";
                         content += " " + "\n";
                         content += "Data: " + valores.get(i).getCreatedAt() + "\n";
                        // content += "ID: " + valores.get(i).getEntryId() + "\n";
                         content += "Humidade Solo: " + valores.get(i).getField1() + "\n";
                         content += "Humidade Ar (%): " + valores.get(i).getField2() + "\n";
                         content += "Temperatura ºC: " + valores.get(i).getField3() + "\n";
                         content += "Monoxido de Carbono: " + valores.get(i).getField4() + "\n";
                         content += "Atuador: " + valores.get(i).getField5() + "\n";
                         content += "Luminosidade:" + valores.get(i).getField6() + "\n";
                         content += "-------------------------------------------------------------" + "\n";
                         textViewResult.append(content);
                     }
                 }
                 @Override
                 public void onFailure(Call<Feeds> call, Throwable t) {
                     textViewResult.setText(t.getMessage());
                 }
             });

         } else if (Filtro.equals(getString(R.string.Temperatura))) {
             textViewResult.setText(" ");
             Call<Feeds> feedsCall = jsonPlaceHolderApi.getAllFeeds();
             feedsCall.enqueue(new Callback<Feeds>() {
                 @Override
                 public void onResponse(Call<Feeds> call, Response<Feeds> response) {
                     Feeds body = response.body();
                     List<Valores> valores = body.getvalor();
                     valores.size();
                     for (int i = valores.size() - 1; i >= 0; i--) {
                         String content = "";
                         content += " " + "\n";
                         content += "Data: " + valores.get(i).getCreatedAt() + "\n";
                        // content += "ID: " + valores.get(i).getEntryId() + "\n";
                         content += "Temperatura ºC: " + valores.get(i).getField3() + "\n";
                         content += "-------------------------------------------------------------" + "\n";
                         textViewResult.append(content);
                     }
                 }
                 @Override
                 public void onFailure(Call<Feeds> call, Throwable t) {
                     textViewResult.setText(t.getMessage());
                 }
             });
        }
         else if (Filtro.equals(getString(R.string.Solo))) {
             textViewResult.setText(" ");
             Call<Feeds> feedsCall = jsonPlaceHolderApi.getAllFeeds();
             feedsCall.enqueue(new Callback<Feeds>() {
                 @Override
                 public void onResponse(Call<Feeds> call, Response<Feeds> response) {
                     Feeds body = response.body();
                     List<Valores> valores = body.getvalor();
                     valores.size();
                     for (int i = valores.size() - 1; i >= 0; i--) {
                         String content = "";
                         content += " " + "\n";
                         content += "Data: " + valores.get(i).getCreatedAt() + "\n";
                         //content += "ID: " + valores.get(i).getEntryId() + "\n";
                         content += "Humidade do Solo: " + valores.get(i).getField1() + "\n";
                         content += "-------------------------------------------------------------" + "\n";
                         textViewResult.append(content);
                     }
                 }
                 @Override
                 public void onFailure(Call<Feeds> call, Throwable t) {
                     textViewResult.setText(t.getMessage());
                 }
             });
         }else if (Filtro.equals(getString(R.string.HumidadeAr))) {
             textViewResult.setText(" ");
             Call<Feeds> feedsCall = jsonPlaceHolderApi.getAllFeeds();
             feedsCall.enqueue(new Callback<Feeds>() {
                 @Override
                 public void onResponse(Call<Feeds> call, Response<Feeds> response) {
                     Feeds body = response.body();
                     List<Valores> valores = body.getvalor();
                     valores.size();
                     for (int i = valores.size() - 1; i >= 0; i--) {
                         String content = "";
                         content += " " + "\n";
                         content += "Data: " + valores.get(i).getCreatedAt() + "\n";
                        // content += "ID: " + valores.get(i).getEntryId() + "\n";
                         content += "Humidade Ar (%): " + valores.get(i).getField2() + "\n";
                         content += "-------------------------------------------------------------" + "\n";
                         textViewResult.append(content);
                     }
                 }
                 @Override
                 public void onFailure(Call<Feeds> call, Throwable t) {
                     textViewResult.setText(t.getMessage());
                 }
             });
         }else if (Filtro.equals(getString(R.string.Monoxido))) {
             textViewResult.setText(" ");
             Call<Feeds> feedsCall = jsonPlaceHolderApi.getAllFeeds();
             feedsCall.enqueue(new Callback<Feeds>() {
                 @Override
                 public void onResponse(Call<Feeds> call, Response<Feeds> response) {
                     Feeds body = response.body();
                     List<Valores> valores = body.getvalor();
                     valores.size();
                     for (int i = valores.size() - 1; i >= 0; i--) {
                         String content = "";
                         content += " " + "\n";
                         content += "Data: " + valores.get(i).getCreatedAt() + "\n";
                         //content += "ID: " + valores.get(i).getEntryId() + "\n";
                         content += "Monoxido de Carbono: " + valores.get(i).getField4() + "\n";
                         content += "-------------------------------------------------------------" + "\n";
                         textViewResult.append(content);
                     }
                 }
                 @Override
                 public void onFailure(Call<Feeds> call, Throwable t) {
                     textViewResult.setText(t.getMessage());
                 }
             });
         }else if (Filtro.equals(getString(R.string.Luminosidade))) {
             textViewResult.setText(" ");
             Call<Feeds> feedsCall = jsonPlaceHolderApi.getAllFeeds();
             feedsCall.enqueue(new Callback<Feeds>() {
                 @Override
                 public void onResponse(Call<Feeds> call, Response<Feeds> response) {
                     Feeds body = response.body();
                     List<Valores> valores = body.getvalor();
                     valores.size();
                     for (int i = valores.size() - 1; i >= 0; i--) {
                         String content = "";
                         content += " " + "\n";
                         content += "Data: " + valores.get(i).getCreatedAt() + "\n";
                         //content += "ID: " + valores.get(i).getEntryId() + "\n";
                         content += "Luminosidade:" + valores.get(i).getField6() + "\n";
                         content += "-------------------------------------------------------------" + "\n";
                         textViewResult.append(content);
                     }
                 }
                 @Override
                 public void onFailure(Call<Feeds> call, Throwable t) {
                     textViewResult.setText(t.getMessage());
                 }
             });
         }else if (Filtro.equals(getString(R.string.Rega))) {
             textViewResult.setText(" ");
             Call<Feeds> feedsCall = jsonPlaceHolderApi.getAllFeeds();
             feedsCall.enqueue(new Callback<Feeds>() {
                 @Override
                 public void onResponse(Call<Feeds> call, Response<Feeds> response) {
                     Feeds body = response.body();
                     List<Valores> valores = body.getvalor();
                     valores.size();
                     for (int i = valores.size() - 1; i >= 0; i--) {
                         String content = "";
                         content += " " + "\n";
                         content += "Data: " + valores.get(i).getCreatedAt() + "\n";
                        // content += "ID: " + valores.get(i).getEntryId() + "\n";
                         content += "Atuador: " + valores.get(i).getField5() + "\n";
                         content += "-------------------------------------------------------------" + "\n";
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






    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        updateFiltro();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}







