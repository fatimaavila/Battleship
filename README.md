# Proyecto 1: Battleship 💣


Battleship es un juego de mesa para dos, que su objetivo es hundir los barcos del contrincante por medio de un a estrategia y un poco de suerte. Este ha sido adaptado para poder jugarlo contra la computadora en Java ☕️ 

En la versión de Java se utilizaron arrays de dos dimensiones para guardar las coordenadas de los barcos y de los disparos. Así pueden almacenar una coordenada [xy]. Se tuvo que implementar una manera que fuera *user friendly* entonces se implemento un formato de tablero como de ajedrez [A1, B4...]

Aunque esta era una buena opción para el usuario, un array no puede ser de dos tipos de datos. Para esto el array sigue funcionando con datos númericos pero el usuario ingresa una letra que se almacena en una variable que con ayuda de la *ascii table* se  transifiere al tablero. 

## Casos de uso 🛳

<img src="https://imgur.com/RGFkVCG.png" width="350" height="300"/> 

Para definir que iban a realizar cada uno de los usuarios se utilizó un diagrama de casos de uso, donde se decidió que la computadora haría la mayoría de los casos. Y el usuario se encargaría de solo lo que el puede controlar.
