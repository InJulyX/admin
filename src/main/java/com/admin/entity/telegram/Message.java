package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 2:17
 */

import lombok.Data;

@Data
public class Message {
    private Integer message_id;
    private User from;
    private Chat sender_chat;
    private Integer date;
    private Chat chat;
    private User forward_from;
    private Chat forward_from_chat;
    private Integer forward_from_message_id;
    private String forward_signature;
    private String forward_sender_name;
    private Integer forward_date;
    private Message reply_to_message;
    private User via_bot;
    private Integer edit_date;
    private String media_group_id;
    private String author_signature;
    private String text;
    private MessageEntity[] entities;
    private Animation animation;
    private Audio audio;
    private Document document;
    private PhotoSize[] photo;
    private Sticker sticker;
    private Video video;
    private VideoNote video_note;
    private Voice voice;
    private String caption;
    private MessageEntity[] caption_entities;
    private Contact contact;
    private Dice dice;
    private Game game;
    private Poll poll;
    private Venue venue;
    private Location location;
    private User[] new_chat_members;
    private User left_chat_member;
    private String new_chat_title;
    private PhotoSize[] new_chat_photo;
    private Boolean delete_chat_photo = true;
    private Boolean group_chat_created = true;
    private Boolean supergroup_chat_created = true;
    private Boolean channel_chat_created = true;
    private Integer migrate_to_chat_id;
    private Message pinned_message;
    private Invoice invoice;
    private SuccessfulPayment successful_payment;
    private String connected_website;
    private PassportData passport_data;
    private ProximityAlertTriggered proximity_alert_triggered;
    private InlineKeyboardMarkup reply_markup;
}
