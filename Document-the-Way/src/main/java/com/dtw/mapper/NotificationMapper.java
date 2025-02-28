package com.dtw.mapper;


import com.dtw.dtos.requestDtos.NotificationRequestDto;
import com.dtw.dtos.responseDtos.NotificationResponseDto;
import com.dtw.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NotificationMapper {

    NotificationMapper MAPPER = Mappers.getMapper(NotificationMapper.class);

    Notification mapToNotification(NotificationRequestDto dto);
    NotificationResponseDto mapToNotificationDto (Notification notification);

}


// User course should have a like some kind of security to access;

