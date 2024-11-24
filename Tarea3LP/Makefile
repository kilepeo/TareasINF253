# Nombre del compilador
JAVAC = javac

# Archivos fuente
SOURCES = NoJavaSky.java Planeta.java Helado.java Oceanico.java \
          Radiactivo.java Volcanico.java CentroGalactico.java MapaGalactico.java \
          tieneAsentamientos.java Nave.java Jugador.java MejoraExotraje.java

# Directorio de los archivos compilados
CLASSES_DIR = bin

# Clase principal
MAIN_CLASS = NoJavaSky

# Archivo jar de salida
JAR_FILE = NoJavaSky.jar

# Objetivo por defecto: compilar todos los archivos
all: $(SOURCES)
	$(JAVAC) -d $(CLASSES_DIR) $(SOURCES)

# Ejecutar el programa
run:
	java -cp $(CLASSES_DIR) $(MAIN_CLASS)

# Limpiar los archivos compilados
clean:
	find . -name "*.class" -exec rm -f {} +
