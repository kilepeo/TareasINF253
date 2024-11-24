#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>
#include "Tablero.h"
#include "Cartas.h"

int dificultad;
void ***tablero;
int barcosRestantes;
int tamanoTablero;

extern Mano Cartas;
extern bool disparo500KGUsado;

#define CARTA_DESACTIVADA NULL

void usarCarta(int cartaSeleccionada, int x, int y);

int main() {
    srand(time(NULL)); 
    int turnosRestantes;

    do {
        printf("Selecciona la dificultad:\n");
        printf("1. Fácil --> (11x11) . 5 barcos. 30 turnos\n");
        printf("2. Intermedia --> (17x17) . 7 barcos. 25 turnos\n");
        printf("3. Difícil --> (21x21) . 9 barcos. 15 turnos\n");
        printf("Ingresa un número (1-3): ");
        caracterValido(&dificultad);
    } while (dificultad < 1 || dificultad > 3);

    if (dificultad == 1) {
        tamanoTablero = 11;
        turnosRestantes = 30;
        barcosRestantes = 16;
    } else if (dificultad == 2) {
        tamanoTablero = 17;
        turnosRestantes = 25;
        barcosRestantes = 21;
    } else {
        tamanoTablero = 21;
        turnosRestantes = 15;
        barcosRestantes = 30;
    }

    inicializarTablero(tamanoTablero);
    generarBarcos(dificultad);
    inicializarMazo();

    int turno = 0;
    while (turno <= turnosRestantes && quedanBarcos()) {
        printf("Turno: %d\n", turno);
        mostrarTableroOculto(tamanoTablero);
        mostrarMazo();

        int cartaSeleccionada;
        do {
            printf("Selecciona una carta (1-5): ");
            caracterValido(&cartaSeleccionada);

            if (cartaSeleccionada < 1 || cartaSeleccionada > 5 || Cartas.carta[cartaSeleccionada - 1] == CARTA_DESACTIVADA) {
                printf("Carta inválida, selecciona otra.\n");
            }
        } while (cartaSeleccionada < 1 || cartaSeleccionada > 5 || Cartas.carta[cartaSeleccionada - 1] == CARTA_DESACTIVADA);

        int x, y;
        do {
            printf("Selecciona Coordenadas\n");
            printf("X: ");
            caracterValido(&x);
            printf("Y: ");
            caracterValido(&y);
        } while (!Limites(x, y, dificultad));

        usarCarta(cartaSeleccionada, x, y);

        turno++;

        if (!quedanBarcos()) {
            printf("¡Felicidades! Has hundido todos los barcos.\n");
            mostrarTablero(tamanoTablero);
            break;
        }
    }

    if (quedanBarcos()) {
        printf("Juego terminado. No quedan más turnos.\n");
    } else {
        printf("¡Juego terminado! Todos los barcos han sido destruidos.\n");
    }

    printf("Tablero final con todos los barcos:\n");
    mostrarTablero(tamanoTablero);

    liberarTablero(tamanoTablero);
    liberarMazo();
    return 0;
}
