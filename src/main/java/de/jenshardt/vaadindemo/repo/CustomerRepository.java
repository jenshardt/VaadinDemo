package de.jenshardt.vaadindemo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.jenshardt.vaadindemo.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByLastNameStartsWithIgnoreCase(String lastName);
}
