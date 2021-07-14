# Refinamento Sucessivo
Assim como se escreve uma redação com várias versões antes da versão final, 
código deve ser escrito da mesma forma. Não se escreve código limpo na primeira
sentada. Na realidade, escreve código sujo e depois o limpamos pouco a pouco.


## Incrementalismo
Faça pequenas mudanças pouco a pouco, garantindo que o código funciona como 
anteriormente. A garantia de funcionamento é dada com TDD. A cada pequena 
mudança, deve ser verificado se os testes estão passando. **Se um teste não 
estiver passando, pare com os incrementos e mudanças e corrija para que os 
testes passem novamente.** 

Algumas maneiras:
- Antes de mover código para novas classes, criar classes internas. Com o tempo,
ir movendo código da classe principal para as classes internas
- classe -> classe abstrata -> interface (exemplo específico do estudo de caso)
- Abstrair estruturas de dados em classes
- Remover `if`s através de polimorfismo?
- Adicionar código que não faz diferença nenhuma -> fazer esse código funcionar
-> remover código anterior que fazia a mesma coisa

No caso de estudo do programa de linha de comando, o esquema é ir adicionando 
mudanças para cada tipo de argumento.


## Estudo de caso: Args
Args é o programa que extrai os argumentos passados a um programa pela linha de
comando usando uma string como esquema. Eu tentei imitar o incrementalismo do 
autor nos pacotes `args_draftxx`, mas acho que não deu muito certo por conta
dos testes unitários que só peguei depois. Fui pego de surpresa com a ideia de 
TDD já que esse conceito só foi apresentado quase no final do capítulo.

Ainda assim, recomendo a experiência.
Apenas lendo o capítulo você não vai absorver o processo que o autor quer 
passar.


[<< Capítulo 12 - Emersão](./../chap12_Emergence/README.md)
|
[Capítulo 15 - Entranhas do JUnit >>](./../chap15_JUnitInternals/README.md)

Não li o capítulo 13.

[• Início](../../README.md)

