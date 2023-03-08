package com.android.Bagir.challenge5gamesuit.startup.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.android.Bagir.challenge5gamesuit.TypeGameActivity
import com.android.Bagir.challenge5gamesuit.databinding.FragmentLandingPage3Binding
import java.util.jar.Attributes.Name

class LandingPage3Fragment : Fragment() {

    private var _binding : FragmentLandingPage3Binding? = null
    private val binding : FragmentLandingPage3Binding
    get() = _binding!!
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: Editor


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLandingPage3Binding.inflate(inflater, container, false)
        val view = binding.root
        SetUpshredPreference()
        return view
    }
    private  fun SetUpshredPreference() {
        val appContext = requireContext().applicationContext
        sharedPreferences = appContext.getSharedPreferences(TABLE_DATA, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.inpUsername.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.imgNextPage.isVisible = s.toString().trim().isNotEmpty()
            }
        })

        binding.imgNextPage.setOnClickListener {
            if (binding.inpUsername.text.isNotEmpty()){
                val username = binding.inpUsername.text.toString()
                editor.putString(NAME_ID,username)
                editor.apply()
                val intent = Intent(activity,TypeGameActivity::class.java)
                intent.putExtra("username",username)
                startActivity(intent)
            }

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        const val NAME_ID = "NAME_ID"
        const val TABLE_DATA = "USER_TABLE"
    }
}