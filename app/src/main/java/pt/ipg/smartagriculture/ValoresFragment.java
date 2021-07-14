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
    int x = 0;
    String Rega;

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
                         x = Integer.parseInt(valores.get(i).getField5());
                         if (x == 0){
                             Rega = getString(R.string.Desligado);
                         }else if (x == 1 ){
                             Rega = getString(R.string.Ligado);
                         }
                         String content = "";
                         content += " " + "\n";
                         content +=  getString(R.string.Data) + " : " + valores.get(i).getCreatedAt() + "\n";
                        // content += "ID: " + valores.get(i).getEntryId() + "\n";
                         content += getString(R.string.Solo) + " : " + valores.get(i).getField1() + "\n";
                         content += getString(R.string.HumidadeAr) + " : "+ valores.get(i).getField2() + "\n";
                         content += getString(R.string.Temperatura) + " : "+ valores.get(i).getField3() + "\n";
                         content += getString(R.string.Monoxido) + " : "+ valores.get(i).getField4() + "\n";
                         content += getString(R.string.Rega) + " : " + Rega + "\n";
                         content += getString(R.string.Luminosidade) + " : " + valores.get(i).getField6() + "\n";
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
                         content += getString(R.string.Data) + valores.get(i).getCreatedAt() + "\n";
                        // content += "ID: " + valores.get(i).getEntryId() + "\n";
                         content += getString(R.string.Temperatura) + " : "+ valores.get(i).getField3() + "\n";
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
                         content += getString(R.string.Data) + " : " + valores.get(i).getCreatedAt() + "\n";
                         //content += "ID: " + valores.get(i).getEntryId() + "\n";
                         content += getString(R.string.Solo) + " : " + valores.get(i).getField1() + "\n";
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
                         content +=  getString(R.string.Data) + " : "  + valores.get(i).getCreatedAt() + "\n";
                        // content += "ID: " + valores.get(i).getEntryId() + "\n";
                         content += getString(R.string.HumidadeAr) + " : " + valores.get(i).getField2() + "\n";
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
                         content +=  getString(R.string.Data) + " : "  + valores.get(i).getCreatedAt() + "\n";
                         //content += "ID: " + valores.get(i).getEntryId() + "\n";
                         content += getString(R.string.Monoxido) + " : " + valores.get(i).getField4() + "\n";
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
                         content +=  getString(R.string.Data) + " : " + valores.get(i).getCreatedAt() + "\n";
                         //content += "ID: " + valores.get(i).getEntryId() + "\n";
                         content += getString(R.string.Luminosidade) + " : "+ valores.get(i).getField6() + "\n";
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
                         x = Integer.parseInt(valores.get(i).getField5());
                         if (x == 0){
                             Rega = "Desligada";
                         }else if (x == 1 ){
                             Rega = "Ligada";
                         }
                         String content = "";
                         content += " " + "\n";
                         content +=  getString(R.string.Data) + " : " + valores.get(i).getCreatedAt() + "\n";
                        // content += "ID: " + valores.get(i).getEntryId() + "\n";
                         content += getString(R.string.Rega) + " : " + Rega + "\n";
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
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        updateFiltro();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}







