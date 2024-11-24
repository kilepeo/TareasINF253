#ifndef TABLERO_H
#define TABLERO_H

#include <stdbool.h>

/*
***
NOMBRE DE LA FUNCION 
inicializarTablero
***
Parametros que recibe 
int tamano - Tamaño del tablero a inicializar
***
returns
void
***
descripcion breve de lo que hace 
Inicializa el tablero asignando memoria para cada celda y configurándolas con NULL.
*/

void inicializarTablero(int tamano);

/*
***
NOMBRE DE LA FUNCION 
mostrarTablero
***
Parametros que recibe 
int tamano - Tamaño del tablero a mostrar
***
returns
void
***
descripcion breve de lo que hace 
Muestra el tablero en la consola, representando el contenido de cada celda.
*/

void mostrarTablero(int tamano);

/*
***
NOMBRE DE LA FUNCION 
liberarTablero
***
Parametros que recibe 
int tamano - Tamaño del tablero a liberar
***
returns
void
***
descripcion breve de lo que hace 
Libera la memoria asignada para el tablero.
*/

void liberarTablero(int tamano);

/*
***
NOMBRE DE LA FUNCION 
colocarBarco
***
Parametros que recibe 
int tamanoBarco - Tamaño del barco a colocar
int tamanoTablero - Tamaño del tablero
***
returns
void
***
descripcion breve de lo que hace 
Coloca un barco de tamaño tamanoBarco en una posición aleatoria válida en el tablero.
*/

void colocarBarco(int tamanoBarco, int tamanoTablero);

/*
***
NOMBRE DE LA FUNCION 
Limites
***
Parametros que recibe 
int x - Coordenada X a verificar
int y - Coordenada Y a verificar
int dificultad - Nivel de dificultad del juego
***
returns
bool - Retorna true si las coordenadas están dentro de los límites del tablero, false en caso contrario
***
descripcion breve de lo que hace 
Verifica si las coordenadas están dentro de los límites del tablero según la dificultad.
*/

bool Limites(int x, int y, int dificultad);

/*
***
NOMBRE DE LA FUNCION 
generarBarcos
***
Parametros que recibe 
int dificultad - Nivel de dificultad del juego
***
returns
void
***
descripcion breve de lo que hace 
Genera todos los barcos necesarios en el tablero según el nivel de dificultad.
*/

void generarBarcos(int dificultad);

/*
***
NOMBRE DE LA FUNCION 
puedeColocarBarco
***
Parametros que recibe 
int x - Coordenada X para colocar el barco
int y - Coordenada Y para colocar el barco
int tamanoBarco - Tamaño del barco
bool horizontal - Booleano que indica si el barco es horizontal o vertical
int tamanoTablero - Tamaño del tablero
***
returns
bool - Retorna true si se puede colocar el barco en la posición dada, false en caso contrario
***
descripcion breve de lo que hace 
Verifica si se puede colocar un barco en la posición dada sin que se superponga con otros barcos.
*/

bool puedeColocarBarco(int x, int y, int tamanoBarco, bool horizontal, int tamanoTablero);

/*
***
NOMBRE DE LA FUNCION 
caracterValido
***
Parametros que recibe 
int *input - Puntero a la variable donde se almacenará el valor ingresado
***
returns
void
***
descripcion breve de lo que hace 
Verifica si el valor ingresado es un entero válido y lo almacena en la variable proporcionada.
*/

void caracterValido(int *input);

/*
***
NOMBRE DE LA FUNCION 
quedanBarcos
***
Parametros que recibe 
Ninguno
***
returns
bool - Retorna true si aún quedan barcos restantes, false en caso contrario
***
descripcion breve de lo que hace 
Verifica si aún quedan barcos restantes en el juego.
*/

bool quedanBarcos();

/*
***
NOMBRE DE LA FUNCION 
desactivarCartaDelMazo
***
Parametros que recibe 
int indice - Índice de la carta que se va a desactivar
***
returns
void
***
descripcion breve de lo que hace 
Desactiva una carta del mazo utilizando el índice proporcionado, marcándola como utilizada.
*/

void desactivarCartaDelMazo(int indice);

/*
***
NOMBRE DE LA FUNCION 
mostrarTableroOculto
***
Parametros que recibe 
int tamano - Tamaño del tablero a mostrar
***
returns
void
***
descripcion breve de lo que hace 
Muestra el tablero en la consola, ocultando los barcos y mostrando solo los disparos y los espacios vacíos.
*/

void mostrarTableroOculto(int tamano);

#endif
