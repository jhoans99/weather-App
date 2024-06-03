# weather-App
El proyecto se crea con jetpack compose, por lo cual es importante que el IDE este actualizado para soportar jetpack compose y build gradle con Kotlin.

La aplicacion esta desarolla con minSdk 26 y se debe compilar con la versión 8.6 de gradle y con la versión de Java 17.0.10.

En consideraciones, es importante destacar que falta el manejo de error en cuando a solicitud http. La cuales lo ideal es controlarlas mediante un clase executor que permita validar codigos de estado tanto de success y failed. y retonar de manera adecuda los casos de exception o de exitoso.

Por otro lado, es ideal guardar las ultimas ubicaciones las cuales, se deberian almacenar de manera local y ser obtenidas por el viewmodel y recuperadas desde room. Para facilitar la experiencia de usuario. 
