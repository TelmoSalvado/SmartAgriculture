package pt.ipg.smartagriculture;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    LineGraphSeries<DataPoint> series;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private List<Feeds> feeds;
    int w=0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_graphs, container, false);



    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://thingspeak.com").addConverterFactory(GsonConverterFactory.create()).build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        GraphView graphView = view.findViewById(R.id.graph);

        final double[] y = new double[200];
        final double[] x = new double[200];
      /*  double[] x= new double[w];
        double[] y= new double[w];*/
        Call<Feeds> feedsCall = jsonPlaceHolderApi.getAllFeeds();
        feedsCall.enqueue(new Callback<Feeds>() {
            @Override
            public void onResponse(Call<Feeds> call, Response<Feeds> response) {
                Feeds body = response.body();
                List<Valores> valores = body.getvalor();
                valores.size();



                series = new LineGraphSeries<DataPoint>();
                for ( w = 0; w < 3; w++) {
                    for (int i = valores.size() - 1; i >= valores.size()-2 ; i--) {
                        String content = "";
                        content = valores.get(i).getField2();
                        x[w] = Double.parseDouble(content);
                       /* y[0] = Double.parseDouble(content);*/
                        Log.d("valor", " " + x[w]);
                        content = valores.get(i).getField3();
                       y[w] = Double.parseDouble(content);
                        Log.d("valory", " " + y[w]);
                        series.appendData(new DataPoint(x[w], y[w]), true, 2);
                    }
                    graphView.addSeries(series);

                }
            }
            @Override
            public void onFailure(Call<Feeds> call, Throwable t) {

            }
        });
        }
    }

//-------------------------------------------------