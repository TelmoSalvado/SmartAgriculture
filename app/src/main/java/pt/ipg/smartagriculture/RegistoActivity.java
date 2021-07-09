package pt.ipg.smartagriculture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class RegistoActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoPass;
    private Button botaoRegistar;
    private FirebaseAuth autenticacao;
    private Utilizador utilizador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo);

        campoNome = findViewById(R.id.editNome);
        campoEmail = findViewById(R.id.editEmail);
        campoPass = findViewById(R.id.editPass);

        botaoRegistar = findViewById(R.id.buttonRegistar);

        botaoRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoNome = campoNome.getText().toString();
                String textoEmail = campoEmail.getText().toString();
                String textoPass = campoPass.getText().toString();

                if(!textoNome.isEmpty()){
                    if (!textoEmail.isEmpty()){
                        if (!textoPass.isEmpty()){

                            utilizador = new Utilizador();
                            utilizador.setNome(textoNome);
                            utilizador.setEmail(textoEmail);
                            utilizador.setPass(textoPass);
                            registarUtilizador();

                        }else{
                            Toast.makeText(RegistoActivity.this, "Preencha a password", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegistoActivity.this, "Preencha o Email", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegistoActivity.this, "Preencha o nome", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void registarUtilizador(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                utilizador.getEmail(), utilizador.getPass()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegistoActivity.this, "Utilizador Registado com sucesso", Toast.LENGTH_SHORT).show();
                    finish();
                }else{

                    String excecao ="";
                    try {
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        excecao= "dige uma pass mais forte!";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "digite um email válido";
                    }catch (FirebaseAuthUserCollisionException e){
                        excecao = "esta conta já foi registada";
                    }catch (Exception e){
                        excecao = "Erro ao registar utilizador: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(RegistoActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}