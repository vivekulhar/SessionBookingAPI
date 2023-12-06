package dev.vivek.sessionbookingapp1.repositories;


import dev.vivek.sessionbookingapp1.models.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    Optional<Meeting> findById(Long meetingId);
    Meeting save(Meeting meeting);
    void delete(Meeting meeting);

    Optional<List<Meeting>> findByClientIdAndConsultantId(Long clientId, Long consultantId);
}
