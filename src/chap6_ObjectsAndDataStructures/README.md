# Capítulo 6 - Objetos e Estruturas de Dados

> There is a reason that we keep our variables private. We don’t want anyone else 
> to depend on them. We want to keep the freedom to change their type or
> implementation on a whim or an impulse. Why, then, do so many programmers  
> automatically add getters and setters to their objects, exposing their private 
> variables as if they were public?

## Abstração de dados

Faça uma classe e exponha sua abstração na interface e não os seus detalhes 
de implementação. **Não adianta apenas deixar as variáveis de classe privadas 
e acrescentar _setters_ e _getters_**. É preciso dedicar um tempo para pensar
em como representar os dados que um objeto contém.

O usuário vai manipular a essência desses dados.

```java
// Expõe detalhes de implementação!!!
class Point {
  double x;
  double y;
}
```

```java
// Esconde detalhes de implementação 
// e expõe a abstração de um ponto 
// através da interface pública.
class Point {
  private double x;
  private double y;
  void setCartesian(double x, double y);
  void setPolar(double r, double theta);
  double getR();
  double getTheta();
}
```

