package es.dwes.backend_usuarios.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.dwes.backend_usuarios.entity.CompraEntrada;
import es.dwes.backend_usuarios.entity.Usuario;
import es.dwes.backend_usuarios.repository.CompraRepository;
import es.dwes.backend_usuarios.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/compras")
@AllArgsConstructor
public class CompraController {

    private final CompraRepository compraRepository;
    private final UsuarioRepository usuarioRepository;

    // Endpoint para procesar la compra de múltiples entradas desde el carrito
    @PostMapping
    public ResponseEntity<?> procesarCompra(@RequestBody Map<String, Object> payload) {
        System.out.println("Payload recibido: " + payload);
        try {
            // Validación de seguridad: el idUsuario es obligatorio
            if (payload.get("idUsuario") == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "idUsuario es null"));
            }
            Long idUsuario = Long.valueOf(payload.get("idUsuario").toString());

            // Obtenemos la lista de items (entradas) del carrito
            List<Map<String, Object>> items = (List<Map<String, Object>>) payload.get("items");
            if (items == null || items.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "No hay items en la compra"));
            }

            // Buscamos al usuario en la base de datos
            Usuario usuario = usuarioRepository.findById(idUsuario)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // Procesamos cada entrada individualmente
            for (Map<String, Object> item : items) {
                System.out.println("Procesando item: " + item);

                if (item.get("idEvento") == null || item.get("precio") == null || item.get("cantidad") == null) {
                    return ResponseEntity.badRequest().body(Map.of("error", "Datos de item incompletos: " + item));
                }

                CompraEntrada compra = new CompraEntrada();
                compra.setUsuario(usuario);

                // Lógica Robusta para el ID del Evento:
                // El frontend puede enviar IDs como "1" o "1-seats-1774...". 
                // Extraemos solo la parte numérica inicial para guardarlo correctamente como Long.
                String idStr = item.get("idEvento").toString();
                if (idStr.contains("-")) {
                    idStr = idStr.split("-")[0];
                }

                compra.setIdEvento(Long.valueOf(idStr));
                compra.setPrecio(Double.valueOf(item.get("precio").toString()));
                compra.setCantidad(Long.valueOf(item.get("cantidad").toString()));

                // Si el item incluye la lista de asientos seleccionados (formato JSON), la guardamos como String
                if (item.get("asientos") != null) {
                    compra.setAsientos(item.get("asientos").toString());
                }

                compra.setFechaCompra(LocalDate.now());
                compraRepository.save(compra);
            }

            return ResponseEntity.ok(Map.of("message", "Compra procesada con éxito"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Endpoint adicional para que el modal de asientos sepa qué asientos están ya ocupados
    @GetMapping("/evento/{idEvento}")
    public ResponseEntity<List<String>> getAsientosOcupados(@PathVariable Long idEvento) {
        List<CompraEntrada> compras = compraRepository.findByIdEvento(idEvento);
        List<String> todosAsientos = new java.util.ArrayList<>();
        for (CompraEntrada c : compras) {
            // Limpiamos el texto ["A1", "A2"] guardado en la DB para devolver una lista de IDs limpia
            if (c.getAsientos() != null && !c.getAsientos().isEmpty()) {
                String[] seats = c.getAsientos().replace("[", "").replace("]", "").split(",");
                for (String s : seats) {
                    todosAsientos.add(s.trim().replace("\"", ""));
                }
            }
        }
        return ResponseEntity.ok(todosAsientos);
    }

    // Obtenemos el historial de compras de un usuario en específico
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> getComprasPorUsuario(@PathVariable Long idUsuario) {
        try {
            // 1. Verificamos si el usuario existe 
            if (!usuarioRepository.existsById(idUsuario)) {
                return ResponseEntity.badRequest().body(Map.of("error", "El usuario no existe"));
            }

            // 2. Buscamos todas las compras asociadas a ese usuario usando el método del repositorio
            List<CompraEntrada> historial = this.compraRepository.findByUsuario_Id(idUsuario);

            // 3. Devolvemos la lista 
            return ResponseEntity.ok(historial);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("error", "Error al obtener el historial: " + e.getMessage()));
        }
    }
}
