package indivus.cosmos.presenter;

import indivus.cosmos.model.server.reply.ReplyDetailResult;
import indivus.cosmos.model.server.reply.ReplyResult;

/**
 * Created by seowo on 2017-07-04.
 */

public interface ReplyCallBack {
    void getReply(ReplyResult result);
    void sendReply();

    void getReplyDetail(ReplyDetailResult result);
    void sendReplyDetail();
}
