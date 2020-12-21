package com.admin.entity.telegram;

/*
 * Author: InJuly
 * time  : 2020/12/21 3:00
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Update {
    private Integer update_id;
    private Message message;
    private Message edited_message;
    private Message channel_post;
    private Message edited_channel_post;
    private InlineQuery inline_query;
    private ChosenInlineResult chosen_inline_result;
    private CallbackQuery callback_query;
    private ShippingQuery shipping_query;
    private PreCheckoutQuery pre_checkout_query;
    private Poll poll;
    private PollAnswer poll_answer;
}
