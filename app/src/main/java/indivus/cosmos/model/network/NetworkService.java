package indivus.cosmos.model.network;

import java.util.ArrayList;
import java.util.List;

import indivus.cosmos.model.server.MessageResult;
import indivus.cosmos.model.server.closet.ClosetResult;
import indivus.cosmos.model.server.collection.CollectionData;
import indivus.cosmos.model.server.collection.CollectionNameData;
import indivus.cosmos.model.server.collection.CollectionResult;
import indivus.cosmos.model.server.create.CreateResult;
import indivus.cosmos.model.server.explorer.ExplorerContentResult;
import indivus.cosmos.model.server.explorer.ExplorerCreatorResult;
import indivus.cosmos.model.server.explorer.ExplorerData;
import indivus.cosmos.model.server.follow.FollowerResult;
import indivus.cosmos.model.server.follow.FollowingIdData;
import indivus.cosmos.model.server.like.CommentDetailLikeData;
import indivus.cosmos.model.server.like.CommentLikeData;
import indivus.cosmos.model.server.like.LikeResult;
import indivus.cosmos.model.server.like.PostLikeData;
import indivus.cosmos.model.server.login.LoginData;
import indivus.cosmos.model.server.login.LoginResult;
import indivus.cosmos.model.server.notice.NoticeIdData;
import indivus.cosmos.model.server.notice.NoticeResult;
import indivus.cosmos.model.server.post.CardCountData;
import indivus.cosmos.model.server.post.CardCountResult;
import indivus.cosmos.model.server.post.HomeCardResult;
import indivus.cosmos.model.server.post.PostCategoryResult;
import indivus.cosmos.model.server.post.PostData;
import indivus.cosmos.model.server.post.PostResult;
import indivus.cosmos.model.server.series.SeriesData;
import indivus.cosmos.model.server.profile.ProfileResult;
import indivus.cosmos.model.server.reply.ReplyDetailData;
import indivus.cosmos.model.server.reply.ReplyDetailResult;
import indivus.cosmos.model.server.reply.ReplyMessage;
import indivus.cosmos.model.server.reply.ReplyPostId;
import indivus.cosmos.model.server.reply.ReplyResult;
import indivus.cosmos.model.server.reply.ReplyWrite;
import indivus.cosmos.model.server.series.SeriesResult;
import indivus.cosmos.model.server.signup.CategoryResult;
import indivus.cosmos.model.server.signup.CheckResult;
import indivus.cosmos.model.server.signup.EmailCheckData;
import indivus.cosmos.model.server.signup.NameCheckData;
import indivus.cosmos.model.server.signup.SelectCategoryData;
import indivus.cosmos.model.server.signup.SelectCategoryResult;
import indivus.cosmos.model.server.signup.SignUpData;
import indivus.cosmos.model.server.signup.SignUpResult;
import indivus.cosmos.model.server.workroom.CreateWorkRoomSeriesData;
import indivus.cosmos.model.server.workroom.WorkRoomResult;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by seowo on 2017-06-25.
 */

public interface NetworkService {

    /**
    *<LOGIN>
    */
    //로그인
    @POST("/login")
    Call<LoginResult> tryLogin(@Body LoginData login_data);

    /**
     *<SIGNUP>
     */
    //이메일 체크
    @POST("/signup/check")
    Call<CheckResult> checkEmail(@Body EmailCheckData email);
    //닉네임 체크
    @POST("/signup/check")
    Call<CheckResult> checkName(@Body NameCheckData username);
    //회원가입->카테고리 선택
    @POST("/signup")
    Call<SignUpResult> signUpNext(@Body SignUpData sign_up_data);
    //카테고리 선택 목록 받아옴
    @GET("/selectCategory")
    Call<CategoryResult> getCategory();
    //카테고리 선택 완료
    @POST("/selectCategory")
    Call<SelectCategoryResult> signUp(@Body SelectCategoryData category_data);

    /**
     *<HOMEFEED>
     */
    //Curation card 목록 받아옴
    @GET("/category-feed")
    Call<HomeCardResult> getCurationCardResult(@Header("Authorization") String authorization);
    //Following card 목록 받아옴
    @GET("/follow-feed")
    Call<HomeCardResult> getFollowingCardResult(@Header("Authorization") String authorization);

    /**
     *<LIKE>
     */
    //post like 누름
    //like success, none like success
    @POST("/like")
    Call<LikeResult> clickPostLike(@Header("Authorization") String authorization, @Body PostLikeData data);
    @POST("/like")
    Call<LikeResult> clickCommentLike(@Header("Authorization") String authorization, @Body CommentLikeData data);
    @POST("/like")
    Call<LikeResult> clickCommentDetailLike(@Header("Authorization") String authorizatioin, @Body CommentDetailLikeData data);


    //Collect 누름
    @POST("/post")
    Call<CardCountResult> clickCollect(@Header("Authorization") String authorization, @Body CardCountData click);

    /**
     * <COMMENT>
     */
    //Comment 누름
    @POST("/comment/view")
    Call<ReplyResult> getReply(@Header("Authorization") String authorization, @Body ReplyPostId post_id);
    //Comment 작성
    @POST("/comment/write")
    Call<ReplyMessage> writeReply(@Header("Authorization") String authorization, @Body ReplyWrite reply_content);
    //Comment comment 작성
    @POST("/comment_detail/view")
    Call<ReplyDetailResult> getReplyDetail(@Header("Authorization") String authorization, @Body ReplyDetailData comment_id);

    /**
     *<POST DETAIL>
     */
    //해당 포스트 콘텐츠 받아옴
    @POST("/post/view")
    Call<PostResult> getPostContent(@Header("Authorization") String authorization, @Body PostData post_data);

    /**
     *<SERIES>
     */
    //해당 시리즈 받아오기
    @POST("/series-detail/view")
    Call<SeriesResult> getPostSeries(@Header("Authorization") String authorization, @Body SeriesData series_name);
    //단편 목록 받아오기
    @GET("/series-detail/view")
    Call<SeriesResult> getPostSingle(@Header("Authorization") String authorization);

    /**
     *<PROFILE>
     */
    @GET("/profile/view")
    Call<ProfileResult> getProfile(@Header("Authorization") String authorization);

    /**
     *<CREATE>
     */
    @Multipart
    @POST("/post/write")
    Call<CreateResult> createPost(@Header("Authorization") String authorization, @Part("title") RequestBody title
            , @Part("sub_title")RequestBody sub_title
            , @Part("explain")RequestBody explain
            , @Part("category_id")RequestBody category_id
            , @Part("keycard") List<RequestBody> keycard
            , @Part("comment")RequestBody comment
            , @Part("content_type")RequestBody content_type
            , @Part MultipartBody.Part card_cover
            , @Part("contents") List<RequestBody> contents
            , @Part List<MultipartBody.Part> files
            , @Part("each_content_type")RequestBody each_content_type);

    /**
     *<CATEGORY & KEYCARD>
     */
    @GET("/list/all-list")
    Call<PostCategoryResult> getCategories(@Header("Authorization") String authorization);

    /**
     *<EXPLORER>
     */
    @POST("/search/title-search")
    Call<ExplorerContentResult> searchExplorerContent(@Header("Authorization") String authorization, @Body ExplorerData explorer_data);

    @POST("/search/creator-search")
    Call<ExplorerCreatorResult> searchExplorerCreator(@Header("Authorization") String authorization, @Body ExplorerData explorer_data);

    /**
     *<WORKROOM>
     */
    @GET("/series/view")
    Call<WorkRoomResult> getMyWorkRoom(@Header("Authorization") String authorization);
    @POST("/series/create")
    Call<MessageResult> createWorkRoomSeries(@Header("Authorization") String authorization, @Body CreateWorkRoomSeriesData create_data);

    /**
     *<NOTICE>
     */
    @GET("/notice/")
    Call<NoticeResult> getNotice(@Header("Authorization") String authorization);
    @POST("/notice/")
    Call<MessageResult> removeNotice(@Header("Authorization") String authorization, @Body NoticeIdData data);

    /**
     *<COLLECTION>
     */
    @GET("/collection/view")
    Call<CollectionResult> getCollection(@Header("Authorization") String authorization);
    @POST("/collection-detail/view")
    Call<SeriesResult> getCollectionDetail(@Header("Authorization") String authorization, @Body CollectionData collection_id);
    @POST("collection/new")
    Call<MessageResult> createCollection(@Header("Authorization") String authorization, @Body CollectionNameData collection_name);

    /**
     *<CLOSET>
     */
    @GET("/closet/view")
    Call<ClosetResult> getCloset(@Header("Authorization") String authorization);

    /**
     *<FOLLOW & FOLLOWING>
     */
    @POST("/friends/follow")
    Call<MessageResult> follow(@Header("Authorizatioin") String authorization, @Body FollowingIdData following_id);
    @GET("/friends/follower/list")
    Call<FollowerResult> getFollower(@Header("Authorization") String authorization);
    @GET("/friends/following/list")
    Call<FollowerResult> getFollowing(@Header("Authorization") String authorizatioin);
}

