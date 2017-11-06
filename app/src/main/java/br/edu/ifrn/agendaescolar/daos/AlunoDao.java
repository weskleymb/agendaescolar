package br.edu.ifrn.agendaescolar.daos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import br.edu.ifrn.agendaescolar.models.Aluno;

public class AlunoDao {

    private static List<Aluno> alunos = new ArrayList<Aluno>();

    public AlunoDao() {
        Aluno a1 = new Aluno("Pietro", "84999267947", "Rua Doutor Raimundo Verissimo, 108", "http://www.ifrn.edu.br", 8.0);
        Aluno a2 = new Aluno("Jean", "84988506387", "Rua Djalma Maranhão, 450", "http://www.youtube.com", 2.0);
        Aluno a3 = new Aluno("Willian", "84999092020", "Rua Nelson Mato, 328", "http://www.ufrn.br", 7.5);

        alunos.add(a1);
        alunos.add(a2);
        alunos.add(a3);
    }

    public void insert(Aluno aluno) {
        alunos.add(aluno);
    }

    public List<Aluno> selectAll() {
        return Collections.unmodifiableList(alunos);
    }

}
