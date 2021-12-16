package com.generation.infokids.seguranca;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.generation.infokids.repository.UsuarioRepository;

import com.generation.infokids.model.Usuario;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	
	@Autowired
	private UsuarioRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		
		Optional<Usuario> usuario = userRepository.findByEmail(username);
		
		/**
		 * Se o usuário não existir, o método lança uma Exception do tipo UsernameNotFoundException.
		 */ 
	  
		usuario.orElseThrow(() -> new UsernameNotFoundException(username + " not found."));

		/**
		 * Retorna um objeto do tipo UserDetailsImpl criado com os dados recuperados do
		 * Banco de dados.
		 * 
		 * O operador :: faz parte de uma expressão que referencia um método, complementando
		 * uma função lambda. Neste exemplo, o operador faz referência ao construtor da 
		 * Classe UserDetailsImpl. 
		 */

		return usuario.map(UserDetailsImpl::new).get();
		
	}

}
