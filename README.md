# Projeto de programação multiplataforma

Este repositório foca na implementação do projeto da matéria de Programação Multiplataforma. Uma breve explicação do que foi pedido:

**Módulo 1** - API Rest de cadastro de pessoas, em que o registro deveria conter ID, nome, data de nascimento e se o registro está ativo. Deveria ser um CRUD com paginação no retorno, utilizar soft delete e ser adicionado ao Graylog.

**Módulo 2** - API Rest de login com usuário e senha. Deveria gerar um token JWT com Basic Auth Bearer. E, seguida era necessário criar um API Gateway e adicionar tanto a API de login, quanto outra API que seria acessada através da autenticação com o token.

**Módulo 3** - Lambda Function que escuta um tópico kafka de um producer e escreva no console se a mensagem chegou. Deveríamos gerar uma imagem Docker dessa aplicação e subir no Docker Hub através do Github Actions.

**Módulo 4** - Criar um kafka producer e dois kafka consumers. Garantir que uma mensagem seja enviada para as duas aplicações com GroupID e que tenha resiliência para que se um broker cair, as mensagens continuem sem problemas.

Todos os módulos deveriam ser desenvolvidos utilizando Java e o Spring Boot Framework, assim como deveriam ser containerizados em imagens Docker.

Também foi sugerido pelo professor de que se os 4 módulos funcionassem em conjunto, seriam acrescentados pontos extras à atividade. Optei por esta abordagem.

Dessa forma, as branches neste repositório foram organizadas assim:

- **pmp (default)**: API de pessoas que utiliza MySQL para banco de dados, JPA para persistência e Grafana+Loki para os logs
- **pmp-login**: API de login que armazena os usuários no banco de dados e faz a autenticação retornando um token JWT. Há também um producer kafka que gera uma mensagem ao chamar a rota de esquecimento de senha
- **pmp-gateway**: API Gateway que expõe os endpoints das APIs pmp e pmp-login e define a segurança das rotas.
- **pmp-lambda**: Função lambda que escuta o tópico kafka e escreve no console para qual usuário deve enviar o e-mail de recuperação.
- **pmp-consumer**: Consumer kafka que também escuta as mensagens da rota de esquecimento, mas não é uma função lambda e pode ter implementações mais complexas futuramente.

---

## Módulo 1

No primeiro módulo, foi desenvolvida a API de Pessoas (branch *pmp*) onde utilizei MySQL para o banco de dados e JPA para a persistência. Também foi solicitado que o Graylog fosse utilizado para a organização de Logs, mas por conta da minha escolha para o banco de dados, tive que optar por utilizar uma alternativa, já que o Graylog só funciona com MongoDB. Dessa forma utilizei o Loki+Grafana por gostar mais da interface e utilização.

Há a separação entre a camada de domínio e infraestrutura, para que as regras de negócio não sejam impactadas pelo uso do Spring Boot Framework.

---

## Módulo 2

No segundo módulo, implementei a API de Login (branch *pmp-login*). Utilizei o mesmo banco de dados, também com JPA para a persistência. Configurei os controllers de cadastro de usuário e autenticação. Em seguida, desenvolvi o API Gateway (branch *pmp-gateway*) que faria a comunicação entre a API de Login e a de Pessoas, expondo seus endpoints e sendo responsável pela configuração de acesso às rotas. Assim, seria necessário se autenticar na API de Login, obter o token JWT e utilizá-lo nas requisições par a API de cadastro de Pessoas, mas tudo isso com as rotas expostas pelo API Gateway.

Após o desenvolvimento, fiz a configuração do arquivo `docker-compose.yml`, onde adicionei os seguintes containers:

- *mysql* na porta **3306**
- *loki* na porta **3100**
- *grafana* na porta **3000**
- *promtail*
- *pmp* na porta **8082**
- *pmp-login* na porta **8081**
- *pmp-gateway* na porta **8080**

Mais tarde um producer kafka seria adicionado à API de Login, referente a uma rota de esquecimento de senha.

Aqui também há uma separação entre a camada de domínio e infraestrutura.

---

## Módulo 3

No terceiro módulo, adicionei um producer Kafka que enviaria uma mensagem com o tópico '*forget*' sempre que a rota de esquecimento de senha fosse acionada. Logo depois desenvolvi a função lambda (branch *pmp-lambda*) que escuta este tópico e imprime no console referenciando a qual usuário deve ser enviado o e-mail.

Após o desenvolvimento, adicionei mais um serviço ao `docker-compose.yml` para a lambda na porta **8083**

---

## Módulo 4

Desenvolvi um consumer Kafka (branch *pmp-consumer*) que escuta o tópico '*forget*' assim como a lambda, mas que pode ser replicado para a resiliência. Ele também escreve no console assim que detecta a mensagem.

Após o desenvolvimento, adicionei duas réplicas do serviço ao `docker-compose.yml`, nas portas 8084 e 8085, onde os dois serviços possuem o mesmo groupID fazendo com que se um estiver indisponível, o outro possa tomar a frente e receber as mensagens.

---

## Após o Desenvolvimento

Fiz a configuração do github actions para cada módulo que sobe a imagem Docker para a minha conta do Docker Hub

---

## Outras considerações

- A versão do Spring Boot utilizada é a `4.0.0`, e versão do Java é a `25`.
- Utilizei o **Insomnia** para fazer o teste das APIs.

---

## Como executar o projeto

### Acessando o código fonte
- Baixe ou clone todas as branches
- Execute `docker build -t <nome_da_branch>:latest .` dentro de projeto
- Dentro do projeto **pmp-gateway** execute `docker-compose up -d`

---

