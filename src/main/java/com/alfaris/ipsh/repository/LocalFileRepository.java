package com.alfaris.ipsh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alfaris.ipsh.entity.LocalFile;

@Repository
public interface LocalFileRepository extends JpaRepository<LocalFile, Long>{

}
