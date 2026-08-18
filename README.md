**Fila de Espera: Notificação de Medicamentos**

A ideia aqui é simples: quando o remédio não tem em estoque, o paciente entra numa fila de espera. Assim que o lote chega, o sistema dispara um aviso por SMS ou e-mail falando "Disponivel pra retirar". Sem drama, sem ficar voltando lá todo dia só pra checar.


Além de ser uma API REST em Spring Boot, esse projeto também faz parte do meu laboratório pra treinar padrões de projeto:

**Singleton** o Spring resolve sozinho. Todos os `@Service` e `@RestController` já nascem como instância única, sem criar objeto à toa e gastar memória.

**Strategy** cada paciente tem sua preferência de como vai ser avisado. Em vez de inflar o código, separei cada jeito de notificar em uma classe própria (`SmsNotificacao`, `EmailNotificacao`), seguindo a mesma interface.

**Facade** em vez do Controller lutar pra salvar no banco, checar estoque e disparar notificação ao mesmo tempo, o Facade vai absorver essa bagunça por dentro e deixa o caminho da entrada da API limpo.


 **Tecnologias Utilizadas**
Java 17
Spring Boot 3 (Web, Data JPA)
Banco de Dados H2 


**Testar localmente**
1. Clonar o repositório.
2. Abrir na sua IDE.
3. Rodar a aplicação `FilaApplication`.
4. Fazer requisições POST para `/api/entrar-fila/{medicamentoId}` informar os dados do paciente e o tipo de notificação que escolher.


-------
Escolhi desenvolver esse projeto pra unir um problema real, que ja venho tentando aprimorar e aprofundar meu estudo prático em arquitetura e Design Patterns.
