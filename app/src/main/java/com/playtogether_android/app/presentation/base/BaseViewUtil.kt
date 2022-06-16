package com.playtogether_android.app.presentation.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.playtogether_android.app.R

sealed class BaseViewUtil {
    abstract class BaseActivity<T : ViewDataBinding>(
        @LayoutRes private val layoutResId: Int
    ) : AppCompatActivity() {
        private var _binding: T? = null
        val binding get() = _binding!!


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            _binding = DataBindingUtil.setContentView(this, layoutResId)

        }
    }

    abstract class BaseBottomDialogFragment<T : ViewDataBinding>(@LayoutRes val layout: Int) :
        BottomSheetDialogFragment() {
        private var _binding: T? = null
        protected val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = DataBindingUtil.inflate(inflater, layout, container, false)
            binding.lifecycleOwner = viewLifecycleOwner
            return binding.root
        }

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return BottomSheetDialog(requireContext(), R.style.BottomDialog)
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

        abstract fun initView()
    }

    abstract class BaseFragment<T : ViewDataBinding>(@LayoutRes private val layoutRes: Int) :
        Fragment() {
        private var _binding: T? = null
        val binding get() = _binding!!


        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
            binding.lifecycleOwner = viewLifecycleOwner
            return binding.root
        }


        override fun onDestroyView() {
            _binding = null
            super.onDestroyView()
        }
    }
}
