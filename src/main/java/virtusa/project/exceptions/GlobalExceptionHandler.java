package virtusa.project.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountDeletedException.class)
    public ResponseEntity<ApiErrorResponse> handleAccountDeleted(
            AccountDeletedException ex) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(
                        ApiErrorResponse.builder()
                                .issue(ex.getMessage())
                                .status(HttpStatus.FORBIDDEN.value())
                                .timestamp(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(
            ResourceNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        ApiErrorResponse.builder()
                                .issue(ex.getMessage())
                                .status(HttpStatus.NOT_FOUND.value())
                                .timestamp(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiErrorResponse> handleBadRequest(
            BadRequestException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiErrorResponse.builder()
                                .issue(ex.getMessage())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .timestamp(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiErrorResponse> handleUnauthorized(
            UnauthorizedException ex) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(
                        ApiErrorResponse.builder()
                                .issue(ex.getMessage())
                                .status(HttpStatus.UNAUTHORIZED.value())
                                .timestamp(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(UnrecognizedPropertyException.class)
    public ResponseEntity<ApiErrorResponse> handleUnknownField(
            UnrecognizedPropertyException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiErrorResponse.builder()
                                .issue("Unknown field: " + ex.getPropertyName())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .timestamp(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleJsonParse(
            HttpMessageNotReadableException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiErrorResponse.builder()
                                .issue("Invalid request body")
                                .status(HttpStatus.BAD_REQUEST.value())
                                .timestamp(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(
            MethodArgumentNotValidException ex) {

        String issue = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .orElse("Validation failed");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiErrorResponse.builder()
                                .issue(issue)
                                .status(HttpStatus.BAD_REQUEST.value())
                                .timestamp(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneric(
            Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ApiErrorResponse.builder()
                                .issue(ex.getMessage())
                                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .timestamp(LocalDateTime.now())
                                .build());
    }
}