import java.util.Random;
import java.lang.Math;
import java.util.Scanner;

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    Volcanico (Constructor)
    ***********************************************************
    Parámetros que recibe  
    int radio, int cristalesHidrogeno, int floresDeSodio
    ***********************************************************
    returns
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Inicializa un objeto o planeta de tipo Volcanico, asignando los valores del radio, cristales de hidrógeno y generando una temperatura aleatoria para cada planeta.
    */
public class Volcanico extends Planeta {
    private int Platino;
    private int temperatura;
    private Scanner input;

    public Volcanico(int radio, int cristalesHidrogeno, int floresDeSodio) {
        super(radio, cristalesHidrogeno, floresDeSodio);
        this.Platino = (int)generarPlatino();
        this.temperatura = generarTemperatura();
        this.input = new Scanner(System.in);
    }
    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    int getTemperatura
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns
    int
    ***********************************************************
    Descripción breve de lo que hace  
    Devuelve el valor de la temperatura del planeta.
    */    
    public int getTemperatura() {
        return temperatura;
    }
    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void setTemperatura
    ***********************************************************
    Parámetros que recibe  
    int temperatura
    ***********************************************************
    returns
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Establece un nuevo valor para la temperatura del planeta.
    */   
    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    int getPlatino
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Retorna el valor del platino.
    */
    public int getPlatino(){
        return Platino;
    }
    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void setPlatino
    ***********************************************************
    Parámetros que recibe  
    int Platino
    ***********************************************************
    returns
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Establece un nuevo valor para el platino.
    */
    public void setPlatino(int Platino){
        this.Platino = Platino;
    }
    private double generarPlatino() {
        return (0.25 * 4 * Math.PI * Math.pow(radio, 2) - (20.5 * Math.pow(temperatura, 2)));
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
        return 0.08 * Math.abs(temperatura);
    }
    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    int generarTemperatura
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns
    int
    ***********************************************************
    Descripción breve de lo que hace  
    Calcula la nueva temperatura aleatoria entre 120 y 256.
    */
    private int generarTemperatura() {
        Random rand = new Random();
        return rand.nextInt(120, 256);
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
        return "Volcanico.";
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
        double consumoEnergia = consumoEnergia();
        boolean seguir = true;
        int RecursoExtraido;
        System.out.println("Visitando planeta Volcanico.");
        while (seguir) {
            System.out.println("\nRadio: " + radio);
            System.out.println("Cristales de Hidrógeno: " + cristalesHidrogeno);
            System.out.println("Flores de Sodio: " + floresDeSodio);
            System.out.println("Temperatura: " + temperatura);
            System.out.println("Platino: " + Platino);
            System.out.println("\n¿Qué deseas hacer?");
            System.out.println("1. Extraer Cristales de Hidrógeno");
            System.out.println("2. Extraer Platino");
            System.out.println("3. Salir");

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
                    System.out.println("\nCuanto Platino deseas extraer?");
                    Aux = input.nextInt();
                    System.out.println("");
                    RecursoExtraido = extraerRecursos(Aux);
                    jugador.consumirEnergia(RecursoExtraido, consumoEnergia);
                    if (jugador.getUnidadesEnergiaProteccion() > 0){
                        if (Platino >= RecursoExtraido){
                            System.out.println("Con ayuda de los pikmins has extraido " + RecursoExtraido + "KG de Platino");
                            jugador.agregarRecurso("Platino", RecursoExtraido);
                            System.out.println(("Energía restante: " + jugador.getUnidadesEnergiaProteccion() ));
                            break;
                        }else{
                            System.out.println("Error, no hay suficiente Platino para extraer");
                            break;
                        }
                    }else{
                        return false;
                    } 
                case 3:
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
