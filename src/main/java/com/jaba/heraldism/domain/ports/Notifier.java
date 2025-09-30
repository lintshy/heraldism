package com.jaba.heraldism.domain.ports;

/**
 * Port interface for notification service (Discord/Telegram).
 */
public interface Notifier {
    
    /**
     * Send a notification message.
     *
     * @param message Message to send
     */
    void sendNotification(String message);
    
    /**
     * Send a formatted notification with title and description.
     *
     * @param title Notification title
     * @param description Notification description
     * @param color Color code for the notification
     */
    void sendFormattedNotification(String title, String description, String color);
    
    /**
     * Send match result notification.
     *
     * @param playerName Player name
     * @param matchId Match ID
     * @param isWin Whether player won
     * @param score Performance score
     */
    void sendMatchNotification(String playerName, String matchId, boolean isWin, Double score);
}
