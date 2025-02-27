package com.dtw.mapper;


import com.dtw.dtos.FeedbackDto;
import com.dtw.entity.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FeedBackMapper {

    FeedBackMapper MAPPER = Mappers.getMapper(FeedBackMapper.class);

//    @Mapping(target = "user", source = "user")
    Feedback mapToFeedback(FeedbackDto feedbackDto);
    FeedbackDto mapToFeedbackDto (Feedback feedback);


}
