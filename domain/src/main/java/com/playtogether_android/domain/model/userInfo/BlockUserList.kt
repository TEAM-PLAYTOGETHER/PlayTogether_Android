package com.playtogether_android.domain.model.userInfo

import com.google.gson.annotations.SerializedName

data class BlockUserList(
    val data: List<Block>
) {
    data class Block(
        @SerializedName("userId")
        val userId: Int,
        @SerializedName("blockUserId")
        val blockUserId: Int,
        @SerializedName("name")
        val name: String
    )
}





