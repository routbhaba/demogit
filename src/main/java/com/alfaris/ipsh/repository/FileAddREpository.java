package com.alfaris.ipsh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alfaris.ipsh.entity.FileAdd;

@Repository
public interface FileAddREpository extends JpaRepository<FileAdd, Long> {

}
