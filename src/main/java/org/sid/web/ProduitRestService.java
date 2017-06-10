package org.sid.web;

import java.util.List;

import org.sid.dao.ProduitRepository;
import org.sid.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProduitRestService {
	
	@Autowired
	private ProduitRepository produitRepository;
	
	@RequestMapping(value="/produits", method=RequestMethod.GET)
	public List<Produit> listProduits() {
		return produitRepository.findAll();
	}
	
	@RequestMapping(value="/produits/{id}", method=RequestMethod.GET)
	public Produit getProduit(@PathVariable(name="id") Long id) {
		return produitRepository.findOne(id);
	}
	
	@RequestMapping(value="/produits", method=RequestMethod.POST)
	public Produit save(@RequestBody Produit p) {
		return produitRepository.save(p);
	}
	
	@RequestMapping(value="/chercher", method=RequestMethod.GET)
	public Page<Produit> chercher(
			@RequestParam(name="mc", defaultValue="")String mc,
			@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size", defaultValue="4")int size) {
		return produitRepository.chercher("%"+mc+"%", new PageRequest(page, size));
	}
	
	@RequestMapping(value="/produits/{id}", method=RequestMethod.PUT)
	public Produit update(@PathVariable(name="id") Long id, @RequestBody Produit p) {
		p.setId(id);
		return produitRepository.save(p);
	}

}
