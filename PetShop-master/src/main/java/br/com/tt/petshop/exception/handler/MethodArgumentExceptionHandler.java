package br.com.tt.petshop.exception.handler;


import br.com.tt.petshop.exception.dto.ApiErroDetailDto;
import br.com.tt.petshop.exception.dto.ApiErrorDto;
import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class MethodArgumentExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiErrorDto handle(MethodArgumentNotValidException e){
       List<ApiErroDetailDto> erros = e.getBindingResult().getFieldErrors().stream()
               .map(error -> new ApiErroDetailDto(
                String.format("%s: %s", error.getObjectName(), error.getDefaultMessage()))).collect(Collectors.toList());
        return new ApiErrorDto("erro validação", "Alguns campos não satisfazem o negócio. Verifique");
    }
}
