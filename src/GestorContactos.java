import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorContactos {
    private static final String ARCHIVO_CONTACTOS = "contactos.txt";
    private List<Contacto> contactos;

    public GestorContactos() {
        contactos = new ArrayList<>();
        cargarContactosDesdeArchivo();
    }

    // Añadir contacto
    public void agregarContacto(Contacto contacto) {
        contactos.add(contacto);
        guardarContactosEnArchivo();
    }

    // Listar contactos
    public void listarContactos() {
        if (contactos.isEmpty()) {
            System.out.println("No hay contactos guardados.");
        } else {
            for (Contacto contacto : contactos) {
                System.out.println(contacto);
            }
        }
    }

    // Buscar contacto
    public Contacto buscarContacto(String nombre) {
        for (Contacto contacto : contactos) {
            if (contacto.getNombre().equalsIgnoreCase(nombre)) {
                return contacto;
            }
        }
        return null;
    }

    // Eliminar contacto
    public void eliminarContacto(String nombre) {
        Contacto contacto = buscarContacto(nombre);
        if (contacto != null) {
            contactos.remove(contacto);
            guardarContactosEnArchivo();
            System.out.println("Contacto eliminado.");
        } else {
            System.out.println("Contacto no encontrado.");
        }
    }

    // Guardar contactos en archivo
    private void guardarContactosEnArchivo() {
        try (FileWriter writer = new FileWriter(ARCHIVO_CONTACTOS)) {
            for (Contacto contacto : contactos) {
                writer.write(contacto.getNombre() + "," + contacto.getTelefono() + "," + contacto.getEmail() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los contactos: " + e.getMessage());
        }
    }

    // Cargar contactos desde archivo
    private void cargarContactosDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_CONTACTOS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    Contacto contacto = new Contacto(datos[0], datos[1], datos[2]);
                    contactos.add(contacto);
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo de contactos.");
        }
    }
}
