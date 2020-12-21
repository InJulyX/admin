package com.admin.entity.telegram;

import lombok.Data;

/*
 * Author: InJuly
 * time  : 2020/12/21 2:29
 */
@Data
public class Poll {
    private String id;
    private String question;
    private PollOption[] options;
    private Integer total_voter_count;
    private Boolean is_closed;
    private Boolean is_anonymous;
    private String type;
    private Boolean allows_multiple_answers;
    private Integer correct_option_id;
    private String explanation;
    private MessageEntity[] explanation_entities;
    private Integer open_period;
    private Integer close_date;
}
