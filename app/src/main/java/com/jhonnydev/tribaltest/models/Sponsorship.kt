package com.jhonnydev.tribaltest.models

import com.google.gson.annotations.SerializedName

data class Sponsorship (

	@SerializedName("impression_urls") val impression_urls : List<String>,
	@SerializedName("tagline") val tagline : String,
	@SerializedName("tagline_url") val tagline_url : String,
	@SerializedName("sponsor") val sponsor : Sponsor
)