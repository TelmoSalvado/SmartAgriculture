package pt.ipg.smartagriculture.gftsantos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

import pt.ipg.smartagriculture.MainActivity;
import pt.ipg.smartagriculture.R;

public class Slider extends IntroActivity {

    private FirebaseAuth autenticacao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.slider);



        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new FragmentSlide.Builder().background(R.color.white).fragment(R.layout.intro_1).build());
        addSlide(new FragmentSlide.Builder().background(R.color.white).fragment(R.layout.intro_2).build());
        addSlide(new FragmentSlide.Builder().background(R.color.white).fragment(R.layout.intro_3).build());
        addSlide(new FragmentSlide.Builder().background(R.color.white).fragment(R.layout.intro_registo).canGoForward(false).canGoBackward(true).build());



       /*

        addSlide(new SimpleSlide.Builder()
                .title("Titulo").description("descricao").image(R.drawable.um).background(R.color.fodas).build()
        );
        addSlide(new SimpleSlide.Builder()
                .title("Titulo2").description("descricao2").image(R.drawable.dois).background(R.color.fodas).build()
        );
        addSlide(new SimpleSlide.Builder()
                .title("Titulo3").description("descricao3").image(R.drawable.tres).background(R.color.fodas).build()
        );
        addSlide(new SimpleSlide.Builder()
                .title("Titulo4").description("descricao4").image(R.drawable.quatro).background(R.color.fodas).build()
        );*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        verificarUtilizadorEntrou();
    }

    public void  btEntrar(View view){
        startActivity(new Intent(this, LoginActivity.class ));

    }
    public void btRegistar (View view){
        startActivity(new Intent(this, RegistoActivity.class ));

    }
    public void verificarUtilizadorEntrou(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signOut();

        if (autenticacao.getCurrentUser() != null){
            abrirInterfacePrincipal();

        }
    }
    public void abrirInterfacePrincipal(){
        startActivity(new Intent(this, MainActivity.class));
        }
}



