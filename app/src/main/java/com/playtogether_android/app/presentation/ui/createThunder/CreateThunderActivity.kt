package com.playtogether_android.app.presentation.ui.createThunder

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityCreateThunderBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import java.lang.String
import java.util.*

class CreateThunderActivity : BaseActivity<ActivityCreateThunderBinding>(R.layout.activity_create_thunder) {
    private var imageClicked = false
    private var infiniteChecked = false
    private lateinit var inputMethodManager:InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inputMethodManager=getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        checkTunderName()
        imageSelected()
        initDatePickerDialog()
        initTimePickerDialog()
        clickInfinite()
        checkThunderPlace()
        checkTunderMember()
        checkTunderExplanation()
    }

    private fun clickInfinite(){
        binding.ivCreatethunderCheck.setOnClickListener{
            if(!infiniteChecked){
                binding.ivCreatethunderCheck.setImageResource(R.drawable.ic_icn_check_active)
                val infiniteText = resources.getString(R.string.createthunder_infinite)
                binding.etCreatethunderPeopleNumber.setText(infiniteText)
                inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                binding.etCreatethunderPeopleNumber.isFocusable=false
                infiniteChecked=true
            }
            else{
                binding.ivCreatethunderCheck.setImageResource(R.drawable.ic_icn_check_inactive)
                binding.etCreatethunderPeopleNumber.text.clear()
                binding.etCreatethunderPeopleNumber.isFocusableInTouchMode=true
                binding.etCreatethunderPeopleNumber.requestFocus()
                inputMethodManager.showSoftInput(binding.etCreatethunderPeopleNumber, InputMethodManager.SHOW_IMPLICIT)
                infiniteChecked=false
            }
        }
    }

    private fun btnActive(){
        with(binding){
            if(etCreatethunderName.text.toString()!="" && imageClicked && tvCreatethunderDate.text.toString()!="" && tvCreatethunderTime.text.toString()!="" && etCreatethunderPlace.text.toString()!="" && etCreatethunderPeopleNumber.text.toString()!="" && etCreatethunderExplanation.text.toString()!=""){
                tvCreatethunderFinish.isClickable=true
                tvCreatethunderFinish.setTextColor(ContextCompat.getColor(this@CreateThunderActivity, R.color.main_green))
            }
            else{
                tvCreatethunderFinish.isClickable=false
                tvCreatethunderFinish.setTextColor(ContextCompat.getColor(this@CreateThunderActivity, R.color.gray_gray01))
            }
        }
    }

    private fun checkTunderName(){
        binding.etCreatethunderName.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                btnActive()
            }
        })
    }

    private fun checkThunderPlace(){
        binding.etCreatethunderPlace.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                btnActive()
            }
        })
    }

    private fun checkTunderMember(){
        binding.etCreatethunderPeopleNumber.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                btnActive()
            }
        })
    }

    private fun checkTunderExplanation(){
        binding.etCreatethunderExplanation.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                btnActive()
            }
        })
    }

    private fun imageSelected(){
        with(binding){
            ivCreatethunderDo.setOnClickListener{
                ivCreatethunderDo.setImageResource(R.drawable.ic_img_do_active)
                ivCreatethunderDo.isSelected=true
                ivCreatethunderGo.setImageResource(R.drawable.ic_img_go_inactive)
                ivCreatethunderGo.isSelected=false
                ivCreatethunderEat.setImageResource(R.drawable.ic_img_eat_inactive)
                ivCreatethunderEat.isSelected=false
                imageClicked=true
                btnActive()
            }
            ivCreatethunderGo.setOnClickListener{
                ivCreatethunderDo.setImageResource(R.drawable.ic_img_do_inactive)
                ivCreatethunderDo.isSelected=false
                ivCreatethunderGo.setImageResource(R.drawable.ic_img_go_active)
                ivCreatethunderGo.isSelected=true
                ivCreatethunderEat.setImageResource(R.drawable.ic_img_eat_inactive)
                ivCreatethunderEat.isSelected=false
                imageClicked=true
                btnActive()
            }
            ivCreatethunderEat.setOnClickListener{
                ivCreatethunderDo.setImageResource(R.drawable.ic_img_do_inactive)
                ivCreatethunderDo.isSelected=false
                ivCreatethunderGo.setImageResource(R.drawable.ic_img_go_inactive)
                ivCreatethunderGo.isSelected=false
                ivCreatethunderEat.setImageResource(R.drawable.ic_img_eat_active)
                ivCreatethunderEat.isSelected=true
                imageClicked=true
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

                    binding.tvCreatethunderDate.text=dateString
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
        binding.tvCreatethunderTime.setOnClickListener{
            val cal = Calendar.getInstance()
            val timeSetListener=TimePickerDialog.OnTimeSetListener{
                    view, hourOfDay, minute->
                val timeString = String.format("%02d:%02d", hourOfDay, minute)
                binding.tvCreatethunderTime.text=timeString
                btnActive()
            }
            val timePickerDialog = TimePickerDialog(this, R.style.TimePickerTheme, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false)
            val textColor = ContextCompat.getColor(this, R.color.gray_black)
            timePickerDialog.show()
            timePickerDialog.getButton(TimePickerDialog.BUTTON_POSITIVE).setTextColor(textColor)
            timePickerDialog.getButton(TimePickerDialog.BUTTON_NEGATIVE).setTextColor(textColor)
        }
    }
}