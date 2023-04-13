package br.com.alura.linguagens.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinguagemController {
	
	@Autowired
	private LinguagemRepository repositorio;
	
	private Linguagem findById(String id) {
		Optional<Linguagem> obj = repositorio.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	@GetMapping("/linguagens")
	public List<Linguagem> obterLinguagens(){
		List<Linguagem> linguagens = repositorio.findAll();
		return linguagens;
	}
	
	@PostMapping("/linguagens")
	public Linguagem cadastrarLinguagem(@RequestBody Linguagem linguagem) {
		Linguagem linguagemSalva = repositorio.save(linguagem);
		return linguagemSalva;
	}
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable String id){
		repositorio.deleteById(id);
	}
	
	
	@PutMapping(value = "/{id}")
	public Linguagem update(@PathVariable String id, @RequestBody Linguagem linguagem) {
		Linguagem linguagemAtualizada = findById(id);
		linguagemAtualizada.setImage(linguagem.getImage());
		linguagemAtualizada.setRanking(linguagem.getRanking());
		linguagemAtualizada.setTitle(linguagem.getTitle());
		return linguagemAtualizada;
	}	
}
