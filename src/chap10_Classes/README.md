# Capítulo 10 - Classes
Classes devem ser pequenas, assim como funções. Quão pequena? É só contar o 
número de responsabilidades.

**Single Responsibility Principle** uma classe deve ter apenas uma razão pra
_sofrer alteração_.

Como saber se uma classe tem muitas responsabilidades? Descreva a classe 
brevemente, 25 palavras, sem usar "e", "ou", "se" ou "mas".

Um sistema com poucas classes grandes é mais difícil de manter que um sistema
com muitas classes pequenas.

## Organização
Convenção muito popular em java, ordem de cima para baixo em uma classe: 

- Atributos estáticos públicos
- Atributos estáticos privados
- Atributos dinâmicos públicos
- Atributos dinâmicos privados
- Métodos públicos
- Métodos privados


## Encapsulamento
É possível definirmos atributos e métodos como protegidos para que eles sejam 
acessíveis pelos testes no mesmo pacote. Mas tente preservar o encapsulamento
antes de tudo.


## Coesão
Quanto mais variáveis de classe os métodos de uma classe acessam ou modificam, 
mais coesa essa classe é.

Se apenas uma parte dos métodos de uma classe usa uma parte dos atributos, talvez 
seja melhor extrair uma classe.


## Organização para mudança
**Open Closed Principle** uma classe deve estar fechada para mudanças e aberta
para extensão. Eu particularmente não gostei muito desse princípio no exemplo
dado no livro (queries de SQL). É lindo que pra adicionar funcionalidade só 
precisa criar uma classe com a funcionalidade desejada, mas como fica o uso 
dessas classes? Não parece fácil de usar como TypeORM ou o Sequelize.


## Isolando classes de mudanças
Classes que dependem de classes concretas estão à mercê das mudanças que ocorrem nessas classes concretas. Usando classes abstratas ou interfaces podemos fazer desacoplar uma classe da outra

**Dependency Inversion Principle (DIP)**  classes devem depender de abstrações e 
não de detalhes concretos.

Exemplo: isolar o comportamento de uma API, cujos resultados podem variar a cada
minuto, com uma interface. A classe que depende da API deve depender, na 
verdade, de uma interface que abstrai o comportamento observável e possível da 
API aceitando uma implementação da interface no construtor.

```java
public interface StockExchange {
  Money currentPrice(String symbol);
}

public class Portfolio {
  private StockExchange exchange;

  public Portfolio(StockExchange exch) {
    exchange = exch;
  }

  // ...
}

public class PortfolioTest {
  private FixedStockExchangeStub exchange;
  private Portfolio portfolio;

  @Before
  protected void setUp() {
    exchange = new FixedStockExchangeStub();
    exchange.fix("MSFT", 100);
    portfolio = new Portfolio(exchange);
  }

  @Test
  public given5MSFTTotalShouldBe500() {
    portfolio.add(5, "MSFT");
    assertEquals(500, portfolio.value());
  }
}
```

[<< Capítulo 9 - Fronteiras](./../chap09_UnitTests/README.md)
|
[Capítulo 11 - Sistemas >>](./../chap11_Systems/README.md)

[• Início](../../README.md)

