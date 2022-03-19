package com.example.demo.api.member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	
	private String username;
	
	@JsonIgnore
	private String password;
	
	private String name;
	
	@Getter @Setter
	public class CreateRequest {
		@NotBlank @Email private String username;
		@NotBlank private String password;
		@NotBlank private String name;
	}
	
	@Getter @Setter
	public class UpdateRequest {
		@NotBlank @Email private String username;
		@NotBlank private String name;
	}
	
	@Getter @Setter
	public class SelectRequest {
		@NotBlank @Email private String username;
	}
	
	@Getter @Setter
	public class DeleteRequest {
		@NotBlank @Email private String username;
	}
	
	public static Member of(final CreateRequest request) {
		return builder()
				.username(request.getUsername())
				.password(request.getPassword())
				.name(request.getName())
				.build();
	}
	
	public static Member of(final Member member, final UpdateRequest request) {
		return builder()
				.username(member.getUsername())
				.password(member.getPassword())
				.name(request.getName())
				.build();
	}
	
	public static Member of(final SelectRequest request) {
		return builder()
				.username(request.getUsername())
				.build();
	}
	
	public static Member of(final DeleteRequest request) {
		return builder()
				.username(request.getUsername())
				.build();
	}
	
}
