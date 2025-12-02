package com.biblioteca.util;

import com.biblioteca.model.*;
import com.biblioteca.service.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private PersonaService personaService;
    private UsuarioSistemaService usuarioSistemaService;
    private AlumnoService alumnoService;
    private ProfesorService profesorService;
    private AdministrativoService administrativoService;
    private TipoContactoService tipoContactoService;
    private DataContactoService dataContactoService;
    private CategoriaService categoriaService;
    private RecursoService recursoService;
    private PrestamoService prestamoService;
    private DetallePrestamoService detallePrestamoService;
    private MultaService multaService;
    private DevolucionService devolucionService;
    private DateTimeFormatter dateFormatter;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.personaService = new PersonaService();
        this.usuarioSistemaService = new UsuarioSistemaService();
        this.alumnoService = new AlumnoService();
        this.profesorService = new ProfesorService();
        this.administrativoService = new AdministrativoService();
        this.tipoContactoService = new TipoContactoService();
        this.dataContactoService = new DataContactoService();
        this.categoriaService = new CategoriaService();
        this.recursoService = new RecursoService();
        this.prestamoService = new PrestamoService();
        this.detallePrestamoService = new DetallePrestamoService();
        this.multaService = new MultaService();
        this.devolucionService = new DevolucionService();
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public void mostrarMenuPrincipal() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n===== SISTEMA DE GESTIÓN DE BIBLIOTECA =====");
            System.out.println("1. Gestionar Usuarios del Sistema");
            System.out.println("2. Gestionar Alumnos");
            System.out.println("3. Gestionar Profesores");
            System.out.println("4. Gestionar Administrativos");
            System.out.println("5. Gestionar Tipos de Contacto");
            System.out.println("6. Gestionar Categorías");
            System.out.println("7. Gestionar Recursos");
            System.out.println("8. Gestionar Préstamos");
            System.out.println("9. Gestionar Multas");
            System.out.println("10. Gestionar Devoluciones");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    menuUsuariosSistema();
                    break;
                case 2:
                    menuAlumnos();
                    break;
                case 3:
                    menuProfesores();
                    break;
                case 4:
                    menuAdministrativos();
                    break;
                case 5:
                    menuTiposContacto();
                    break;
                case 6:
                    menuCategorias();
                    break;
                case 7:
                    menuRecursos();
                    break;
                case 8:
                    menuPrestamos();
                    break;
                case 9:
                    menuMultas();
                    break;
                case 10:
                    menuDevoluciones();
                    break;
                case 0:
                    salir = true;
                    System.out.println("Gracias por usar el sistema.");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void menuPersonas() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n===== GESTIÓN DE PERSONAS =====");
            System.out.println("1. Registrar Persona");
            System.out.println("2. Buscar Persona");
            System.out.println("3. Listar Personas");
            System.out.println("4. Actualizar Persona");
            System.out.println("5. Eliminar Persona");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarPersona();
                    break;
                case 2:
                    buscarPersona();
                    break;
                case 3:
                    listarPersonas();
                    break;
                case 4:
                    actualizarPersona();
                    break;
                case 5:
                    eliminarPersona();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void registrarPersona() {
        System.out.println("\n--- Registrar Persona ---");
        System.out.print("Nombres: ");
        String nombres = scanner.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.print("Fecha de nacimiento (yyyy-MM-dd): ");
        LocalDate fechaNacimiento = leerFecha();
        System.out.print("Número de documento: ");
        String numeroDocumento = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Nacionalidad: ");
        String nacionalidad = scanner.nextLine();

        Persona persona = new Persona(nombres, apellidos, fechaNacimiento, numeroDocumento, direccion, nacionalidad) {};
        personaService.registrarPersona(persona);
    }

    private void buscarPersona() {
        System.out.println("\n--- Buscar Persona ---");
        System.out.print("ID de la Persona: ");
        int id = leerEntero();
        Persona persona = personaService.buscarPersona(id);
        if (persona != null) {
            System.out.println(persona);
        } else {
            System.out.println("Persona no encontrada");
        }
    }

    private void listarPersonas() {
        System.out.println("\n--- Listado de Personas ---");
        List<Persona> personas = personaService.listarPersonas();
        if (personas.isEmpty()) {
            System.out.println("No hay personas registradas");
        } else {
            personas.forEach(System.out::println);
        }
    }

    private void actualizarPersona() {
        System.out.println("\n--- Actualizar Persona ---");
        System.out.print("ID de la Persona: ");
        int id = leerEntero();
        Persona persona = personaService.buscarPersona(id);
        if (persona != null) {
            System.out.print("Nuevos nombres: ");
            persona.setNombres(scanner.nextLine());
            System.out.print("Nuevos apellidos: ");
            persona.setApellidos(scanner.nextLine());
            System.out.print("Nueva dirección: ");
            persona.setDireccion(scanner.nextLine());
            personaService.actualizarPersona(persona);
        } else {
            System.out.println("Persona no encontrada");
        }
    }

    private void eliminarPersona() {
        System.out.println("\n--- Eliminar Persona ---");
        System.out.print("ID de la Persona: ");
        int id = leerEntero();
        personaService.eliminarPersona(id);
    }

    private void menuUsuariosSistema() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n===== GESTIÓN DE USUARIOS DEL SISTEMA =====");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Buscar Usuario");
            System.out.println("3. Listar Usuarios");
            System.out.println("4. Actualizar Usuario");
            System.out.println("5. Eliminar Usuario");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarUsuarioSistema();
                    break;
                case 2:
                    buscarUsuarioSistema();
                    break;
                case 3:
                    listarUsuariosSistema();
                    break;
                case 4:
                    actualizarUsuarioSistema();
                    break;
                case 5:
                    eliminarUsuarioSistema();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void registrarUsuarioSistema() {
        System.out.println("\n--- Registrar Usuario del Sistema ---");
        System.out.print("Nombres: ");
        String nombres = scanner.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.print("Fecha de nacimiento (yyyy-MM-dd): ");
        LocalDate fechaNacimiento = leerFecha();
        System.out.print("Número de documento: ");
        String numeroDocumento = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Nacionalidad: ");
        String nacionalidad = scanner.nextLine();
        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();

        UsuarioSistema usuarioSistema = new UsuarioSistema(nombres, apellidos, fechaNacimiento, numeroDocumento, direccion, nacionalidad, usuario, contrasena);
        usuarioSistemaService.registrarUsuario(usuarioSistema);
    }

    private void buscarUsuarioSistema() {
        System.out.println("\n--- Buscar Usuario del Sistema ---");
        System.out.print("ID del Usuario: ");
        int id = leerEntero();
        UsuarioSistema usuario = usuarioSistemaService.buscarUsuario(id);
        if (usuario != null) {
            System.out.println(usuario);
        } else {
            System.out.println("Usuario no encontrado");
        }
    }

    private void listarUsuariosSistema() {
        System.out.println("\n--- Listado de Usuarios del Sistema ---");
        List<UsuarioSistema> usuarios = usuarioSistemaService.listarUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados");
        } else {
            usuarios.forEach(System.out::println);
        }
    }

    private void actualizarUsuarioSistema() {
        System.out.println("\n--- Actualizar Usuario del Sistema ---");
        System.out.print("ID del Usuario: ");
        int id = leerEntero();
        UsuarioSistema usuario = usuarioSistemaService.buscarUsuario(id);
        if (usuario != null) {
            System.out.print("Nuevos nombres: ");
            usuario.setNombres(scanner.nextLine());
            System.out.print("Nuevos apellidos: ");
            usuario.setApellidos(scanner.nextLine());
            System.out.print("Nuevo usuario: ");
            usuario.setUsuario(scanner.nextLine());
            System.out.print("Nueva contraseña: ");
            usuario.setContrasenaSystem(scanner.nextLine());
            usuarioSistemaService.actualizarUsuario(usuario);
        } else {
            System.out.println("Usuario no encontrado");
        }
    }

    private void eliminarUsuarioSistema() {
        System.out.println("\n--- Eliminar Usuario del Sistema ---");
        System.out.print("ID del Usuario: ");
        int id = leerEntero();
        usuarioSistemaService.eliminarUsuario(id);
    }

    private void menuAlumnos() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n===== GESTIÓN DE ALUMNOS =====");
            System.out.println("1. Registrar Alumno");
            System.out.println("2. Buscar Alumno");
            System.out.println("3. Listar Alumnos");
            System.out.println("4. Actualizar Alumno");
            System.out.println("5. Eliminar Alumno");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarAlumno();
                    break;
                case 2:
                    buscarAlumno();
                    break;
                case 3:
                    listarAlumnos();
                    break;
                case 4:
                    actualizarAlumno();
                    break;
                case 5:
                    eliminarAlumno();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void registrarAlumno() {
        System.out.println("\n--- Registrar Alumno ---");
        System.out.print("Nombres: ");
        String nombres = scanner.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.print("Fecha de nacimiento (yyyy-MM-dd): ");
        LocalDate fechaNacimiento = leerFecha();
        System.out.print("Número de documento: ");
        String numeroDocumento = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Nacionalidad: ");
        String nacionalidad = scanner.nextLine();
        System.out.print("Código de alumno: ");
        String codigoAlumno = scanner.nextLine();

        Alumno alumno = new Alumno(nombres, apellidos, fechaNacimiento, numeroDocumento, direccion, nacionalidad, codigoAlumno);
        alumnoService.registrarAlumno(alumno);

        // Buscar el alumno recién registrado para obtener su ID
        Alumno alumnoRegistrado = alumnoService.buscarPorCodigo(codigoAlumno);
        if (alumnoRegistrado != null) {
            registrarDatosContacto(alumnoRegistrado.getIdPersona());
        }
    }

    private void buscarAlumno() {
        System.out.println("\n--- Buscar Alumno ---");
        System.out.print("ID del Alumno: ");
        int id = leerEntero();
        Alumno alumno = alumnoService.buscarAlumno(id);
        if (alumno != null) {
            System.out.println(alumno);
        } else {
            System.out.println("Alumno no encontrado");
        }
    }

    private void listarAlumnos() {
        System.out.println("\n--- Listado de Alumnos ---");
        List<Alumno> alumnos = alumnoService.listarAlumnos();
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados");
        } else {
            alumnos.forEach(System.out::println);
        }
    }

    private void actualizarAlumno() {
        System.out.println("\n--- Actualizar Alumno ---");
        System.out.print("ID del Alumno: ");
        int id = leerEntero();
        Alumno alumno = alumnoService.buscarAlumno(id);
        if (alumno != null) {
            System.out.print("Nuevos nombres: ");
            alumno.setNombres(scanner.nextLine());
            System.out.print("Nuevos apellidos: ");
            alumno.setApellidos(scanner.nextLine());
            System.out.print("Nuevo código: ");
            alumno.setCodigoAlumno(scanner.nextLine());
            alumnoService.actualizarAlumno(alumno);
        } else {
            System.out.println("Alumno no encontrado");
        }
    }

    private void eliminarAlumno() {
        System.out.println("\n--- Eliminar Alumno ---");
        System.out.print("ID del Alumno: ");
        int id = leerEntero();
        alumnoService.eliminarAlumno(id);
    }

    private void menuProfesores() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n===== GESTIÓN DE PROFESORES =====");
            System.out.println("1. Registrar Profesor");
            System.out.println("2. Buscar Profesor");
            System.out.println("3. Listar Profesores");
            System.out.println("4. Actualizar Profesor");
            System.out.println("5. Eliminar Profesor");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarProfesor();
                    break;
                case 2:
                    buscarProfesor();
                    break;
                case 3:
                    listarProfesores();
                    break;
                case 4:
                    actualizarProfesor();
                    break;
                case 5:
                    eliminarProfesor();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void registrarProfesor() {
        System.out.println("\n--- Registrar Profesor ---");
        System.out.print("Nombres: ");
        String nombres = scanner.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.print("Fecha de nacimiento (yyyy-MM-dd): ");
        LocalDate fechaNacimiento = leerFecha();
        System.out.print("Número de documento: ");
        String numeroDocumento = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Nacionalidad: ");
        String nacionalidad = scanner.nextLine();
        System.out.print("Código de profesor: ");
        String codigoProfesor = scanner.nextLine();
        System.out.print("Grado de estudio: ");
        String gradoEstudio = scanner.nextLine();

        Profesor profesor = new Profesor(nombres, apellidos, fechaNacimiento, numeroDocumento, direccion, nacionalidad, codigoProfesor, gradoEstudio);
        profesorService.registrarProfesor(profesor);

        // Buscar el profesor recién registrado para obtener su ID
        Profesor profesorRegistrado = profesorService.buscarPorCodigo(codigoProfesor);
        if (profesorRegistrado != null) {
            registrarDatosContacto(profesorRegistrado.getIdPersona());
        }
    }

    private void buscarProfesor() {
        System.out.println("\n--- Buscar Profesor ---");
        System.out.print("ID del Profesor: ");
        int id = leerEntero();
        Profesor profesor = profesorService.buscarProfesor(id);
        if (profesor != null) {
            System.out.println(profesor);
        } else {
            System.out.println("Profesor no encontrado");
        }
    }

    private void listarProfesores() {
        System.out.println("\n--- Listado de Profesores ---");
        List<Profesor> profesores = profesorService.listarProfesores();
        if (profesores.isEmpty()) {
            System.out.println("No hay profesores registrados");
        } else {
            profesores.forEach(System.out::println);
        }
    }

    private void actualizarProfesor() {
        System.out.println("\n--- Actualizar Profesor ---");
        System.out.print("ID del Profesor: ");
        int id = leerEntero();
        Profesor profesor = profesorService.buscarProfesor(id);
        if (profesor != null) {
            System.out.print("Nuevos nombres: ");
            profesor.setNombres(scanner.nextLine());
            System.out.print("Nuevos apellidos: ");
            profesor.setApellidos(scanner.nextLine());
            System.out.print("Nuevo código: ");
            profesor.setCodigoProfesor(scanner.nextLine());
            profesorService.actualizarProfesor(profesor);
        } else {
            System.out.println("Profesor no encontrado");
        }
    }

    private void eliminarProfesor() {
        System.out.println("\n--- Eliminar Profesor ---");
        System.out.print("ID del Profesor: ");
        int id = leerEntero();
        profesorService.eliminarProfesor(id);
    }

    private void menuAdministrativos() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n===== GESTIÓN DE ADMINISTRATIVOS =====");
            System.out.println("1. Registrar Administrativo");
            System.out.println("2. Buscar Administrativo");
            System.out.println("3. Listar Administrativos");
            System.out.println("4. Actualizar Administrativo");
            System.out.println("5. Eliminar Administrativo");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarAdministrativo();
                    break;
                case 2:
                    buscarAdministrativo();
                    break;
                case 3:
                    listarAdministrativos();
                    break;
                case 4:
                    actualizarAdministrativo();
                    break;
                case 5:
                    eliminarAdministrativo();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void registrarAdministrativo() {
        System.out.println("\n--- Registrar Administrativo ---");
        System.out.print("Nombres: ");
        String nombres = scanner.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.print("Fecha de nacimiento (yyyy-MM-dd): ");
        LocalDate fechaNacimiento = leerFecha();
        System.out.print("Número de documento: ");
        String numeroDocumento = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Nacionalidad: ");
        String nacionalidad = scanner.nextLine();
        System.out.print("Código administrativo: ");
        String codigoAdministrativo = scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();

        Administrativo administrativo = new Administrativo(nombres, apellidos, fechaNacimiento, numeroDocumento, direccion, nacionalidad, codigoAdministrativo, cargo);
        administrativoService.registrarAdministrativo(administrativo);

        // Buscar el administrativo recién registrado para obtener su ID
        Administrativo administrativoRegistrado = administrativoService.buscarPorCodigo(codigoAdministrativo);
        if (administrativoRegistrado != null) {
            registrarDatosContacto(administrativoRegistrado.getIdPersona());
        }
    }

    private void buscarAdministrativo() {
        System.out.println("\n--- Buscar Administrativo ---");
        System.out.print("ID del Administrativo: ");
        int id = leerEntero();
        Administrativo administrativo = administrativoService.buscarAdministrativo(id);
        if (administrativo != null) {
            System.out.println(administrativo);
        } else {
            System.out.println("Administrativo no encontrado");
        }
    }

    private void listarAdministrativos() {
        System.out.println("\n--- Listado de Administrativos ---");
        List<Administrativo> administrativos = administrativoService.listarAdministrativos();
        if (administrativos.isEmpty()) {
            System.out.println("No hay administrativos registrados");
        } else {
            administrativos.forEach(System.out::println);
        }
    }

    private void actualizarAdministrativo() {
        System.out.println("\n--- Actualizar Administrativo ---");
        System.out.print("ID del Administrativo: ");
        int id = leerEntero();
        Administrativo administrativo = administrativoService.buscarAdministrativo(id);
        if (administrativo != null) {
            System.out.print("Nuevos nombres: ");
            administrativo.setNombres(scanner.nextLine());
            System.out.print("Nuevos apellidos: ");
            administrativo.setApellidos(scanner.nextLine());
            System.out.print("Nuevo código: ");
            administrativo.setCodigoAdministrativo(scanner.nextLine());
            administrativoService.actualizarAdministrativo(administrativo);
        } else {
            System.out.println("Administrativo no encontrado");
        }
    }

    private void eliminarAdministrativo() {
        System.out.println("\n--- Eliminar Administrativo ---");
        System.out.print("ID del Administrativo: ");
        int id = leerEntero();
        administrativoService.eliminarAdministrativo(id);
    }

    private void menuTiposContacto() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n===== GESTIÓN DE TIPOS DE CONTACTO =====");
            System.out.println("1. Registrar Tipo de Contacto");
            System.out.println("2. Buscar Tipo de Contacto");
            System.out.println("3. Listar Tipos de Contacto");
            System.out.println("4. Actualizar Tipo de Contacto");
            System.out.println("5. Eliminar Tipo de Contacto");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarTipoContacto();
                    break;
                case 2:
                    buscarTipoContacto();
                    break;
                case 3:
                    listarTiposContacto();
                    break;
                case 4:
                    actualizarTipoContacto();
                    break;
                case 5:
                    eliminarTipoContacto();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void registrarTipoContacto() {
        System.out.println("\n--- Registrar Tipo de Contacto ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        TipoContacto tipoContacto = new TipoContacto(nombre);
        tipoContactoService.registrarTipoContacto(tipoContacto);
    }

    private void buscarTipoContacto() {
        System.out.println("\n--- Buscar Tipo de Contacto ---");
        System.out.print("ID del Tipo de Contacto: ");
        int id = leerEntero();
        TipoContacto tipoContacto = tipoContactoService.buscarTipoContacto(id);
        if (tipoContacto != null) {
            System.out.println(tipoContacto);
        } else {
            System.out.println("Tipo de contacto no encontrado");
        }
    }

    private void listarTiposContacto() {
        System.out.println("\n--- Listado de Tipos de Contacto ---");
        List<TipoContacto> tiposContacto = tipoContactoService.listarTiposContacto();
        if (tiposContacto.isEmpty()) {
            System.out.println("No hay tipos de contacto registrados");
        } else {
            tiposContacto.forEach(System.out::println);
        }
    }

    private void actualizarTipoContacto() {
        System.out.println("\n--- Actualizar Tipo de Contacto ---");
        System.out.print("ID del Tipo de Contacto: ");
        int id = leerEntero();
        TipoContacto tipoContacto = tipoContactoService.buscarTipoContacto(id);
        if (tipoContacto != null) {
            System.out.print("Nuevo nombre: ");
            tipoContacto.setNombre(scanner.nextLine());
            tipoContactoService.actualizarTipoContacto(tipoContacto);
        } else {
            System.out.println("Tipo de contacto no encontrado");
        }
    }

    private void eliminarTipoContacto() {
        System.out.println("\n--- Eliminar Tipo de Contacto ---");
        System.out.print("ID del Tipo de Contacto: ");
        int id = leerEntero();
        tipoContactoService.eliminarTipoContacto(id);
    }

    private void menuDatosContacto() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n===== GESTIÓN DE DATOS DE CONTACTO =====");
            System.out.println("1. Registrar Dato de Contacto");
            System.out.println("2. Buscar Dato de Contacto");
            System.out.println("3. Listar Datos de Contacto");
            System.out.println("4. Actualizar Dato de Contacto");
            System.out.println("5. Eliminar Dato de Contacto");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarDataContacto();
                    break;
                case 2:
                    buscarDataContacto();
                    break;
                case 3:
                    listarDatosContacto();
                    break;
                case 4:
                    actualizarDataContacto();
                    break;
                case 5:
                    eliminarDataContacto();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void registrarDataContacto() {
        System.out.println("\n--- Registrar Dato de Contacto ---");
        System.out.print("ID de la Persona: ");
        int idPersona = leerEntero();
        System.out.print("ID delTipo de Contacto: ");
        int idTipoContacto = leerEntero();
        System.out.print("Dato (teléfono, email, etc.): ");
        String dato = scanner.nextLine();
        DataContacto dataContacto = new DataContacto(idPersona, idTipoContacto, dato);
        dataContactoService.registrarDataContacto(dataContacto);
    }
    private void buscarDataContacto() {
        System.out.println("\n--- Buscar Dato de Contacto ---");
        System.out.print("ID del Dato de Contacto: ");
        int id = leerEntero();
        DataContacto dataContacto = dataContactoService.buscarDataContacto(id);
        if (dataContacto != null) {
            System.out.println(dataContacto);
        } else {
            System.out.println("Dato de contacto no encontrado");
        }
    }

    private void listarDatosContacto() {
        System.out.println("\n--- Listado de Datos de Contacto ---");
        List<DataContacto> datosContacto = dataContactoService.listarDataContactos();
        if (datosContacto.isEmpty()) {
            System.out.println("No hay datos de contacto registrados");
        } else {
            datosContacto.forEach(System.out::println);
        }
    }

    private void actualizarDataContacto() {
        System.out.println("\n--- Actualizar Dato de Contacto ---");
        System.out.print("ID del Dato de Contacto: ");
        int id = leerEntero();
        DataContacto dataContacto = dataContactoService.buscarDataContacto(id);
        if (dataContacto != null) {
            System.out.print("Nuevo dato: ");
            dataContacto.setDato(scanner.nextLine());
            dataContactoService.actualizarDataContacto(dataContacto);
        } else {
            System.out.println("Dato de contacto no encontrado");
        }
    }

    private void eliminarDataContacto() {
        System.out.println("\n--- Eliminar Dato de Contacto ---");
        System.out.print("ID del Dato de Contacto: ");
        int id = leerEntero();
        dataContactoService.eliminarDataContacto(id);
    }

    private void menuCategorias() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n===== GESTIÓN DE CATEGORÍAS =====");
            System.out.println("1. Registrar Categoría");
            System.out.println("2. Buscar Categoría");
            System.out.println("3. Listar Categorías");
            System.out.println("4. Actualizar Categoría");
            System.out.println("5. Eliminar Categoría");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarCategoria();
                    break;
                case 2:
                    buscarCategoria();
                    break;
                case 3:
                    listarCategorias();
                    break;
                case 4:
                    actualizarCategoria();
                    break;
                case 5:
                    eliminarCategoria();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void registrarCategoria() {
        System.out.println("\n--- Registrar Categoría ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        Categoria categoria = new Categoria(nombre, descripcion);
        categoriaService.registrarCategoria(categoria);
    }

    private void buscarCategoria() {
        System.out.println("\n--- Buscar Categoría ---");
        System.out.print("ID de la Categoría: ");
        int id = leerEntero();
        Categoria categoria = categoriaService.buscarCategoria(id);
        if (categoria != null) {
            System.out.println(categoria);
        } else {
            System.out.println("Categoría no encontrada");
        }
    }

    private void listarCategorias() {
        System.out.println("\n--- Listado de Categorías ---");
        List<Categoria> categorias = categoriaService.listarCategorias();
        if (categorias.isEmpty()) {
            System.out.println("No hay categorías registradas");
        } else {
            categorias.forEach(System.out::println);
        }
    }

    private void actualizarCategoria() {
        System.out.println("\n--- Actualizar Categoría ---");
        System.out.print("ID de la Categoría: ");
        int id = leerEntero();
        Categoria categoria = categoriaService.buscarCategoria(id);
        if (categoria != null) {
            System.out.print("Nuevo nombre: ");
            categoria.setNombre(scanner.nextLine());
            System.out.print("Nueva descripción: ");
            categoria.setDescripcion(scanner.nextLine());
            categoriaService.actualizarCategoria(categoria);
        } else {
            System.out.println("Categoría no encontrada");
        }
    }

    private void eliminarCategoria() {
        System.out.println("\n--- Eliminar Categoría ---");
        System.out.print("ID de la Categoría: ");
        int id = leerEntero();
        categoriaService.eliminarCategoria(id);
    }

    private void menuRecursos() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n===== GESTIÓN DE RECURSOS =====");
            System.out.println("1. Registrar Recurso");
            System.out.println("2. Buscar Recurso");
            System.out.println("3. Listar Recursos");
            System.out.println("4. Actualizar Recurso");
            System.out.println("5. Eliminar Recurso");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarRecurso();
                    break;
                case 2:
                    buscarRecurso();
                    break;
                case 3:
                    listarRecursos();
                    break;
                case 4:
                    actualizarRecurso();
                    break;
                case 5:
                    eliminarRecurso();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void registrarRecurso() {
        System.out.println("\n--- Registrar Recurso ---");
        System.out.print("ID de la Categoría: ");
        int idCategoria = leerEntero();
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Cantidad disponible: ");
        int cantidadDisponible = leerEntero();
        System.out.print("Cantidad total: ");
        int cantidadTotal = leerEntero();
        Recurso recurso = new Recurso(idCategoria, titulo, autor, isbn, cantidadDisponible, cantidadTotal);
        recursoService.registrarRecurso(recurso);
    }

    private void buscarRecurso() {
        System.out.println("\n--- Buscar Recurso ---");
        System.out.print("ID del Recurso: ");
        int id = leerEntero();
        Recurso recurso = recursoService.buscarRecurso(id);
        if (recurso != null) {
            System.out.println(recurso);
        } else {
            System.out.println("Recurso no encontrado");
        }
    }

    private void listarRecursos() {
        System.out.println("\n--- Listado de Recursos ---");
        List<Recurso> recursos = recursoService.listarRecursos();
        if (recursos.isEmpty()) {
            System.out.println("No hay recursos registrados");
        } else {
            recursos.forEach(System.out::println);
        }
    }

    private void actualizarRecurso() {
        System.out.println("\n--- Actualizar Recurso ---");
        System.out.print("ID del Recurso: ");
        int id = leerEntero();
        Recurso recurso = recursoService.buscarRecurso(id);
        if (recurso != null) {
            System.out.print("Nuevo título: ");
            recurso.setTitulo(scanner.nextLine());
            System.out.print("Nuevo autor: ");
            recurso.setAutor(scanner.nextLine());
            System.out.print("Nueva cantidad disponible: ");
            recurso.setCantidadDisponible(leerEntero());
            recursoService.actualizarRecurso(recurso);
        } else {
            System.out.println("Recurso no encontrado");
        }
    }

    private void eliminarRecurso() {
        System.out.println("\n--- Eliminar Recurso ---");
        System.out.print("ID del Recurso: ");
        int id = leerEntero();
        recursoService.eliminarRecurso(id);
    }

    private void menuPrestamos() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n===== GESTIÓN DE PRÉSTAMOS =====");
            System.out.println("1. Registrar Préstamo");
            System.out.println("2. Buscar Préstamo");
            System.out.println("3. Listar Préstamos");
            System.out.println("4. Actualizar Préstamo");
            System.out.println("5. Eliminar Préstamo");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarPrestamo();
                    break;
                case 2:
                    buscarPrestamo();
                    break;
                case 3:
                    listarPrestamos();
                    break;
                case 4:
                    actualizarPrestamo();
                    break;
                case 5:
                    eliminarPrestamo();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void registrarPrestamo() {
        System.out.println("\n--- Registrar Préstamo ---");
        System.out.print("ID del Alumno: ");
        int idAlumno = leerEntero();
        System.out.print("ID del Administrativo: ");
        int idAdministrativo = leerEntero();
        System.out.print("Fecha de préstamo (yyyy-MM-dd): ");
        LocalDate fechaPrestamo = leerFecha();
        System.out.print("Fecha de vencimiento (yyyy-MM-dd): ");
        LocalDate fechaVencimiento = leerFecha();
        Prestamo prestamo = new Prestamo(idAlumno, idAdministrativo, fechaPrestamo, fechaVencimiento);
        prestamoService.registrarPrestamo(prestamo);

        // El ID del préstamo ahora está disponible en el objeto prestamo
        if (prestamo.getIdPrestamo() > 0) {
            registrarDetallesPrestamo(prestamo.getIdPrestamo());
        }
    }

    private void buscarPrestamo() {
        System.out.println("\n--- Buscar Préstamo ---");
        System.out.print("ID del Préstamo: ");
        int id = leerEntero();
        Prestamo prestamo = prestamoService.buscarPrestamo(id);
        if (prestamo != null) {
            System.out.println("\n=== CABECERA DEL PRÉSTAMO ===");
            System.out.println(prestamo);

            // Buscar y mostrar los detalles del préstamo
            List<DetallePrestamo> detalles = detallePrestamoService.buscarPorPrestamo(id);
            System.out.println("\n=== DETALLES DEL PRÉSTAMO ===");
            if (detalles.isEmpty()) {
                System.out.println("No hay detalles registrados para este préstamo.");
            } else {
                System.out.println("Total de recursos prestados: " + detalles.size());
                for (DetallePrestamo detalle : detalles) {
                    Recurso recurso = recursoService.buscarRecurso(detalle.getIdRecurso());
                    if (recurso != null) {
                        System.out.println("  - ID Detalle: " + detalle.getIdDetallePrestamo() +
                                         " | Recurso: " + recurso.getTitulo() +
                                         " (ID: " + recurso.getIdRecurso() +
                                         ") | Autor: " + recurso.getAutor() +
                                         " | ISBN: " + recurso.getIsbn());
                    } else {
                        System.out.println("  - " + detalle);
                    }
                }
            }
        } else {
            System.out.println("Préstamo no encontrado");
        }
    }

    private void listarPrestamos() {
        System.out.println("\n--- Listado de Préstamos ---");
        List<Prestamo> prestamos = prestamoService.listarPrestamos();
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados");
        } else {
            System.out.println("Total de préstamos: " + prestamos.size());
            for (Prestamo prestamo : prestamos) {
                System.out.println("\n=== PRÉSTAMO ID: " + prestamo.getIdPrestamo() + " ===");
                System.out.println(prestamo);

                // Buscar y mostrar los detalles del préstamo
                List<DetallePrestamo> detalles = detallePrestamoService.buscarPorPrestamo(prestamo.getIdPrestamo());
                System.out.println("--- Detalles ---");
                if (detalles.isEmpty()) {
                    System.out.println("  Sin detalles registrados.");
                } else {
                    System.out.println("  Recursos prestados: " + detalles.size());
                    for (DetallePrestamo detalle : detalles) {
                        Recurso recurso = recursoService.buscarRecurso(detalle.getIdRecurso());
                        if (recurso != null) {
                            System.out.println("    • " + recurso.getTitulo() + " - " + recurso.getAutor());
                        } else {
                            System.out.println("    • Recurso ID: " + detalle.getIdRecurso());
                        }
                    }
                }
                System.out.println("----------------------------------------");
            }
        }
    }

    private void actualizarPrestamo() {
        System.out.println("\n--- Actualizar Préstamo ---");
        System.out.print("ID del Préstamo: ");
        int id = leerEntero();
        Prestamo prestamo = prestamoService.buscarPrestamo(id);
        if (prestamo != null) {
            System.out.print("Nueva fecha de vencimiento (yyyy-MM-dd): ");
            prestamo.setFechaVencimiento(leerFecha());
            prestamoService.actualizarPrestamo(prestamo);
        } else {
            System.out.println("Préstamo no encontrado");
        }
    }

    private void eliminarPrestamo() {
        System.out.println("\n--- Eliminar Préstamo ---");
        System.out.print("ID del Préstamo: ");
        int id = leerEntero();
        prestamoService.eliminarPrestamo(id);
    }

    private void menuDetallesPrestamo() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n===== GESTIÓN DE DETALLES DE PRÉSTAMO =====");
            System.out.println("1. Registrar Detalle de Préstamo");
            System.out.println("2. Buscar Detalle de Préstamo");
            System.out.println("3. Listar Detalles de Préstamo");
            System.out.println("4. Actualizar Detalle de Préstamo");
            System.out.println("5. Eliminar Detalle de Préstamo");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarDetallePrestamo();
                    break;
                case 2:
                    buscarDetallePrestamo();
                    break;
                case 3:
                    listarDetallesPrestamo();
                    break;
                case 4:
                    actualizarDetallePrestamo();
                    break;
                case 5:
                    eliminarDetallePrestamo();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void registrarDetallePrestamo() {
        System.out.println("\n--- Registrar Detalle de Préstamo ---");
        System.out.print("ID del Préstamo: ");
        int idPrestamo = leerEntero();
        System.out.print("ID del Recurso: ");
        int idRecurso = leerEntero();
        DetallePrestamo detallePrestamo = new DetallePrestamo(idPrestamo, idRecurso);
        detallePrestamoService.registrarDetallePrestamo(detallePrestamo);
    }

    private void buscarDetallePrestamo() {
        System.out.println("\n--- Buscar Detalle de Préstamo ---");
        System.out.print("ID del Detalle de Préstamo: ");
        int id = leerEntero();
        DetallePrestamo detallePrestamo = detallePrestamoService.buscarDetallePrestamo(id);
        if (detallePrestamo != null) {
            System.out.println(detallePrestamo);
        } else {
            System.out.println("Detalle de préstamo no encontrado");
        }
    }

    private void listarDetallesPrestamo() {
        System.out.println("\n--- Listado de Detalles de Préstamo ---");
        List<DetallePrestamo> detallesPrestamo = detallePrestamoService.listarDetallesPrestamo();
        if (detallesPrestamo.isEmpty()) {
            System.out.println("No hay detalles de préstamo registrados");
        } else {
            detallesPrestamo.forEach(System.out::println);
        }
    }

    private void actualizarDetallePrestamo() {
        System.out.println("\n--- Actualizar Detalle de Préstamo ---");
        System.out.print("ID del Detalle de Préstamo: ");
        int id = leerEntero();
        DetallePrestamo detallePrestamo = detallePrestamoService.buscarDetallePrestamo(id);
        if (detallePrestamo != null) {
            System.out.print("Nuevo ID del Recurso: ");
            detallePrestamo.setIdRecurso(leerEntero());
            detallePrestamoService.actualizarDetallePrestamo(detallePrestamo);
        } else {
            System.out.println("Detalle de préstamo no encontrado");
        }
    }

    private void eliminarDetallePrestamo() {
        System.out.println("\n--- Eliminar Detalle de Préstamo ---");
        System.out.print("ID del Detalle de Préstamo: ");
        int id = leerEntero();
        detallePrestamoService.eliminarDetallePrestamo(id);
    }

    private void menuMultas() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n===== GESTIÓN DE MULTAS =====");
            System.out.println("1. Registrar Multa");
            System.out.println("2. Buscar Multa");
            System.out.println("3. Listar Multas");
            System.out.println("4. Listar Multas No Pagadas");
            System.out.println("5. Actualizar Multa");
            System.out.println("6. Eliminar Multa");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarMulta();
                    break;
                case 2:
                    buscarMulta();
                    break;
                case 3:
                    listarMultas();
                    break;
                case 4:
                    listarMultasNoPagadas();
                    break;
                case 5:
                    actualizarMulta();
                    break;
                case 6:
                    eliminarMulta();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void registrarMulta() {
        System.out.println("\n--- Registrar Multa ---");
        System.out.print("ID del Préstamo: ");
        int idPrestamo = leerEntero();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        Multa multa = new Multa(idPrestamo, LocalDate.now(), descripcion);
        multaService.registrarMulta(multa);
    }

    private void buscarMulta() {
        System.out.println("\n--- Buscar Multa ---");
        System.out.print("ID de la Multa: ");
        int id = leerEntero();
        Multa multa = multaService.buscarMulta(id);
        if (multa != null) {
            System.out.println(multa);
        } else {
            System.out.println("Multa no encontrada");
        }
    }

    private void listarMultas() {
        System.out.println("\n--- Listado de Multas ---");
        List<Multa> multas = multaService.listarMultas();
        if (multas.isEmpty()) {
            System.out.println("No hay multas registradas");
        } else {
            multas.forEach(System.out::println);
        }
    }

    private void listarMultasNoPagadas() {
        System.out.println("\n--- Listado de Multas No Pagadas ---");
        List<Multa> multas = multaService.listarNoPageadas();
        if (multas.isEmpty()) {
            System.out.println("No hay multas no pagadas");
        } else {
            multas.forEach(System.out::println);
        }
    }

    private void actualizarMulta() {
        System.out.println("\n--- Actualizar Multa ---");
        System.out.print("ID de la Multa: ");
        int id = leerEntero();
        Multa multa = multaService.buscarMulta(id);
        if (multa != null) {
            System.out.print("¿Está pagada? (true/false): ");
            multa.setPagada(scanner.nextLine().equalsIgnoreCase("true"));
            if (multa.isPagada()) {
                multa.setFechaPago(LocalDate.now());
            }
            multaService.actualizarMulta(multa);
        } else {
            System.out.println("Multa no encontrada");
        }
    }

    private void eliminarMulta() {
        System.out.println("\n--- Eliminar Multa ---");
        System.out.print("ID de la Multa: ");
        int id = leerEntero();
        multaService.eliminarMulta(id);
    }

    private void menuDevoluciones() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n===== GESTIÓN DE DEVOLUCIONES =====");
            System.out.println("1. Registrar Devolución");
            System.out.println("2. Buscar Devolución");
            System.out.println("3. Listar Devoluciones");
            System.out.println("4. Actualizar Devolución");
            System.out.println("5. Eliminar Devolución");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarDevoluccion();
                    break;
                case 2:
                    buscarDevoluccion();
                    break;
                case 3:
                    listarDevoluciones();
                    break;
                case 4:
                    actualizarDevoluccion();
                    break;
                case 5:
                    eliminarDevoluccion();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void registrarDevoluccion() {
        System.out.println("\n--- Registrar Devolución ---");
        System.out.print("ID del Préstamo: ");
        int idPrestamo = leerEntero();
        System.out.print("ID del Administrativo: ");
        int idAdministrativo = leerEntero();
        System.out.print("Observación: ");
        String observacion = scanner.nextLine();
        Devoluccion devoluccion = new Devoluccion(idPrestamo, idAdministrativo, LocalDate.now(), observacion);
        devolucionService.registrarDevoluccion(devoluccion);
    }

    private void buscarDevoluccion() {
        System.out.println("\n--- Buscar Devolución ---");
        System.out.print("ID de la Devolución: ");
        int id = leerEntero();
        Devoluccion devoluccion = devolucionService.buscarDevoluccion(id);
        if (devoluccion != null) {
            System.out.println(devoluccion);
        } else {
            System.out.println("Devolución no encontrada");
        }
    }

    private void listarDevoluciones() {
        System.out.println("\n--- Listado de Devoluciones ---");
        List<Devoluccion> devoluciones = devolucionService.listarDevoluciones();
        if (devoluciones.isEmpty()) {
            System.out.println("No hay devoluciones registradas");
        } else {
            devoluciones.forEach(System.out::println);
        }
    }

    private void actualizarDevoluccion() {
        System.out.println("\n--- Actualizar Devolución ---");
        System.out.print("ID de la Devolución: ");
        int id = leerEntero();
        Devoluccion devoluccion = devolucionService.buscarDevoluccion(id);
        if (devoluccion != null) {
            System.out.print("Nueva observación: ");
            devoluccion.setObservacion(scanner.nextLine());
            devolucionService.actualizarDevoluccion(devoluccion);
        } else {
            System.out.println("Devolución no encontrada");
        }
    }

    private void eliminarDevoluccion() {
        System.out.println("\n--- Eliminar Devolución ---");
        System.out.print("ID de la Devolución: ");
        int id = leerEntero();
        devolucionService.eliminarDevoluccion(id);
    }

    private int leerEntero() {
        try {
            int valor = Integer.parseInt(scanner.nextLine());
            return valor;
        } catch (NumberFormatException e) {
            System.out.println("Por favor ingrese un número válido");
            return leerEntero();
        }
    }

    private LocalDate leerFecha() {
        try {
            String fecha = scanner.nextLine();
            return LocalDate.parse(fecha, dateFormatter);
        } catch (Exception e) {
            System.out.println("Formato de fecha inválido. Use yyyy-MM-dd");
            return leerFecha();
        }
    }

    private void registrarDatosContacto(int idPersona) {
        System.out.println("\n--- Registrar Datos de Contacto ---");
        System.out.print("¿Desea agregar datos de contacto? (s/n): ");
        String respuesta = scanner.nextLine();

        while (respuesta.equalsIgnoreCase("s")) {
            // Mostrar tipos de contacto disponibles
            System.out.println("\nTipos de contacto disponibles:");
            List<TipoContacto> tiposContacto = tipoContactoService.listarTiposContacto();
            if (tiposContacto.isEmpty()) {
                System.out.println("No hay tipos de contacto registrados. Por favor, registre tipos de contacto primero.");
                return;
            }
            tiposContacto.forEach(tc -> System.out.println(tc.getIdTipoContacto() + ". " + tc.getNombre()));

            System.out.print("ID del Tipo de Contacto: ");
            int idTipoContacto = leerEntero();
            System.out.print("Dato (teléfono, email, etc.): ");
            String dato = scanner.nextLine();

            DataContacto dataContacto = new DataContacto(idPersona, idTipoContacto, dato);
            dataContactoService.registrarDataContacto(dataContacto);

            System.out.print("¿Desea agregar otro dato de contacto? (s/n): ");
            respuesta = scanner.nextLine();
        }
    }

    private void registrarDetallesPrestamo(int idPrestamo) {
        System.out.println("\n--- Registrar Detalles del Préstamo ---");
        System.out.println("IMPORTANTE: Debe registrar al menos un recurso para este préstamo.");
        String respuesta;
        int contadorDetalles = 0;

        do {
            // Mostrar recursos disponibles
            System.out.println("\nRecursos disponibles:");
            List<Recurso> recursos = recursoService.listarRecursos();
            if (recursos.isEmpty()) {
                System.out.println("No hay recursos disponibles.");
                if (contadorDetalles == 0) {
                    System.out.println("ERROR: No se puede completar el préstamo sin recursos disponibles.");
                }
                return;
            }
            recursos.forEach(r -> System.out.println(r.getIdRecurso() + ". " + r.getTitulo() + " - Disponible: " + r.getCantidadDisponible()));

            System.out.print("ID del Recurso: ");
            int idRecurso = leerEntero();

            DetallePrestamo detallePrestamo = new DetallePrestamo(idPrestamo, idRecurso);
            detallePrestamoService.registrarDetallePrestamo(detallePrestamo);
            contadorDetalles++;

            System.out.print("¿Desea agregar otro recurso al préstamo? (s/n): ");
            respuesta = scanner.nextLine();
        } while (respuesta.equalsIgnoreCase("s"));

        System.out.println("Total de recursos agregados al préstamo: " + contadorDetalles);
    }
}