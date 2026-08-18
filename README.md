# 🏥 Fila de Espera: Notificação Inteligente de Medicamentos

Sabe aquele problema clássico de ir até o posto de saúde buscar um remédio e descobrir que ele está em falta? Este projeto nasceu para resolver exatamente essa dor de cabeça, simulando um sistema que coloca os pacientes em uma **Fila de Espera** inteligente.

Quando o medicamento não tem estoque, o paciente entra na fila. Assim que o lote chega, o sistema dispara uma notificação (por SMS ou E-mail) avisando que ele já pode ir retirar! Simples e direto ao ponto.

## 🧠 Por Trás dos Panos (Onde a Magia Acontece)
Além de ser uma API REST funcional com Spring Boot, este repositório é também um laboratório de estudos. Durante o desenvolvimento, eu aproveitei para consolidar a aplicação prática de alguns dos **Padrões de Projeto (Design Patterns)** mais usados no mercado:

* **Singleton:** Garantido pelo próprio Spring Boot (via `@Service` e `@RestController`), mantendo instâncias únicas dos nossos serviços e economizando memória.
* **Strategy:** Cada paciente pode preferir ser avisado de um jeito. Usei o Strategy para encapsular as lógicas de envio (`SmsNotificacao` e `EmailNotificacao`), deixando o código pronto para ganhar novas formas de aviso no futuro (alô, WhatsApp!) sem bagunçar tudo.
* **Facade:** A classe `FilaFacade` faz o "trabalho sujo". Em vez de espalhar lógicas complexas (como salvar no banco, checar estoque e disparar notificação) direto no Controller, o Facade centraliza tudo, deixando a porta de entrada da API limpa e elegante.

## 🛠️ Tecnologias Utilizadas
* **Java 17**
* **Spring Boot 3** (Web, Data JPA)
* **Banco de Dados H2** (em memória, pra testar rapidinho sem dor de cabeça)

## 🚀 Como testar localmente?
1. Clone o repositório.
2. Abra na sua IDE (IntelliJ, Eclipse, VS Code).
3. Rode a aplicação a partir da classe `FilaApplication`.
4. Faça requisições POST para `/api/entrar-fila/{medicamentoId}` informando os dados do paciente e o tipo de notificação escolhida!

---
*Projeto desenvolvido como forma de unir o útil (um problema real do dia a dia) ao agradável (aprofundar estudos práticos em arquitetura e Design Patterns).*
