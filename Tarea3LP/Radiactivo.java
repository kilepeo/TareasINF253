import java.util.Random;
import java.lang.Math;
import java.util.Scanner;

/*
***********************************************************
NOMBRE DE LA FUNCIÓN 
Radioactivo (Constructor)
***********************************************************
Parámetros que recibe  
int radio, int cristalesHidrogeno, int floresDeSodio
***********************************************************
returns
void
***********************************************************
Descripción breve de lo que hace  
Inicializa un objeto o planeta de tipo Radioactivo, asignando los valores del radio, cristales de hidrógeno, 
flores de sodio y generando Radiacion y Uranio aleatorios para cada planeta.
*/
public class Radiactivo extends Planeta {
    private int Uranio;
    private int Radiacion;
    private Scanner input;

    public Radiactivo(int radio, int cristalesHidrogeno, int floresDeSodio) {
        super(radio, cristalesHidrogeno, floresDeSodio);
        this.Uranio = (int) generarUranio();
        this.Radiacion = generarRadiacion();
        this.input = new Scanner(System.in);
    }
    /*
        ***********************************************************
        NOMBRE DE LA FUNCIÓN 
        int getUranio
        ***********************************************************
        Parámetros que recibe  
        Ninguno
        ***********************************************************
        returns
        int
        ***********************************************************
        Descripción breve de lo que hace  
        Devuelve el valor del Uranio.
    */
    public int getUranio() {
        return Uranio;
    }

    /*
        ***********************************************************
        NOMBRE DE LA FUNCIÓN 
        int setUranio
        ***********************************************************
        Parámetros que recibe  
        uranio
        ***********************************************************
        returns
        void
        ***********************************************************
        Descripción breve de lo que hace  
        Establece un nuevo valor para el uranio del planeta.
    */    
    public void setUranio(int uranio) {
        this.Uranio = uranio;
    }
    /*
        ***********************************************************
        NOMBRE DE LA FUNCIÓN 
        int getRadiacion
        ***********************************************************
        Parámetros que recibe  
        Ninguno
        ***********************************************************
        returns
        int
        ***********************************************************
        Descripción breve de lo que hace  
        Devuelve el valor de la radiacion.
    */
    public int getRadiacion() {
        return Radiacion;
    }
    /*
        ***********************************************************
        NOMBRE DE LA FUNCIÓN 
        void setRadiacion
        ***********************************************************
        Parámetros que recibe  
        radiacion
        ***********************************************************
        returns
        void
        ***********************************************************
        Descripción breve de lo que hace  
        Establece un nuevo valor para la Radiacion del planeta.
    */     
    public void setRadiacion(int radiacion) {
        this.Radiacion = radiacion;
    }
    /*
        ***********************************************************
        NOMBRE DE LA FUNCIÓN 
        double generarUranio
        ***********************************************************
        Parámetros que recibe  
        Ninguno
        ***********************************************************
        returns
        double Valor del uranio generado
        ***********************************************************
        Descripción breve de lo que hace  
        Genera un valor aleatorio para el Uranio dependiendo del radio
    */
    private double generarUranio() {
        return 0.25 * 4 * Math.PI * Math.pow(radio, 2);
    }
    /*
        ***********************************************************
        NOMBRE DE LA FUNCIÓN 
        int generarRadiacion
        ***********************************************************
        Parámetros que recibe  
        Ninguno
        ***********************************************************
        returns
        int Valor de la radiacion generada
        ***********************************************************
        Descripción breve de lo que hace  
        Genera un valor aleatorio para la radiacion.
    */
    private int generarRadiacion() {
        Random rand = new Random();
        return rand.nextInt(10, 50);
    }
    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    double consumoEnergia
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns
    double
    ***********************************************************
    Descripción breve de lo que hace  
    Calcula el consumo de energía en función del valor absoluto de la temperatura.
    */
    private double consumoEnergia() {
        return 0.3 * Radiacion;
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
    Devuelve la representación en texto del objeto Radioactivo.
    */    
    @Override
    public String toString() {
        return "Radiactivo.";
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
    Simula la visita de un jugador al planeta, permitiendo la extracción de recursos y la interacción con asentamientos.
    */
    @Override
    public boolean visitar(Jugador jugador) {
        boolean seguir = true;
        int RecursoExtraido;
        double consumoEnergia = consumoEnergia();
        System.out.println("Visitando planeta radioactivo.");
        while (seguir) {
            System.out.println("\nRadio: " + radio);
            System.out.println("Cristales de Hidrógeno: " + cristalesHidrogeno);
            System.out.println("Flores de Sodio: " + floresDeSodio);
            System.out.println("Niveles de Radiación: " + Radiacion + " rad");
            System.out.println("Depósitos de Uranio: " + Uranio + " kg");

            System.out.println("\n¿Qué deseas hacer?");
            System.out.println("1. Extraer Cristales de Hidrógeno");
            System.out.println("2. Extraer Flores de Sodio");
            System.out.println("3. Extraer Uranio");
            System.out.println("4. Salir");
            int opcion = input.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("\nCuantos Cristales de Hidrógeno deseas extraer?");
                    int Aux = input.nextInt();
                    RecursoExtraido = extraerRecursos(Aux);
                    System.out.println("");
                    jugador.consumirEnergia(RecursoExtraido, consumoEnergia);
                    if (jugador.getUnidadesEnergiaProteccion() > 0){
                        if (cristalesHidrogeno >= RecursoExtraido){
                            System.out.println("Con ayuda de los pikmins has extraido " + RecursoExtraido + "KG de Cristales de Hidrógeno");
                            jugador.agregarRecurso("Cristales de Hidrógeno", RecursoExtraido);
                            System.out.println(("Energía restante: " + jugador.getUnidadesEnergiaProteccion() ));
                            break;
                        }else{
                            System.out.println("Error, no hay suficientes Cristales de Hidrógeno para extraer");
                            break;
                        }
                    }else{
                        return false;
                    }
                case 2:
                    System.out.println("\nCuantas Flores de sodio deseas extraer?");
                    Aux = input.nextInt();
                    System.out.println("");
                    RecursoExtraido = extraerRecursos(Aux);
                    jugador.consumirEnergia(RecursoExtraido, consumoEnergia);
                    if (jugador.getUnidadesEnergiaProteccion() > 0){
                        if (floresDeSodio >= RecursoExtraido){
                            System.out.println("Con ayuda de los pikmins has extraido " + RecursoExtraido + "KG de Flores de Sodio");
                            jugador.agregarRecurso("Flores de Sodio", RecursoExtraido);
                            System.out.println(("Energía restante: " + jugador.getUnidadesEnergiaProteccion() ));
                            break;
                        }else{
                            System.out.println("Error, no hay suficientes Flores de sodio para extraer");
                            break;
                        }
                    }else{
                        return false;
                    } 
                case 3:
                    System.out.println("\nCuanto Uranio deseas extraer?");
                    Aux = input.nextInt();
                    System.out.println("");
                    RecursoExtraido = extraerRecursos(Aux);
                    jugador.consumirEnergia(RecursoExtraido, consumoEnergia);
                    if (jugador.getUnidadesEnergiaProteccion() > 0){
                        if (Uranio >= RecursoExtraido){
                            System.out.println("Con ayuda de los pikmins has extraido " + RecursoExtraido + "KG de Uranio");
                            jugador.agregarRecurso("Uranio", RecursoExtraido);
                            System.out.println(("Energía restante: " + jugador.getUnidadesEnergiaProteccion() ));
                            break;
                        }else{
                            System.out.println("Error, no hay suficiente Uranio para extraer");
                            break;
                        }
                    }else{
                        return false;
                    } 
                case 4:
                    seguir = salir();
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
        return true;
    }
}
