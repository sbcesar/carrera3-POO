import java.util.*
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.random.Random

/**
 * Extiende la clase [Float] para permitir el redondeo del número a un número específico de posiciones decimales.
 *
 * @param posiciones El número de posiciones decimales a las que se redondeará el valor.
 * @return Un [Float] redondeado al número de posiciones decimales especificadas.
 */
fun Float.redondear(posiciones: Int): Float {
    val factor = 10.0.pow(posiciones.toDouble()).toFloat()
    return (this * factor).roundToInt() / factor
}

/**
 * Extiende la clase [String] para capitalizar cada palabra en una cadena de texto.
 *
 * @return Una nueva cadena de texto con cada palabra capitalizada.
 */
fun String.capitalizar(): String {
    return this.split(" ").joinToString(" ") { it.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } }
}

/**
 * Genera un vehículo aleatorio basado en los parámetros especificados.
 *
 * @param nombre El nombre del vehículo.
 * @param marcas La lista de marcas disponibles para seleccionar al azar.
 * @param modelos El mapa de modelos disponibles para cada marca.
 * @param listaVehiculos La lista de vehículos ya existentes, para evitar nombres duplicados.
 * @return Un vehículo aleatorio generado.
 */
fun generarVehiculoAleatorio(nombre: String, marcas: List<String>, modelos: Map<String,List<String>>,listaVehiculos: List<Vehiculo>): Vehiculo {

    val nombreNuevo = comprobarNombreVehiculo(nombre, listaVehiculos)

    val marcaAleatoria = marcas.random()
    val modeloAleatorio = modelos.getValue(marcaAleatoria).random()

    val numerosRandoms = (0..3).random()

    return when (Random.nextInt(4)) {
        0 -> Motocicleta(nombreNuevo, marcaAleatoria, modeloAleatorio, Random.nextInt(15, 31).toFloat(), Vehiculo.generarCombustibleAleatorio(Random.nextInt(15, 31).toFloat()), 0f,generarCilindradaMotocicleta())
        1 -> Quad(nombreNuevo, marcaAleatoria, modeloAleatorio, (20..40).random().toFloat(), Vehiculo.generarCombustibleAleatorio((20..40).random().toFloat()),0f, generarCilindradaQuad(),TipoQuad.values().random())
        2 -> Automovil(nombreNuevo, marcaAleatoria, modeloAleatorio, Random.nextInt(30, 61).toFloat(), Vehiculo.generarCombustibleAleatorio(Random.nextInt(30, 61).toFloat()), 0f,Random.nextBoolean())
        3 -> Camion(nombreNuevo, marcaAleatoria, modeloAleatorio, Random.nextInt(90, 151).toFloat(), Vehiculo.generarCombustibleAleatorio(Random.nextInt(90, 151).toFloat()), 0f,Random.nextBoolean(),Random.nextInt(1000,10000).toFloat())
        else -> throw IllegalArgumentException("Número aleatorio inválido")
    }
}

/**
 * Genera un valor aleatorio para la cilindrada de una motocicleta.
 *
 * @return La cilindrada generada aleatoriamente.
 */
fun generarCilindradaMotocicleta(): Int {
    return listOf(125, 250, 400, 500, 750, 900, 1000).random().toInt()
}

/**
 * Genera un valor aleatorio para la cilindrada de un quad.
 *
 * @return La cilindrada generada aleatoriamente.
 */
fun generarCilindradaQuad(): Int {
    return listOf(125, 250, 400, 500, 750, 900, 1000).random()
}

/**
 * Verifica si el nombre del vehículo ya está en uso y lo capitaliza si es válido.
 *
 * @param nombre El nombre del vehículo a verificar.
 * @param listaVehiculos La lista de vehículos ya existentes.
 * @return El nombre del vehículo capitalizado o un mensaje de error si el nombre ya está en uso.
 * @throws IllegalArgumentException Si el nombre del vehículo está vacío o ya está en uso.
 */
fun comprobarNombreVehiculo(nombre: String, listaVehiculos: List<Vehiculo>): String {
    if (nombre.isBlank()) {
        throw IllegalArgumentException("El nombre del vehículo no puede estar vacío o contener solo espacios.")
    }

    listaVehiculos.forEach {
        if (it.nombre == nombre)
            return "El vehiculo ya esta usado"
    }

    return nombre.capitalizar()
}


/**
 * Punto de entrada del programa. Crea una lista de vehículos y una carrera, e inicia la carrera mostrando
 * los resultados al finalizar.
 */
fun main() {

    val marcas = listOf("Toyota","Honda","Ford","Volkswagen","Nissan","BMW","Derbi","Yamaha")
    val modelos = mapOf(
        "Toyota" to listOf("Corolla", "Camry", "Rav4", "Yaris", "Highlander"),
        "Honda" to listOf("Civic", "Accord", "CR-V", "Pilot", "Odyssey"),
        "Ford" to listOf("Fiesta", "Focus", "Mustang", "Escape", "Explorer"),
        "Volkswagen" to listOf("Golf", "Jetta", "Passat", "Tiguan", "Atlas"),
        "Nissan" to listOf("Sentra", "Altima", "Maxima", "Rogue", "Pathfinder"),
        "BMW" to listOf("3 Series", "5 Series", "X3", "X5", "7 Series"),
        "Yamaha" to listOf("YZF-R1", "MT-09", "YZF-R6", "MT-07", "YZF-R3", "YZF-R125")
    )

    val listaVehiculos: MutableList<Vehiculo> = mutableListOf()

    print("Introduce el numero de participantes: ")
    val numeroParticipantes = readln().toInt()
    for (indice in 0 until numeroParticipantes) {
        print("     * Nombre del vehículo ${indice + 1} -> ")
        val nombreVehiculo = readln()

        val vehiculo = generarVehiculoAleatorio(nombreVehiculo,marcas,modelos,listaVehiculos)

        listaVehiculos.add(vehiculo)

        println("     Te ha tocado un $vehiculo")
    }

    val carrera = Carrera("Gran Carrera de Filigranas", 1000f, listaVehiculos)

    println("\n*** ${carrera.nombreCarrera} ***\n")
    carrera.iniciarCarrera()

    val resultados = carrera.obtenerResultados()

    println("* Clasificación:\n")
    resultados.forEach { println("${it.posicion} -> ${it.vehiculo.nombre} (${it.vehiculo.kilometrosActuales} kms)") }

    println("\n" + resultados.joinToString("\n") { it.toString() })

    println("\n* Historial Detallado:\n")
    resultados.forEach { println("${it.posicion} -> ${it.vehiculo.nombre}\n${it.historialAcciones.joinToString("\n")}\n") }
}

