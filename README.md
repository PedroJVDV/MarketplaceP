# MarketplaceP

API backend de um marketplace, desenvolvida com Java e Spring Boot.

O projeto ainda está em desenvolvimento e atualmente passa por uma fase de revisão da estrutura, segurança, performance e escalabilidade.

## Tecnologias

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- JWT
- MySQL
- Lombok
- Springdoc OpenAPI

## Estrutura atual

O projeto utiliza uma organização dividida em camadas:

- `controllers`: recebem e encaminham as requisições da API;
- `services`: concentram as regras de negócio;
- `repositories`: realizam o acesso ao banco de dados;
- `entities`: representam os dados persistidos;
- `DTOs`: controlam os dados recebidos e enviados pela API;
- `security`: contém as configurações e filtros de segurança;
- `exceptions`: centralizam o tratamento de erros;
- `enums`: concentram valores fixos utilizados pelo sistema.

## Funcionalidades atuais

O backend possui estruturas relacionadas a:

- autenticação e cadastro de usuários;
- login com JWT;
- controle de acesso por roles;
- usuários;
- produtos;
- pedidos;
- vendas;
- descontos;
- lista de desejos.

## Segurança

A aplicação utiliza o Spring Security para proteger os endpoints e controlar o acesso conforme o perfil do usuário.

O fluxo de autenticação utiliza JWT, e as senhas são armazenadas utilizando hash por meio do `PasswordEncoder`.

Também estão sendo revisados:

- contratos de entrada e saída dos endpoints;
- exposição de dados sensíveis;
- definição de permissões;
- fluxo de autenticação;
- validação de dados;
- separação entre informações fornecidas pelo cliente e regras definidas pelo sistema.

## Evolução atual

O projeto está passando por uma etapa de amadurecimento do backend. Neste momento, o foco está em validar e melhorar:

- segurança;
- organização das responsabilidades;
- tratamento de erros;
- performance;
- escalabilidade;
- contratos da API;
- testes;
- configuração do fluxo de autenticação.

### Issues e tarefas em andamento

- Revisão do fluxo de autenticação e das dependências do Spring Security;
- Validação do uso correto do `PasswordEncoder`;
- Revisão dos DTOs de entrada e saída;
- Fortalecimento do controle de roles e permissões;
- Melhorias no tratamento de exceções;
- Avaliação de performance e escalabilidade;
- Preparação da API para integração com a interface gráfica.

> Os status e detalhes dessas tarefas acompanham a evolução do projeto na aba Projects do GitHub.

## Próxima etapa

A fase final planejada para este projeto é a implementação da interface gráfica utilizando Angular.

Antes disso, o backend está sendo revisado para garantir uma base mais segura, organizada e preparada para crescer. A ideia é evitar que problemas de arquitetura, segurança ou contrato da API sejam levados para a camada visual.

## Futuro do projeto

O projeto passará por novas mudanças e receberá serviços maiores no futuro. Alguns desses serviços ainda não podem ser divulgados, mas a estrutura atual está sendo preparada para permitir essa evolução.

O MarketplaceP continuará sendo desenvolvido de forma gradual, com foco em uma base sólida para novas funcionalidades e integrações.

> ## Aviso importante sobre a licença

Antes de acessar, executar, copiar ou utilizar este projeto, leia atentamente o arquivo [`LICENSE`](./LICENSE).

Este projeto está protegido por uma **licença proprietária personalizada**. O acesso público ao repositório não concede autorização para usar, copiar, modificar, adaptar, distribuir, publicar, comercializar ou incorporar qualquer parte deste projeto em outro produto ou serviço.

Qualquer utilização, total ou parcial, depende de **autorização prévia e expressa, por escrito, do titular**. A ausência de autorização poderá constituir violação dos direitos aplicáveis e sujeitar o responsável às medidas legais cabíveis.

A simples visualização, clonagem ou download do repositório não representa concessão de licença ou permissão de uso.