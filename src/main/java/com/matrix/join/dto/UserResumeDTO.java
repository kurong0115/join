package com.matrix.join.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName UserResumeDTO
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/24 21:43
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserResumeDTO {

    @Valid
    private ResumeDTO resume;

    private List<ResumeWorkExperienceDTO> resumeWorkExperienceList;

    private List<ResumeEducationDTO> resumeEducationList;
}
