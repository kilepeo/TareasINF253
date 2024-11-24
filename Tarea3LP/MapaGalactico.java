import java.util.ArrayList;
import java.util.Random;

public class MapaGalactico {
    private ArrayList<Planeta> planetas;
    private int posicionActual;

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    MapaGalactico
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Constructor de la clase que inicializa la lista de planetas y añade el primer planeta generado.
    */
    public MapaGalactico() {
        this.planetas = new ArrayList<>();
        this.posicionActual = 0;
        planetas.add(generadorPlaneta());
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    Planeta generadorPlaneta
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns  
    Planeta
    ***********************************************************
    Descripción breve de lo que hace  
    Genera un nuevo planeta basado en probabilidades. Dependiendo de un número aleatorio, puede crear un planeta de tipo Helado, Oceanico, Radioactivo, Volcanico, o Centro Galactico.
    Si ya ha sido generado el Centro Galactico, se reintentan las probabilidades.
    */
    public Planeta generadorPlaneta() {
        Random rand = new Random();
        double probabilidad = rand.nextDouble();
        boolean YaGenerado = false;
        Random rand2 = new Random();
        double probabilidad2 = rand2.nextDouble();

        if (probabilidad < 0.30) {
            int radio = rand.nextInt(1000, 1000000);
            int cristalesHidrogeno = (int) (0.65 * (4 * Math.PI * Math.pow(radio, 2)));
            int floresDeSodio = (int) (0.35 * (4 * Math.PI * Math.pow(radio, 2)));
            return new Helado(radio, cristalesHidrogeno, floresDeSodio);
        } else if (probabilidad < 0.60) {
            int radio = rand.nextInt(10000, 1000000);
            int cristalesHidrogeno = (int) (0.2 * (4 * Math.PI * Math.pow(radio, 2)));
            int floresDeSodio = (int) (0.65 * (4 * Math.PI * Math.pow(radio, 2)));
            return new Oceanico(radio, cristalesHidrogeno, floresDeSodio);
        } else if (probabilidad < 0.80) {
            int radio = rand.nextInt(10000, 100000);
            int cristalesHidrogeno = (int) (0.2 * (4 * Math.PI * Math.pow(radio, 2)));
            int floresDeSodio = (int) (0.2 * (4 * Math.PI * Math.pow(radio, 2)));
            return new Radiactivo(radio, cristalesHidrogeno, floresDeSodio);
        } else if (probabilidad < 0.99) {
            int radio = rand.nextInt(10000, 100000);
            int cristalesHidrogeno = (int) (0.3 * (4 * Math.PI * Math.pow(radio, 2)));
            int floresDeSodio = 0;
            return new Volcanico(radio, cristalesHidrogeno, floresDeSodio);
        } else if (YaGenerado == true) { 
            if (probabilidad2 < 0.30) {
                int radio = rand.nextInt(1000, 1000000);
                int cristalesHidrogeno = (int) (0.65 * (4 * Math.PI * Math.pow(radio, 2)));
                int floresDeSodio = (int) (0.35 * (4 * Math.PI * Math.pow(radio, 2)));
                return new Helado(radio, cristalesHidrogeno, floresDeSodio);
            } else if (probabilidad2 < 0.60) {
                int radio = rand.nextInt(10000, 1000000);
                int cristalesHidrogeno = (int) (0.2 * (4 * Math.PI * Math.pow(radio, 2)));
                int floresDeSodio = (int) (0.65 * (4 * Math.PI * Math.pow(radio, 2)));
                return new Oceanico(radio, cristalesHidrogeno, floresDeSodio);
            } else if (probabilidad2 < 0.80) {
                int radio = rand.nextInt(10000, 100000);
                int cristalesHidrogeno = (int) (0.2 * (4 * Math.PI * Math.pow(radio, 2)));
                int floresDeSodio = (int) (0.2 * (4 * Math.PI * Math.pow(radio, 2)));
                return new Radiactivo(radio, cristalesHidrogeno, floresDeSodio);
            } else {
                int radio = rand.nextInt(10000, 100000);
                int cristalesHidrogeno = (int) (0.3 * (4 * Math.PI * Math.pow(radio, 2)));
                int floresDeSodio = 0;
                return new Volcanico(radio, cristalesHidrogeno, floresDeSodio);
            }
        } else {
            YaGenerado = true;
            return new CentroGalactico(0, 0, 0);
        }
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    Planeta getPlanetaActual
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns  
    Planeta
    ***********************************************************
    Descripción breve de lo que hace  
    Devuelve el planeta actual en la posición indicada por 'posicionActual'.
    */
    public Planeta getPlanetaActual() {
        return planetas.get(posicionActual);
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void viajarAdelante
    ***********************************************************
    Parámetros que recibe  
    int i
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Avanza 'i' posiciones en el mapa galáctico. Si se alcanza el final de la lista de planetas, se genera un nuevo planeta.
    */
    public void viajarAdelante(int i) {
        for (int j = 0; j < i; j++) {
            posicionActual++;
            if (posicionActual >= planetas.size()) {
                Planeta nuevoPlaneta = generadorPlaneta();
                planetas.add(nuevoPlaneta);
            }
        }
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void reset
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Reinicia la posición actual del mapa galáctico a 0.
    */
    public void reset() {
        posicionActual = 0;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void setPlanetas
    ***********************************************************
    Parámetros que recibe  
    ArrayList<Planeta> planetas
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Establece la lista de planetas del mapa galáctico.
    */
    public void setPlanetas(ArrayList<Planeta> planetas) {
        this.planetas = planetas;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void setPosicionActual
    ***********************************************************
    Parámetros que recibe  
    int posicionActual
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Establece la posición actual en el mapa galáctico.
    */
    public void setPosicionActual(int posicionActual) {
        this.posicionActual = posicionActual;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void viajarAtras
    ***********************************************************
    Parámetros que recibe  
    int i
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Retrocede 'i' posiciones en el mapa galáctico. La posición no puede ser menor que 0.
    */
    public void viajarAtras(int i) {
        for (int j = 0; j < i; j++) {
            posicionActual--;
            if (posicionActual < 0) {
                posicionActual = 0;
                break;
            }
        }
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    int getPosicionActual
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns  
    int
    ***********************************************************
    Descripción breve de lo que hace  
    Devuelve la posición actual en el mapa galáctico.
    */
    public int getPosicionActual() {
        return posicionActual;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void agregarPlaneta
    ***********************************************************
    Parámetros que recibe  
    Planeta planeta
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Agrega un nuevo planeta a la lista de planetas en el mapa galáctico.
    */
    public void agregarPlaneta(Planeta planeta) {
        planetas.add(planeta);
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    getPlanetas
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns  
    ArrayList<Planeta>
    ***********************************************************
    Descripción breve de lo que hace  
    Devuelve la lista de planetas en el mapa galáctico.
    */
    public ArrayList<Planeta> getPlanetas() {
        return planetas;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    int explorarPlanetaActual
    ***********************************************************
    Parámetros que recibe  
    Jugador jugador
    ***********************************************************
    returns  
    int
    ***********************************************************
    Descripción breve de lo que hace  
    Explora el planeta actual y retorna un resultado basado en la visita. 
    Si el planeta es un Centro Galáctico, verifica si el jugador cumple con la eficiencia requerida.
    */
    public int explorarPlanetaActual(Jugador jugador) {
        Planeta planetaActual = getPlanetaActual();  
        boolean resultadoVisita = planetaActual.visitar(jugador);  

        if (!planetaActual.toString().equals("Centro galactico")) {
            return resultadoVisita ? 1 : 0;  
        } else {
            if (jugador.getEficiencia("Eficiencia") >= 0.5) {
                return 2; 
            } else {
                System.out.println("No cumples con las condiciones necesarias para poder viajar al centro galáctico.");
                System.out.println("Eficiencia requerida: 0.5. Eficiencia Actual: " + jugador.getEficiencia("Eficiencia"));
                return resultadoVisita ? 1 : 0; 
            }
        }
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void mostrarPlanetas
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Muestra la lista de planetas en la consola, mostrando su índice y descripción.
    */
    public void mostrarPlanetas() {
        for (int i = 0; i < planetas.size(); i++) {
            Planeta planeta = planetas.get(i);
            System.out.println("\n     " + i + ") " + planeta.toString());
        }
    }
}
