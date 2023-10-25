package com.desafiopicpay.dtos;

import org.springframework.http.HttpStatus;

public record ExceptionDTO (String message, int statusCode) {

}
