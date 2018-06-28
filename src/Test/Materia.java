package Test;

public class Materia {
    private String Nombre;
    private String Codigo;
    private int Asistencia;
    private float U1;
    private float U2;
    private float U3;
    private float U4;
    private float califFinal;
    public Materia(String nombre, String codigo, int asistencia, float u1, float u2, float u3, float u4, float califfinal){
        this.Nombre=nombre;
        this.Codigo=codigo;
        this.Asistencia=asistencia;
        this.U1=u1;
        this.U2=u2;
        this.U3=u3;
        this.U4=u4;
        this.califFinal=califfinal;
    }
    
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        this.Codigo = codigo;
    }

    public int getAsistencia() {
        return Asistencia;
    }

    public void setAsistencia(int Asistencia) {
        this.Asistencia = Asistencia;
    }

    public float getU1() {
        return U1;
    }

    public void setU1(float u1) {
        this.U1 = u1;
    }

    public float getU2() {
        return U2;
    }

    public void setU2(float u2) {
        this.U2 = u2;
    }

    public float getU3() {
        return U3;
    }

    public void setU3(float u3) {
        this.U3 = u3;
    }

    public float getU4() {
        return U4;
    }

    public void setU4(float u4) {
        this.U4 = u4;
    }
     public float getCalifFinal(){
        return this.califFinal;
    }
    
    public void setCalifFinal(int a){
        this.califFinal=a;
    }
}
