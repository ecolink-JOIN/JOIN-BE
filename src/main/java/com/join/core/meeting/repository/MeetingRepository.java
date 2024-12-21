package com.join.core.meeting.repository;

import com.join.core.meeting.domain.Meeting;
import com.join.core.study.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    List<Meeting> findByStudy(Study study);

}
