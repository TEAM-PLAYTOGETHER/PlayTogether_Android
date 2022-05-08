package com.playtogether_android.app.presentation.ui.message

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ChatViewHolder<in T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item:T)
}