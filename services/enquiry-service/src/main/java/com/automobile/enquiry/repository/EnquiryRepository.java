package com.automobile.enquiry.repository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.automobile.enquiry.enquiry.Enquiry;
public interface EnquiryRepository extends JpaRepository<Enquiry, UUID> {}