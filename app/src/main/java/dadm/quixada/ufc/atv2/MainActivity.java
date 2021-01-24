package dadm.quixada.ufc.atv2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT = "dadm.quixada.ufc.atv2.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "dadm.quixada.ufc.atv2.EXTRA_NUMBER";

    ListView listView;
    Button btnAdd;
    Button btnEdit;
    ArrayList<Carro> listaCarros;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listCarros);
        btnAdd = findViewById(R.id.buttonAdd);
        btnEdit = findViewById(R.id.buttonEdit);
        editText = findViewById(R.id.editTextTextPersonName);

       listaCarros = new ArrayList<Carro>();

        listaCarros.add(new Carro("teste1",0));
        listaCarros.add(new Carro("teste2",2));

        CarroAdapter carroAdapter = new CarroAdapter(this, R.layout.list_row,listaCarros);

        listView.setAdapter(carroAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivity(listaCarros.get(listaCarros.size()-1).getId(),"",0);
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivity(Integer.parseInt(editText.getText().toString()),"",1);
            }
        });

    }
    public void openActivity(int id, String nome, int acao){

        Intent intent = new Intent(this, MainActivity2.class);

        if(acao == 0){
            intent.putExtra("id", id+1);
            intent.putExtra("nome", nome);
            intent.putExtra("acao", acao);
        }else{
            String editNome = "";
            for (int i=0; i< listaCarros.size(); i++){
                if(listaCarros.get(i).getId() == id){
                    editNome = listaCarros.get(i).getNome();
                    break;
                }
            }
            intent.putExtra("id", id);
            intent.putExtra("nome", editNome);
            intent.putExtra("acao", acao);
        }


        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if (resultCode == RESULT_OK){
                int resultId = data.getIntExtra("id",0);
                String resultNome = data.getStringExtra("nome");
                int acao = data.getIntExtra("acao",0);

                if(acao == 0){
                    Toast.makeText(MainActivity.this,"ADD",Toast.LENGTH_SHORT).show();
                    listaCarros.add(new Carro(resultNome,resultId));
                    CarroAdapter carroAdapter = new CarroAdapter(this, R.layout.list_row,listaCarros);
                    listView.setAdapter(carroAdapter);
                }else{
                    Toast.makeText(MainActivity.this,"EDIT",Toast.LENGTH_SHORT).show();

                    for (int i=0; i< listaCarros.size(); i++){
                        if(listaCarros.get(i).getId() == resultId){
                            listaCarros.get(i).setNome(resultNome);
                            break;
                        }
                    }
                    CarroAdapter carroAdapter = new CarroAdapter(this, R.layout.list_row,listaCarros);
                    listView.setAdapter(carroAdapter);
                }
            }
        }
    }
}