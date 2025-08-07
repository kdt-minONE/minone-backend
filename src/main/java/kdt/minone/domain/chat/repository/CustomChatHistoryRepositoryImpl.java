package kdt.minone.domain.chat.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kdt.minone.domain.chat.dto.MessageResDto;
import kdt.minone.domain.chat.dto.QMessageResDto;
import kdt.minone.domain.chat.entity.QChatHistory;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class CustomChatHistoryRepositoryImpl implements CustomChatHistoryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MessageResDto> findAllWithLimit(Long chatRoomId, Integer limit) {
        QChatHistory chatHistory = QChatHistory.chatHistory;

        JPAQuery<MessageResDto> messageQuery = jpaQueryFactory
                .select(new QMessageResDto(
                        chatHistory.id,
                        chatHistory.isAi,
                        chatHistory.content,
                        chatHistory.createdAt
                ))
                .from(chatHistory)
                .where(chatHistory.chatRoom.id.eq(chatRoomId))
                .orderBy(chatHistory.createdAt.desc());

        if (limit != null) {
            messageQuery.limit(limit);
        }

        List<MessageResDto> messages = messageQuery.fetch();
        Collections.reverse(messages);
        
        return messages;
    }
}
