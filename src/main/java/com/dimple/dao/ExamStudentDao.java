package com.dimple.dao;

import com.dimple.entity.ExamStudent;
import com.dimple.entity.Score;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 试卷和学生的关联表(ExamStudent)表数据库访问层
 *
 * @author makejava
 * @since 2019-05-01 11:39:01
 */
public interface ExamStudentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param esId 主键
     * @return 实例对象
     */
    ExamStudent queryById(Integer esId);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param examStudent 实例对象
     * @return 对象列表
     */
    List<ExamStudent> queryAll(ExamStudent examStudent);

    /**
     * 新增数据
     *
     * @param examStudent 实例对象
     * @return 影响行数
     */
    int insert(ExamStudent examStudent);

    /**
     * 修改数据
     *
     * @param examStudent 实例对象
     * @return 影响行数
     */
    int update(ExamStudent examStudent);

    /**
     * 通过主键删除数据
     *
     * @param esId 主键
     * @return 影响行数
     */
    int deleteById(Integer esId);

    /**
     * 根据考试的id删除记录
     *
     * @param examId
     * @return
     */
    int deleteByExamId(Integer examId);

    /**
     * 根据试卷的id和学生的id，更换它的状态
     *
     * @param examId
     * @param stuId
     * @return
     */
    int updateStatusByExamIdAndStuId(@Param("examId") Integer examId, @Param("stuId") Integer stuId, @Param("status") String status);

    /**
     * 根据exam的id和stu的id获取单条记录
     *
     * @param examId
     * @param id
     * @return
     */
    ExamStudent selectByExamIdAndStuId(@Param("exmaId") Integer examId, @Param("stuId") Integer id);

    ExamStudent queryByStudentIdAndExamId(@Param("stuId") Integer userId, @Param("examId") Integer examId);

    /**
     * 获取ExamStudent实体类的信息
     *
     * @param examId
     * @return
     */
    List<ExamStudent> selectByExamId(Integer examId);

    /**
     * 根据studentId和exam的id更新成绩
     *
     * @param stuId
     * @param examId
     * @param score
     * @param reading
     * @return
     */
    int updateReadingAndTotalScoreByStuIdAndExamId(@Param("stuId") Integer stuId, @Param("examId") Integer examId, @Param("score") Double score, @Param("reading") String reading);

    /**
     * 查询所有的成绩的集合
     *
     * @param score 成绩
     * @return 成绩集合
     */
    List<Score> selectScoreList(Score score);
}