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


## Dê contexto para as exceções
Cada exceção  lançada dever prover informação o suficiente para determinar a 
origem e o local do erro. Deve ficar clara a _intenção da operação_.

Use a mensagem da exceção para informar o tipo de falha.


## Defina a exceção em termos das necessidades de quem chama
Ruim, muita duplicação:
```java
ACMEPort port new ACMEPort(12);
try {
  port.open();
} catch(DeviceResponseException e) {
  reportPortError(e);
  logger.log("Device response exception", e);
} catch(ATM1212UnlockedException e) {
  reportPortError(e);
  logger.log("Unlock exception", e);
} catch(GMXError e) {
  reportPortError(e);
  logger.log("Device response exception", e);
} finally {
  // ...
}
```

Melhor: embrulhar a API e garantir que retorna um tipo comum de exceção.
```java
LocalPort port = new LocalPort(12);
try {
  port.open();
} catch(PortDeviceFailure e)  {
  reportError(e);
  logger.log(e.getMessage(), e);
} finally {
  // ...
}

public class LocalPort {
  private ACMEPort innerPort;

  public LocalPort(int portNumber) {
    innerPort = new ACMEPort(portNumber);
  }

  public void open() {
    try {
      innerPort.open();
    } catch(DeviceResponseException e) {
      throw new PortDeviceFailure(e);
    } catch(ATM1212UnlockedException e) {
      throw new PortDeviceFailure(e);
    } catch(GMXError e) {
      throw new PortDeviceFailure(e);
    }
  }
}
```

Embrulhar API de terceiros é uma boa prática. Isso minimiza as dependências sobre ela: você pode escolher mudar de biblioteca no futuro sem muito atrito. 
Também facilita criar o mock de código de terceiros. Também deixa o código no cliente mais limpo.

Geralmente uma única classe de exceção é o suficiente para uma área.


## Defina o fluxo normal
(Não achei interessante ou estava com preguiça de ler essa parte.)


## Não retorne null
Código com várias instâncias checando se o objeto é nulo deixa o código sujo e
fica fácil deixar de notar um lugar em que deveria ter mais uma checagem.

Não retorne `null`. 
Lance uma exceção, retorne um container (lista, array, etc) _vazio_ ou retorne
um 
[Objeto de Caso Especial](https://martinfowler.com/eaaCatalog/specialCase.html).
Se você está usando código de terceiro que retorna `null`, considere embrulhar 
esse código com um método que faça uma das coisas mencionadas acima.

Em java é possível usar `Collections.emptyList()`:

```java
public List<Employee> getEmployees() {
  if (/* there are no employees */) 
    return Collections.emptyList();
}
```

Isso minimiza a chance um `NullPointerException` ser lançado das profundezas do seu código.


## Não passe null
É isso mesmo. Se um método lança uma exceção do tipo
`InvalidArgumentException` alguém lá fora vai ter que tratar isso. O que esse 
alguém deveria fazer?

Apenas evite e proíba

## Conclusão
Separar a lógica do tratamento de erros. O programa deve ser legível _e_ 
robusto. 

---


[<< Capítulo 6 - Objetos e Estruturas de Dados](./../chap06_ObjectsAndDataStructures/README.md) 
|
[>> Capítulo 8 - Fronteiras](./../chap08_Boundaries/README.md)

[• Início](../../README.md)

