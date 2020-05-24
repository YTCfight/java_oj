package problem;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 实体类，每个 Problem 对象对应到 oj_table 中的一条记录
@Getter
@Setter
@ToString
public class Problem {

    private int id;
    private String title;
    private String level;
    private String description;
    private String templateCode;
    private String testCode;
}
