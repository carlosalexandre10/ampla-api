package br.com.ampla.marca.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionConfig extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<Object> runtimeException(RuntimeException ex) {
		return new ResponseEntity<>(new ApiErrors(ex.getMessage()), HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> errorNotFound(EmptyResultDataAccessException ex) {
		return new ResponseEntity<>(new ApiErrors(ex.getMessage()), HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler({ IllegalArgumentException.class })
	public ResponseEntity<Object> errorBadRequest(IllegalArgumentException ex) {
		return new ResponseEntity<>(new ApiErrors(ex.getMessage()), HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Object> accessDenied() {
		return new ResponseEntity<>(new ApiErrors("Acesso negado."), HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler({ ResponseStatusException.class })
	public ResponseEntity<Object> errorBadRequest(ResponseStatusException ex) {
		String mensagemErro = ex.getMessage();
		HttpStatus codigoStatus = ex.getStatus();
		ApiErrors apiErros = new ApiErrors(mensagemErro);
		
		return new ResponseEntity<>(apiErros, codigoStatus);
	}

	@Override
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult bindingResult = ex.getBindingResult();

		List<String> messages = bindingResult.getAllErrors().stream()
				.map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList());

		return new ResponseEntity<>(new ApiErrors(messages), status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(new ApiErrors("Operação não permitida"), HttpStatus.METHOD_NOT_ALLOWED);
	}

}