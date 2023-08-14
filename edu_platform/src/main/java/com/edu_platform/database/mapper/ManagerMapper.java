package com.edu_platform.database.mapper;

import com.edu_platform.database.data.Comment;
import com.edu_platform.database.data.Report;
import com.edu_platform.database.data.ReportRst;
import com.edu_platform.database.data.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 管理员Mapper
 * @Author YanRanyudi
 * @CreateTime 2022/3/4 20:27
 * @Version 1.0.0
 */

@Mapper
public interface ManagerMapper {

    /**
     * 按照名称模糊查询用户
     * @param name
     */
    @Select("<script>"
            +  "select user_id,user_name,user_phone,user_state from user"
            +    "<where>"
            +      "<if test='userName!=null'>"
            +        "user_name like '%${userName}%'"
            +      "</if>"
            +  "</where>"
            +"</script>")
    public List<User> queryUserList(String name)throws Exception;

    /**
     * 按照内容查询评论
     * @param content
     * @return
     * @throws Exception
     */
    @Select("<script>"
            +  "select * from comment"
            +    "<where>"
            +      "<if test='commentContent!=null'>"
            +        "comment_content like '%${commentContent}%'"
            +      "</if>"
            +  "</where>"
            +"</script>")
    public List<Comment> queryCommentList(String content)throws Exception;

    /**
     * 按照内容查询投诉
     * @param content
     * @return
     * @throws Exception
     */
    @Select("select report_id,report_content,agency_name,user_name,agency_phone,user_phone,report_state from report,user,agency where report.agency_id = agency.agency_id and report.user_id = user.user_id")
    public List<ReportRst> queryReportRstList(String content)throws Exception;
    /**
     * 处理完投诉
     * @param ids
     * @throws Exception
     */
    @Update("update report set report_state=1 where report_id in(${ids})")
    public void dealReport(String ids) throws Exception;
    /**
     * 评论审核不通过
     * @param ids
     * @throws Exception
     */
    @Update("update comment set comment_state=3 where comment_id in(${ids})")
    public void banComment(String ids) throws Exception;

    /**
     * 评论审核通过
     * @param ids
     * @throws Exception
     */
    @Update("update comment set comment_state=2 where comment_id in(${ids})")
    public void readComment(String ids) throws Exception;

    /**
     * 封禁用户
     * @param ids
     * @throws Exception
     */
    @Update("update user set user_state='是' where user_id in(${ids})")
    public void banUser(String ids)throws Exception;

    /**
     * 解封用户
     * @param ids
     * @throws Exception
     */
    @Update("update user set user_state='否' where user_id in(${ids})")
    public void unbanUser(String ids)throws Exception;

//    /**
//     * 联表查询report-user-agency
//     */
//     public List<Report>



}
