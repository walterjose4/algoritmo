package arreglos;

import clases.Producto;
import java.util.ArrayList;

public class ArregloProductos {
    private ArrayList<Producto> productos;

    public ArregloProductos() {
        productos = new ArrayList<>();
    }

    public void adicionar(Producto p) {
        productos.add(p);
    }

    public int tamanio() {
        return productos.size();
    }

    public Producto obtener(int i) {
        return productos.get(i);
    }

    public Producto buscarPorItem(String item) {
        for (Producto p : productos) {
            if (p.getItem().equals(item)) {
                return p;
            }
        }
        return null;
    }

    public void eliminar(Producto p) {
        productos.remove(p);
    }
}
