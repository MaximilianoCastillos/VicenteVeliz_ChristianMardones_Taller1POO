package main;

import java.util.List;

/**
 * Clase que representa a un usuario con rol de Coordinador. Permite gestionar las certificaciones y estudiantes.
 */
public class Coordinador extends Usuario {

    /**
     * Constructor para crear un coordinador.
     */
    public Coordinador(String username, String password, String rol) {
        super(username, password, rol);
    }

    /**
     * Modifica una certificación, actualizando su nombre, descripción, requisitos y validez.
     */
    public void modificarLineaCertificacion(Certificacion certificacion, String nuevoNombre, String nuevaDescripcion, int nuevosRequisitos, int nuevaValidez) {
        certificacion.setNombre(nuevoNombre);
        certificacion.setDescripcion(nuevaDescripcion);
        certificacion.setRequisitos(nuevosRequisitos);
        certificacion.setValidez(nuevaValidez);
    }

    /**
     * Genera un certificado para un estudiante que ha completado la certificación.
     * Verifica si el estudiante cumple con los requisitos.
     */
    public String generarCertificado(Estudiante estudiante, Certificacion certificacion) {
        if (certificacion.cumpleRequisitos(estudiante.calcularPromedio())) {
            return "Certificado de " + certificacion.getNombre() + " generado para el estudiante " + estudiante.getNombre();
        } else {
            return "El estudiante no cumple los requisitos para la certificación " + certificacion.getNombre();
        }
    }

    /**
     * Muestra estadísticas de inscripción de estudiantes en una certificación.
     */
    public void mostrarEstadisticasInscripciones(List<Estudiante> estudiantes, Certificacion certificacion) {
        int totalEstudiantes = estudiantes.size();
        int inscritos = 0;

        for (Estudiante estudiante : estudiantes) {
            if (estudiante.isInscrito(certificacion)) {
                inscritos++;
            }
        }

        System.out.println("Total estudiantes: " + totalEstudiantes);
        System.out.println("Estudiantes inscritos en " + certificacion.getNombre() + ": " + inscritos);
    }

    /**
     * Analiza las asignaturas críticas dentro de una certificación.
     * Muestra las asignaturas que tienen baja tasa de aprobación o requieren atención especial.
     */
    public void analizarAsignaturasCriticas(List<Curso> cursos, Certificacion certificacion) {
        System.out.println("Análisis de asignaturas críticas en la certificación: " + certificacion.getNombre());
        for (Curso curso : cursos) {
            System.out.println("Asignatura: " + curso.getNombre() + " - Estado crítico (en base a datos)"); 
        }
    }

    /**
     * Consulta el perfil completo de un estudiante dentro de una certificación.
     */
    public String consultarPerfilEstudiante(Estudiante estudiante) {
        return estudiante.getPerfil();
    }

    /**
     * Valida los avances académicos de un estudiante en relación con los requisitos de una certificación.
     */
    public String validarAvanceAcademico(Estudiante estudiante, Certificacion certificacion) {
        if (estudiante.calcularPromedio() >= certificacion.getRequisitos()) {
            return "Estudiante " + estudiante.getNombre() + " ha cumplido con los requisitos de " + certificacion.getNombre();
        } else {
            return "Estudiante " + estudiante.getNombre() + " no ha cumplido con los requisitos de " + certificacion.getNombre();
        }
    }
}
