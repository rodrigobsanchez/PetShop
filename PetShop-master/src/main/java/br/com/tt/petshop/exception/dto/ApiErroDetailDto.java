package br.com.tt.petshop.exception.dto;

public class ApiErroDetailDto {
    private String message;


    public ApiErroDetailDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
