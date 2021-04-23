# Capítulo 9 - Testes Unitários

## 3 Leis do [TDD](https://en.wikipedia.org/wiki/Test-driven_development)


**1ª Lei** <!--  Você não pode falar sobre o clube luta --> 
Você não pode escrever código de produção até você escrever um teste de unidade
falhando. 

**2ª Lei** 
Você não pode escrever mais do que é necessário de um teste de unidade pra ter 
um teste falhando. Não compilar também conta.

**3ª Lei**
Você não pode escrever mais do que é necessário de código de produção pra passar
no teste atual que está falhando.

Em outras palavras:

**1º passo** Escreva um teste de unidade que falha e depois comece a escrever
o código de produção.

**2º passo** Refatore o código de produção até passar no teste unitário.


## Tests Enable the -ilities
(Eu não soube traduzir essa seção)

É a presença de testes limpos que flexibiliza a mudança código. Se você mudar
algo no código do jeito errado, os testes serão os primeiros a te dizer que tem
alguma coisa errada. Nesse contexto você não precisa ter medo de introduzir bugs
no código quando mexer nele.


## Testes limpos
Legibilidade é a ordem da casa. Testes devem ser curtos, simples e densamente
expressivos, ou seja, devem falar muita coisa com poucas expressões.

Use métodos privados para abstrair ações e detalhes que não são interessantes 
para o teste em questão.

Use o pattern BUILD-OPERATE-CHECK. A primeira parte do teste monta a estrutura a
ser testada, a segunda parte realiza a operação sobre a estrutura e a terceira 
parte verifica que se o resultado da operação é o esperado.

## Linguagem de teste específica de domínio
O processo de escrever testes limpos é usar cada vez menos a API da própria 
linguagem de programação. Com o passar do tempo e a evolução do código, os 
testes vão ficando mais parecidos com o Listing9-2 e os programadores montam um 
grupo de funções e utilitários que compõem uma API específica para o domínio 
do problema, uma linguagem de teste específica para o domínio do problema.


---



[<< Capítulo 8 - Fronteiras](./../chap8_Boundaries/README.md)
|
<!-- [<< Capítulo 10 - ---](./../chap10_Classes/README.md) -->

[• Início](../../README.md)
