# Descripción del proyecto

## Este proyecto consiste en la gestión de una base de datos que consta de cometas y personas.

### Mi tablero de Trello https://trello.com/b/YTSiRLbZ/kite-project

### Presentación con slides 
https://www.canva.com/design/DAGnVvLkai8/cfejTn0jg6HY0nOd8G5iuQ/edit?utm_content=DAGnVvLkai8&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton

1. Aquí tengo definido el UML y sus relaciones correspondientes 

```
@startuml
abstract class Kite{
- id: int
- windRequired: int
- shape: KiteShape
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

![img_50.png](img_50.png)

2. Así es como he creado el proyecto, con estás dependencias

![img_1.png](img_1.png)

3. Una vez levantado el servidor, me crea las tablas automáticamente, ya que en el fichero properties, tengo configurado esta opción

```spring.jpa.hibernate.ddl-auto=update```

Estás son las tablas resultantes, cuyas relaciones son estas, como se puede observar, el shape de la tabla kites es una columna
determinante, también llamada como discriminator value, ya que esta va a determinar el tipo de cometa que tiene la persona:

![img_51.png](img_51.png)

![img_52.png](img_52.png)

![img_53.png](img_53.png)

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

Inicialmente, tenemos las tablas así

* Persons
![img_58.png](img_58.png)

* Kites (he vaciado esta tabla para documentar las demos de los insert)
![img_55.png](img_55.png)

* Roles (inserto los datos manualmente en MySQL)
![img_56.png](img_56.png)

* Persons_roles (inserto los datos manualmente en MySQL)
![img_57.png](img_57.png)


  
#### Inserciones
  
  Las inserciones requieren el token de un administrador, de no tener el token de administrador, no va a ejecutar la petición

  - Nuevas cometas
    
   Por ejemplo, le asigno a hombre_de_la_rae
   
   ![img_63.png](img_63.png)
   
   ![img_64.png](img_64.png)
   
   ![img_65.png](img_65.png)

   Ahora voy a asignarle 2 cometas a auronplay
   
   ![img_66.png](img_66.png)

   ![img_67.png](img_67.png)
   
   La tabla resultante de kites que nos queda es esta
   
   ![img_68.png](img_68.png)

  - Nuevas personas
    
    Para crear nuevas personas, requiere que el que lo realice tenga como rol un administrador, para ello, he creado mediante un test
    un administrador para que me encripte la contraseña del admin. 
     
    ![img_44.png](img_44.png)
  
   Recordemos que los roles son estos.
   
   ![img_45.png](img_45.png)
   
   Y la tabla que gestiona los roles es ésta
   ![img_46.png](img_46.png)

   Cada vez que añada una nueva persona, dependiendo del rol, hay que hacer manualmente en MySQL este insert, el rol_id hace referencia al
   número que tiene el id del rol en la tabla de roles.
     ```
     insert into persons_roles (person_username, roles_id) values (<'userName'>, <rol_id>);
     ```

 Aquí estoy insertando a una nueva persona, le he pasado el token de un administrador
  
 ![img_47.png](img_47.png)
 
 Si inserto otra persona, pero sin pasarle el token no me va a dejar, tampoco me va a dejar si le paso el token cuyo rol no es ADMIN
 ![img_49.png](img_49.png)

#### Muestreo

* Muestreo de personas
  
  Al mostrar personas, muestra tanto los roles como las cometas que tiene dicha persona
  
  - Muestro todas las personas
     
     ![img_69.png](img_69.png)
  
  - Muestro una persona por username
     
    ![img_70.png](img_70.png)


* Muestreo de cometas
   
   - Muestro todas las cometas
      
     ![img_71.png](img_71.png)
  
   - Muestro las cometas registradas en Madrid
     
      ![img_72.png](img_72.png)
  
   - Muestro las cometas que pertenecen a hombre_de_la_rae
      
      ![img_73.png](img_73.png)
  
   - Muestro las cometas que pertenecen a hombre_de_la_rae y que estén registradas en Madrid
     
     ![img_74.png](img_74.png)
  
   - Muestro una cometa por id
      
     ![img_75.png](img_75.png)

    
#### Modificaciones

En una API Rest, hay 2 formas de hacer modificaciones:

- PUT: Para realizar modificaciones completas, por lo tanto, requiere que el usuario especifique todos los campos, lo que viene a ser
  un remplazo. Aunque haya campos que se desse mantener los valores, hay que especificarlos también con esos mismos valores.

- PATCH: Para realizar modificaciones parciales, aquí se garantiza que se puedan modificar ciertas caracteristicas sin especificar
  todos los campos.

En cuando a modificaciones  parciales, se puede modificar o bien el viento requerido
o bien la ubicación donde se use, garantizando que esa modificación solo la pueda realizar el dueño.

Así como las modificaciones totales, que también requiere que el que lo realice sea el dueño

  - Le modifico la ubicación a una cometa de auronplay
    
    ![img_76.png](img_76.png)
   
    Así queda la tabla
   
    ![img_77.png](img_77.png)


  - Ahora, le modifico el viento requerido
    
    ![img_78.png](img_78.png)

    Así queda la tabla
    
    ![img_79.png](img_79.png)
 
   - Ahora, pruebo a hacer un remplazo total. 
     
     ![img_80.png](img_80.png)

     Así quedaría la tabla
     
     ![img_81.png](img_81.png)


#### Eliminaciones

 Partimos de las tablas así, por un lado, tenemos la tabla con las cometas. Estos endpoints requiere que lo realice un admin.
 
  ![img_82.png](img_82.png)

 Y por otro lado, tenemos la tabla con las personas
  
  ![img_83.png](img_83.png)
  
  * Eliminación de cometas

 Vamos a eliminar las 2 cometas que tiene auronplay, 
  
   ![img_84.png](img_84.png)
   
   ![img_85.png](img_85.png)

 La tabla quedaría así
  
  ![img_86.png](img_86.png)

  * Eliminación de personas
    
    Inicialmente, la tabla personas están así
     
     ![img_87.png](img_87.png)
   
    Voy a probar a eliminar a auronplay
    
     ![img_88.png](img_88.png)

    Finalmente, la tabla de personas quedaría así
     
    ![img_89.png](img_89.png)


   
   



 
