package kdt.minone.domain.chat.controller;

import kdt.minone.domain.chat.dto.ChatRoomResDto;
import kdt.minone.domain.chat.dto.ChatRoomUpdateReqDto;
import kdt.minone.domain.chat.service.UserChatRoomService;
import kdt.minone.global.common.dto.BaseListResDto;
import kdt.minone.global.common.dto.BaseResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/{userId}/chat-rooms")
public class UserChatRoomController {

    private final UserChatRoomService userChatRoomService;

    @GetMapping
    public ResponseEntity<BaseListResDto<ChatRoomResDto>> findAll(
            @PathVariable Long userId
    ) {

        List<ChatRoomResDto> results = userChatRoomService.findAll(userId);

        return new ResponseEntity<>(new BaseListResDto<>(
                "채팅방 전체 조회 성공",
                results
        ), HttpStatus.OK);
    }

    @PatchMapping("/{chatRoomId}")
    public ResponseEntity<BaseResDto<ChatRoomResDto>> updateChatRoomById(
            @PathVariable Long userId,
            @PathVariable Long chatRoomId,
            @RequestBody ChatRoomUpdateReqDto dto
    ) {

        ChatRoomResDto result = userChatRoomService.updateChatRoomById(
                userId,
                chatRoomId,
                dto.getTitle()
        );

        return new ResponseEntity<>(new BaseResDto<>(
                "채팅방 수정 성공",
                result
        ), HttpStatus.OK);
    }

    @DeleteMapping("/{chatRoomId}")
    public ResponseEntity<Void> deleteChatRoomById(
            @PathVariable Long userId,
            @PathVariable Long chatRoomId
    ) {

        userChatRoomService.deleteChatRoomById(userId, chatRoomId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
