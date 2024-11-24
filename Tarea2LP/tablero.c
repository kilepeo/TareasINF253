#include <stdio.h>
#include <stdlib.h>
#include <stdint.h> 
#include "Tablero.h"

extern void ***tablero;
extern int dificultad;
extern int barcosRestantes;
extern int tamanoTablero;

void inicializarTablero(int tamano) {
    tablero = malloc(tamano * sizeof(void **));
    for (int i = 0; i < tamano; i++) {
        tablero[i] = malloc(tamano * sizeof(void *));
        for (int j = 0; j < tamano; j++) {
            tablero[i][j] = NULL;
        }
    }
}

void mostrarTablero(int tamano) {
    for (int i = 0; i < tamano; i++) {
        for (int j = 0; j < tamano; j++) {
            char celda = (char)(uintptr_t)tablero[i][j];
            if (celda == 'B') {
                printf("|B");
            } else if (celda == 'O'){
                printf("|%c", celda);
            }else if (celda == 'X'){
                printf("|%c", celda);
            }
            else{
                printf("| %c", celda);
            } 
        }
        printf("| \n");
    } 
}

void mostrarTableroOculto(int tamano) {
    for (int i = 0; i < tamano; i++) {
        for (int j = 0; j < tamano; j++) {
            char celda = (char)(uintptr_t)tablero[i][j];
            if (celda == 'B') {
                printf("| ");
            } else if (celda == 'O'){
                printf("|%c", celda);
            }else if (celda == 'X'){
                printf("|%c", celda);
            }
            else{
                printf("| %c", celda);
            } 
        }
        printf("|\n");
    }
}

bool Limites(int x, int y, int dificultad) {
    int tamanoTablero;
    if (dificultad == 1) {
        tamanoTablero = 11;
    } else if (dificultad == 2) {
        tamanoTablero = 17;
    } else if (dificultad == 3) {
        tamanoTablero = 21;
    }
    return (x >= 0 && x < tamanoTablero && y >= 0 && y < tamanoTablero);
}   

void liberarTablero(int tamano) {
    for (int i = 0; i < tamano; i++) {
        free(tablero[i]);
    }
    free(tablero);
}

bool puedeColocarBarco(int x, int y, int tamanoBarco, bool horizontal, int tamanoTablero) {
    if (horizontal) {
        if (y + tamanoBarco > tamanoTablero) return false;
        for (int j = 0; j < tamanoBarco; j++) {
            if (tablero[x][y + j] != NULL) return false;
        }
    } else {
        if (x + tamanoBarco > tamanoTablero) return false;
        for (int i = 0; i < tamanoBarco; i++) {
            if (tablero[x + i][y] != NULL) return false;
        }
    }
    return true;
}

void colocarBarco(int tamanoBarco, int tamanoTablero) {
    bool colocado = false;
    while (!colocado) {
        int x = rand() % tamanoTablero;
        int y = rand() % tamanoTablero;
        bool horizontal = rand() % 2;

        if (puedeColocarBarco(x, y, tamanoBarco, horizontal, tamanoTablero)) {
            if (horizontal) {
                for (int j = 0; j < tamanoBarco; j++) {
                    tablero[x][y + j] = (void *)(uintptr_t)'B';
                }
            } else {
                for (int i = 0; i < tamanoBarco; i++) {
                    tablero[x + i][y] = (void *)(uintptr_t)'B';
                }
            }
            colocado = true;
        }
    }
}

void generarBarcos(int dificultad) {
    int tamanoTablero;
    int barcos[5] = {0};

    if (dificultad == 1) {
        tamanoTablero = 11;
        barcos[2] = 2;
        barcos[3] = 1;
        barcos[4] = 1;
        barcos[5] = 1;
    } else if (dificultad == 2) {
        tamanoTablero = 17;
        barcos[2] = 3;
        barcos[3] = 2;
        barcos[4] = 1;
        barcos[5] = 1;
    } else if (dificultad == 3) {
        tamanoTablero = 21;
        barcos[2] = 3;
        barcos[3] = 2;
        barcos[4] = 2;
        barcos[5] = 2;
    }

    for (int tamano = 2; tamano <= 5; tamano++) {
        for (int i = 0; i < barcos[tamano]; i++) {
            colocarBarco(tamano, tamanoTablero);
        }
    }
}

void caracterValido(int *input){
    char ch;
    int valido = scanf("%d", input);
    if (valido != 1){
        *input = -1;
        printf("Carácter invalido, por favor vuelva a ingresarlo\n");
        while((ch = getchar()) != '\n' && ch != EOF);
    }
}

bool quedanBarcos() {
    return barcosRestantes != 0;
}
