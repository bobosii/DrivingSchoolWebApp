package dev.emir.DrivingSchoolWebApp.repository;

import dev.emir.DrivingSchoolWebApp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderId(Long senderId);
    List<Message> findByReceiverId(Long receiverId);
    List<Message> findByStatus(Message.MessageStatus status);
} 