import kotlin.random.Random

/**
 * Clase que representa un personaje en el juego.
 *
 * Atributos:
 * - nombre: El nombre del personaje.
 * - raza: La raza del personaje (Humano, Elfo, Enano, Maldito).
 * - clase: La clase del personaje (Brujo, Mago, Guerrero).
 * - salud: La salud actual del personaje.
 * - ataque: La capacidad de ataque del personaje.
 * - experiencia: La experiencia acumulada por el personaje.
 * - nivel: El nivel actual del personaje.
 *
 * Enumeraciones:
 * - Raza: Enumeración para el tipo de raza del personaje.
 * - Clase: Enumeración para el tipo de clase del personaje.
 *
 * Métodos:
 * - getNombre(): Obtiene el nombre del personaje.
 * - setNombre(nuevoNombre: String): Establece un nuevo nombre para el personaje.
 * - getRaza(): Obtiene la raza del personaje.
 * - getSalud(): Obtiene la salud actual del personaje.
 * - setSalud(nuevaSalud: Int): Establece una nueva salud para el personaje.
 * - getAtaque(): Obtiene la capacidad de ataque del personaje.
 * - setAtaque(nuevoAtaque: Int): Establece una nueva capacidad de ataque para el personaje.
 * - getClase(): Obtiene la clase del personaje.
 * - setClase(nuevaClase: Clase): Establece una nueva clase para el personaje.
 * - getExperiencia(): Obtiene la experiencia acumulada por el personaje.
 * - setExperiencia(experienciaGanada: Int): Añade experiencia al personaje y gestiona el nivel.
 * - getNivel(): Obtiene el nivel actual del personaje.
 * - subirNivel(): Incrementa el nivel del personaje y ajusta salud y ataque.
 * - calcularSalud(): Calcula el valor de salud en función del nivel.
 * - calcularAtaque(): Calcula el valor de ataque en función del nivel.
 * - pelea(monstruo: Monstruo): Simula una pelea entre el personaje y un monstruo.
 * - toString(): Genera una representación en texto del personaje.
 */
class Personaje(
        private var nombre: String,
        private val raza: Raza,
        private var clase: Clase
) {
    private var salud: Int
    private var ataque: Int
    private var experiencia: Int
    private var nivel: Int
    private var suerte: Int
    private var fuerza: Int
    private var destreza: Int
    private var constitucion: Int
    private var inteligencia: Int
    private var sabiduria: Int
    private var carisma: Int
    private var vida: Int
    private var batallasGandas: Int

    enum class Raza { Humano, Elfo, Enano, Maldito }
    enum class Clase { Brujo, Mago, Guerrero }
    enum class Edad { Anciano, Adulto, Joven }

    init {
        salud = 100
        ataque = 10
        experiencia = 0
        nivel = 1
        suerte = (0..10).random()
        fuerza = 0
        destreza = 0
        constitucion = 0
        inteligencia = 0
        sabiduria = 0
        carisma = 0
        vida = 0
        batallasGandas = 0

    }

    fun getNombre(): String {
        return nombre
    }

    fun setNombre(nuevoNombre: String) {
        nombre = nuevoNombre
    }

    fun getRaza(): Raza {
        return raza
    }

    fun getSalud(): Int {
        return salud
    }

    fun setSalud(nuevaSalud: Int) {
        salud = nuevaSalud
    }

    fun getAtaque(): Int {
        return ataque
    }

    fun setAtaque(nuevoAtaque: Int) {
        ataque = nuevoAtaque
    }

    fun getClase(): Clase {
        return clase
    }

    fun setClase(nuevaClase: Clase) {
        clase = nuevaClase
    }

    fun getExperiencia(): Int {
        return experiencia
    }

    fun setExperiencia(experienciaGanada: Int) {
        experiencia += experienciaGanada
        while (experiencia >= 1000) {
            subirNivel()
            experiencia -= 1000 // Reducir la experiencia en 1000 al subir de nivel
        }
    }

    fun getNivel(): Int {
        return nivel
    }

    fun subirNivel() {
        if (nivel < 10) { // Limitar el nivel a 10
            nivel++
            calcularSalud() // Calcular el nuevo valor de salud al subir de nivel
            calcularAtaque() // Calcular el nuevo valor de ataque al subir de nivel
        }
    }

    private fun calcularSalud() {
        salud = when (nivel) {
            1 -> 100
            2 -> 200
            3 -> 300
            4 -> 450
            5 -> 600
            6 -> 800
            7 -> 1000
            8 -> 1250
            9 -> 1500
            10 -> 2000
            else -> 100 // Valor por defecto si el nivel está fuera del rango especificado
        }
    }

    private fun calcularAtaque() {
        ataque = when (nivel) {
            1 -> 10
            2 -> 20
            3 -> 25
            4 -> 30
            5 -> 40
            6 -> 100
            7 -> 200
            8 -> 350
            9 -> 400
            10 -> 450
            else -> 10 // Valor por defecto si el nivel está fuera del rango especificado
        }
    }

    fun pelea(monstruo: Monstruo) {
        var vidaMonstruo = monstruo.getSalud()
        var expGanada = monstruo.getSalud() // La experiencia ganada es igual a la salud inicial del monstruo
        var vidaPersonaje = salud

        while (vidaMonstruo > 0 && vidaPersonaje > 0) {
            // Personaje ataca al monstruo
            vidaMonstruo -= ataque
            println("${nombre} ataca al ${monstruo.getNombre()}. Salud del ${monstruo.getNombre()}: ${vidaMonstruo}")
            if (vidaMonstruo <= 0) {
                setExperiencia(expGanada)  // El personaje gana experiencia igual a la salud inicial del monstruo
                println("${nombre} ha derrotado al ${monstruo.getNombre()} y gana ${expGanada} de experiencia.")
                break
            }
            // Monstruo ataca al personaje
            vidaPersonaje -= monstruo.getAtaque()
            println("${monstruo.getNombre()} ataca a ${nombre}. Salud de ${nombre}: ${vidaPersonaje}")
        }
    }

    fun comunicacion(frase: String, edad: Edad): String {
        when (edad) {
            Edad.Joven -> {
                if (frase.first() == '¿' && frase.last() == '?' && frase.uppercase() == frase) {
                    return "Tranqui, se lo que hago"
                } else if (frase.uppercase() == frase) {
                    return "Eh, relajate"
                } else if (frase.first() == '¿' && frase.last() == '?') {
                    return "De lujo"
                } else if (frase == nombre) {
                    return "¿Que pasa?"
                } else if (frase == "Hasta la proxima luchadores") {
                    return "Un placer servirle"
                } else {
                    return "Yo que se"
                }
            }

            Edad.Adulto -> {
                if (frase.first() == '¿' && frase.last() == '?' && frase.uppercase() == frase) {
                    return "Estoy buscando la mejor solucion"
                } else if (frase.uppercase() == frase) {
                    return "No me levantes la voz mequetrefe"
                } else if (frase.first() == '¿' && frase.last() == '?') {
                    return "En la flor de la vida, pero me empieza a doler la espalda"
                } else if (frase == nombre) {
                    return "¿Necesitas algo?"
                } else if (frase == "Hasta la proxima luchadores") {
                    return "Un placer servirle"
                } else {
                    return "No se de que me estas hablando"
                }
            }

            Edad.Anciano -> {
                if (frase.first() == '¿' && frase.last() == '?' && frase.uppercase() == frase) {
                    return "Que no te escucho!"
                } else if (frase.uppercase() == frase) {
                    return "Hablame mas alto que no te escucho"
                } else if (frase.first() == '¿' && frase.last() == '?') {
                    return "No me puedo mover"
                } else if (frase == nombre) {
                    return "Las 5 de la tarde"
                } else if (frase == "Hasta la proxima luchadores") {
                    return "Un placer servirle"
                } else {
                    return "En mis tiempos esto no pasaba"
                }

            }
        }
    }

    fun habilidad() {
        when (clase) {
            Clase.Mago -> {
                calcularSalud()
            }

            Clase.Brujo -> {
                ataque *= 2
            }

            Clase.Guerrero -> {
                suerte *= 2
            }

        }

    }

    fun CalculoVida(){
        var tirada1 : Int
        var tirada2 : Int
        var tirada3 : Int
        var tirada4 : Int
        var suma : Int
        var varibale: Int
        var habilidades: MutableList<Int> = mutableListOf(fuerza,destreza,constitucion,inteligencia
        ,sabiduria,carisma)
        when(raza){
            Raza.Humano -> {

                for (i in 0..habilidades.size){
                    tirada1 = Dado().tirada()
                    tirada2 = Dado().tirada()
                    tirada3 = Dado().tirada()
                    tirada4 = Dado().tirada()

                    if (tirada1 < tirada2 && tirada1 < tirada3 && tirada1 < tirada4) {
                        suma = tirada2 + tirada3 + tirada4
                    } else if (tirada2 < tirada3 && tirada2 < tirada4){
                        suma = tirada1 + tirada3 + tirada4
                    } else if (tirada3 < tirada4){
                        suma = tirada1 + tirada2 + tirada4
                    }else{
                        suma = tirada1 + tirada2 + tirada3
                    }
                    varibale = habilidades[i]

                    if (varibale == fuerza || varibale == destreza || varibale == constitucion || varibale == inteligencia){
                        varibale = suma + 5
                    }else{
                        varibale = suma
                    }
                }

                vida = (10 +(constitucion -10)/2).toInt()
            }
            Raza.Elfo->{
                for (i in 0..habilidades.size){
                    tirada1 = Dado().tirada()
                    tirada2 = Dado().tirada()
                    tirada3 = Dado().tirada()
                    tirada4 = Dado().tirada()

                    if (tirada1 < tirada2 && tirada1 < tirada3 && tirada1 < tirada4) {
                        suma = tirada2 + tirada3 + tirada4
                    } else if (tirada2 < tirada3 && tirada2 < tirada4){
                        suma = tirada1 + tirada3 + tirada4
                    } else if (tirada3 < tirada4){
                        suma = tirada1 + tirada2 + tirada4
                    }else{
                        suma = tirada1 + tirada2 + tirada3
                    }
                    varibale = habilidades[i]

                    if (varibale == sabiduria || varibale == destreza || varibale == inteligencia){
                        varibale = suma + 5
                    }else{
                        varibale = suma
                    }
                }

                vida = (10 +(constitucion -10)/2).toInt()

            }
            Raza.Maldito ->{
                for (i in 0..habilidades.size){
                    tirada1 = Dado().tirada()
                    tirada2 = Dado().tirada()
                    tirada3 = Dado().tirada()
                    tirada4 = Dado().tirada()

                    if (tirada1 < tirada2 && tirada1 < tirada3 && tirada1 < tirada4) {
                        suma = tirada2 + tirada3 + tirada4
                    } else if (tirada2 < tirada3 && tirada2 < tirada4){
                        suma = tirada1 + tirada3 + tirada4
                    } else if (tirada3 < tirada4){
                        suma = tirada1 + tirada2 + tirada4
                    }else{
                        suma = tirada1 + tirada2 + tirada3
                    }
                    varibale = habilidades[i]

                    if (varibale == destreza || varibale == fuerza || varibale == carisma ){
                        varibale = suma + 5
                    }else{
                        varibale = suma
                    }
                }

                vida = (10 +(constitucion -10)/2).toInt()

            }
            Raza.Enano ->{
                for (i in 0..habilidades.size){
                    tirada1 = Dado().tirada()
                    tirada2 = Dado().tirada()
                    tirada3 = Dado().tirada()
                    tirada4 = Dado().tirada()

                    if (tirada1 < tirada2 && tirada1 < tirada3 && tirada1 < tirada4) {
                        suma = tirada2 + tirada3 + tirada4
                    } else if (tirada2 < tirada3 && tirada2 < tirada4){
                        suma = tirada1 + tirada3 + tirada4
                    } else if (tirada3 < tirada4){
                        suma = tirada1 + tirada2 + tirada4
                    }else{
                        suma = tirada1 + tirada2 + tirada3
                    }
                    varibale = habilidades[i]

                    if (varibale == fuerza || varibale == constitucion || varibale == destreza){
                        varibale = suma + 5
                    }else{
                        varibale = suma
                    }
                }

                vida = (10 +(constitucion -10)/2).toInt()

            }
        }
    }

    fun Combate(jugador1: Personaje, jugador2: Personaje) {
        var tirada1: Int
        var tirada2: Int
        var rondas = 0
        var i = 0
        var vida_resultante = 0
        while (jugador1.vida > 0 || jugador2.vida > 0) {
            println("Ronda $i")
            tirada1 = Dado().tirada()
            tirada2 = Dado().tirada()

            if (i == 1) {
                if (tirada1 > tirada2) {
                    println("El jugador $jugador1 ha ganado la primera ronda")

                } else {
                    println("El jugador $jugador2 ha ganado la primera ronda")
                }

            } else {
                if (tirada1 > tirada2) {
                    vida_resultante = ((jugador1.fuerza + jugador1.destreza) * 0.2 - (jugador2.constitucion + jugador2.inteligencia) * 0.1).toInt()

                    var suma = jugador2.carisma + jugador2.sabiduria
                    if (suma > 20) {
                        var tirada3 = Dado().tirada()
                        if (tirada3 == 4 || tirada3 == 5 || tirada3 == 6) {
                            println("EL jugador $jugador2 ha esquivado el ataque")
                        }
                    }

                    if (vida_resultante < 0) {
                        println("EL $jugador1 ha fallado")
                    } else {
                        jugador2.vida = jugador2.vida - vida_resultante
                    }


                } else {

                    if (tirada2 > tirada1) {
                        vida_resultante = ((jugador2.fuerza + jugador2.destreza) * 0.2 - (jugador1.constitucion + jugador1.inteligencia) * 0.1).toInt()

                        var suma = jugador1.carisma + jugador1.sabiduria
                        if (suma > 20) {
                            var tirada3 = Dado().tirada()
                            if (tirada3 == 4 || tirada3 == 5 || tirada3 == 6) {
                                println("EL jugador $jugador1 ha esquivado el ataque")
                            }
                        }

                        if (vida_resultante < 0) {
                            println("EL $jugador2 ha fallado")
                        } else {
                            jugador1.vida = jugador1.vida - vida_resultante
                        }

                    }
                }
            }
        }

        if (jugador1.salud > 0){
            jugador1.batallasGandas += 1
            println("El jugador $jugador1 ha ganado $batallasGandas batallas")
            var recomensa =  Articulo("objeto1", 135, 1)
            Mochila(1).addArticulo(recomensa)


        } else{
            jugador2.batallasGandas += 1
            println("El jugador $jugador1 ha ganado $batallasGandas batallas")
            var recomensa =  Articulo("objeto1", 135, 1)
            Mochila(1).addArticulo(recomensa)
        }

    }

    fun cifrado(mensaje: String, numero: Int) {
        when (raza) {
            Raza.Humano -> {

            }

            Raza.Maldito -> {
                var respuesta = mensaje.lowercase().toCharArray()
                var abecedario = arrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')
                var x = 0
                var salir = false
                for (i in 0..respuesta.size - 1) {
                    while (x < abecedario.size && !salir) {
                        if (respuesta[i] == ' ') {
                            salir = true
                        } else if (respuesta[i] == abecedario[x]) {
                            respuesta[i] = abecedario[(x + numero) % abecedario.size]
                            salir = true
                        }
                        x += 1
                    }
                    x = 0
                    salir = false
                }
                println(respuesta)
                //descifrado
                for (i in 0..respuesta.size - 1) {
                    while (x < abecedario.size && !salir) {
                        if (respuesta[i] == ' ') {
                            salir = true
                        } else if (respuesta[i] == abecedario[x]) {
                            respuesta[i] = abecedario[(x - numero) % abecedario.size]
                            salir = true
                        }
                        x += 1
                    }
                    x = 0
                    salir = false
                }
                println(respuesta)

            }

            Raza.Enano -> {
                println(mensaje.uppercase())
            }

            Raza.Elfo -> {
                var respuesta = mensaje.toCharArray()
                var abecedario = arrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')
                var x = 0
                var salir = false
                //cifrado
                for (i in 0..respuesta.size - 1) {
                    while (x < abecedario.size && !salir) {
                        if (respuesta[i] == ' ') {
                            salir = true
                        } else if (respuesta[i] == abecedario[x]) {
                            respuesta[i] = abecedario[(x + numero) % abecedario.size]
                            salir = true
                        }
                        x += 1
                    }
                    x = 0
                    salir = false
                }
                println(respuesta)
                //descifrado
                for (i in 0..respuesta.size - 1) {
                    while (x < abecedario.size && !salir) {
                        if (respuesta[i] == ' ') {
                            salir = true
                        } else if (respuesta[i] == abecedario[x]) {
                            respuesta[i] = abecedario[(x - numero) % abecedario.size]
                            salir = true
                        }
                        x += 1
                    }
                    x = 0
                    salir = false
                }
                println(respuesta)

            }
        }

    }


    override fun toString(): String {
        return "Personaje: Nombre: $nombre, Nivel: $nivel, Salud: $salud, Ataque: $ataque, Raza: $raza, Clase: $clase"
    }


}
