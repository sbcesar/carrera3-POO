enum class TipoQuad(private val nombre: String) {
    CUADRICICLOS_LIGEROS("Cuadricilos ligeros"),
    CUADRICICLOS_NO_LIGEROS("Cuadriciclos no ligeros"),
    VEHICULOS_ESPECIALES("Vehiculos especiales");

    override fun toString(): String {
        return nombre.capitalizar()
    }
}