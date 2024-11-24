public class MejoraExotraje {
    private double eficienciaActual;
    private int costoActual;
    private int costoInicial;

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    MejoraExotraje
    ***********************************************************
    Parámetros que recibe  
    double eficienciaInicial
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Constructor de la clase que inicializa un nuevo objeto MejoraExotraje con una eficiencia inicial y establece el costo actual.
    */
    public MejoraExotraje(double eficienciaInicial) {
        this.eficienciaActual = eficienciaInicial;
        this.costoActual = costoInicial;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    boolean realizarMejora
    ***********************************************************
    Parámetros que recibe  
    Jugador jugador
    ***********************************************************
    returns  
    boolean
    ***********************************************************
    Descripción breve de lo que hace  
    Realiza la mejora del exotraje si el jugador tiene suficientes recursos y la eficiencia de energía de protección es menor a 1.
    */
    public boolean realizarMejora(Jugador jugador) {
        if (jugador.getCantidadRecurso("Platino") >= costoActual && jugador.getEficienciaEnergiaProteccion() < 1) {
            System.out.println("¡Has mejorado tu exotraje! Puedes revisar la mejora en tu nave");
            jugador.agregarRecurso("Platino", -costoActual); 
            eficienciaActual += 0.1;
            return true;
        } else {
            return false;
        }
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    int calcularCostoMejora
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns  
    int
    ***********************************************************
    Descripción breve de lo que hace  
    Devuelve el costo actual de la mejora del exotraje.
    */
    public int calcularCostoMejora() {
        return costoActual;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    double getNuevaEficiencia
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns  
    double
    ***********************************************************
    Descripción breve de lo que hace  
    Devuelve la nueva eficiencia del exotraje después de realizar las mejoras.
    */
    public double getNuevaEficiencia() {
        return eficienciaActual;
    }
}
