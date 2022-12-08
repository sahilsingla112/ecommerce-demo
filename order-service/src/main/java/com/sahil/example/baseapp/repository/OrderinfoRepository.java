package com.sahil.example.baseapp.repository;

import com.sahil.example.baseapp.model.Orderinfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderinfoRepository extends CrudRepository<Orderinfo, String> {
}
