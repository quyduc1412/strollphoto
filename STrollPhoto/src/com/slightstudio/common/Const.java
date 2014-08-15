package com.slightstudio.common;


public interface Const {

	public static final String JMAGAZINE = "jmaggram";
	public static final String DEBUG_TAG = JMAGAZINE;
	
	public static final String PREFERENCES_CODE = "jmagazine_prefs";
	
	public static final String SERVER_URL = "http://jmobstudio.com/jmaggram";
	public static final String SERVER_TEMPLATE_URL = SERVER_URL + "/templates";
	public static final String SERVER_MEDIA_URL = SERVER_URL + "/media";
	public static final String VERSION_URL = SERVER_URL + "/templates/version.json";
	public static final String MORE_STICKER_URL = SERVER_URL + "/stickers/stickers.json";
	
	public static final String AVIARY_SECRET_KEY = "a96bf0fc6c3cda76";
	public static final String FACEBOOK_APP_ID = "1451521288412568";
	
	public static final String TEST_DEVICE_ID = "317DDB219D9A054D3550F4A0A829E487";
	public static final String SHARE_INTERSTITIAL_AD_UNIT_ID = "ca-app-pub-3538686508999563/6029085730";

	
	public static final String TWITTER_CONSUMER_KEY = "gyRaYuqSHv1gbUoeJjF3UKhYr";
	public static final String TWITTER_CONSUMER_SECRET = "6uGOMgdwHJvUG8Ga0Cwi7Ojf1RUxlQEDdZcaOM0LDDCS6ylmJg";
	
	public static final String CAMERA_MODE_FRONT = "front";
	public static final String CAMERA_MODE_REAR = "rear";
	
	public static final String TEMPLATE_CATEGORY = "template_category";
	public static final String TEMPLATE_CATEGORY_CLASSIC = "classic";
	public static final String TEMPLATE_CATEGORY_MAGAZINE = "magazine";
	public static final String TEMPLATE_CATEGORY_FRAME = "frame";
	public static final String TEMPLATE_CATEGORY_RECENT = "recent";

	public static final String TEMPLATE_TYPE = "template_type";
	public static final String TEMPLATE_TYPE_PORTRAIT = "portrait";
	public static final String TEMPLATE_TYPE_SQUARE = "square";
	public static final String TEMPLATE_TYPE_LANDSCAPE = "landscape";
	public static final String TEMPLATE_TYPE_DOWNLOAD = "download";
	
	public static final String ITEM_TYPE_IMAGE = "image";
	public static final String ITEM_TYPE_TEXT = "text";
	public static final String ITEM_TYPE_WEATHER = "weather";
	public static final String ITEM_TYPE_DATETIME = "datetime";
	public static final String ITEM_TYPE_LOCATION = "location";
	public static final String ITEM_TYPE_COLOR = "color";
	public static final String ITEM_TYPE_POLYGON = "polygon";
	public static final String ITEM_TYPE_RECTANGLE = "rectangle";
	public static final String ITEM_TYPE_CIRCLE = "circle";
	
	public static final String SELECTED_TEMPLATE = "selected_template";
	public static final String SELECTED_ALBUM = "selected_album";
	public static final String SELECTED_PHOTOS = "selected_photos";
	public static final String SELECTED_PHOTO = "selected_photo";
	
	public static final String NUMBER_OF_COLUMN = "number_of_column";
	
	public static final int DEFAULT_STICKER_WIDTH = (int)UIUtils.dp2px(150);
	public static final int DEFAULT_STICKER_HEIGHT = (int)UIUtils.dp2px(150);
	
	public static final int DEFAULT_NUMBER_OF_COLUMN = 2;
	public static final int DEFAULT_CLASSIC_NUMBER_OF_COLUMN = 4;
	
	public static final String GOOGLE_GEOCODING_BY_LOCATION = "http://maps.googleapis.com/maps/api/geocode/json?latlng=%f,%f&sensor=true";
	
	public static final String FOURSQUARE_BY_LOCATION = "https://api.foursquare.com/v2/venues/search?ll=%f,%f&client_id=%s&client_secret=%s&v=20111119";
	public static final String FOURSQUARE_BY_QUERY = "https://api.foursquare.com/v2/venues/search?ll=%f,%f&query=%s&client_id=%s&client_secret=%s&v=20111119";
	
	public static final String CLIENT_ID = "VSBAWGZFXNUCZMRYTLJSS33LOAK3ZFGBNZYGWYQWFUPVG1D5";
	public static final String CLIENT_SECRET = "1UXFN2ZHUJ3SMTTQ1GYBNMR0RFPX4HSYZZNLX5JBK1SGFXDK";
	
	public static final String DEFAULT_SHARE_CAPTION = "I've uploaded a photo using amazing #jmagazine android app. Get it for FREE at http://jmobstudio.com/jmagazine";
	public static final String DEFAULT_TEXT_FONT = "I Love jMaggram";
	
	public static final String SERVER_FONT_URL = "http://jmobstudio.com/jmaggram/media/fonts/list.json";
	public static final String DIR_FONT = "jmaggram-font";
}
