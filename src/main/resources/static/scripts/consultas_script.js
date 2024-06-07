function buscarPessoas() {
    const pessoasSelect = document.getElementById('pessoaNome');

    fetch('/pessoas')
        .then(response => response.json())
        .then(pessoas => {

            pessoas.forEach(pessoa => {
                const option = document.createElement('option');
                option.text = pessoa.nomePessoa;

                if (pessoa.id) {
                    option.value = pessoa.id;
                }

                pessoasSelect.appendChild(option);
                console.log(pessoa);
            })
        })
}
buscarPessoas();


document.addEventListener("DOMContentLoaded", function() {

    function buscarDisciplinas() {
        const pessoaId = document.getElementById('pessoaNome').value; // Obtém o ID da pessoa selecionada
        const tabelaDisciplinas = document.getElementById('tabelaDisciplinas').getElementsByTagName('tbody')[0]; // Obtém o corpo da tabela

        tabelaDisciplinas.innerHTML = '';

        fetch(`/disciplinas/aluno/${pessoaId}`)
            .then(response => response.json())
            .then(disciplinas => {
                disciplinas.forEach((disciplina) => {
                    const newRow = tabelaDisciplinas.insertRow();
                    newRow.innerHTML = `
                        <td>${disciplina.id}</td>
                        <td>${disciplina.nomeDisciplina}</td>
                        <td>${disciplina.cargaHoraria}</td>
                        <td>${disciplina.professor.nomePessoa}</td>
                    `;
                });
            })
            .catch(error => console.error('Erro ao buscar disciplinas:', error));
    }

    document.getElementById('consultarDisciplinasBtn').addEventListener('click', buscarDisciplinas);
});

$(document).ready(function() {
    $('#consultarFaturamentoBtn').click(function() {
        const disciplinaId = $('#disciplinaId').val();
        const dataInicio = convertDateFormat($('#dataInicio').val());
        const dataFim = convertDateFormat($('#dataFim').val());
        $.ajax({
            url:'/matriculas/faturamento',
            type: 'GET',
            data: {
                disciplinaId: disciplinaId,
                dataInicio: dataInicio,
                dataFim: dataFim
            },
            success: function(response) {
                let tabela = $('#tabelaFaturamento tbody');
                tabela.empty();

                tabela.append(`
                    <tr>
                        <td>${dataInicio} - ${dataFim}</td>
                        <td>${response.toFixed(2)}</td>
                    </tr>
                `);
            },
            error: function(error) {
                console.log(error);
            }
        });
    });
});

function convertDateFormat(dateString) {
    let dateParts = dateString.split("-");
    return dateParts[0] + "/" + dateParts[1] + "/" + dateParts[2];
}