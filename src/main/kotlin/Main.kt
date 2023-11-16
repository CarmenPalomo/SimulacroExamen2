fun main() {
    var personaje: Personaje? = null
    var monstruo: Monstruo? = null

    while (true) {
        println("Menú:")
        println("1. Crear Personaje")
        println("2. Crear Monstruo")
        println("3. Pelear")
        println("4. Subir Nivel del Personaje")
        println("5. Salir")
        print("Selecciona una opción: ")

        val opcion = readLine()

        when (opcion) {
            "1" -> {
                println("Crear Personaje")
                var nombre: String
                do {
                    print("Nombre del Personaje: ")
                    nombre = readLine() ?: ""
                    if (nombre.isBlank()) {
                        println("El nombre no puede estar vacío. Inténtalo de nuevo.")
                    }
                } while (nombre.isBlank())
                var raza: Personaje.Raza? = null
                var clase: Personaje.Clase? = null

                while (raza == null) {
                    println("Elige una raza:")
                    for (razaEnum in Personaje.Raza.values()) {
                        println("${razaEnum.ordinal + 1}. ${razaEnum.name}")
                    }
                    print("Selecciona una raza: ")
                    val razaSeleccionada = readLine()?.toIntOrNull()

                    if (razaSeleccionada != null && razaSeleccionada in 1..Personaje.Raza.values().size) {
                        raza = Personaje.Raza.values()[razaSeleccionada - 1]
                    } else {
                        println("Selección no válida. Inténtalo de nuevo.")
                    }
                }

                while (clase == null) {
                    println("Elige una clase:")
                    for (claseEnum in Personaje.Clase.values()) {
                        println("${claseEnum.ordinal + 1}. ${claseEnum.name}")
                    }
                    print("Selecciona una clase: ")
                    val claseSeleccionada = readLine()?.toIntOrNull()

                    if (claseSeleccionada != null && claseSeleccionada in 1..Personaje.Clase.values().size) {
                        clase = Personaje.Clase.values()[claseSeleccionada - 1]
                    } else {
                        println("Selección no válida. Inténtalo de nuevo.")
                    }
                }

                personaje = Personaje(nombre, raza, clase)
                println("Personaje creado con éxito.")
                println(personaje.toString())
            }
            "2" -> {
                println("Crear Monstruo")
                var nombre: String
                do {
                    print("Nombre del Monstruo: ")
                    nombre = readLine() ?: ""
                    if (nombre.isBlank()) {
                        println("El nombre no puede estar vacío. Inténtalo de nuevo.")
                    }
                } while (nombre.isBlank())
                print("Nivel del Monstruo: ")
                val nivel = readLine()?.toIntOrNull()

                if (nivel != null && nivel !=0 && nivel <=10 && nivel>0) {
                    monstruo = Monstruo(nombre, nivel)
                    println("Monstruo creado con éxito.")
                    println(monstruo.toString())

                } else {
                    println("Nivel no válido. Inténtalo de nuevo.")
                }
            }
            "3" -> {
                if (personaje != null && monstruo != null) {
                    println("Inicia la pelea entre ${personaje.getNombre()} y ${monstruo.getNombre()}")
                    personaje.pelea(monstruo)
                    println("Final de la pelea.")
                    println("${personaje.getNombre()} - Nivel: ${personaje.getNivel()}, Salud: ${personaje.getSalud()}, Experiencia: ${personaje.getExperiencia()}")
                    println("${monstruo.getNombre()} - Nivel: ${monstruo.getNivel()}, Salud: ${monstruo.getSalud()}")
                } else {
                    println("Debes crear un Personaje y un Monstruo primero.")
                }
            }
            "4" -> {
                if (personaje != null) {
                    println("Subiendo el nivel de ${personaje.getNombre()}")
                    personaje.subirNivel()
                    println("Nivel actual de ${personaje.getNombre()}: ${personaje.getNivel()}")
                } else {
                    println("Debes crear un Personaje primero.")
                }
            }
            "5" -> {
                println("Saliendo del programa.")
                return
            }
            "6" -> {
                println("Prueba Enum")
                if (personaje != null) {
                    personaje.setClase(Personaje.Clase.Guerrero)
                    println(personaje.toString())
                }

            }
            else -> {
                println("Opción no válida. Inténtalo de nuevo.")
            }
        }
    }
}
