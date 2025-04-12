package com.join.core.fine.repository;

import com.join.core.fine.domain.FineReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FineReaderImpl implements FineReader {

    private final FineRepository fineRepository;

    @Override
    public int getTotalFineAmountByAvatar(Long studyId, Long avatarId) {
        return fineRepository.sumTotalFineByStudyIdAndAvatarId(studyId, avatarId);
    }
}