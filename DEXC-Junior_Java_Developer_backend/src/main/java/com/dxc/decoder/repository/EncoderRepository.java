package com.dxc.decoder.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.decoder.entity.EncoderLog;

public interface EncoderRepository extends JpaRepository<EncoderLog, Long>{
} 

