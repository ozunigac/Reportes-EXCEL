package Test;

import java.util.ArrayList;

public class Alumno {
    private String Nombre;
    private String ApellidoMa;
    private String ApellidoPa;
    private int Matricula;
    private ArrayList<Materia> materias;
    public Alumno(String nombre, String apellidoPa,String apellidoMa,int matricula,ArrayList<Materia> materi){
        this.Nombre=nombre;
        this.ApellidoMa=apellidoMa;
        this.ApellidoPa=apellidoPa;
        this.Matricula=matricula;
        this.materias=materi;
    }
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidoMa() {
        return ApellidoMa;
    }

    public void setApellidoMa(String ApellidoMa) {
        this.ApellidoMa = ApellidoMa;
    }

    public String getApellidoPa() {
        return ApellidoPa;
    }

    public void setApellidoPa(String ApellidoPa) {
        this.ApellidoPa = ApellidoPa;
    }

    public int getMatricula() {
        return Matricula;
    }

    public void setMatricula(int Matricula) {
        this.Matricula = Matricula;
    }
    public ArrayList<Materia> getMaterias() {
        return this.materias;
    }

    public void setMaterias(ArrayList<Materia> materias) {
        this.materias = materias;
    }
   
}
