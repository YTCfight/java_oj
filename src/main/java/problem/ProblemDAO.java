package problem;

import common.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// 数据访问层
public class ProblemDAO {

    // 获取所有题目信息
    public List<Problem> selectAll() {
        List<Problem> result = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        String sql = "select * from oj_table";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Problem problem = new Problem();
                problem.setId(resultSet.getInt("id"));
                problem.setTitle(resultSet.getString("title"));
                problem.setLevel(resultSet.getString("level"));
//                problem.setTemplateCode(resultSet.getString("templateCode"));
//                problem.setTestCode(resultSet.getString("testCode"));
//                problem.setDescription(resultSet.getString("description"));
                result.add(problem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return result;
    }

    // 获取指定 id 题目信息
    public Problem selectOne(int id) {
        Connection connection = DBUtil.getConnection();
        String sql = "select * from oj_table where id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Problem problem = new Problem();
                problem.setId(resultSet.getInt("id"));
                problem.setTitle(resultSet.getString("title"));
                problem.setLevel(resultSet.getString("level"));
                problem.setTemplateCode(resultSet.getString("templateCode"));
                problem.setTestCode(resultSet.getString("testCode"));
                problem.setDescription(resultSet.getString("description"));
                return problem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return null;
    }

    // 新增一个题目到数据库中
    public void insert(Problem problem) {
        // 1.获取数据库连接
        Connection connection = DBUtil.getConnection();
        // 2.构建 SQL 语句
        String sql = "insert into oj_table values(null, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, problem.getTitle());
            statement.setString(2, problem.getLevel());
            statement.setString(3, problem.getDescription());
            statement.setString(4, problem.getTemplateCode());
            statement.setString(5, problem.getTestCode());
            // 3.执行 SQL 语句
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 4.关闭释放相关资源
            DBUtil.close(connection, statement, null);
        }
    }

    // 删除指定题目信息
    public void delete(int id) {
        Connection connection = DBUtil.getConnection();
        String sql = "delete from oj_table where id = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, null);
        }

    }

    public static void main(String[] args) {
        // 1. 验证 insert 操作
        Problem problem = new Problem();
        problem.setTitle("各位相加");
        problem.setLevel("简单");
        problem.setDescription("给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。\n" +
                "\n" +
                "示例:\n" +
                "\n" +
                "输入: 38\n" +
                "输出: 2 \n" +
                "解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。\n");
        problem.setTemplateCode("public class Solution {\n" +
                "    public int addDigits(int num) {\n" +
                "\n" +
                "    }\n" +
                "}");
        problem.setTestCode(
                "    public static void main(String[] args) {\n" +
                "        Solution s = new Solution();\n" +
                "        if (s.addDigits(38) == 2) {\n" +
                "            System.out.println(\"test OK\");\n" +
                "        } else {\n" +
                "            System.out.println(\"test failed\");\n" +
                "        }\n" +
                "        \n" +
                "        if (s.addDigits(1) == 1) {\n" +
                "            System.out.println(\"test OK\");\n" +
                "        } else {\n" +
                "            System.out.println(\"test failed\");\n" +
                "        }\n" +
                "    }\n");
        ProblemDAO problemDAO = new ProblemDAO();
        problemDAO.insert(problem);
        System.out.println("insert OK");

        // 2.测试 selectAll
//        ProblemDAO problemDAO = new ProblemDAO();
//        List<Problem> problems = problemDAO.selectAll();
//        System.out.println("selectAll OK");

        // 3.测试 selectOne
//        ProblemDAO problemDAO = new ProblemDAO();
//        System.out.println(problemDAO.selectOne(1));
        // 4. 测试 delete
//        ProblemDAO problemDAO = new ProblemDAO();
//        problemDAO.delete(1);
    }
}
