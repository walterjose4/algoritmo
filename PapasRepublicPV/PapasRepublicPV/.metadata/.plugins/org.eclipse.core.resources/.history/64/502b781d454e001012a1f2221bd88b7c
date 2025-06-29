package arreglos;

import clases.Cliente;
import java.util.ArrayList;

public class ArregloClientes {
    private ArrayList<Cliente> clientes;

    public ArregloClientes() {
        clientes = new ArrayList<>();
    }

    public int tamanio() {
        return clientes.size();
    }

    public Cliente obtener(int i) {
        return clientes.get(i);
    }

    public int codigoCorrelativo() {
        if (clientes.isEmpty())
            return 1;
        else
            return clientes.get(clientes.size() - 1).getCodigo() + 1;
    }

    public void adicionar(Cliente c) {
        clientes.add(c);
    }

    public void eliminar(Cliente c) {
        clientes.remove(c);
    }

    public Cliente buscar(int codigo) {
        for (Cliente c : clientes) {
            if (c.getCodigo() == codigo)
                return c;
        }
        return null;
    }

    //MÉTODO PARA BUSCAR POR NÚMERO DE DOCUMENTO
    public Cliente buscar(long numeroDeDocumento) {
        for (Cliente c : clientes) {
            if (c.getNumeroDeDocumento() == numeroDeDocumento)
                return c;
        }
        return null;
    }
}