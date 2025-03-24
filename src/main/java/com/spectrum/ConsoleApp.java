package com.spectrum;

import com.spectrum.ecommerce.model.Producto;
import com.spectrum.ecommerce.model.Usuario;
import com.spectrum.ecommerce.service.ProductoService;
import com.spectrum.ecommerce.service.UsuarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

@ApplicationScoped
public class ConsoleApp {
        private static final Scanner scanner = new Scanner(System.in);
        @Inject
        private  UsuarioService usuarioService;
        @Inject
        private  ProductoService productoService;

        public static void main(String[] args) {
           Weld weld = new Weld();
            try(WeldContainer container = weld.initialize()) {
                ConsoleApp app = container.select(ConsoleApp.class).get();
                app.run();
            } catch (Exception e) {
                    e.printStackTrace();
            }
       }
      public void run() {

            while (true) {
                showMainMenu();
                int option = getOptionFromUser();
                switch (option) {
                    case 1:
                        showUserMenu();
                        break;
                    case 2:
                        showProductMenu();
                        break;
                    case 3:
                        showPedidoMenu();
                        break;
                    case 0:
                        // Salir del programa
                        System.exit(0);
                }
            }
        }

        private void showMainMenu() {
            System.out.println("------- Menú Principal -------");
            System.out.println("1. Gestionar Usuarios");
            System.out.println("2. Gestionar Productos");
            System.out.println("3. Gestionar Pedidos");
            System.out.println("0. Salir");
            System.out.println("-----------------------------");
        }

    private int getOptionFromUser() {
        System.out.print("Ingrese el número de opción: ");
        try {
            int option = scanner.nextInt();
            scanner.nextLine();
            return option;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Limpiar el buffer del escáner
            System.out.println("¡Error! Debe ingresar un número válido.");
            return getOptionFromUser(); // Llamada recursiva para solicitar nuevamente la opción
        }
    }


    private void showUserMenu() {
            while (true) {
                System.out.println("------- Menú Usuarios -------");
                System.out.println("1. Crear Usuario");
                System.out.println("2. Actualizar Usuario");
                System.out.println("3. Eliminar Usuario");
                System.out.println("4. Listar Usuarios");
                System.out.println("5. Buscar Usuario");
                System.out.println("0. Volver al Menú Principal");
                System.out.println("-----------------------------");

                int option = getOptionFromUser();
                switch (option) {
                    case 1:
                        createUser();
                        break;
                    case 2:
                        updateUser();
                        break;
                    case 3:
                        deleteUser();
                        break;
                    case 4:
                        getAllUser();
                        break;
                    case 5:
                        showUserMenuByCriteria();
                        break;
                    case 0:
                        return; // Volver al Menú Principal
                }
            }
        }


        private void createUser() {
            System.out.println("------- Crear Usuario -------");
            try {
                System.out.println("Ingrese el nombre: ");
                String nombre = scanner.nextLine();
                System.out.println("Ingrese el apellido1: ");
                String apellido1 = scanner.nextLine();
                System.out.println("Ingrese el apellido2: ");
                String apellido2 = scanner.nextLine();
                System.out.println("Ingrese email: ");
                String email = scanner.nextLine();

                Usuario usuario = new Usuario(apellido1, apellido2, email,nombre);
                System.out.println(usuario);
                boolean esExitoso = usuarioService.insertUsuario(usuario);
                if (esExitoso) {
                    System.out.println("Usuario creado exitosamente");
                } else {
                    System.out.println("Error al crear el usuario");
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error durante la creación del usuario: " + e.getMessage());
                e.printStackTrace();
            }
        }

        private  void updateUser() {
            System.out.println("------- Actualizar Usuario -------");

            try {
                System.out.println("Ingrese el ID del usuario a actualizar: ");
                String idString = scanner.nextLine();
                UUID id = UUID.fromString(idString);
                Usuario usuarioActualizar = usuarioService.getUsuarioById(id);
                System.out.println("Ingrese el nuevo nombre: ");
                String nuevoNombre = scanner.nextLine();
                System.out.println("Ingrese el nuevo apellido1: ");
                String nuevoApellido1 = scanner.next();
                System.out.println("Ingrese el nueva apellido2: ");
                String nuevoApellido2 = scanner.next();
                System.out.println("Ingrese el nuevo email: ");
                String nuevoEmail = scanner.next();

                Usuario usuarioActualizado = new Usuario(nuevoNombre, nuevoApellido1, nuevoApellido2,nuevoEmail);
                usuarioActualizado.setId(id);
                System.out.println("Anterior "+usuarioActualizar);
                System.out.println("Actual "+usuarioActualizado);
                boolean esExitoso = usuarioService.updateUsuario(usuarioActualizado);
                if (esExitoso) {
                    System.out.println("Usuario actualizado exitosamente");
                } else {
                    System.out.println("Error al actualizar el usuario");
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error durante la actualización del usuario: " + e.getMessage());
                e.printStackTrace();
            }
        }

        private  void deleteUser() {
            System.out.println("------- Eliminar Usuario -------");
            try {
                System.out.println("Ingrese el ID del usuario a eliminar: ");
                String idString = scanner.nextLine();
                UUID id = UUID.fromString(idString);

                System.out.println(usuarioService.getUsuarioById(id));
                boolean esExitoso = usuarioService.deleteUsuario(id);
                if (esExitoso) {
                    System.out.println("Usuario eliminado exitosamente");
                } else {
                    System.out.println("Error al eliminar el usuario");
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error durante la eliminación del usuario: " + e.getMessage());
                e.printStackTrace();
            }
        }


       private  void getAllUser() {
           System.out.println("------- Lista de Usuarios -------");
           try {

               List<Usuario> usuarios = usuarioService.getAllUsuario();
               if (usuarios != null) {
                   mostrarUsuarios(usuarios);

               } else {
                   System.out.println("no se encontro usuarios");
               }
           } catch (Exception e) {
               System.out.println("Ocurrió un error durante consulta de usuarios: " + e.getMessage());
               e.printStackTrace();
           }
       }

       private void showUserMenuByCriteria() {
           while (true) {
               System.out.println("------- Menú Buscar por Criterio -------");
               System.out.println("1. Buscar por id");
               System.out.println("2. Buscar por nombre");
               System.out.println("3. Buscar por apellido1");
               System.out.println("4. Buscar por apellido2");
               System.out.println("5. Buscar por correo");
               System.out.println("0. Salir");
               System.out.println("-----------------------------");
               int option = getOptionFromUser();
               switch (option) {
                   case 1:
                       System.out.println("1. Buscar por id");
                       System.out.println("----------------");
                       System.out.println("Introduzca id a buscar: ");

                       if (scanner.hasNextLine()) {
                           String idString = scanner.nextLine();

                           try {
                               UUID id = UUID.fromString(idString);
                               List<Usuario> usuarioId = usuarioService.getUsuarioByCriterio("id", id);
                               mostrarUsuarios(usuarioId);
                           } catch (IllegalArgumentException e) {
                               System.out.println("Entrada inválida. El formato del UUID es incorrecto.");
                           }
                       } else {
                           System.out.println("Entrada inválida. Se esperaba un valor UUID.");
                       }
                       break;
                   case 2:
                       System.out.println("Introduzca nombre a buscar:");
                       String nombre = scanner.nextLine();

                       try {
                           List<Usuario> usuarioNombre = usuarioService.getUsuarioByCriterio("nombre", nombre);
                           mostrarUsuarios(usuarioNombre);
                       } catch (Exception e) {
                           System.out.println("Se produjo un error al buscar usuarios por nombre: " + e.getMessage());
                       }
                       break;
                   case 3:
                       System.out.println("Introduzca apellido1 a buscar:");
                       String apellido1 = scanner.nextLine();

                       try {
                           List<Usuario> usuarioapellido1 = usuarioService.getUsuarioByCriterio("apellido1", apellido1);
                           mostrarUsuarios(usuarioapellido1);
                       } catch (Exception e) {
                           System.out.println("Se produjo un error al buscar usuarios por apellido1: " + e.getMessage());
                       }
                       break;
                   case 4:
                       System.out.println("Introduzca apellido2 a buscar:");
                       String apellido2 = scanner.nextLine();

                       try {
                           List<Usuario> usuarioapellido2 = usuarioService.getUsuarioByCriterio("apellido2", apellido2);
                           mostrarUsuarios(usuarioapellido2);
                       } catch (Exception e) {
                           System.out.println("Se produjo un error al buscar usuarios por apellido2: " + e.getMessage());
                       }
                       break;
                   case 5:
                       System.out.println("Introduzca email a buscar:");
                       String correo = scanner.nextLine();

                       try {

                           if (usuarioService.validarCorreo(correo)) {
                               List<Usuario> usuarioCorreo = usuarioService.getUsuarioByCriterio("email", correo);
                               mostrarUsuarios(usuarioCorreo);
                           } else {
                               System.out.println("El formato del email es inválido.");
                           }
                       } catch (Exception e) {
                           System.out.println("Se produjo un error al buscar usuarios por email: " + e.getMessage());
                       }

                       break;
                   case 0:
                       return; // Volver al Menú User
               }
           }
       }
       private  void getUsuarioById() {
           System.out.println("------- Usuario Encontrado -------");
           try {
               System.out.println("Introduzca id a buscar :");
               String idString = scanner.nextLine();
               UUID id = UUID.fromString(idString);
               Usuario usuario = usuarioService.getUsuarioById(id);
               if (usuario != null) {
                   System.out.println(usuario);
                   System.out.println("-----------------------------");
               } else {
                   System.out.println("no se encontro usuario con id "+ id);
               }
           } catch (Exception e) {
               System.out.println("Ocurrió un error durante consulta de usuario por id: " + e.getMessage());
               e.printStackTrace();
           }
       }
       private  void mostrarUsuarios(List<Usuario> usuarios) {
           if (usuarios.isEmpty()) {
               System.out.println("No se encontraron usuarios.");
           } else {
               int cantidadUsuarios = usuarios.size();
               for (Usuario usuario : usuarios) {
                   System.out.println(usuario);
                   System.out.println("-----------------------------");
               }
               System.out.println("Cantidad de usuarios mostrados: " + cantidadUsuarios);
           }
       }


    private void showProductMenu() {
        while (true) {
            System.out.println("------- Menú Productos -------");
            System.out.println("1. Crear Producto");
            System.out.println("2. Actualizar Productos");
            System.out.println("3. Eliminar Productos");
            System.out.println("4. Listar Productos");
            System.out.println("5. Buscar Productos");
            System.out.println("0. Volver al Menú Principal");
            System.out.println("-----------------------------");

            int option = getOptionFromUser();
            switch (option) {
                case 1:
                    createProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    getAllProduct();
                    break;
                case 5:
                    showProductMenuByCriteria();
                    break;
                case 0:
                    return; // Volver al Menú Principal
            }
        }
    }


    private void createProduct() {
        System.out.println("------- Crear Producto -------");
        try {
            System.out.println("Ingrese el nombre: ");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese el precio: ");
            String precio = scanner.nextLine();


            Producto producto = new Producto(nombre, precio);
            boolean esExitoso = productoService.insertProducto(producto);
            if (esExitoso) {
                System.out.println("Producto creado exitosamente");
            } else {
                System.out.println("Error al crear el producto");
            }
        } catch (Exception e) { // Fin del bloque try
            System.out.println("Ocurrió un error durante la creación del producto: " + e.getMessage());
            e.printStackTrace();

        }
    }

    private void updateProduct() {
        System.out.println("------- Actualizar Producto -------");

        try {
            System.out.println("Ingrese el ID del producto a actualizar: ");
            String idString = scanner.nextLine();
            UUID id = UUID.fromString(idString);
            Producto productoActualizar = productoService.getProductoById(id);
            System.out.println("Ingrese el nuevo nombre: ");
            String nuevoNombre = scanner.nextLine();
            System.out.println("Ingrese el nuevo precio: ");
            String nuevoPrecio = scanner.next();

            Producto productoActualizado = new Producto(nuevoNombre, nuevoPrecio);
            productoActualizado.setId(id);
            System.out.println("Anterior "+productoActualizar);
            System.out.println("Actual "+productoActualizado);
            boolean esExitoso = productoService.updateProducto(productoActualizado);
            if (esExitoso) {
                System.out.println("Producto actualizado exitosamente");
            } else {
                System.out.println("Error al actualizar el producto");
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error durante la actualización del producto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void deleteProduct() {
        System.out.println("------- Eliminar Producto -------");
        try {
            System.out.println("Ingrese el ID del producto a eliminar: ");
            String idString = scanner.nextLine();
            UUID id = UUID.fromString(idString);

            System.out.println(productoService.getProductoById(id));
            boolean esExitoso = productoService.deleteProducto(id);
            if (esExitoso) {
                System.out.println("Producto eliminado exitosamente");
            } else {
                System.out.println("Error al eliminar el producto");
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error durante la eliminación del producto: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void getAllProduct() {
        System.out.println("------- Lista de productos -------");
        try {

            List<Producto> productos = productoService.getAllProducto();
            if (productos != null) {
                mostrarProductos(productos);

            } else {
                System.out.println("no se encontro productos");
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error durante consulta de productos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showProductMenuByCriteria() {
        while (true) {
            System.out.println("------- Menú Buscar por Criterio -------");
            System.out.println("1. Buscar por id");
            System.out.println("2. Buscar por nombre");
            System.out.println("3. Buscar por precio");
            System.out.println("0. Salir");
            System.out.println("-----------------------------");
            int option = getOptionFromUser();
            switch (option) {
                case 1:
                    System.out.println("1. Buscar por id");
                    System.out.println("----------------");
                    System.out.println("Introduzca id a buscar: ");

                    if (scanner.hasNextLine()) {
                        String idString = scanner.nextLine();

                        try {
                            UUID id = UUID.fromString(idString);
                            List<Producto> productoId = productoService.getProductoByCriterio("id", id);
                            mostrarProductos(productoId);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Entrada inválida. El formato del UUID es incorrecto.");
                        }
                    } else {
                        System.out.println("Entrada inválida. Se esperaba un valor UUID.");
                    }
                    break;
                case 2:
                    System.out.println("Introduzca nombre a buscar:");
                    String nombre = scanner.nextLine();

                    try {
                        List<Producto> productoNombre = productoService.getProductoByCriterio("nombre", nombre);
                        mostrarProductos(productoNombre);
                    } catch (Exception e) {
                        System.out.println("Se produjo un error al buscar productos por nombre: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Introduzca precio a buscar:");
                    String precio = scanner.nextLine();

                    try {
                        List<Producto> productoPrecio = productoService.getProductoByCriterio("precio", precio);
                        mostrarProductos(productoPrecio);
                    } catch (Exception e) {
                        System.out.println("Se produjo un error al buscar productos por precio: " + e.getMessage());
                    }
                    break;
                case 0:
                    return; // Volver al Menú Product
            }
        }
    }
    private void getProductoById() {
        System.out.println("------- Producto Encontrado -------");
        try {
            System.out.println("Introduzca id a buscar :");
            String idString = scanner.nextLine();
            UUID id = UUID.fromString(idString);
            Producto producto = productoService.getProductoById(id);
            if (producto != null) {
                System.out.println(producto);
                System.out.println("-----------------------------");
            } else {
                System.out.println("no se encontro producto con id "+ id);
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error durante consulta de producto por id: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void mostrarProductos(List<Producto> productos) {
        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos.");
        } else {
            int cantidadProductos = productos.size();
            for (Producto producto : productos) {
                System.out.println(producto);
                System.out.println("-----------------------------");
            }
            System.out.println("Cantidad de prodcutos mostrados: " + cantidadProductos);
        }
    }

        private void showPedidoMenu() {
            // Implementar menú de gestión de etiquetas
        }


    }