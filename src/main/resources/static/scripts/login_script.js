// Seleciona os botões de login e registro
const loginButton = document.getElementById('login_btn');
const registerButton = document.getElementById('registro_btn');


// Adiciona eventos aos botões
loginButton.addEventListener('click', Login);
registerButton.addEventListener('click', Registrar);

// Função para manipular o evento de login
function Login() {
    const usernameImput = document.getElementById('username').value;
    const passwordImput = document.getElementById('password').value;

    const username = usernameImput.toString();
    const password = passwordImput.toString();

    console.log(username);
    console.log(password);

    if (username === '' || password === '') {
        alert('Por favor, preencha todos os campos.');
        return;
    }

    const credencial = {
        nomeUsuario: username,
        senha: password
    };

    fetch('usuario/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(credencial)
    })
        .then(response => {
            if (response.ok) {
                return response.json(); // Parse JSON da resposta
            } else if (response.status === 401) {
                throw new Error('Credenciais inválidas');
            } else {
                throw new Error('Erro ao tentar fazer login');
            }
        })
        .then(data => {
            if (data === true) { // Se a resposta for 'true', redireciona para 'template.html'
                window.location.href = 'template.html';
            } else {
                throw new Error('Autenticação falhou');
            }
        })
        .catch(error => {
            console.error(error);
            alert(error.message);
        });

}

// Função para manipular o evento de registro
function Registrar() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    if (username === '' || password === '') {
        alert('Por favor, preencha todos os campos.');
        return;
    }

    const credencial = {
        nomeUsuario: username,
        senha: password
    };

    fetch('/usuario/registrar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(credencial)
    })
        .then(response => {
            if (response.ok) {
                alert("Usuario Cadastrado!")
            } else {
                throw new Error('Erro ao tentar registrar');
            }
        })
        .catch(error => {
            console.error(error);
            alert(error.message);
        });
}
