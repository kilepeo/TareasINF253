public class Nave {
    private float unidadesCombustible;  
    private float eficienciaPropulsor;   

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    Nave (Constructor)
    ***********************************************************
    Parámetros que recibe  
    int combustibleInicial, int eficienciaInicial
    ***********************************************************
    returns
    void
    *********************************************************** 
    Descripción breve de lo que hace  
    Inicializa los valores de combustible y eficiencia del propulsor.
    */
    public Nave(int combustibleInicial, int eficienciaInicial) {
        this.unidadesCombustible = combustibleInicial;
        this.eficienciaPropulsor = eficienciaInicial;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    boolean viajarPlaneta
    ***********************************************************
    Parámetros que recibe  
    MapaGalactico MG, int direccion, int tamanoSalto
    ***********************************************************
    returns
    boolean
    ***********************************************************
    Descripción breve de lo que hace  
    Calcula si hay suficiente combustible para viajar a otro planeta y realiza el viaje.
    */
    public boolean viajarPlaneta(MapaGalactico MG, int direccion, int tamanoSalto) {
        double consumo = calcularConsumoCombustible(tamanoSalto);
        if (verificarCombustible(consumo)) {
            this.unidadesCombustible -= consumo;
            System.out.println("Has viajado " + tamanoSalto + " planetas. Combustible restante: " + this.unidadesCombustible);
            if (direccion > 0){
                MG.viajarAdelante(tamanoSalto);
            } else if (direccion < 0) {
                MG.viajarAtras(tamanoSalto);
            }
            return true;
        }else{
            return false;
        }
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    double calcularConsumoCombustible
    ***********************************************************
    Parámetros que recibe  
    int tamanoSalto
    ***********************************************************
    returns
    double
    ***********************************************************
    Descripción breve de lo que hace  
    Calcula el consumo de combustible necesario según el tamaño del salto.
    */
    private double calcularConsumoCombustible(int tamanoSalto) {
        return (0.75 * Math.pow(tamanoSalto, 2) * (1 - eficienciaPropulsor));
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    boolean verificarCombustible
    ***********************************************************
    Parámetros que recibe  
    double consumo
    ***********************************************************
    returns
    boolean
    ***********************************************************
    Descripción breve de lo que hace  
    Verifica si hay suficiente combustible disponible para el viaje.
    */
    private boolean verificarCombustible(double consumo) {
        return this.unidadesCombustible >= consumo;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void recargarCombustible
    ***********************************************************
    Parámetros que recibe  
    int cristalesHidrogeno
    ***********************************************************
    returns
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Recarga el combustible en función de los cristales de hidrógeno disponibles.
    */
    public void recargarCombustible(int cristalesHidrogeno) {
        int combustibleRecargado = (int) (0.6 * cristalesHidrogeno * (1 + eficienciaPropulsor));
        this.unidadesCombustible += combustibleRecargado;
        System.out.println("Combustible recargado: " + combustibleRecargado + ". Combustible actual: " + this.unidadesCombustible);
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    float getUnidadesCombustible
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns
    float
    ***********************************************************
    Descripción breve de lo que hace  
    Devuelve la cantidad de unidades de combustible disponibles.
    */
    public float getUnidadesCombustible() {
        return unidadesCombustible;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void setUnidadesCombustible
    ***********************************************************
    Parámetros que recibe  
    int combustible
    ***********************************************************
    returns
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Establece una nueva cantidad de unidades de combustible.
    */
    public void setUnidadesCombustible(int combustible) {
        this.unidadesCombustible = combustible;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    float getEficienciaPropulsor
    ***********************************************************
    Parámetros que recibe  
    Ninguno
    ***********************************************************
    returns
    float
    ***********************************************************
    Descripción breve de lo que hace  
    Devuelve la eficiencia actual del propulsor.
    */
    public float getEficienciaPropulsor() {
        return eficienciaPropulsor;
    }

    /*
    ***********************************************************
    NOMBRE DE LA FUNCIÓN 
    void setEficienciaPropulsor
    ***********************************************************
    Parámetros que recibe  
    float eficiencia
    ***********************************************************
    returns
    void
    ***********************************************************
    Descripción breve de lo que hace  
    Establece una nueva eficiencia para el propulsor.
    */
    public void setEficienciaPropulsor(float eficiencia) {
        this.eficienciaPropulsor = eficiencia;
    }

}
