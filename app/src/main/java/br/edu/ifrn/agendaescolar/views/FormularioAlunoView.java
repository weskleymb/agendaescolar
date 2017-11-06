package br.edu.ifrn.agendaescolar.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import br.edu.ifrn.agendaescolar.daos.AlunoDao;
import br.edu.ifrn.agendaescolar.models.Aluno;

public class FormularioAlunoView extends AppCompatActivity {

    private EditText etNome, etEndereco, etFone, etSite;
    private RatingBar rbNota;
    private Button btSalvar;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno_view);
        inicializarComponentes();
        definirEventos();
        aluno = new Aluno(etNome.getText().toString(), etFone.getText().toString(), etEndereco.getText().toString(), etSite.getText().toString(), Double.valueOf(rbNota.getProgress()));
    }

    private void inicializarComponentes() {
        etNome = (EditText) findViewById(R.id.formulario_nome);
        etEndereco = (EditText) findViewById(R.id.formulario_endereco);
        etFone = (EditText) findViewById(R.id.formulario_fone);
        etSite = (EditText) findViewById(R.id.formulario_site);
        rbNota = (RatingBar) findViewById(R.id.formulario_nota);
        btSalvar = (Button) findViewById(R.id.formulario_salvar);
    }

    private void definirEventos() {
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlunoDao().insert(aluno);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return true;
    }
}
