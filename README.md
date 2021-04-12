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

## Modelo do banco de dados
A partir dos dados disponibilizados, junto com os especialistas do time, mapeamos os dados no seguinte modelo relacional

![alt text](https://github.com/carlosalexandre10/ampla-api/blob/develop/doc/bd.png?raw=true)
