# Capítulo 4 - Comentários
_Um mal necessário._

O uso de comentário é para compensar pela falha em nos expressar bem através de 
código. Cometnário é uma *_falha_*.

Antes de escrever um comentário, reescreva o código.

Comentários são, na melhor das hipóteses, verdades com data de validade. A única
fonte verdadeira é o código e uma hora os comentários que o acompanham ficam 
desatualizados ou se vão para longe do trecho original.

## Bons usos de comentários

```java
// Check to sse if the employee is eligible for full benefits
if (employee.flags && HOURLY_FLAG && (employee.age > 65))

// Bem melhor
if (employee.isEligibleForFullBenefits())
```

Definitivamente use comentários para Regex

```java
//format matched kk::mm::ss EEE, MMM, dd, yyyyy
Pattern timeMatcher = Pattern.compile(
  "\\d*:\\d*:\\d* \\w*, \\w* \\d*, \\d*"
);
```

Use comentários para explicar sua intenção e não para explicar o que o código
está fazendo.

`// TODO` é aceitável, desde que isso não seja usado como desculpa para deixar 
código mal escrito na code base.

Comentários para ressaltar a importância de certeos trechos de código que 
outra forma passariam por inconsequentes.

Comentários para documentação pública.

Comentários que servem de aviso para as consequências de rodar um trecho de 
código, como um teste unitário.

```java
// Don't run unless you have time to kill.
public void _testWithReallyBigFile() {
  // ...
}
```

Mas a seguinte forma pode ser melhor: 

```java
@Ignore("takes too long to run")
public void _testWithReallyBigFile() {
  // ...
}
```

## Maus usos de comentários
A maioria caem nessa categoria. Usados como desculpa para continuar com código 
mal escrito.

**Não deixe sobreviver código comentado**. Outros que veem código comentado não
têm a coragem para apagá-los por que dão a impressão que ainda são importantes.

Comentários que adicionam informações que que não pertecem ao contexto onde ele
se localiza.

Comentários óbvios.


```java
class User {
  private int age = 18; // user age
}
```

Javadocs para código que não é público: desnecessário.

---
[<< Capítulo 3 - Functions](./../chap3_Functions/README.md) |
[Capítulo 5 - Formatting >>](./../chap5_Formatting/README.md)

[• Início](../../README.md)

