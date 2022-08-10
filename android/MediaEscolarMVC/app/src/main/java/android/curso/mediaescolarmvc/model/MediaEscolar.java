package android.curso.mediaescolarmvc.model;

/**
 * Created by marcomaddo on 04/11/2017.
 */


// POJO
public class MediaEscolar {

    private int id; // PK SQLite

    private int idPK; // PK MySQL

    private String materia;
    private String bimestre;
    private String situacao;
    private double notaProva;
    private double notaTrabalho;
    private double mediaFinal;

    public MediaEscolar() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPK() {
        return idPK;
    }

    public void setIdPK(int idPK) {
        this.idPK = idPK;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getBimestre() {
        return bimestre;
    }

    public void setBimestre(String bimestre) {
        this.bimestre = bimestre;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public double getNotaProva() {
        return notaProva;
    }

    public void setNotaProva(double notaProva) {
        this.notaProva = notaProva;
    }

    public double getNotaTrabalho() {
        return notaTrabalho;
    }

    public void setNotaTrabalho(double notaTrabalho) {
        this.notaTrabalho = notaTrabalho;
    }

    public double getMediaFinal() {
        return mediaFinal;
    }

    public void setMediaFinal(double mediaFinal) {
        this.mediaFinal = mediaFinal;
    }
}
