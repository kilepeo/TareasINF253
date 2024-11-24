public class CentroGalactico extends Planeta {
    protected int radio;
    protected double cristalesHidrogeno;
    protected double floresDeSodio;

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    CentroGalactico
    ***********************************************************
    Parámetros que recibe  
    int radio, int cristalesHidrogeno, int floresDeSodio
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Constructor de la clase que inicializa un nuevo objeto CentroGalactico utilizando los valores de radio, cristales de hidrógeno y flores de sodio.
    Esto es de auxiliar pues al visitarlo y se cumplen las condiciones, se gana, por lo que los atributos no son necesarios.
    */
    protected CentroGalactico(int radio, int cristalesHidrogeno, int floresDeSodio) {
        super(radio, cristalesHidrogeno, floresDeSodio);
    }    

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    toString
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns  
    String
    ***********************************************************
    Descripción breve de lo que hace  
    Devuelve una representación en forma de cadena del objeto, indicando que es un "Centro galáctico".
    */
    @Override
    public String toString() {
        return "Centro galactico";
    }
}
