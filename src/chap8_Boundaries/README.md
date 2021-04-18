# Capítulo 8 - Fronteiras

Abstrair o uso de código de terceiro em uma interface específica para a nossa
aplicação **nas fronteiras** do sistema.

> Interface abrangente do código de terceiro 
> -> 
> interface específica para aplicação

## Aprendendo e explorando fronteiras
Usamos códigos de terceiros para economizar tempo. Mas entender esse código é
difícil. Difícil também é integrá-lo no sistema.

**Testes de aprendizado**: chamamos o código como esperamos que ele funciona
no nosso sistema.

Exemplo na suite de testes [LogTest.java](LogTest.java). Testes de aprendizado 
verificam se entendemos corretamente o funcionamento da biblioteca. 
Se houver um novo lançamento no futuro, podemos verificar se o comportamento 
mudou executando a suite novamente. _Se houver breaking changes saberemos_
_prontamente_.

## Fronteiras limpas
Quando usamos códigos de terceiros temos que garantir que as mudanças 
que ocorrerem no futuro não sejam muito custosas. Nosso código não deve saber
de sua existência.

Fazemos isso limitando a poucos os pontos no código que tem contato com o código
de terceiros. Fazemos usando o [Adapter Pattern]() para converter da interface
que usamos para a interface provida.


---

[<< Capítulo 7 - Tratamento de Erros](./../chap7_ErrorHandling/README.md)
|
[<< Capítulo 9 - Testes Unitários](./../chap9_UnitTests/README.md)

[• Início](../../README.md)
