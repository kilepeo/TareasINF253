#ifndef H_CARTAS
#define H_CARTAS

typedef struct Mano {
    void **carta;
    int disponibles;
} Mano;

extern Mano Cartas;

typedef void *(*TipoDisparo)(int, int); 

/*
***
NOMBRE DE LA FUNCION 
inicializarMazo
***
Parametros que recibe 
Ninguno
***
returns
void
***
descripcion breve de lo que hace 
Inicializa el mazo de cartas, asignando todas las cartas a disparoSimple y estableciendo el número de cartas disponibles.
*/

void inicializarMazo();

/*
***
NOMBRE DE LA FUNCION 
mostrarMazo
***
Parametros que recibe 
Ninguno
***
returns
void
***
descripcion breve de lo que hace 
Muestra las cartas disponibles en el mazo, indicando el tipo de disparo asociado a cada carta.
*/
void mostrarMazo();

/*
***
NOMBRE DE LA FUNCION 
eliminarCartaDelMazo
***
Parametros que recibe 
int indice - Índice de la carta a eliminar
***
returns
void
***
descripcion breve de lo que hace 
Elimina una carta del mazo en el índice especificado, marcándola como NULL.
*/
void eliminarCartaDelMazo(int indice);

/*
***
NOMBRE DE LA FUNCION 
usarCarta
***
Parametros que recibe 
int cartaSeleccionada - Índice de la carta a usar
int x - Coordenada X para el disparo
int y - Coordenada Y para el disparo
***
returns
void
***
descripcion breve de lo que hace 
Usa la carta seleccionada para realizar un disparo en las coordenadas especificadas y actualiza la carta en el mazo.
*/
void usarCarta(int cartaSeleccionada, int x, int y);

/*
***
NOMBRE DE LA FUNCION 
obtenerSiguienteDisparo
***
Parametros que recibe 
TipoDisparo disparoActual - Tipo actual de disparo
***
returns
TipoDisparo - El siguiente tipo de disparo basado en probabilidades y el estado actual del disparo
***
descripcion breve de lo que hace 
Determina el siguiente tipo de disparo basado en el tipo de disparo actual y una probabilidad aleatoria, ajustando la probabilidad si el disparo de 500KG ya ha sido usado.
*/
TipoDisparo obtenerSiguienteDisparo(TipoDisparo disparoActual);

/*
***
NOMBRE DE LA FUNCION 
disparoSimple
***
Parametros que recibe 
int x - Coordenada X para el disparo
int y - Coordenada Y para el disparo
***
returns
void* - Retorna el siguiente tipo de disparo a utilizar
***
descripcion breve de lo que hace 
Realiza un disparo simple en las coordenadas especificadas, marcando el tablero con un hit o miss.
*/
void *disparoSimple(int x, int y);

/*
***
NOMBRE DE LA FUNCION 
disparoGrande
***
Parametros que recibe 
int x - Coordenada X para el disparo
int y - Coordenada Y para el disparo
***
returns
void* - Retorna el siguiente tipo de disparo a utilizar
***
descripcion breve de lo que hace 
Realiza un disparo grande en las coordenadas especificadas, afectando un área 3x3 alrededor del punto de impacto.
*/
void *disparoGrande(int x, int y);

/*
***
NOMBRE DE LA FUNCION 
disparoLineal
***
Parametros que recibe 
int x - Coordenada X para el disparo
int y - Coordenada Y para el disparo
***
returns
void* - Retorna el siguiente tipo de disparo a utilizar
***
descripcion breve de lo que hace 
Realiza un disparo lineal en la dirección especificada (horizontal o vertical) afectando un área de 5 celdas en esa dirección.
*/
void *disparoLineal(int x, int y);

/*
***
NOMBRE DE LA FUNCION 
disparoRadar
***
Parametros que recibe 
int x - Coordenada X para el disparo
int y - Coordenada Y para el disparo
***
returns
void* - Retorna el siguiente tipo de disparo a utilizar
***
descripcion breve de lo que hace 
Realiza un escaneo de radar en un área 5x5 alrededor de las coordenadas especificadas para detectar barcos.
*/
void *disparoRadar(int x, int y);

/*
***
NOMBRE DE LA FUNCION 
disparo500KG
***
Parametros que recibe 
int x - Coordenada X para el disparo
int y - Coordenada Y para el disparo
***
returns
void* - Retorna NULL ya que este disparo no tiene un siguiente tipo de disparo asociado
***
descripcion breve de lo que hace 
Realiza un disparo de 500KG afectando un área 11x11 alrededor del punto de impacto, marcando un ultra hit si se detecta algún barco.
*/
void *disparo500KG(int x, int y);

/*
***
NOMBRE DE LA FUNCION 
liberarMazo
***
Parametros que recibe 
void
***
returns
void
***
descripcion breve de lo que hace 
Libera la memoria ocupada por el mazo
*/
void liberarMazo();

#endif
