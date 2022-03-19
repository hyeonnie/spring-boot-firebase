package com.example.demo.api.member;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "members")
public class MemberController {

	private final MemberService memberService;
	
	@Autowired
	public MemberController(final MemberService memberService) {
		this.memberService = memberService;
	}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid final Member.CreateRequest request) {
		final boolean result = memberService.create(request);
		return ResponseEntity.status(result ? HttpStatus.OK : HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping
	public ResponseEntity<?> update(@Valid final Member.UpdateRequest request) {
		final boolean result = memberService.update(request);
		return ResponseEntity.status(result ? HttpStatus.OK : HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping
	public Member select(@Valid final Member.SelectRequest request) {
		return memberService.select(request);
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@Valid final Member.DeleteRequest request) {
		final boolean result = memberService.delete(request);
		return ResponseEntity.status(result ? HttpStatus.OK : HttpStatus.NO_CONTENT).build();
	}
	
}
