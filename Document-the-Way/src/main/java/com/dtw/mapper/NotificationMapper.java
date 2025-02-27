package com.dtw.mapper;


import com.dtw.dtos.NotificationDto;
import com.dtw.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NotificationMapper {

    NotificationMapper MAPPER = Mappers.getMapper(NotificationMapper.class);

    Notification mapToNotification(NotificationDto dto);
    NotificationDto mapToNotificationDto ( Notification notification);


}


// User course should have a like some kind of security to access;

