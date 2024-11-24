import java.util.LinkedHashMap;
import java.util.Map;

public class Jugador {
    private double unidadesEnergiaProteccion;
    private double eficienciaEnergiaProteccion;
    private Map<String, Integer> inventarioRecursos;
    private Map<String, Float> inventarioEficiencia;
    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    Jugador (Constructor)
    ***********************************************************
    Parámetros que recibe  
    double unidadesEnergiaProteccion, double eficienciaEnergiaProteccion
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Inicializa los valores de energía y eficiencia, además de configurar los inventarios del jugador.
    */
    public Jugador(double unidadesEnergiaProteccion, double eficienciaEnergiaProteccion) {
        this.unidadesEnergiaProteccion = unidadesEnergiaProteccion;
        this.eficienciaEnergiaProteccion = eficienciaEnergiaProteccion;
        this.inventarioRecursos = new LinkedHashMap<>();
        this.inventarioEficiencia = new LinkedHashMap<>();
        this.inventarioRecursos.put("Cristales de Hidrógeno", 0);
        this.inventarioRecursos.put("Flores de Sodio", 0);
        this.inventarioRecursos.put("Uranio", 0);
        this.inventarioRecursos.put("Platino", 0);
        this.inventarioEficiencia.put("Eficiencia", 0f);
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void setUnidadesEnergiaProteccion
    ***********************************************************
    Parámetros que recibe  
    double unidadesEnergiaProteccion
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Establece un nuevo valor para las unidades de energía de protección.
    */
    public void setUnidadesEnergiaProteccion(double unidadesEnergiaProteccion) {
        this.unidadesEnergiaProteccion = unidadesEnergiaProteccion;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void setEficienciaEnergiaProteccion
    ***********************************************************
    Parámetros que recibe  
    double eficienciaEnergiaProteccion
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Establece un nuevo valor para la eficiencia energética de protección.
    */
    public void setEficienciaEnergiaProteccion(double eficienciaEnergiaProteccion) {
        this.eficienciaEnergiaProteccion = eficienciaEnergiaProteccion;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    double getUnidadesEnergiaProteccion
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns  
    double
    ***********************************************************
    Descripción breve de lo que hace  
    Devuelve el valor de las unidades de energía de protección.
    */
    public double getUnidadesEnergiaProteccion() {
        return this.unidadesEnergiaProteccion;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    double getEficienciaEnergiaProteccion
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns  
    double
    ***********************************************************
    Descripción breve de lo que hace  
    Devuelve el valor de la eficiencia energética de protección.
    */
    public double getEficienciaEnergiaProteccion() {
        return this.eficienciaEnergiaProteccion;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void consumirEnergia
    ***********************************************************
    Parámetros que recibe  
    double cantidad
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Reduce las unidades de energía de protección en la cantidad indicada.
    */
    public void consumirEnergia(double cantidad) {
        this.unidadesEnergiaProteccion -= cantidad;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void consumirEnergia
    ***********************************************************
    Parámetros que recibe  
    double unidadesRecurso, double consumoEnergia
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Calcula la energía consumida según el recurso y la eficiencia, y la resta del total.
    */
    public void consumirEnergia(double unidadesRecurso, double consumoEnergia) {
        double energiaConsumida = 0.5 * unidadesRecurso * (consumoEnergia / 100) * (1 - this.eficienciaEnergiaProteccion);
        this.unidadesEnergiaProteccion -= energiaConsumida;
        System.out.println("Has consumido " + energiaConsumida + " unidades de energía.");
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void recargarEnergiaProteccion
    ***********************************************************
    Parámetros que recibe  
    double cantidad
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Incrementa las unidades de energía de protección en la cantidad indicada.
    */
    public void recargarEnergiaProteccion(double cantidad) {
        this.unidadesEnergiaProteccion += cantidad;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void agregarRecurso
    ***********************************************************
    Parámetros que recibe  
    String recurso, int cantidad
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Agrega una cantidad al recurso especificado en el inventario.
    */
    public void agregarRecurso(String recurso, int cantidad) {
        if (this.inventarioRecursos.containsKey(recurso)) {
            int cantidadActual = this.inventarioRecursos.get(recurso);
            this.inventarioRecursos.put(recurso, cantidadActual + cantidad);
        }
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void agregarEficiencia
    ***********************************************************
    Parámetros que recibe  
    String recurso, float cantidad
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Agrega una cantidad de eficiencia al recurso correspondiente.
    */
    public void agregarEficiencia(String recurso, float cantidad) {
        if (this.inventarioEficiencia.containsKey(recurso)) {
            float cantidadActual = this.inventarioEficiencia.get(recurso);
            this.inventarioEficiencia.put(recurso, cantidadActual + cantidad);
        }
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void mostrarInventario
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Muestra el inventario completo de recursos.
    */
    public void mostrarInventario() {
        System.out.println("Inventario del jugador:");
        for (Map.Entry<String, Integer> entry : this.inventarioRecursos.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void mostrarInventarioCristales
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Muestra la cantidad de cristales de hidrógeno en el inventario.
    */
    public void mostrarInventarioCristales() {
        System.out.println("Cristales de Hidrógeno: " + this.inventarioRecursos.getOrDefault("Cristales de Hidrógeno", 0));
    }
    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void mostrarInventarioFlores
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Muestra la cantidad de flores de sodio en el inventario.
    */
    public void mostrarInventarioFlores() {
        System.out.println("Flores de Sodio: " + this.inventarioRecursos.getOrDefault("Flores de Sodio", 0));
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void mostrarInventarioUranio
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Muestra la cantidad de uranio en el inventario.
    */
    public void mostrarInventarioUranio() {
        System.out.println("Uranio: " + this.inventarioRecursos.getOrDefault("Uranio", 0));
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void mostrarInventarioPlatino
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Muestra la cantidad de platino en el inventario.
    */
    public void mostrarInventarioPlatino() {
        System.out.println("Platino: " + this.inventarioRecursos.getOrDefault("Platino", 0));
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void mostrarInventarioEficiencia
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Muestra la eficiencia del inventario del jugador, esta funciona como auxiliar para la eficiencia de la nave.
    */
    public void mostrarInventarioEficiencia() {
        System.out.println("Eficiencia " + this.inventarioEficiencia.getOrDefault("Eficiencia", 0.0f));
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    int getCantidadRecurso
    ***********************************************************
    Parámetros que recibe  
    String nombreRecurso
    ***********************************************************
    returns  
    int
    ***********************************************************
    Descripción breve de lo que hace  
    Devuelve la cantidad de un recurso específico.
    */
    public int getCantidadRecurso(String nombreRecurso) {
        return inventarioRecursos.getOrDefault(nombreRecurso, 0);
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    float getEficiencia
    ***********************************************************
    Parámetros que recibe  
    String nombreRecurso
    ***************************************************** ******
    returns  
    float
    ***********************************************************
    Descripción breve de lo que hace  
    Devuelve la eficiencia de un recurso.
    */
    public float getEficiencia(String nombreRecurso) {
        return inventarioEficiencia.get("Eficiencia");
    }

}
