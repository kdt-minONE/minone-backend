package kdt.minone.domain.chat.controller;

import kdt.minone.domain.chat.dto.ChatRoomResDto;
import kdt.minone.domain.chat.dto.ChatRoomUpdateReqDto;
import kdt.minone.domain.chat.service.UserChatRoomService;
import kdt.minone.global.common.dto.BaseResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/{userId}/chat-rooms")
public class UserChatRoomController {

    private final UserChatRoomService userChatRoomService;

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
