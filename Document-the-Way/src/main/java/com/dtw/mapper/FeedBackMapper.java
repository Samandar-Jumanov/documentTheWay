package com.dtw.mapper;

import com.dtw.dtos.requestDtos.FeedbackRequestDto;
import com.dtw.dtos.responseDtos.FeedbackResponseDto;
import com.dtw.dtos.responseDtos.UserResponseDto;
import com.dtw.entity.Feedback;
import com.dtw.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FeedBackMapper {

    FeedBackMapper MAPPER = Mappers.getMapper(FeedBackMapper.class);

    Feedback mapToFeedback(FeedbackRequestDto feedbackDto);

    @Mapping(target = "user", source = "user", qualifiedByName = "toUserResponseDtoWithoutFeedbacks")
    FeedbackResponseDto mapToFeedbackDto(Feedback feedback);

    // Add this method with a specific name to break the cycle
    @org.mapstruct.Named("toUserResponseDtoWithoutFeedbacks")
    @Mapping(target = "feedbacks", ignore = true)
    @Mapping(target = "documents", ignore = true)
    @Mapping(target = "reposts", ignore = true)
    @Mapping(target = "notifications", ignore = true)
    @Mapping(target = "purchases", ignore = true)
    UserResponseDto mapToUserResponseDtoWithoutFeedbacks(User user);
}