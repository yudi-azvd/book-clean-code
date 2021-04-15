# Capítulo 7 - Tratamento de Erros
O algoritmo do tratamento de erros deve estar separado da lógica da aplicação.

Use exceções ao invés de códigos de retorno.

Escreva `try-catch-finally` primeiro por que isso _define o escopo_ do programa.
De certo modo, blocos `try` são como transações. O `catch` deve sair do programa
em um estado consistente, independente do que acontecer no `try`. Isso ajuda a
definir o que o usuário do código deve esperar, independente do que acontecer no `try`.

## Use Unchecked Exceptions
No contexto de Java, para a maioria das aplicações, usar exceções não checadas é
melhor. 

Se um método em um nível de abstração muito baixo lança uma exceção checada, 
**todos os métodos** entre ele e um método alguns níveis de abstração mais acima
que tem o `catch` devem incluir a exceção checada na assinatura do método. Isso 
fere o princípio do SOLID Open/Closed Principle.

No [exemplo](Class3.java) (exemplo muito besta, mas ilustra o impacto), a 
exceção checada do método de baixo nível `Class1.readFile` obrigou todos os 
métodos mais acima que o usam também mudem suas assinaturas para incluir `throw`. Se não fosse isso, deveriam usar o `catch` (todas os métodos ou apenas um?).

Isso quebra o encapsulamento porque cascateia as mudanças do mais baixo nível do 
programa até o mais alto.

---


[<< Capítulo 6 - Objetos e Estruturas de Dados](./../chap6_ObjectsAndDataStructures/README.md) 
|
<!-- [>> Capítulo 8 - Limites](./../chap7_ErrorHandling/README.md) -->

[• Início](../../README.md)


