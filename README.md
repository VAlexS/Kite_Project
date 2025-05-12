# Descripción del proyecto

## Este proyecto consiste en la gestión de una base de datos que consta de cometas y personas.

### Mi tablero de Trello https://trello.com/b/YTSiRLbZ/kite-project

1. Aquí tengo definido el UML y sus relaciones correspondientes 

```
@startuml
abstract class Kite{
- id: int
- windLimit: int
- shape: KiteShape
- line: LineType
- location: String
- owner: Person
  }

class StuntKite extends Kite {

}

class StaticKite extends Kite {
}

class TractionKite extends Kite {
}

class Person {
- userName: String
- password: String
- kites: Kite[]
- roles: Role[]
  }

enum KiteShape {
DELTA, DIAMOND, PARAFOIL
}

enum LineType {
SINGLE_LINE, DUAL_LINE
}

enum ERole {
ADMIN, USER
}

class Role {
- id: int
- rol: ERole
}

Kite "*" o-- "1" Person

Role "*" o-- "*" Person
@enduml
```

![img_19.png](img_19.png)

2. Así es como he creado el proyecto, con estás dependencias

![img_1.png](img_1.png)

3. Una vez levantado el servidor, me crea las tablas automáticamente, ya que en el fichero properties, tengo configurado esta opción

```spring.jpa.hibernate.ddl-auto=update```

Estás son las tablas resultantes, cuyas relaciones son estas, como se puede observar, el shape de la tabla kites es una columna
determinante, también llamada como discriminator value, ya que esta va a determinar el tipo de cometa que tiene la persona:

![img_20.png](img_20.png)

![img_21.png](img_21.png)

![img_22.png](img_22.png)

Como se puede observar en las imágenes, en la tabla kite, hay una columna especial llamada shape, ya que en Java, al haber una clase
madre (Kite) y varias clases hijas, que hacen referencia a los distintos tipos de cometas. Como aplicamos herencia, he escogido la estrategia
de una unica tabla con una columna discriminatoria.

4. Para que funcione la autenticación, he añadido esta dependencia en el fichero pom.xml

```
<dependency>
	<groupId>com.auth0</groupId>
	<artifactId>java-jwt</artifactId>
	<version>3.18.1</version>
</dependency>
```

5. Demos con postman

Actualmente, tras realizar varios test, tengo registros en las tablas persons y kites.

* Muestreo personas

Por un lado, en las tablas person tenemos esto:

![img_23.png](img_23.png)

Al mostrar las personas, nos lo muestra con sus cometas y sus roles

![img_25.png](img_25.png)

Lo mismo pasa cuando muestro una persona en particular

![img_28.png](img_28.png)

* Muestreo cometas

Por otro lado, en las tablas kites tenemos esto:

![img_29.png](img_29.png)


Por otro lado, al mostrar todas las cometas, simplemente muestro las cometas que hay en la base de datos.
Ya que de mostrar también a sus dueños, entrariamos en una redundancia ciclica y la salida se haría ilegible.

![img_31.png](img_31.png)

Ese mismo endpoint permite incluir parámetros para realizar búsquedas filtradas. 

 - Búsqueda por dueño
    
    ![img_32.png](img_32.png)

 - Búsqueda por ubicación donde se usan

    ![img_33.png](img_33.png)

 - Búsqueda por ambos filtros, tanto dueño como ubicación
    
    ![img_34.png](img_34.png)

Al obtener una cometa por id, me sale este resultado

![img_36.png](img_36.png)

* Modificaciones

En una API Rest, hay 2 formas de hacer modificaciones:

 - PUT: Para realizar modificaciones completas, por lo tanto, requiere que el usuario especifique todos los campos, lo que viene a ser
   un remplazo. Aunque haya campos que se desse mantener los valores, hay que especificarlos también con esos mismos valores.

 - PATCH: Para realizar modificaciones parciales, aquí se garantiza que se puedan modificar ciertas caracteristicas sin especificar 
   todos los campos.

Las modificaciones de cometas que he implementado de momento han sido parciales, de forma que se pueda modificar o bien el viento requerido
o bien la ubicación donde se use, garantizando que esa modificación solo la pueda realizar el dueño.

Ahora, hombre_de_la_rae va a intentar modificar el viento requerido y la ubicación del auronplay

![img_37.png](img_37.png)

![img_38.png](img_38.png)

La tabla permanece igual

![img_40.png](img_40.png)

Como se puede observar, no lo permite, ahora voy a hacer lo mismo, pero con el token del dueño correspondiente

![img_39.png](img_39.png)

![img_41.png](img_41.png)

Y si visualizamos de nuevo la tabla, ya está con los datos actualizados

![img_42.png](img_42.png)







