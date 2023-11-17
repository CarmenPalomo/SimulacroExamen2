import kotlin.random.Random
/***********************************************************************************************************************
 *  CLASE: Dado
 *  CONSTRUCTOR:
 *      numMin    - > límite inferior del rango del dado
 *      numMax    - > límite superior del rango del dado
 *
 *  METODOS
 *      tirada()  - > Devuelve el valor (Int) aleatorio entre 1 y 6
 **********************************************************************************************************************/
class Dado() {

    private var numMin=1
    private var numMax=6

    fun tirada (): Int {
        return Random.nextInt(numMin, numMax)
    }
}

/***********************************************************************************************************************
 *  CLASE: Articulo
 *  CONSTRUCTOR:
 *      id      - > Nombre del artículo
 *      peso    - > Peso del artículo
 *      valor   - > Valor del artículo
 *
 *  METODOS
 *      getPeso()       - > Devuelve el peso como Int
 *      getValor()      - > Devuelve el valor como Int
 *      getId()         - > Devuelve el nombre del artículo como String
 *      toString()      - > Sobreescribimos el método toString para darle formato a la visualización de los artículos
 **********************************************************************************************************************/
class Articulo (private var id:String, private var peso:Int, private var valor:Int){

    fun getPeso():Int{
        return peso
    }
    fun getValor():Int{
        return valor
    }
    fun getId():String{
        return id
    }
    override fun toString(): String {
        return "[ID:$id, Peso:$peso, Valor:$valor]"
    }
}

/***********************************************************************************************************************
 *  CLASE: Mochila
 *  CONSTRUCTOR:
 *      pesoMochila      - > Peso máximo que puede soportar la mochila
 *
 *  METODOS
 *      getPesoMochila()        - > Devuelve el peso máximo como Int
 *      addArticulo()           - > Añade un artículo (articulo) a la mochila, comprobando el límite
 *      getContenido()          - > Devuelve el ArrayList de artículos que contiene la mochila
 *      findObjeto()            - > Devuelve la posición del artículo que pasamos como entrada o -1 si no lo encuentra
 *
 **********************************************************************************************************************/
class Mochila(private var pesoMochila: Int){
    private var contenido=ArrayList<Articulo>()

    fun getPesoMochila():Int{
        return pesoMochila
    }
    fun addArticulo(articulo:Articulo){
        if (articulo.getPeso()<=pesoMochila){
            contenido.add(articulo)
            this.pesoMochila-=articulo.getPeso()
        }else{
                println("La mochila está llena, debes vender artículos")
        }
        println("Peso restante de la Mochila: "+pesoMochila)

    }
    fun getContenido(): ArrayList<Articulo> {
        return contenido
    }
    fun findObjeto(id: String):Int{
        for((indice,item) in contenido.withIndex()){
            if (item.getId() == id) {
                return indice
            }
        }
        return -1
    }
}

/***********************************************************************************************************************
 *  CLASE: Personaje
 *  CONSTRUCTOR:
 *      nombre          - > Nombre del personaje
 *      pesoMochila     - > Peso máximo que puede soportar la mochila
 *      estadoVital     - > Sólo puede tomar los valores Adulto, Joven o Anciano
 *      raza            - > Sólo puede tomar los valores Enano, Elfo, Humano y Goblin
 *      clase           - > Sólo puede tomar los valores Mercader, Ladrón, Guerrero, Mago y Berserker
 *
 *
 *  ATRIBUTOS:
 *      mochila         - > Cada personaje dispone de una mochila con un límite de peso establecido en el constructor
 *      monedero        - > Cada personaje dispone de un monedero con capacidad para coins de 1, 5, 10, 25 y 100
 *
 *  METODOS
 *      get/setNombre()         - > Devuelve/Establece el nombre del Personaje
 *      get/setEstadoVital()    - > Devuelve/Establece el estado vital del Personaje
 *      get/setRaza()           - > Devuelve/Establece la raza del Personaje
 *      get/setClase()          - > Devuelve/Establece la clase del Personaje
 *      getMochila              - > Devuelve/Establece la mochila del Personaje
 *
 *      cifrado                 - > Param. Entrada: <Texto a crifrar> <ROT>
 *      comunicacion            - > Param. Entrada: <Mensaje para el usuario>
 *      vender                  - > Param. Entrada: <Personaje Mercader> <Articulo a vender>
 *                                  Descripción: método que comprueba si el Personaje pasado como parámetro de entrada
 *                                  es Mercader o no. Asimismo, comprueba el contenido de la mochila y si en él se
 *                                  encuentra el Artículo pasado como parámetro de entrada. De ser así, lo mueve a la
 *                                  mochila del mercader, realiza la transacción económica y lo elimina de la mochila
 *                                  del Personaje.
 *
 *      cashConverter           - > Param. Entrada: <Articulo>
 *                                  Descripción: método que transforma el valor del Artículo en monedas.
 *
 **********************************************************************************************************************/


