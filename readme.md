# CloudAppi Code Challenge: Users API

### Decisiones de implementacion

* El tamaño máximo del texto de un tweet se aumenta a 281 
* La base de datos (H2) esta en modo volatil para seguir el orden de las peticiones de postman. Este comportamiento puede modificarse comentando la linuea que temina en create y descomentando la que acaba en unpdate
* Los IDs de usuario seran siempre de 50 en adelante. Un valor inferior a 50 no sera un valor valido de id de usuario 
* Al realizar actualizacion (metodo PUT) se prioriza el valor del parametro path sobre el del body ante posible incoherencia


