package com.auth.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;



@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(
            ResourceNotFoundException.class
    )
    public ResponseEntity<ErrorResponse>
    handleNotFound(
            ResourceNotFoundException ex){



        return new ResponseEntity<>(

                new ErrorResponse(

                        404,

                        ex.getMessage(),

                        LocalDateTime.now()

                ),

                HttpStatus.NOT_FOUND

        );


    }




    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse>
    handleGeneral(Exception ex){



        return new ResponseEntity<>(

                new ErrorResponse(

                        500,

                        ex.getMessage(),

                        LocalDateTime.now()

                ),

                HttpStatus.INTERNAL_SERVER_ERROR

        );

    }

}