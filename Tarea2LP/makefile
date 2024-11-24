CC = gcc            # Especifica el compilador
CFLAGS = -Wall -g   # Flags para el compilador: mostrar advertencias (-Wall) y depuración (-g)
OBJS = citydefender.o tablero.o cartas.o  # Archivos objeto

# Regla principal para generar el ejecutable
citydefender: $(OBJS)
	$(CC) $(CFLAGS) -o citydefender $(OBJS)

# Regla para compilar citydefender.c
citydefender.o: citydefender.c Tablero.h Cartas.h
	$(CC) $(CFLAGS) -c citydefender.c

# Regla para compilar tablero.c
tablero.o: tablero.c Tablero.h
	$(CC) $(CFLAGS) -c tablero.c

# Regla para compilar cartas.c
cartas.o: cartas.c Cartas.h
	$(CC) $(CFLAGS) -c cartas.c

# Regla para limpiar archivos generados (.o y el ejecutable)
clean:
	rm -f *.o citydefender
