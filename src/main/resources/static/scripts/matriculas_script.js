function buscarAlunos() {
    const alunoSelect = document.getElementById('aluno');

    fetch('/pessoas')
        .then(response => response.json())
        .then(pessoas => {

            pessoas.forEach(aluno => {
                const option = document.createElement('option');
                option.text = aluno.nomePessoa;

                if (aluno.id) {
                    option.value = aluno.id;
                }

                // Adicionar opção ao select
                alunoSelect.appendChild(option);
            })
        })
}
buscarAlunos();
function buscarAlunosEdit() {
    const alunoSelect = document.getElementById('alunoEdit');

    fetch('/pessoas')
        .then(response => response.json())
        .then(pessoas => {

            pessoas.forEach(aluno => {
                const option = document.createElement('option');
                option.text = aluno.nomePessoa;

                if (aluno.id) {
                    option.value = aluno.id;
                }
                
                alunoSelect.appendChild(option);
            })
        })
}
buscarAlunosEdit();
function buscarDisciplinas() {
    const disciplinaSelect = document.getElementById('disciplina');

    fetch('/disciplinas')
        .then(response => response.json())
        .then(disciplinas => {

            disciplinas.forEach(disciplina => {
                const option = document.createElement('option');
                option.text = disciplina.nomeDisciplina;

                if (disciplina.id) {
                    option.value = disciplina.id;
                }

                // Adicionar opção ao select
                disciplinaSelect.appendChild(option);
            })
        })
}
buscarDisciplinas();
function buscarDisciplinasEdit() {
    const disciplinaSelect = document.getElementById('disciplinaEdit');

    fetch('/disciplinas')
        .then(response => response.json())
        .then(disciplinas => {

            disciplinas.forEach(disciplina => {
                const option = document.createElement('option');
                option.text = disciplina.nomeDisciplina;

                if (disciplina.id) {
                    option.value = disciplina.id;
                }

                // Adicionar opção ao select
                disciplinaSelect.appendChild(option);
            })
        })
}
buscarDisciplinasEdit();

const tabelaMatriculas = document.getElementById('tabelaMatriculas');
const tbody = tabelaMatriculas.querySelector('tbody');

function buscarMatriculas() {
    fetch('/matriculas')
        .then(response => response.json())
        .then(matriculas => {
            tbody.innerHTML = '';

            matriculas.forEach(matricula => {
                console.log(matricula)
                const linha = document.createElement('tr');
                linha.id = matricula.idMat;

                const celulaId = document.createElement('th');
                celulaId.textContent = matricula.idMat;
                const celulaNomeAluno = document.createElement('th');
                celulaNomeAluno.textContent = matricula.aluno.nomePessoa;
                const celulaNomeDisciplina = document.createElement('th');
                celulaNomeDisciplina.textContent = matricula.disciplina.nomeDisciplina;
                const celulaValorPago = document.createElement('th');
                celulaValorPago.textContent = matricula.valorPago;
                const celulaDataMatricula = document.createElement('th');
                celulaDataMatricula.textContent = matricula.dataMatricula;
                const celulaPeriodo = document.createElement('th');
                celulaPeriodo.textContent = matricula.periodo;
                const celulaAcoes = document.createElement('th')

                // Criar botão Editar
                const botaoEditar = document.createElement('button');
                botaoEditar.classList.add('btn', 'btn-primary', 'btn-sm');
                botaoEditar.textContent = 'Editar';
                botaoEditar.dataset.matriculaId = matricula.idMat;
                botaoEditar.addEventListener('click', editarMatricula);

                // Criar botão Excluir
                const botaoExcluir = document.createElement('button');
                botaoExcluir.classList.add('btn', 'btn-danger', 'btn-sm'); // Estilos Bootstrap
                botaoExcluir.textContent = 'Excluir';
                botaoExcluir.dataset.matriculaId = matricula.idMat; // Armazenar ID da pessoa
                botaoExcluir.addEventListener('click', excluirMatricula);

                celulaAcoes.appendChild(botaoEditar);
                celulaAcoes.appendChild(botaoExcluir);

                linha.appendChild(celulaId);
                linha.appendChild(celulaNomeAluno);
                linha.appendChild(celulaNomeDisciplina);
                linha.appendChild(celulaValorPago);
                linha.appendChild(celulaDataMatricula);
                linha.appendChild(celulaPeriodo);
                linha.appendChild(celulaAcoes);

                // ... adicionar outras células ...

                tbody.appendChild(linha);
                console.log(matricula)
            });
        })
        .catch(error => console.error(error));
}
buscarMatriculas();

function editarMatricula(event) {
    const matriculaId = event.target.dataset.matriculaId;

    fetch(`/matriculas/${matriculaId}`)
        .then(response => response.json())
        .then(matricula => {
            // Preencher campos do modal
            const AlunoIdInput = document.getElementById('alunoEdit');
            AlunoIdInput.value = matricula.aluno.id;

            const disciplinaIdInput = document.getElementById('disciplinaEdit');
            disciplinaIdInput.value = matricula.disciplina.id;

            const ValorPagoInput = document.getElementById('valorEdit');
            ValorPagoInput.value = matricula.valorPago;

            const PeriodoInput = document.getElementById('periodoEdit');
            PeriodoInput.value = matricula.periodo;


            document.getElementById('matriculaIdEditInput').value = matriculaId;

            // Abrir modal de edição pré-existente
            $('#edicaomodal').modal('show');
        })
        .catch(error => console.error(error));
}

function excluirMatricula(event) {
    const matriculaId = event.target.dataset.matriculaId;
    console.log(matriculaId);
    if (confirm(`Deseja realmente excluir?`)) {
        fetch(`/matriculas/${matriculaId}/delete`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (response.ok) {
                    // Remover a linha da pessoa da interface do usuário

                    alert('Matricula excluída com sucesso!')
                    buscarMatriculas();
                } else {
                    // Exibir mensagem de erro
                    alert('Falha ao excluir matricula. Tente novamente mais tarde.');
                }
            })
            .catch(error => {
                console.error('Erro ao excluir matricula:', error);
            });
    }
}

const salvarButton = document.getElementById('salvar_btn');
salvarButton.addEventListener('click', salvarMatricula);

function salvarMatricula() {
    const matriculaId = document.getElementById('matriculaIdInput').value;
    console.log(matriculaId);
    const matricula = {
        disciplinaId: document.getElementById('disciplina').value,
        valorPago: document.getElementById('valor').value,
        alunoId: document.getElementById('aluno').value,
        periodo: document.getElementById('periodo').value,
    };
    console.log(matricula);

    fetch('/matriculas/salvar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(matricula)
    })
        .then(response => {
            if (response.ok) {

                $('#cadastroModal').modal('hide');

                alert('Matricula salva com sucesso!');
                buscarMatriculas();
            } else {
                console.error('Erro ao salvar:', response.status);
                // Handle error gracefully (e.g., display an error message to the user)
            }
        })
        .catch(error => console.error(error));

}

const editarButton = document.getElementById('editar_btn');
editarButton.addEventListener('click', salvarEditMatricula);

function salvarEditMatricula() {
    const matriculaId = document.getElementById('matriculaIdEditInput').value;

    const matricula = {
        idMat: matriculaId,
        disciplinaId: document.getElementById('disciplinaEdit').value,
        valorPago: document.getElementById('valorEdit').value,
        alunoId: document.getElementById('alunoEdit').value,
        periodo: document.getElementById('periodoEdit').value,
    };
    console.log(matricula);

    fetch(`/matriculas/editar`, {
        method: 'PUT', // ou 'POST' dependendo da sua API
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(matricula),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao salvar os dados');
            }
            // return response.json();
            console.log('Resposta do servidor indica sucesso');
            console.log('Dados salvos com sucesso');
            alert("Dados salvos com sucesso!");
            buscarMatriculas();

            $('#edicaomodal').modal('hide');
        })
        .catch(error => {
            console.error('Erro ao salvar os dados:', error);
        });
}

function atualizarLinhaTabela(matriculaId, matriculaAtualizada) {
    const linhas = tbody.querySelectorAll('tr');

    for (const linha of linhas) {
        const celulaId = linha.querySelector('th:first-child'); // Primeira célula (ID)

        if (celulaId.textContent === matriculaId.toString()) {
            const celulaNomeAluno = linha.querySelector('th:nth-child(2)');
            celulaNomeAluno.textContent = matriculaAtualizada.alunoid.nomePessoa;

            const celulaNomeDisciplina = linha.querySelector('th:nth-child(3)');
            celulaNomeDisciplina.textContent = matriculaAtualizada.disciplinaId.nomeDisciplina;

            const celulaValorPago = linha.querySelector('th:nth-child(4)');
            celulaValorPago.textContent = matriculaAtualizada.valorPago;

            const celulaDataMatricula = linha.querySelector('th:nth-child(5)');
            celulaDataMatricula.textContent = matriculaAtualizada.dataMatricula;

            break;
        }
    }
}