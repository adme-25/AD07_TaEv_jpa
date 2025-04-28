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

import eus.birt.adavila.domain.Jugador;
import eus.birt.adavila.repository.EquipoRepo;
import eus.birt.adavila.repository.JugadorRepo;

@RestController
@RequestMapping ("api/jugadores")
public class JugadorController {
	
	@Autowired
	JugadorRepo jugadorRepo;
	
	@Autowired
	EquipoRepo equipoRepo;
	
	@GetMapping({"/",""})
	public List <Jugador> index(){
		return jugadorRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Jugador show(@PathVariable("id") Long id) {
		return jugadorRepo.findById(id).orElse(null);
	}
	
	@PostMapping({"","/"})
	@ResponseStatus (HttpStatus.CREATED)
	public Jugador create(@RequestBody Jugador jugador) {
		return jugadorRepo.save(jugador);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus (HttpStatus.CREATED)
	public Jugador update(@RequestBody Jugador jugador,@PathVariable("id") Long id) {
		Jugador jugadorTemp = jugadorRepo.findById(id).orElse(null);
		
		jugadorTemp.setNombre(jugador.getNombre());
		jugadorTemp.setApellido(jugador.getApellido());
		jugadorTemp.setEquipo(jugador.getEquipo());
		jugadorTemp.setPosicion(jugador.getPosicion());
		jugadorTemp.setProcedencia(jugador.getProcedencia());
		
		return jugadorRepo.save(jugadorTemp);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		jugadorRepo.deleteById(id);
	}

}
