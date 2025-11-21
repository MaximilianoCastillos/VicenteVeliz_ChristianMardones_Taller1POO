package main;

import java.util.ArrayList;
import java.util.List;

/**
 * La interfaz PrioridadStrategy define el contrato para el metodo ordenar
 * de tareas y con esto Cualquier clase que implemente esta interfaz debe proporcionar una
 * implementación para el método `ordenar`, que organiza una lista de tareas.
 */

public interface PrioridadStrategy {

    /**
     * Ordena la lista de tareas según una estrategia específica.
     *
     * @param tareas La lista de tareas a ordenar.
     * @return La lista de tareas ordenada según la estrategia.
     */
	
    List<Tarea> ordenar(List<Tarea> tareas);
}

/**
 * Estrategia para ordenar las tareas por fecha. Las tareas más antiguas se colocan primero
 * en la lista.
 * La comparación de las fechas se hace utilizando el formato de fecha "yyyy-MM-dd",
 * que se ordena correctamente de forma alfabética.
 */
class PrioridadPorFecha implements PrioridadStrategy {
	/**
     * Ordena la lista de tareas por fecha (más antigua primero).
     *
     * @param tareas La lista de tareas a ordenar.
     * @return La lista de tareas ordenada por fecha.
     */
	
	
    @Override
    public List<Tarea> ordenar(List<Tarea> tareas) {
        List<Tarea> copia = new ArrayList<>(tareas);
        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = i + 1; j < copia.size(); j++) {
                String f1 = copia.get(i).getFecha();
                String f2 = copia.get(j).getFecha();
                if (f1.compareTo(f2) > 0) { // si f1 es posterior a f2
                    Tarea temp = copia.get(i);
                    copia.set(i, copia.get(j));
                    copia.set(j, temp);
                }
            }
        }
        return copia;
    }
}
/**
 * Estrategia para ordenar las tareas por impacto. El orden de prioridad es:
 * 1. Bug
 * 2. Feature
 * 3. Documentación
 */

class PrioridadPorImpacto implements PrioridadStrategy {
	 /**
     * Asigna un valor a cada tipo de tarea basado en su impacto:
     * - "bug" tiene la mayor prioridad (valor 1).
     * - "feature" tiene la segunda prioridad (valor 2).
     * - "documentación" tiene la menor prioridad (valor 3).
     *
     * @param tipo El tipo de tarea.
     * @return El valor asociado al tipo de tarea.
     */
    private int valorTipo(String tipo) {
        if (tipo == null) return 999;
        tipo = tipo.toLowerCase();
        if (tipo.equals("bug")) return 1;
        if (tipo.equals("feature")) return 2;
        if (tipo.equals("documentacion") || tipo.equals("documentación")) return 3;
        return 999;
    }
    /**
     * Ordena la lista de tareas por tipo de tarea (impacto).
     * Las tareas de tipo "bug" tienen mayor prioridad que las de tipo "feature",
     * y estas a su vez tienen mayor prioridad que las de tipo "documentación".
     *
     * @param tareas La lista de tareas a ordenar.
     * @return La lista de tareas ordenada por impacto.
     */

    @Override
    public List<Tarea> ordenar(List<Tarea> tareas) {
        List<Tarea> copia = new ArrayList<>(tareas);
        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = i + 1; j < copia.size(); j++) {
                if (valorTipo(copia.get(i).getTipo()) > valorTipo(copia.get(j).getTipo())) {
                    Tarea temp = copia.get(i);
                    copia.set(i, copia.get(j));
                    copia.set(j, temp);
                }
            }
        }
        return copia;
    }
}
/**
 * Estrategia para ordenar las tareas por complejidad. El orden de prioridad es:
 * 1. Alta
 * 2. Media
 * 3. Baja
 */

class PrioridadPorComplejidad implements PrioridadStrategy {
	/**
     * Asigna un valor a la complejidad de la tarea:
     * - "alta" tiene la mayor prioridad (valor 1).
     * - "media" tiene la segunda prioridad (valor 2).
     * - "baja" tiene la menor prioridad (valor 3).
     *
     * @param comp El nivel de complejidad de la tarea.
     * @return El valor asociado a la complejidad de la tarea.
     */
    private int valorComplejidad(String comp) {
        if (comp == null) return 999;
        comp = comp.toLowerCase();
        if (comp.equals("alta")) return 1;
        if (comp.equals("media")) return 2;
        if (comp.equals("baja")) return 3;
        return 999;
    }
    /**
     * Ordena la lista de tareas por complejidad.
     * Las tareas de complejidad "alta" tienen mayor prioridad que las de "media",
     * y las de "media" tienen mayor prioridad que las de "baja".
     *
     * @param tareas La lista de tareas a ordenar.
     * @return La lista de tareas ordenada por complejidad.
     */

    @Override
    public List<Tarea> ordenar(List<Tarea> tareas) {
        List<Tarea> copia = new ArrayList<>(tareas);
        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = i + 1; j < copia.size(); j++) {
                if (valorComplejidad(copia.get(i).getComplejidad()) > 
                    valorComplejidad(copia.get(j).getComplejidad())) {
                    Tarea temp = copia.get(i);
                    copia.set(i, copia.get(j));
                    copia.set(j, temp);
                }
            }
        }
        return copia;
    }
}