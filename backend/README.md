application.properties:
-----------------------

I use h2 with path:
spring.datasource.url=jdbc:h2:file:/home/zbyszek/military-equipment/h2db/h2db

You have to use the path, for example:
spring.datasource.url=jdbc:h2:file:/home{/xxx}/h2db/h2db
    


run spring boot:
----------------

mvn spring-boot:run

