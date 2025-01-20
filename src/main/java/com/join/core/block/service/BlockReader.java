package com.join.core.block.service;

public interface BlockReader {

    boolean existBySubjectTokenAndTargetToken(String subjectToken, String targetToken);
}
