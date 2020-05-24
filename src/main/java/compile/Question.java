package compile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Question {
    // 要编译和执行的代码内容
    private String code;
    // 执行时标准输入要输入的内容，实际上后面没用到
    private String stdin;

}
