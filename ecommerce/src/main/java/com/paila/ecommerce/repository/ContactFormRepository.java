package com.paila.ecommerce.repository;

import com.paila.ecommerce.entity.ContactForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactFormRepository extends JpaRepository<ContactForm,Long> {
}
