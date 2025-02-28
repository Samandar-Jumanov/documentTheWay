package com.dtw.mapper;


import com.dtw.dtos.requestDtos.FeedbackRequestDto;
import com.dtw.dtos.responseDtos.FeedbackResponseDto;
import com.dtw.entity.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FeedBackMapper {

    FeedBackMapper MAPPER = Mappers.getMapper(FeedBackMapper.class);

    Feedback mapToFeedback(FeedbackRequestDto feedbackDto);
    FeedbackResponseDto mapToFeedbackDto (Feedback feedback);


}
