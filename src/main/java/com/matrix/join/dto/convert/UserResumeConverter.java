package com.matrix.join.dto.convert;


import com.matrix.join.dto.UserResumeDTO;
import com.matrix.join.entity.UserResume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @ClassName UserResumeConverter
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/25 10:53
 * @Version 1.0
 */
@Component
public class UserResumeConverter extends CommonConverter{

    @Autowired
    ResumeConverter resumeConverter;

    @Autowired
    ResumeEducationConverter educationConverter;

    @Autowired
    ResumeWorkExperienceConverter workExperienceConverter;

    /**
     * po -> dto
     * @param po
     * @return
     */
    public UserResumeDTO convertPOToDTO(UserResume po) {
        UserResumeDTO dto = super.convert(UserResumeDTO.class, po);
        dto.setResume(resumeConverter.convertPOToDTO(po.getResume()))
                .setResumeEducationList(po.getResumeEducationList().stream().map(x -> educationConverter.convertPOToDTO(x)).collect(Collectors.toList()))
                .setResumeWorkExperienceList(po.getResumeWorkExperienceList().stream().map(x -> workExperienceConverter.convertPOToDTO(x)).collect(Collectors.toList()));
        return dto;
    }

    /**
     * dto -> po
     * @param dto
     * @return
     */
    public UserResume convertDTOToPO(UserResumeDTO dto) {
        UserResume po = super.convert(UserResume.class, dto);
        po.setResume(resumeConverter.convertDTOToPO(dto.getResume()))
                .setResumeEducationList(dto.getResumeEducationList().stream().map(x -> educationConverter.convertDTOToPO(x)).collect(Collectors.toList()))
                .setResumeWorkExperienceList(dto.getResumeWorkExperienceList().stream().map(x -> workExperienceConverter.convertDTOToPO(x)).collect(Collectors.toList()));
        return po;
    }
}
