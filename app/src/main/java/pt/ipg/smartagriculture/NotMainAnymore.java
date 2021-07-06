package pt.ipg.smartagriculture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import static pt.ipg.smartagriculture.NotificationView.CHANNEL_1_ID;

public class NotMainAnymore extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navgation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        return true;

    }
private BottomNavigationView.OnNavigationItemSelectedListener navListener =
        new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment =  null;
                switch (item.getItemId()){
                    case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                    case R.id.nav_values:
                        selectedFragment = new ValoresFragment();
                        break;
                    case R.id.nav_graph:
                        selectedFragment = new GraphsFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
            }
        };
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_definicoes) {
            return true;
        }else if(id == R.id.action_sair){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}