package sv.org.arrupe.API_BackEnd.model;

public enum TipoBeca {
    BECA_COMPLETA("Beca Completa (alimentación, libros, uniformes y cuota)"),
    BECA_LIBRO("Beca de Libro"),
    BECA_UNIFORME("Beca Uniforme"),
    BECA_ALIMENTO("Beca Alimento");

    private final String descripcion;

    TipoBeca(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
