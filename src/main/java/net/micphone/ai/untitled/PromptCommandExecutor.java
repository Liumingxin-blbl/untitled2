package net.micphone.ai.untitled;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.aigc.generation.models.QwenParam;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.Constants;

public class PromptCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                // 将命令参数合并为一个字符串
                String prompt = String.join(" ", args);

                // 调用 DashScope API 生成文本
                try {
                    Constants.apiKey = "sk-8fd0069be6154336884c96d90c11f472"; // 确保这里使用了有效的 API key
                    Generation gen = new Generation();
                    QwenParam param = QwenParam.builder()
                            .model(Generation.Models.QWEN_TURBO) // 选择合适的模型
                            .prompt(prompt)
                            .topP(0.8)
                            .build();

                    GenerationResult result = gen.call(param);
                    if (result.getOutput() != null && result.getOutput().getText() != null) {
                        player.sendMessage("生成的文本: " + result.getOutput().getText());
                    } else {
                        player.sendMessage("未能获取到生成的文本。");
                    }
                } catch (NoApiKeyException | ApiException | InputRequiredException e) {
                    player.sendMessage("在调用 DashScope API 时出现错误: " + e.getMessage());
                }

                return true;
            } else {
                player.sendMessage("请提供一个 prompt。");
                return false;
            }
        }
        return false;
    }
}