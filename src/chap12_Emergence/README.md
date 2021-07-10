# Emersão
4 regras simples para o Design Simples, em ordem de importância:

1. Executa todos os testes
1. Não contém duplicação
1. Expressa claramente a intenção do programador
1. Minimiza o número de classes e métodos


## Executa todos os testes
É bem claro.


## Não contém duplicação
Usar o pattern TEMPLATE-METHOD. Uma super classe abstrata implementa os métodos
em comum, que seriam duplicados sem o TEMPLATE-METHOD. Essa super classe ainda 
disponibiliza a assinatura de um método template (protegido) que deve ser 
sobrescrito por uma classe filha que contém uma lógica específica, código que 
não é duplicado.


## Expressa claramente a intenção do programador
O maior custo de um projeto de software é a manutenção a longo prazo. Escrever 
código expressivo e fácil de entender minimiza esse custo.

- Expressa claramente com bons nomes. Não há supresa ao descobrir as 
responsabilidades de uma classe ou função quando ouvir seu nome.
- Expressa claramente com classes e métodos pequenos. 
- Expressa claramente com nomenclatura bem estabelecida. Ex: prefixo I para 
interfaces, nome do pattern no nome da classe. 
- Expressa claramente com testes unitários, que servem como documentação como
exemplo. 


## Minimiza o número de classes e métodos
Regra com menos importância. Geralmente essa regra entra em jogo quando o Single
Responsability Principle, Injeção de Dependência, eliminação de duplicação são 
levados ao extremo.


[<< Capítulo 11 - Sistemas](./../chap11_Systems/README.md)
|
[Capítulo 14 - Refinamento Sucessivo >>](./../chap14_SuccessiveRefinement/README.md)

Não li o capítulo 13.

[• Início](../../README.md)
