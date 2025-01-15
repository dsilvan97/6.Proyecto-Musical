package com.biogade.proyectomusical.principal;

import com.biogade.proyectomusical.model.Cancion;
import com.biogade.proyectomusical.model.Cantante;
import com.biogade.proyectomusical.repository.CantanteRepository;

import java.util.*;

public class Principal {

    Scanner consola = new Scanner(System.in);
    private CantanteRepository repositorio;
    Optional<Cantante> verificarExistenciaCantante;

    public Principal(CantanteRepository repository){this.repositorio = repository;}

    public void mostrarMenu() {

        //Variable para mantener en loop el programa.
        var opcion = -1;

        //Loop que ejecutará el programa hasta que el usuario envíe el número "0".
        while (opcion != 0) {
            System.out.println("""
                    Selecciona la opción que desees:
                    
                    1 - Agregar Cantante.
                    2 - Agregar Cancion.
                    3 - Mostrar Cantantes.
                    4 - Mostrar Canciones de un Cantante.
                    5 - Buscar Canciones por Titulo.
                    
                    0 - Salir.
                    """);

            opcion = consola.nextInt();
            consola.nextLine();

            switch (opcion){
                case 1:
                    agregarCantante();
                    break;

                case 2:
                    agregarCancion();
                    break;

                case 3:
                    mostrarCantantes();
                    break;

                case 4:
                    mostrarCancionesDeCantante();
                    break;

                case 5:
                    mostrarCancionesPorTitulo();
                    break;

                case 0:
                    System.out.println("Cerrando Aplicación...");
                    break;

                default:
                    System.out.println("Opción Inválida");
            }

        }

        System.exit(0);
    }

    public void agregarCantante(){
        System.out.println("Ingresa el nombre del cantante que deseas agregar:");
        var nombreCantante = consola.nextLine();

        System.out.println("Ingresa la edad del cantante:");
        var edadCantante = consola.nextInt();
        consola.nextLine();

        System.out.println("Indica el Género Musical del Cantante:");
        var generoCantante = consola.nextLine();

        System.out.println("Ingresa la nacionalidad del cantante:");
        var nacionalidadCantante = consola.nextLine();

        System.out.println("Ingresa la biografía del cantante:");
        var biografiaCantante = consola.nextLine();


        verificarExistenciaCantante = repositorio.findByNombreContainsIgnoreCase(nombreCantante);

        if (verificarExistenciaCantante.isPresent()){
            System.out.println("Ya existe este artista en la base de datos...");
            System.out.println(verificarExistenciaCantante.get());
        }else {
            Cantante nuevoCantante = new Cantante(nombreCantante, edadCantante, generoCantante, nacionalidadCantante, biografiaCantante);
            repositorio.save(nuevoCantante);
            System.out.println("Cantante agregado con éxito:");
            System.out.println(nuevoCantante);
        }

    }

    public void agregarCancion(){
        System.out.println("\n***********************************************************************************");
        System.out.println("Artistas Existentes:");
        repositorio.findAll().stream()
                .forEach(e-> System.out.println(e.getNombre()));
        System.out.println("""
                ***********************************************************************************\n
                Ingresa el nombre de uno de los artistas de la anterior lista:
                """);
        var nombreCantante = consola.nextLine();

        verificarExistenciaCantante = repositorio.findByNombreContainsIgnoreCase(nombreCantante);

        if (verificarExistenciaCantante.isPresent()){

            Cantante cantanteSeleccionado = verificarExistenciaCantante.get();

            System.out.println("Artista encontrado: " + cantanteSeleccionado);

            System.out.println("Ingresa el título de la canción:");
            var nombreCancion = consola.nextLine();

            System.out.println("Ingresa la duración de la canción en SEGUNDOS:");
            var duracionCancion = consola.nextInt();
            consola.nextLine();

            System.out.println("Ingresa el álbum al cual pertenece esta canción:");
            var albumCancion = consola.nextLine();

            Cancion cancionNueva = new Cancion(nombreCancion, duracionCancion, albumCancion, cantanteSeleccionado);

            List<Cancion> listaCancion = new ArrayList<>();
            listaCancion.add(cancionNueva);

            cantanteSeleccionado.setCanciones(listaCancion);

            repositorio.save(cantanteSeleccionado);

            System.out.println("Canción guardada con éxito...");
            System.out.println(cancionNueva);

        }else {
            System.out.println("No existe el artista en la base de datos...");

        }

    }

    public void mostrarCantantes(){
        System.out.println("""
                ***********************************************************************************
                A continuación los cantantes que has buscado hasta el momento:""");

        repositorio.findAll().stream()
                .forEach(e -> System.out.println(String.format("""
                        -----------CANTANTE-------------
                        Nombre: %s
                        Edad: %d
                        Genero Musical: %s
                        Biografía: %s
                        --------------------------------
                        """,e.getNombre(), e.getEdad(), e.getGeneroMusical(), e.getBiografia())));

        System.out.println("***********************************************************************************");
    }

    public void mostrarCancionesDeCantante(){
        System.out.println("\n***********************************************************************************");
        System.out.println("Artistas Existentes:");
        repositorio.findAll().stream()
                .forEach(e-> System.out.println(e.getNombre()));
        System.out.println("""
                ***********************************************************************************\n
                Ingresa el nombre de uno de los artistas de la anterior lista:
                """);

        var artistaABuscar = consola.nextLine();

        Optional<Cantante> cantanteBuscado = repositorio.findByNombreContainsIgnoreCase(artistaABuscar);

        if (cantanteBuscado.isPresent()){
            Cantante cantanteEncontrado = cantanteBuscado.get();

            System.out.println("El artista seleccionado es " + cantanteEncontrado.getNombre() + ":");
            cantanteEncontrado.getCanciones().stream()
                    .forEach(e -> System.out.println(String.format("""
                            ------------CANCIÓN------------
                            Titulo: %s
                            Álbum: %s
                            Duración en Segundos: %d
                            -------------------------------
                            """, e.getTitulo(), e.getAlbum(), e.getDuracionEnSegundos())));
        }else {
            System.out.println("El artista no se encuentra en la base de datos...");
        }
    }

    public void mostrarCancionesPorTitulo(){
        System.out.println("Ingresa la canción que desees buscar:");
        var cancionBuscada = consola.nextLine();

        System.out.println("\nMostrando canciones que contengan '" + cancionBuscada + "':\n");

        repositorio.cancionesPorTitulo(cancionBuscada).stream()
                .forEach(e-> System.out.println(String.format("""
                        ***********************************************************************************
                        Artista: %s
                        Titulo: %s
                        Álbum: %s
                        Duración en Segundos: %d                        
                        ***********************************************************************************
                        """,e.getCantante().getNombre(), e.getTitulo(), e.getAlbum(), e.getDuracionEnSegundos())));

    }

}