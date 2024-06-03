const tabelaPessoas = document.getElementById('tabelaPessoas');
const tbody = tabelaPessoas.querySelector('tbody');

// Função para buscar dados da API e preencher a tabela
function buscarPessoas() {
    fetch('/pessoas')
        .then(response => response.json())
        .then(pessoas => {
            tbody.innerHTML = ''; // Limpar linhas existentes

            pessoas.forEach(pessoa => {
                const linha = document.createElement('tr');
                linha.id = pessoa.id;

                const celulaId = document.createElement('th');
                celulaId.textContent = pessoa.id;
                const celulaNome = document.createElement('th');
                celulaNome.textContent = pessoa.nomePessoa;
                const celulaEndereco = document.createElement('th');
                celulaEndereco.textContent = pessoa.endereco;
                const celulaUf = document.createElement('th');
                celulaUf.textContent = pessoa.uf;
                const celulaTel = document.createElement('th');
                celulaTel.textContent = pessoa.telefone;
                const celulaCPF = document.createElement('th');
                celulaCPF.textContent = pessoa.cpf;
                const celulaEmail = document.createElement('th');
                celulaEmail.textContent = pessoa.email;
                const celulaAcoes = document.createElement('th'); // Nova célula para ações


                // Criar botão Editar
                const botaoEditar = document.createElement('button');
                botaoEditar.classList.add('btn', 'btn-primary', 'btn-sm'); // Estilos Bootstrap
                botaoEditar.textContent = 'Editar';
                botaoEditar.dataset.pessoaId = pessoa.id; // Armazenar ID da pessoa
                botaoEditar.addEventListener('click', editarPessoa); // Evento de clique

                // Criar botão Excluir
                const botaoExcluir = document.createElement('button');
                botaoExcluir.classList.add('btn', 'btn-danger', 'btn-sm'); // Estilos Bootstrap
                botaoExcluir.textContent = 'Excluir';
                botaoExcluir.dataset.pessoaId = pessoa.id; // Armazenar ID da pessoa
                botaoExcluir.addEventListener('click', excluirPessoa);

                celulaAcoes.appendChild(botaoEditar);
                celulaAcoes.appendChild(botaoExcluir);
                // Adicione outras colunas conforme necessário (ex: email, idade)

                linha.appendChild(celulaId);
                linha.appendChild(celulaNome);
                linha.appendChild(celulaEndereco);
                linha.appendChild(celulaUf);
                linha.appendChild(celulaTel);
                linha.appendChild(celulaCPF);
                linha.appendChild(celulaEmail);
                linha.appendChild(celulaAcoes);

                // ... adicionar outras células ...

                tbody.appendChild(linha);
            });
        })
        .catch(error => console.error(error));
}

buscarPessoas();

function editarPessoa(event) {
    const pessoaId = event.target.dataset.pessoaId;

    fetch(`/pessoas/${pessoaId}`)
        .then(response => response.json())
        .then(pessoa => {
            // Preencher campos do modal
            const nomeInput = document.getElementById('nome');
            nomeInput.value = pessoa.nomePessoa;

            const cpfInput = document.getElementById('cpf');
            cpfInput.value = pessoa.cpf;

            const emailInput = document.getElementById('email');
            emailInput.value = pessoa.email;

            const enderecoInput = document.getElementById('endereco');
            enderecoInput.value = pessoa.endereco;

            const ufInput = document.getElementById('uf');
            ufInput.value = pessoa.uf;

            const telefoneInput = document.getElementById('telefone');
            telefoneInput.value = pessoa.telefone;

            document.getElementById('pessoaIdInput').value = pessoaId;

            // Abrir modal de edição pré-existente
            $('#cadastroModal').modal('show');
        })
        .catch(error => console.error(error));
}

function excluirPessoa(event) {
    const pessoaId = event.target.dataset.pessoaId;
    console.log(pessoaId);
    if (confirm(`Deseja realmente excluir?`)) {
        fetch(`/pessoas/${pessoaId}/delete`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (response.ok) {
                    // Remover a linha da pessoa da interface do usuário

                    alert('Pessoa excluída com sucesso!')
                    buscarPessoas();
                } else {
                    // Exibir mensagem de erro
                    alert('Falha ao excluir pessoa. Tente novamente mais tarde.');
                }
            })
            .catch(error => {
                console.error('Erro ao excluir pessoa:', error);
            });
    }
}


const salvarButton = document.getElementById('salvar_btn');
salvarButton.addEventListener('click', salvarPessoa);

function salvarPessoa() {
    const pessoaId = document.getElementById('pessoaIdInput').value;
    const pessoa = {
        id: pessoaId, // Obter ID da pessoa (já existente)
        nomePessoa: document.getElementById('nome').value,
        endereco: document.getElementById('endereco').value,
        uf: document.getElementById('uf').value,
        telefone: document.getElementById('telefone').value,
        cpf: document.getElementById('cpf').value,
        email: document.getElementById('email').value
    };

    fetch('/pessoas/salvar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(pessoa)
    })
        .then(response => {
            if (response.ok) {
                atualizarLinhaTabela(pessoaId, pessoa);

                $('#cadastroModal').modal('hide');

                alert('Pessoa salva com sucesso!');
                buscarPessoas();
            } else {
                console.error('Erro ao salvar alterações:', response.status);
                // Handle error gracefully (e.g., display an error message to the user)
            }
        })
        .catch(error => console.error(error));

}


function atualizarLinhaTabela(pessoaId, pessoaAtualizada) {
    const linhas = tbody.querySelectorAll('tr');

    for (const linha of linhas) {
        const celulaId = linha.querySelector('th:first-child'); // Primeira célula (ID)

        if (celulaId.textContent === pessoaId.toString()) {
            const celulaNome = linha.querySelector('th:nth-child(2)');
            celulaNome.textContent = pessoaAtualizada.nomePessoa;

            const celulaEndereco = linha.querySelector('th:nth-child(3)');
            celulaEndereco.textContent = pessoaAtualizada.endereco;

            const celulaUf = linha.querySelector('th:nth-child(4)');
            celulaUf.textContent = pessoaAtualizada.uf;

            const celulaTelefone = linha.querySelector('th:nth-child(5)');
            celulaTelefone.textContent = pessoaAtualizada.telefone;

            const celulaCPF = linha.querySelector('th:nth-child(6)');
            celulaCPF.textContent = pessoaAtualizada.cpf;

            const celulaEmail = linha.querySelector('th:nth-child(7)');
            celulaEmail.textContent = pessoaAtualizada.email;


            // ... atualizar outras células conforme necessário ...

            break; // Parar a iteração após encontrar a linha
        }
    }
}