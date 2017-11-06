package br.edu.ifrn.agendaescolar.views;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifrn.agendaescolar.daos.AlunoDao;
import br.edu.ifrn.agendaescolar.models.Aluno;

public class AlunoListaView extends AppCompatActivity {

    private ListView lvAlunosLista;
    private Button btNovoAluno;
    private MenuItem miSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_lista_view);
        inicializarComponentes();
        definirEventos();



        Activity context = this;
        int layout = android.R.layout.simple_list_item_1;
        List<Aluno> lista = new AlunoDao().selectAll();

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(context, layout, lista);
        lvAlunosLista.setAdapter(adapter);
    }

    private void inicializarComponentes() {
        lvAlunosLista = (ListView) findViewById(R.id.alunos_lista);
        btNovoAluno = (Button) findViewById(R.id.novo_aluno);
        registerForContextMenu(lvAlunosLista);
    }

    private void definirEventos() {
        btNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irParaFormulario();
            }
        });
        lvAlunosLista.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });

    }

    private void irParaFormulario() {
        Intent vaiParaFormulario = new Intent(this, FormularioAlunoView.class);
        startActivity(vaiParaFormulario);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contexto, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Aluno aluno = (Aluno) lvAlunosLista.getItemAtPosition(info.position);
        switch(item.getItemId()) {
            case R.id.item_deletar:
                //Instrução pra deletar
                break;
            case R.id.item_site:
                Intent intentSite = new Intent(Intent.ACTION_VIEW);
                intentSite.setData(Uri.parse(aluno.getSite()));
                item.setIntent(intentSite);
                break;
            case R.id.item_mapa:
                Intent intentMapa = new Intent(Intent.ACTION_VIEW);
                intentMapa.setData(Uri.parse("geo:0,0?q=" + aluno.getEndereco()));
                item.setIntent(intentMapa);
                break;
            case R.id.item_sms:
                Intent intentSms = new Intent(Intent.ACTION_VIEW);
                intentSms.setData(Uri.parse("sms:" + aluno.getFone()));
                item.setIntent(intentSms);
                break;
            case R.id.item_ligar:
                Activity context = AlunoListaView.this;
                String[] permissions = new String[] {Manifest.permission.CALL_PHONE};
                int permission = ActivityCompat.checkSelfPermission(context, permissions[0]);
                int granted = PackageManager.PERMISSION_GRANTED;
                if (permission != granted) {
                    int requestCode = 123;
                    ActivityCompat.requestPermissions(context, permissions, requestCode);
                } else {
                    Intent intentLigar = new Intent(Intent.ACTION_CALL);
                    intentLigar.setData(Uri.parse("tel:" + aluno.getFone()));
                    item.setIntent(intentLigar);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
