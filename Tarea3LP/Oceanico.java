import java.util.Random;
import java.lang.Math;
/*
***********************************************************
NOMBRE DE LA FUNCIÓN 
Oceanico (Constructor)
***********************************************************
Parámetros que recibe  
int radio, int cristalesHidrogeno, int floresDeSodio
***********************************************************
returns
void
***********************************************************
Descripción breve de lo que hace  
Inicializa un objeto o planeta de tipo Oceanico, asignando los valores del radio, cristales de hidrógeno, 
flores de sodio y generando una profundidad aleatoria para cada planeta.
*/
public class Oceanico extends Planeta implements tieneAsentamientos {
    private int profundidad;
    public Oceanico(int radio, int cristalesHidrogeno, int floresDeSodio){
        super(radio, cristalesHidrogeno, floresDeSodio);
        this.profundidad = generarProfundidad();
    }
    /*
        ***********************************************************
        NOMBRE DE LA FUNCIÓN 
        int generarProfundidad
        ***********************************************************
        Parámetros que recibe  
        Ninguno
        ***********************************************************
        returns
        double
        ***********************************************************
        Descripción breve de lo que hace  
        Genera un valor aleatorio de la profundidad entre 30 y 100.
    */
    private int generarProfundidad() {
        Random rand = new Random();
        return rand.nextInt(30, 100);
    }
    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    int getProfundidad
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns
    int
    ***********************************************************
    Descripción breve de lo que hace  
    Devuelve la profunidad.
    */
    public int getProfundidad(){
        return profundidad;
    }
    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void setProfundidad
    ***********************************************************
    Parámetros que recibe  
    int profundidad
    ***********************************************************
    returns
    profundidad
    ***********************************************************
    Descripción breve de lo que hace  
    Settea la profundidad a travez de un setter.
    */
    public void setProfundidad(int profundidad){
        this.profundidad = profundidad;
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
    private double consumoEnergia(){
        return 0.002*Math.pow(profundidad,2);
    }
    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    double puedeExistirAsentamiento
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns
    double
    ***********************************************************
    Descripción breve de lo que hace  
    Genera una probabilidad aleatoria para determinar si existe un asentamiento en el planeta.
    */
    private double puedeExistirAsentamiento(){
        Random rand = new Random();
        double probabilidad = rand.nextDouble();
        return probabilidad;
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
    Devuelve la representación en texto del objeto Oceanico.
    */
    @Override
    public String toString() {
        return "Oceanico.";
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
        double rand = puedeExistirAsentamiento();
        int RecursoExtraido;
        System.out.println("Visitando planeta Oceanico.");
        if (rand > 0.5) 
        while (seguir) {
            System.out.println("\nRadio: " + radio);
            System.out.println("Cristales de Hidrógeno: " + cristalesHidrogeno);
            System.out.println("Flores de Sodio: " + floresDeSodio);
            System.out.println("Profundidad: " + profundidad);
            System.out.println("Vaya! Parece que te has encontrado con... ¿Unos pikmins? Dicen que tienen mercancias que podrian mejorar tu traje...");
            System.out.println("\n¿Qué deseas hacer?");
            System.out.println("1. Extraer Cristales de Hidrógeno");
            System.out.println("2. Extraer Flores de sodio");
            System.out.println("3. Visitar a los pikmins");
            System.out.println("4. Salir");
            System.out.print("[1/2/3/4]: ");
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
                        System.out.println("");
                        this.visitarAsentamientos(jugador);
                        break;
                    case 4:
                        seguir = salir();
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            }else{
            while (seguir) {
                System.out.println("\nRadio: " + radio);
                System.out.println("Cristales de Hidrógeno: " + cristalesHidrogeno);
                System.out.println("Flores de Sodio: " + floresDeSodio);
                System.out.println("Profundidad: " + profundidad);
                
                System.out.println("\n¿Qué deseas hacer?");
                System.out.println("1. Extraer Cristales de Hidrógeno");
                System.out.println("2. Extraer Flores de sodio");
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
                        seguir = salir();
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                    }

            }
        }return true;
    } 
    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void visitarAsentamientos
    ***********************************************************
    Parámetros que recibe  
    Jugador jugador
    ***********************************************************
    returns
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Permite al jugador visitar y negociar mejoras con los habitantes pikmins del planeta. Aqui mismo se ejecutan las mejoras del exotraje y de la nave.
    */
    public void visitarAsentamientos(Jugador jugador) {
        boolean seguir3 = true;
        System.out.println("Visitando asentamiento de los pikmins locatarios...");
        while (seguir3) {
            MejoraExotraje mejoraExotraje = new MejoraExotraje(jugador.getEficienciaEnergiaProteccion());
            System.out.print("");
            System.out.println("El pikmin te está ofreciendo muy alegre las siguientes opciones, nunca ha tenido clientes!");
            System.out.println("1: Mejorar exotraje [Platino]");
            System.out.println("2: Mejorar propulsores de la nave [Uranio]");
            System.out.println("3: Abandonar el asentamiento Pikmin.");
            System.out.println("¿Qué deseas hacer?");
            System.out.print("[1/2/3]: ");
            int opcion3 = input.nextInt();
            
            switch (opcion3) {
                case 1:
                    boolean seguir2 = true;
                    while (seguir2) {
                        jugador.mostrarInventarioPlatino();
                        System.out.println("Eficiencia actual: " + jugador.getEficienciaEnergiaProteccion());
                        System.out.println("Eficiencia luego de la mejora: " + (jugador.getEficienciaEnergiaProteccion() + 0.1));
                        int costoPlatino = 300;
                        System.out.println("Costo: " + costoPlatino + " unidades de Platino");
                        System.out.println("¿Deseas mejorar tu exotraje?");
                        System.out.println("1: Sí");
                        System.out.println("2: No");
                        System.out.print("[1/2]: ");
                        int opcion4 = input.nextInt();
                        
                        if (opcion4 == 1) {
                            if (jugador.getCantidadRecurso("Platino") >= costoPlatino) {
                                mejoraExotraje.realizarMejora(jugador);
                                jugador.setEficienciaEnergiaProteccion(mejoraExotraje.getNuevaEficiencia());
                                seguir2 = false;
                            } else {
                                System.out.println("A los pikmins locatarios no les ha gustado nada que ojearas su mercancia sin los materiales suficientes.");
                                System.out.println("Juntando fuerzas, te han forzado a salir del planeta. ¡Vuelve pronto!");
                                seguir3 = false;
                            }
                        } else {
                            seguir2 = false;
                        }
                    }
                    break;
                case 2:
                seguir2 = true;
                while (seguir2) {
                    jugador.mostrarInventarioUranio();
                    System.out.println("Eficiencia de los propulsores actual: " + jugador.getEficiencia("Eficiencia"));
                    System.out.println("Eficiencia luego de la mejora: " + (jugador.getEficiencia("Eficiencia") + 0.1f));
                    int costoUranio = 500;
                    System.out.println("Costo: " + costoUranio + " unidades de Uranio");
                    System.out.println("¿Deseas mejorar tu nave?");
                    System.out.println("1: Sí");
                    System.out.println("2: No");
                    System.out.print("[1/2]: ");
                    int opcion4 = input.nextInt();
                    
                    if (opcion4 == 1) {
                        if (jugador.getCantidadRecurso("Uranio") >= costoUranio) {
                            System.out.println("¡Has mejorado tu nave! Puedes ver la mejora en tu nave.");
                            jugador.agregarRecurso("Uranio", -costoUranio); 
                            jugador.agregarEficiencia("Eficiencia", 0.1f);
                            seguir2 = false;
                        } else {
                        System.out.println("A los pikmins locatarios no les ha gustado nada que ojearas su mercancia sin los materiales suficientes.");
                        System.out.println("Juntando fuerzas, te han forzado a salir del planeta. ¡Vuelve pronto!");
                        seguir3 = false;
                        }   
                    }else{
                        seguir2 = false;
                    }
                }
                case 3:
                    seguir3 = false;
                    break;
            }
        }
    }
}