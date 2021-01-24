package dadm.quixada.ufc.atv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String nome = intent.getStringExtra("nome");
        int acao = intent.getIntExtra("acao",0);

        TextView txtId = findViewById(R.id.textView4);
        txtId.setText(""+id);

        Button btnAplicar = findViewById(R.id.buttonAplicar);
        EditText txtEdit = findViewById(R.id.editTextTextPersonName2);
        if(acao == 1){
            txtEdit.setText(nome);
        }

        btnAplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String novoNome = txtEdit.getText().toString();

                Intent intent1 = new Intent();

                intent1.putExtra("nome",novoNome);
                intent1.putExtra("id",id);
                intent1.putExtra("acao",acao);

                setResult(RESULT_OK,intent1);
                finish();
            }
        });
    }
}