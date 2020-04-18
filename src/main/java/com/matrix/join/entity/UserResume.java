package com.matrix.join.entity;

import com.matrix.join.entity.ResumeEducationEntity;
import com.matrix.join.entity.ResumeEntity;
import com.matrix.join.entity.ResumeWorkExperienceEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName UserResume
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/25 10:51
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserResume implements Serializable {

    private static final long serialVersionUID = 7437095881156225487L;

    private ResumeEntity resume;

    private List<ResumeWorkExperienceEntity> resumeWorkExperienceList;

    private List<ResumeEducationEntity> resumeEducationList;
}
