# Entranhas do JUnit
Estudo de caso do `ComparisonCompactor` do JUnit.

Notas:
- Quando dois ou mais métodos de um classe devem ser executados em uma ordem 
específica, eles estão _temporalmente acoplados_. Nesse caso, seria melhor que
a primeira função retorne o argumento para o segundo método:

Acomplamento:

    method1();
    method2();

Da seguinte maneira fica explícita a importância das chamadas:

    arg1 = method1();
    method2(arg1);
  
Logo em seguida o autor desfez isso porque algum programador no futuro poderia
achar o uso do `arg1` um tanto arbitrário, pois não explica nada sobre a 
necessidade da ordem de chamada.

O que o autor fez em seguida:

    method1AndMethod2() {
      method1()
      // o corpo do method2 ...
    }



[<< Capítulo 14 - Emersão](./../chap14_SuccessiveRefinement/README.md)
|
[Capítulo 16 - Refatorando SerialDate >>](./../chap16_RefactoringSerialDate/README.md)

[• Início](../../README.md)
