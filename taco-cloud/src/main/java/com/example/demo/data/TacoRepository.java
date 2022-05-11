package com.example.demo.data;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
