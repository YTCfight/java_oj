package compile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Answer {
    // 通过 error 表示当前的错误类型
    // 约定 error 为 0，表示没有错误， error 为 1，表示编译错误，error 为 2 表示运行错误
    private int error;
    // 表示具体的出错原因，可能是编译错误，也可能是运行错误（异常信息）
    private String reason;
    // 执行时标准输出对应的内容
    private String stdout;
    // 执行时标准错误对应的内容
    private String stderr;
}
