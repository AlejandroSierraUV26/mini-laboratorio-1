package principal;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import logica.Gato;
import logica.Mascota;
import logica.Pais_Origen;
import logica.Perro;
import logica.Vacuna;
public class App {
    public static void Insertar_Mascota(ArrayList<Mascota> lista_mascotas) throws InterruptedException, IOException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        String nombre,color,raza;
        byte numero_patas, años, cantidad_vacunas=0;
        int precio,opcion,opcion_animal;
        Pais_Origen pais_origen;
        nombre = JOptionPane.showInputDialog(null,"Ingrese el nombre de su mascota");
        años = Byte.parseByte(JOptionPane.showInputDialog(null,"Ingrese el año de su mascota"));
        numero_patas = Byte.parseByte(JOptionPane.showInputDialog(null,"Ingrese el numero de piernas de su mascota"));
        color = JOptionPane.showInputDialog(null,"Ingrese el color de su mascota");
        precio = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el valor de su mascota"));
        raza = JOptionPane.showInputDialog(null,"Ingrese la raza de su mascota: ");
        opcion = 0;
        //Origen
        opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese que pais de origen es su animal\n1. Colombia\n2. España\n3. Alemania\n4. Inglaterra\n5. Ecuador\n6. Brasil \n7. Canada\n8. Noruega"));
        switch(opcion){
            //Colombia,España,USA,Alemania,Inglaterra,Ecuador,Brasil,Canada,Noruega
            case 1:pais_origen = Pais_Origen.Colombia;break;
            case 2:pais_origen = Pais_Origen.España;break;
            case 3:pais_origen = Pais_Origen.Alemania;break;
            case 4:pais_origen = Pais_Origen.Inglaterra;break;
            case 5:pais_origen = Pais_Origen.Ecuador;break;
            case 6:pais_origen = Pais_Origen.Brasil;break;
            case 7:pais_origen = Pais_Origen.Canada;break;
            case 8:pais_origen = Pais_Origen.Noruega;break;
            default:pais_origen =null;break;
        }
        //Puede ser una o pueden ser varias.
        //Por ahora solo van a ser una
        ArrayList<String> lista_vacunas = new ArrayList<String>();
            lista_vacunas.add("malota");
            lista_vacunas.add("rabia");
            lista_vacunas.add("mejorcito");
            lista_vacunas.add("alivio2000");
            int numero_vacuna;
            numero_vacuna = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese que vacuna desea inyectar \n 1. Malota\n 2. Rabia \n 3. Mejorcito \n 4. Alivio2000"));
            Vacuna vacuna = new Vacuna(lista_vacunas.get(numero_vacuna-1));
        opcion_animal = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese que mascota:\n1. Perro \n2. Gato"));
        if(opcion_animal == 1){        
            Perro perro1 = new Perro(nombre,numero_patas,años,cantidad_vacunas,precio,raza,pais_origen,color);
            perro1.insertarInyeccion(vacuna);
            perro1.MostrarDatos();
            lista_mascotas.add(perro1);
        }
        else if(opcion_animal == 2){
            Gato gato1 = new Gato(nombre,numero_patas,años,cantidad_vacunas,precio,raza,pais_origen,color);
            gato1.insertarInyeccion(vacuna);
            gato1.MostrarDatos();
            lista_mascotas.add(gato1);
        }

        
    }
    public static void Actualizar_Mascota(ArrayList<Mascota> lista_mascotas) throws InterruptedException, IOException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        if(lista_mascotas.size() ==0){
            System.out.println("-----------------------------------------------------------------");
            System.out.println("        No hay Mascotas en la Veterinaria");
            System.out.println("-----------------------------------------------------------------");
            JOptionPane.showMessageDialog(null, "No es posible Actualizar Mascota", null, 0);
        }
        else{
            int i=0;
        i = Buscar_Mascota(lista_mascotas);
        if(i>=0){
        String nombre;
        byte años;
        int precio,opcion;
        opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "Que desea cambiar de su mascota\n"
        +"1.Cambia el nombre de su mascota.\n"
        +"2. Cambiar la edad de su mascota\n"
        +"3. Cambia el total de vacunas y agrega una nueva.\n"
        +"4.Cambiar el precio de la mascota"));
        System.out.println("-----------------------------------------------------------------");
        System.out.println("=================================================================");
        switch(opcion){
            case 1:
            //Cambia el nombre del perro.
            System.out.println("Cambia el nombre");
            System.out.println("Nombre actual : "+lista_mascotas.get(i).getNombre());
            nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre de su mascota:");
            lista_mascotas.get(i).setNombre(nombre);
            System.out.println("Nombre nuevo : " + lista_mascotas.get(i).getNombre());
            break;
            case 2:
            //Cambiar la edad del perro
            System.out.println("Cambia edad");
            System.out.println("Edad anterior : "+lista_mascotas.get(i).getAños());
            años = Byte.parseByte(JOptionPane.showInputDialog(null, "Ingrese la edad nueva del perro:"));
            lista_mascotas.get(i).setAños(años);
            System.out.println("Edad nueva : " + lista_mascotas.get(i).getAños());
            break;
            case 3:
            Aplicar_Inyeccion(lista_mascotas);
            JOptionPane.showMessageDialog(null,"Cantidad de vacunas actuales: " +lista_mascotas.get(i).cantidad_vacunas());
            break;
            case 4:
            //Cambiar el precio de la mascota
            System.out.println("Cambia precio");
            System.out.println("Precio anterior: "+lista_mascotas.get(i).getPrecio());
            precio = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el precio nuevo de su mascota"));
            lista_mascotas.get(i).setPrecio(precio);
            System.out.println("Precio nuevo : " + lista_mascotas.get(i).getPrecio());

        }
        System.out.println("=================================================================");
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        lista_mascotas.get(i).MostrarDatos();
        }
        else{
            JOptionPane.showMessageDialog(null,"Al parecer esa mascota no esta aqui");
        }
        }
    }
    public static void Eliminar_Mascota(ArrayList<Mascota> lista_mascotas) throws InterruptedException, IOException{
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        if(lista_mascotas.size() ==0){
            System.out.println("-----------------------------------------------------------------");
            System.out.println("        No hay Mascotas en la Veterinaria");
            System.out.println("-----------------------------------------------------------------");
            JOptionPane.showMessageDialog(null, "No es posible Eliminar Mascota", null, 0);
        }
        else{
            int i= 0;
            i = Buscar_Mascota(lista_mascotas);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            if (!lista_mascotas.isEmpty()){
                lista_mascotas.remove(i);
                System.out.println("----------------------------------------------------\n              Mascota Eliminada\n----------------------------------------------------");
                JOptionPane.showMessageDialog(null, "Se elimino la mascota", null, 1);
            }
        }
           
    }
    public static int Buscar_Mascota(ArrayList<Mascota> lista_mascotas) throws InterruptedException, IOException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        if(lista_mascotas.size()== 0){
            System.out.println("-----------------------------------------------------------------");
            System.out.println("        No hay Mascotas en la Veterinaria");
            System.out.println("-----------------------------------------------------------------");
            JOptionPane.showMessageDialog(null, "No es posible Buscar Mascota", null, 0);
        }
        else{
        String nombre;
        nombre = JOptionPane.showInputDialog(null,"Ingrese el nombre de su mascota");
        for(int i=0; i<lista_mascotas.size();i++){
            if(lista_mascotas.get(i).getNombre().equals(nombre)){
                lista_mascotas.get(i).MostrarDatos();
                return i;
            }
            }
        }
        return -1;

    }   
    public static void Listar_Todas_Mascotas(ArrayList<Mascota> lista_mascotas) throws InterruptedException, IOException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        if(lista_mascotas.size()==0){
            System.out.println("-----------------------------------------------------------------");
            System.out.println("        No hay Mascotas en la Veterinaria");
            System.out.println("-----------------------------------------------------------------");
            JOptionPane.showMessageDialog(null, "No es posible Alistar las Mascotas", null, 0);
        }
        for (int i = 0; i < lista_mascotas.size(); i++){
            lista_mascotas.get(i).MostrarDatos();
        }
    }
    public static void VacunaMalota(ArrayList<Mascota> lista_mascotas) throws InterruptedException, IOException{
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        if(lista_mascotas.size()==0){
            System.out.println("-----------------------------------------------------------------");
            System.out.println("        No hay Mascotas con Vacunas en la Veterinaria");
            System.out.println("-----------------------------------------------------------------");
            JOptionPane.showMessageDialog(null, "No es posible Conocer que Mascota tiene la vacuna MALOTA", null, 0);
        }
        //Aqui
            for(int j=0;j<lista_mascotas.size();j++){
                for(int i=0; i<lista_mascotas.get(j).getVacunas().size();i++){    
                if(lista_mascotas.get(j).getVacunas().get(i).getNombre().equals("malota")){
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("        La mascota [" + lista_mascotas.get(j).getNombre()+ "] tiene la vacuna malota");
                    System.out.println("-----------------------------------------------------------------");
                }
        }
    }
    }
    public static void Mayor_Precio_Mascotas(ArrayList<Mascota> lista_mascotas) throws InterruptedException, IOException{
    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    if(lista_mascotas.size() == 0) {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("        No hay Mascotas en la Veterinaria");
        System.out.println("-----------------------------------------------------------------");
    }
    else{
        int nElementos,precio_actual,precio_siguiente,contador=0;
        Mascota aux = lista_mascotas.get(0);
        nElementos = lista_mascotas.size();
        for(int i=0;i<nElementos-1;i++){ 
            for(int j=0;j<nElementos-1;j++){
                precio_actual = lista_mascotas.get(j).getPrecio();
                precio_siguiente = lista_mascotas.get(j+1).getPrecio();
                if(precio_actual < precio_siguiente){
                    aux = lista_mascotas.get(j);
                    lista_mascotas.set(j,lista_mascotas.get(j+1));
                    lista_mascotas.set(j+1,aux);
                    }
                }
            }
        for (int i=0;i<lista_mascotas.size();i++){
            if(contador==5){
                i=lista_mascotas.size()+1;
            }
            System.out.println("-----------------------------------------------------------------");
            System.out.println("                      Mascota "+(i+1));
            System.out.println("                      "+lista_mascotas.get(i).getNombre());
            System.out.println("                      $" + lista_mascotas.get(i).getPrecio());
            System.out.println("-----------------------------------------------------------------");
            contador=contador+1;
        }
    }
    }
    public static void Lista_Origen_Mascotas(ArrayList<Mascota> lista_mascotas) throws InterruptedException, IOException{
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        if(lista_mascotas.size()==0){
            System.out.println("-----------------------------------------------------------------");
            System.out.println("        No hay Mascotas en la Veterinaria");
            System.out.println("-----------------------------------------------------------------");
            JOptionPane.showMessageDialog(null, "No es posible Cononcer el origen de la Mascota", null, 0);
        }
        else{
        ArrayList<Pais_Origen> lista_paises = new ArrayList<Pais_Origen>();
        lista_paises.add(Pais_Origen.Brasil);
        lista_paises.add(Pais_Origen.Colombia);
        lista_paises.add(Pais_Origen.Ecuador);
        
        for(int i=0; i<lista_paises.size(); i++){
            for(int j=0; j<lista_mascotas.size(); j++){
                System.out.println("-----------------------------------------------------------------");
                if(lista_mascotas.get(j).getPais_origen() ==lista_paises.get(i)){
                    System.out.println("           Su mascota"+lista_mascotas.get(j).getNombre()+" esta dentro de latinoamerica");
                }
                else if(i==lista_paises.size()-1){
                    System.out.println("           Su mascota "+lista_mascotas.get(j).getNombre() +" tiene origen fuera de latinoamerica");
                }
                System.out.println("-----------------------------------------------------------------");
            }
        }
    }
        
    }
    public static void Aplicar_Inyeccion(ArrayList<Mascota> lista_mascotas) throws InterruptedException, IOException{
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        if(lista_mascotas.size() == 0){
            System.out.println("-----------------------------------------------------------------");
            System.out.println("        No hay Mascotas en la Veterinaria");
            System.out.println("-----------------------------------------------------------------");
            JOptionPane.showMessageDialog(null, "No es posible Aplicar Vacuna Mascota", null, 0);
        }
        else{
        ArrayList<String> lista_vacunas = new ArrayList<String>();
        lista_vacunas.add("malota");
        lista_vacunas.add("rabia");
        lista_vacunas.add("mejorcito");
        lista_vacunas.add("alivio2000");   
        //Comparar si la mascota ya tiene la vacuna
        //Mensaje: Su mascota ya tiene esa inyeccion.
        int i,numero_vacuna;
        i = Buscar_Mascota(lista_mascotas);
        numero_vacuna = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese que vacuna desea inyectar \n 1. Malota\n 2. Rabia \n 3. Mejorcito \n 4. Alivio2000"));
        Vacuna vacuna = new Vacuna(lista_vacunas.get(numero_vacuna-1));
        lista_mascotas.get(i).insertarInyeccion(vacuna);
        lista_mascotas.get(i).imprimirVacunas();
    
        }
    }
    public static void main(String[] args) throws Exception {
        System.out.println("Bienvenido a nuestra veterinaria");
        ArrayList <Mascota> lista_mascotas = new ArrayList<Mascota>();
        byte opcion,opcion2;
        while(true){
        opcion = Byte.parseByte(JOptionPane.showInputDialog("Seleccione la opcion que desea: \n1. Insertar mascota\n2. Actualizar mascota\n3. Eliminar mascota\n4. Buscar mascota por nombre\n5. Listar todas las mascotas\n6. Aplicar Vacuna\n7. Salir", null));
        switch(opcion){
            case 1:Insertar_Mascota(lista_mascotas);break;case 2:Actualizar_Mascota(lista_mascotas);break;case 3:Eliminar_Mascota(lista_mascotas);break;
            case 4:Buscar_Mascota(lista_mascotas);break;case 5:Listar_Todas_Mascotas(lista_mascotas);break;case 6:Aplicar_Inyeccion(lista_mascotas);break;
            case 7: System.exit(0);default:break;
        }
        opcion2 = Byte.parseByte(JOptionPane.showInputDialog("Seleccione la opcion que desea: \n1. Qué mascotas tienen la vacuna malota\n2. Cual es el top 5 de las mascotas más costosas.\n3. Que mascotas no tienen país de origen en latinoamérica.", null));
        switch (opcion2) {
            case 1:VacunaMalota(lista_mascotas);break;case 2:Mayor_Precio_Mascotas(lista_mascotas);break;
            case 3:Lista_Origen_Mascotas(lista_mascotas);break;default:break;
        }
    }
    }
}
