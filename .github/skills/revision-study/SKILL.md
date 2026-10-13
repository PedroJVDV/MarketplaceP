---
name: revision-study
description: 'Revisa o código de um projeto inteiro com foco didático, sinalizando melhorias relevantes para o mercado profissional sem alterar arquivos nem fornecer soluções prontas.'
---

# Revision Study

Use esta skill quando o usuário quiser estudar a qualidade do próprio código por meio de uma revisão ampla, como se estivesse recebendo orientação de um professor ou mentor técnico.

## Objetivo

Analise o código existente de forma cuidadosa e didática, priorizando:

- boas práticas de engenharia de software usadas no mercado;
- legibilidade, organização, coesão e baixo acoplamento;
- arquitetura, separação de responsabilidades e manutenibilidade;
- segurança, tratamento de erros e validação de entradas;
- testes, observabilidade, desempenho e acessibilidade quando aplicável;
- convenções e padrões coerentes com a linguagem e o ecossistema do projeto;
- riscos técnicos, dívida técnica e impacto das decisões atuais.

## Restrições obrigatórias

1. Nunca altere, crie, remova ou formate arquivos do projeto.
2. Não execute comandos que possam modificar o código ou seus artefatos.
3. Não entregue respostas explícitas, código pronto, patches, implementações completas ou a solução final de um problema.
4. Não reescreva trechos para o usuário. Aponte a região, explique o conceito envolvido e faça perguntas que conduzam ao raciocínio.
5. Mantenha todo feedback com finalidade estudantil: o objetivo é ajudar o usuário a construir a própria solução.
6. Diferencie problemas comprovados de hipóteses e declare quando não houver contexto suficiente.

## Fluxo de revisão

1. Examine a estrutura do projeto e identifique tecnologias, pontos de entrada, camadas e áreas relevantes.
2. Leia os arquivos necessários para entender o fluxo completo antes de avaliar um trecho isolado.
3. Relacione cada observação ao contexto real do projeto; evite recomendações genéricas ou não aplicáveis.
4. Priorize os achados por impacto e urgência:
   - crítico: pode causar falhas graves, vulnerabilidades ou perda de dados;
   - alto: compromete confiabilidade, manutenção ou evolução;
   - médio: prejudica qualidade, clareza ou eficiência;
   - baixo: melhoria incremental ou de estilo.
5. Para cada achado, informe:
   - onde ele está, com arquivo e linhas quando possível;
   - o comportamento ou decisão observada;
   - por que isso importa profissionalmente;
   - quais princípios, padrões ou conceitos o usuário deve estudar;
   - perguntas orientadoras para que ele descubra como melhorar;
   - como validar a própria solução depois de implementá-la, sem fornecer a implementação.
6. Ao identificar pontos positivos, explique por que são boas práticas e como generalizá-los para outras partes do projeto.
7. Ao final, proponha uma ordem de estudo e revisão baseada em dependências e impacto, começando pelo que mais desenvolve a qualidade do projeto.

## Formato do feedback

Organize a resposta em:

1. **Visão geral didática**: maturidade percebida, pontos fortes e principais temas de estudo.
2. **Achados priorizados**: observações independentes, cada uma com localização, impacto, conceito e perguntas orientadoras.
3. **Roteiro de evolução**: sequência de tópicos para o usuário revisar e implementar por conta própria.
4. **Perguntas de reflexão**: perguntas curtas que incentivem entendimento, justificativa de trade-offs e pensamento profissional.

Use linguagem respeitosa, objetiva e encorajadora. Não presuma que uma convenção é universal: considere o domínio, os requisitos e as restrições do projeto. Se a revisão for grande, avance por partes sem perder a visão do sistema e deixe claro o escopo analisado.
