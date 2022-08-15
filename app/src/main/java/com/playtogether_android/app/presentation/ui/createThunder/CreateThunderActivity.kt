package com.playtogether_android.app.presentation.ui.createThunder

import android.Manifest
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityCreateThunderBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.createThunder.adapter.CreateThunderPhotoListAdapter
import com.playtogether_android.app.presentation.ui.thunder.OpenThunderDetailActivity
import com.playtogether_android.app.util.SpacesItemDecorationPhoto
import com.playtogether_android.app.util.shortToast
import com.playtogether_android.domain.model.thunder.PostThunderCreateData
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.*

@AndroidEntryPoint
class CreateThunderActivity :
    BaseActivity<ActivityCreateThunderBinding>(R.layout.activity_create_thunder) {
    private var imageClicked = false
    private var infiniteChecked = false
    private val createThunderViewModel: CreateThunderViewModel by viewModels()
    private lateinit var inputMethodManager: InputMethodManager
    private lateinit var category: String
    private val galleryItemList = mutableListOf<Uri>()
    private lateinit var intentLauncher: ActivityResultLauncher<Intent>
    private lateinit var photoListAdapter: CreateThunderPhotoListAdapter
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                Timber.d("권한 승인")
                uploadImageListener()
            } else
                shortToast("권한요청이 거절되었습니다.")
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.createThunderViewModel = createThunderViewModel
        binding.lifecycleOwner = this
        initAdapter()
        setTextChangeListener()
        setClickListener()
        uploadImageCallbackListener()
    }

    private fun initAdapter() {
        photoListAdapter = CreateThunderPhotoListAdapter(createThunderViewModel)
        //todo 스크롤이 원하는 방향으로 가지 않는다.

        createThunderViewModel.photoList.observe(this) {
            photoListAdapter.mutablePhotoList = it
            photoListAdapter.notifyDataSetChanged()
        }

        with(binding.rvCreatethunderPhotoContainer) {
            layoutManager =
                GridLayoutManager(this@CreateThunderActivity, 2, LinearLayout.VERTICAL, false)
            adapter = photoListAdapter
            addItemDecoration(SpacesItemDecorationPhoto())
        }
    }

    private fun uploadImageCallbackListener() {
        inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        intentLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == RESULT_OK) {
                Timber.d("resultCode : OK")
                galleryItemList.clear()
                if (it.data?.clipData != null) {
                    val count = it.data?.clipData!!.itemCount
                    if (count > 3) {
                        shortToast("사진은 최대 3장까지 가능합니다.")
                        return@registerForActivityResult
                    }
                    for (i in 0 until count) {
                        val imageUri = it.data?.clipData!!.getItemAt(i).uri
                        galleryItemList.add(imageUri)
                    }
                } else {
                    it.data?.data?.let { uri ->
                        val imageUri = it.data?.data
                        if (imageUri != null) {
                            galleryItemList.add(imageUri)
                        }
                    }
                }
                Timber.e("rere list : $galleryItemList")
                createThunderViewModel.setPhotoList(galleryItemList)
            }
        }
    }

    private fun setTextChangeListener() {
        initDatePickerDialog()
        initTimePickerDialog()
        checkTunderName()
        checkThunderPlace()
        checkTunderMember()
        checkTunderExplanation()
    }

    private fun setClickListener() {
        imageSelected()
        clickInfinite()
        clickComplete()
        imageSelected()
        uploadPhotoClickListener()
    }

    private fun uploadPhotoClickListener() {
        binding.clCreatethunderPhoto.setOnClickListener {
            aboutPermission()
        }
    }

    private fun aboutPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            uploadImageListener()
            Timber.e("rere permission ok")
        } else if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_DENIED
        ) {
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private fun uploadImageListener() {
        Intent(Intent.ACTION_PICK).apply {
            type = MediaStore.Images.Media.CONTENT_TYPE
            data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            setResult(RESULT_OK)
            intentLauncher.launch(this)
        }
    }

    private fun clickComplete() {
        binding.tvCreatethunderFinish.setOnClickListener {
            val date = binding.tvCreatethunderDate.text.toString().replace(".", "-")
            val time = binding.tvCreatethunderTime.text.toString()
            val title = binding.etCreatethunderName.text.toString()
            val place = binding.etCreatethunderPlace.text.toString()
            var peopleCnt = 0
            if (binding.etCreatethunderPeopleNumber.text.toString() == resources.getString(R.string.createthunder_infinite))
                peopleCnt = -1
            else
                peopleCnt = binding.etCreatethunderPeopleNumber.text.toString().toInt()
            val description = binding.etCreatethunderExplanation.text.toString()
            createThunderViewModel.postThunderCreate(
                PostThunderCreateData(
                    title,
                    category,
                    date,
                    time,
                    place,
                    peopleCnt,
                    description
                )
            )
//            Log.d("createThunder", title)
//            Log.d("createThunder", category)
//            Log.d("createThunder", date)
//            Log.d("createThunder", time)
//            Log.d("createThunder", place)
//            Log.d("createThunder", "$peopleCnt")
//            Log.d("createThunder", description)
        }

        createThunderViewModel.getThunderCreateData.observe(this) {
            if (it.success) {
                val intent = Intent(this, OpenThunderDetailActivity::class.java)
                intent.putExtra("thunderId", it.lightId)
                startActivity(intent)
                finish()
            } else {
                Log.d("createThunder", "번개 생성 안됨")
            }
        }
    }

    private fun clickInfinite() {
        binding.ivCreatethunderCheck.setOnClickListener {
            if (!infiniteChecked) {
                binding.ivCreatethunderCheck.setImageResource(R.drawable.ic_icn_check_active)
                val infiniteText = resources.getString(R.string.createthunder_infinite)
                binding.etCreatethunderPeopleNumber.setText(infiniteText)
                inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                binding.etCreatethunderPeopleNumber.isFocusable = false
                infiniteChecked = true
            } else {
                binding.ivCreatethunderCheck.setImageResource(R.drawable.ic_icn_check_inactive)
                binding.etCreatethunderPeopleNumber.text.clear()
                binding.etCreatethunderPeopleNumber.isFocusableInTouchMode = true
                binding.etCreatethunderPeopleNumber.requestFocus()
                inputMethodManager.showSoftInput(
                    binding.etCreatethunderPeopleNumber,
                    InputMethodManager.SHOW_IMPLICIT
                )
                infiniteChecked = false
            }
        }
    }

    private fun btnActive() {
        with(binding) {
            if (etCreatethunderName.text.toString() != "" && imageClicked && tvCreatethunderDate.text.toString() != "" && tvCreatethunderTime.text.toString() != "" && etCreatethunderPlace.text.toString() != "" && etCreatethunderPeopleNumber.text.toString() != "" && etCreatethunderExplanation.text.toString() != "") {
                tvCreatethunderFinish.isClickable = true
                tvCreatethunderFinish.setTextColor(
                    ContextCompat.getColor(
                        this@CreateThunderActivity,
                        R.color.main_green
                    )
                )
            } else {
                tvCreatethunderFinish.isClickable = false
                tvCreatethunderFinish.setTextColor(
                    ContextCompat.getColor(
                        this@CreateThunderActivity,
                        R.color.gray_gray01
                    )
                )
            }
        }
    }

    private fun checkTunderName() {
        binding.etCreatethunderName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                btnActive()
            }
        })
    }

    private fun checkThunderPlace() {
        binding.etCreatethunderPlace.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                btnActive()
            }
        })
    }

    private fun checkTunderMember() {
        binding.etCreatethunderPeopleNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                btnActive()
            }
        })
    }

    private fun checkTunderExplanation() {
        binding.etCreatethunderExplanation.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                btnActive()
            }
        })
    }

    private fun imageSelected() {
        with(binding) {
            ivCreatethunderDo.setOnClickListener {
                ivCreatethunderDo.setImageResource(R.drawable.ic_img_do_active)
                ivCreatethunderDo.isSelected = true
                ivCreatethunderGo.setImageResource(R.drawable.ic_img_go_inactive)
                ivCreatethunderGo.isSelected = false
                ivCreatethunderEat.setImageResource(R.drawable.ic_img_eat_inactive)
                ivCreatethunderEat.isSelected = false
                imageClicked = true
                category = "할래"
                btnActive()
            }
            ivCreatethunderGo.setOnClickListener {
                ivCreatethunderDo.setImageResource(R.drawable.ic_img_do_inactive)
                ivCreatethunderDo.isSelected = false
                ivCreatethunderGo.setImageResource(R.drawable.ic_img_go_active)
                ivCreatethunderGo.isSelected = true
                ivCreatethunderEat.setImageResource(R.drawable.ic_img_eat_inactive)
                ivCreatethunderEat.isSelected = false
                imageClicked = true
                category = "갈래"
                btnActive()
            }
            ivCreatethunderEat.setOnClickListener {
                ivCreatethunderDo.setImageResource(R.drawable.ic_img_do_inactive)
                ivCreatethunderDo.isSelected = false
                ivCreatethunderGo.setImageResource(R.drawable.ic_img_go_inactive)
                ivCreatethunderGo.isSelected = false
                ivCreatethunderEat.setImageResource(R.drawable.ic_img_eat_active)
                ivCreatethunderEat.isSelected = true
                imageClicked = true
                category = "먹을래"
                btnActive()
            }
        }
    }

    private fun initDatePickerDialog() {
        binding.tvCreatethunderDate.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()

            val datePickerDialog = DatePickerDialog(
                this,
                R.style.MySpinnerDatePickerStyle,
                { _, year, month, day ->
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_YEAR, day)

                    val month = month + 1
                    val dateString = String.format("%d.%02d.%02d", year, month, day)

                    binding.tvCreatethunderDate.text = dateString
                    btnActive()
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            val textColor = ContextCompat.getColor(this, R.color.gray_black)
            datePickerDialog.show()
            datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(textColor)
            datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(textColor)
        }
    }

    private fun initTimePickerDialog() {
        binding.tvCreatethunderTime.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                val timeString = String.format("%02d:%02d", hourOfDay, minute)
                binding.tvCreatethunderTime.text = timeString
                btnActive()
            }
            val timePickerDialog = TimePickerDialog(
                this,
                R.style.TimePickerTheme,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                false
            )
            val textColor = ContextCompat.getColor(this, R.color.gray_black)
            timePickerDialog.show()
            timePickerDialog.getButton(TimePickerDialog.BUTTON_POSITIVE).setTextColor(textColor)
            timePickerDialog.getButton(TimePickerDialog.BUTTON_NEGATIVE).setTextColor(textColor)
        }
    }

    companion object {
        const val MAX_PICTURE_COUNT = 5
    }
}