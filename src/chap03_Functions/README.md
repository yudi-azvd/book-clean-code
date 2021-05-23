# Capítulo 3 - Funções

A primeira regra de funções é que elas devem ser pequenas. A segunda regra é que elas devem ser menores que isso.

> FUNCTIONS SHOULD DO ONE THING. THEY SHOULD DO IT WELL.
> THEY SHOULD DO IT ONLY.

Como determinar se a função faz apenas uma coisa? Todas 
as linhas da função devem estar um nível de abstração
abaixo do nome da função.

_Outra maneira de determinar se a função faz apenas uma 
coisa é se não é possível extrair outras funções que não
sejam apenas uma reafirmação de sua implementação._

A melhor quantidade de argumentos é zero. Em seguida 1 argumento. Em seguida 2 argumentos e depois 3 argumentos. Mais que 3 argumentos já é muita bagunça. Quanto mais argumentos um função recebe, mais difícil fica de entendê-la e testá-la.

Mais difícil de entender ainda é o argumento que usado como saída da função, além do valor de retorno.

**Quando a função precisa de 3 argumentos ou mais é bem provável que 
o argumento deveria ser o objeto de uma classe:**

```java
Circle makeCircle(double x, double y, double radius)
Circle makeCircle(Point center, double radius)
```

## Separação entre Comando e Pedido
Uma função comumente _faz_ alguma coisa ou _obtém informação_ sobre alguma coisa. Mas quando faz as duas coisas deixas o código confuso:

```java
// confuso X(
if (set("username", "unclebob")) ...

// melhor! :D
if (attributeExists("username"))
  setAttribute("username", "unclebob")
```

## Lance exceções e não use retorno de código de erro.
Retorno de código de erro incentiva estruturas muito aninhadas.

```java
// Estrutura muito aninhada XXX
if (deletePage(page) == E_OK) {
  if (registry.deleteReference(page.name) == E_OK) {
    if (configKeys.deleteKey(page.name.makeKey()) == E_OK){
      logger.log("page deleted");
    } else {
      logger.log("configKey not deleted");
    }
  } else {
    logger.log("deleteReference from registry failed");
    }
} else {
  logger.log("delete failed");
  return E_ERROR;
}


// Estrutura mais direta OK
try {
  deletePage();
  registry.deleteReference(page.name);
  configKeys.deleteKey(page.name.makeKey());
} catch (Exception e) {
  logger.log(e.getMessage());
}
```

Tratamento de erros/exceções já é **uma coisa**. Se uma função faz tratamento de
erros, não deve fazer outra coisa além disso.

```java
public void delete(Page page) {
  try {
    deletePageAndAllReferences(page);
  }
  catch(Exception e) {
    logError(e);
  }
}

private void deletePageAndAllReferences(Page page) throws Exception { 
  deletePage();
  registry.deleteReference(page.name);
  configKeys.deleteKey(page.name.makeKey());
}

private void logError (Exception e) {
  logger.logError(e.getMessage());
}
```

## Como escrever funções assim?
Escrever programas é como escrever um artigo científico ou jornalístico. Não é 
no primeiro que rascunho que o texto vai ser agradável, claro e fácil de ler.

No primeiro rascunho os nomes podem ser ruins, podem haver duplicações, muitos 
argumentos e um corpo de função longo. Mais um grupo de testes unitários que 
cobre cada linha de código.

O próximo passo é reescrever essa função extraindo outras funções mais curtas e 
até mesmo uma classe para dar contexto. Escolher nomes apropriados e etc. Tudo 
isso enquanto os testes passam.  

_É difícil alguém escrever algo assim na primeira tentativa_.


---
[<< Capítulo 2 - Nomes com Significado ](./../chap02_MeaningfulNames/README.md) 
|
[Capítulo 4 - Comentários >>](./../chap04_Comments/README.md)

[• Início](../../README.md)

