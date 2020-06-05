import com.google.gson.annotations.SerializedName

data class PhotoResponse (

	@SerializedName("id") val id : String,
	@SerializedName("created_at") val created_at : String,
	@SerializedName("updated_at") val updated_at : String,
	@SerializedName("promoted_at") val promoted_at : String,
	@SerializedName("width") val width : Int,
	@SerializedName("height") val height : Int,
	@SerializedName("color") val color : String,
	@SerializedName("description") val description : String,
	@SerializedName("alt_description") val alt_description : String,
	@SerializedName("urls") val urls : Urls,
	@SerializedName("links") val links : Links,
	@SerializedName("categories") val categories : List<String>,
	@SerializedName("likes") val likes : Int,
	@SerializedName("liked_by_user") val liked_by_user : Boolean,
	@SerializedName("current_user_collections") val current_user_collections : List<String>,
	@SerializedName("sponsorship") val sponsorship : Sponsorship,
	@SerializedName("user") val user : User
)