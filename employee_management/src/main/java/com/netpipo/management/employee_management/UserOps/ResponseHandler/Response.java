package com.netpipo.management.employee_management.UserOps.ResponseHandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//this generic class to display json format for status and message
public class Response<T> {
    private HttpStatus status;
    private String message;


}
