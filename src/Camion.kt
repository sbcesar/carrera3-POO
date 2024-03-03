class Camion(
    nombre: String,
    marca: String,
    modelo: String,
    capacidadCombustible: Float,
    combustibleActual: Float,
    kilometrosActuales: Float,
    esHibrido: Boolean,
    private val peso: Float
) : Automovil(nombre, marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales, esHibrido) {

    constructor(
        nombre: String,
        capacidadCombustible: Float,
        combustibleActual: Float,
        kilometrosActuales: Float,
        esHibrido: Boolean,
        peso: Float
    ) : this(nombre, "", "", capacidadCombustible, combustibleActual, kilometrosActuales, esHibrido, peso)

    companion object {
        const val KM_POR_LITRO = 6.25f   //100KM / 16L
    }

    init {
        require(peso in 1000f..10000f) { "El peso debe ser entre 1000 y 10000 KG." }
        require(peso > 0) { "El peso debe ser un número positivo." }
    }

    /**
     * Calcula y devuelve la autonomía del automóvil en kilómetros. Si el automóvil es eléctrico,
     * se ajusta la eficiencia de combustible restando el ahorro eléctrico.
     *
     * @return La autonomía del automóvil en kilómetros como [Int].
     */
    override fun calcularAutonomia(): Float {
        val vecesAReducir = (peso / 1000).toInt()
        var kilometrosPorLitroReducidos = KM_POR_LITRO - vecesAReducir

        if (kilometrosPorLitroReducidos < 0) kilometrosPorLitroReducidos = 0f

        return (combustibleActual * kilometrosPorLitroReducidos).redondear(2)
    }

    override fun toString(): String {
        return "Camion(nombre=$nombre, marca=$marca, modelo=$modelo, capacidadCombustible=$capacidadCombustible, combustibleActual=$combustibleActual, kilometrosActuales=$kilometrosActuales, esElectrico=$esHibrido, peso=$peso)"
    }
}