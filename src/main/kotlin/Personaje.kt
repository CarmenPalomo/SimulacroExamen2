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
    enum class Raza { Humano, Elfo, Enano, Maldito }
    enum class Clase { Brujo, Mago, Guerrero }

    init {
        salud = 100
        ataque = 10
        experiencia = 0
        nivel = 1
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

    override fun toString(): String {
        return "Personaje: Nombre: $nombre, Nivel: $nivel, Salud: $salud, Ataque: $ataque, Raza: $raza, Clase: $clase"
    }

}
