package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Guestbook;

public interface GuestbookRepository 
                  extends JpaRepository<Guestbook, Long> {

}
