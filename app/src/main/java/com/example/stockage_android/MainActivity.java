package com.example.stockage_android;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText nomFichier,contenuET;
    Button remplacer,ajouter,charger;
    TextView contenuTV;
    ListView listFichiers;

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

        nomFichier=findViewById(R.id.nomFichier);
        contenuET=findViewById(R.id.contenuEditText);
        remplacer=findViewById(R.id.remplacer);
        ajouter=findViewById(R.id.ajouter);
        charger=findViewById(R.id.charger);
        contenuTV=findViewById(R.id.contenu);
        listFichiers=findViewById(R.id.listeFichier);


        var fileList = Arrays.asList(this.fileList());
        var files = new ArrayList<>(fileList);
        var adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, files);
        adapter.sort(Comparator.naturalOrder());
        listFichiers.setAdapter(adapter);

        listFichiers.setOnItemClickListener((parent,view,position,id)->{

        });

        remplacer.setOnClickListener(v->{
            String fileName=nomFichier.getText().toString();
            String contenu=contenuET.getText().toString();
            try{
                FileOutputStream fos=this.openFileOutput(fileName, Context.MODE_PRIVATE);
                fos.write(contenu.getBytes());
                fos.close();
            }catch (FileNotFoundException e){
                message(String.format("Creation du fichier %s",fileName));
            } catch (IOException e) {
                message(String.format("erreur en écrivant dans le fichier %s",fileName));
            }
        });
    }
    public void message(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();

    }
}