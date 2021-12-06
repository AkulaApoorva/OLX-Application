package com.olx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.dto.Advertise;


public interface AdvertiseRepository extends JpaRepository<Advertise, String> {

}