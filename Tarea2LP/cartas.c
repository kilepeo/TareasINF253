#include "Cartas.h"
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "Tablero.h"

extern void ***tablero;
extern int dificultad;
extern int barcosRestantes;
extern int tamanoTablero;
bool disparo500KGUsado = false;

Mano Cartas;
int numCartas = 5;
void inicializarMazo() {
    Cartas.carta = malloc(numCartas * sizeof(void *));
    Cartas.disponibles = numCartas;
    for (int i = 0; i < numCartas; i++) {
        Cartas.carta[i] = disparoSimple;
    }
}

void usarCarta(int cartaSeleccionada, int x, int y) {
    TipoDisparo disparoSeleccionado = Cartas.carta[cartaSeleccionada - 1];
    disparoSeleccionado(x, y);
    
    if (disparoSeleccionado == disparo500KG) {
        desactivarCartaDelMazo(cartaSeleccionada-1);
        disparo500KGUsado = true;
    } else {
        TipoDisparo nuevoDisparo = obtenerSiguienteDisparo(disparoSeleccionado);
        if (nuevoDisparo == disparo500KG && disparo500KGUsado) {
            do {
                nuevoDisparo = obtenerSiguienteDisparo(disparoSeleccionado);
            } while (nuevoDisparo == disparo500KG);
        }
        Cartas.carta[cartaSeleccionada - 1] = nuevoDisparo;
    }
}


void mostrarMazo() {
    printf("Cartas disponibles:\n");
    for (int i = 0; i < Cartas.disponibles; i++){
        if (Cartas.carta[i] == disparoSimple) {
            printf("Simple");
        } else if (Cartas.carta[i] == disparoGrande) {
            printf("Grande");
        } else if (Cartas.carta[i] == disparoLineal) {
            printf("Lineal");
        } else if (Cartas.carta[i] == disparoRadar) {
            printf("Radar");
        } else if (Cartas.carta[i] == disparo500KG) {
            printf("500KG");
        } else {
            printf("---");
        }
        if (i < Cartas.disponibles - 1) {
            printf(" | ");
        }
    }
    printf("\n");
}

TipoDisparo obtenerSiguienteDisparo(TipoDisparo disparoActual) {
    int r = rand() % 100;
    if (disparoActual == NULL) return NULL;

    if (disparoActual == disparoSimple) {
        if (r <= 65) return disparoSimple;
        else if (r <= 85) return disparoGrande;
        else if (r <= 90) return disparoLineal;
        else return disparoRadar;
    } else if (disparoActual == disparoGrande) {
        if (r <= 80) return disparoSimple;
        else if (r <= 83) return disparoGrande;
        else if (r <= 93) return disparoLineal;
        else if (r <= 98) return disparoRadar;
        else if (r <= 100) {
            if (disparo500KGUsado) {
                return obtenerSiguienteDisparo(disparoActual);
            } else {
                return disparo500KG;
            }
        }
    } else if (disparoActual == disparoLineal) {
        if (r <= 85) return disparoSimple;
        else if (r <= 90) return disparoGrande;
        else if (r <= 92) return disparoLineal;
        else if (r <= 100) {
            if (disparo500KGUsado) {
                return obtenerSiguienteDisparo(disparoActual);
            } else {
                return disparo500KG;
            }
        }
    } else if (disparoActual == disparoRadar) {
        if (r <= 75) return disparoSimple;
        else if (r <= 90) return disparoGrande;
        else if (r <= 95) return disparoLineal;
        else if (r <= 100) {
            if (disparo500KGUsado) {
                return obtenerSiguienteDisparo(disparoActual);
            } else {
                return disparo500KG;
            }
        }
    }
    return disparoSimple;
}

void *disparoSimple(int x, int y) {
    if (tablero[x][y] == NULL || tablero[x][y] == (void *)'O') {
        tablero[x][y] = (void *)'O';
        printf("MISS!\n");
    } else {
        tablero[x][y] = (void *)'X';
        barcosRestantes--;
        printf("HIT!\n");
    }
    return obtenerSiguienteDisparo(disparoSimple);
}

void *disparoGrande(int x, int y) {
    for (int i = -1; i <= 1; i++) {
        for (int j = -1; j <= 1; j++) {
            int nx = x + i;
            int ny = y + j;
            if (nx >= 0 && ny >= 0) {
                if (tablero[nx][ny] == NULL || tablero[nx][ny] == (void *)'O') {
                    tablero[nx][ny] = (void *)'O';
                    printf("MISS!\n");
                } else {
                    tablero[nx][ny] = (void *)'X';
                    barcosRestantes--;
                    printf("HIT!\n");
                }
            }
        }
    }
    return obtenerSiguienteDisparo(disparoGrande);
}

void *disparoLineal(int x, int y) {
    int direccion;
    do {
        printf("Ingrese dirección a lanzar: Horizontal [1] o Vertical [2]: ");
        caracterValido(&direccion);
    } while (direccion != 1 && direccion != 2);

    if (direccion == 1) {
        for (int i = -2; i <= 2; i++) {
            int ny = y + i;
            if (ny >= 0 && ny < tamanoTablero) {
                if (tablero[x][ny] == NULL || tablero[x][ny] == (void *)'O') {
                    tablero[x][ny] = (void *)'O';
                    printf("MISS!\n");
                } else {
                    tablero[x][ny] = (void *)'X';
                    barcosRestantes--;
                    printf("HIT!\n");
                }
            }
        }
    } else if (direccion == 2) {
        for (int i = -2; i <= 2; i++) {
            int nx = x + i;
            if (nx >= 0 && nx < tamanoTablero) {
                if (tablero[nx][y] == NULL || tablero[nx][y] == (void *)'O') {
                    tablero[nx][y] = (void *)'O';
                    printf("MISS!\n");
                } else {
                    tablero[nx][y] = (void *)'X';
                    barcosRestantes--;
                    printf("HIT!\n");
                }
            }
        }
    }

    return obtenerSiguienteDisparo(disparoLineal);
}


void *disparoRadar(int x, int y) {
    if (!Limites(x, y, dificultad)) {
        printf("Coordenadas fuera de los límites.\n");
        return disparoSimple;
    }

    printf("Escaneando...\n");
    for (int i = -2; i <= 2; i++) {
        for (int j = -2; j <= 2; j++) {
            int nx = x + i;
            int ny = y + j;
            if (Limites(nx, ny, dificultad)) {
                if (tablero[nx][ny] != NULL && tablero[nx][ny] != (void *)'O') {
                    printf("Barco detectado en un área 5x5.\n");
                    return obtenerSiguienteDisparo(disparoRadar);
                }
            }
        }
    }
    printf("No se han detectado barcos en el radar.\n");
    return obtenerSiguienteDisparo(disparoRadar);
}

void *disparo500KG(int x, int y) {
    disparo500KGUsado = true;
    bool impacto = false;

    for (int i = -5; i <= 5; i++) {
        for (int j = -5; j <= 5; j++) {
            int nx = x + i;
            int ny = y + j;
            if (nx >= 0 && ny >= 0) {
                if (tablero[nx][ny] == NULL || tablero[nx][ny] == (void *)'O') {
                    tablero[nx][ny] = (void *)'O';
                } else {
                    tablero[nx][ny] = (void *)'X';
                    barcosRestantes--;
                    impacto = true;
                }
            }
        }
    }
    if (impacto) {
        printf("ULTRA HIT!\n");
    } else {
        printf("MISS!\n");
    }
    for (int i = 0; i < Cartas.disponibles; i++) {
        if (Cartas.carta[i] == disparo500KG) {
            desactivarCartaDelMazo(i);
            break;
        }
    }

    return NULL;
}


void desactivarCartaDelMazo(int cartaIndex) {
    if (cartaIndex >= 0 && cartaIndex < 5) {
        Cartas.carta[cartaIndex] = NULL;
    }
}

void liberarMazo() {
    free(Cartas.carta);
    Cartas.carta = NULL;
    Cartas.disponibles = 0;
}
