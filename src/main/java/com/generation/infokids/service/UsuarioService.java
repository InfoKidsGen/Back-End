package com.generation.infokids.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.generation.infokids.model.Usuario;
import com.generation.infokids.model.UsuarioLogin;
import com.generation.infokids.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

		if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent())
			return Optional.empty();

		usuario.setSenha(criptografarSenha(usuario.getSenha()));

		return Optional.of(usuarioRepository.save(usuario));

	}

	public Optional<Usuario> atualizarUsuario(Usuario usuario) {

		if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {

			usuario.setSenha(criptografarSenha(usuario.getSenha()));

			return Optional.of(usuarioRepository.save(usuario));

		}

		return Optional.empty();

	}

	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {

		Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioLogin.get().getEmail());

		if (usuario.isPresent()) {
			if (compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {

				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNomeCompleto(usuario.get().getNomeCompleto());
				usuarioLogin.get().setFoto(usuario.get().getFoto());
				usuarioLogin.get()
						.setToken(gerarBasicToken(usuarioLogin.get().getEmail(), usuarioLogin.get().getSenha()));
				usuarioLogin.get().setSenha(usuario.get().getSenha());
				usuarioLogin.get().setPerfis(usuario.get().getPerfis());
				return usuarioLogin;

			}
		}

		return Optional.empty();

	}

	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);

	}

	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(senhaDigitada, senhaBanco);

	}

	private String gerarBasicToken(String usuario, String senha) {

		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);

	}

}
