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
                }

                // Adicionar opção ao select
                professorSelect.appendChild(option);
                console.log(professorSelect);
                console.log(professor);
            })
        })
}
buscarProfessores();


const tabelaDisciplinas = document.getElementById('tabelaDisciplinas');
const tbody = tabelaDisciplinas.querySelector('tbody');

// Função para buscar dados da API e preencher a tabela
function buscarDisciplinas() {
    fetch('/disciplinas')
        .then(response => response.json())
        .then(disciplinas => {
            tbody.innerHTML = ''; // Limpar linhas existentes

            disciplinas.forEach(disciplina => {
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
                const celulaLmtAluno = document.createElement('th');
                celulaLmtAluno.textContent = disciplina.lmtAlunos;
                const celulaAcoes = document.createElement('th')


                // Criar botão Editar
                const botaoEditar = document.createElement('button');
                botaoEditar.classList.add('btn', 'btn-primary', 'btn-sm'); // Estilos Bootstrap
                botaoEditar.textContent = 'Editar';
                botaoEditar.dataset.disciplinaId = disciplina.id; // Armazenar ID da pessoa
                botaoEditar.addEventListener('click', editarDisciplina); // Evento de clique

                // Criar botão Excluir
                const botaoExcluir = document.createElement('button');
                botaoExcluir.classList.add('btn', 'btn-danger', 'btn-sm'); // Estilos Bootstrap
                botaoExcluir.textContent = 'Excluir';
                botaoExcluir.dataset.disciplinaId = disciplina.id; // Armazenar ID da pessoa
                botaoExcluir.addEventListener('click', excluirDisciplina);

                celulaAcoes.appendChild(botaoEditar);
                celulaAcoes.appendChild(botaoExcluir);

                linha.appendChild(celulaId);
                linha.appendChild(celulaNome);
                linha.appendChild(celulaProfessor);
                linha.appendChild(celulaCargaHoraria);
                linha.appendChild(celulaLmtAluno);
                linha.appendChild(celulaAcoes);

                // ... adicionar outras células ...

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
            // Preencher campos do modal
            const nomeInput = document.getElementById('nome_disciplina');
            nomeInput.value = disciplina.nomeDisciplina;

            const ProfessorIdInput = document.getElementById('professorId');
            ProfessorIdInput.value = disciplina.professor;

            const CargaHorariaInput = document.getElementById('carga_horaria');
            CargaHorariaInput.value = disciplina.cargaHoraria;

            const LmtAlunosInput = document.getElementById('limite_alunos');
            LmtAlunosInput.value = disciplina.lmtAlunos;


            document.getElementById('disciplinaIdInput').value = disciplinaId;

            // Abrir modal de edição pré-existente
            $('#cadastroModal').modal('show');
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
                    // Remover a linha da pessoa da interface do usuário

                    alert('Disciplina excluída com sucesso!')
                    buscarDisciplinas();
                } else {
                    // Exibir mensagem de erro
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

function salvarDisciplina() {
    const disciplinaId = document.getElementById('disciplinaIdInput').value;
    console.log(disciplinaId);
    const disciplina = {
        id: disciplinaId, // Obter ID da pessoa (já existente)
        nomeDisciplina: document.getElementById('nome_disciplina').value,
        cargaHoraria: document.getElementById('carga_horaria').value,
        professor: document.getElementById('professor').value,
        lmtAlunos: document.getElementById('limite_alunos').value,
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
                // Handle error gracefully (e.g., display an error message to the user)
            }
        })
        .catch(error => console.error(error));

}


function atualizarLinhaTabela(disciplinaId, disciplinaAtualizada) {
    const linhas = tbody.querySelectorAll('tr');

    for (const linha of linhas) {
        const celulaId = linha.querySelector('th:first-child'); // Primeira célula (ID)

        if (celulaId.textContent === disciplinaId.toString()) {
            const celulaNome = linha.querySelector('th:nth-child(2)');
            celulaNome.textContent = disciplinaAtualizada.nomeDisciplina;

            const celulaCargaHoraria = linha.querySelector('th:nth-child(3)');
            celulaCargaHoraria.textContent = disciplinaAtualizada.cargaHoraria;

            const celulaProfessor = linha.querySelector('th:nth-child(4)');
            celulaProfessor.textContent = disciplinaAtualizada.professor;

            const celulaLmtAlunos = linha.querySelector('th:nth-child(5)');
            celulaLmtAlunos.textContent = disciplinaAtualizada.lmtAlunos;

            break; // Parar a iteração após encontrar a linha
        }
    }
}