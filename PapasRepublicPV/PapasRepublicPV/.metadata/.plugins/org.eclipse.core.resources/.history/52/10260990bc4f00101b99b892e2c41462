package arreglos;

import clases.Usuario;
import java.util.ArrayList;

public class ArregloUsuarios {
    private ArrayList<Usuario> listaUsuarios;

    public ArregloUsuarios() {
        listaUsuarios = new ArrayList<>();
        
    }

    public void adicionar(Usuario usuario) {
        listaUsuarios.add(usuario);
    }

    public int tamanio() {
        return listaUsuarios.size();
    }

    public Usuario obtener(int i) {
        return listaUsuarios.get(i);
    }

    public Usuario buscar(int codigo) {
        for (Usuario u : listaUsuarios) {
            if (u.getCodigo() == codigo) {
                return u;
            }
        }
        return null;
    }
    
    public Usuario buscarPorNombreUsuario(String nombreUsuario) {
        for (Usuario u : listaUsuarios) {
            if (u.getNombreDeUsuario().equalsIgnoreCase(nombreUsuario)) {
                return u;
            }
        }
        return null;
    }


    public void eliminar(Usuario usuario) {
        listaUsuarios.remove(usuario);
    }

    public int codigoCorrelativo() {
        if (tamanio() == 0) {
            return 1001; // Primer código
        } else {
            return obtener(tamanio() - 1).getCodigo() + 1;
        }
    }
    

}