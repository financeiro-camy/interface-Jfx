# Gerenciador financeiro: CAMY growth

Uma aplicação bastante intuitiva feita com o objetivo de facilitar e organizar a vida financeira de qualquer estudante. Organiza as finanças de maneira rápida a partir dos dados do usuário, poupando tempo e permitindo a realização de projetos úteis

Este projeto foi desenvolvido para avaliação nas disciplinas de Linguagem de Programação 1 (LP1) e Banco de Dados (BDD), ambas ministradas sob a orientação do professor Domingos Lucas Latorre.


## Apêndice

#### **Projeto JavaFX usando Maven**

**A aplicação desktop foi desenvolvida utilizando:**

- Para a construção do **programa**, foram utilizadas as linguagens: SQL (Structured Query Language) e Java.
- Para a construção da **interface, foi utilizada JavaFX, que inclui componentes como a linguagem de marcação** *FXML*. Essa biblioteca permite a integração da interface com o código java, além de promover a integração do código com folhas de estilo CSS.

### Sumário: 

- Tela Apresentação 
- Tela Login 
- Tela Cadastro 

#### Formulários:

- Cadastrar Conta Dinheiro
- Cadastrar Projeto Cofrinho 
- Realizar uma inserção ao projeto 
- Cadastrar Despesa 
- Cadastrar Receita 
- Personalizar Periodicidade

- Tela Menu 
- Tela Logout

 #### DAO'S: 

- UsuarioDAO: gerencia o armazenamento das informações da tabela Usuario
- UsuarioAtributoDAO: gerencia o armazenamento das informações da tabela UsuarioAtributoDAO (é usada para armazenar o id do usuário logado) 
- CategoriaDAO: gerencia as informações armazenadas da tabela Categoria 
- PeriodicidadeDAO: gerencia as informações armazenadas da tabela Periodicidade
- ContasDinheiroDAO: gerencia o armazenamento das informações da tabela ContasDinheiro 
- ProjetoCofrinhoDAO: gerencia o armazenamento das informações da tabela ProjetoCofrinho
- RelatorioPCDAO: gerencia o armazenamento das informações da tabela RelatorioPC (utilizada para guardar as inserções de valores no cofrinho)
- LancamentoDAO: gerencia o armazenamento das informações da tabela Lancamento (utilizada para guardar as inserções de valores na conta)

## Autores

- [@ca12loss](https://github.com/ca12loss): *Carlos Alberto*
- [@LiceeIF](https://github.com/LiceeIF): *Alice Maria*
- [@MarianaMegumi](https://github.com/MarianaMegumi): *Mariana Megumi*
- [@gyyoshida](https://github.com/gyyoshida): *Guilherme Yoshida*



## Instalação

Instale **financeiro-camy/interface-Jfx** e abra no ambiente Visual Studio Code *(vscode)*

bash
  mkdir folder
  cd folder
  git clone https://github.com/financeiro-camy/interface-Jfx.git
  code interface-Jfx

    
## Como executar

Clone o repositório em sua máquina

- Remover a pasta .git:
bash
rm -rf .git

- **não esqueça de trocar o endereço da database para que o código seja alocado em sua própria database!**

No mesmo terminal que foi configurado o proxy executar o comando:
bash
./mvnw javafx:run
