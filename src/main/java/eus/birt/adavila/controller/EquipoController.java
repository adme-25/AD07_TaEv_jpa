package eus.birt.adavila.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import eus.birt.adavila.domain.Equipo;
import eus.birt.adavila.domain.Jugador;
import eus.birt.adavila.repository.EquipoRepo;

@RestController
@RequestMapping ("api/equipos")
public class EquipoController {
	
	@Autowired
	EquipoRepo equipoRepo;
	
	//Metodo para mostrar todos los equipos
	@GetMapping({"/", ""})
	public List<Equipo> index (){
		return equipoRepo.findAll();
	}
	
	//Metodo para mostrar un equipo por su id
	@GetMapping("/{id}")
	public Equipo show(@PathVariable("id") Long id){
		return equipoRepo.findById(id).orElse(null);
	}
	
	//Metodo para crear un equipo
	@PostMapping({"/",""})
	@ResponseStatus (HttpStatus.CREATED)
	public Equipo create(@RequestBody Equipo equipo) {
		return equipoRepo.save(equipo);
	}
	
	//Metodo para actualizar un equipo
	@PutMapping("/{id}")
	@ResponseStatus (HttpStatus.CREATED)
	public Equipo update(@RequestBody Equipo equipo, @PathVariable("id") Long id) {
		Equipo equipoTemp = equipoRepo.findById(id).orElse(null);
		
		equipoTemp.setNombre(equipo.getNombre());
		equipoTemp.setCiudad(equipo.getCiudad());
		equipoTemp.setConferencia(equipo.getConferencia());
		equipoTemp.setDivision(equipo.getDivision());
		
		
		return equipoRepo.save(equipoTemp);
	}
	
	//Metodo para borrar un equipo por su id
	@DeleteMapping("/{id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		equipoRepo.deleteById(id);
	}

	//Metodo para incluir un jugador en un equipo
	@PutMapping("/{id}/")
	@ResponseStatus (HttpStatus.CREATED)
	public Equipo addJugador(@RequestBody Jugador jugador, @PathVariable("id") Long id) {
		Equipo equipoTemp =  equipoRepo.findById(id).orElse(null);
		jugador.setEquipo(equipoTemp);
		equipoTemp.anadirJugador(jugador);
		
		return equipoRepo.save(equipoTemp);
		
	}
}
