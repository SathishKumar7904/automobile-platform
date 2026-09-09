package com.automobile.enquiry.exception;
import java.util.UUID;
public class InvalidDealerAssignmentException extends RuntimeException { public InvalidDealerAssignmentException(UUID dealerId,UUID variantId){super("Dealer "+dealerId+" is not available for variant "+variantId);} }