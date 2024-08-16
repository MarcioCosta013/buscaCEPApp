package br.com.local.buscacepapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Button btn_Buscar;
    EditText txt_Cep;
    TextView lbl_Resposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_Buscar = findViewById(R.id.btn_Buscar);
        txt_Cep = findViewById(R.id.txt_Cep);
        lbl_Resposta = findViewById(R.id.lbl_Resposta);

        txt_Cep.setHint("Digite o CEP");  // Define um hint que aparece quando o campo está vazio

        btn_Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CEP retorno = new HttpService(txt_Cep.getText().toString().trim()).execute().get();

                    if (retorno != null){
                        lbl_Resposta.setText(retorno.toString());
                    } else {
                        lbl_Resposta.setText("CEP não encontrado ou erro na requisição.");
                    }

                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}