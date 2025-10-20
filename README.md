# SerraMusic - Nathan Lourenço

> Nesse projeto foi criada uma API que permite o armazenamento de músicas, artitas e playlists que são vinculadas a um usuário que tem o seu perfil criado no programa!

## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:

- Você instalou `<JAVA 17.0.16 / Maven 3.9.11 / Postman/Insomnia / IDE Java (VSCode, Eclipse, IntelliJ, etc./>`

## ☕ Usando o SerraMusic

Para usar o SerraMusic, siga estas etapas:

Clone o repositório, importe ele através do Maven em seu editor de preferência, selecione e rode como um aplicativo java a classe SerratecMusicApllication.java localizada em: SerratecMusic/src/main/java/org.serratec.SerratecMusic. 

> Você pode acessar a interface do Swagger através deste link: http://localhost:8080/swagger-ui/index.html
##
> No Postman você deve passar o seguinte endereço padrão: http://localhost:8080/

##

## Modelos para as requisições e endpoints (JSON):

### POST /usuarios: Ao criar um novo usuário, o seu Perfil deve ser criado junto, em uma única requisição (JSON aninhado).

{
  "nome": "Ana Souza",
  "email": "ana.souza@email.com",
  "perfil": {
    "telefone": "(22) 98765-4321",
    "dataNascimento": "15/05/1995"
  }
}

### POST /playlists: Ao criar uma nova playlist, ela deve ser associada a um Usuario já existente (informando o id do usuário no body).

{
  "nome": "Indie para Relaxar",
  "descricao": "Músicas calmas para o fim de tarde.",
  "usuario": {
    "id": 1
  }
}

### PUT /playlists/{id}: Ao atualizar uma playlist, deve ser possível alterar sua lista de Músicas (adicionando ou removendo músicas existentes na lista, informando seus ids).

{
  "nome": "Indie para Relaxar (Atualizada)",
  "descricao": "A melhor seleção de indie do momento.",
  "usuario": {
    "id": 1
  },
  "musicas": [
    {
      "id": 2
    },
    {
      "id": 3
    }
  ]
}

### Obs: Tive um problema na execução desse código, erro 415, só foi corrigido através do uso do@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Playlist.class), pelo o que pesquisei não é o ideal, mas foi a única coisa que fez funcionar, na dúvida deixei essa linha comentada apenas para quando for fazer essa requisição. 

### Modelos JSON:

### Artistas

{
  "nome": "Queen",
  "nacionalidade": "Britânica"
}

### Músicas

{
  "titulo": "Bohemian Rhapsody",
  "minutos": 6,
  "generoMusical": "ROCK",
  "artistas": [
    {
      "id": 1
    }
  ]
}

### Usuário + Perfil

{
  "nome": "Carlos Silva",
  "email": "carlos.silva@exemplo.com",
  "perfil": {
    "telefone": "(21) 99887-6655",
    "dataNascimento": "25/08/1990"
  }
}

### Playlist

{
  "nome": "Clássicos do Rock",
  "descricao": "Minhas músicas de rock favoritas",
  "usuario": {
    "id": 1
  },
  "musicas": [
    {
      "id": 1
    }
  ]
}

