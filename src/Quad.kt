class Quad(
    nombre: String,
    marca: String,
    modelo: String,
    capacidadCombustible: Float,
    combustibleActual: Float,
    kilometrosActuales: Float,
    override val cilindrada: Int,
    private val tipoQuad: TipoQuad
) : Motocicleta(nombre, marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales, cilindrada) {

    init {
        require(cilindrada in 125..1000) { "La cilindrada del quad debe estar entre 125 cc y 1000 cc." }
    }

    constructor(
        nombre: String,
        capacidadCombustible: Float,
        combustibleActual: Float,
        kilometrosActuales: Float,
        cilindrada: Int,
        tipoQuad: TipoQuad
    ) : this(nombre, "", "", capacidadCombustible, combustibleActual, kilometrosActuales, cilindrada, tipoQuad)

    fun calcularAutonomia(otraMotocicleta: Motocicleta): Float {
        return when {
            cilindrada == otraMotocicleta.cilindrada -> super.calcularAutonomia() / 2
            else -> super.calcularAutonomia()
        }
    }

    override fun toString(): String {
        return "Quad(nombre=$nombre, marca=$marca, modelo=$modelo, capacidadCombustible=$capacidadCombustible, combustibleActual=$combustibleActual, kilometrosActuales=$kilometrosActuales, cilindrada=$cilindrada, tipoQuad=$tipoQuad)"
    }
}