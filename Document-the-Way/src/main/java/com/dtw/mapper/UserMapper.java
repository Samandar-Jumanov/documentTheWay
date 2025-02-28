package com.dtw.mapper;

import com.dtw.dtos.requestDtos.UserRequestDto;
import com.dtw.dtos.responseDtos.*;
import com.dtw.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);


    @Mapping(target = "password" , ignore = true)
    User mapToUser(UserRequestDto dto);

    UserResponseDto mapToUserResponseDto(User user);

    DocumentResponseDto mapToDocumentResponseDto(Document document);
    FeedbackResponseDto mapToFeedbackResponseDto(Feedback feedback);
    RepostedDocumentResponseDto mapToRepostedDocumentResponseDto(RepostedDocument repostedDocument);
    NotificationResponseDto mapToNotificationResponseDto(Notification notification);
    PurchaseResponseDto mapToPurchaseResponseDto(Purchase purchase);
}
