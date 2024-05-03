package com.justice.nkopane.justnotes.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record ApiErroResponse(ZonedDateTime timestamp, HttpStatus status, String message, String path) {
}
