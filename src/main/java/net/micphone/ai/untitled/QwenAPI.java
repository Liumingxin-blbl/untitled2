package net.micphone.ai.untitled;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.aigc.generation.models.QwenParam;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.Constants;

import java.util.Scanner;

public class QwenAPI {
    public static void main(String[] args) {
        Constants.apiKey = "sk-8fd0069be6154336884c96d90c11f472";
        System.out.println("请输入您的prompt:");
        Scanner scanner = new Scanner(System.in);
        String userPrompt = scanner.nextLine(); // 从命令行读取用户输入

        try {
            // 初始化API调用
            Generation gen = new Generation();
            QwenParam param = QwenParam.builder()
                    .model(Generation.Models.QWEN_TURBO) // 选择模型
                    .prompt(userPrompt) // 设置用户的prompt
                    .topP(0.8)
                    .build();

            // 调用API
            GenerationResult result = gen.call(param);

            // 打印结果
            if (result.getOutput() != null && result.getOutput().getText() != null) {
                System.out.println("模型回复: " + result.getOutput().getText());
            } else {
                System.out.println("未能获取到模型回复。");
            }
        } catch (NoApiKeyException | ApiException | InputRequiredException e) {
            e.printStackTrace();
        }
    }
}

