application.properties
-----------------------

I use h2 with path:
spring.datasource.url=jdbc:h2:file:/home/zbyszek/military-equipment/h2db/h2db

You must use your path, for example:
spring.datasource.url=jdbc:h2:file:/home{/YOUR PATH}/h2db/h2db
    


run spring boot:
----------------

mvn spring-boot:run

