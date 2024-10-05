package com.example.hotel.Controladores;

import com.example.hotel.Entidades.Encuesta;
import com.example.hotel.Servicios.ServicioEncuesta;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/encuestas")
public class EncuestaController {

    @Autowired
    private ServicioEncuesta encuestaService;

    @GetMapping("/")
    public String listarEncuestas(Model model) {
        List<Encuesta> encuestas = encuestaService.listarEncuestas();
        int totalEncuestas = encuestas.size();

        // Contar encuestas por nivel de satisfacción
        int[] counts = new int[5];
        for (Encuesta encuesta : encuestas) {
            switch (encuesta.getNivelSatisfaccion()) {
                case "muy_satisfecho":
                    counts[0]++;
                    break;
                case "satisfecho":
                    counts[1]++;
                    break;
                case "neutral":
                    counts[2]++;
                    break;
                case "insatisfecho":
                    counts[3]++;
                    break;
                case "muy_insatisfecho":
                    counts[4]++;
                    break;
            }
        }

        // Calcular porcentajes
        double[] percentages = new double[5];
        for (int i = 0; i < 5; i++) {
            percentages[i] = (counts[i] / (double) totalEncuestas) * 100;
        }

        // Preparar los atributos para la vista
        model.addAttribute("encuestas", encuestas);
        model.addAttribute("promedioAge", encuestaService.promedioEdad());
        model.addAttribute("porcentajeMS", String.format("%.2f", percentages[0]));
        model.addAttribute("porcentajeS", String.format("%.2f", percentages[1]));
        model.addAttribute("porcentajeN", String.format("%.2f", percentages[2]));
        model.addAttribute("porcentajeI", String.format("%.2f", percentages[3]));
        model.addAttribute("porcentajeMI", String.format("%.2f", percentages[4]));

        return "listaEncuestas";
    }


    @GetMapping("/encuestas/filtrar")
    public String filtrarEncuestas(@RequestParam(defaultValue = "") String nivelSatisfaccion, Model model) {
        List<Encuesta> encuestasFiltradas = encuestaService.filtrarEncuestas(nivelSatisfaccion);

        // Obtener estadísticas para todas las encuestas
        List<Encuesta> todasLasEncuestas = encuestaService.listarEncuestas();
        int[] counts = new int[5];
        for (Encuesta encuesta : todasLasEncuestas) {
            switch (encuesta.getNivelSatisfaccion()) {
                case "muy_satisfecho":
                    counts[0]++;
                    break;
                case "satisfecho":
                    counts[1]++;
                    break;
                case "neutral":
                    counts[2]++;
                    break;
                case "insatisfecho":
                    counts[3]++;
                    break;
                case "muy_insatisfecho":
                    counts[4]++;
                    break;
            }
        }

        // Calcular porcentajes
        double[] percentages = new double[5];
        for (int i = 0; i < 5; i++) {
            percentages[i] = (counts[i] / (double) todasLasEncuestas.size()) * 100;
        }

        // Preparar los atributos para la vista
        model.addAttribute("encuestas", encuestasFiltradas);
        model.addAttribute("promedioAge", encuestaService.promedioEdad());
        model.addAttribute("porcentajeMS", String.format("%.2f", percentages[0]));
        model.addAttribute("porcentajeS", String.format("%.2f", percentages[1]));
        model.addAttribute("porcentajeN", String.format("%.2f", percentages[2]));
        model.addAttribute("porcentajeI", String.format("%.2f", percentages[3]));
        model.addAttribute("porcentajeMI", String.format("%.2f", percentages[4]));

        return "listaEncuestas";
    }

    @GetMapping("/{id}")
    public String verEncuesta(@PathVariable Long id, Model model) {
        Encuesta encuesta = encuestaService.encontrarPorId(id);
        model.addAttribute("encuesta", encuesta);
        return "verEncuesta";
    }

    @GetMapping("/crear")
    public String crearEncuesta(Model model) {
        Encuesta nuevaEncuesta = new Encuesta();
        model.addAttribute("encuesta", nuevaEncuesta);
        return "formularioEncuesta";
    }

    @PostMapping
    public String guardarEncuesta(@Valid Encuesta encuesta, Model model) {
        Encuesta nuevaEncuesta = encuestaService.saveOrUpdateEncuesta(encuesta);
        model.addAttribute("encuesta", nuevaEncuesta);
        return "redirect:/encuestas/" + nuevaEncuesta.getId();
    }
    @GetMapping("/eliminar/{id}")
    public String eliminarEncuesta(@RequestParam Long id, Model model) {
        encuestaService.deleteEncuesta(id);
        return "listaEncuestas";
    }
    @GetMapping("/editar/{id}")
    public String editarEncuesta(@PathVariable Long id, Model model) {
        Encuesta encuesta = encuestaService.encontrarPorId(id);
        model.addAttribute("encuesta", encuesta);
        return "formularioEditEncuesta";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarEncuesta(@PathVariable Long id, @ModelAttribute Encuesta encuesta) {
        encuesta.setId(id);
        encuestaService.saveOrUpdateEncuesta(encuesta);
        return "redirect:/encuestas/";
    }


}