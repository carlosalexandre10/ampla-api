# INPI API
Este projeto destina-se ao Hack INPI da equipe **Ampla Assessoria**

## Integrantes
- Carlos Alexandre da Silva Oliveira
- Diego Américo Guedes – Responsável pela equipe
- Frederico Augusto Martins Correia
- Iamê Domingues Silva
- Shalon Andrade Santos

## Categoria 
Acreditamos que a categoria que mais se adequa é a de serviço, por ser uma Tecnologia inovadora e otimização de serviços para sociedade.

## Problemas do INPI a serem resolvidos
- Sobrecarregada do servidor de consulta de marcas causado por Robôs;
- Acesso a consulta de marcas limitado e não inteligente.

## Como a solução resolve o problema
A solução é a criação de uma API para consulta de dados públicos de marcas já fornecidos na web sem autenticação de usuário.
- A API pode ser disponibilizada em um servidor diferente. Além disso, não será necessário que robôs tentem replicar a base de dados de marcas;
- A API pode fornecer diferentes serviços como, por exemplo, se o registro da marca já foi publicada na revista.


## Resultados esperados
- Descongestionamento do servidor de busca de marcas;
- Melhoramento de processos internos e gestão de marcas das empresas que atuam com registro de marca;
- Incentivo a operações de Startup e automatização de etapas do registro de marca.

## Tecnologias Utilizadas
Utilizando o framework open source [Java Spring](https://spring.io/) com o Java 8. Especificamente, utilizamos as seguintes tecnologias _open source_:
- _Spring Boot_ para inicialização automático do servidor de aplicação
- _Spring Data_ para mapeamento do banco de dados relacional
- _Spring Security_ para autenticação e gerenciamento de permissões de usuários
- _Lombok_ para mapeamento automático de construtores, getter, setter, etc
- _Postgres_ como banco de dados relacional
- _ElasticSearch_ para uma possível utilização de um banco de dados NoSQL e separado do banco de dados utilizado para outras funcionalidades

## Modelo do banco de dados
A partir dos dados disponibilizados, junto com os especialistas do time, mapeamos os dados no seguinte modelo relacional

![alt text](https://github.com/carlosalexandre10/ampla-api/blob/develop/doc/bd.png?raw=true)

## Testando a API
A API foi testada utilizando a ferramente livre [Insomnia](https://insomnia.rest/download) . Nosso arquivo Insomnia está [neste link](https://github.com/carlosalexandre10/ampla-api/blob/develop/doc/Insomnia_2021-04-11.json)

## Vídeo do Pitch

O vídeo do nosso pitch está no Youtube [neste link](https://www.youtube.com/watch?v=BcxcW8N01kA)

## Descrição dos serviços disponíveis na API

### Cadastro de usuários
----
  Serviço destino ao cadastro de usuários que irão utilizar a API. Somente usuários admin podem criar novos usuários. Retorna o JSON do usuário cadastrado

* **URL**

  /usuarios

* **Método:**  
   `POST` 
  
*  **Parâmetros da URL**

   **Requerido:**
 
    Não tem  

* **Parâmetros**

  {
	"nome": "NOME_USUARIO",
	"email": "EMAIL",
	"senha": "SENHA",
	[OPCIONAL]"roles": [{"id": 1},{"id": 2}]
}

* **Resposta de sucesso:**  
  
  * **Código:** 201 <br />
    **Conteúdo:** `{
  "id": "f844c075-d9b0-468b-bbac-f1deb2ed5ec2",
  "nome": "NOME_USUARIO",
  "email": "EMAIL"
}`
 
* **Resposta de Erro:**

  * **Code:** 405 UNAUTHORIZED <br />
    **Content:** `{
  "errors": [
    "Email já foi cadastrado!"
  ]
}`

   OU

  * **Code:** 423 METHOD NOT ALLOWED <br />
    **Content:** `{ error : "Email Invalid" }`
    
 
### Envio de CSV
----
  Como não tínhamos um banco cadastrado, populamos os dados a partir dos CSV disponibilizados. Somente usuários ADMIN podem inserir dados. Em princípio, esse serviço não faria sentido em um ambiente de produção no INPI

* **URL**

  /leitorCSV/upload

* **Método:**  
   `POST` 
  
*  **Parâmetros da URL**

   **Requerido:**
 

*  **Parâmetros no Header **

Token Bearer de ADMIN

* **Parâmetros**

  {
	"file": ARQUIVO_CSV
}

### Pegar Autenticação
----
 Obter o token requerido na maioria dos serviços da API.

* **URL**

  /login

* **Método:**  
   `POST` 
  
*  **Parâmetros da URL**

   **Requerido:**
Não tem

*  **Parâmetros no Header**

Não tem

* **Parâmetros**

  {
	"nome": "NOME_USUARIO",
	"email": "EMAIL",
}



### Consultar marca
----
 Consultar se a marca existe na base de dados do INPI

* **URL**

  /marcas

* **Método:**  
   `GET` 
  
*  **Parâmetros da URL**

   **Requerido:**
NOME_MARCA
[OPCIONAL] radical

*  **Parâmetros no Header**

BEARER TOKEN

* **Parâmetros**

  Não tem

* **Resposta de sucesso:**  
  
  * **Código:** 200 OK <br />
    **Conteúdo:** `{
    "numeroProcesso": 913973807,
    "nomeMarca": "Shadowplay",
    "descricaoSituacao": "Pedido definitivamente arquivado",
    "descricaoApresentacao": "Mista",
    "descricaoNatureza": "De Produto",
    "classificacaoViena": "4 - 5.5.20     - Flores estilizadas  /  4 - 27.5.1     - Letras apresentando um grafismo especial",
    "nomeRepresentanteLegal": "",
    "dataDeposito": "2018-01-01",
    "dataConcessao": null,
    "dataVigencia": null,
    "nomeTitular": "ROGERIA ROSA XERXENEVSKY - ME"
  }`

### Consultar número processo
----
 Consultar o número do processo da marca

* **URL**

 /marcas/processo

* **Método:**  
   `GET` 
  
*  **Parâmetros da URL**

   **Requerido:**
Número do processo

*  **Parâmetros no Header**
BEARER TOKEN

* **Parâmetros**

  Não tem

* **Resposta de sucesso:**  
  
  * **Código:** 200 OK <br />
    **Conteúdo:** `{
  "numeroProcesso": 914046853,
  "nomeMarca": "Masterchefe Bolo",
  "descricaoSituacao": "Registro de marca em vigor",
  "descricaoApresentacao": "Nominativa",
  "descricaoNatureza": "De Serviço",
  "classificacaoViena": "",
  "nomeRepresentanteLegal": "",
  "dataDeposito": "2018-01-19",
  "dataConcessao": "2019-04-24",
  "dataVigencia": "2029-04-24",
  "nomeTitular": "J J PRADO CONSULTORIA ME"
}`

### Consultar marca no ElasticSearch
----
 Consultar se a marca existe na base de dados do INPI no ElasticSearch

* **URL**

  /marcas-elasticearch

* **Método:**  
   `GET` 
  
*  **Parâmetros da URL**

   **Requerido:**
NOME_MARCA

*  **Parâmetros no Header**

BEARER TOKEN

* **Parâmetros**

  Não tem

* **Resposta de sucesso:**  
  
  * **Código:** 200 OK <br />
    **Conteúdo:** `{
    "numeroProcesso": 913973807,
    "nomeMarca": "Shadowplay",
    "descricaoSituacao": "Pedido definitivamente arquivado",
    "descricaoApresentacao": "Mista",
    "descricaoNatureza": "De Produto",
    "classificacaoViena": "4 - 5.5.20     - Flores estilizadas  /  4 - 27.5.1     - Letras apresentando um grafismo especial",
    "nomeRepresentanteLegal": "",
    "dataDeposito": "2018-01-01",
    "dataConcessao": null,
    "dataVigencia": null,
    "nomeTitular": "ROGERIA ROSA XERXENEVSKY - ME"
  }`

### Consultar Nice
----
 Consultar código nice

* **URL**

  /nices

* **Método:**  
   `GET` 
  
*  **Parâmetros da URL**

   **Requerido:**
CODIGO_NICE

*  **Parâmetros no Header**

BEARER TOKEN

* **Parâmetros**

  Não tem

* **Resposta de sucesso:**  
  
  * **Código:** 200 OK <br />
    **Conteúdo:** `[
  {
    "codigoClassificacaoNice": 42,
    "numeroRevisaoClassificacaoNice": 11,
    "especificacaoClassificacaoNice": "Certificação de Comércio varejista de roupas ,atacadista de roupas,fabricantes de roupas",
    "marca": {
      "numeroProcesso": 914039431,
      "nomeMarca": "Be Yourself",
      "descricaoSituacao": "Pedido definitivamente arquivado",
      "descricaoApresentacao": "Mista",
      "descricaoNatureza": "Certific.",
      "classificacaoViena": "4 - 9.3.1      - Roupas  /  4 - 9.3.14     - Roupas de baixo, cuecas, fraldas, roupas de banho  /  4 - 9.3.26     - Grupos de roupas de diferentes espécies  /  4 - 9.3.2      - Paletós, casacos, mantôs, capas  /  4 - 9.3.9      - Camisas, camisetas, corpetes, blusas de manga comprida",
      "nomeRepresentanteLegal": "",
      "dataDeposito": "2018-01-18",
      "dataConcessao": null,
      "dataVigencia": null,
      "nomeTitular": "VINICIUS BASILIO GASPAR"
    }
  }
]`
  
  
