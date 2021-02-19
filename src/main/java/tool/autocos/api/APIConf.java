package tool.autocos.api;

public class APIConf {
    public static final String LIKE = "https://cos.tv/api/v1/cos-chain-proxy/vote";
    public static final String CMT = "https://cos.tv/api/v1/cos-chain-proxy/reply";
    public static final String FOLLOW = "https://cos.tv/api/v1/feed/video_user/follow";
    public static final String UNFOLLOW = "https://cos.tv/api/v1/feed/video_user/unfollow";
    public static final String CSRF_TOKEN = "https://cos.tv/api/v1/user/common/get_csrf_token";
    public static final String CLAIM_POP = "https://cos.tv/api/v1/popcorn/videoIntegral";
    public static final String URL_ACTIVE = "https://cos.tv/subject/main/build/login.html#/login/active_email?code=";
    public static final String UPDATE_IMAGE = "https://cos.tv/api/v1/user/common/upload_image"; //image: (binary)
    public static final String UPDATE_INFO = "https://cos.tv/api/v1/user/account/update_user_info"; //{"avatar":"https://img.contentos.io/65463cd59f31f7ce00131d6990e4d205.jpg","nickname":"peceja1980","fullname":"","countryCode":"MT"}
    public static final String UP_VIDEO = "https://cos.tv/api/v1/studio/video/upload_confirm";
    //{"title":"Bi sắt 1000 độ C, hủy diệt mọi thứ","introduction":"Bi sắt 1000 độ C, hủy diệt mọi thứ","tag":"[\"16\",\"93\",\"91\",\"52\"]","tag_user":"[]","videosource":"http://1259667713.vod2.myqcloud.com/443b77b0vodhk1259667713/001c99595285890814048737588/VeKb6r3U9akA.mp4","video_cover_big":"https://img.contentos.io/894b3f2197785be467c0418b6879218f.jpg","language":"vi","privacy":"1","fileId":"5285890814048737588","file_md5":"","time_zone":"","release_time":"","topic_class":"86","type":"212","is_adults_only":"0","is_ads":"","timestamp":1613234907888}
    public static final String VIEW ="https://cos.tv/api/v1/feed/video/play_action_report";
}
