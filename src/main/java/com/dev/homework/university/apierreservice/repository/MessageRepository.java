package com.dev.homework.university.apierreservice.repository;

import com.dev.homework.university.apierreservice.entity.Message;
import com.dev.homework.university.apierreservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m " +
            "FROM Message m " +
            "WHERE m.recipient = :recipient " +
            "ORDER BY m.timestamp DESC"
    )
    List<Message> findByRecipientOrderByTimestampDesc(User recipient);
}
