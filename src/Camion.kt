class Camion(
    nombre: String,
    marca: String,
    modelo: String,
    capacidadCombustible: Float,
    combustibleActual: Float,
    kilometrosActuales: Float,
    esHibrido: Boolean,
    peso: Float
) : Automovil(nombre, marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales, esHibrido) {

    companion object {
        const val CONSUMO = 0.16f   //16L / 100KM
    }

    init {
        require(peso in 1000f..10000f) { "El peso debe ser entre 1000 y 10000 KG." }
        require(peso > 0) { "El peso debe ser un número positivo." }
    }

    fun reducirKmLitro() {

    }

    override fun calcularAutonomia(): Float {
        while ()
    }
}