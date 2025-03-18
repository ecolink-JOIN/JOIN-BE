package com.join.core.proof.dto.response;

import java.util.Collection;

public record ProofSubjectsResponse(
        String studyToken,
        Collection<ProofSubject> subjects
) {
}
