import java.util.Scanner;

public abstract class Planeta {
    protected int radio;
    protected int cristalesHidrogeno;
    protected int floresDeSodio;
    Scanner input = new Scanner(System.in);

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    Planeta (Constructor)
    ***********************************************************
    Parámetros que recibe  
    int radio, int cristalesHidrogeno, int floresDeSodio
    ***********************************************************
    returns
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Inicializa un objeto de tipo Planeta, asignando valores al radio, 
    a los cristales de hidrógeno y a las flores de sodio.
    */
    protected Planeta(int radio, int cristalesHidrogeno, int floresDeSodio) {
        this.radio = radio;
        this.cristalesHidrogeno = cristalesHidrogeno;
        this.floresDeSodio = floresDeSodio;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    boolean visitar
    ***********************************************************
    Parámetros que recibe  
    Jugador jugador
    ***********************************************************
    returns
    boolean
    ***********************************************************
    Descripción breve de lo que hace  
    Define la lógica básica para visitar un planeta. Por defecto, devuelve `true`.
    */
    public boolean visitar(Jugador jugador) {
        return true;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    int extraerRecursos
    ***********************************************************
    Parámetros que recibe  
    int tipo
    ***********************************************************
    returns
    int
    ***********************************************************
    Descripción breve de lo que hace  
    Devuelve el valor del recurso extraído según el tipo ingresado.
    */
    public int extraerRecursos(int tipo) {
        return tipo;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    boolean salir
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns
    boolean
    ***********************************************************
    Descripción breve de lo que hace  
    Pregunta al usuario si desea salir del planeta y devuelve `false` si confirma, 
    o `true` si decide quedarse.
    */
    public boolean salir() {
        System.out.println("\nRealmente quieres salir?");
        System.out.println("1:Si");
        System.out.println("2:No");
        System.err.print("[1/2]: ");
        int Aux = input.nextInt();
        if (Aux == 1) {
            return false;
        } else {
            return true;
        }
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    int getRadio
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns
    int
    ***********************************************************
    Descripción breve de lo que hace  
    Devuelve el valor del radio del planeta.
    */
    public int getRadio() {
        return radio;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    int getCristalesHidrogeno
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns
    int
    ***********************************************************
    Descripción breve de lo que hace  
    Devuelve la cantidad de cristales de hidrógeno del planeta.
    */
    public int getCristalesHidrogeno() {
        return cristalesHidrogeno;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    int getFloresDeSodio
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns
    int
    ***********************************************************
    Descripción breve de lo que hace  
    Devuelve la cantidad de flores de sodio del planeta.
    */
    public int getFloresDeSodio() {
        return floresDeSodio;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void setRadio
    ***********************************************************
    Parámetros que recibe  
    int radio
    ***********************************************************
    returns
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Establece un nuevo valor para el radio del planeta.
    */
    public void setRadio(int radio) {
        this.radio = radio;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void setCristalesHidrogeno
    ***********************************************************
    Parámetros que recibe  
    int cristalesHidrogeno
    ***********************************************************
    returns
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Establece una nueva cantidad de cristales de hidrógeno para el planeta.
    */
    public void setCristalesHidrogeno(int cristalesHidrogeno) {
        this.cristalesHidrogeno = cristalesHidrogeno;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void setFloresDeSodio
    ***********************************************************
    Parámetros que recibe  
    int floresDeSodio
    ***********************************************************
    returns
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Establece una nueva cantidad de flores de sodio para el planeta.
    */
    public void setFloresDeSodio(int floresDeSodio) {
        this.floresDeSodio = floresDeSodio;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    string toString
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns
    String
    ***********************************************************
    Descripción breve de lo que hace  
    Devuelve una representación en texto del objeto Planeta.
    */
    @Override
    public String toString() {
        return "";
    }

}
