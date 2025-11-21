package main;
/**
 * La interfaz `TareaVisitor` define el contrato para un visitante que puede realizar
 * acciones específicas sobre diferentes tipos de tareas: Bug, Feature y Documentación.
 * El patrón de diseño Visitor permite agregar nuevas operaciones sin modificar las clases de tarea.
 */
public interface TareaVisitor {

    /**
     * Método para visitar un objeto de tipo `Bug`.
     * Este método define la acción que se debe realizar cuando un visitante visita una tarea de tipo `Bug`.
     *
     * @param b El objeto de tipo `Bug` que está siendo visitado.
     */
	
    void visit(Bug b);
    
    /**
     * Método para visitar un objeto de tipo `Feature`.
     * Este método define la acción que se debe realizar cuando un visitante visita una tarea de tipo `Feature`.
     *
     * @param f El objeto de tipo `Feature` que está siendo visitado.
     */
    
    
    void visit(Feature f);
    
    
    /**
     * Método para visitar un objeto de tipo `Documentacion`.
     * Este método define la acción que se debe realizar cuando un visitante visita una tarea de tipo `Documentación`.
     *
     * @param d El objeto de tipo `Documentacion` que está siendo visitado.
     */
    void visit(Documentacion d);
}

/**
 * La clase `AccionVisitor` define la forma en que se haran las acciones específicas que se deben realizar
 * cuando un visitante visita un objeto de tipo `Bug`, `Feature` o `Documentacion`.
 */

class AccionVisitor implements TareaVisitor {

    @Override
    public void visit(Bug b) {
        System.out.println("BUG " + b.getId() + ": Afecta la criticidad del proyecto.");
       
         Proyecto pr = b.getProyecto();
      
    }

    @Override
    public void visit(Feature f) {
        System.out.println("FEATURE " + f.getId() + ": Impacta en la estimación de tiempo.");
        
    }

    @Override
    public void visit(Documentacion d) {
        System.out.println("DOC " + d.getId() + ": Mejora la calidad del proyecto.");
        
    }
}