package eus.birt.adavila.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="jugadores")
public class Jugador implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nombre;
	
	@Column
	private String apellido;
	
	@Column
	private String posicion;
	
	@Column
	private String procedencia;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn (name = "id_equipo")
	private Equipo equipo;

	//Añade propiedad nombreEquipo a JSON 
	@JsonProperty("nombreEquipo")
	public String getNombreEquipo() {
	    return equipo != null ? equipo.getNombre() : null;
	}
	public Jugador(String nombre, String apellido, String posicion, String procedencia, Equipo equipo) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.posicion = posicion;
		this.procedencia = procedencia;
		this.equipo = equipo;
	}
	
}
