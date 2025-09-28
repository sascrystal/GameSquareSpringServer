package com.square_game_server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ConfigurationProperties(prefix = "bot.priority")
@Data
public class BotPriorityConfig {
    private int pointsForFriendlyPiece0;
    private int pointsForFriendlyPiece1;
    private int pointsForFriendlyPiece2;
    private int pointsForEnemyPiece0;
    private int pointsForEnemyPiece1;
    private int pointsForEnemyPiece2;
}
