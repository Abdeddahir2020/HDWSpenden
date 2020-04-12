package de.hdw.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hdw.exception.RecordNotFoundException;
import de.hdw.model.Spender;
import de.hdw.service.SpenderService;


@RestController
@RequestMapping("/spender")
public class SpenderController {
	@Autowired
	SpenderService service;

	@GetMapping
	public ResponseEntity<List<Spender>> getAllSpender() {
		List<Spender> list = service.getAllSpender();

		return new ResponseEntity<List<Spender>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{iban}")
	public ResponseEntity<Spender> getSpenderById(@PathVariable("iban") Long iban) throws RecordNotFoundException {
		Spender entity = service.getSpenderByIban(iban);

		return new ResponseEntity<Spender>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Spender> createOrUpdateSpender(Spender spedner) throws RecordNotFoundException {
		Spender updated = service.createOrUpdateSpender(spedner);
		return new ResponseEntity<Spender>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{iban}")
	public HttpStatus deleteSpenderById(@PathVariable("iban") Long iban) throws RecordNotFoundException {
		service.deleteSpenderByIban(iban);
		return HttpStatus.FORBIDDEN;
	}

}