# Capítulo 2 - Nomes com significado

- Nomes abreviados são uma má ideia.
- Nomes notavelmente diferentes dos outros.
- Nomeação inconsistente é **desinformação**.

- Nomes com números em série são o oposto de nomenclatura intencional
(`a1`, `a2`, etc).

- Escolha nomes pronunciáveis.

Ruim:
```java
class DtaRcrd102 {
  private Date genymdhms;
  private Date modymdhms;
  private final String pszqint = "102";
}
```

Melhor:
```java
class Costumer {
  private Date generationTimestamp;
  private Date modificationTimestamp;
  private final String recordId = "102";
}
```
- Escolha uma palavra por conceito. 
Escolha apenas uma entre `fetch`, `retrieve` e `get`.
Escolha apenas uma entre `controller`, `driver` e `manager`.