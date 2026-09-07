package com.automobile.customer.exception;

public class DuplicateEmailException extends RuntimeException {


public DuplicateEmailException(String email) {
    super("A customer with email already exists: " + email);
}


}
