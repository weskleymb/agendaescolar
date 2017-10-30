package br.edu.ifrn.agendaescolar.views;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

        Aluno a1 = new Aluno("Pietro", "84999267947", "Rua Doutor Raimundo Verissimo, 108", "http://www.ifrn.edu.br", 8.0);
        Aluno a2 = new Aluno("Jean", "84988506387", "Rua Djalma Maranhão, 450", "http://www.youtube.com", 2.0);
        Aluno a3 = new Aluno("Willian", "84999092020", "Rua Nelson Mato, 328", "http://www.ufrn.br", 7.5);

        List<Aluno> alunos = new ArrayList<Aluno>();

        alunos.add(a1);
        alunos.add(a2);
        alunos.add(a3);

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(
                this,
                android.R.layout.simple_list_item_1,
                alunos);
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
            case R.id.item_site:
                Intent intentSite = new Intent(Intent.ACTION_VIEW);
                intentSite.setData(Uri.parse(aluno.getSite()));
                item.setIntent(intentSite);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
