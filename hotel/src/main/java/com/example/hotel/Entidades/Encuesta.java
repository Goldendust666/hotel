package com.example.hotel.Entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "encuestas")
public class Encuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nombre es obligatorio")
    @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
    private String nombre;

    @NotBlank(message = "Apellidos son obligatorios")
    @Size(min = 2, message = "Los apellidos deben tener al menos 2 caracteres")
    private String apellidos;

    @NotBlank(message = "Email es obligatorio")
    @Email(message = "Debes ingresar un correo electrónico válido")
    private String email;

    @Min(value = 18, message = "Debes tener al menos 18 años")
    private Integer edad;

    @NotBlank(message = "Teléfono es obligatorio")
    private String telefono;

    @PastOrPresent(message = "La fecha debe ser anterior a la fecha actual")
    private java.time.LocalDate fechaInicioEstancia;

    @NotBlank(message = "Motivo de visita es obligatorio")
    private String motivoVisita;

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "encuesta_servicios", joinColumns = @JoinColumn(name = "encuesta_id"))
    @Column(name = "servicio")
    private Set<String> serviciosUtilizados;

    @NotBlank(message = "Nivel de satisfacción es obligatorio")
    private String nivelSatisfaccion;

    private String otrosComentarios;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public java.time.LocalDate getFechaInicioEstancia() {
        return fechaInicioEstancia;
    }

    public void setFechaInicioEstancia(java.time.LocalDate fechaInicioEstancia) {
        this.fechaInicioEstancia = fechaInicioEstancia;
    }

    public String getMotivoVisita() {
        return motivoVisita;
    }

    public void setMotivoVisita(String motivoVisita) {
        this.motivoVisita = motivoVisita;
    }

    public Set<String> getServiciosUtilizados() {
        return serviciosUtilizados;
    }

    public void setServiciosUtilizados(Set<String> serviciosUtilizados) {
        this.serviciosUtilizados = serviciosUtilizados;
    }

    public String getNivelSatisfaccion() {
        return nivelSatisfaccion;
    }

    public void setNivelSatisfaccion(String nivelSatisfaccion) {
        this.nivelSatisfaccion = nivelSatisfaccion;
    }

    public String getOtrosComentarios() {
        return otrosComentarios;
    }

    public void setOtrosComentarios(String otrosComentarios) {
        this.otrosComentarios = otrosComentarios;
    }
}
