package main;

import java.util.ArrayList;
import java.util.List;

public interface PrioridadStrategy {
    List<Tarea> ordenar(List<Tarea> tareas);
}

/* 1) Ordenar por FECHA (más antigua primero)
   La comparación se hace como texto "yyyy-MM-dd", 
   que se ordena bien alfabéticamente en ese formato. */
class PrioridadPorFecha implements PrioridadStrategy {
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

/* 2) Ordenar por impacto (tipo: Bug > Feature > Documentación) */
class PrioridadPorImpacto implements PrioridadStrategy {
    private int valorTipo(String tipo) {
        if (tipo == null) return 999;
        tipo = tipo.toLowerCase();
        if (tipo.equals("bug")) return 1;
        if (tipo.equals("feature")) return 2;
        if (tipo.equals("documentacion") || tipo.equals("documentación")) return 3;
        return 999;
    }

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

/* 3) Ordenar por COMPLEJIDAD (Alta > Media > Baja) */
class PrioridadPorComplejidad implements PrioridadStrategy {
    private int valorComplejidad(String comp) {
        if (comp == null) return 999;
        comp = comp.toLowerCase();
        if (comp.equals("alta")) return 1;
        if (comp.equals("media")) return 2;
        if (comp.equals("baja")) return 3;
        return 999;
    }

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