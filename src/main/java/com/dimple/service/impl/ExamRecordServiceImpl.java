package com.dimple.service.impl;

import com.alibaba.druid.sql.visitor.functions.If;
import com.dimple.entity.ExamRecord;
import com.dimple.dao.ExamRecordDao;
import com.dimple.service.ExamRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 考试记录(ExamRecord)表服务实现类
 *
 * @author makejava
 * @since 2019-05-04 01:11:58
 */
@Service("examRecordService")
public class ExamRecordServiceImpl implements ExamRecordService {
    @Resource
    private ExamRecordDao examRecordDao;

    @Override
    public int insertOrUpdateRecord(ExamRecord record) {
        if (record == null || record.getExamId() == null || record.getAnswer() == null || record.getQuestionId() == null) {
            return -1;
        }
        return examRecordDao.insertOrUpdateRecord(record);
    }
}