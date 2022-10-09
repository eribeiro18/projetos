package android.curso.mediaescolarmvc.model;

public class Materia {

    private int id;
    private String codigoDaMateria;
    private String nomeDaMateria;

    public Materia(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoDaMateria() {
        return codigoDaMateria;
    }

    public void setCodigoDaMateria(String codigoDaMateria) {
        this.codigoDaMateria = codigoDaMateria;
    }

    public String getNomeDaMateria() {
        return nomeDaMateria;
    }

    public void setNomeDaMateria(String nomeDaMateria) {
        this.nomeDaMateria = nomeDaMateria;
    }
}
