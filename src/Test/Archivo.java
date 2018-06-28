package Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Archivo {

    List datos = new ArrayList();

    //pondremos el nombre del archivo aqui
    String nombreArchivo ="ejemplo1.xlsx";
    
    
    //****************************************************************************************
    //cada que tengamos un objeto de la clase archivo leeremos el archivo Excel con los registros
    //****************************************************************************************
    public Archivo(){
        //Vamos a crear un ArrayList que guarde los datos traidos del archivo Excel
        try{
            //Obtiene los bytes del archivo
            FileInputStream fis = new FileInputStream(nombreArchivo);
            //Obtiene la hoja del excel con el nombre dado
            XSSFWorkbook workbook  = new XSSFWorkbook(fis);
            //XSSFSheet son las estructuras centrales dentro de un libro
            XSSFSheet hsf = workbook.getSheetAt(0);
            //un iterador de coleccion de las filas
            Iterator rowIterator = hsf.rowIterator();
            //Nos moveremos por los registros de cada fila
            while(rowIterator.hasNext()){
                //Obtenemos el numero de datos que esta en esa fila
                XSSFRow hsfRow = (XSSFRow) rowIterator.next();
                //para almacenar los datos en el iterator
                Iterator iterador = hsfRow.cellIterator();
                List cellTemp = new ArrayList();
                int cont=-1;
                //para recorrer todos los datos de cada celda
                while(iterador.hasNext()){
                    //cuenta las celdas que tienen datos.
                    XSSFCell hsfCell = (XSSFCell)iterador.next();
                    //los datos que almacenamos en hsfCell los guardamos en el ArrayList temporal
                    cellTemp.add(hsfCell);
                    cont++;
                }
                for(int i=cont;i<10;i++){
                    cellTemp.add("0");
                }
                //y los datos de cada celda los almacenamos en el ArrayList que declaramos al inicio.
                datos.add(cellTemp);
            }            
        }catch(Exception e){
            System.out.println("Error con el archivo\nEl archivo puede estar dañado o el archivo no existe");
        }
        //mandamos llamar el metodo Obtener y le mandamos el ArrayList
        //ImprimirRegistros(datos);
    }
    
    
    
    
    
    //****************************************************************************************
    //**********************imprimir los registros que estan en el excel********************
    //****************************************************************************************
    public void ImprimirRegistros(List listaAlumnos){
        for(int i = 0; i<listaAlumnos.size();i++){
            //toma la primera posicion del ArrayList, toma persona por persona
            List TempList = (List)listaAlumnos.get(i);
            //Se empezara a recorrer todos los atributos de la persona
            for( int j=0; j<TempList.size();j++){
                //se toma un dato y se imprime
                XSSFCell hsfCell = (XSSFCell)TempList.get(j);
                System.out.print(hsfCell.toString()+" ");
            }
            System.out.println(" ");
        }
    }
    
    
    /*************************************************************************************************
    *****************INSERTAMOS NUEVOS REGISTROS AL ARCHIVO EXCEL**********************************
    ******************************************************************************************/
    public void insertarAlumno(Alumno alumno) throws FileNotFoundException, IOException{
        try{
            //Ingresamos el archivo que queremos leer
            File f = new File(nombreArchivo);
            //leemos el archivo
            FileInputStream fis = new FileInputStream(f);
            //usamos el libro del archivo excel
            XSSFWorkbook libro = new XSSFWorkbook(fis);
            //creamos una hoja dentro del libro del archivo
            XSSFSheet hoja = libro.getSheet("Hoja1");
            //vamos a indicar en que fila se encuentra la celda en la que queremos escribir
            int numFila=hoja.getLastRowNum()+1;
            XSSFRow fila = hoja.createRow(numFila);
            //vamos a insertar los datos en la fila
            fila.createCell(0).setCellValue(alumno.getNombre());
            fila.createCell(1).setCellValue(alumno.getApellidoMa());
            fila.createCell(2).setCellValue(alumno.getApellidoPa());
            fila.createCell(3).setCellValue(alumno.getMatricula());
            fila.createCell(4).setCellValue(alumno.getMaterias().get(0).getNombre());
            fila.createCell(5).setCellValue(alumno.getMaterias().get(0).getCodigo());
            fila.createCell(6).setCellValue(alumno.getMaterias().get(0).getAsistencia());
            fila.createCell(7).setCellValue(alumno.getMaterias().get(0).getU1());
            fila.createCell(8).setCellValue(alumno.getMaterias().get(0).getU2());
            fila.createCell(9).setCellValue(alumno.getMaterias().get(0).getU3());
            fila.createCell(10).setCellValue(alumno.getMaterias().get(0).getU4());
            try{
                //hacemos una instancia para poder escribir en el archivo
                FileOutputStream arch = new FileOutputStream(nombreArchivo);
                //escribimos en el archivo
                libro.write(arch);
                //cerramos el archivo
                arch.close();
            }catch(Exception ex){
                System.out.println("El archivo esta dañado o no existe");
            }
        }catch(Exception e){
            System.out.println("El archivo esta dañado o no existe");
        }
    }
    
    
    
    
    
    //****************************************************************************************
    //***********************************comprobar si un dato es numerico*********************
    //****************************************************************************************
    public boolean isNumeric(String cadena){
	try {
            //si la convercion sale mal entonces no es numerico y regresa false
            Integer.parseInt(cadena);
            return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
    
    //validar si el numero de cada unidad esta en el rango del 0 a 100
    public boolean isValid(float num){
        if(num>=0&&num<=100){
            return true;
        }else{
            return false;
        }
    }
    
    /*************************************************************************************************
    ************************CONTAR LAS MATERIAS QUE TIENE CADA ALUMNO*********************************
    *************************************************************************************************/
    public int contarMaterias(int matricula){
        int cont=0;
        
        for(int i = 1; i<datos.size();i++){
            //toma la primera posicion del ArrayList, toma persona por persona
            List TempList = (List)datos.get(i);
            //tomamos el dato de la columna 4, que es la matricula
            XSSFCell hsfCell = (XSSFCell)TempList.get(3);
            //hacemos una convercion de string a flot porque el dato que trae del archivo lo marca con .0
            float conv=Float.parseFloat(hsfCell.getRawValue());
            //despues de ya tenerlo en flotante, le quitaremos los decimales haciendo un casting de float a int
            if((int)conv==matricula){
                //cada que se encuentre la matricula del alumno se contará la materia
                cont++;
            }
        }
        return cont;
    }
    

    /*************************************************************************************************
    ************************CONTAR LOS ALUMNOS QUE ESTAN REGISTRADOS EN LA MATERIA*********************************
    *************************************************************************************************/
    public int contarAlumnosMateria(String claveMateria){
        int cont=0;
        
        for(int i = 1; i<datos.size();i++){
            //toma la primera posicion del ArrayList, toma persona por persona
            List TempList = (List)datos.get(i);
            //tomamos el dato de la columna 4, que es la matricula
            XSSFCell hsfCell = (XSSFCell)TempList.get(5);
            //haremos la validacion de si se han registrado en la materia seleccionada.
            if(hsfCell.toString().equals(claveMateria)){
                //cada que se encuentre a una persona registrada en una materia se ira 
                //contando para sacar un total de cuantas personas estan registradas
                cont++;
            }
        }
        return cont;
    }
    
    //****************************************************************************************
    //*************************++ORDENAR LOS ALUMNOS POR CALIFICACIONES**************************************
    //****************************************************************************************
    public ArrayList<Alumno> OrganizarAlumnoMateria(){
        //creamos un arreglo de enteros donde guardaremos las matriculas de los alumnos
        int[] alumnosTemp=new int[datos.size()];
        //para hacer validaciones y no guardar alumnos repetidos
        int cont=0;
        boolean igual=false;
        
        
        //recorreremos toda la lista para ir sacando todos los alumnos sin repeticiones
        for(int i =1; i<datos.size();i++){
            //hacemos una lista para recibir los registros y poder manipularlos
            List listTemp = (List) datos.get(i);
            //hacemos un casting de la matricula que conseguimos del registo
            int matricula =(int)(Float.parseFloat(listTemp.get(3).toString()));

            //recorremos el arreglo para validar si el alumno se repite
            for(int j=0;j<alumnosTemp.length;j++){
                if(alumnosTemp[j]==matricula){
                    //en caso de que la matricula ya este entonces se interpreta como que ya esta
                    igual=true;
                }
            }
            //si no esta entonces se interpretara con el false de que no esta en el arreglo con las matriculas
            if(igual==false){
                //se agregará la matricula del alumno
                alumnosTemp[cont]=matricula;
                cont++;
            }
            //se reinicia el valor de igual
            igual=false;
        }
        
        int[] alumnos = new int[cont];
        for(int i=0;i<cont;i++){
            alumnos[i]=alumnosTemp[i];
        }
        
        //guardaremos los datos del alumno tales como nombre, apellido paterno y materno.
        String nombre="";
        String ap="";
        String am="";

        //haremos un arreglo de alumnos donde cada uno tendrá sus materias
        ArrayList<Alumno> alumno=new ArrayList<Alumno>();
        //recorreremos los alumnos registrados con materias
        for(int i=0;i<alumnos.length;i++){
            //las materias que seran insertadas en la clase alumno
            ArrayList<Materia> materias = new ArrayList<Materia>();
            //recorreremos la lista de alumnos para encontrar las materias de un alumno en especifico
            for(int j=1;j<datos.size();j++){
                //hacemos una lista temporal porque manejaremos los registros de 1 por 1
                List listTemp = (List) datos.get(j);
                //hacemos un casting de la matricula que conseguimos del registo
                int matricula =(int)(Float.parseFloat(listTemp.get(3).toString()));
                //si la matricula concuerda 
                if(matricula==alumnos[i]){
                    //iremos agregando las materias del alumno al arreglo de materias
                    float finalcal;
                    if(Float.parseFloat(listTemp.get(7).toString())<=60||Float.parseFloat(listTemp.get(8).toString())<=60||Float.parseFloat(listTemp.get(9).toString())<=60||Float.parseFloat(listTemp.get(10).toString())<=60){
                        finalcal=60;
                    }else{
                        finalcal=(Float.parseFloat(listTemp.get(7).toString())+Float.parseFloat(listTemp.get(8).toString())+Float.parseFloat(listTemp.get(9).toString())+Float.parseFloat(listTemp.get(10).toString()))/4;
                    }
                    materias.add(new Materia(listTemp.get(4).toString(),listTemp.get(5).toString(),(int)(Float.parseFloat(listTemp.get(6).toString())),Float.parseFloat(listTemp.get(7).toString()),Float.parseFloat(listTemp.get(8).toString()),Float.parseFloat(listTemp.get(9).toString()),Float.parseFloat(listTemp.get(10).toString()),finalcal ));
                    //tomaremos el nombre completo del alumno
                    nombre=listTemp.get(0).toString();
                    ap=listTemp.get(1).toString();
                    am=listTemp.get(2).toString();
                }
            }
            
            
            //agregaremos al alumno junto con sus materias
            alumno.add(new Alumno(nombre,ap,am,alumnos[i],materias));    
        }
        
        return alumno;
    }
    
    
    
    /*************************************************************************************************
    ************************BUSCAR ALUMNO POR MATRICULA*********************************
    *************************************************************************************************/
    public Alumno buscarAlumnoMatricula(int matricula){
        ArrayList<Alumno> alumnos = OrganizarAlumnoMateria();
        for(int i =0; i<alumnos.size();i++){
            if(alumnos.get(i).getMatricula()==matricula){
                return alumnos.get(i);
            }
        }
        return null;
    }
}