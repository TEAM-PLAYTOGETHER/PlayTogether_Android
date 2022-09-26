package com.playtogether_android.app.util

import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.playtogether_android.app.R
import com.playtogether_android.app.di.PlayTogetherApplication
import com.playtogether_android.app.presentation.ui.home.ThunderDetailActivity
import com.playtogether_android.data.singleton.PlayTogetherRepository
import timber.log.Timber
import kotlin.math.roundToInt

fun Context.getPageTransformer(): ViewPager2.PageTransformer {
    val compositePageTransformer = CompositePageTransformer()
    compositePageTransformer.addTransformer(MarginPageTransformer((20 * resources.displayMetrics.density).roundToInt()))

    return compositePageTransformer
}

fun Context.shortToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.viewPagerAnimation(viewpager: ViewPager2) {
    val compositePageTransformer = getPageTransformer()
    with(viewpager) {
        clipToPadding = false
        clipChildren = false
        offscreenPageLimit = 1
        setPageTransformer(compositePageTransformer)
        setPadding(
            (20 * resources.displayMetrics.density).roundToInt(),
            0,
            (55 * resources.displayMetrics.density).roundToInt(),
            0
        )
        getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }
}

fun Context.imageNullCheck(image: String?, imageView: ImageView) {
    if (image == null) {
        Glide
            .with(this)
            .load(R.drawable.ic_img_profile)
            .into(imageView)
    } else {
        Glide
            .with(this)
            .load(image)
            .into(imageView)
    }
}

fun Context.stringListBuilder(context: Context, stringList: List<String?>): String {
    val sb = StringBuilder()

    for (it in stringList) {
        if (it.isNullOrEmpty()) {
            sb.append("미정")
        } else if (it == "0") {
            sb.append(context.getString(R.string.createthunder_infinite))
        } else {
            sb.append(it)
        }
    }
    return sb.toString()
}


fun Context.applyOpenChecker(context: Context, thunderId: Int, isApply: Boolean, isOpen: Boolean) {
    val intent = Intent(context, ThunderDetailActivity::class.java)
    Timber.e("apply item : $isApply")
    Timber.e("open item : $isOpen")
    if (isOpen && isApply) intent.putExtra("category", "open")
    else if (isApply && !isOpen) intent.putExtra("category", "apply")
    else intent.putExtra("category", "default")
    intent.putExtra("thunderId", thunderId)
    startActivity(intent)
}

fun Context.showCustomPopUp(
    view: View,
    arrayInt: Int,
    baseContext: Context
): ListPopupWindow {

    val items = this.resources.getStringArray(arrayInt)
    val popupAdapter =
        object : ArrayAdapter<String>(baseContext, R.layout.item_detail, items) {
            override fun getView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view = super.getView(position, convertView, parent)
                val color = if (position == 0) {
                    R.color.gray_black
                } else {
                    R.color.gray_black
                }
                (view as TextView).setTextColor(ContextCompat.getColor(context, color))
                return view
            }
        }

    val popup = ListPopupWindow(this).apply {
        anchorView = view
        setAdapter(popupAdapter)
        setDropDownGravity(Gravity.NO_GRAVITY)
        width = measureContentWidth(baseContext, popupAdapter)
        height = ListPopupWindow.WRAP_CONTENT
        isModal = true
        val screenWidth = PlayTogetherApplication.pixelRatio.screenWidth / 5
        val screenWidthStr = "-$screenWidth"
        horizontalOffset = screenWidthStr.toInt()
        verticalOffset = -20

        setBackgroundDrawable(ContextCompat.getDrawable(baseContext, R.drawable.img_popup))
    }
    return popup
}