package main;

public interface TareaVisitor {
    void visit(Bug b);
    void visit(Feature f);
    void visit(Documentacion d);
}

/* Visitante con las acciones que se piden*/
class AccionVisitor implements TareaVisitor {

    @Override
    public void visit(Bug b) {
        System.out.println("BUG " + b.getId() + ": Afecta la criticidad del proyecto.");
        // Ejemplo de efecto: si luego agregas campos en Proyecto, aquí podrías modificarlos
        // Proyecto pr = b.getProyecto();
        // if (pr != null) pr.setCriticidad(pr.getCriticidad()+1);
    }

    @Override
    public void visit(Feature f) {
        System.out.println("FEATURE " + f.getId() + ": Impacta en la estimación de tiempo.");
        // Ejemplo: acumular horas estimadas
    }

    @Override
    public void visit(Documentacion d) {
        System.out.println("DOC " + d.getId() + ": Mejora la calidad del proyecto.");
        // Ejemplo: subir índice de calidad
    }
}