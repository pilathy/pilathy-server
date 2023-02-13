package com.pilathy.domain.service.service.center;

import com.pilathy.common.exception.ErrorCode;
import com.pilathy.common.exception.model.NotFoundException;
import com.pilathy.domain.rds.domain.center.Center;
import com.pilathy.domain.rds.domain.center.CenterRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CenterServiceHelper {

    public static Center findCenterById(CenterRepository centerRepository, Long centerId) {
        Center center = centerRepository.findCenterById(centerId);
        if (center == null) {
            throw new NotFoundException(String.format("해당하는 센터(%s)는 존재하지 않습니다.", centerId), ErrorCode.E404_NOT_EXISTS_CENTER);
        }
        return center;
    }

}
