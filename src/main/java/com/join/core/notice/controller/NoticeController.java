package com.join.core.notice.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.notice.controller.specification.NoticeControllerSpecification;
import com.join.core.notice.domain.NoticeReadService;
import com.join.core.notice.dto.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/notices")
public class NoticeController implements NoticeControllerSpecification {

    private final NoticeReadService noticeReadService;

    @GetMapping
    public ApiResponse<List<NoticeResponse>> getNotices(@AuthenticationPrincipal UserPrincipal principal) {
        return ApiResponse.ok(noticeReadService.getNotices());
    }

}
