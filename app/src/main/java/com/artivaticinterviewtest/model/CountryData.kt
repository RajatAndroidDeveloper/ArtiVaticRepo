package com.artivaticinterviewtest.model

import com.google.gson.annotations.SerializedName

data class CountryData(
	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("rows")
	val rows: ArrayList<RowsItem?>? = null
)

data class RowsItem(
	@field:SerializedName("imageHref")
	val imageHref: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)
