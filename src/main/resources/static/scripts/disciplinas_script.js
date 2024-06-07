function buscarProfessores() {
    const professorSelect = document.getElementById('professor');

    fetch('/pessoas')
        .then(response => response.json())
        .then(pessoas => {

            pessoas.forEach(professor => {
                const option = document.createElement('option');
                option.text = professor.nomePessoa;

                if (professor.id) {
                    option.value = professor.id;
                    console.log(option);
                }

                professorSelect.appendChild(option);
            })
        })
}
buscarProfessores();

function buscarProfessoresEdit() {
    const professorSelect = document.getElementById('professorEdit');

    fetch('/pessoas')
        .then(response => response.json())
        .then(pessoas => {

            pessoas.forEach(professor => {
                const option = document.createElement('option');
                option.text = professor.nomePessoa;

                if (professor.id) {
                    option.value = professor.id;
                }

                professorSelect.appendChild(option);
                console.log(professor);
            })
        })
}

buscarProfessoresEdit();

const tabelaDisciplinas = document.getElementById('tabelaDisciplinas');
const tbody = tabelaDisciplinas.querySelector('tbody');

function buscarDisciplinas() {
    fetch('/disciplinas')
        .then(response => response.json())
        .then(disciplinas => {
            tbody.innerHTML = '';

            disciplinas.forEach(disciplina => {
                console.log(disciplina);
                const linha = document.createElement('tr');
                linha.id = disciplina.id;

                const celulaId = document.createElement('th');
                celulaId.textContent = disciplina.id;
                const celulaNome = document.createElement('th');
                celulaNome.textContent = disciplina.nomeDisciplina;
                const celulaCargaHoraria = document.createElement('th');
                celulaCargaHoraria.textContent = disciplina.cargaHoraria;
                const celulaProfessor = document.createElement('th');
                celulaProfessor.textContent = disciplina.professor.nomePessoa;
                const celulaLimiteAluno = document.createElement('th');
                celulaLimiteAluno.textContent = disciplina.limiteAlunos;
                const celulaAcoes = document.createElement('th')



                const botaoEditar = document.createElement('button');
                botaoEditar.classList.add('btn', 'btn-primary', 'btn-sm');
                botaoEditar.textContent = 'Editar';
                botaoEditar.dataset.disciplinaId = disciplina.id;
                botaoEditar.addEventListener('click', editarDisciplina);


                const botaoExcluir = document.createElement('button');
                botaoExcluir.classList.add('btn', 'btn-danger', 'btn-sm');
                botaoExcluir.textContent = 'Excluir';
                botaoExcluir.dataset.disciplinaId = disciplina.id;
                botaoExcluir.addEventListener('click', excluirDisciplina);

                celulaAcoes.appendChild(botaoEditar);
                celulaAcoes.appendChild(botaoExcluir);

                linha.appendChild(celulaId);
                linha.appendChild(celulaNome);
                linha.appendChild(celulaProfessor);
                linha.appendChild(celulaCargaHoraria);
                linha.appendChild(celulaLimiteAluno);
                linha.appendChild(celulaAcoes);


                tbody.appendChild(linha);
            });
        })
        .catch(error => console.error(error));
}

buscarDisciplinas();

function editarDisciplina(event) {
    const disciplinaId = event.target.dataset.disciplinaId;

    fetch(`/disciplinas/${disciplinaId}`)
        .then(response => response.json())
        .then(disciplina => {
            const nomeInput = document.getElementById('nome_disciplinaEdit');
            nomeInput.value = disciplina.nomeDisciplina;

            const ProfessorIdInput = document.getElementById('professorIdEdit');
            ProfessorIdInput.value = disciplina.professor.id;

            const CargaHorariaInput = document.getElementById('carga_horariaEdit');
            CargaHorariaInput.value = disciplina.cargaHoraria;

            const LimiteAlunosInput = document.getElementById('limite_alunosEdit');
            LimiteAlunosInput.value = disciplina.limiteAlunos;


            document.getElementById('disciplinaEditIdInput').value = disciplinaId;

            $('#edicaoModal').modal('show');
        })
        .catch(error => console.error(error));
}

function excluirDisciplina(event) {
    const disciplinaId = event.target.dataset.disciplinaId;
    console.log(disciplinaId);
    if (confirm(`Deseja realmente excluir?`)) {
        fetch(`/disciplinas/${disciplinaId}/delete`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (response.ok) {

                    alert('Disciplina excluída com sucesso!')
                    buscarDisciplinas();
                } else {

                    alert('Falha ao excluir disciplina. Tente novamente mais tarde.');
                }
            })
            .catch(error => {
                console.error('Erro ao excluir disciplina:', error);
            });
    }
}

const salvarButton = document.getElementById('salvar_btn');
salvarButton.addEventListener('click', salvarDisciplina);

const editarButton = document.getElementById('editar_btn')
editarButton.addEventListener("click", salvarEditDisciplina)

function salvarEditDisciplina() {
    const disciplinaId = document.getElementById('disciplinaEditIdInput').value;

    const disciplina = {
        id: disciplinaId,
        nomeDisciplina: document.getElementById('nome_disciplinaEdit').value,
        cargaHoraria: document.getElementById('carga_horariaEdit').value,
        professorId: document.getElementById('professorEdit').value,
        limiteAlunos: document.getElementById('limite_alunosEdit').value,
    };
    console.log(disciplina);

    fetch(`/disciplinas/editar`, {
        method: 'PUT', // ou 'POST' dependendo da sua API
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(disciplina),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao salvar os dados');
            }
            console.log('Resposta do servidor indica sucesso');
            console.log('Dados salvos com sucesso');
            alert("Dados salvos com sucesso!");
            buscarDisciplinas();

            $('#edicaoModal').modal('hide');
        })

        .catch(error => {
            console.error('Erro ao salvar os dados:', error);
            alert("Erro ao salvar os dados")
        });
}

function salvarDisciplina() {
    const disciplinaId = document.getElementById('disciplinaIdInput').value;
    console.log(disciplinaId);
    const disciplina = {
        nomeDisciplina: document.getElementById('nome_disciplina').value,
        cargaHoraria: document.getElementById('carga_horaria').value,
        professorId: document.getElementById('professor').value,
        limiteAlunos: document.getElementById('limite_alunos').value,
    };
    console.log(disciplina);

    fetch('/disciplinas/salvar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(disciplina)
    })
        .then(response => {
            if (response.ok) {
                atualizarLinhaTabela(disciplinaId, disciplina);

                $('#cadastroModal').modal('hide');

                alert('Disciplina salva com sucesso!');
                buscarDisciplinas();
            } else {
                console.error('Erro ao salvar:', response.status);
            }
        })
        .catch(error => console.error(error));

}

function atualizarLinhaTabela(disciplinaId, disciplinaAtualizada) {
    const linhas = tbody.querySelectorAll('tr');
    console.log(disciplinaAtualizada);
    for (const linha of linhas) {
        const celulaId = linha.querySelector('th:first-child'); // Primeira célula (ID)

        if (celulaId.textContent === disciplinaId.toString()) {
            const celulaNome = linha.querySelector('th:nth-child(2)');
            celulaNome.textContent = disciplinaAtualizada.nomeDisciplina;

            const celulaCargaHoraria = linha.querySelector('th:nth-child(4)');
            celulaCargaHoraria.textContent = disciplinaAtualizada.cargaHoraria;

            const celulaProfessor = linha.querySelector('th:nth-child(3)');
            celulaProfessor.textContent = disciplinaAtualizada.professorId.nomePessoa;

            const celulaLimiteAlunos = linha.querySelector('th:nth-child(5)');
            celulaLimiteAlunos.textContent = disciplinaAtualizada.limiteAlunos;

            break;
        }
    }
}