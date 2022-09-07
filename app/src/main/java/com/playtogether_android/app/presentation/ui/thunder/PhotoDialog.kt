import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.FragmentPhotoDialogBinding
import com.playtogether_android.app.presentation.base.BaseBottomDialogFragment
import kotlin.math.roundToInt

class PhotoDialog(private val image: String) :
    BaseBottomDialogFragment<FragmentPhotoDialogBinding>(R.layout.fragment_photo_dialog) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        binding.clPhotoContainer.layoutParams.height =
            (resources.displayMetrics.heightPixels * 0.99).roundToInt()

        (dialog as? BottomSheetDialog)?.behavior?.apply {
            addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_DRAGGING)
                        state = BottomSheetBehavior.STATE_EXPANDED
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                }
            })
            isFitToContents = true
            state = BottomSheetBehavior.STATE_EXPANDED
        }
        binding.ivPhotolargeExit.setOnClickListener {
            dialog?.dismiss()
        }

        Glide
            .with(requireActivity())
            .load(image)
            .into(binding.ivPhotolargeImage)
    }
}