### Tech

O motivo das tecnologias utilizadas a baixo foram pelo meu conhecimento
e a facilidade que as mesmas proporcionam diante ao desenvolvimento Java.

Por exemplo o spring boot que facilita e agiliza a configuração, O hibernate
como já cria a estrutura logica do banco de dados, assim não precisamos criar
manualmente os scripts.

O Swagger que serve para termos uma melhor documentação da api.

O Mysql foi utilizado pois é agil para pequenas aplicações e podemos utilizar no Heroku

* Java 8
* Spring Boot Web; Hibernate; Data;
* Swagger 2
* MySQL
* Redis
* H2 Database

### Instalação

```sh
$ git clone https://github.com/jeanvisoski/abastecei-app.git
$ cd votacao-pauta
$ mvn package
$ cd target
$ java -jar votacao-pauta.jar
```
#### Swagger

```
http://localhost:8080/swagger-ui.html#/
```

### Versionamento
    - Os endpoints são versionados pelo número major da versão (v1) diretamente na URL de acesso.


