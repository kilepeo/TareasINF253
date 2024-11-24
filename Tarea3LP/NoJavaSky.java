import java.util.Scanner;

public class NoJavaSky {
    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    main
    ***********************************************************
    Parámetros que recibe  
    String[] args
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Es el punto de entrada del programa, donde se inicializan los objetos y se ejecuta el bucle del juego.
    */
    public static void main(String[] args) {
        MapaGalactico mapa = new MapaGalactico();
        Jugador jugador = new Jugador(100, 0);
        Nave nave = new Nave(100, 0);
        Scanner input = new Scanner(System.in);
        boolean caso = true;
        boolean game = true;
        System.out.println("Estas en un planeta desconocido... Has despertado luego de un largo sueño");
        System.out.println("Unos seres desconocidos rondan al lado tuyo, al parecer estos tienen una bulba de una flor en su cabeza?");
        System.out.println("Decides llamarlos Pikmins. estos están dispuestos a ayudarte en tu viaje para llegar al centro galactico");

        while (game) {
            imprimirBanner("\nPLANETA ACTUAL: " + mapa.getPosicionActual());
            System.out.println("\n Tipo: " + mapa.getPlanetaActual());
            imprimirMenu();

            System.out.print("\nElige una opción [1/2/3/4/5/6/7/8]: ");
            int opcion = input.nextInt();

            switch (opcion) {
                case 1: // Viajar al siguiente planeta
                    if (nave.viajarPlaneta(mapa, 1, 1)) {
                        imprimirCaja("Has viajado al siguiente planeta.");
                    }
                    break;
                case 2: // Viajar al planeta anterior
                    if (nave.viajarPlaneta(mapa, -1, 1)) {
                        imprimirCaja("Has viajado al planeta anterior.");
                    }
                    break;
                case 3: // Visitar el planeta actual
                    imprimirCaja("Visitando el planeta actual...");
                    int resultado = mapa.explorarPlanetaActual(jugador);
                    manejarResultadoExploracion(resultado, mapa, nave, jugador);
                    break;
                case 4: // Salto entre planetas
                    realizarSalto(input, mapa, nave);
                    break;
                case 5: // Ver inventario
                    jugador.mostrarInventarioCristales();
                    jugador.mostrarInventarioFlores();
                    jugador.mostrarInventarioUranio();
                    jugador.mostrarInventarioPlatino();
                    break;
                case 6: // Recargar
                    recargar(input, nave, jugador);
                    break;
                case 7: // Mostrar órbita
                    mapa.mostrarPlanetas();
                    break;
                case 8: // Salir del juego
                    imprimirCaja("Saliendo del juego... ¡Adiós!");
                    System.exit(0);
                    break;
                default:
                    imprimirCaja("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void imprimirBanner
    ***********************************************************
    Parámetros que recibe  
    String texto
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Imprime un banner decorativo con el texto proporcionado.
    */
    private static void imprimirBanner(String texto) {
        System.out.println("\n===========================================");
        System.out.println("  " + texto);
        System.out.println("===========================================\n");
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void imprimirMenu
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Imprime el menú principal del juego.
    */
    private static void imprimirMenu() {
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║        MENÚ PRINCIPAL                 ║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.println("║ 1. Viajar al siguiente planeta        ║");
        System.out.println("║ 2. Viajar al planeta anterior         ║");
        System.out.println("║ 3. Visitar el planeta actual          ║");
        System.out.println("║ 4. Realizar un salto                  ║");
        System.out.println("║ 5. Ver inventario                     ║");
        System.out.println("║ 6. Recargar energía/combustible       ║");
        System.out.println("║ 7. Ver órbita                         ║");
        System.out.println("║ 8. Salir del juego                    ║");
        System.out.println("╚═══════════════════════════════════════╝");
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void imprimirCaja
    ***********************************************************
    Parámetros que recibe  
    String mensaje
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Imprime un mensaje dentro de una caja decorativa.
    */
    private static void imprimirCaja(String mensaje) {
        System.out.println("──────────────────────────────────────────");
        System.out.printf(" %-28s \n", mensaje);
        System.out.println("──────────────────────────────────────────");
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void manejarResultadoExploracion
    ***********************************************************
    Parámetros que recibe  
    int resultado, MapaGalactico mapa, Nave nave, Jugador jugador
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Maneja el resultado de la exploración del planeta según el resultado obtenido.
    */
    private static void manejarResultadoExploracion(int resultado, MapaGalactico mapa, Nave nave, Jugador jugador) {
        switch (resultado) {
            case 0:
                imprimirCaja("Oh no! Te has quedado sin energía.");
                System.out.println("Regresando al planeta 0...");
                mapa.reset();
                nave.setUnidadesCombustible(100);
                jugador.setUnidadesEnergiaProteccion(100);
                break;
            case 1:
                nave.setEficienciaPropulsor(jugador.getEficiencia("Eficiencia"));
                break;
            case 2:
                imprimirCaja("¡Has llegado al Centro Galáctico!");
                System.out.println("¡Los Pikmins se despiden felices!");
                System.exit(0);
                break;
        }
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void realizarSalto
    ***********************************************************
    Parámetros que recibe  
    Scanner input, MapaGalactico mapa, Nave nave
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Realiza un salto entre planetas basado en la entrada del usuario.
    */
    private static void realizarSalto(Scanner input, MapaGalactico mapa, Nave nave) {
        imprimirCaja("¿Cuántos planetas deseas saltar?");
        int tamanoSalto = input.nextInt();
        System.out.println("¿Hacia dónde? (1 = adelante, -1 = atrás)");
        int direccion = input.nextInt();
        if (nave.viajarPlaneta(mapa, direccion, tamanoSalto)) {
            imprimirCaja("Salto realizado exitosamente.");
        } else {
            imprimirCaja("Combustible insuficiente.");
        }
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void recargar
    ***********************************************************
    Parámetros que recibe  
    Scanner input, Nave nave, Jugador jugador
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Maneja el proceso de recarga de energía y combustible.
    */
    private static void recargar(Scanner input, Nave nave, Jugador jugador) {
        boolean recargando = true;
        System.out.println(jugador.getEficienciaEnergiaProteccion());
        System.out.println(nave.getEficienciaPropulsor());
        while (recargando) {
            imprimirMenuRecarga();
            int opcion = input.nextInt();
            switch (opcion) {
                case 1:
                    recargarCombustible(input, nave, jugador);
                    break;
                case 2:
                    recargarExotraje(input, jugador);
                    break;
                case 3:
                    recargando = false;
                    imprimirCaja("Volviendo al menú principal.");
                    break;
                default:
                    imprimirCaja("Opción no válida.");
            }
        }
    }
    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void imprimirMenuRecarga
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Imprime el menú de recarga de energía y combustible.
    */
    private static void imprimirMenuRecarga() {
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║         MENÚ DE RECARGA      ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║ 1. Recargar combustible      ║");
        System.out.println("║ 2. Recargar exotraje         ║");
        System.out.println("║ 3. Volver al menú principal  ║");
        System.out.println("╚══════════════════════════════╝");
    }

    private static void recargarCombustible(Scanner input, Nave nave, Jugador jugador) {
        jugador.mostrarInventarioCristales();
        System.out.println("¿Cuánto combustible quieres recargar?");
        int cantidad = input.nextInt();
        if (cantidad > jugador.getCantidadRecurso("Cristales de Hidrógeno")) {
            imprimirCaja("No tienes suficientes cristales.");
        } else if (nave.getUnidadesCombustible() + cantidad > 100) {
            imprimirCaja("No puedes exceder 100 unidades.");
        } else {
            nave.recargarCombustible(cantidad);
            jugador.agregarRecurso("Cristales de Hidrógeno", -cantidad);
            imprimirCaja("Combustible recargado.");
        }
    }
    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void recargarCombustible
    ***********************************************************
    Parámetros que recibe  
    Scanner input, Nave nave, Jugador jugador
    ***********************************************************
    returns  
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Permite al jugador recargar combustible usando cristales de hidrógeno.
    */
    private static void recargarExotraje(Scanner input, Jugador jugador) {
        jugador.mostrarInventarioFlores();
        System.out.println("¿Cuántas flores quieres usar?");
        int cantidad = input.nextInt();
        if (cantidad > jugador.getCantidadRecurso("Flores de Sodio")) {
            imprimirCaja("No tienes suficientes flores.");
        } else if (jugador.getUnidadesEnergiaProteccion() + cantidad > 100) {
            imprimirCaja("Energía máxima alcanzada.");
        } else {
            jugador.recargarEnergiaProteccion(cantidad);
            jugador.agregarRecurso("Flores de Sodio", -cantidad);
            imprimirCaja("Energía recargada.");
        }
    }
}
